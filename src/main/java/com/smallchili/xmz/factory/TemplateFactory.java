package com.smallchili.xmz.factory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public interface TemplateFactory {

	static final String TEMPLATE_PATH = "src\\main\\java\\com\\smallchili\\xmz\\template\\";
	
	static Logger log = LoggerFactory.getLogger(TemplateFactory.class);

	void create();
	
	void create(String destPath);
	 
	default void generateByTemplate(String templatePath, String templateName, String destPath, Map<String, Object> paramMap) {
		File templateDir = new File(templatePath);
		if (!templateDir.exists()) {
			log.error("====模板路径 {}  不存在，结束====");
			return;
		}			
		try (FileWriter fileWriter = new FileWriter(destPath);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
			// 读模板
			Configuration config = new Configuration(Configuration.VERSION_2_3_29);
			config.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
			config.setDirectoryForTemplateLoading(templateDir);
			Template template = config.getTemplate(templateName + ".ftl");
			// 写
			template.process(paramMap, bufferedWriter);
			bufferedWriter.flush();
		} catch (Exception e) {
			log.error("====加载模板{} 生成代码失败，发生异常{}====", templateName, e);
		}
	}

}
