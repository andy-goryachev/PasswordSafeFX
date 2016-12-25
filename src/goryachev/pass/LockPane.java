// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.crypto.fx.SecretField;
import goryachev.fx.CAction;
import goryachev.fx.CButton;
import goryachev.fx.CPane;
import goryachev.fx.CssStyle;
import goryachev.fx.FX;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;


/**
 * Lock Pane.
 */
public class LockPane
	extends CPane
{
	public final CAction browseAction = new CAction(this::browse);
	public final CAction openAction = new CAction(this::open);
	public final TextField fileField;
	public final SecretField passwordField;
	public static final CssStyle PANE = new CssStyle("LockPane_PANE");
	
	
	public LockPane(File f)
	{
		fileField = new TextField();
		
		passwordField = new SecretField();
		
		// layout
		
		setGaps(10, 10);
		setPadding(50);
		
		addColumns
		(
			PREF,
			FILL,
			PREF
		);
		addRows
		(
			FILL,
			PREF,
			PREF,
			PREF,
			FILL
		);
		
		int r = 1;
		add(0, r, FX.label("File:", Pos.CENTER_RIGHT));
		add(1, r, fileField);
		add(2, r, new CButton("Browse", browseAction));
		r++;
		add(0, r, FX.label("Passphrase:", Pos.CENTER_RIGHT));
		add(1, r, 2, 1, passwordField);
		r++;
		add(2, r, new CButton("Open", openAction));
		// TODO "Create New Data File"
		// TODO "Help"
		// TODO keyboard
		// TODO progress icon
	}
	
	
	public void browse()
	{
		// TODO
	}
	
	
	public void open()
	{
		// TODO
	}
}
