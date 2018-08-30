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
	private String description;
	private String defaultValue;
	private String columnDefinition;
	private String validationRegex;
	
	/**
	 * Given a user defined type, set the correct type if type was defined in shorthand, and then assign default attributes as 
	 * appropriate.  
	 */
	public void setType(){
		
	}
	
	public String toString(){
		return "name=>"+ this.attributeName +", type=>"+ this.type;
	}
	
}
