// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/ads/googleads/v0/services/geo_target_constant_service.proto

package com.google.ads.googleads.v0.services;

public final class GeoTargetConstantServiceProto {
  private GeoTargetConstantServiceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_google_ads_googleads_v0_services_GetGeoTargetConstantRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_ads_googleads_v0_services_GetGeoTargetConstantRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\nBgoogle/ads/googleads/v0/services/geo_t" +
      "arget_constant_service.proto\022 google.ads" +
      ".googleads.v0.services\032;google/ads/googl" +
      "eads/v0/resources/geo_target_constant.pr" +
      "oto\032\034google/api/annotations.proto\032\036googl" +
      "e/protobuf/wrappers.proto\"4\n\033GetGeoTarge" +
      "tConstantRequest\022\025\n\rresource_name\030\001 \001(\t2" +
      "\332\001\n\030GeoTargetConstantService\022\275\001\n\024GetGeoT" +
      "argetConstant\022=.google.ads.googleads.v0." +
      "services.GetGeoTargetConstantRequest\0324.g" +
      "oogle.ads.googleads.v0.resources.GeoTarg" +
      "etConstant\"0\202\323\344\223\002*\022(/v0/{resource_name=g" +
      "eoTargetConstants/*}B\335\001\n$com.google.ads." +
      "googleads.v0.servicesB\035GeoTargetConstant" +
      "ServiceProtoP\001ZHgoogle.golang.org/genpro" +
      "to/googleapis/ads/googleads/v0/services;" +
      "services\242\002\003GAA\252\002 Google.Ads.GoogleAds.V0" +
      ".Services\312\002 Google\\Ads\\GoogleAds\\V0\\Serv" +
      "icesb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.ads.googleads.v0.resources.GeoTargetConstantProto.getDescriptor(),
          com.google.api.AnnotationsProto.getDescriptor(),
          com.google.protobuf.WrappersProto.getDescriptor(),
        }, assigner);
    internal_static_google_ads_googleads_v0_services_GetGeoTargetConstantRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_google_ads_googleads_v0_services_GetGeoTargetConstantRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_google_ads_googleads_v0_services_GetGeoTargetConstantRequest_descriptor,
        new java.lang.String[] { "ResourceName", });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.google.api.AnnotationsProto.http);
    com.google.protobuf.Descriptors.FileDescriptor
        .internalUpdateFileDescriptor(descriptor, registry);
    com.google.ads.googleads.v0.resources.GeoTargetConstantProto.getDescriptor();
    com.google.api.AnnotationsProto.getDescriptor();
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
