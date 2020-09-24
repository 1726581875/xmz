package com.smallchili.xmz;

import com.smallchili.xmz.factory.MavenProjectFactory;
import com.smallchili.xmz.factory.TemplateFactory;

public class Application {

	public static void main(String[] args) throws Exception {
		TemplateFactory mavenPjFactory = new MavenProjectFactory();
		mavenPjFactory.create();
	}
}
