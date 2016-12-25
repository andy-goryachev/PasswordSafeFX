// Copyright © 2006-2016 Andy Goryachev <andy@goryachev.com>
package goryachev.fx;
import goryachev.common.util.CKit;
import goryachev.common.util.CancellableThread;
import goryachev.common.util.Log;
import goryachev.common.util.Progress;
import javafx.application.Platform;


/**
 * A cancellable thread that runs some computation in background, 
 * then updates the UI in an FX thread.
 */
public abstract class FxThread
	extends CancellableThread
{
	/** executed in a background thread */
	protected abstract void process() throws Throwable;
	
	/** Invoked in an FX thread when the background process ends successfully or with an exception. */
	protected void onProcessEnd() { }
	
	/** executed in an FX thread when process() returns normally */
	protected abstract void processSuccess();
	
	/** 
	 * executed in an FX thread when process() throws an Throwable.
	 * The default implementation simply logs the exception. 
	 */ 
	protected void processError(Throwable e) { Log.ex(e); }
	
	/** overwrite to enable time estimate and progress report */
	public Progress getProgress() { return null; }
	
	
	//
	
	
	private long startTime;
	
	
	public FxThread(String name, int priority)
	{
		setName(name);
		setPriority(priority);
	}
	

	public FxThread(String name)
	{
		// truly background thread
		this(name, NORM_PRIORITY - 3);
	}
	
	
	public final void run()
	{
		try
		{
			synchronized(this)
			{
				startTime = System.currentTimeMillis();
			}
			
			// force a context switch
			CKit.sleep(10);

			// background thread body
			process();
			
			// update ui with the result
			Platform.runLater(() -> 
			{
				try
				{
					onProcessEnd();
				}
				catch(Throwable e)
				{
					Log.ex(e);
				}
				
				processSuccess();	
			});
		}
		catch(Throwable err)
		{
			Platform.runLater(() -> 
			{
				try
				{
					onProcessEnd();
				}
				catch(Throwable e)
				{
					Log.ex(e);
				}
				
				processError(err);
			});
		}
	}
	
	
	public synchronized long getStartTime()
	{
		return startTime;
	}
	
	
	/** sleeps, if necessary, to insure minimum delay from start */
	public void comfortSleep(int minimumTimeMilliseconds)
	{
		CKit.comfortSleep(startTime, minimumTimeMilliseconds);
	}
	
	
	/** sleeps, if necessary, to insure minimum delay from start */
	public void comfortSleep()
	{
		comfortSleep(400);
	}
}
