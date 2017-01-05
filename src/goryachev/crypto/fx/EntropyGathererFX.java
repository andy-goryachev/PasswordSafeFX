// Copyright © 2012-2017 Andy Goryachev <andy@goryachev.com>
package goryachev.crypto.fx;
import goryachev.crypto.EntropyGathererBase;
import goryachev.fx.FX;
import goryachev.fx.FxWindow;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


/**
 * An entropy gathering component and corresponding SecureRandom.
 * <p>
 * This goal of this implementation is to improve security of the standard JVM 
 * implementation by using a publicly known algorithm (Bouncycastle's DigestRandomGenerator),
 * while using both JVM SecureRandom and user AWT events as the source of entropy.
 * By using both sources the overall quality of the generated random numbers should improve,
 * even assuming a possibility that the JVM implementation is compromised, as in this case:
 * http://www.theregister.co.uk/2013/08/12/android_bug_batters_bitcoin_wallets/
 * <p>
 * This component must be attached to the AWT thread by calling its start() method 
 * in Application.start() method.
 */
public final class EntropyGathererFX
	extends EntropyGathererBase
{
	private EntropyGathererFX()
	{
		super("EntropyGathererFX");
	}
	
	
	/** 
	 * Attaches the global entropy gatherer instance to the AWT event queue.  
	 * Subsequent calls have no effect.
	 * It is recommenended to call this method when AWT queue becomes available, 
	 * such as on the main application window creation.  
	 */
	public synchronized static final void start()
	{
		EntropyGathererFX g = new EntropyGathererFX();
		FX.addWindowMonitor((w) -> g.attach(w));
	}
	
	
	protected void attach(FxWindow w)
	{
		w.addEventFilter(MouseEvent.ANY, (ev) -> handleEvent(ev));
		w.addEventFilter(KeyEvent.ANY, (ev) -> handleEvent(ev));
	}
	

	public final void handleEvent(InputEvent ev)
	{
		// let's mix in everything we can get our hands on
		addSeedMaterial(jvmRandom.nextLong());
		addSeedMaterial(System.currentTimeMillis());
		addSeedMaterial(ev.getEventType().hashCode());
		
		if(ev.getSource() != null)
		{
			addSeedMaterial(ev.getSource().hashCode());
		}
		
		if(ev instanceof MouseEvent)
		{
			MouseEvent e = (MouseEvent)ev;
			addSeedMaterial(e.getScreenX());
			addSeedMaterial(e.getScreenY());
			addSeedMaterial(e.getX());
			addSeedMaterial(e.getY());
			addSeedMaterial(e.getButton().hashCode());
			addSeedMaterial(e.getClickCount());
		}
		else if(ev instanceof KeyEvent)
		{
			KeyEvent e = (KeyEvent)ev;
			addSeedMaterial(e.getCharacter());
			addSeedMaterial(e.getCode().hashCode());
			addSeedMaterial(e.getText());
		}
		
		addSeedMaterial(Runtime.getRuntime().freeMemory());
		addSeedMaterial(System.nanoTime());
		
		tick();
	}
}
