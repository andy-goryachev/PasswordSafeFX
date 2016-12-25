// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.fx.GlobalBooleanProperty;
import goryachev.fx.GlobalStringProperty;


/**
 * Options.
 */
public class Options
{
	/** password file */
	public static final GlobalStringProperty dataFile = new GlobalStringProperty("data.file");
	
	// FIX
	public static final GlobalBooleanProperty showConversationThreads = new GlobalBooleanProperty("show.conversation.threads", true);
}
