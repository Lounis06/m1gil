package tp5;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class TableViewer {
     // Display a Swing component.
     public static void display(Component c, String title) {
          JFrame frame = new JFrame(title);
          frame.getContentPane().add(c);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.pack();
          frame.setVisible(true);
     }

	public TableViewer(TableModel tm) {
          JTable table = new JTable(tm);
          JScrollPane pane = new JScrollPane(table);
          pane.setPreferredSize(new java.awt.Dimension(300, 200));
          display(pane, "Token Viewer");
	
	}
} 
