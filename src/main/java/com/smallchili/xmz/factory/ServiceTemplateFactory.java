package com.smallchili.xmz.factory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.smallchili.xmz.util.BuildPathUtil;
import com.smallchili.xmz.util.DataBaseUtil;
import com.smallchili.xmz.util.NameConverUtil;
import com.smallchili.xmz.util.XmlUtil;

/**
 * @author xmz
 * @date 2020/09/20
 *
 */
public class ServiceTemplateFactory implements TemplateFactory {

	public static final String SERVICE_TEMPLATE_PATH = BuildPathUtil.buildDirPath(TEMPLATE_PATH, "service");
	
	public static final String DEFAULT_TEMPLATE_NAME = "service";
	
	@Override
	public void create() {

	}

	@Override
	public void create(String destPath) {
		create(destPath, DEFAULT_TEMPLATE_NAME);
	}
	
	public void create(String destPath, String templateName) {
		checkAndCreateDir(destPath);
		
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
				// 目标文件全路径
				String destFullPath = destPath + File.separator + entityName + "Service.java";
				// 模板参数Map
				Map<String, Object> templateParamMap = new HashMap<>();
				templateParamMap.put("servicePkName",servicePkName);
				templateParamMap.put("entityPkName", entityPkName);
				templateParamMap.put("daoPkName", daoPkName);
				templateParamMap.put("dtoPkName", dtoPkName);
				templateParamMap.put("voPkName", voPkName);
				templateParamMap.put("utilPkName", utilPkName);
				templateParamMap.put("author", author);
				templateParamMap.put("nowDate", nowDate);
				templateParamMap.put("entityKey", DataBaseUtil.getPrimaryName(tableName));
				//设置实体名
				templateParamMap.put("Domain", entityName);
				templateParamMap.put("domain", NameConverUtil.lineToHump(entityName));
				generateByTemplate(SERVICE_TEMPLATE_PATH, templateName,
						destFullPath,templateParamMap);
				log.info("已创建 [{}Service.java]", entityName);
			});
		} catch (Exception e) {
			log.error("======Service类生成发生异常，异常信息:{}======", e);
		}
		log.info("======Service类生成完成  end======");

	}
	

}
