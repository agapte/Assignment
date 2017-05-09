package com.logger.sink;

import java.util.Properties;

public class SinkFactory {
	
	/**
	 * Create a Sink object from configuration file properties and return.
	 * @param sinkProperties
	 * @return
	 */
	public static ISink getSink(Properties sinkProperties)
	{
		String sinkType = sinkProperties.getProperty("sink_type");
		if ( sinkType.equals("CONSOLE"))
		{
			return new ConsoleSink(sinkProperties);
		} else if ( sinkType.equals("FILE"))
		{
			return new FileSink(sinkProperties);
		}
		return null;
	}

}
