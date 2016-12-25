// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.fx.CButton;
import goryachev.fx.CMenu;
import goryachev.fx.CMenuBar;
import goryachev.fx.CMenuItem;
import goryachev.fx.FX;
import goryachev.fx.HPane;
import goryachev.fx.VPane;
import javafx.scene.Node;


/**
 * Tool Bar.
 */
public class ToolBar
	extends VPane
{
	public ToolBar(MainController c)
	{
		add(createMenu(c));
		add(createButtons(c));
	}
	
	
	protected Node createButtons(MainController c)
	{
		HPane p = new HPane(10);
		p.add(FX.label("Find:"));
		p.add(c.searchField);
		p.fill();
		p.add(new CButton("Add"));
		p.add(new CButton("Save"));
		p.add(new CButton("Lock", c.lockAction));
		return p;
	}
	

	protected Node createMenu(MainController c)
	{
		CMenuBar mb = new CMenuBar();
		CMenu m;
		// file
		m = mb.addMenu("File");
		m.add("New Database");
		m.add("Change Database Passphrase");
		m.add("Lock", c.lockAction);
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
}
