// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass.data2;
import goryachev.crypto.OpaqueChars;
import javafx.beans.property.Property;


/**
 * Data Entry Interface.
 */
public interface IDataEntry
{
	public String getName();
	
	
	public Property<String> nameProperty();


	public Property<String> userNameProperty();
	
	
	public Property<String> notesProperty();

	
	public OpaqueChars getPassword();
	
	
	public void setPassword(OpaqueChars pw);
	
	
	public OpaqueChars getCustomField(String id);
	
	
	public void setCustomField(String id, OpaqueChars data);
}
