package com.logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.logger.sink.ISink;
import com.logger.sink.SinkFactory;

public class CustomLogger {
	
	private static CustomLogger logger = null;
	
	private Map<MessageLevel, ISink> messageLevelToSinkMap = new HashMap<MessageLevel, ISink>();
	
	private CustomLogger() {
		initLogger();
	}
	
	private void initLogger() {
		String confFilesLocation = System.getenv("LOGGER_CONF");
		if ( confFilesLocation == null)
		{
			String path = System.getProperty("user.dir") ;
			confFilesLocation = path + "\\src\\conf";
		}
		
		
		File folder = new File(confFilesLocation);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.getName().endsWith(".properties"))
			{
				Properties properties = new Properties();
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
					properties.load(fis);
					ISink sink = SinkFactory.getSink(properties);
					List<MessageLevel> supportedMessageLevels = sink.getSupportedMessageLevels();
					for (MessageLevel messageLevel : supportedMessageLevels) {
						messageLevelToSinkMap.put(messageLevel, sink);
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if ( fis != null)
						{
							fis.close();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
	}
	
	public void log(MessageLevel MessageLevel, String message, String nameSpace)
	{
		ISink iSink = messageLevelToSinkMap.get(MessageLevel);
		iSink.log(nameSpace, message, MessageLevel);
	}

	public static CustomLogger getLogger() {
		if (logger == null) {
			synchronized (CustomLogger.class) {
				if (logger == null) {
					logger = new CustomLogger();
				}
			}
		}
		return logger;
	}

}
