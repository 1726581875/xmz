package com.smallchili.xmz.factory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import com.smallchili.xmz.util.BuildPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 所有工厂公共父接口
 * @author xmz
 * @date 2020/9/18
 */
public interface TemplateFactory {
	// 模板基础路径
	String TEMPLATE_PATH = BuildPath.buildDir("src", "main","java","com","smallchili","xmz","template");
	
	Logger log = LoggerFactory.getLogger(TemplateFactory.class);

	void create();
	
	void create(String destPath);
	
	void create(String destPath, String templateName);
	 
	default void generateByTemplate(String templatePath, String templateName, String destPath, Map<String, Object> paramMap) {
		File templateDir = new File(templatePath);
		if (!templateDir.exists()) {
			log.error("====模板路径 {}  不存在，结束====",templatePath);
			throw new IllegalArgumentException("模板路径必须提前创建");
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
	
   /**
    * 如果目录不存在则创建目录	
    * @param destPath
    */
	default void checkAndCreateDir(String destPath) {
		File destDir = new File(destPath);
		if (!destDir.exists()) {
			try {
				log.warn("目录{} 不存在", destPath);
				destDir.mkdirs();
				log.info("已创建目录{} ", destPath);
			}catch (Exception e){
				log.error("创建目录{} 出现异常{}",destPath,e);
			}
		}		
	}
	
	/**
	 * 创建一个模板工厂
	 * @param clazz 工厂的class对象
	 * @return
	 */
	public static TemplateFactory build(Class<?> clazz){	
		TemplateFactory tempFactory = null;
		try {
			tempFactory = (TemplateFactory) clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {			
			e.printStackTrace();
		}
		return tempFactory;		
	}
	

}
