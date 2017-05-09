package com.logger.sink;

import java.util.List;
import java.util.Properties;

import com.logger.MessageLevel;

public class ConsoleSink extends AbstractSink {

	public ConsoleSink(Properties properties) {
		super(properties);
	}

	@Override
	public void log(String nameSpace, String message, MessageLevel messageLevel) {
		System.out.println(getCurrentTimeStamp()+ " " + messageLevel.name() + " ["+nameSpace+"]"+ message);
	}

	@Override
	public List<MessageLevel> getSupportedMessageLevels() {
		return messageLevels;
	}

}
