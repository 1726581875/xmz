package com.smallchili.xmz.factory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.smallchili.xmz.constant.PathConstant;
import com.smallchili.xmz.enums.ProjectEnum;
import com.smallchili.xmz.model.Field;
import com.smallchili.xmz.util.BuildPath;
import com.smallchili.xmz.util.DataBaseUtil;
import com.smallchili.xmz.util.NameConverUtil;
import com.smallchili.xmz.util.XmlUtil;
/**
 * 生成实体类的工厂
 * @author xmz
 * 2020/9/18
 *
 */
public class EntityTemplateFactory implements TemplateFactory {

	public static final String ENTITY_TEMPLATE_PATH = BuildPath
			.buildDir(TEMPLATE_PATH, "model");
	
	public static final String TEMPLATE_NAME = "entity";
	
	/**
	 *  1.生成包目录
	 *  2.生成实体类
	 *  
	 */
	@Override
	public void create() {
		//xml里配置的实体类包名路径
		String defaultDirPath = PathConstant.SOURCE_PATH
				+ BuildPath.converToDir(NameConverUtil.getPackageName(
						ProjectEnum.ENTITY_PACKAGE.getElementName()));

		create(defaultDirPath);
		
	}

	/**
	 * 生成实体类
	 * @param destPath 目标路径
	 */
	@Override
	public void create(String destPath) {
		create(destPath, TEMPLATE_NAME);
	}
	
	@Override
	public void create(String destPath, String templateName) {	
		checkAndCreateDir(destPath);		
		// 获取配置profile.xml文件里的所有配置,map<tableName,ObjectName>
		Map<String, String> tableMap = XmlUtil.getTableNameMap();
		log.info("======开始生成数据库实体类======");
		try{
		// 遍历多个表信息,生成数据库对应实体类
		tableMap.forEach((tableName, objectName) ->{
			// 目标文件全路径
			String destFullPath = destPath + File.separator + objectName +".java";
			// 获取列信息
			List<Field> fieldList = DataBaseUtil.getColumnByTableName(tableName);
			Set<String> javaTypeSet = DataBaseUtil.getJavaTypes(fieldList);
			Map<String, Object> templateParamMap = new HashMap<>();
			templateParamMap.put("fieldList", fieldList);
			templateParamMap.put("javaTypeSet", javaTypeSet);
			templateParamMap.put("className", objectName);
			templateParamMap.put("tableName",tableName);
			templateParamMap.put("packageName", NameConverUtil.getPackageName(ProjectEnum.ENTITY_PACKAGE.getElementName()));
			//传入参数，根据模板生成对应类
			generateByTemplate(ENTITY_TEMPLATE_PATH, templateName, destFullPath, templateParamMap);
			log.info("已创建 [{}.java]", objectName);
		});
		}catch(Exception e){
			log.error("======数据库实体类生成发生异常，异常信息:{}======", e);
		}
		log.info("======数据库实体类生成完成======");
		
	}

}
