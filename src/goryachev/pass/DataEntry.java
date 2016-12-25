// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import javafx.beans.property.SimpleStringProperty;


/**
 * Data Entry.
 */
public class DataEntry
{
	public final SimpleStringProperty name = new SimpleStringProperty();
	
	
	public DataEntry(String name)
	{
		setName(name);
	}
	
	
	public void setName(String s)
	{
		name.set(s);
	}
}
