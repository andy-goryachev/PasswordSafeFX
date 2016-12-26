// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.fx.CPane;
import goryachev.fx.FX;
import goryachev.fx.FxCtl;
import goryachev.pass.data2.IDataEntry;
import javafx.scene.control.Label;


/**
 * Detail Pane.
 */
public class DetailPane
	extends CPane
{
	public final Label nameField;
	
	
	public DetailPane(IDataEntry en)
	{
		nameField = FX.label(FxCtl.BOLD);
		nameField.textProperty().bind(en.nameProperty());
		
		addColumns
		(
			PREF,
			FILL
		);
		addRows
		(
			PREF,
			PREF,
			PREF,
			FILL
		);
		
		add(0, 0, 2, 1, nameField);
	}
}
