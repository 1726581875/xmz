package com.smallchili.xmz.factory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import com.smallchili.xmz.constant.PathConstant;
import com.smallchili.xmz.util.BuildPathUtil;
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
	public static final String DAO_TEMPLATE_PATH = BuildPathUtil.buildDirPath(TEMPLATE_PATH, "dao");
	// 模板名称
	public static final String TEMPLATE_NAME = "dao";
	
	@Override
	public void create() {
		//1.生成包
		//获取路径
		String daoPackageName = XmlUtil.getText("daoPath");
		String daoPackageDirPath = PathConstant.SOURCE_PATH + 
				NameConverUtil.getDirNameByPackageName(daoPackageName);		
		File daoPackageDir = new File(daoPackageDirPath);
		//如果目录不存在，创建目录
		if (!daoPackageDir.exists()) {
			daoPackageDir.mkdirs();
		}
        //获取全部 <表名,实体类名>
		Map<String, String> tableNameMap = XmlUtil.getTableNameMap();
		tableNameMap.forEach((tableName, ObjectName) -> {
			String keyType = DataBaseUtil.getPrimaryType(tableName);
			String author = XmlUtil.getRootElement().getName();
			Map<String, Object> map = new HashMap<>();
			map.put("daoPackageName", daoPackageName);
			map.put("entityPackageName", XmlUtil.getText("entityPath"));
			map.put("entityName", ObjectName);
			map.put("nowDate", new SimpleDateFormat("yyyy/MM/dd").format(new Date()).toString());	
			map.put("keyType", keyType);
			map.put("author", author);
			generateByTemplate(DAO_TEMPLATE_PATH, TEMPLATE_NAME, daoPackageDirPath + ObjectName +"Repository" +".java", map);					     
		});
		
	}
	
	public void create(String destPath) {			
		Map<String, String> tableNameMap = XmlUtil.getTableNameMap();
		tableNameMap.forEach((tableName, ObjectName) -> {
			String destFullPath = destPath + File.separator +ObjectName +"Repository" +".java";
			String keyType = DataBaseUtil.getPrimaryType(tableName);
			String author = XmlUtil.getRootElement().getName();
			Map<String, Object> map = new HashMap<>();
			map.put("daoPackageName", NameConverUtil.getPackageName("daoPackage"));
			map.put("entityPackageName", NameConverUtil.getPackageName("entityPackage"));
			map.put("entityName", ObjectName);
			map.put("nowDate", new SimpleDateFormat("yyyy/MM/dd").format(new Date()).toString());	
			map.put("keyType", keyType);
			map.put("author", author);
			generateByTemplate(DAO_TEMPLATE_PATH, TEMPLATE_NAME, destFullPath, map);					     
		});
		
	}
	
	
	@Test
	public void test(){
		create();
	}

}
