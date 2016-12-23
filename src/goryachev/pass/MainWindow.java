// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.fx.CAction;
import goryachev.fx.CCheckMenuItem;
import goryachev.fx.CMenu;
import goryachev.fx.CMenuBar;
import goryachev.fx.CMenuItem;
import goryachev.fx.FX;
import goryachev.fx.FxDump;
import goryachev.fx.FxWindow;


/**
 * Main Window.
 */
public class MainWindow
	extends FxWindow
{
	public final CAction composeAction = new CAction(this::compose);
//	public final MainWindowController control;
	
	
	public MainWindow()
	{
		super("MainWindow");
		
		setTitle(PasswordSafeFxApp.TITLE);
		setMinSize(400, 300);
		setSize(1100, 800);

//		control = new MainWindowController();
		setTop(createMenuBar());
//		setCenter(control.split);
		
		FxDump.attach(this);
		
//		control.loadData();
	}
	
	
	private CMenuBar createMenuBar()
	{
		CMenuBar mb = new CMenuBar();
		CMenu m;
		// file
		m = mb.addMenu("File");
		m.add(new CMenuItem("Exit", FX.exitAction()));
		// edit
		m = mb.addMenu("Edit");
		// view
		m = mb.addMenu("View");
		m.add(new CCheckMenuItem("An Option", Options.showConversationThreads));
		// go
		m = mb.addMenu("Go");
		// message
		m = mb.addMenu("Message");
		m.add(new CMenuItem("Compose", composeAction));
		// tools
		m = mb.addMenu("Tools");
		// help
		m = mb.addMenu("Help");
		return mb;
	}
	
	
	public void compose()
	{
		// TODO
	}
}
