// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.crypto.fx;
import goryachev.common.util.D;
import goryachev.common.util.Hex;
import goryachev.crypto.Crypto;
import goryachev.crypto.OpaqueChars;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


/**
 * Secret Field.
 */
public final class SecretField
	extends TextField
{
	private static final String TEXT_PHASE_1 = "$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*";
	private static final String TEXT_PHASE_2 = "*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$*$";
	private final OpaqueChars password;
	
	
	public SecretField()
	{
		password = new OpaqueChars();
		
		addEventFilter(KeyEvent.KEY_PRESSED, (ev) -> handleKeyPressed(ev));
		addEventFilter(KeyEvent.KEY_TYPED, (ev) -> handleKeyTyped(ev));
		addEventFilter(KeyEvent.KEY_RELEASED, (ev) -> handleKeyReleased(ev));
		
		tick();
	}
	
	
	protected void tick()
	{
		String s = getText();
		if(TEXT_PHASE_1.equals(s))
		{
			s = TEXT_PHASE_2;
		}
		else
		{
			s = TEXT_PHASE_1;
		}
		setText(s);
		selectPositionCaret(0);
	}
	
	
	protected final boolean shouldIgnore(KeyCode k)
	{
		switch(k)
		{
		case ENTER:
		case TAB:
			return true;
		}
		return false;
	}
	
	
	protected final boolean shouldIgnore(String k)
	{
		switch(k)
		{
		case "\r":
		case "\t":
			return true;
		}
		return false;
	}

	
	protected final void handleKeyPressed(KeyEvent ev)
	{
		String ch = ev.getCharacter();
		KeyCode k = ev.getCode();
		
		if(shouldIgnore(k))
		{
			return;
		}
		
//		D.print(k, ch);
		
		ev.consume();
	}
	
	
	protected final void handleKeyReleased(KeyEvent ev)
	{
		String ch = ev.getCharacter();
		KeyCode k = ev.getCode();
		
		if(shouldIgnore(k))
		{
			return;
		}
		
//		D.print(k, ch);
		
		ev.consume();
	}
	
	
	protected final void handleKeyTyped(KeyEvent ev)
	{
		String ch = ev.getCharacter();
		
		if(shouldIgnore(ch))
		{
			return;
		}
				
		switch(ch)
		{
		case "\b":
			// backspace
			password.deleteLastChar();
			break;
		case "\u007f":
			// delete
			break;
		default:
			password.append(ch);
			break;
		}
		
		D.print(ch, Hex.toHexString(ch.charAt(0)));
		
		ev.consume();
		
		tick();
	}
}
