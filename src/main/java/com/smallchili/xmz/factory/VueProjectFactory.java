package com.smallchili.xmz.factory;

public class VueProjectFactory implements TemplateFactory{

	
	
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
		
	}



	@Override
	public void create(String destPath) {
		
		
	}

}
