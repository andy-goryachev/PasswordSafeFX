// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.fx.CommonStyles;
import goryachev.fx.FxStyleSheet;
import javafx.scene.paint.Color;


/**
 * Application style sheet.
 */
public class Styles
	extends FxStyleSheet
{
	public Styles()
	{
		// TODO themes
		add
		(
			// basic styles
			new Selector(".root").defines
			(
				prop("-fx-accent", Color.RED),
				prop("-fx-focus-color", Color.RED),
				prop("-fx-faint-focus-color", Color.RED)
			),
			
			new Selector(".text").defines
			(
				prop("-fx-font-smoothing-type", "gray")
			),
			
			// common fx styles
			new CommonStyles()
		);
	}
}
