package com.smallchili.xmz.factory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.smallchili.xmz.constant.PathConstant;
import com.smallchili.xmz.model.Author;
import com.smallchili.xmz.util.BuildPath;
import com.smallchili.xmz.util.DataBaseUtil;
import com.smallchili.xmz.util.NameConverUtil;
import com.smallchili.xmz.util.XmlUtil;

/**
 * dao模板工厂
 * @author xmz
 * @date 2020/09/26
 *
 */
public class DaoTemplateFactory implements TemplateFactory {
    // 模板位置
	public static final String DAO_TEMPLATE_PATH = BuildPath.buildDir(TEMPLATE_PATH, "dao");
	// 模板名称
	public static final String TEMPLATE_NAME = "dao";
	
	@Override
	public void create() {
        // TODO 待测试
		String defaultDirPath = PathConstant.SOURCE_PATH +
				BuildPath.converToDir(NameConverUtil.getPackageName("daoPackage"));
		create(defaultDirPath);
	}
	
	public void create(String destPath) {
		create(destPath,TEMPLATE_NAME);
	}


	@Override
	public void create(String destPath, String templateName) {
		checkAndCreateDir(destPath);

		Map<String, String> tableNameMap = XmlUtil.getTableNameMap();
		tableNameMap.forEach((tableName, ObjectName) -> {
			String destFullPath = destPath + File.separator +ObjectName +"Repository" +".java";
			String keyType = DataBaseUtil.getPrimaryType(tableName);
			String author = XmlUtil.getRootElement().getName();
			Map<String, Object> map = new HashMap<>();
			map.put("daoPackageName", NameConverUtil.getPackageName("daoPackage"));
			map.put("entityPackageName", NameConverUtil.getPackageName("entityPackage"));
			map.put("entityName", ObjectName);
			map.put("keyType", keyType);
			map.put("Author", Author.build());
			generateByTemplate(DAO_TEMPLATE_PATH, templateName, destFullPath, map);
		});
	}

}
