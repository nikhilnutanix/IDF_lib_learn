/*
 * Copyright (c) 2018 Nutanix Inc. All rights reserved.
 *
 * Author:  InsightsDataFabric-dev@nutanix.com
 *
 * Sample code for: GetEntities - By Entity Type
 */
 package idf

//  import(
//    "flag"
//    insights_interface "github.com/nutanix-core/idf-interfaces/go/insights/insights_interface"
//    "github.com/golang/protobuf/proto"
//  )
//  import "fmt"
//  var(
//    service *insights_interface.InsightsService
//  )
 
//  func main() {
 
//    flag.Parse()
 
//    // Creating New Service
//    service = insights_interface.NewInsightsService("10.15.4.36", 2027)
   
//    arg :=  &insights_interface.GetEntitiesArg{};
//    response := &insights_interface.GetEntitiesRet{}
   
//    query := `
// entity_guid_list {
//   entity_type_name: "abac_entity_capability"
//   entity_id : "1010"
// }
//    `
//    proto.UnmarshalText(query, arg)
   
//    err := service.SendMsgWithTimeout("GetEntities" /* service */,
// 									 arg, response, nil /* backoff */,
// 									 60 /* timeoutSecs */)
//    if err != nil {
//     fmt.Println("Failed because of error -", err)
//    }
//    fmt.Println(proto.MarshalTextString(response))
//  }
 
 
 