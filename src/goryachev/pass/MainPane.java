// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.fx.CPane;
import goryachev.fx.table.FxTable;
import goryachev.fx.table.FxTableColumn;
import goryachev.pass.data2.DataEntry;
import goryachev.pass.data2.IDataEntry;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;


/**
 * Main Pane.
 */
public class MainPane
	extends CPane
{
	public final FxTable<IDataEntry> table;
	public final CPane detailPane;
	public final SplitPane split;
	
	
	public MainPane(ObservableList<IDataEntry> entries)
	{
		table = new FxTable<>();
		table.addColumn(new FxTableColumn<IDataEntry>());
		table.hideHeader();
		table.setResizePolicyConstrained();
		table.setItems(entries);
		table.getSelectionModel().selectedItemProperty().addListener((src) -> handleTableSelection());
		
		detailPane = new CPane();
		
		split = new SplitPane(table, detailPane);
		split.setOrientation(Orientation.HORIZONTAL);
		
		setCenter(split);
		
		table.selectFirst();
	}
	
	
	protected void handleTableSelection()
	{
		IDataEntry en = table.getSelectedItem();
		if(en == null)
		{
			detailPane.setCenter(null);
		}
		else
		{
			detailPane.setCenter(new DetailPane(en));
		}
	}
}
