package cn.blmdz.test;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EmbededTomcatBootstrap {
    
    private final static Logger log = LogManager.getLogger(EmbededTomcatBootstrap.class);
	
	public static void main(String[] args) throws Exception {
	    Long start = System.currentTimeMillis();
        
        String path = new File("").getAbsolutePath();
        String name = path.substring(path.lastIndexOf("\\") + 1);
        String prefix = "/";// + name;
        String webappPath = path + "/" + "src/main/webapp/";
        String classesPath = path + "/" + "target/classes";
        String tomcatPath = "/home/tomcat/" + name;
        Integer port = 8081;
        log.info("----------------  Tomcat Project  ----------------");
        log.info("Tomcat path: {}", tomcatPath);
        log.info("Tomcat port: {}", port);
        
        log.info("Project name: {}", name);
        log.info("Project path: {}", path);
        log.info("Project prefix: {}", prefix);
        log.info("Project webappPath: {}", webappPath);
        log.info("Project classesPath: {}", classesPath);
        log.info("----------------  Project Project  ----------------");
        
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setBaseDir(tomcatPath);
        tomcat.getHost().setAutoDeploy(true);
        
        
        StandardContext ctx = (StandardContext) tomcat.addWebapp(prefix, webappPath);

        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", classesPath, "/"));
        ctx.setResources(resources);
        ctx.setPath(prefix);
        ctx.addLifecycleListener(new Tomcat.FixContextListener());
        ctx.setName("news-web");

        tomcat.start();
        log.info("Tomcat start successfully. time consuming {} ms. Please reqeust {}", System.currentTimeMillis() - start, "http://localhost:" + port + prefix);
        tomcat.getServer().await();
    }
}