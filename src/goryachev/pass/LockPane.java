// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.crypto.OpaqueChars;
import goryachev.crypto.fx.SecretField;
import goryachev.fx.CAction;
import goryachev.fx.CButton;
import goryachev.fx.CButtonPane;
import goryachev.fx.CPane;
import goryachev.fx.CssStyle;
import goryachev.fx.FX;
import goryachev.fx.icon.ProcessingIcon;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * Lock Pane.
 */
public class LockPane
	extends CPane
{
	public final CAction browseAction = new CAction(this::browse);
	public final CAction newSafeAction = new CAction(this::newSafe);
	public final CAction unlockAction = new CAction(this::unlock);
	public final MainController control;
	public final TextField fileField;
	public final SecretField passwordField;
	public final Label progressField;
	public static final CssStyle PANE = new CssStyle("LockPane_PANE");
	
	
	public LockPane(MainController c, File f)
	{
		this.control = c;
		
		fileField = new TextField();
		
		passwordField = new SecretField();
		
		progressField = new Label();
		
		CButtonPane bp = new CButtonPane();
		bp.add(new CButton("On-screen Keyboard"));
		bp.add(new CButton("Create New Safe", newSafeAction));
		
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
			0.25,
			PREF,
			PREF,
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
		add(2, r, new CButton("Unlock", unlockAction));
		r++;
		add(2, r, 1, 2, progressField);
		r++;
		add(1, r, bp);
		
		// TODO "Create New Data File"
		// TODO "Help"
		// TODO keyboard
		// TODO progress icon
	}
	
	
	public void browse()
	{
		// TODO
	}
	
	
	public void unlock()
	{
		File f = null;
		OpaqueChars pw = null;
		
		control.unlockFile(this, f, pw);
	}
	
	
	protected void newSafe()
	{
		// TODO
	}


	public void setProgress(boolean on)
	{
		FX.setDisable
		(
			on,
			fileField,
			browseAction,
			passwordField,
			unlockAction,
			newSafeAction
		);

		if(on)
		{
			progressField.setGraphic(ProcessingIcon.create(30));
		}
		else
		{
			progressField.setGraphic(null);
		}
	}


	public void showError(Throwable e)
	{
		// TODO dialog?
	}
}
