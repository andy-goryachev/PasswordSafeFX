// Copyright © 2016-2017 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.fx.CommonStyles;
import goryachev.fx.FX;
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
		Color textBG = Color.WHITE;
		Color accent = FX.rgb(0x990099);
		Color focus = FX.mix(accent, textBG, 0.1);
		
		// TODO text fg, selected text fg/bg
		
		// TODO themes
		add
		(
			// basic styles
			new Selector(".root").defines
			(
				prop("-fx-accent", accent),
				prop("-fx-focus-color", focus),
				// FIX effect here?
				prop("-fx-faint-focus-color", FX.mix(focus, textBG, 0.2))
			),
			
			new Selector(".text").defines
			(
				prop("-fx-font-smoothing-type", "gray")
			),
			
			// common fx styles
			new CommonStyles(),
			
			// application styles
			
			// FIX buttons
			// FIX square edges of text fields
			
			new Selector(ToolBar.FIND_BAR).defines
			(
				padding(spaces(2, 2, 2, 10))
			),
			
			new Selector(StatusBar.PANE).defines
			(
				padding(2)
			),
			
			new Selector(DetailPane.TITLE).defines
			(
				fontSize("160%"),
				borderColor(Color.GRAY),
				borderWidth(0, 0, 1, 0),
				padding(spaces(0, 0, 2, 0))
			)
		);
	}
}
