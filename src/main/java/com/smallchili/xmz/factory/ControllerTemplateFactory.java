package com.smallchili.xmz.factory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.smallchili.xmz.model.Author;
import com.smallchili.xmz.util.BuildPath;
import com.smallchili.xmz.util.NameConverUtil;
import com.smallchili.xmz.util.XmlUtil;
/**
 * controller模板工厂
 * @author xmz
 * @date 2020/09/26
 */
public class ControllerTemplateFactory implements TemplateFactory{
    // 模板路径
	public static final String CONTROLLER_TEMPLATE_PATH = BuildPath.buildDir(TEMPLATE_PATH, "controller");
	// 默认模板名
	public static final String DEFAULT_TEMPLATE_NAME = "controller";
	
	@Override
	public void create() {
		 
	}

	@Override
	public void create(String destPath) {
		create(destPath,DEFAULT_TEMPLATE_NAME);
	}

	@Override
	public void create(String destPath, String templateName) {	
		//checkTemplate(CONTROLLER_TEMPLATE_PATH + File.separator + templateName);
		checkAndCreateDir(destPath);
		
		log.info("======开始生成Controller类   begin======");
		Map<String, String> tableMap = XmlUtil.getTableNameMap();
		/* 获取包名 */
		String controllerPkName = NameConverUtil.getPackageName("controllerPackage");
		String servicePkName = NameConverUtil.getPackageName("servicePackage");
		String entityPkName = NameConverUtil.getPackageName("entityPackage");	
		String voPkName = NameConverUtil.getPackageName("voPackage");	
		/* 类作者、日期 */
		String author = XmlUtil.getRootElement().getName();
		String nowDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		try {
			tableMap.forEach((tableName, entityName) -> {
				String destFullPath = destPath + File.separator + entityName + "Controller.java";
				Map<String, Object> templateParamMap = new HashMap<>();
				templateParamMap.put("controllerPkName",controllerPkName);
				templateParamMap.put("servicePkName",servicePkName);
				templateParamMap.put("entityPkName", entityPkName);
				templateParamMap.put("voPkName", voPkName);
				templateParamMap.put("Author", Author.build());
				//设置实体名
				templateParamMap.put("Domain", entityName);
				templateParamMap.put("domain", NameConverUtil.bigHumpToHump(entityName));
				generateByTemplate(CONTROLLER_TEMPLATE_PATH, templateName,
						destFullPath,templateParamMap);
				log.info("已创建 [{}Controller.java]", entityName);
			});
		} catch (Exception e) {
			log.error("======Controller类生成发生异常，异常信息:{}======", e);
		}
		log.info("======Controller类生成完成  end======");
		
	}

}
