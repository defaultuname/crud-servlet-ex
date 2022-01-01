import ch.qos.logback.classic.Logger;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.loader.WebappLoader;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
    Main-класс. Отвечает за запуск embedded контейнера сервлетов (томката)
    Запуск происходит локально, на порту 8080.
    Порт можно изменить в config.properties
*/

public class Main {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Main.class);
    private static final String pathToConfig = "./config.properties";

    public static void main(String[] args) throws LifecycleException, IOException {
        String webappDirLocation = "src/main/webapp/";
        Properties prop = new Properties();
        Tomcat tomcat = new Tomcat();

        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(pathToConfig);
        prop.load(inputStream);

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = prop.getProperty("tomcat.port");
        }
        tomcat.setPort(Integer.parseInt(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
        logger.info("Configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());
        WebappLoader webappLoader = new WebappLoader(Thread.currentThread().getContextClassLoader());
        ctx.setLoader(webappLoader);

        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }
}