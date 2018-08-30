package com.nbt.codegen;

import com.nbt.codegen.model.EntityDefinition;
import com.nbt.codegen.model.AttributeDefinition;
import com.google.gson.Gson;

/**
 * Performs 
 * 
 * @author jefe
 *
 */
public class EntityParser {

	private Gson gson = new Gson();
	
	public EntityDefinition parserEntityFromJson(String json){
		EntityDefinition entity = gson.fromJson(json, EntityDefinition.class);
		
		validateEntity(entity);
		return entity;
	}

	void validateEntity(EntityDefinition entity){
		
	}
	
	public String toJson(EntityDefinition entity){
		return gson.toJson(entity);
	}
	
	
	
}
