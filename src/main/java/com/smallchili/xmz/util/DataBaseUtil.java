package com.smallchili.xmz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smallchili.xmz.enums.DataSourceEnum;
import com.smallchili.xmz.enums.TypeMappingEnum;
import com.smallchili.xmz.model.Field;

/**
 * @author xmz
 * 2020年9月12日
 * 数据库连接工具
 */
public class DataBaseUtil {

	private static Logger log = LoggerFactory.getLogger(DataBaseUtil.class);
	/**
	 * 获取数据库连接
	 * @return
	 */
    public static Connection getConnection() {
        Connection conn = null;
        String url = null;
        String user = null;
        String password = null;
        try {
            Class.forName(XmlUtil.getText(DataSourceEnum.DRIVER.getValue()));
            url = XmlUtil.getText(DataSourceEnum.URL.getValue());
            user = XmlUtil.getText(DataSourceEnum.USERNAME.getValue());
            password = XmlUtil.getText(DataSourceEnum.PASSWORD.getValue());
            conn = DriverManager.getConnection(url, user, password);          
        } catch (Exception e) {
        	log.info("driver:{}", XmlUtil.getText(DataSourceEnum.DRIVER.getValue()));
        	log.info("url:{}",url);
        	log.info("user:{}",user);
        	log.info("password:{}",password);
        	log.error("=====获取数据库连接失败{}====",e);
        	throw new RuntimeException("获取数据库连接失败");
        } 
        
        return conn;
    }
    
    
    
    public static List<Field> getColumnByTableName(String tableName){
		 try{
	        List<Field> fieldList = new ArrayList<>();
	        Connection conn = DataBaseUtil.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("show full columns from `" + tableName + "`");
	        if (rs != null) {
	            while(rs.next()) {
	                String columnName = rs.getString("Field");
	                String type = rs.getString("Type");
	                String comment = rs.getString("Comment");
	                String nullAble = rs.getString("Null"); //YES NO
	                String keyType = rs.getString("Key");
	                Field field = new Field();
	                field.setName(columnName);
	                field.setNameHump(NameConverUtil.lineToHump(columnName));
	                field.setNameBigHump(NameConverUtil.lineToBigHump(columnName));
	                field.setType(type);	               
	                field.setJavaType(TypeMappingEnum.getJavaType(type));
	                field.setComment(comment);
	                field.setKeyType(keyType);
	                if (comment.contains("|")) {
	                    field.setNameCn(comment.substring(0, comment.indexOf("|")));
	                } else {
	                    field.setNameCn(comment);
	                }
	                field.setNullAble("YES".equals(nullAble));
	                if (type.toUpperCase().contains("varchar".toUpperCase())) {
	                    String lengthStr = type.substring(type.indexOf("(") + 1, type.length() - 1);
	                    field.setLength(Integer.valueOf(lengthStr));
	                } else {
	                    field.setLength(0);
	                }
	/*                if (comment.contains("枚举")) {
	                    field.setEnums(true);

	                    // 以课程等级为例：从注释中的“枚举[CourseLevelEnum]”，得到COURSE_LEVEL
	                    int start = comment.indexOf("[");
	                    int end = comment.indexOf("]");
	                    String enumsName = comment.substring(start + 1, end);
	                    String enumsConst = EnumGenerator.toUnderline(enumsName);
	                    field.setEnumsConst(enumsConst);
	                } else {
	                    field.setEnums(false);
	                }*/
	                fieldList.add(field);
	            }
	        }
	        rs.close();
	        stmt.close();
	        conn.close();
	        //System.out.println("列信息：" + fieldList);
	        return fieldList;
		 }catch(Exception e){
			 e.printStackTrace();
			 
		 }
		return null;
	    }
	    
    /**
     * 获取表主键的Java类型
     * @param tableName
     * @return
     */
    public static String getPrimaryTypeByTableName(String tableName){
    	String keyType = null;
		 try{
	        List<Field> fieldList = new ArrayList<>();
	        Connection conn = DataBaseUtil.getConnection();
	        Statement stmt = conn.createStatement();
	        ResultSet result = stmt.executeQuery("show full columns from `" + tableName + "`");
	        if (result != null) {
	            while(result.next()) {	             
	                String key = result.getString("Key");
	                if(key.equals("PRI")){
	                String type = result.getString("Type");
	                	keyType = TypeMappingEnum.getJavaType(type);
	                }
	            }
	        }
	       
	        result.close();
	        stmt.close();
	        conn.close();
		 }catch(Exception e){
			 e.printStackTrace();
			 
		 }
	    return keyType;

	    }
   
    @Test
    public void testGetKeyType(){
    	String typeName = getPrimaryTypeByTableName("article");
    	System.out.println(typeName);
    }

	    /**
	     * 获取所有的Java类型，使用Set去重
	     */
	    public static Set<String> getJavaTypes(List<Field> fieldList) {
	        Set<String> set = new HashSet<>();
	        for (int i = 0; i < fieldList.size(); i++) {
	            Field field = fieldList.get(i);
	            set.add(field.getJavaType());
	        }
	        return set;
	    }
    
    
}
