package com.smallchili.xmz;

import com.smallchili.xmz.factory.*;
import com.smallchili.xmz.factory.web.VueProjectFactory;

public class Application {
	public static void main(String[] args) throws Exception {

		//生成springboot工程
		TemplateFactory.build(MavenProjectFactory.class).create();
		//生成vue工程
		TemplateFactory.build(VueProjectFactory.class).create();

		//按需生成
/*		TemplateFactory.build(TestTemplateFactory.class).create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\test");
		TemplateFactory.build(ControllerTemplateFactory.class).create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\controller");
		TemplateFactory.build(ServiceTemplateFactory.class).create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\service", "service-lombok");
		TemplateFactory.build(EntityTemplateFactory.class).create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\entity", "entity-lombok");
		TemplateFactory.build(DaoTemplateFactory.class).create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\dao");
	*/
	}
}
