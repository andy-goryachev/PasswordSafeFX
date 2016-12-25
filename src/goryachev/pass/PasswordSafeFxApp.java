// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.common.util.CSystem;
import goryachev.common.util.GlobalSettings;
import goryachev.common.util.Log;
import goryachev.common.util.Parsers;
import goryachev.fx.CssLoader;
import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Password Safe FX App.
 */
public class PasswordSafeFxApp
	extends Application
{
	public static final String COPYRIGHT = "copyright © 2016 andy goryachev";	
	public static final String TITLE = "Passwørd Safe FX";
	public static final String SETTINGS_FILE = "settings.conf";
	
	
	public static void main(String[] args)
	{
		// disables LCD anti-aliasing in WebView
		System.setProperty("prism.lcdtext", "false");
			
		// init logger
		Log.initConsole();
		Log.conf("DebugSettingsProvider", true);
		
		// init non-ui subsystems
		GlobalSettings.setFileProvider(new File(getSettingsDir(), SETTINGS_FILE));
		
		// launch ui
		launch(PasswordSafeFxApp.class, args);
	}
	
	
	public static File getSettingsDir()
	{
		String u = CSystem.getUserHome();
		return new File(u + "/Documents/goryachev.com/PasswordSafeFX/");
	}
	

	public void start(Stage s) throws Exception
	{
		// TODO set language from default locale
		
		File f = Parsers.parseFile(Options.dataFile.get());
		new MainWindow(f).open();
		CssLoader.setStyles(new Styles());
	}
}
