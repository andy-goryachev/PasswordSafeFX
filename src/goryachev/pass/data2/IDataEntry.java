// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass.data2;
import javafx.beans.property.Property;


/**
 * Data Entry Interface.
 */
public interface IDataEntry
{
	public Property<String> nameProperty();
	
	
	public String getName();
	
	
	public byte[] getField(String id);
}
