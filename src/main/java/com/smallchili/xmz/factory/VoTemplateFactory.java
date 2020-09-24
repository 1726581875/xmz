package com.smallchili.xmz.factory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.smallchili.xmz.util.NameConverUtil;
import com.smallchili.xmz.util.XmlUtil;

public class VoTemplateFactory implements TemplateFactory {

	public static final String VO_TEMPLATE_PATH = TEMPLATE_PATH + "vo\\";

	public static final String RESULT_TEMPLATE_ANME = "RespResult";
	
	public static final String PAGE_VO_TEMPLATE_ANME = "PageVO";

	@Override
	public void create() {

	}

	@Override
	public void create(String destPath) {

		createRespResult(destPath);
		createPageVO(destPath);
	}

	private void createPageVO(String destPath) {
		String author = XmlUtil.getRootElement().getName();
		Map<String, Object> templateParamMap = new HashMap<>();
		templateParamMap.put("author", author);
		templateParamMap.put("nowDate", new SimpleDateFormat("yyyy/MM/dd").format(new Date()).toString());
		templateParamMap.put("packageName", NameConverUtil.getPackageName("voPackage"));
		generateByTemplate(VO_TEMPLATE_PATH, PAGE_VO_TEMPLATE_ANME, destPath + "\\PageVO.java", templateParamMap);

	}

	private void createRespResult(String destPath) {
		String author = XmlUtil.getRootElement().getName();
		Map<String, Object> templateParamMap = new HashMap<>();
		templateParamMap.put("author", author);
		templateParamMap.put("nowDate", new SimpleDateFormat("yyyy/MM/dd").format(new Date()).toString());
		templateParamMap.put("packageName", NameConverUtil.getPackageName("voPackage"));
		generateByTemplate(VO_TEMPLATE_PATH, RESULT_TEMPLATE_ANME, destPath + "\\RespResult.java", templateParamMap);
	}

}
