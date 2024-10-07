package com.example.nikhil;

import com.example.nikhil.utils.Constants;
import com.nutanix.insights.exception.InsightsInterfaceException;
import com.nutanix.insights.insights_interface.InsightsInterface;
import com.nutanix.insights.ifc.InsightsInterfaceProto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import filter.protobuf.FilterOuterClass;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.lang.System.exit;

@Component
@Slf4j
public class IdfCreateAssociation implements CommandLineRunner {

    private final ApplicationArguments args;
    private String host = "localhost";
    private String port = "2027";
    private String category = "";
    private String kind = "";
    private String kind_id = "";

    public IdfCreateAssociation(ApplicationArguments args) {
        this.args = args;
    }

    public void create_abac_entity_capability(InsightsInterface insightsInterface, String ext_id, String kind,
            String kind_id) {
        InsightsInterfaceProto.GetEntitiesArg.Builder getEntitiesArgBuilder = InsightsInterfaceProto.GetEntitiesArg
                .newBuilder();
        InsightsInterfaceProto.EntityGuid entityGuid = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.ABAC_ENTITY_CAPABILITY)
                .build();

        getEntitiesArgBuilder.addEntityGuidList(entityGuid);
        InsightsInterfaceProto.GetEntitiesArg getEntitiesArg = getEntitiesArgBuilder.build();
        InsightsInterfaceProto.GetEntitiesRet getEntitiesRet = null;
        try {
            getEntitiesRet = insightsInterface.getEntities(getEntitiesArg);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }
        // check whether the entity with kind and kind_id exists
        String ext_id1 = "";
        assert getEntitiesRet != null;
        for (InsightsInterfaceProto.Entity entity : getEntitiesRet.getEntityList()) {
            for (InsightsInterfaceProto.NameTimeValuePair entityAttribute : entity.getAttributeDataMapList()) {
                if (entityAttribute.getName().equals(Constants.CATEGORY_ID_LIST)) {
                    for (String value : entityAttribute.getValue().getStrList().getValueListList()) {
                        if (value.equals(ext_id)) {
                            for (InsightsInterfaceProto.NameTimeValuePair entityAttribute1 : entity
                                    .getAttributeDataMapList()) {
                                if (entityAttribute1.getName().equals(Constants.KIND)
                                        && entityAttribute1.getValue().getStrValue().equals(kind)) {
                                    for (InsightsInterfaceProto.NameTimeValuePair entityAttribute2 : entity
                                            .getAttributeDataMapList()) {
                                        if (entityAttribute2.getName().equals(Constants.KIND_ID)
                                                && entityAttribute2.getValue().getStrValue().equals(kind_id)) {
                                            ext_id1 = entity.getEntityGuid().getEntityId();
                                            log.info("Association already exists with ext_id: " + ext_id1);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // create update entity arg
        UUID AssociationId = UUID.randomUUID();
        InsightsInterfaceProto.EntityGuid entityGuid1 = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.ABAC_ENTITY_CAPABILITY)
                .setEntityId(AssociationId.toString())
                .build();

        List<InsightsInterfaceProto.AttributeDataArg> attributeDataArgList_ = Arrays.asList(
                InsightsInterfaceProto.AttributeDataArg.newBuilder()
                        .setAttributeData(InsightsInterfaceProto.AttributeData.newBuilder()
                                .setName(Constants.CATEGORY_ID_LIST)
                                .setValue(InsightsInterfaceProto.DataValue.newBuilder()
                                        .setStrList(InsightsInterfaceProto.DataValue.StrList.newBuilder()
                                                .addAllValueList(Arrays.asList(ext_id))
                                                .build())
                                        .build())
                                .build())
                        .build(),
                InsightsInterfaceProto.AttributeDataArg.newBuilder()
                        .setAttributeData(InsightsInterfaceProto.AttributeData.newBuilder()
                                .setName(Constants.KIND)
                                .setValue(InsightsInterfaceProto.DataValue.newBuilder()
                                        .setStrValue(kind)
                                        .build())
                                .build())
                        .build(),
                InsightsInterfaceProto.AttributeDataArg.newBuilder()
                        .setAttributeData(InsightsInterfaceProto.AttributeData.newBuilder()
                                .setName(Constants.KIND_ID)
                                .setValue(InsightsInterfaceProto.DataValue.newBuilder()
                                        .setStrValue(kind_id)
                                        .build())
                                .build())
                        .build());

        InsightsInterfaceProto.UpdateEntityArg updateEntityArg = InsightsInterfaceProto.UpdateEntityArg.newBuilder()
                .setEntityGuid(entityGuid1)
                .addAllAttributeDataArgList(attributeDataArgList_)
                .build();

        // log.info("AttributeDataArgBuilder: " + updateEntityArg);

        try {
            InsightsInterfaceProto.UpdateEntityRet updateEntityRet = insightsInterface.updateEntity(updateEntityArg);
            log.info("UpdateEntityRet: " + updateEntityRet);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }
    }

    public void create_volume_group_entity_capability(InsightsInterface insightsInterface, String ext_id,
            String kind_id) {
        InsightsInterfaceProto.GetEntitiesArg.Builder getEntitiesArgBuilder = InsightsInterfaceProto.GetEntitiesArg
                .newBuilder();
        InsightsInterfaceProto.EntityGuid entityGuid = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.VOLUME_GROUP_ENTITY_CAPABILITY)
                .build();

        getEntitiesArgBuilder.addEntityGuidList(entityGuid);
        InsightsInterfaceProto.GetEntitiesArg getEntitiesArg = getEntitiesArgBuilder.build();
        InsightsInterfaceProto.GetEntitiesRet getEntitiesRet = null;
        try {
            getEntitiesRet = insightsInterface.getEntities(getEntitiesArg);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }
        // check whether the entity with kind and kind_id exists
        String ext_id1 = "";
        assert getEntitiesRet != null;
        for (InsightsInterfaceProto.Entity entity : getEntitiesRet.getEntityList()) {
            for (InsightsInterfaceProto.NameTimeValuePair entityAttribute : entity.getAttributeDataMapList()) {
                if (entityAttribute.getName().equals(Constants.CATEGORY_ID_LIST)) {
                    for (String value : entityAttribute.getValue().getStrList().getValueListList()) {
                        if (value.equals(ext_id)) {
                            for (InsightsInterfaceProto.NameTimeValuePair entityAttribute1 : entity
                                    .getAttributeDataMapList()) {
                                for (InsightsInterfaceProto.NameTimeValuePair entityAttribute2 : entity
                                        .getAttributeDataMapList()) {
                                    if (entityAttribute2.getName().equals(Constants.KIND_ID)
                                            && entityAttribute2.getValue().getStrValue().equals(kind_id)) {
                                        ext_id1 = entity.getEntityGuid().getEntityId();
                                        log.info("Association already exists with ext_id: " + ext_id1);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // create update entity arg
        UUID AssociationId = UUID.randomUUID();
        InsightsInterfaceProto.EntityGuid entityGuid1 = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.VOLUME_GROUP_ENTITY_CAPABILITY)
                .setEntityId(AssociationId.toString())
                .build();

        List<InsightsInterfaceProto.AttributeDataArg> attributeDataArgList_ = Arrays.asList(
                InsightsInterfaceProto.AttributeDataArg.newBuilder()
                        .setAttributeData(InsightsInterfaceProto.AttributeData.newBuilder()
                                .setName(Constants.CATEGORY_ID_LIST)
                                .setValue(InsightsInterfaceProto.DataValue.newBuilder()
                                        .setStrList(InsightsInterfaceProto.DataValue.StrList.newBuilder()
                                                .addAllValueList(Arrays.asList(ext_id))
                                                .build())
                                        .build())
                                .build())
                        .build(),
                InsightsInterfaceProto.AttributeDataArg.newBuilder()
                        .setAttributeData(InsightsInterfaceProto.AttributeData.newBuilder()
                                .setName(Constants.KIND_ID)
                                .setValue(InsightsInterfaceProto.DataValue.newBuilder()
                                        .setStrValue(kind_id)
                                        .build())
                                .build())
                        .build());

        InsightsInterfaceProto.UpdateEntityArg updateEntityArg = InsightsInterfaceProto.UpdateEntityArg.newBuilder()
                .setEntityGuid(entityGuid1)
                .addAllAttributeDataArgList(attributeDataArgList_)
                .build();

        try {
            InsightsInterfaceProto.UpdateEntityRet updateEntityRet = insightsInterface.updateEntity(updateEntityArg);
            log.info("UpdateEntityRet: " + updateEntityRet);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }

    }

    public void create_vm_host_affinity_policy(InsightsInterface insightsInterface, String category_id,
            String entity_id) {
        InsightsInterfaceProto.GetEntitiesArg.Builder getEntitiesArgBuilder = InsightsInterfaceProto.GetEntitiesArg
                .newBuilder();
        InsightsInterfaceProto.EntityGuid entityGuid = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.VM_HOST_AFFINITY_POLICY)
                .build();

        getEntitiesArgBuilder.addEntityGuidList(entityGuid);
        InsightsInterfaceProto.GetEntitiesArg getEntitiesArg = getEntitiesArgBuilder.build();
        InsightsInterfaceProto.GetEntitiesRet getEntitiesRet = null;
        try {
            getEntitiesRet = insightsInterface.getEntities(getEntitiesArg);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }
        // check whether the entity with kind and kind_id exists
        String ext_id1 = "";
        assert getEntitiesRet != null;
        for (InsightsInterfaceProto.Entity entity : getEntitiesRet.getEntityList()) {
            if (entity.getEntityGuid().getEntityId().equals(entity_id)) {
                for (InsightsInterfaceProto.NameTimeValuePair entityAttribute : entity.getAttributeDataMapList()) {
                    if (entityAttribute.getName().equals(Constants.VM_CATEGORY_UUIDS)) {
                        for (String value : entityAttribute.getValue().getStrList().getValueListList()) {
                            if (value.equals(category_id)) {
                                ext_id1 = entity.getEntityGuid().getEntityId();
                                log.info("Association already exists with ext_id: " + ext_id1);
                                return;
                            }
                        }
                    }
                }
                for (InsightsInterfaceProto.NameTimeValuePair entityAttribute : entity.getAttributeDataMapList()) {
                    if (entityAttribute.getName().equals(Constants.HOST_CATEGORY_UUIDS)) {
                        for (String value : entityAttribute.getValue().getStrList().getValueListList()) {
                            if (value.equals(category_id)) {
                                ext_id1 = entity.getEntityGuid().getEntityId();
                                log.info("Association already exists with ext_id: " + ext_id1);
                                return;
                            }
                        }
                    }
                }
            }
        }
        // create update entity arg
        String AssociationId = entity_id;
        InsightsInterfaceProto.EntityGuid entityGuid1 = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.VM_HOST_AFFINITY_POLICY)
                .setEntityId(AssociationId)
                .build();

        List<InsightsInterfaceProto.AttributeDataArg> attributeDataArgList_ = Arrays.asList(
                InsightsInterfaceProto.AttributeDataArg.newBuilder()
                        .setAttributeData(InsightsInterfaceProto.AttributeData.newBuilder()
                                .setName(Constants.VM_CATEGORY_UUIDS)
                                .setValue(InsightsInterfaceProto.DataValue.newBuilder()
                                        .setStrList(InsightsInterfaceProto.DataValue.StrList.newBuilder()
                                                .addAllValueList(Arrays.asList(category_id))
                                                .build())
                                        .build())
                                .build())
                        .build(),
                InsightsInterfaceProto.AttributeDataArg.newBuilder()
                        .setAttributeData(InsightsInterfaceProto.AttributeData.newBuilder()
                                .setName(Constants.HOST_CATEGORY_UUIDS)
                                .setValue(InsightsInterfaceProto.DataValue.newBuilder()
                                        .setStrList(InsightsInterfaceProto.DataValue.StrList.newBuilder()
                                                .addAllValueList(Arrays.asList(category_id))
                                                .build())
                                        .build())
                                .build())
                        .build());

        InsightsInterfaceProto.UpdateEntityArg updateEntityArg = InsightsInterfaceProto.UpdateEntityArg.newBuilder()
                .setEntityGuid(entityGuid1)
                .addAllAttributeDataArgList(attributeDataArgList_)
                .build();

        try {
            InsightsInterfaceProto.UpdateEntityRet updateEntityRet = insightsInterface.updateEntity(updateEntityArg);
            log.info("UpdateEntityRet: " + updateEntityRet);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }

    }

    public void create_vm_anti_affinity_policy(InsightsInterface insightsInterface, String category_id,
            String entity_id) {
        InsightsInterfaceProto.GetEntitiesArg.Builder getEntitiesArgBuilder = InsightsInterfaceProto.GetEntitiesArg
                .newBuilder();
        InsightsInterfaceProto.EntityGuid entityGuid = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.VM_ANTI_AFFINITY_POLICY)
                .build();

        getEntitiesArgBuilder.addEntityGuidList(entityGuid);
        InsightsInterfaceProto.GetEntitiesArg getEntitiesArg = getEntitiesArgBuilder.build();
        InsightsInterfaceProto.GetEntitiesRet getEntitiesRet = null;
        try {
            getEntitiesRet = insightsInterface.getEntities(getEntitiesArg);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }
        // check whether the entity with kind and kind_id exists
        String ext_id1 = "";
        assert getEntitiesRet != null;
        for (InsightsInterfaceProto.Entity entity : getEntitiesRet.getEntityList()) {
            if (entity.getEntityGuid().getEntityId().equals(entity_id)) {
                for (InsightsInterfaceProto.NameTimeValuePair entityAttribute : entity.getAttributeDataMapList()) {
                    if (entityAttribute.getName().equals(Constants.CATEGORY_UUIDS)) {
                        for (String value : entityAttribute.getValue().getStrList().getValueListList()) {
                            if (value.equals(category_id)) {
                                ext_id1 = entity.getEntityGuid().getEntityId();
                                log.info("Association already exists with ext_id: " + ext_id1);
                                return;
                            }
                        }
                    }
                }
            }
        }

        // create update entity arg
        String AssociationId = entity_id;
        InsightsInterfaceProto.EntityGuid entityGuid1 = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.VM_ANTI_AFFINITY_POLICY)
                .setEntityId(AssociationId)
                .build();

        List<InsightsInterfaceProto.AttributeDataArg> attributeDataArgList_ = Arrays.asList(
                InsightsInterfaceProto.AttributeDataArg.newBuilder()
                        .setAttributeData(InsightsInterfaceProto.AttributeData.newBuilder()
                                .setName(Constants.CATEGORY_UUIDS)
                                .setValue(InsightsInterfaceProto.DataValue.newBuilder()
                                        .setStrList(InsightsInterfaceProto.DataValue.StrList.newBuilder()
                                                .addAllValueList(Arrays.asList(category_id))
                                                .build())
                                        .build())
                                .build())
                        .build());

        InsightsInterfaceProto.UpdateEntityArg updateEntityArg = InsightsInterfaceProto.UpdateEntityArg.newBuilder()
                .setEntityGuid(entityGuid1)
                .addAllAttributeDataArgList(attributeDataArgList_)
                .build();

        try {
            InsightsInterfaceProto.UpdateEntityRet updateEntityRet = insightsInterface.updateEntity(updateEntityArg);
            log.info("UpdateEntityRet: " + updateEntityRet);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }
    }

    public void create_filter(InsightsInterface insightsInterface, String ext_id, String entity_kind,
            String entity_uuid) {
        InsightsInterfaceProto.GetEntitiesArg.Builder getEntitiesArgBuilder = InsightsInterfaceProto.GetEntitiesArg
                .newBuilder();
        InsightsInterfaceProto.EntityGuid entityGuid = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.FILTER)
                .build();

        getEntitiesArgBuilder.addEntityGuidList(entityGuid);
        InsightsInterfaceProto.GetEntitiesArg getEntitiesArg = getEntitiesArgBuilder.build();
        InsightsInterfaceProto.GetEntitiesRet getEntitiesRet = null;
        try {
            getEntitiesRet = insightsInterface.getEntities(getEntitiesArg);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }
        // check whether the entity with kind and kind_id exists
        String ext_id1 = "";
        assert getEntitiesRet != null;
        for (InsightsInterfaceProto.Entity entity : getEntitiesRet.getEntityList()) {
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
                    if (filter.getEntityUuid().equals(entity_uuid) && filter.getEntityKind().equals(entity_kind)) {
                        // assert filter.getFilterExpressionsCount() > 0;
                        for (FilterOuterClass.FilterExpression filterExpression : filter.getFilterExpressionsList()) {
                            if (filterExpression.getLhsEntityType().equals(Constants.CATEGORY) && filterExpression
                                    .getOperator().equals(FilterOuterClass.FilterExpression.Operator.kIn)) {
                                if (filterExpression.getEntityUuids().contains(ext_id)) {
                                    ext_id1 = entity.getEntityGuid().getEntityId();
                                    log.info("Association already exists with ext_id: " + ext_id1);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        UUID AssociationId = UUID.randomUUID();
        FilterOuterClass.FilterExpression filterExpression = FilterOuterClass.FilterExpression.newBuilder()
                .setLhsEntityType(Constants.CATEGORY)
                .setOperator(FilterOuterClass.FilterExpression.Operator.kIn)
                .setEntityUuids(ext_id)
                .build();
        // create a filterExpression List
        List<FilterOuterClass.FilterExpression> filterExpressionList = Arrays.asList(filterExpression);
        FilterOuterClass.Filter filter = FilterOuterClass.Filter.newBuilder()
                .setEntityUuid(entity_uuid)
                .setEntityKind(entity_kind)
                .setUuid(AssociationId.toString())
                .addFilterExpressions(filterExpression)
                .build();
        // create update entity arg for filter
        // serialize filter to byte array

        InsightsInterfaceProto.AttributeData.Builder attributeDataBuilder = InsightsInterfaceProto.AttributeData
                .newBuilder()
                .setName("__zprotobuf__")
                .setValue(InsightsInterfaceProto.DataValue.newBuilder()
                        .setBytesValue(filter.toByteString())
                        .build());

        InsightsInterfaceProto.EntityGuid entityGuid1 = InsightsInterfaceProto.EntityGuid.newBuilder()
                .setEntityTypeName(Constants.FILTER)
                .setEntityId(AssociationId.toString())
                .build();

        List<InsightsInterfaceProto.AttributeDataArg> attributeDataArgList_ = Arrays.asList(
                InsightsInterfaceProto.AttributeDataArg.newBuilder()
                        .setAttributeData(attributeDataBuilder)
                        .build()
        // InsightsInterfaceProto.AttributeDataArg.newBuilder()
        // .setAttributeData(InsightsInterfaceProto.AttributeData.newBuilder()
        // .setName("filter_expressions.lhs_entity_id")
        // .setValue(InsightsInterfaceProto.DataValue.newBuilder()
        // .setStrList(InsightsInterfaceProto.DataValue.StrList.newBuilder()
        // .addAllValueList(Arrays.asList(ext_id))
        // .build())
        // .build())
        // .build())
        // .build(),
        // InsightsInterfaceProto.AttributeDataArg.newBuilder()
        // .setAttributeData(InsightsInterfaceProto.AttributeData.newBuilder()
        // .setName("kind")
        // .setValue(InsightsInterfaceProto.DataValue.newBuilder()
        // .setStrValue(entity_kind)
        // .build())
        // .build())
        // .build(),
        // InsightsInterfaceProto.AttributeDataArg.newBuilder()
        // .setAttributeData(InsightsInterfaceProto.AttributeData.newBuilder()
        // .setName("entity_uuid")
        // .setValue(InsightsInterfaceProto.DataValue.newBuilder()
        // .setStrValue(entity_uuid)
        // .build())
        // .build())
        // .build()
        );

        InsightsInterfaceProto.UpdateEntityArg updateEntityArg = InsightsInterfaceProto.UpdateEntityArg.newBuilder()
                .setEntityGuid(entityGuid1)
                .addAllAttributeDataArgList(attributeDataArgList_)
                .build();
        log.info("UpdateEntityArg: " + updateEntityArg);
        try {
            InsightsInterfaceProto.UpdateEntityRet updateEntityRet = insightsInterface.updateEntity(updateEntityArg);
            log.info("UpdateEntityRet: " + updateEntityRet);
        } catch (InsightsInterfaceException e) {
            log.error("Error: " + e);
        }

    }

    public void create_single_association() {
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

        // validate kind_id if it is not empty
        if (this.kind_id.isEmpty()) {
            log.error("Kind_id is empty");
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
            create_volume_group_entity_capability(insightsInterface, ext_id, this.kind_id);
        } else if (this.kind.equals(Constants.VM_HOST_AFFINITY_POLICY)) {
            create_vm_host_affinity_policy(insightsInterface, ext_id, this.kind_id);
        } else if (this.kind.equals(Constants.VM_ANTI_AFFINITY_POLICY)) {
            create_vm_anti_affinity_policy(insightsInterface, ext_id, this.kind_id);
        } else if (Constants.ALLOWED_POLICY_KINDS.contains(this.kind)) {
            create_filter(insightsInterface, ext_id, this.kind, this.kind_id);
        } else if (Constants.ALLOWED_ENTITY_KINDS.contains(this.kind)) {
            create_abac_entity_capability(insightsInterface, ext_id, this.kind, this.kind_id);
        } else {
            log.error("Kind is not in ALLOWED_POLICY_KINDS or ALLOWED_ENTITY_KINDS");
        }

    }

    @Override
    public void run(String... args) throws Exception {

        // this.args.getOptionNames().forEach(optionName -> {
        //     // System.out.println(optionName + " = " +
        //     // this.args.getOptionValues(optionName));
        //     switch (optionName) {
        //         case "host":
        //             this.host = this.args.getOptionValues(optionName).get(0);
        //             break;
        //         case "port":
        //             this.port = this.args.getOptionValues(optionName).get(0);
        //             break;
        //         case "category":
        //             this.category = this.args.getOptionValues(optionName).get(0);
        //             break;
        //         case "kind":
        //             this.kind = this.args.getOptionValues(optionName).get(0);
        //             break;
        //         case "kind_id":
        //             this.kind_id = this.args.getOptionValues(optionName).get(0);
        //             break;
        //     }
        // });

        // System.out.println("host: " + this.host);
        // System.out.println("port: " + this.port);
        // System.out.println("category: " + this.category);
        // System.out.println("kind: " + this.kind);
        // System.out.println("kind_id: " + this.kind_id);

        // create_single_association();
        // exit(0);
    }

}
