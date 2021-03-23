package com.smallchili.xmz.factory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Element;
import com.smallchili.xmz.enums.ProjectEnum;
import com.smallchili.xmz.util.BuildPath;
import com.smallchili.xmz.util.FileUtil;
import com.smallchili.xmz.util.NameConverUtil;
import com.smallchili.xmz.util.XmlUtil;

/**
 * @author xmz
 * @date 2020/09/29
 * 生成Maven工程工厂
 */
public class MavenProjectFactory implements TemplateFactory {

	// 工程根目录
	private String ROOT_PATH = BuildPath.buildDir(XmlUtil.getText(ProjectEnum.PROJECT_PATH),
			XmlUtil.getText(ProjectEnum.ARTIFACT_ID));
	// 源码目录
	private String SOURCE_CODE_PATH = BuildPath.buildDir(ROOT_PATH, "src", "main", "java");
	// 测试代码目录
	private String TEST_CODE_PATH = BuildPath.buildDir(ROOT_PATH, "src", "test", "java");
	// 资源目录
	private String RESOURCES_PATH = BuildPath.buildDir(ROOT_PATH, "src", "main", "resources");

	// 基础包路径
	private String BASE_PACKAGE = BuildPath.buildDir(SOURCE_CODE_PATH,
			BuildPath.converToDir(XmlUtil.getText(ProjectEnum.GROUP_ID)),
			BuildPath.converToDir(XmlUtil.getText(ProjectEnum.ARTIFACT_ID)));
	// 测试包路径
	private String TEST_BASE_PACKAGE = BuildPath.buildDir(TEST_CODE_PATH,
			BuildPath.converToDir(XmlUtil.getText(ProjectEnum.GROUP_ID)),
			BuildPath.converToDir(XmlUtil.getText(ProjectEnum.ARTIFACT_ID)));
	
	@Override
	public void create() {

		// 初始化maven目录
		initMavenDir();
		// 初始化工程基本包结构
		initPackage();
		// 创建启动类
		createApplication();
		// 创建application.yml文件
		createYmlFile();
        // 生成代码
		generatorCode();
        // 生成测试代码
		generatorTestCode();
		
	}

	private void generatorTestCode() {
		String testDirPath = BuildPath.buildDir(TEST_BASE_PACKAGE);
		TemplateFactory.build(TestTemplateFactory.class).create(testDirPath);
	}

	@Override
	public void create(String destPath) {
		this.ROOT_PATH = BuildPath.buildDir(destPath, XmlUtil.getText(ProjectEnum.ARTIFACT_ID));
		create();
	}
	
	private void generatorCode() {
		// 创建实体类	
		String entityDirPath = BuildPath.buildDir(BASE_PACKAGE, XmlUtil.getText(ProjectEnum.ENTITY_PACKAGE));
		TemplateFactory.build(EntityTemplateFactory.class).create(entityDirPath,"entity-lombok");

		// 创建dto类
		String dtoDirPath = BuildPath.buildDir(BASE_PACKAGE, XmlUtil.getText(ProjectEnum.DTO_PACKAGE_NAME));
		TemplateFactory.build(DtoTemplateFactory.class).create(dtoDirPath);

		// 创建dao	
		String daoDirPath = BuildPath.buildDir(BASE_PACKAGE, XmlUtil.getText(ProjectEnum.DAO_PACKAGE_NAME));
		TemplateFactory.build(DaoTemplateFactory.class).create(daoDirPath);
		
		// 创建util		
		String utilDirPath = BuildPath.buildDir(BASE_PACKAGE, XmlUtil.getText(ProjectEnum.UTIL_PACKAGE_NAME));
		TemplateFactory.build(UtilTemplateFactory.class).create(utilDirPath);

		// 创建vo
		String voDirPath = BuildPath.buildDir(BASE_PACKAGE, XmlUtil.getText(ProjectEnum.VO_PACKAGE_NAME));
		TemplateFactory.build(VoTemplateFactory.class).create(voDirPath);

		// 生成Service层代码
		String serviceDirPath = BuildPath.buildDir(BASE_PACKAGE + XmlUtil.getText(ProjectEnum.SERVICE_PACKAGE_NAME));
		TemplateFactory.build(ServiceTemplateFactory.class).create(serviceDirPath,"service-lombok");
		
		// 生成Controller层代码
		String controllerDirPath = BuildPath.buildDir(BASE_PACKAGE, XmlUtil.getText(ProjectEnum.CONTROLLER_PACKAGE_NAME));
		TemplateFactory.build(ControllerTemplateFactory.class).create(controllerDirPath);

	}

	/**
	 * 创建application.yml文件
	 */
	private void createYmlFile() {
		String ymlTemplatePath = BuildPath.buildDir(TEMPLATE_PATH , "config");
		String ymlTemplateName = "application";

		String destFullPath =  RESOURCES_PATH + File.separator + "application.yml";
		String driver = XmlUtil.getText("driver");
		String url = XmlUtil.getText("url");
		String username = XmlUtil.getText("username");
		String password = XmlUtil.getText("password");
		Map<String, Object> ymlParamMap = new HashMap<>();
		ymlParamMap.put("driver", driver);
		ymlParamMap.put("url", url);
		ymlParamMap.put("username", username);
		ymlParamMap.put("password", password);
		generateByTemplate(ymlTemplatePath, ymlTemplateName, destFullPath, ymlParamMap);

		log.info("生成application.yml配置文件");

	}

	/**
	 * 初始化maven目录 1、创建工程根目录 2、创建src/main/java源码目录 3、创建src/text/java测试目录
	 * 4、创建src/main/resource资源目录 5、创建pom.xml配置文件
	 */
	private void initMavenDir() {
		log.info("===开始初始化maven目录  begin===");

		// 创建根目录
		log.info("创建项目根目录[{}]", ROOT_PATH);
		FileUtil.mkdirs(ROOT_PATH);

		// 源代码目录
		log.info("创建项目源码目录[{}]", "src/main/java");
		FileUtil.mkdirs(SOURCE_CODE_PATH);

		// 测试代码目录
		log.info("创建项目测试代码目录[{}]", "src/test/java");
		FileUtil.mkdirs(TEST_CODE_PATH);

		// 资源目录
		log.info("创建项目资源目录[{}]", "src/main/resources");
		FileUtil.mkdirs(RESOURCES_PATH);

		// 生成pom.xml文件
		createPomXML();

		log.info("===maven基础目录创建完成  end===");
	}

	/**
	 * 初始化工程基本package
	 * 1、创建基础包，如smallchili.com.blog (src/main/java下)
	 * 2、创建测试基础包，如smallchili.com.blog (src/test/java下)
	 * 3、创建xml配置文件里配置的包名，如dao、service、controller等包
	 */
	private void initPackage() {
		log.info("===开始初始化包结构   begin===");
		log.info("创建基础包[{}]", String.format(XmlUtil.getText("groupId"), ".", XmlUtil.getText("artifactId")));
		// 1、创建基础包
		FileUtil.mkdirs(BASE_PACKAGE);

		// 2、创建测试包
		log.info("创建测试基础包[{}]", String.format(XmlUtil.getText("groupId"), ".", XmlUtil.getText("artifactId")));
		FileUtil.mkdirs(TEST_BASE_PACKAGE);

		// 3、创建xml里配置的包

		// 获取xml里的<package></package>标签节点
		Element packageNode = XmlUtil.getNode("package");
		if (packageNode == null) {
			log.warn("xml里没有配置<package><package/>标签");
			return;
		}
		List<Element> packageNodeList = packageNode.elements();
		if (null == packageNodeList || packageNodeList.isEmpty()) {
			log.warn("xml配置里没有配置包名");
			return;
		}

		// package标签节点下的子节点
		packageNodeList.forEach(node -> {
			String nodeName = node.getTextTrim();
			if (!nodeName.equals("")) {
				// [src/main/java]下生成包目录(dao、service、controller等)
				FileUtil.mkdirs(BASE_PACKAGE + nodeName);
				// [src/test/java]测试目录下生成对应包
				FileUtil.mkdirs(TEST_BASE_PACKAGE + nodeName);
				log.info("创建{}包", nodeName);
			}
		});

		log.info("===初始化包结构结束！ end===");
	}

	private void createPomXML() {
		String pomTemplatePath = BuildPath.buildDir(TEMPLATE_PATH, "config");
		String pomTemplateName = "pom";
        
		String destFullPath = ROOT_PATH + File.separator + "pom.xml";
		Map<String, Object> pomParamMap = new HashMap<>();
		String groupId = XmlUtil.getText("groupId");
		String artifactId = XmlUtil.getText("artifactId");
		pomParamMap.put("groupId", groupId);
		pomParamMap.put("artifactId", artifactId);
		generateByTemplate(pomTemplatePath, pomTemplateName, destFullPath, pomParamMap);

		log.info("pom.xml文件生成成功");
	}

	/**
	 * 创建启动类
	 */
	private void createApplication() {
		String appTemplatePath = TEMPLATE_PATH;
		String appTemplateName = "application";
        
		Map<String, Object> appParamMap = new HashMap<>();
		// TODO
		String packageName = XmlUtil.getText(ProjectEnum.GROUP_ID) + "." + XmlUtil.getText(ProjectEnum.ARTIFACT_ID);
		String className = NameConverUtil.lineToBigHump(XmlUtil.getText(ProjectEnum.ARTIFACT_ID));
		String destFullPath = BASE_PACKAGE + File.separator + className + "Application.java";
		appParamMap.put("packageName", packageName);
		appParamMap.put("className", className);
		generateByTemplate(appTemplatePath, appTemplateName, destFullPath, appParamMap);

		log.info("生成启动类 {}Application.java", className);
	}


	@Override
	public void create(String destPath, String templateName) {
		
	}


}
