package jmx.client.service;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.access.MBeanProxyFactoryBean;
import org.springframework.stereotype.Service;

/**
 * 代理MBean
 * @author jaybill
 *
 */
@Service
public class ClientMBeanProxyService {
	@Autowired
	private MBeanProxyFactoryBean proxyBean;
	
	public void proxyAttribute(){
	}
}
