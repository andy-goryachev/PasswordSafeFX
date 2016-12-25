// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.crypto.OpaqueChars;
import goryachev.fx.CAction;
import goryachev.fx.FxThread;
import java.io.File;
import javafx.scene.control.TextField;


/**
 * Main Controller.
 */
public class MainController
{
	public final CAction lockAction = new CAction(this::lock);
	public final MainWindow win;
	public final TextField searchField;
	
	
	public MainController(MainWindow w)
	{
		this.win = w;
		
		this.searchField = new TextField();
	}
	
	
	public void lock()
	{
		// TODO destroy in-memory data
		win.setTop(null);
		win.showLockPane();
		win.setBottom(null);
	}


	public void unlockFile(LockPane p, File f, OpaqueChars pw)
	{
		p.setProgress(true);

		new FxThread("unlock")
		{
			protected void process() throws Throwable
			{
				sleep(1000);
			}
			
			protected void onProcessEnd() 
			{ 
				p.setProgress(false);
			}
			
			protected void processSuccess()
			{
				handleUnlock();
			}
			
			protected void processError(Throwable e)
			{
				super.processError(e);
				handleError(p, e);
			}
		}.start();
	}
	
	
	protected void handleUnlock()
	{
		win.showMainPane();
	}
	
	
	protected void handleError(LockPane p, Throwable e)
	{
		p.showError(e);
	}
}
