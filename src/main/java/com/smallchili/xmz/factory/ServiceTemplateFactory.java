package com.smallchili.xmz.factory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.smallchili.xmz.util.NameConverUtil;
import com.smallchili.xmz.util.XmlUtil;

/**
 * @author xmz
 * @date 2020/09/20
 *
 */
public class ServiceTemplateFactory implements TemplateFactory {

	public static final String SERVICE_TEMPLATE_PATH = TEMPLATE_PATH + "\\service\\";
	
	public static final String TEMPLATE_NAME = "service";
	
	@Override
	public void create() {

	}

	@Override
	public void create(String destPath) {
		log.info("======开始生成Service类   begin======");
		Map<String, String> tableMap = XmlUtil.getTableNameMap();
		/* 获取包名 */
		String servicePkName = NameConverUtil.getPackageName("servicePackage");
		String entityPkName = NameConverUtil.getPackageName("entityPackage");
		String daoPkName = NameConverUtil.getPackageName("daoPackage");
		String dtoPkName = NameConverUtil.getPackageName("dtoPackage");
		String voPkName = NameConverUtil.getPackageName("voPackage");
		String utilPkName = NameConverUtil.getPackageName("utilPackage");
		/* 类作者、日期 */
		String author = XmlUtil.getRootElement().getName();
		String nowDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date()).toString();		
		try {
			tableMap.forEach((tableName, entityName) -> {			
				Map<String, Object> templateParamMap = new HashMap<>();
				templateParamMap.put("servicePkName",servicePkName);
				templateParamMap.put("entityPkName", entityPkName);
				templateParamMap.put("daoPkName", daoPkName);
				templateParamMap.put("dtoPkName", dtoPkName);
				templateParamMap.put("voPkName", voPkName);
				templateParamMap.put("utilPkName", utilPkName);
				templateParamMap.put("author", author);
				templateParamMap.put("nowDate", nowDate);
				//设置实体名
				templateParamMap.put("Domain", entityName);
				templateParamMap.put("domain", NameConverUtil.lineToHump(tableName));
				generateByTemplate(SERVICE_TEMPLATE_PATH, TEMPLATE_NAME, destPath + "//" + entityName + "Service.java",
						templateParamMap);
				log.info("生成{}Service.java", entityName);
			});
		} catch (Exception e) {
			log.error("======Service类生成发生异常，异常信息:{}======", e);
		}
		log.info("======Service类生成完成  end======");

	}

}
