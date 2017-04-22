package jmx.client.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientMBeanService {
	@Autowired
	private MBeanServerConnection connection;
	
	/**
	 * 查询所有MBean
	 * @return
	 * @throws IOException
	 */
	public Set<ObjectName> findAll() throws IOException{
		int count = connection.getMBeanCount();
		System.out.println("暴露的MBean数量为："+count);
		Set<ObjectName> set = connection.queryNames(null, null);
		Iterator<ObjectName> it = set.iterator();
		while(it.hasNext()){
			ObjectName name = it.next();
			System.out.println(name.toString());
		}
		return set;
	}
	
	/**
	 * 操作MBean属性
	 * @throws IOException 
	 * @throws ReflectionException 
	 * @throws MBeanException 
	 * @throws MalformedObjectNameException 
	 * @throws InstanceNotFoundException 
	 * @throws AttributeNotFoundException 
	 */
	public void operateAttribute() throws AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException{
		//获取属性值
		String cronExpression = (String) connection.getAttribute(new ObjectName("jmx:name=JmxController"), "User");
		System.out.println("获取属性值："+cronExpression);
		//修改属性值
//		connection.setAttribute(new ObjectName("jmx:name=JmxController"), new Attribute("user"));
	}
}
