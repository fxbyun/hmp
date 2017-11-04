package com.qiaobei.hmp.modules.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Formatter;
import java.util.Properties;

public class EnvironmentContextListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentContextListener.class);

    /**
     * 实例化一个新的环境
     */
    public EnvironmentContextListener() {
        super();
        logger.debug("[{}] initialized...", EnvironmentContextListener.class.getSimpleName());
    }


    private String collectEnvironmentInfo() {
        final Properties properties = System.getProperties();
        try (final Formatter formatter = new Formatter()) {
            formatter.format("\n******************** init(cmc) *******************\n");
            formatter.format("Java Home: %s\n", properties.get("java.home"));
            formatter.format("Java Vendor: %s\n", properties.get("java.vendor"));
            formatter.format("Java Version: %s\n", properties.get("java.version"));
            formatter.format("OS Architecture: %s\n", properties.get("os.arch"));
            formatter.format("OS Name: %s\n", properties.get("os.name"));
            formatter.format("OS Version: %s\n", properties.get("os.version"));
            formatter.format("*******************************************************\n");
            return formatter.toString();
        }
    }

    private String collectLogo() {
//
        try (final Formatter formatter = new Formatter()) {
            formatter.format("\n                              _.._        ,------------.\n");
            formatter.format("                           ,'      `.    ( just fuck it! )\n");
            formatter.format("                          /  __) __` \\    `-,----------'\n");
            formatter.format("                         (  (`-`(-')  ) _.-'\n");
            formatter.format("                         /)  \\  = /  (\n");
            formatter.format("                        /'    |--' .  \\\n");
            formatter.format("                       (  ,---|  `-.)__`\n");
            formatter.format("                        )(  `-.,--'   _`-.\n");
            formatter.format("                       '/,'          (  Uu\",\n");
            formatter.format("                        (_       ,    `/,-' )\n");
            formatter.format("                        `.__,  : `-'/  /`--'\n");
            formatter.format("                          |     `--'  |\n");
            formatter.format("                           \\        (\n");
            formatter.format("                           /\\ .      \\.  cih\n");
            formatter.format("                          / |` \\     ,-\\\n");
            formatter.format("                         /  \\| .)   /   \\\n");
            formatter.format("                        ( ,'|\\    ,'     :\n");
            formatter.format("                        | \\,`.`--\"/      }\n");
            formatter.format("                        `,'    \\  |,'    /\n");
            formatter.format("                       / \"-._   `-/      |\n");
            formatter.format("                       \"-.   \"-.,'|     ;\n");
            formatter.format("                      /        _/[\"---'\"\"]\n");
            formatter.format("                     :        /  |\"-     '\n");
            formatter.format("                     '           |      /\n");
            formatter.format("                                 `      |\n");
            return formatter.toString();
        }
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info(collectLogo());
        logger.info(collectEnvironmentInfo());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
