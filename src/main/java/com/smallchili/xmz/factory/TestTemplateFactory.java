package com.smallchili.xmz.factory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import com.smallchili.xmz.enums.ProjectEnum;
import com.smallchili.xmz.util.BuildPathUtil;
import com.smallchili.xmz.util.NameConverUtil;
import com.smallchili.xmz.util.XmlUtil;

/**
 * 生成实体类的工厂
 * @author xmz
 * @date 2020/10/11
 */
public class TestTemplateFactory implements TemplateFactory {

	// 测试模板的位置
	private final String TEST_TEMPLATE_PATH = BuildPathUtil.buildDirPath(TEMPLATE_PATH, "test");

	private final String CONTROLLER_TEST_TEMPLATE_PATH = BuildPathUtil.buildDirPath(TEST_TEMPLATE_PATH, "controller");
    //测试包名，和项目源码包名一样 groupId + artifactId
	private final String TEST_PACKAGE_NAME = NameConverUtil.getPackageName(null);
	
	@Override
	public void create() {

	}

	@Override
	public void create(String destPath) {
		create(destPath, null);
	}

	@Override
	public void create(String destPath, String templateName) {

		createBaseMvcTest(destPath, "base_mvc_test");

		createControllerTest(destPath + File.separator + "controller", "controller_test");

		//createServiceTest(destPath, templateName);

	}

	/**
	 * 生成BaseMvcTest.java
	 * @param destPath
	 * @param templateName
	 */
	private void createBaseMvcTest(String destPath, String templateName) {
		checkAndCreateDir(destPath);

		// 目标文件全路径
		String fullPath = destPath + File.separator + "BaseMvcTest.java";
		
		String author = XmlUtil.getRootElement().getName();

		Map<String, Object> templateParamMap = new HashMap<>();
		templateParamMap.put("nowDate", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		templateParamMap.put("author", author);
		templateParamMap.put("packageName", TEST_PACKAGE_NAME);
		generateByTemplate(TEST_TEMPLATE_PATH, templateName, fullPath, templateParamMap);
		log.info("已创建 [BaseMvcTest.java]");

	}

	/**
	 * 生成controller层测试类
	 * @param destPath
	 * @param templateName
	 */
	private void createControllerTest(String destPath, String templateName) {
		checkAndCreateDir(destPath);
		// 获取配置profile.xml文件里的所有配置,map<tableName,ObjectName>
		Map<String, String> tableMap = XmlUtil.getTableNameMap();
		log.info("======开始生成controller层测试类======");
		try{
			// 遍历多个表信息,生成数据库对应实体类
			tableMap.forEach((tableName, objectName) ->{
				// 目标文件全路径
				String destFullPath = destPath + File.separator + objectName +"ControllerTest.java";

				Map<String, Object> templateParamMap = new HashMap<>();
				templateParamMap.put("author", XmlUtil.getRootElement().getName());
				templateParamMap.put("nowDate", new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
				templateParamMap.put("Domain", objectName);
				templateParamMap.put("domain",NameConverUtil.lineToHump(objectName));
				templateParamMap.put("packageName", NameConverUtil.getPackageName(
						ProjectEnum.CONTROLLER_PACKAGE_NAME.getElementName()));
				templateParamMap.put("testPkName",TEST_PACKAGE_NAME);
				//传入参数，根据模板生成对应类
				generateByTemplate(TEST_TEMPLATE_PATH, templateName, destFullPath, templateParamMap);
				log.info("已创建 [{}ControllerTest.java]", objectName);
			});
		}catch(Exception e){
			log.error("======controller层测试类生成发生异常，异常信息:{}======", e);
			return;
		}
		log.info("======controller层测试类生成完成======");
	}


	private void createServiceTest(String destPath, String templateName) {

	}


}
