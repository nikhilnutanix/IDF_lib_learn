/*
 * Copyright (c) 2018 Nutanix Inc. All rights reserved.
 *
 * Author:  InsightsDataFabric-dev@nutanix.com
 *
 * Sample code for: GetEntities - By Entity Type
 */
package main

import (
	"flag"
	"fmt"
	"idf_go_lib/constants"
	"os"
	"regexp"
	"strconv"

	"github.com/golang/protobuf/proto"
	"github.com/google/uuid"
	insights_interface "github.com/nutanix-core/idf-interfaces/go/insights/insights_interface"
)

var (
	service *insights_interface.InsightsService
)

func create_single_association() {
	// Define the CLI arguments
	host := flag.String("host", "localhost", "Host IP")
	port := flag.String("port", "2027", "Port")
	category := flag.String("category", "", "category_key/category_value")
	kind := flag.String("kind", "", "the type of the entity or policy")
	kind_id := flag.String("kind_id", "", "kind_id")
	flag.Parse()
	fmt.Println("host:", *host)
	fmt.Println("port:", *port)
	fmt.Println("category:", *category)
	fmt.Println("kind:", *kind)
	fmt.Println("kind_id:", *kind_id)
	// Check if category is empty
	if *category == "" {
		fmt.Println("Please provide a category.")
		os.Exit(1)
	}
	// Define a regular expression for key/value format
	re := regexp.MustCompile(`^[^/]+/[^/]+$`)

	// Validate the category input
	if !re.MatchString(*category) {
		fmt.Println("The input string is NOT in key/value format.")
		os.Exit(1)
	}

	//validate kind if it not from ALLOWED_POLICY_KINDS or ALLOWED_ENTITY_KINDS
	if !constants.ALLOWED_POLICY_KINDS.Contains(*kind) && !constants.ALLOWED_ENTITY_KINDS.Contains(*kind) {
		fmt.Println("The kind is not from ALLOWED_POLICY_KINDS or ALLOWED_ENTITY_KINDS.")
		os.Exit(1)
	}

	if *kind_id == "" {
		fmt.Println("Please provide a kind_id.")
		os.Exit(1)
	}

	// take a int value from port string
	port_num, _ := strconv.Atoi(*port)

	// Creating New Service
	service = insights_interface.NewInsightsService(*host, uint16(port_num))

	arg := &insights_interface.GetEntitiesArg{}
	response := &insights_interface.GetEntitiesRet{}

	query := `
	entity_guid_list {
		entity_type_name: "category"
	}
	`
	proto.UnmarshalText(query, arg)

	err := service.SendMsgWithTimeout("GetEntities", /* service */
		arg, response, nil, /* backoff */
		60 /* timeoutSecs */)
	if err != nil {
		fmt.Println("Failed because of error -", err)
	}
	// search in the response for the entity with fq name "category"
	// if found, print the entity id
	bool_flag := false
	ext_id := ""
	for _, entity := range response.GetEntity() {
		for _, attrData := range entity.GetAttributeDataMap() {
			if attrData.GetName() == "fq_name" && attrData.GetValue().GetStrValue() == *category {
				bool_flag = true
				ext_id = entity.GetEntityGuid().GetEntityId()
				break
			}
			if bool_flag {
				break
			}
		}
		if bool_flag {
			break
		}
	}
	if !bool_flag {
		fmt.Println("Category not found.")
		os.Exit(1)
	}

	fmt.Println("Entity ID for category", *category, "is", ext_id)


	// create update entity arg
	EntityTypeName := constants.ABAC_ENTITY_CAPABILITY
	KindNameStr := constants.KIND
	KindIdStr := constants.KIND_ID
	CategoryIdListStr := constants.CATEGORY_ID_LIST

	arg = &insights_interface.GetEntitiesArg{}
	response = &insights_interface.GetEntitiesRet{}

	query = `
	entity_guid_list {
		entity_type_name: "abac_entity_capability"
	}
	`
	proto.UnmarshalText(query, arg)
	err = service.SendMsgWithTimeout("GetEntities", /* service */
		arg, response, nil, /* backoff */
		60 /* timeoutSecs */)
	if err != nil {
		fmt.Println("Failed because of error -", err)
	}
	// check whether the entity with kind and kind_id exists
	bool_flag = false
	ass_id := ""
	for _, entity := range response.GetEntity() {
		for _, attrData := range entity.GetAttributeDataMap() {
			if attrData.GetName() == constants.CATEGORY_ID_LIST {
				for _, val := range attrData.GetValue().GetStrList().GetValueList() {
					if val == ext_id {
						for _, attrData1 := range entity.GetAttributeDataMap() {
							if attrData1.GetName() == constants.KIND && attrData1.GetValue().GetStrValue() == *kind {
								for _, attrData2 := range entity.GetAttributeDataMap() {
									if attrData2.GetName() == constants.KIND_ID && attrData2.GetValue().GetStrValue() == *kind_id {
										bool_flag = true
										ass_id = entity.GetEntityGuid().GetEntityId()
										fmt.Println("Association exists for kind:", *kind, "and kind_id:", *kind_id, "with entity id:", ass_id)
										break
									}
								}
							}
							if bool_flag {
								break
							}
						}
					}
					if bool_flag {
						break
					}
				}
				if bool_flag {
					break
				}
			}
		}
		if bool_flag {
			return
		}
	}

	// generate a random uuid
	AssociationId := uuid.New().String()

	update_entity_arg := &insights_interface.UpdateEntityArg{
		EntityGuid: &insights_interface.EntityGuid{
			EntityId:       &AssociationId,
			EntityTypeName: &EntityTypeName,
		},
		AttributeDataArgList: []*insights_interface.AttributeDataArg{
			{
				AttributeData: &insights_interface.AttributeData{
					Name: &CategoryIdListStr,
					Value: &insights_interface.DataValue{
						ValueType: &insights_interface.DataValue_StrList_{
							StrList: &insights_interface.DataValue_StrList{
								ValueList: []string{ext_id},
							},
						},
					},
				},
			},
			{
				AttributeData: &insights_interface.AttributeData{
					Name: &KindNameStr,
					Value: &insights_interface.DataValue{
						ValueType: &insights_interface.DataValue_StrValue{
							StrValue: *kind,
						},
					},
				},
			},
			{
				AttributeData: &insights_interface.AttributeData{
					Name: &KindIdStr,
					Value: &insights_interface.DataValue{
						ValueType: &insights_interface.DataValue_StrValue{
							StrValue: *kind_id,
						},
					},
				},
			},
		},
	}
	// fmt.Println("UpdateEntity request:", proto.MarshalTextString(update_entity_arg))

	// send update entity request
	update_entity_response := &insights_interface.UpdateEntityRet{}
	err = service.SendMsgWithTimeout("UpdateEntity", /* service */
		update_entity_arg, update_entity_response, nil, /* backoff */
		60 /* timeoutSecs */)
	if err != nil {
		fmt.Println("Failed because of error -", err)
	}
	fmt.Println("CreateEntity response:", proto.MarshalTextString(update_entity_response))

}

func remove_category_associations() {
	// Define the CLI arguments
	host := flag.String("host", "localhost", "Host IP")
	port := flag.String("port", "2027", "Port")
	category := flag.String("category", "", "category_key/category_value")
	kind := flag.String("kind", "", "the type of the entity or policy")
	flag.Parse()
	fmt.Println("host:", *host)
	fmt.Println("port:", *port)
	fmt.Println("category:", *category)
	fmt.Println("kind:", *kind)
	// Check if category is empty
	if *category == "" {
		fmt.Println("Please provide a category.")
		os.Exit(1)
	}
	// Define a regular expression for key/value format
	re := regexp.MustCompile(`^[^/]+/[^/]+$`)

	// Validate the input
	if !re.MatchString(*category) {
		fmt.Println("The input string is NOT in key/value format.")
		os.Exit(1)
	}

	//validate kind if it not from ALLOWED_POLICY_KINDS or ALLOWED_ENTITY_KINDS
	if !constants.ALLOWED_POLICY_KINDS.Contains(*kind) && !constants.ALLOWED_ENTITY_KINDS.Contains(*kind) {
		fmt.Println("The kind is not from ALLOWED_POLICY_KINDS or ALLOWED_ENTITY_KINDS.")
		os.Exit(1)
	}

	// take a int value from port string
	port_num, _ := strconv.Atoi(*port)

	// Creating New Service
	service = insights_interface.NewInsightsService(*host, uint16(port_num))

	arg := &insights_interface.GetEntitiesArg{}
	response := &insights_interface.GetEntitiesRet{}

	query := `
	entity_guid_list {
		entity_type_name: "category"
	}
	`
	proto.UnmarshalText(query, arg)

	err := service.SendMsgWithTimeout("GetEntities", /* service */
		arg, response, nil, /* backoff */
		60 /* timeoutSecs */)
	if err != nil {
		fmt.Println("Failed because of error -", err)
	}
	// fmt.Println(proto.MarshalTextString(response))
	// os.Exit(1)
	// search in the response for the entity with fq name "category"
	// if found, print the entity id
	bool_flag := false
	ext_id := ""
	for _, entity := range response.GetEntity() {
		for _, attrData := range entity.GetAttributeDataMap() {
			if attrData.GetName() == "fq_name" && attrData.GetValue().GetStrValue() == *category {
				bool_flag = true
				ext_id = entity.GetEntityGuid().GetEntityId()
				break
			}
			if bool_flag {
				break
			}
		}
		if bool_flag {
			break
		}
	}

	if !bool_flag {
		fmt.Println("Category not found.")
		os.Exit(1)
	}

	fmt.Println("Entity ID for category", *category, "is", ext_id)
	EntityTypeName := constants.ABAC_ENTITY_CAPABILITY

	// now get all the associations for the category
	// create get entities arg
	get_entities_arg := &insights_interface.GetEntitiesArg{
		EntityGuidList: []*insights_interface.EntityGuid{
			{
				EntityTypeName: &EntityTypeName,
			},
		},
	}
	response = &insights_interface.GetEntitiesRet{}
	err = service.SendMsgWithTimeout("GetEntities", /* service */
		get_entities_arg, response, nil, /* backoff */
		60 /* timeoutSecs */)
	if err != nil {
		fmt.Println("Failed because of error -", err)
	}
	AssociationIdList := []string{}
	for _, entity := range response.GetEntity() {
		// create a map of string string
		for _, attrData := range entity.GetAttributeDataMap() {
			// fmt.Println("attrData:", proto.MarshalTextString(attrData))
			if attrData.GetName() == "category_id_list" {
				for _, value := range attrData.GetValue().GetStrList().GetValueList() {
					if value == ext_id {
						for _, attrData1 := range entity.GetAttributeDataMap() {
							if attrData1.GetName() == "kind" && attrData1.GetValue().GetStrValue() == *kind {
								// fmt.Println("attrData:", proto.MarshalTextString(attrData))
								AssociationIdList = append(AssociationIdList, entity.GetEntityGuid().GetEntityId())
							}
						}
					}
				}
			}
		}
	}
	fmt.Println("AssociationIdList:", AssociationIdList)
	// os.Exit(0)
	for _, AssociationId := range AssociationIdList {
		// create delete entity arg
		delete_entity_arg := &insights_interface.DeleteEntityArg{
			EntityGuid: &insights_interface.EntityGuid{
				EntityId:       &AssociationId,
				EntityTypeName: &EntityTypeName,
			},
		}
		// fmt.Println("DeleteEntity request:", proto.MarshalTextString(delete_entity_arg))

		// send delete entity request
		delete_entity_response := &insights_interface.DeleteEntityRet{}
		err = service.SendMsgWithTimeout("DeleteEntity", /* service */
			delete_entity_arg, delete_entity_response, nil, /* backoff */
			60 /* timeoutSecs */)
		if err != nil {
			fmt.Println("Failed because of error -", err)
		}
		fmt.Println("DeleteEntity response:", proto.MarshalTextString(delete_entity_response))
	}

}

func main() {

	// create_single_association()
	remove_category_associations()

}
