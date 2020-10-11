package com.smallchili.xmz.factory;

import java.io.File;

import com.smallchili.xmz.util.BuildPathUtil;
import com.smallchili.xmz.util.FileUtil;
import com.smallchili.xmz.util.XmlUtil;
import org.junit.jupiter.api.Test;

public class VueProjectFactory implements TemplateFactory{

	// vue模板位置
	private String VUE_TEMPLATE_PATH = BuildPathUtil.buildDirPath(TEMPLATE_PATH,"vue");
	
	private String VUE_ROOT_PATH = BuildPathUtil.buildDirPath(XmlUtil.getText("projectPath"),"vue-" + XmlUtil.getText("artifactId"));
	
	private String VUE_PUBLIC_PATH = BuildPathUtil.buildDirPath(VUE_ROOT_PATH, "public");
	
	private String VUE_SRC_PATH = BuildPathUtil.buildDirPath(VUE_ROOT_PATH, "src");
	
	private String VUE_COMPONENTS_PATH = BuildPathUtil.buildDirPath(VUE_SRC_PATH,"components");
	
	private String VUE_VIEWS_PATH = BuildPathUtil.buildDirPath(VUE_SRC_PATH, "views");
	
	private String VUE_ASSETS_PATH = BuildPathUtil.buildDirPath(VUE_SRC_PATH, "assets");
	
	@Test
	public void testCreate(){
		create();
	}
	
	@Override
	public void create() {
		
	    // 1.创建工程基本目录
		initBaseDir();
		// 2.创建工程基础配置文件
		initBsaeConfig();
		// 3.创建router.js
		createRouterJs();
		
		// 3.创建component组件
		createComponent();
		
	}



	private void createRouterJs() {
		
		
		
	}

	private void createComponent() {
	}



	private void initBsaeConfig() {
		
		// babel.config.js
		generateByTemplate(VUE_TEMPLATE_PATH, "babel.config",
				VUE_ROOT_PATH + File.separator + "babel.config.js", null);
		
		// package.json
		generateByTemplate(VUE_TEMPLATE_PATH, "package",
				VUE_ROOT_PATH + File.separator + "package.json", null);
		
		// package-lock.json
		generateByTemplate(VUE_TEMPLATE_PATH, "package-lock",
				VUE_ROOT_PATH + File.separator + "package-lock.json", null);
		
		// vue.config.js
		generateByTemplate(VUE_TEMPLATE_PATH, "vue.config",
				VUE_ROOT_PATH + File.separator + "vue.config.js", null);
		
		// main.js
		generateByTemplate(VUE_TEMPLATE_PATH, "main",
				VUE_SRC_PATH + File.separator + "main.js", null);
		// App.vue
		generateByTemplate(VUE_TEMPLATE_PATH, "App",
				VUE_SRC_PATH + File.separator + "App.vue", null);
		
		// index.html
		generateByTemplate(VUE_TEMPLATE_PATH, "index",
				VUE_PUBLIC_PATH + File.separator + "index.html", null);
				
	}


	private void initBaseDir() {
		/* 创建vue基本目录 */
	    FileUtil.mkdirs(VUE_ROOT_PATH);
	    FileUtil.mkdirs(VUE_SRC_PATH);
	    FileUtil.mkdirs(VUE_PUBLIC_PATH);
	    FileUtil.mkdirs(VUE_COMPONENTS_PATH);
	    FileUtil.mkdirs(VUE_VIEWS_PATH);
	    FileUtil.mkdirs(VUE_ASSETS_PATH);
	}



	@Override
	public void create(String destPath) {
		
		
	}

	@Override
	public void create(String destPath, String templateName) {
	}

}
