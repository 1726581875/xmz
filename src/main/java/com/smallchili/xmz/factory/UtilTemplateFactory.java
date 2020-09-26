package com.smallchili.xmz.factory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import com.smallchili.xmz.constant.PathConstant;
import com.smallchili.xmz.util.BuildPathUtil;
import com.smallchili.xmz.util.NameConverUtil;
import com.smallchili.xmz.util.XmlUtil;

public class UtilTemplateFactory implements TemplateFactory {

	public static final String UTIL_TEMPLATE_PATH = BuildPathUtil.buildDirPath(TEMPLATE_PATH, "util");
	
	public static final String COPY_UTIL_TEMPLATE_ANME = "CopyUtil";
	
	@Test
	public void test(){
		create();
	}
	
	
	@Override
	public void create() {
		// 获取路径
		String utilPackageName = XmlUtil.getText("utilPath");
		String utilPackageDirPath = PathConstant.SOURCE_PATH + NameConverUtil.getDirNameByPackageName(utilPackageName);
		File utilPackageDir = new File(utilPackageDirPath);
		// 如果目录不存在，创建目录
		if (!utilPackageDir.exists()) {
			utilPackageDir.mkdirs();
		}
		
		createCopyUtil(utilPackageDirPath);
	}

	/**
	 * 创建CopyUtil
	 * @param path
	 */
	private void createCopyUtil(String dirPath) { 
		//目标文件全路径
		String fullPath = dirPath + File.separator +"CopyUtil.java";
		String author = XmlUtil.getRootElement().getName();
		Map<String, Object> templateParamMap = new HashMap<>();
		templateParamMap.put("nowTime", new SimpleDateFormat("yyyy/MM/dd").format(new Date()).toString());
		templateParamMap.put("author", author);
		templateParamMap.put("packageName", NameConverUtil.getPackageName("utilPackage"));
		generateByTemplate(UTIL_TEMPLATE_PATH, COPY_UTIL_TEMPLATE_ANME,
				fullPath, templateParamMap);
		log.info("已创建 [CopyUtil.java]");
	}
	

	@Override
	public void create(String destPath) {
		log.info("===开始创建Util工具类  begin===");
		createCopyUtil(destPath);
		log.info("===Util工具类创建完成  end===");
	}
	

}
