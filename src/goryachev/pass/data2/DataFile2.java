// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass.data2;
import goryachev.common.util.CMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Data File.
 */
public class DataFile2
{
	public final ObservableList<IDataEntry> entries = FXCollections.observableArrayList();
	
	
	public DataFile2()
	{
	}
	
	
	public void addEntry(String name, CMap<String,byte[]> fields)
	{
		entries.add(new DataEntry(name, fields));
	}
}
