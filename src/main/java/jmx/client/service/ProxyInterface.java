package jmx.client.service;

import jmx.server.entity.User;

public interface ProxyInterface {
	void setUser(User user);
	User getUser();
}
