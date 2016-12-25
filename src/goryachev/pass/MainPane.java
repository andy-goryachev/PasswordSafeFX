// Copyright © 2016 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.fx.CPane;
import goryachev.fx.table.FxTable;
import goryachev.fx.table.FxTableColumn;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;


/**
 * Main Pane.
 */
public class MainPane
	extends CPane
{
	public final FxTable<DataEntry> table;
	public final CPane detailPane;
	public final SplitPane split;
	
	
	public MainPane()
	{
		table = new FxTable<>();
		table.addColumn(new FxTableColumn<DataEntry>());
		table.hideHeader();
		
		detailPane = new CPane();
		
		split = new SplitPane(table, detailPane);
		split.setOrientation(Orientation.HORIZONTAL);
		
		setCenter(split);
	}
}
