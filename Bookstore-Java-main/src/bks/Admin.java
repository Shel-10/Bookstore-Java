package bks;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Admin extends JFrame
{
	Clock clock=new Clock();
	//JFrame panel1;
	JScrollPane pane;
	JButton btn_addemp,btn_upemp,btn_rememp,btn_logout;
	PojoVerify pojo=new PojoVerify();
	public Admin(String Username) {
		
		//Frame block
		//setSize(1080,600);
		
		setLayout(null);
		setResizable(false);
		
		//Background block
		try {
			setContentPane(new JLabel(new ImageIcon(LoginFrame.class.getResource("../admin.png"))));
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JLabel header=new JLabel("Logged in as : "+Username.toUpperCase());
		header.setBounds(60, 100, 300, 60);
		header.setFont(new Font("Times New Roman",Font.BOLD,20));
		add(header);
		
		JLabel header2=new JLabel("INVENTORY");
		header2.setBounds(520, 100, 300, 60);
		header2.setFont(new Font("Times New Roman",Font.BOLD,20));
		add(header2);
		
		pojo.setSet(false);
		
		//Clock Calendar block
		clock.ctime.setBounds(850, 50,200, 30);
		clock.ctime.setFont(new Font("Serif",Font.BOLD,20));
		add(clock.ctime);
		
		clock.d_time.setBounds(650, 50,200, 30);
		clock.d_time.setFont(new Font("Serif",Font.BOLD,20));
		add(clock.d_time);
		
		String [] colNames= {"Name","ID","Price","Quantity"};
		
		
		
		
		JTable tab;
		DefaultTableModel mod;
		mod=new DefaultTableModel(null,colNames);
		
		btn_addemp=new JButton("Add Employee");
		btn_addemp.setBounds(60, 250, 150, 30);
		add(btn_addemp);
		btn_addemp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if(PojoVerify.isSet()==false)
				{
					pojo.setSet(true);
				new AddFrame("ADD");
				}
				else
				{
					JOptionPane.showMessageDialog(getComponent(0), "already open", "err",JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
		});
		
		btn_upemp=new JButton("Update Employee");
		btn_upemp.setBounds(60, 300, 150, 30);
		add(btn_upemp);
		btn_upemp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PojoVerify.isSet()==false)
				{
					pojo.setSet(true);;
					new AddFrame("UPDATE");
				}
				else
				{
					JOptionPane.showMessageDialog(getComponent(0), "Please complete pending operation", "WARNING",JOptionPane.WARNING_MESSAGE);
					
				}
				
				
			}
		});
		
		btn_rememp=new JButton("Remove Employee");
		btn_rememp.setBounds(60, 350, 150, 30);
		add(btn_rememp);
		btn_rememp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PojoVerify.isSet()==false)
				{
					pojo.setSet(true);
					new RemoveFrame("REMOVE");
				}
				else
				{
					JOptionPane.showMessageDialog(getComponent(0), "already open", "Error",JOptionPane.ERROR_MESSAGE);
					
				}
				
				
			}
		});
		
		btn_logout=new JButton("Logout");
		btn_logout.setBounds(60, 500, 150, 30);
		add(btn_logout);
		
		btn_logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int opt=JOptionPane.showConfirmDialog(getComponent(0), "Are you sure you want to Logout", "Confirm",JOptionPane.OK_CANCEL_OPTION);
				if(opt==0)
				{
					System.out.println("ok");
					dispose();
					new LoginFrame();
					
				}
				else if(opt==2)
				{
					System.out.println("cancel");
					//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
					
				}
				else
				{
					//Nothing
				}
			}
		});
		
		
		tab=new JTable();
		tab.setModel(mod);
		
		pane=new JScrollPane(tab);
		pane.setBounds(300,150, 600, 350);
		add(pane);
		
		Statement inv=null;
		try {
			inv=Dbcon.Conn().createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet invset=null;
		try {
			invset=inv.executeQuery("Select * from inv");
			String name,id;
			double price;
			int quantity;
			while(invset.next())
			{
				name=invset.getString(1);
				id=invset.getString(2);
				price=invset.getDouble(3);
				quantity=invset.getInt(4);
				mod.addRow(new Object[]{name,id,price,quantity});
				System.out.println("filled 1");
			}	
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Dbcon.Conn().close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//panel1=new JFrame();
		
		setBounds(200,150, 1080, 600);
		setEnabled(true);
		setVisible(true);
		
		
	}
	
}
