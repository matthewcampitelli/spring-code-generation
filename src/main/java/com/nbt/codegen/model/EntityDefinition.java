package com.nbt.codegen.model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>Representation of an 'entity'. Intent is to model the object in such a fashion that it is 
 * language/framework agnostic.</p>
 * 
 * <p>
 * Entities are typically associated with a given stereotype, which is used to help with generation activities. 
 * </p>
 * 
 * 
 * 
 * @author jefe
 *
 */
public class EntityDefinition {

	/** Indicates that no stereotype has been assigned to the entity. */
	public static final int STEREOTYPE_UNDEFINED = -1;
	/** First class citizen, typically graph consists of other dependent objects. Usually when this entity is deleted, 
	 * child relations are deleted. */
	public static final int STEREOTYPE_TOP_LEVEL = 1;
	/** Member of an graph. */
	public static final int STEREOTYPE_DEPENDENT = 2;
	/** 'Reference' type data, for example a 'Product Category' or a 'Status'. Usually these types of objects require CRUD screens for simple maintenance,
	 * and the data is less likely to change. */
	public static final int STEREOTYPE_DICTIONARY = 3;
	
	/** Implies that entity should be deleted */
	public static final int DELETION_STRATEGY_PURGE = 1;
	/** Implies that entity is never deleted, instead an active flag is set.*/
	public static final int DELETION_STRATEGY_DEACTIVATE = 2;
	
	/** Provisions entity into a group, this would directly transfer to the 'package' name for a java entity. */
	private String nameSpace;
	private String entityName;
	/** Optional description for entity, will be included in header comments. */
	private String description;
	/** Optional notes, will not be included in the generated artifacts.*/
	private String notes;
	
	private int stereotype = EntityDefinition.STEREOTYPE_UNDEFINED;
	
	private int deletionStrategy = DELETION_STRATEGY_PURGE;
	/** Indicates that attributes should be added to the entity which allow last creation and update dates to be tracked. */
	private boolean trackUpdates = true;
	
	private List<AttributeDefinition>attributes = new ArrayList();
	private List<DictionaryReference>dictionaryReferences = new ArrayList();
	private List<ChildCollection>childCollections = new ArrayList();
	
	public EntityDefinition(String name){
		this.entityName = name;
	}
	
	public EntityDefinition(String name, String packageName){
		this.entityName = name;
		this.nameSpace = packageName;
	}
	
	public void addDictionaryReference(DictionaryReference ref){
		this.dictionaryReferences.add(ref);
	}
	
	public void addChildCollection(ChildCollection child){
		this.childCollections.add(child);
	}
	
	public void addAttribute(AttributeDefinition newAttribute){
		attributes.add(newAttribute);
	}
	
	public String toString(){
		return "nameSpace=>"+ this.nameSpace +", name=>"+ this.entityName;
	}
	
	/**
	 * Produce a unique identifier for the current entity.  
	 * 
	 * @return
	 */
	public String getUniqueIdentifier(){
		if(this.nameSpace==null){
			return this.entityName;
		}
		return this.nameSpace +"."+ this.entityName;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setnameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getStereotype() {
		return stereotype;
	}

	public void setStereotype(int stereotype) {
		this.stereotype = stereotype;
	}

	public int getDeletionStrategy() {
		return deletionStrategy;
	}

	public void setDeletionStrategy(int deletionStrategy) {
		this.deletionStrategy = deletionStrategy;
	}

	public boolean isTrackUpdates() {
		return trackUpdates;
	}

	public void setTrackUpdates(boolean trackUpdates) {
		this.trackUpdates = trackUpdates;
	}

	public List<AttributeDefinition> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeDefinition> attributes) {
		this.attributes = attributes;
	}

	public List<DictionaryReference> getDictionaryReferences() {
		return dictionaryReferences;
	}

	public void setDictionaryReferences(List<DictionaryReference> dictionaryReferences) {
		this.dictionaryReferences = dictionaryReferences;
	}

	public List<ChildCollection> getChildCollections() {
		return childCollections;
	}

	public void setChildCollections(List<ChildCollection> childCollections) {
		this.childCollections = childCollections;
	}
	
}
