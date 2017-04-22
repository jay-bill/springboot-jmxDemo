# springboot-jmxDemo
一、使用springboot整合mybatis：
  需要引入
  <dependency>
	    <groupId>org.mybatis.spring.boot</groupId>
	    <artifactId>mybatis-spring-boot-starter</artifactId>
	    <version>1.1.1</version>
	</dependency>
   <dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
	</dependency>
  （在使用springboot时，注意main方法所在的包必须为其他包的父包。）

二、练习使用了jmx（java管理扩展）
  目的是可以在远程查看或修改MBean的状态。
  1、在springboot中，只需要在需要暴露成MBean的类上加：@ManagedResource("mbean名")，
  然后在属性的getter/setter方法上加上@ManagedAttribute注解，即可暴露为MBean。
  在本demo中，把JmxController暴露成MBean。
  2、在远程使用RMI协议访问MBean，在此demo中主要是jmx.client包下实现。
