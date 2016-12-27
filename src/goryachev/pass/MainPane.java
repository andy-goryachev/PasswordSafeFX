// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.fx.CPane;
import goryachev.fx.table.FxTable;
import goryachev.fx.table.FxTableColumn;
import goryachev.pass.data2.IDataEntry;
import javafx.beans.Observable;
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
		table.setMultipleSelection(true);
		table.getSelectedItems().addListener((Observable src) -> handleTableSelection());
		
		detailPane = new CPane();
		
		split = new SplitPane(table, detailPane);
		split.setOrientation(Orientation.HORIZONTAL);
		
		setCenter(split);
		
		table.selectFirst();
	}
	
	
	protected void handleTableSelection()
	{
		ObservableList<IDataEntry> es = table.getSelectedItems();
		switch(es.size())
		{
		case 1:
			IDataEntry en = es.get(0);
			detailPane.setCenter(new DetailPane(en));
			break;
		default:
			detailPane.setCenter(null);
			break;
		}
	}
}
