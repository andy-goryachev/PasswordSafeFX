// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass.data2;
import goryachev.common.util.CMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;


/**
 * Data Entry.
 */
public class DataEntry
	implements IDataEntry
{
	public final SimpleStringProperty name = new SimpleStringProperty();
	public final ObservableMap<String,byte[]> fields = FXCollections.observableHashMap();
	
	
	public DataEntry(String name, CMap<String,byte[]> fields)
	{
		setName(name);
	}
	
	
	public String toString()
	{
		return getName();
	}
	
	
	public void setName(String s)
	{
		name.set(s);
	}


	public String getName()
	{
		return name.get();
	}


	public byte[] getField(String id)
	{
		return fields.get(id);
	}
}
