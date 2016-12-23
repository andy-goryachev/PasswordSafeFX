// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.common.util.GlobalSettings;
import goryachev.common.util.Log;
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
		// TODO better location?
		String u = System.getProperty("user.home");
		return new File(u + "/goryachev.com/PasswordSafeFX/");
	}
	
	
	public static void createDatabase() throws Exception
	{
	}
	

	public void start(Stage s) throws Exception
	{
//		IMailData data = loadData();
//		if(data == null)
//		{
//			// TODO new WelcomeWizard().open();
//			createDatabase();
//			data = loadData();
//			if(data == null)
//			{
//				throw new Error("unable to create database");
//			}
//			
//			// TODO import all folders from my mail
//		}
//
//		DB.setData(data);
		
		new MainWindow().open();
		CssLoader.setStyles(new Styles());
	}
}
