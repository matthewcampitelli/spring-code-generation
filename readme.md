#Spring Code Generation Utils

#Overview
The overall goal is to streamline boilerplate related tasks related to common development tasks for Spring based projects.

A core concept of the generation process is to support the ability to define entities using an DSL. Whenever possible, defaults and stereotypes are 
used to minimize manual input. For example, attributes can be declared as attributes such as 'name', 'address', or as 'phone number', which allows the 
generation framework to make certain assumptions and perform a lot of the manual steps required to create a more complete atttribute/entity representation 
including validation, mock data generation, etc.

#Entity Definition Format (EDF)

A DSL used for defining entities. Format is JSON based, which allows the contents to be hand edited, however in most cases artifacts will most likely be initially generated and then hand edited as required. 
   
#History
