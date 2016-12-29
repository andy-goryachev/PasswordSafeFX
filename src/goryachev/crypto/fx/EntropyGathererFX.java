// Copyright © 2012-2016 Andy Goryachev <andy@goryachev.com>
package goryachev.crypto.fx;
import goryachev.crypto.EntropyGathererBase;
import javafx.scene.input.InputEvent;


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
		
		// TODO register with FxWindow
	}
	

	public final void eventDispatched(InputEvent ev)
	{
		// let's mix in everything we can get our hands on
		random.addSeedMaterial(jvmRandom.nextLong());
		random.addSeedMaterial(System.currentTimeMillis());
		// FIX
//		random.addSeedMaterial(ev.getID());
		
		if(ev.getSource() != null)
		{
			random.addSeedMaterial(ev.getSource().hashCode());
		}
		
//		if(ev instanceof MouseEvent)
//		{
//			MouseEvent e = (MouseEvent)ev;
//			random.addSeedMaterial(e.getXOnScreen());
//			random.addSeedMaterial(e.getYOnScreen());
//			random.addSeedMaterial(e.getX());
//			random.addSeedMaterial(e.getY());
//			random.addSeedMaterial(e.getModifiersEx());
//			random.addSeedMaterial(e.getClickCount());
//		}
//		else if(ev instanceof KeyEvent)
//		{
//			KeyEvent e = (KeyEvent)ev;
//			random.addSeedMaterial(e.getKeyChar());
//			random.addSeedMaterial(e.getKeyCode());
//			random.addSeedMaterial(e.getModifiersEx());
//		}
		
		random.addSeedMaterial(Runtime.getRuntime().freeMemory());
		random.addSeedMaterial(System.nanoTime());
		
		tick();
	}
}
