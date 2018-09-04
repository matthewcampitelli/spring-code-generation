package com.nbt.codegen.template;

public interface TemplateEngine {
	
	public void init();
	public void loadTemplates();
	public String renderTemplate(String templateName, Object context);
	
}
