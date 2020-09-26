package com.smallchili.xmz.util;

import java.io.File;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 目录操作工具类
 * @author xmz
 * 2020年9月14日
 *
 */
public class FileUtil {

	public static Logger log = LoggerFactory.getLogger(FileUtil.class);
	
	
	/**
	 * 
	 * 如果目录不存在，创建目录
	 */
	public static void mkdirs(String dirPath){		
		File dir = new File(dirPath);
		if(!dir.exists()){
			dir.mkdirs();
			/*log.info("创建目录  [{}]",dirPath);*/
		}	
	}

	public static void main(String[] args) {
		
	}
	
}
