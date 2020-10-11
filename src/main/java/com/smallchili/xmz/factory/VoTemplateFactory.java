package com.smallchili.xmz.factory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.smallchili.xmz.util.BuildPathUtil;
import com.smallchili.xmz.util.NameConverUtil;
import com.smallchili.xmz.util.XmlUtil;

public class VoTemplateFactory implements TemplateFactory {

	public static final String VO_TEMPLATE_PATH = BuildPathUtil.buildDirPath(TEMPLATE_PATH, "vo");

	public static final String RESULT_TEMPLATE_ANME = "resp_result";
	
	public static final String PAGE_VO_TEMPLATE_ANME = "page_vo";

	@Override
	public void create() {

	}

	@Override
	public void create(String destPath) {
		log.info("===开始创建VO类  begin===");
		createRespResult(destPath);
		createPageVO(destPath);
		log.info("===VO类创建完成  den===");
	}

	private void createPageVO(String destPath) {
		String fullPath = destPath + File.separator+ "PageVO.java";
		String author = XmlUtil.getRootElement().getName();
		Map<String, Object> templateParamMap = new HashMap<>();
		templateParamMap.put("author", author);
		templateParamMap.put("nowDate", new SimpleDateFormat("yyyy/MM/dd").format(new Date()).toString());
		templateParamMap.put("packageName", NameConverUtil.getPackageName("voPackage"));
		generateByTemplate(VO_TEMPLATE_PATH, PAGE_VO_TEMPLATE_ANME, 
				fullPath, templateParamMap);
		log.info("已创建 [PageVO.java]");
	}

	private void createRespResult(String destPath) {
		String fullPath = destPath + File.separator +"RespResult.java";
		String author = XmlUtil.getRootElement().getName();
		Map<String, Object> templateParamMap = new HashMap<>();
		templateParamMap.put("author", author);
		templateParamMap.put("nowDate", new SimpleDateFormat("yyyy/MM/dd").format(new Date()).toString());
		templateParamMap.put("packageName", NameConverUtil.getPackageName("voPackage"));
		generateByTemplate(VO_TEMPLATE_PATH, RESULT_TEMPLATE_ANME,
				fullPath, templateParamMap);
		log.info("已创建 [RespResult.java]");
	}

	@Override
	public void create(String destPath, String templateName) {
		log.info("===开始创建VO类  begin===");
		createRespResult(destPath);
		createPageVO(destPath);
		log.info("===VO类创建完成  den===");
	}

}
