package jmx.server.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import jmx.server.entity.User;
import jmx.server.mapper.UserMapper;

@ManagedResource(objectName="jmx:name=JmxController")//将控制器暴露为MBean
@Controller  
@RequestMapping("/")
public class JmxController {
	
	@Autowired
	private UserMapper userMapper;
	
	private User user;
	@ManagedAttribute//暴露成MBean的属性
	public void setUser(User user){
		this.user = user;
	}
	@ManagedAttribute//暴露成MBean的属性
	public User getUser(){
		return user;
	}
	
	//暴露为远程MBean
	@Bean
	public ConnectorServerFactoryBean connectorServerFactoryBean(){
		ConnectorServerFactoryBean csfb = new ConnectorServerFactoryBean();
		csfb.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/jmx");
		return csfb;
	}
	//注册RMI协议运行时注册表
	@Bean
	public RmiRegistryFactoryBean rmiRegistryFB(){
		RmiRegistryFactoryBean rrfb = new RmiRegistryFactoryBean();
		rrfb.setPort(1099);
		return rrfb;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String insert(int id,String name,int age){
		userMapper.insert(id,name,age);
		return "redirect:/";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String findAll(Map<String,Object> map){		
		List<User> userList = userMapper.findAll();
		map.put("users", userList);
		return "index";
	}
}
