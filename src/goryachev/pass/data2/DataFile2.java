// Copyright © 2016-2017 Andy Goryachev <andy@goryachev.com>
package goryachev.pass.data2;
import goryachev.crypto.OpaqueChars;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Data File.
 */
public class DataFile2
{
	private final ObservableList<DataEntry> entries = FXCollections.observableArrayList();
	
	
	public DataFile2()
	{
	}
	
	
	public ObservableList<DataEntry> getEntries()
	{
		return entries;
	}
	
	
	public void addEntry(String title, String ... fields)
	{
		DataEntry en = new DataEntry();
		en.title.set(title);
		
		for(int i=0; i<fields.length; )
		{
			String k = fields[i++];
			String v = fields[i++];
			
			switch(k)
			{
			case DataEntry.FIELD_NOTES:
				en.notes.set(v);
				break;
			case DataEntry.FIELD_PASSWORD:
				en.password.set(new OpaqueChars(v.toCharArray()));
				break;
			case DataEntry.FIELD_TITLE:
				en.title.set(v);
				break;
			case DataEntry.FIELD_USER_NAME:
				en.userName.set(v);
				break;
			default:
				throw new Error("?" + k);
			}
		}
		
		entries.add(en);
	}


	public static DataFile2 createEmptyFile()
	{
		DataFile2 f = new DataFile2();
		f.addEntry("Untitled");
		return f;
	}
}
