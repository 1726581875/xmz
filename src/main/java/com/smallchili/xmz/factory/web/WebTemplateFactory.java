package com.smallchili.xmz.factory.web;

import com.smallchili.xmz.factory.TemplateFactory;
import com.smallchili.xmz.util.BuildPath;


/**
 * @author xmz
 * @date: 2020/10/11
 */
public abstract class WebTemplateFactory implements TemplateFactory {

    // 前端模板的根路径
    protected final String WEB_TEMPLATE_PATH =  BuildPath.buildDir(TEMPLATE_PATH,"web");
    // view模板位置
    protected final String VIEW_TEMPLATE_PATH = BuildPath.buildDir(WEB_TEMPLATE_PATH,"views");


}
