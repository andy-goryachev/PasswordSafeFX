// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.common.util.Copyright;
import goryachev.fx.CssStyle;
import goryachev.fx.FX;
import goryachev.fx.HPane;
import javafx.geometry.Pos;


/**
 * Status Bar.
 */
public class StatusBar
	extends HPane
{
	public static final CssStyle PANE = new CssStyle("StatusBar_PANE");
	
	
	public StatusBar()
	{
		fill();
		add(FX.label(Copyright.COPYRIGHT, Pos.CENTER_RIGHT));
	}
}
