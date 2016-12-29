// Copyright © 2012-2016 Andy Goryachev <andy@goryachev.com>
package goryachev.crypto.swing;
import goryachev.crypto.EntropyGathererBase;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


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
 * This component must be attached to the AWT thread by calling its start() method when the 
 * event dispatch thread is active.  Recommended way is to call EntropyGatherer.start() in
 * every application window constructor.
 */
public final class EntropyGathererSwing
	extends EntropyGathererBase
	implements AWTEventListener
{
	private EntropyGathererSwing()
	{
		super("EntropyGathererSwing");
	}
	
	
	/** 
	 * Attaches the global entropy gatherer instance to the AWT event queue.  
	 * Subsequent calls have no effect.
	 * It is recommenended to call this method when AWT queue becomes available, 
	 * such as on the main application window creation.  
	 */
	public synchronized static final void start()
	{
		// TODO avoid error here if already registered
		EntropyGathererSwing g = new EntropyGathererSwing();
		
		long mask =
			AWTEvent.HIERARCHY_EVENT_MASK |
			AWTEvent.FOCUS_EVENT_MASK |
			AWTEvent.MOUSE_EVENT_MASK |
			AWTEvent.MOUSE_MOTION_EVENT_MASK |
			AWTEvent.MOUSE_WHEEL_EVENT_MASK |
			AWTEvent.KEY_EVENT_MASK;
		
		Toolkit.getDefaultToolkit().addAWTEventListener(g, mask);
	}


	public final void eventDispatched(AWTEvent ev)
	{
		// let's mix in everything we can get our hands on
		addSeedMaterial(jvmRandom.nextLong());
		addSeedMaterial(System.currentTimeMillis());
		addSeedMaterial(ev.getID());
		
		if(ev.getSource() != null)
		{
			addSeedMaterial(ev.getSource().hashCode());
		}
		
		if(ev instanceof MouseEvent)
		{
			MouseEvent e = (MouseEvent)ev;
			addSeedMaterial(e.getXOnScreen());
			addSeedMaterial(e.getYOnScreen());
			addSeedMaterial(e.getX());
			addSeedMaterial(e.getY());
			addSeedMaterial(e.getModifiersEx());
			addSeedMaterial(e.getClickCount());
		}
		else if(ev instanceof KeyEvent)
		{
			KeyEvent e = (KeyEvent)ev;
			addSeedMaterial(e.getKeyChar());
			addSeedMaterial(e.getKeyCode());
			addSeedMaterial(e.getModifiersEx());
		}
		
		addSeedMaterial(Runtime.getRuntime().freeMemory());
		addSeedMaterial(System.nanoTime());
		
		tick();
	}
}
