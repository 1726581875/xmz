package com.smallchili.xmz.util;

import com.smallchili.xmz.enums.ProjectEnum;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xmz
 * @date: 2020/10/11
 */
public class NameMap {
    //<节点名,包名>映射
    private static Map<String,String> namePkMap = new HashMap<>();
    //<包名,目录名>映射
    private static Map<String,String> pkDirMap = new HashMap<>();

    private static Logger log = LoggerFactory.getLogger(NameMap.class);

    static{
        // 获取基础包名
        String groupId = XmlUtil.getText("groupId");
        String artifactId = XmlUtil.getText("artifactId");
        String basePackageName = groupId + "." + artifactId;
        namePkMap.put("basePackageName",basePackageName);

        //获取<package>节点对象
        Element packageElement = XmlUtil.getNode("package");
        //获取<package><package>节点标签下的所有元素
        List<Element> packageElementList = packageElement.elements();
        //遍历，获取<节点名称，值> 放入namePkMap
        packageElementList.forEach(e -> namePkMap.put(e.getName(),basePackageName + "." + e.getTextTrim()));

        //遍历namePkMap，构造pkDirMap
        namePkMap.forEach((k,v) -> pkDirMap.put(v, BuildPath.converToDir(v)));

    }

    public static String getDir(String nodeName){
        checkNodeName(nodeName);
        return pkDirMap.get(namePkMap.get(nodeName));
    }

    public static String getDir(ProjectEnum pjEnum){
        String nodeName = pjEnum.getElementName();
        checkNodeName(nodeName);
        return pkDirMap.get(namePkMap.get(nodeName));
    }

    public static String getPk(String nodeName){
        checkNodeName(nodeName);
        return namePkMap.get(nodeName);
    }

    public static String getPk(ProjectEnum pjEnum){
        String nodeName = pjEnum.getElementName();
        checkNodeName(nodeName);
        return namePkMap.get(nodeName);
    }

    private static void checkNodeName(String nodeName) {
        if (!namePkMap.containsKey(nodeName)) {
            log.error("该节点名不存在,请检查xml配置");
            throw new IllegalArgumentException("namePkMap里没有名字为" + nodeName + "的key");
        }
    }



    public static void main(String[] args) {
        namePkMap.forEach((k,v)->{
            System.out.println(k + " : " + v);
        });
        System.out.println("=====================");
        pkDirMap.forEach((k,v)->{
            System.out.println(k + " : " + v);
        });

        System.out.println("=====================");

        System.out.println(getPk(ProjectEnum.CONTROLLER_PACKAGE_NAME));
        System.out.println(getDir(ProjectEnum.CONTROLLER_PACKAGE_NAME));

    }

}
