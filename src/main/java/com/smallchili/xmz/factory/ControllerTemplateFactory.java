package com.smallchili.xmz.factory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.smallchili.xmz.util.NameConverUtil;
import com.smallchili.xmz.util.XmlUtil;

public class ControllerTemplateFactory implements TemplateFactory{

	public static final String CONTROLLER_TEMPLATE_PATH = TEMPLATE_PATH + "\\controller\\";
	
	public static final String TEMPLATE_NAME = "controller";
	
	@Override
	public void create() {
		
	}

	@Override
	public void create(String destPath) {
		log.info("======开始生成Controller类   begin======");
		Map<String, String> tableMap = XmlUtil.getTableNameMap();
		/* 获取包名 */
		String controllerPkName = NameConverUtil.getPackageName("controllerPackage");
		String servicePkName = NameConverUtil.getPackageName("servicePackage");
		String entityPkName = NameConverUtil.getPackageName("entityPackage");	
		String voPkName = NameConverUtil.getPackageName("voPackage");	
		/* 类作者、日期 */
		String author = XmlUtil.getRootElement().getName();
		String nowDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date()).toString();		
		try {
			tableMap.forEach((tableName, entityName) -> {			
				Map<String, Object> templateParamMap = new HashMap<>();
				templateParamMap.put("controllerPkName",controllerPkName);
				templateParamMap.put("servicePkName",servicePkName);
				templateParamMap.put("entityPkName", entityPkName);
				templateParamMap.put("voPkName", voPkName);
				templateParamMap.put("author", author);
				templateParamMap.put("nowDate", nowDate);
				//设置实体名
				templateParamMap.put("Domain", entityName);
				templateParamMap.put("domain", NameConverUtil.lineToHump(tableName));
				generateByTemplate(CONTROLLER_TEMPLATE_PATH, TEMPLATE_NAME, destPath + "//" + entityName + "Controller.java",
						templateParamMap);
				log.info("生成{}Controller.java", entityName);
			});
		} catch (Exception e) {
			log.error("======Controller类生成发生异常，异常信息:{}======", e);
		}
		log.info("======Controller类生成完成  end======");
	}

}
