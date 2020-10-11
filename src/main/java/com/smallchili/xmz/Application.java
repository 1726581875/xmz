package com.smallchili.xmz;

import com.smallchili.xmz.factory.*;

public class Application {
	public static void main(String[] args) throws Exception {
		//生成springboot工程
		TemplateFactory.build(MavenProjectFactory.class).create();

		//按需生成
/*		TemplateFactory.build(DaoTemplateFactory.class).create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\dao");
		TemplateFactory.build(ServiceTemplateFactory.class).create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\service","service-lombok");
		TemplateFactory.build(ControllerTemplateFactory.class).create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\controller");
		TemplateFactory.build(EntityTemplateFactory.class).create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\entity","entity-lombok");
		TemplateFactory.build(DtoTemplateFactory.class).create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\dto","dto-lombok");
		TemplateFactory.build(UtilTemplateFactory.class).create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\util");
	*/

/*
	TemplateFactory.build(TestTemplateFactory.class).create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\test");
	TemplateFactory.build(ControllerTemplateFactory.class).create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\controller");
	TemplateFactory.build(ServiceTemplateFactory.class).create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\service","service-lombok");
*/

	}
}
