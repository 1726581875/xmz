package com.smallchili.xmz.factory;

import com.smallchili.xmz.util.BuildPathUtil;
import com.smallchili.xmz.util.FileUtil;
import com.smallchili.xmz.util.XmlUtil;

public class VueProjectFactory implements TemplateFactory{

	private String VUE_ROOT_PATH = BuildPathUtil.buildDirPath(XmlUtil.getText("projectPath"),"vue");
	
	private String VUE_PUBLIC_PATH = BuildPathUtil.buildDirPath(VUE_ROOT_PATH, "public");
	
	private String VUE_SRC_PATH = BuildPathUtil.buildDirPath(VUE_ROOT_PATH, "src");
	
	private String VUE_COMPONENTS_PATH = BuildPathUtil.buildDirPath(VUE_SRC_PATH,"components");
	
	private String VUE_VIEWS_PATH = BuildPathUtil.buildDirPath(VUE_SRC_PATH, "components");
	
	private String VUE_ASSETS_PATH = BuildPathUtil.buildDirPath(VUE_SRC_PATH, "assets");
	
	
	
	@Override
	public void create() {
		
	    // 1.创建工程基本目录
		initBaseDir();
		// 2.创建工程基础配置文件
		initBsaeConfig();
		// 3.创建index.html
		createIndexHtml();
		// 3.创建component组件
		createComponent();
		
	}

	
	
	private void createIndexHtml() {
		
	}



	private void createComponent() {
	}



	private void initBsaeConfig() {
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

}
