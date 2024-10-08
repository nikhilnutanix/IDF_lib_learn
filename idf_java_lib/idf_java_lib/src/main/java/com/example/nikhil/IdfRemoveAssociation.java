package com.example.nikhil;

import com.example.nikhil.utils.Constants;
import com.nutanix.insights.exception.InsightsInterfaceException;
import com.nutanix.insights.insights_interface.InsightsInterface;
import com.nutanix.insights.ifc.InsightsInterfaceProto;
import filter.protobuf.FilterOuterClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

@Component
@Slf4j
public class IdfRemoveAssociation implements CommandLineRunner {

    private final ApplicationArguments args;
    private String host = "localhost";
    private String port = "2027";
    private String category = "";
    private String kind = "";

    public IdfRemoveAssociation(ApplicationArguments args) {
        this.args = args;
    }

    public void remove_abac_entity_capability_List(InsightsInterface insightsInterface, String ext_id, String kind) {
        InsightsInterfaceProto.GetEntitiesArg.Builder getEntitiesArgBuilder1 = InsightsInterfaceProto.GetEntitiesArg
                .newBuilder();
        InsightsInterfaceProto.EntityGuid entityGuid1 = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.ABAC_ENTITY_CAPABILITY)
                .build();

        getEntitiesArgBuilder1.addEntityGuidList(entityGuid1);
        InsightsInterfaceProto.GetEntitiesArg getEntitiesArg1 = getEntitiesArgBuilder1.build();
        InsightsInterfaceProto.GetEntitiesRet getEntitiesRet1 = null;
        try {
            getEntitiesRet1 = insightsInterface.getEntities(getEntitiesArg1);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }

        List<String> AssociationIdList = new ArrayList<>();

        assert getEntitiesRet1 != null;
        for (InsightsInterfaceProto.Entity entity : getEntitiesRet1.getEntityList()) {
            for (InsightsInterfaceProto.NameTimeValuePair entityAttribute : entity.getAttributeDataMapList()) {
                if (entityAttribute.getName().equals(Constants.CATEGORY_ID_LIST)) {
                    for (String value : entityAttribute.getValue().getStrList().getValueListList()) {
                        if (value.equals(ext_id)) {
                            // log.info("Category ID List: " +
                            // entityAttribute.getValue().getStrList().getValueListList());
                            for (InsightsInterfaceProto.NameTimeValuePair entityAttribute1 : entity
                                    .getAttributeDataMapList()) {
                                if (entityAttribute1.getName().equals(Constants.KIND)) {
                                    if (entityAttribute1.getValue().getStrValue().equals(kind)) {
                                        AssociationIdList.add(entity.getEntityGuid().getEntityId());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        log.info("AssociationIdList: " + AssociationIdList);

        for (String AssociationId : AssociationIdList) {
            InsightsInterfaceProto.EntityGuid entityGuid2 = InsightsInterfaceProto.EntityGuid.newBuilder()
                    .setEntityTypeName(Constants.ABAC_ENTITY_CAPABILITY)
                    .setEntityId(AssociationId)
                    .build();
            InsightsInterfaceProto.DeleteEntityArg deleteEntityArg = InsightsInterfaceProto.DeleteEntityArg.newBuilder()
                    .setEntityGuid(entityGuid2)
                    .build();
            try {
                InsightsInterfaceProto.DeleteEntityRet deleteEntityRet = insightsInterface
                        .deleteEntity(deleteEntityArg);
                log.info("DeleteEntityRet: " + deleteEntityRet);
            } catch (InsightsInterfaceException e) {
                log.error("Error: " + e);
            }
        }
    }

    public void remove_volume_group_entity_capability_List(InsightsInterface insightsInterface, String ext_id) {
        InsightsInterfaceProto.GetEntitiesArg.Builder getEntitiesArgBuilder1 = InsightsInterfaceProto.GetEntitiesArg
                .newBuilder();
        InsightsInterfaceProto.EntityGuid entityGuid1 = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.VOLUME_GROUP_ENTITY_CAPABILITY)
                .build();

        getEntitiesArgBuilder1.addEntityGuidList(entityGuid1);
        InsightsInterfaceProto.GetEntitiesArg getEntitiesArg1 = getEntitiesArgBuilder1.build();
        InsightsInterfaceProto.GetEntitiesRet getEntitiesRet1 = null;
        try {
            getEntitiesRet1 = insightsInterface.getEntities(getEntitiesArg1);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }

        List<String> AssociationIdList = new ArrayList<>();

        assert getEntitiesRet1 != null;
        for (InsightsInterfaceProto.Entity entity : getEntitiesRet1.getEntityList()) {
            for (InsightsInterfaceProto.NameTimeValuePair entityAttribute : entity.getAttributeDataMapList()) {
                if (entityAttribute.getName().equals(Constants.CATEGORY_ID_LIST)) {
                    for (String value : entityAttribute.getValue().getStrList().getValueListList()) {
                        if (value.equals(ext_id)) {
                            AssociationIdList.add(entity.getEntityGuid().getEntityId());
                        }
                    }
                }
            }
        }

        log.info("AssociationIdList: " + AssociationIdList);

        for (String AssociationId : AssociationIdList) {
            InsightsInterfaceProto.EntityGuid entityGuid2 = InsightsInterfaceProto.EntityGuid.newBuilder()
                    .setEntityTypeName(Constants.VOLUME_GROUP_ENTITY_CAPABILITY)
                    .setEntityId(AssociationId)
                    .build();
            InsightsInterfaceProto.DeleteEntityArg deleteEntityArg = InsightsInterfaceProto.DeleteEntityArg.newBuilder()
                    .setEntityGuid(entityGuid2)
                    .build();
            try {
                InsightsInterfaceProto.DeleteEntityRet deleteEntityRet = insightsInterface
                        .deleteEntity(deleteEntityArg);
                log.info("DeleteEntityRet: " + deleteEntityRet);
            } catch (InsightsInterfaceException e) {
                log.error("Error: " + e);
            }
        }
    }

    public void remove_vm_host_affinity_policy_List(InsightsInterface insightsInterface, String ext_id) {
        InsightsInterfaceProto.GetEntitiesArg.Builder getEntitiesArgBuilder1 = InsightsInterfaceProto.GetEntitiesArg
                .newBuilder();
        InsightsInterfaceProto.EntityGuid entityGuid1 = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.VM_HOST_AFFINITY_POLICY)
                .build();

        getEntitiesArgBuilder1.addEntityGuidList(entityGuid1);
        InsightsInterfaceProto.GetEntitiesArg getEntitiesArg1 = getEntitiesArgBuilder1.build();
        InsightsInterfaceProto.GetEntitiesRet getEntitiesRet1 = null;
        try {
            getEntitiesRet1 = insightsInterface.getEntities(getEntitiesArg1);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }

        List<String> AssociationIdList = new ArrayList<>();

        assert getEntitiesRet1 != null;
        for (InsightsInterfaceProto.Entity entity : getEntitiesRet1.getEntityList()) {
            boolean flag = false;
            for (InsightsInterfaceProto.NameTimeValuePair entityAttribute : entity.getAttributeDataMapList()) {
                if (entityAttribute.getName().equals(Constants.VM_CATEGORY_UUIDS)) {
                    for (String value : entityAttribute.getValue().getStrList().getValueListList()) {
                        if (value.equals(ext_id)) {
                            AssociationIdList.add(entity.getEntityGuid().getEntityId());
                            flag = true;
                            break;
                        }
                    }
                }
                if (entityAttribute.getName().equals(Constants.HOST_CATEGORY_UUIDS)) {
                    for (String value : entityAttribute.getValue().getStrList().getValueListList()) {
                        if (value.equals(ext_id)) {
                            AssociationIdList.add(entity.getEntityGuid().getEntityId());
                            flag = true;
                            break;
                        }
                    }
                }
                if (flag) {
                    break;
                }
            }
        }

        log.info("AssociationIdList: " + AssociationIdList);

        for (String AssociationId : AssociationIdList) {
            InsightsInterfaceProto.EntityGuid entityGuid2 = InsightsInterfaceProto.EntityGuid.newBuilder()
                    .setEntityTypeName(Constants.VM_HOST_AFFINITY_POLICY)
                    .setEntityId(AssociationId)
                    .build();
            InsightsInterfaceProto.DeleteEntityArg deleteEntityArg = InsightsInterfaceProto.DeleteEntityArg.newBuilder()
                    .setEntityGuid(entityGuid2)
                    .build();
            try {
                InsightsInterfaceProto.DeleteEntityRet deleteEntityRet = insightsInterface
                        .deleteEntity(deleteEntityArg);
                log.info("DeleteEntityRet: " + deleteEntityRet);
            } catch (InsightsInterfaceException e) {
                log.error("Error: " + e);
            }
        }
    }

    public void remove_vm_anti_affinity_policy_List(InsightsInterface insightsInterface, String ext_id) {
        InsightsInterfaceProto.GetEntitiesArg.Builder getEntitiesArgBuilder1 = InsightsInterfaceProto.GetEntitiesArg
                .newBuilder();
        InsightsInterfaceProto.EntityGuid entityGuid1 = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.VM_ANTI_AFFINITY_POLICY)
                .build();

        getEntitiesArgBuilder1.addEntityGuidList(entityGuid1);
        InsightsInterfaceProto.GetEntitiesArg getEntitiesArg1 = getEntitiesArgBuilder1.build();
        InsightsInterfaceProto.GetEntitiesRet getEntitiesRet1 = null;
        try {
            getEntitiesRet1 = insightsInterface.getEntities(getEntitiesArg1);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }

        List<String> AssociationIdList = new ArrayList<>();

        assert getEntitiesRet1 != null;
        for (InsightsInterfaceProto.Entity entity : getEntitiesRet1.getEntityList()) {
            for (InsightsInterfaceProto.NameTimeValuePair entityAttribute : entity.getAttributeDataMapList()) {
                if (entityAttribute.getName().equals(Constants.CATEGORY_UUIDS)) {
                    for (String value : entityAttribute.getValue().getStrList().getValueListList()) {
                        if (value.equals(ext_id)) {
                            AssociationIdList.add(entity.getEntityGuid().getEntityId());
                            break;
                        }
                    }
                }
            }
        }

        log.info("AssociationIdList: " + AssociationIdList);

        for (String AssociationId : AssociationIdList) {
            InsightsInterfaceProto.EntityGuid entityGuid2 = InsightsInterfaceProto.EntityGuid.newBuilder()
                    .setEntityTypeName(Constants.VM_ANTI_AFFINITY_POLICY)
                    .setEntityId(AssociationId)
                    .build();
            InsightsInterfaceProto.DeleteEntityArg deleteEntityArg = InsightsInterfaceProto.DeleteEntityArg.newBuilder()
                    .setEntityGuid(entityGuid2)
                    .build();
            try {
                InsightsInterfaceProto.DeleteEntityRet deleteEntityRet = insightsInterface
                        .deleteEntity(deleteEntityArg);
                log.info("DeleteEntityRet: " + deleteEntityRet);
            } catch (InsightsInterfaceException e) {
                log.error("Error: " + e);
            }
        }
    }

    public void remove_filter_List(InsightsInterface insightsInterface, String ext_id, String entity_kind) {
        InsightsInterfaceProto.GetEntitiesArg.Builder getEntitiesArgBuilder1 = InsightsInterfaceProto.GetEntitiesArg
                .newBuilder();
        InsightsInterfaceProto.EntityGuid entityGuid1 = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.FILTER)
                .build();

        getEntitiesArgBuilder1.addEntityGuidList(entityGuid1);
        InsightsInterfaceProto.GetEntitiesArg getEntitiesArg1 = getEntitiesArgBuilder1.build();
        InsightsInterfaceProto.GetEntitiesRet getEntitiesRet1 = null;
        try {
            getEntitiesRet1 = insightsInterface.getEntities(getEntitiesArg1);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }

        List<String> AssociationIdList = new ArrayList<>();

        assert getEntitiesRet1 != null;
        for (InsightsInterfaceProto.Entity entity : getEntitiesRet1.getEntityList()) {
            for (InsightsInterfaceProto.NameTimeValuePair entityAttribute : entity.getAttributeDataMapList()) {
                if (entityAttribute.getName().equals("__zprotobuf__")) {
                    FilterOuterClass.Filter filter = null;
                    try {
                        filter = FilterOuterClass.Filter.parseFrom(entityAttribute.getValue().getBytesValue());
                    } catch (Exception e) {
                        log.error("Error: " + e);
                        break;
                    }
                    assert filter != null;
                    // assert filter.getFilterExpressionsCount() > 0;
                    if (filter.getEntityKind().equals(entity_kind)) {
                        for (FilterOuterClass.FilterExpression filterExpression : filter.getFilterExpressionsList()) {
                            if (filterExpression.getLhsEntityType().equals(Constants.CATEGORY) && filterExpression
                                    .getOperator().equals(FilterOuterClass.FilterExpression.Operator.kIn)) {
                                if (filterExpression.getEntityUuids().contains(ext_id)) {
                                    AssociationIdList.add(entity.getEntityGuid().getEntityId());
                                }
                            }
                        }
                    }
                }
            }
        }

        log.info("AssociationIdList: " + AssociationIdList);

        for (String AssociationId : AssociationIdList) {
            InsightsInterfaceProto.EntityGuid entityGuid2 = InsightsInterfaceProto.EntityGuid.newBuilder()
                    .setEntityTypeName(Constants.FILTER)
                    .setEntityId(AssociationId)
                    .build();
            InsightsInterfaceProto.DeleteEntityArg deleteEntityArg = InsightsInterfaceProto.DeleteEntityArg.newBuilder()
                    .setEntityGuid(entityGuid2)
                    .build();
            try {
                InsightsInterfaceProto.DeleteEntityRet deleteEntityRet = insightsInterface
                        .deleteEntity(deleteEntityArg);
                log.info("DeleteEntityRet: " + deleteEntityRet);
            } catch (InsightsInterfaceException e) {
                log.error("Error: " + e);
            }
        }
    }

    public void remove_category_associations() {
        // Check if category is empty
        if (this.category.isEmpty()) {
            log.error("Category is empty");
            return;
        }
        // Define a regular expression for key/value format
        String regex = "^[^/]+/[^/]+$";
        // Validate the category input
        if (!this.category.matches(regex)) {
            log.error("Category is not in key/value format");
            return;
        }
        // validate kind if it not from ALLOWED_POLICY_KINDS or ALLOWED_ENTITY_KINDS
        if (!Constants.ALLOWED_POLICY_KINDS.contains(this.kind)
                && !Constants.ALLOWED_ENTITY_KINDS.contains(this.kind)) {
            log.error("Kind is not in ALLOWED_POLICY_KINDS or ALLOWED_ENTITY_KINDS");
            return;
        }

        int port_num = Integer.parseInt(this.port);
        InsightsInterface insightsInterface = new InsightsInterface(this.host, port_num);
        InsightsInterfaceProto.GetEntitiesArg.Builder getEntitiesArgBuilder = InsightsInterfaceProto.GetEntitiesArg
                .newBuilder();
        InsightsInterfaceProto.EntityGuid entityGuid = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.CATEGORY)
                .build();

        getEntitiesArgBuilder.addEntityGuidList(entityGuid);
        InsightsInterfaceProto.GetEntitiesArg getEntitiesArg = getEntitiesArgBuilder.build();
        InsightsInterfaceProto.GetEntitiesRet getEntitiesRet = null;
        try {
            getEntitiesRet = insightsInterface.getEntities(getEntitiesArg);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }

        // search in the response for the entity with fq name "category"
        // if found, print the entity id
        boolean bool_flag = false;
        String ext_id = "";
        assert getEntitiesRet != null;
        for (InsightsInterfaceProto.Entity entity : getEntitiesRet.getEntityList()) {
            for (InsightsInterfaceProto.NameTimeValuePair entityAttribute : entity.getAttributeDataMapList()) {
                if (entityAttribute.getName().equals("fq_name")
                        && entityAttribute.getValue().getStrValue().equals(this.category)) {
                    bool_flag = true;
                    ext_id = entity.getEntityGuid().getEntityId();
                    break;
                }
            }
            if (bool_flag) {
                break;
            }
        }

        if (!bool_flag) {
            log.error("Category not found");
            return;
        }

        log.info("Category found with ext_id: " + ext_id);

        if (this.kind.equals(Constants.VOLUMEGROUP_KIND)) {
            remove_volume_group_entity_capability_List(insightsInterface, ext_id);
        } else if (this.kind.equals(Constants.VM_HOST_AFFINITY_POLICY_KIND)) {
            remove_vm_host_affinity_policy_List(insightsInterface, ext_id);
        } else if (this.kind.equals(Constants.VM_ANTI_AFFINITY_POLICY_KIND)) {
            remove_vm_anti_affinity_policy_List(insightsInterface, ext_id);
        } else if (Constants.ALLOWED_ENTITY_KINDS.contains(this.kind)) {
            remove_abac_entity_capability_List(insightsInterface, ext_id, this.kind);
        } else if (Constants.ALLOWED_POLICY_KINDS.contains(this.kind)) {
            remove_filter_List(insightsInterface, ext_id, this.kind);
        } else {
            log.error("Kind is not in ALLOWED_POLICY_KINDS or ALLOWED_ENTITY_KINDS");
        }

    }

    @Override
    public void run(String... args) throws Exception {

        this.args.getOptionNames().forEach(optionName -> {
            // System.out.println(optionName + " = " +
            // this.args.getOptionValues(optionName));
            switch (optionName) {
                case "host":
                    this.host = this.args.getOptionValues(optionName).get(0);
                    break;
                case "port":
                    this.port = this.args.getOptionValues(optionName).get(0);
                    break;
                case "category":
                    this.category = this.args.getOptionValues(optionName).get(0);
                    break;
                case "kind":
                    this.kind = this.args.getOptionValues(optionName).get(0);
                    break;
            }
        });

        System.out.println("host: " + this.host);
        System.out.println("port: " + this.port);
        System.out.println("category: " + this.category);
        System.out.println("kind: " + this.kind);

        remove_category_associations();
        exit(0);
    }

}
