package com.smallchili.xmz.factory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.smallchili.xmz.constant.PathConstant;
import com.smallchili.xmz.model.Author;
import com.smallchili.xmz.util.BuildPath;
import com.smallchili.xmz.util.NameConverUtil;
import com.smallchili.xmz.util.XmlUtil;

public class UtilTemplateFactory implements TemplateFactory {

	public static final String UTIL_TEMPLATE_PATH = BuildPath.buildDir(TEMPLATE_PATH, "util");
	
	public static final String COPY_UTIL_TEMPLATE_NAME = "copy_util";
	
	
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


	@Override
	public void create(String destPath) {
		create(destPath, null);
	}


	@Override
	public void create(String destPath, String templateName) {

		log.info("===开始创建Util工具类  begin===");
		createCopyUtil(destPath);
		log.info("===Util工具类创建完成  end===");

	}

	/**
	 *
	 * @param destPath
	 */
	private void createCopyUtil(String destPath) {
		checkAndCreateDir(destPath);
		//目标文件全路径
		String fullPath = destPath + File.separator +"CopyUtil.java";
		String author = XmlUtil.getRootElement().getName();
		Map<String, Object> templateParamMap = new HashMap<>();
		templateParamMap.put("Author", Author.build());
		templateParamMap.put("packageName", NameConverUtil.getPackageName("utilPackage"));
		generateByTemplate(UTIL_TEMPLATE_PATH, COPY_UTIL_TEMPLATE_NAME,
				fullPath, templateParamMap);
		log.info("已创建 [CopyUtil.java]");
	}


}
