package com.nbt.codegen.model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class EntityDefinition {

	private String namesSpace;
	private String entityName;
	private String description;
	
	private List<AttributeDefinition>attributes = new ArrayList();
	
	public void addAttribute(AttributeDefinition newAttribute){
		attributes.add(newAttribute);
	}
	
	public String toString(){
		return "nameSpace=>"+ this.namesSpace +", name=>"+ this.entityName;
	}
}
