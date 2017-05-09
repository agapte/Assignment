package com.logger.sink;

import java.util.List;

import com.logger.MessageLevel;

public interface ISink {
	
	public void log ( String nameSpace, String message, MessageLevel messageLavel);
	
	public List<MessageLevel> getSupportedMessageLevels();

}
