#Spring Code Generation Utils

#Overview
The overall goal is to streamline boilerplate related tasks related to common development tasks for Spring based projects.

A core concept of the generation process is to support the ability to define entities using an DSL. Whenever possible, defaults and stereotypes are 
used to minimize manual input. For example, attributes can be declared as attributes such as 'name', 'address', or as 'phone number', which allows the 
generation framework to make certain assumptions and perform a lot of the manual steps required to create a more complete atttribute/entity representation 
including validation, mock data generation, etc.

#Goals
Minimize boilerplate code operations
Increase focus on defining objects.
Promote syntactical consistency across code base.
Ensure solid testability of project, there is always the pressure to push back on testing automation vs. feature delivery. Let's tip the scale a bit and focus a bit 

#Core concepts

##Entity definitions
A DSL used for defining entities. Format is JSON based, which allows the contents to be hand edited, however in most cases artifacts will most likely be initially generated and then hand edited as required. 

##Storage engines
Entity definition and object graph structures are defined, and then imported into a 'storage engine'.

##Code generation
Artifacts are generated based on entity definitions. 
   
#History

#TODO:
*Expand JPA entity template related
	Add ability to render child collections
	Add ability to render dictionary refs
	Implement full set of attribute attributes.
*Implement template logic for JPA repositories
*Add ability to perform 'high level operations. For example, given a reference to a top level element of an object graph, render the entire stack (REST controllers, service, entity, and repo).
*Add support for the ability to manage relations between entities. For example, when defining a one to many relation, the referenced child entity most likely will need to be updated with the inverse of the relation. 

