package com.smallchili.xmz.enums;
/**
 * 
 * @author xmz
 * 2020年9月12日
 * 模板类型枚举类，DO/DAO/Service/Controller层的模板名字
 */
public enum TemplateTypeEnum {
	DO("entity"), DAO("dao"), SERVICE("service"), CONTROLLER("controller"),
	VUE("vue");
	
	private String templateType;
	
	private TemplateTypeEnum(String templateType){
		this.templateType = templateType;
	}
	public String getType(){
		return this.templateType;
	}
}
