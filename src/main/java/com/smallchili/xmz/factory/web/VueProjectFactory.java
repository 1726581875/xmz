package com.smallchili.xmz.factory.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.smallchili.xmz.factory.TemplateFactory;
import com.smallchili.xmz.model.Field;
import com.smallchili.xmz.model.Table;
import com.smallchili.xmz.util.*;
import freemarker.template.Template;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VueProjectFactory extends WebTemplateFactory {

	private final static Logger log = LoggerFactory.getLogger(VueProjectFactory.class);

	// vue模板位置
	private String VUE_TEMPLATE_PATH = BuildPath.buildDir(TEMPLATE_PATH,"web");
	// vue根目录
	private String VUE_ROOT_PATH = BuildPath.buildDir(XmlUtil.getText("projectPath"),"vue-" + XmlUtil.getText("artifactId"));
	
	private String VUE_PUBLIC_PATH = BuildPath.buildDir(VUE_ROOT_PATH, "public");

	private String VUE_SRC_PATH = BuildPath.buildDir(VUE_ROOT_PATH, "src");

	private String VUE_ROUTER_PATH = BuildPath.buildDir(VUE_SRC_PATH, "router");

	private String VUE_COMPONENTS_PATH = BuildPath.buildDir(VUE_SRC_PATH,"components");
	
	private String VUE_VIEWS_PATH = BuildPath.buildDir(VUE_SRC_PATH, "views");
	
	private String VUE_ASSETS_PATH = BuildPath.buildDir(VUE_SRC_PATH, "assets");



	@Test
	public void testCreate(){
		create();
	}
	
	@Override
	public void create() {
		log.info("=======开始生成vue工程  begin======");

	    // 1.创建工程基本目录
		initBaseDir();
		// 2.创建工程基础配置文件
		initBsaeConfig();
		// 3.创建router.js
		createRouterJs(VUE_ROUTER_PATH);

		// 3.创建component组件
		createComponent();

		log.info("=======vue工程生成完成  end======");
	}



	private void createComponent() {
		TemplateFactory.build(ViewTemplateFactory.class).create(VUE_VIEWS_PATH);
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
		createAppJS(VUE_SRC_PATH);

		// index.html
		generateByTemplate(VUE_TEMPLATE_PATH, "index",
				VUE_PUBLIC_PATH + File.separator + "index.html", null);
				
	}

    private void createAppJS(String destPath){
		FileUtil.mkdirs(destPath);
		List<Table> tableList = XmlUtil.getTableList();
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("tableList",tableList);
		generateByTemplate(VUE_TEMPLATE_PATH, "App",
				destPath + File.separator + "App.vue", paramMap);
	}

	private void createRouterJs(String destPath) {
		FileUtil.mkdirs(destPath);
		List<Table> tableList = XmlUtil.getTableList();
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("tableList",tableList);
		generateByTemplate(VUE_TEMPLATE_PATH + File.separator + "router", "index",
				destPath + File.separator + "index.js", paramMap);
	}

	@Test
	public void createTest(){
		File file = new File(VUE_SRC_PATH);
		if(!file.exists()) {
			file.mkdirs();
		}
		createRouterJs(VUE_ROUTER_PATH);
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
