// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass.data2;


/**
 * Data Entry Interface.
 */
public interface IDataEntry
{
	public String getName();
	
	
	public byte[] getField(String id);
}
