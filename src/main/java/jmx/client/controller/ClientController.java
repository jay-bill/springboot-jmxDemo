package jmx.client.controller;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ReflectionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jmx.client.service.ClientMBeanService;

@Controller
@RequestMapping("/")
public class ClientController {
	@Autowired
	private ClientMBeanService service;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public void findAll() throws IOException, AttributeNotFoundException, InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException{
		service.findAll();
		service.operateAttribute();
	}
}
