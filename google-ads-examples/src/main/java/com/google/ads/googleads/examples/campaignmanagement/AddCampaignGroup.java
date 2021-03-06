// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.ads.googleads.examples.campaignmanagement;

import com.beust.jcommander.Parameter;
import com.google.ads.googleads.examples.utils.ArgumentNames;
import com.google.ads.googleads.examples.utils.CodeSampleParams;
import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.lib.GoogleAdsException;
import com.google.ads.googleads.lib.utils.FieldMasks;
import com.google.ads.googleads.v0.errors.GoogleAdsError;
import com.google.ads.googleads.v0.resources.Campaign;
import com.google.ads.googleads.v0.resources.CampaignGroup;
import com.google.ads.googleads.v0.resources.CampaignName;
import com.google.ads.googleads.v0.services.CampaignGroupOperation;
import com.google.ads.googleads.v0.services.CampaignGroupServiceClient;
import com.google.ads.googleads.v0.services.CampaignOperation;
import com.google.ads.googleads.v0.services.CampaignServiceClient;
import com.google.ads.googleads.v0.services.MutateCampaignGroupsResponse;
import com.google.ads.googleads.v0.services.MutateCampaignResult;
import com.google.ads.googleads.v0.services.MutateCampaignsResponse;
import com.google.common.collect.ImmutableList;
import com.google.protobuf.StringValue;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This example adds a campaign group and then adds campaigns to the group. To get campaigns, run
 * GetCampaigns.java.
 */
public class AddCampaignGroup {

  private static class AddCampaignGroupParams extends CodeSampleParams {
    @Parameter(names = ArgumentNames.CUSTOMER_ID, required = true)
    private Long customerId;

    @Parameter(names = ArgumentNames.CAMPAIGN_ID, required = true)
    private List<Long> campaignIds;
  }

  public static void main(String[] args) {
    AddCampaignGroupParams params = new AddCampaignGroupParams();
    if (!params.parseArguments(args)) {

      // Either pass the required parameters for this example on the command line, or insert them
      // into the code here. See the parameter class definition above for descriptions.
      params.customerId = Long.parseLong("INSERT_CUSTOMER_ID_HERE");
      params.campaignIds =
          Arrays.asList(
              Long.valueOf("INSERT_CAMPAIGN_ID_HERE"), Long.valueOf("INSERT_CAMPAIGN_ID_HERE"));
    }

    GoogleAdsClient googleAdsClient;
    try {
      googleAdsClient = GoogleAdsClient.newBuilder().fromPropertiesFile().build();
    } catch (FileNotFoundException fnfe) {
      System.err.printf(
          "Failed to load GoogleAdsClient configuration from file. Exception: %s%n", fnfe);
      return;
    } catch (IOException ioe) {
      System.err.printf("Failed to create GoogleAdsClient. Exception: %s%n", ioe);
      return;
    }

    try {
      new AddCampaignGroup().runExample(googleAdsClient, params.customerId, params.campaignIds);
    } catch (GoogleAdsException gae) {
      // GoogleAdsException is the base class for most exceptions thrown by an API request.
      // Instances of this exception have a message and a GoogleAdsFailure that contains a
      // collection of GoogleAdsErrors that indicate the underlying causes of the
      // GoogleAdsException.
      System.err.printf(
          "Request ID %s failed due to GoogleAdsException. Underlying errors:%n",
          gae.getRequestId());
      int i = 0;
      for (GoogleAdsError googleAdsError : gae.getGoogleAdsFailure().getErrorsList()) {
        System.err.printf("  Error %d: %s%n", i++, googleAdsError);
      }
    }
  }

  /**
   * Creates a new CampaignGroup in the specified client account.
   *
   * @param googleAdsClient the Google Ads API client.
   * @param customerId the client customer ID.
   * @return resource name of the newly created campaign group.
   * @throws GoogleAdsException if an API request failed with one or more service errors.
   */
  private static String addCampaignGroup(GoogleAdsClient googleAdsClient, long customerId) {
    CampaignGroup campaignGroup =
        CampaignGroup.newBuilder()
            .setName(StringValue.of("Mars campaign group #" + System.currentTimeMillis()))
            .build();

    CampaignGroupOperation op =
        CampaignGroupOperation.newBuilder().setCreate(campaignGroup).build();

    try (CampaignGroupServiceClient campaignGroupServiceClient =
        googleAdsClient.getCampaignGroupServiceClient()) {
      MutateCampaignGroupsResponse response =
          campaignGroupServiceClient.mutateCampaignGroups(
              Long.toString(customerId), ImmutableList.of(op));
      String groupResourceName = response.getResults(0).getResourceName();
      System.out.printf("Added campaign group with resource name: %s%n", groupResourceName);
      return groupResourceName;
    }
  }

  /**
   * Adds campaigns to a CampaignGroup in the specified client account.
   *
   * @param googleAdsClient the Google Ads API client.
   * @param customerId the client customer ID.
   * @param campaignGroupResourceName the resource name of the campaign group.
   * @param campaignIds the IDs of the campaigns to add to the campaign group.
   * @throws GoogleAdsException if an API request failed with one or more service errors.
   */
  private static void addCampaignsToGroup(
      GoogleAdsClient googleAdsClient,
      long customerId,
      String campaignGroupResourceName,
      List<Long> campaignIds) {
    List<CampaignOperation> operations = new ArrayList<>();
    for (Long campaignId : campaignIds) {
      Campaign campaign =
          Campaign.newBuilder()
              .setResourceName(
                  CampaignName.format(Long.toString(customerId), Long.toString(campaignId)))
              .setCampaignGroup(StringValue.of(campaignGroupResourceName))
              .build();

      CampaignOperation op =
          CampaignOperation.newBuilder()
              .setUpdate(campaign)
              .setUpdateMask(FieldMasks.allSetFieldsOf(campaign))
              .build();
      operations.add(op);
    }

    try (CampaignServiceClient campaignServiceClient = googleAdsClient.getCampaignServiceClient()) {
      MutateCampaignsResponse response =
          campaignServiceClient.mutateCampaigns(Long.toString(customerId), operations);
      System.out.printf(
          "Added %d campaigns to campaign group with resource name %s:%n",
          response.getResultsCount(), campaignGroupResourceName);
      for (MutateCampaignResult campaignResponse : response.getResultsList()) {
        System.out.printf("\t%s%n", campaignResponse.getResourceName());
      }
    }
  }

  /**
   * Runs the example.
   *
   * @param googleAdsClient the Google Ads API client.
   * @param customerId the client customer ID.
   * @param campaignIds the IDs of the campaigns to add to the campaign group.
   * @throws GoogleAdsException if an API request failed with one or more service errors.
   */
  private void runExample(
      GoogleAdsClient googleAdsClient, long customerId, List<Long> campaignIds) {
    String campaignGroupResourceName = addCampaignGroup(googleAdsClient, customerId);
    addCampaignsToGroup(googleAdsClient, customerId, campaignGroupResourceName, campaignIds);
  }
}
