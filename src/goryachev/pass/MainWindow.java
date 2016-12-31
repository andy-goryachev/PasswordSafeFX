// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.fx.FX;
import goryachev.fx.FxDump;
import goryachev.fx.FxWindow;
import goryachev.pass.data2.DataFile2;
import java.io.File;


/**
 * Main Window.
 */
public class MainWindow
	extends FxWindow
{
	public final MainController control = new MainController(this);
	private File file; // TODO property use OpenFileLogic
	
	
	public MainWindow(File f)
	{
		super("MainWindow");
		this.file = f;
		
		setTitle(PasswordSafeFxApp.TITLE + " " + Version.VERSION);
		setMinSize(400, 300);
		setSize(1100, 800);

		FxDump.attach(this);
		
		setFile(f);
	}
	
	
	public void setFile(File f)
	{
		if(f == null)
		{
			control.setDataFile(DataFile2.createEmptyFile());
		}
		else
		{
			showLockPane();
		}
	}
	
	
	public void showLockPane()
	{
		FX.storeSettings(this);
		
		LockPane p = new LockPane(control, file);
		setCenter(p);
	}
	
	
	public void showMainPane(DataFile2 df)
	{
		MainPane p = new MainPane(df.getEntries());

		setTop(new ToolBar(control));
		setCenter(p);
		setBottom(new StatusBar());
		
		FX.restoreSettings(this);
	}
}
