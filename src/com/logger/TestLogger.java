package com.logger;

public class TestLogger {
	
	public static void main(String[] args) {
		CustomLogger logger = CustomLogger.getLogger();
		
		logger.log(MessageLevel.ERROR, "Sample Console Message", "Console");
		logger.log(MessageLevel.INFO, "Sample File Message", "File");
	}

}
