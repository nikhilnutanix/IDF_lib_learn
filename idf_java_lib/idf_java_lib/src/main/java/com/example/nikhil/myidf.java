package com.example.nikhil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class myidf {
//    public InsightsInterface insightsInterface = new InsightsInterface("10.15.4.36", 2027);
//
//    public void getEntityById(String entityType, String extId) {
//
//        // String query = "entity_guid_list {\n" +
//        // " entity_type_name: \"abac_entity_capability\"\n" +
//        // " entity_id: \"1010\"\n" +
//        // "}";
//        InsightsInterfaceProto.EntityGuid entityGuid = InsightsInterfaceProto.EntityGuid.newBuilder()
//                .setEntityTypeName(entityType)
//                .setEntityId(extId)
//                .build();
//        InsightsInterfaceProto.GetEntitiesArg arg = InsightsInterfaceProto.GetEntitiesArg.newBuilder()
//                .addEntityGuidList(entityGuid)
//                .build();
//        System.out.println("GetEntitiesArg: " + arg);
//
//        try {
//            InsightsInterfaceProto.GetEntitiesRet ret = insightsInterface.getEntities(arg);
//            System.out.println("Entities: " + ret);
//        } catch (InsightsInterfaceException e) {
//            System.out.println("Error: " + e);
//        }
//    }
//
//    public void getEntitiesByType(String entityType) {
//
//        InsightsInterfaceProto.EntityGuid entityGuid = InsightsInterfaceProto.EntityGuid.newBuilder()
//                .setEntityTypeName(entityType)
//                .build();
//        InsightsInterfaceProto.GetEntitiesArg arg = InsightsInterfaceProto.GetEntitiesArg.newBuilder()
//                .addEntityGuidList(entityGuid).build();
//        System.out.println("GetEntitiesArg: " + arg);
//
//        try {
//            InsightsInterfaceProto.GetEntitiesRet ret = insightsInterface.getEntities(arg);
//            System.out.println("Entities: " + ret);
//        } catch (InsightsInterfaceException e) {
//            System.out.println("Error: " + e);
//        }
//    }
//
//    public void getEntitiesMultipleType(String[] entityTypes) {
//
//        InsightsInterfaceProto.GetEntitiesArg.Builder argBuilder = InsightsInterfaceProto.GetEntitiesArg.newBuilder();
//        for (String entityType : entityTypes) {
//            InsightsInterfaceProto.EntityGuid entityGuid = InsightsInterfaceProto.EntityGuid.newBuilder()
//                    .setEntityTypeName(entityType)
//                    .build();
//            argBuilder.addEntityGuidList(entityGuid);
//        }
//        InsightsInterfaceProto.GetEntitiesArg arg = argBuilder.build();
//        System.out.println("GetEntitiesArg: " + arg);
//
//        try {
//            InsightsInterfaceProto.GetEntitiesRet ret = insightsInterface.getEntities(arg);
//            System.out.println("Entities: " + ret);
//        } catch (InsightsInterfaceException e) {
//            System.out.println("Error: " + e);
//        }
//    }
//
//    public void updateEntity(String entityType, String extId, String name){
//        // InsightsInterfaceProto.EntityGuid entityGuid = InsightsInterfaceProto.EntityGuid.newBuilder()
//        //         .setEntityTypeName(entityType)
//        //         .setEntityId(extId)
//        //         .build();
//        InsightsInterfaceProto.AttributeData.Builder attr = InsightsInterfaceProto.AttributeData.newBuilder();
//        attr.setName("name");
//        InsightsInterfaceProto.DataValue.Builder dataBuilder = InsightsInterfaceProto.DataValue.newBuilder();
//        dataBuilder.setStrValue(name);
//        attr.setValue(dataBuilder.build());
//        InsightsInterfaceProto.AttributeDataArg.Builder attrArg = InsightsInterfaceProto.AttributeDataArg.newBuilder();
//        attrArg.setAttributeData(attr.build());
//        InsightsInterfaceProto.UpdateEntityArg.Builder arg = InsightsInterfaceProto.UpdateEntityArg.newBuilder();
//        arg.addAttributeDataArgList(attrArg.build());
//        System.out.println("UpdateEntityArg: " + arg);
//
//        // try {
//        //     // InsightsInterfaceProto.UpdateEntityRet ret = insightsInterface.updateEntity(arg);
//        //     // System.out.println("UpdateEntityRet: " + ret);
//        // } catch (InsightsInterfaceException e) {
//        //     System.out.println("Error: " + e);
//        // }
//    }

}
