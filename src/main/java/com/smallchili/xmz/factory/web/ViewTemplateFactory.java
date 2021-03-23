package com.smallchili.xmz.factory.web;

import com.smallchili.xmz.model.Field;
import com.smallchili.xmz.util.DataBaseUtil;
import com.smallchili.xmz.util.NameConverUtil;
import com.smallchili.xmz.util.XmlUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xmz
 * @date: 2020/10/11
 */
public class ViewTemplateFactory extends WebTemplateFactory{

    //默认模板名称
    private final String DEFAULT_TEMPLATE_NAME = "page";

    @Override
    public void create() {

    }

    @Override
    public void create(String destPath) {
        create(destPath, DEFAULT_TEMPLATE_NAME);
    }

    @Override
    public void create(String destPath, String templateName) {
        log.info("开始生成vue文件");
        // 检查目录，若不存在则创建
        checkAndCreateDir(destPath);
        // 获取<表名,实体对象名> Map
        Map<String, String> tableNameMap = XmlUtil.getTableNameMap();
        tableNameMap.forEach((tableName, objectName) -> {
            String fullPath = destPath + File.separator + objectName + ".vue";
            List<Field> fieldList = DataBaseUtil.getColumnByTableName(tableName);
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("Domain", objectName);
            paramMap.put("domain", NameConverUtil.bigHumpToHump(objectName));
            paramMap.put("fieldList", fieldList);

            generateByTemplate(VIEW_TEMPLATE_PATH,templateName,fullPath,paramMap);
            log.info("已创建 [{}.vue]",objectName);
        });

        log.info("开始生成vue文件");
    }

    @Test
    public void test(){
        create("C:\\Users\\asus\\Desktop\\mooc\\generatory\\web\\FileManage","role");
    }


}
