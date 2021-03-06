package bks;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Test extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//removable

	public static void main(String[] args) {
		new Test();
	}

	JTable table;
	DefaultTableModel model;
	
	String[] columnNames= {"Name","Pass","Status"};
	String name;
	String pass,status;

	
	public Test() {
		// TODO Auto-generated constructor stub
		setVisible(true);
		setSize(400, 600);
		setLayout(null);
		
		model=new DefaultTableModel(null,columnNames);
		Statement s=null;
		try {
			s = Dbcon.Conn().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs=null;
		try {
		rs = s.executeQuery("select * from login");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(rs.next())
			{
				name=rs.getString("Username");
				pass=rs.getString("Password");
				status=rs.getString("Status");
				model.addRow(new Object[]{name,pass,status});
				System.out.println("filled 1");
				
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table=new JTable();
		
		//model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		table.setFillsViewportHeight(true);
		table.setBounds(20, 20, 200, 200);
		add(table);
		JScrollPane sp=new JScrollPane(table);
		sp.setBounds(20, 20, 200, 200);
		add(sp);
	}
}
