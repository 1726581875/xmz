package com.smallchili.xmz.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smallchili.xmz.factory.TemplateFactory;

/**
 * 检查工具类
 * @author xmz
 * @date 2020/10/10
 *
 */
public class CheckUtil {
	
	static Logger log = LoggerFactory.getLogger(TemplateFactory.class);
	
	/**
	 * 检查目录是否存在，若不存则创建
	 * @param path
	 */
	public void checkDir(String path){
		File destDir = new File(path);
		if (!destDir.exists()) {
			log.warn("目录{} 不存在", path);
			destDir.mkdirs();
			log.info("已创建目录{} ", path);
		}		
	}
	
	public void checkTemplate(String path){
		File template = new File(path);
		if (!template.exists()) {
			log.error("模板{}不存在", path);		
		}		
	}
	
	

}
