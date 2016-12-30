// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass.data2;
import goryachev.common.util.CMap;
import goryachev.crypto.OpaqueChars;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;


/**
 * Data Entry.
 */
public final class DataEntry
	implements IDataEntry
{
	private static final String FIELD_NAME = " n";
	private static final String FIELD_NOTES = " s";
	private static final String FIELD_PASSWORD = " p";
	private static final String FIELD_USER_NAME = " u";
	
	private final SimpleStringProperty name = new SimpleStringProperty();
	private final SimpleStringProperty userName = new SimpleStringProperty();
	private final SimpleStringProperty notes = new SimpleStringProperty();
	private final ObservableMap<String,Object> fields = FXCollections.observableHashMap();
	
	
	public DataEntry(String name, CMap<String,Object> fields)
	{
		setName(name);
		this.fields.putAll(fields);
	}
	
	
	public String toString()
	{
		return getName();
	}
	
	
	public Property<String> nameProperty()
	{
		return name;
	}
	

	public Property<String> userNameProperty()
	{
		return userName;
	}
	
	
	public Property<String> notesProperty()
	{
		return notes;
	}

	
	public void setName(String s)
	{
		name.set(s);
	}


	public String getName()
	{
		return name.get();
	}


	public OpaqueChars getCustomField(String id)
	{
		Object v = fields.get(id);
		if(v instanceof OpaqueChars)
		{
			return (OpaqueChars)v;
		}
		return null;
	}
	
	
	public void setCustomField(String id, OpaqueChars data)
	{
		fields.put(id, data);
	}


	public OpaqueChars getPassword()
	{
		return getCustomField(FIELD_PASSWORD);
	}


	public void setPassword(OpaqueChars pw)
	{
		setCustomField(FIELD_PASSWORD, pw);
	}
}
