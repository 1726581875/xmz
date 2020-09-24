package com.smallchili.xmz.util;

import java.io.File;
import java.util.Random;

/**
 * 目录操作工具类
 * @author xmz
 * 2020年9月14日
 *
 */
public class FileUtil {

	/**
	 * 
	 * 如果目录不存在，创建目录
	 */
	public static void mkdirs(String dirPath){		
		File dir = new File(dirPath);
		if(!dir.exists()){
			dir.mkdirs();
		}	
	}

	public static void main(String[] args) {
		
	}
	
}
