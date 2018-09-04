package com.nbt.codegen.template;

public class TemplateEngineFactory {
	
	private HandlebarsTemplateEngine engine;
	
	public TemplateEngine getTemplateEngine(){
		if(engine==null){
			engine = new HandlebarsTemplateEngine();
			engine.init();
			engine.loadTemplates();
		}
		return engine;
	}
	
}
