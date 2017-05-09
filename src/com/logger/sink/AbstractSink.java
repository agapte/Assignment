package com.logger.sink;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import com.logger.MessageLevel;

/**
 * This is abstract class for Sink. All common code for Sink goes here
 * @author apte
 *
 */
public abstract class AbstractSink implements ISink {
	
	/**
	 * List of message levels supported by Sink 
	 */
	protected List<MessageLevel> messageLevels = new ArrayList<MessageLevel>();
	
	/**
	 * Time Stamp format
	 */
	protected String timeStampFormat = null;

	/**
	 * Constructor
	 * @param properties
	 */
	public AbstractSink(Properties properties) {
		String log_level = properties.getProperty("log_level");
		String[] messageLevelStrings = log_level.split(",");
		for (String messageLevelString : messageLevelStrings) {
			MessageLevel messageLevel = MessageLevel.valueOf(messageLevelString);
			if( messageLevel != null)
			{
				messageLevels.add(messageLevel);
			}
		}
		
		timeStampFormat = properties.getProperty("ts_format");
	}
	
	/**
	 * Return current time stamp in Time Stamp format defined by timeStampFormat
	 * @return
	 */
	protected String getCurrentTimeStamp(){
		String timeStamp = new SimpleDateFormat(timeStampFormat).format(Calendar.getInstance().getTime());
		return timeStamp;
	}
}
