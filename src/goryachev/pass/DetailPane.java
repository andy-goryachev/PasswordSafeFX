// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.crypto.fx.SecretField;
import goryachev.fx.CAction;
import goryachev.fx.CButton;
import goryachev.fx.CPane;
import goryachev.fx.CssStyle;
import goryachev.fx.FX;
import goryachev.fx.FxCtl;
import goryachev.pass.data2.DataEntry;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


/**
 * Detail Pane.
 */
public class DetailPane
	extends CPane
{
	public static final CssStyle NOTES = new CssStyle("DetailPane_NOTES");
	public static final CssStyle PANE = new CssStyle("DetailPane_PANE");
	public static final CssStyle TITLE = new CssStyle("DetailPane_TITLE");

	public final CAction copyPasswordAction = new CAction(this::copyPassword);
	public final CAction copyUsernameAction = new CAction(this::copyUsername);
	public final Label titleField;
	public final TextField usernameField;
	public final SecretField passwordField;
	public final TextArea notesField;
	
	
	public DetailPane(DataEntry en)
	{
		FX.style(this, PANE);
		setPadding(10);
		
		titleField = FX.label(FxCtl.BOLD, TITLE);
		titleField.textProperty().bind(en.title);
		
		usernameField = new TextField();
		usernameField.textProperty().bind(en.userName);
		
		passwordField = new SecretField();
		// TODO
		//passwordField.opaqueTextProperty().bind(en.passwordProperty());
		
		// TODO non-editable
		notesField = new TextArea();
		notesField.textProperty().bind(en.notes);
		notesField.setEditable(false);
		FX.style(notesField, NOTES);
		
		// layout
		
		setGaps(10);
		
		addColumns
		(
			PREF,
			FILL,
			PREF
		);
		addRows
		(
			PREF,
			PREF,
			PREF,
			FILL
		);
		
		int r = 0;
		add(0, r, 2, 1, titleField);
		r++;
		add(0, r, FX.label("Username:", Pos.CENTER_RIGHT));
		add(1, r, usernameField);
		add(2, r, new CButton("Copy Username", copyUsernameAction));
		r++;
		add(0, r, FX.label("Password:", Pos.CENTER_RIGHT));
		add(1, r, passwordField);
		add(2, r, new CButton("Copy Password", copyPasswordAction));
		r++;
		add(0, r, FX.label("Notes:", Pos.TOP_RIGHT));
		add(1, r, 2, 2, notesField);
	}
	
	
	protected void copyPassword()
	{
		// TODO
	}
	
	
	protected void copyUsername()
	{
		// TODO
	}
}
