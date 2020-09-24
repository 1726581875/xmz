package com.smallchili.xmz.enums;

/**
 * 数据源枚举类
 * @author xmz
 * 2020年9月12日
 *
 */
public enum DataSourceEnum {
	URL("url"), DRIVER("driver"), USERNAME("username"),PASSWORD("password");
	private String nodeName;
	private DataSourceEnum(String nodeName){
		this.nodeName = nodeName;
	}
	public String getValue(){
		return this.nodeName;
	}
}
