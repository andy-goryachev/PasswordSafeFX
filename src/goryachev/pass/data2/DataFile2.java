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
	private final ObservableList<IDataEntry> entries = FXCollections.observableArrayList();
	
	
	public DataFile2()
	{
	}
	
	
	public ObservableList<IDataEntry> getEntries()
	{
		return entries;
	}
	
	
	public void addEntry(String name, CMap<String,Object> fields)
	{
		entries.add(new DataEntry(name, fields));
	}
}
