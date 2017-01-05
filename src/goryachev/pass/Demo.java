// Copyright © 2016-2017 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.common.util.CMap;
import goryachev.pass.data2.DataEntry;
import goryachev.pass.data2.DataFile2;


/**
 * Demo.
 */
public class Demo
{
	public static DataFile2 createDataFile()
	{
		DataFile2 f = new DataFile2();
		f.addEntry("amazon.com", DataEntry.FIELD_USER_NAME, "andy", DataEntry.FIELD_NOTES, "notes\n1\n2\n3");
		f.addEntry("ebay.com", DataEntry.FIELD_USER_NAME, "andy", DataEntry.FIELD_NOTES, "notes\n1\n2\n3");
		return f;
	}
}
