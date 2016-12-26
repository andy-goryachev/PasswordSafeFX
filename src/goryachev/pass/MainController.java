// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.common.util.CMap;
import goryachev.crypto.OpaqueChars;
import goryachev.fx.CAction;
import goryachev.fx.FxThread;
import goryachev.pass.data2.DataFile2;
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
	protected DataFile2 data;
	
	
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
			private DataFile2 df;
			
			protected void process() throws Throwable
			{
				sleep(2000);
				df = new DataFile2();
				df.addEntry("ebay.com", new CMap<>());
			}
			
			protected void onProcessEnd() 
			{ 
				p.setProgress(false);
			}
			
			protected void processSuccess()
			{
				handleUnlock(df);
			}
			
			protected void processError(Throwable e)
			{
				super.processError(e);
				handleError(p, e);
			}
		}.start();
	}
	
	
	protected void handleUnlock(DataFile2 df)
	{
		win.showMainPane(df);
	}
	
	
	protected void handleError(LockPane p, Throwable e)
	{
		p.showError(e);
	}


	public void setDataFile(DataFile2 df)
	{
		data = df;
		
		win.showMainPane(df);
	}
}
