// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.fx.CAction;
import goryachev.fx.CMenu;
import goryachev.fx.CMenuBar;
import goryachev.fx.CMenuItem;
import goryachev.fx.FX;
import goryachev.fx.FxDump;
import goryachev.fx.FxWindow;
import java.io.File;


/**
 * Main Window.
 */
public class MainWindow
	extends FxWindow
{
	public final CAction lockAction = new CAction(this::lock);
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
	
	
	private CMenuBar createMenuBar()
	{
		CMenuBar mb = new CMenuBar();
		CMenu m;
		// file
		m = mb.addMenu("File");
		m.add("New Database");
		m.add("Change Database Passphrase");
		m.add("Lock", lockAction);
		m.separator();
		m.add("Open");
		m.add("Save");
		m.add("Save As...");
		m.separator();
		m.add("Preferences");
		m.separator();
		m.add(new CMenuItem("Exit", FX.exitAction()));
		// edit
		m = mb.addMenu("Edit");
		m.add("Undo");
		m.add("Redo");
		m.separator();
		m.add("Copy Username to Clipboard");
		m.add("Copy Password to Clipboard");
		m.add("Clear Clipboard");
		m.add("Delete Entry"); // TODO wrong place
		// view
//		m = mb.addMenu("View");
//		m.add(new CCheckMenuItem("An Option", Options.showConversationThreads));
		// help
		m = mb.addMenu("Help");
		m.add("Contact Customer Support");
		m.add("Check for Updates");
		m.separator();
		m.add("Mandatory XKCD Reference");
		m.separator();
		m.add("License");
		m.add("Open Source Licenses");
		m.separator();
		m.add("About");
		return mb;
	}
	
	
	protected void showLockPane()
	{
		LockPane p = new LockPane(file);
		setCenter(p);
	}
	
	
	protected void showMainPane()
	{
		MainPane p = new MainPane();

		setTop(createMenuBar());
		setCenter(p);
		setBottom(new StatusBar());
	}
	
	
	public void lock()
	{
		// TODO destroy in-memory data
		setTop(null);
		showLockPane();
		setBottom(null);
	}
}
