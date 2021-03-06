package com.smallchili.xmz.enums;

/**
 * @author xmz
 * @date 2020/09/25
 * 工程基本属性枚举，对应profile.xml里配置的标签名
 */
public enum ProjectEnum {
	/* 工程根节点名字，groupId，artifactId */
	PROJECT_PATH("projectPath"), 
	GROUP_ID("groupId"),  
	ARTIFACT_ID("artifactId"),
	/* 数据源对应节点名 */
	URL("url"),
	DRIVER("driver"),
	USERNAME("username"),
	PASSWORD("password"),
	/* 各类包名的标签节点名 */
	ENTITY_PACKAGE("entityPackage"),
	DAO_PACKAGE_NAME("daoPackage"),
	SERVICE_PACKAGE_NAME("servicePackage"),
	CONTROLLER_PACKAGE_NAME("controllerPackage"),
	UTIL_PACKAGE_NAME("utilPackage"),
	DTO_PACKAGE_NAME("dtoPackage"),
	VO_PACKAGE_NAME("voPackage"),
	;

	private String elementName;
	
	private ProjectEnum(String elementName){
		this.elementName = elementName;
	}

	public String getElementName() {
		return elementName;
	}
		
	
}
