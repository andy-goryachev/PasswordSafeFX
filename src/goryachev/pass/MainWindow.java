// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.fx.FX;
import goryachev.fx.FxDump;
import goryachev.fx.FxWindow;
import goryachev.fx.HPane;
import java.io.File;
import javafx.scene.Node;


/**
 * Main Window.
 */
public class MainWindow
	extends FxWindow
{
	public final MainController control = new MainController(this);
	private File file;
	
	
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
			showMainPane();
		}
		else
		{
			showLockPane();
		}
	}
	
	
	protected void showLockPane()
	{
		LockPane p = new LockPane(control, file);
		setCenter(p);
	}
	
	
	protected void showMainPane()
	{
		MainPane p = new MainPane();

		setTop(new ToolBar(control));
		setCenter(p);
		setBottom(new StatusBar());
	}
}
