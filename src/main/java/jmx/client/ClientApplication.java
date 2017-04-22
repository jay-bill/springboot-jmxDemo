package jmx.client;
import java.net.MalformedURLException;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.jmx.access.MBeanProxyFactoryBean;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import jmx.client.service.ProxyInterface;

@SpringBootApplication
public class ClientApplication implements EmbeddedServletContainerCustomizer{
	
	public static void main(String [] args){
		SpringApplication.run(ClientApplication.class, args);
	}

	/**
	 * 修改端口号
	 */
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(8081);
	}
	
	//访问RMI服务端,记得基于java配置的bean名和方法名一致。
	@Bean
	public MBeanServerConnectionFactoryBean connection() throws MalformedURLException{
		MBeanServerConnectionFactoryBean mbscfb = new 
				MBeanServerConnectionFactoryBean();
		mbscfb.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/jmx");
		return  mbscfb;
	}
	
	/**
	 * 代理bean，可以在本地对远程的Bean像本地bean一样操作
	 * @param connection
	 * @return
	 * @throws MalformedObjectNameException
	 */
	@Bean
	public MBeanProxyFactoryBean proxyBean(MBeanServerConnectionFactoryBean connection) throws MalformedObjectNameException{
		MBeanProxyFactoryBean proxy = new MBeanProxyFactoryBean();
		proxy.setObjectName("jmx:name=JmxController");
		proxy.setServer((MBeanServerConnection)connection);
		proxy.setProxyInterface(ProxyInterface.class);
		return proxy;
	}
}
