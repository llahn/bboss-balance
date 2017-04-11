package com.hpe.bboss.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "balance.server")
public class Servers {
	private String userService;
	private String adminService;

	public String getUserService() {
		return userService;
	}

	public void setUserService(String userService) {
		this.userService = userService;
	}

	public String getAdminService() {
		return adminService;
	}

	public void setAdminService(String adminService) {
		this.adminService = adminService;
	}

}
