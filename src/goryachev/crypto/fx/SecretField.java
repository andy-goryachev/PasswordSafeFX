// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.crypto.fx;
import goryachev.common.util.D;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


/**
 * SecretField.
 */
public final class SecretField
	extends TextField
{
	public SecretField()
	{
		addEventFilter(KeyEvent.ANY, (ev) -> handleKey(ev));
	}

	
	protected void  handleKey(KeyEvent ev)
	{
		D.print(ev);
		// TODO focus traversal, enter
		ev.consume();
	}
}
