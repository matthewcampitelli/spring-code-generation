package com.nbt.codegen.model;

public class AttributeDefinition {

	public static String ATTRIBUTE_TYPE_INTEGER = "";
	public static String ATTRIBUTE_TYPE_STRING = "";
	public static String ATTRIBUTE_TYPE_BOOLEAN = "";
	public static String ATTRIBUTE_TYPE_LONG = "";
	public static String ATTRIBUTE_TYPE_DOUBLE = "";
	
	/** Specialized attributes which are actually references to objects, although modeled as 'attributes'. */
	public static String ATTRIBUTE_TYPE_PHONE_NUMBER = "";
	public static String ATTRIBUTE_TYPE_ADDRESS = "";
	public static String ATTRIBUTE_TYPE_EMAIL_ADDRESS = "";
	public static String ATTRIBUTE_TYPE_PERSON = "";
	public static String ATTRIBUTE_TYPE_SSN = "";
	
	private String type = ATTRIBUTE_TYPE_STRING;
	private String attributeName;
	/** Optional description that will appear in the comments. */
	private String description;
	private String defaultValue;
	private String columnDefinition;
	private String validationRegex;
	//private String javaAttributeType="xyz";
	
	public AttributeDefinition(String name){
		this.attributeName = name;
	}
	
	public AttributeDefinition(String name, String typeRef){
		this.attributeName = name;
		this.setType(typeRef);
	}
	
	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getColumnDefinition() {
		return columnDefinition;
	}

	public void setColumnDefinition(String columnDefinition) {
		this.columnDefinition = columnDefinition;
	}

	public String getValidationRegex() {
		return validationRegex;
	}

	public void setValidationRegex(String validationRegex) {
		this.validationRegex = validationRegex;
	}

	public String getType() {
		return type;
	}

	/**
	 * Given a user defined type, set the correct type if type was defined in shorthand, and then assign default attributes as 
	 * appropriate.  
	 */
	public void setType(String type){
		
	}
	
	public String toString(){
		return "name=>"+ this.attributeName +", type=>"+ this.type;
	}
	
	public String getAttributeType(){
		if(this.type.equals(ATTRIBUTE_TYPE_STRING)){
			return "String";
		}
		
		return "undefined";
	}

	
	public String getJavaAttributeType() {
		if(this.type.equals(ATTRIBUTE_TYPE_STRING)){
			return "String";
		}
		
		return "undefined";
	}
	/*
	public void setJavaAttributeType(String javaAttributeType) {
		this.javaAttributeType = javaAttributeType;
	}
	*/
	
}
