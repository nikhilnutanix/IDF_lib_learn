package constants

import(
	"github.com/deckarep/golang-set"
)

func ToSliceOfInterface[T any](in []T) []interface{} {
	out := make([]interface{}, 0, len(in))
	for _, v := range in {
		out = append(out, v)
	}
	return out
}

var ALLOWED_POLICY_KINDS = mapset.NewSetFromSlice(ToSliceOfInterface([]string{
	IMAGE_PLACEMENT_POLICY_KIND,
	NETWORK_SECURITY_POLICY_KIND,
	NETWORK_SECURITY_RULE_KIND,
	AFFINITY_RULE_KIND,
	NGT_POLICY_KIND,
	QOS_POLICY_KIND,
	PROTECTION_RULE_KIND,
	ACCESS_CONTROL_POLICY_KIND,
	STORAGE_POLICY_KIND,
	IMAGE_RATE_LIMIT_KIND,
	RECOVERY_PLAN_KIND,
	POLICY_SCHEMA_KIND,
	VM_HOST_AFFINITY_POLICY_KIND,
	VM_ANTI_AFFINITY_POLICY_KIND,
	ACTION_RULE_KIND,
}))

var ALLOWED_ENTITY_KINDS = mapset.NewSetFromSlice(ToSliceOfInterface([]string{
	VM_KIND,
	MH_VM_KIND,
	VOLUMEGROUP_KIND,
	CLUSTER_KIND,
	SUBNET_KIND,
	HOST_KIND,
	REPORT_KIND,
	IMAGE_KIND,
	MARKETPLACE_ITEM_KIND,
	BLUEPRINT_KIND,
	APP_KIND,
	BUNDLE_KIND,
	HOST_NIC_KIND,
	VIRTUAL_NIC_KIND,
}))

const (
	ABAC_ENTITY_CAPABILITY = "abac_entity_capability"
	VOLUME_GROUP_ENTITY_CAPABILITY = "volume_group_entity_capability"
	FILTER = "filter"
	VM_HOST_AFFINITY_POLICY = "vm_host_affinity_policy"
	VM_ANTI_AFFINITY_POLICY = "vm_anti_affinity_policy"
	KIND = "kind"
	KIND_ID = "kind_id"
	CATEGORY_ID_LIST = "category_id_list"
	IMAGE_PLACEMENT_POLICY_KIND = "image_placement_policy"
	NETWORK_SECURITY_POLICY_KIND = "network_security_policy"
	NETWORK_SECURITY_RULE_KIND = "network_security_rule"
	AFFINITY_RULE_KIND = "affinity_rule"
	NGT_POLICY_KIND = "ngt_policy"
	QOS_POLICY_KIND = "qos_policy"
	PROTECTION_RULE_KIND = "protection_rule"
	ACCESS_CONTROL_POLICY_KIND = "access_control_policy"
	STORAGE_POLICY_KIND = "storage_policy"
	IMAGE_RATE_LIMIT_KIND = "image_rate_limit"
	RECOVERY_PLAN_KIND = "recovery_plan"
	POLICY_SCHEMA_KIND = "policy_schema"
	VM_HOST_AFFINITY_POLICY_KIND = "vm_host_affinity_policy"
	VM_ANTI_AFFINITY_POLICY_KIND = "vm_anti_affinity_policy"
	ACTION_RULE_KIND = "action_rule"

	VM_KIND = "vm"
	MH_VM_KIND = "mh_vm"
	VOLUMEGROUP_KIND = "volumegroup"
	CLUSTER_KIND = "cluster"
	SUBNET_KIND = "subnet"
	HOST_KIND = "host"
	REPORT_KIND = "report"
	IMAGE_KIND = "image"
	MARKETPLACE_ITEM_KIND = "marketplace_item"
	BLUEPRINT_KIND = "blueprint"
	APP_KIND = "app"
	BUNDLE_KIND = "bundle"
	HOST_NIC_KIND = "host_nic"
	VIRTUAL_NIC_KIND = "virtual_nic"
)