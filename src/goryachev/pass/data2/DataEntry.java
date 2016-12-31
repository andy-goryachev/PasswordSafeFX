// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass.data2;
import goryachev.crypto.OpaqueChars;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;


/**
 * Data Entry is an FX property container corresponding to a single database record.
 */
public final class DataEntry
{
	// leading space identifies special persistence keys
	public static final String FIELD_NOTES = " s";
	public static final String FIELD_PASSWORD = " p";
	public static final String FIELD_TITLE = " t";
	public static final String FIELD_USER_NAME = " u";

	public final SimpleStringProperty title = new SimpleStringProperty();
	public final SimpleStringProperty userName = new SimpleStringProperty();
	public final SimpleObjectProperty<OpaqueChars> password = new SimpleObjectProperty<>();
	public final SimpleStringProperty notes = new SimpleStringProperty();
	public final ObservableMap<String,SimpleObjectProperty<Object>> customFields = FXCollections.observableHashMap();
	
	
	public DataEntry()
	{
	}
	
	
	public String toString()
	{
		return getTitle();
	}
	

	public String getTitle()
	{
		return title.get();
	}


	public SimpleObjectProperty getCustomField(String id)
	{
		return customFields.get(id);
	}
}
