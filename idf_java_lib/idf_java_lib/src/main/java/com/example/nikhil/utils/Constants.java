package com.example.nikhil.utils;


import java.util.Arrays;
import java.util.List;

public class Constants {

    // KINDs
    public static final String IMAGE_PLACEMENT_POLICY_KIND = "image_placement_policy";
    public static final String NETWORK_SECURITY_POLICY_KIND = "network_security_policy";
    public static final String NETWORK_SECURITY_RULE_KIND = "network_security_rule";
    public static final String NGT_POLICY_KIND = "ngt_policy";
    public static final String QOS_POLICY_KIND = "qos_policy";
    public static final String PROTECTION_RULE_KIND = "protection_rule";
    public static final String ACCESS_CONTROL_POLICY_KIND = "access_control_policy";
    public static final String STORAGE_POLICY_KIND = "storage_policy";
    public static final String IMAGE_RATE_LIMIT_KIND = "image_rate_limit";
    public static final String RECOVERY_PLAN_KIND = "recovery_plan";
    public static final String VM_KIND = "vm";
    public static final String MH_VM_KIND = "mh_vm";
    public static final String CLUSTER_KIND = "cluster";
    public static final String SUBNET_KIND = "subnet";
    public static final String VIRTUAL_NIC_KIND = "virtual_nic";
    public static final String HOST_NIC_KIND = "host_nic";
    public static final String HOST_KIND = "host";
    public static final String REPORT_KIND = "report";
    public static final String IMAGE_KIND = "image";
    public static final String MARKETPLACE_ITEM_KIND = "marketplace_item";
    public static final String BLUEPRINT_KIND = "blueprint";
    public static final String APP_KIND = "app";
    public static final String VOLUMEGROUP_KIND = "volumegroup";
    public static final String VM_HOST_AFFINITY_POLICY_KIND = "vm_host_affinity_policy";
    public static final String VM_ANTI_AFFINITY_POLICY_KIND = "vm_anti_affinity_policy";
    public static final String ACTION_RULE_KIND = "action_rule";

    public static final String CATEGORY = "category";
    public static final String ABAC_ENTITY_CAPABILITY = "abac_entity_capability";
    public static final String FILTER = "filter";
    public static final String VOLUME_GROUP_ENTITY_CAPABILITY = "volume_group_entity_capability";
    public static final String VM_HOST_AFFINITY_POLICY = "vm_host_affinity_policy";
    public static final String VM_ANTI_AFFINITY_POLICY = "vm_anti_affinity_policy";
    public static final String KIND = "kind";
    public static final String KIND_ID = "kind_id";
    public static final String CATEGORY_ID_LIST = "category_id_list";
    public static final String ENTITY_KIND = "entity_kind";
    public static final String ENTITY_UUID = "entity_uuid";
    public static final String CATEGORY_UUIDS = "category_uuids";
    public static final String VM_CATEGORY_UUIDS = "vm_category_uuids";
    public static final String HOST_CATEGORY_UUIDS = "host_category_uuids";

    // ALLOWED KINDS

    public static List<String> ALLOWED_ENTITY_KINDS = Arrays.asList(
            VM_KIND,
            MH_VM_KIND,
            CLUSTER_KIND,
            SUBNET_KIND,
            VIRTUAL_NIC_KIND,
            HOST_NIC_KIND,
            HOST_KIND,
            REPORT_KIND,
            IMAGE_KIND,
            MARKETPLACE_ITEM_KIND,
            BLUEPRINT_KIND,
            APP_KIND,
            VOLUMEGROUP_KIND
    );

    public static List<String> ALLOWED_POLICY_KINDS = Arrays.asList(
            IMAGE_PLACEMENT_POLICY_KIND,
            NETWORK_SECURITY_POLICY_KIND,
            NETWORK_SECURITY_RULE_KIND,
            NGT_POLICY_KIND,
            QOS_POLICY_KIND,
            PROTECTION_RULE_KIND,
            ACCESS_CONTROL_POLICY_KIND,
            STORAGE_POLICY_KIND,
            IMAGE_RATE_LIMIT_KIND,
            RECOVERY_PLAN_KIND,
            VM_HOST_AFFINITY_POLICY_KIND,
            VM_ANTI_AFFINITY_POLICY_KIND,
            ACTION_RULE_KIND
    );
}


