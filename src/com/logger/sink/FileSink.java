package com.logger.sink;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.logger.MessageLevel;

public class FileSink extends AbstractSink {
	File logFile = null;
	FileWriter fw = null;
	
	public FileSink(Properties properties) {
		super(properties);
		String fileLocation = properties.getProperty("file_location");
		logFile = new File(fileLocation);
		if ( !logFile.exists())
		{
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<MessageLevel> getSupportedMessageLevels() {
		return messageLevels;
	}

	@Override
	public void log(String nameSpace, String message, MessageLevel messageLevel) {
		try {
			fw  = new FileWriter(logFile);
			fw.write(getCurrentTimeStamp()+ " " + messageLevel.name() + " ["+nameSpace+"]"+ message);
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if ( fw!= null)
				{
				fw.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
