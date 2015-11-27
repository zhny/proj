package cn.zhny.base.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.avaje.agentloader.AgentLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EbeanAgentListener implements ServletContextListener  {
	public static final Logger log = LoggerFactory.getLogger(EbeanAgentListener.class);
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		if (!AgentLoader.loadAgentFromClasspath("avaje-ebeanorm-agent","debug=1;packages=cn.zhny.**")) {
			log.info("avaje-ebeanorm-agent not found in classpath - not dynamically loaded");
		  }
	}

}
