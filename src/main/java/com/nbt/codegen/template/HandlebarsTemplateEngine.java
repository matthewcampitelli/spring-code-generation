package com.nbt.codegen.template;

import java.util.HashMap;
import java.util.Map;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

public class HandlebarsTemplateEngine implements TemplateEngine{

	Handlebars handlebars;
	boolean isInitialized = false;
	Map<String, Template>templates = new HashMap();
	
	@Override
	public void init() {
		if(isInitialized){
			throw new RuntimeException("init() method called after initialization already performed.");
		}
		
		TemplateLoader loader = new ClassPathTemplateLoader();
		loader.setPrefix("/templates");
		loader.setSuffix(".hbs");
		handlebars = new Handlebars(loader);
		
	}

	@Override
	public void loadTemplates(){
		try{
			templates.put("test", handlebars.compile("test"));
			templates.put("jpa-entity", handlebars.compile("jpa-entity"));
			templates.put("jpa-repo", handlebars.compile("jpa-repo"));
			templates.put("rest-controller", handlebars.compile("rest-controller")); 
			templates.put("crud-service-interface", handlebars.compile("crud-service-interface")); 
			templates.put("crud-service-impl", handlebars.compile("crud-service-impl")); 
			templates.put("jpa-repo-test", handlebars.compile("jpa-repo-test"));
			templates.put("rest-controller-test", handlebars.compile("rest-controller-test"));
			templates.put("crud-service-impl-test", handlebars.compile("crud-service-impl-test"));
			
		}
		catch(Exception e){
			throw new RuntimeException("Unable to compile referenced template");
		}
	}

	@Override
	public String renderTemplate(String templateName, Object context){
		try{
			return templates.get(templateName).apply(context);
		}
		catch(Exception e){
			throw new RuntimeException("Unable to render referenced template '"+ templateName +"'");
		}
	}

}
