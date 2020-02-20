package com.example;

import javax.servlet.http.HttpServletRequest;

public interface Command {
	void execute(HttpServletRequest request);
	
}
