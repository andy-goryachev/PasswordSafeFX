// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.common.util.CMap;
import goryachev.pass.data2.DataFile2;


/**
 * Demo.
 */
public class Demo
{
	public static DataFile2 createDataFile()
	{
		DataFile2 f = new DataFile2();
		f.addEntry("amazon.com", new CMap<>());
		f.addEntry("ebay.com", new CMap<>());
		return f;
	}
}
