package bks;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

class User extends JFrame
{
	Clock clock=new Clock();
	JLabel lbl_bid,lbl_bno,lbl_pname,lbl_pid,lbl_price,lbl_qty,disp_pname,disp_price;
	JTextField txt_pid,txt_qty;
	JTable table;
	DefaultTableModel model;
	JScrollPane pane;
	JButton add_btn,refresh_btn,btn_logout;
	
	JLabel lcust_name,lcust_no,lbl_sum,lbl_summsg,header;
	JTextField cust_name,cust_no;
	
	public User(String Username) {
		//Frame block
		setSize(1080,600);
		setResizable(false);

		//background block
		try {
			setContentPane(new JLabel(new ImageIcon(LoginFrame.class.getResource("../user.png"))));
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//UI block
		
		header=new JLabel("Logged in as : "+Username.toUpperCase());
		header.setBounds(50,40, 300, 60);
		header.setFont(new Font("Times New Roman",Font.BOLD,20));
		add(header);
		
		JButton Confirm_btn=new JButton("Confirm");
		Confirm_btn.setBounds(830,520, 110,30);
		add(Confirm_btn);
		
		lcust_name=new JLabel("Customer Name");
		lcust_name.setBounds(100,150, 150, 30);
		lcust_name.setFont(new Font("Times New Roman",Font.PLAIN,18));
		add(lcust_name);
		
		cust_name=new JTextField();
		cust_name.setBounds(260,150, 200, 30);
		add(cust_name);
		
		cust_no=new JTextField();
		cust_no.setBounds(610, 150, 150, 30);
		add(cust_no);
		
		lcust_no=new JLabel("Contact");
		lcust_no.setBounds(500, 150, 100, 30);
		lcust_no.setFont(new Font("Times New Roman",Font.PLAIN,18));
		add(lcust_no);
		
		
		lbl_bid=new JLabel("Bill ID:");
		lbl_bid.setBounds(20,260, 100, 30);
		lbl_bid.setFont(new Font("Times New Roman",Font.BOLD,20));
		add(lbl_bid);
		
		
		lbl_bno=new JLabel("15246");
		lbl_bno.setBounds(140,260, 100, 30);
		lbl_bno.setFont(new Font("Times New Roman",Font.BOLD,20));
		add(lbl_bno);
		
		//clock block N
		clock.d_time.setBounds(650,50, 200, 30);
		clock.d_time.setFont(new Font("Times New Roman",Font.BOLD,20));
		add(clock.d_time);
		
		
		clock.ctime.setBounds(850,50,200, 30);
		clock.ctime.setFont(new Font("Times New Roman",Font.BOLD,20));
		add(clock.ctime);
		
		lbl_pname=new JLabel("Product Name");
		lbl_pname.setBounds(20, 300, 100, 30);
		add(lbl_pname);
		
		disp_pname=new JLabel();
		disp_pname.setBounds(140,300, 100, 30);
		disp_pname.setBorder(new LineBorder(Color.black));
		add(disp_pname);
		
		
		lbl_pid=new JLabel("Product ID");
		lbl_pid.setBounds(20, 340, 100, 30);
		add(lbl_pid);
		
		txt_pid=new JTextField();
		txt_pid.setBounds(140,340, 100, 30);
		add(txt_pid);
		txt_pid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Statement stat=null;
				try {
					stat=Dbcon.Conn().createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ResultSet rs=null;
				try {
					rs=stat.executeQuery("select * from inv");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Obtained");
				try {
					while(rs.next())
					{
						if(txt_pid.getText().equals(rs.getString("pid")))
						{
							disp_pname.setText(rs.getString("pname"));
							disp_price.setText(rs.getString("price"));
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		lbl_price=new JLabel("Price");
		lbl_price.setBounds(20, 380, 100, 30);
		add(lbl_price);
		
		disp_price=new JLabel();
		disp_price.setBounds(140,380, 100, 30);
		disp_price.setBorder(new LineBorder(Color.black));
		add(disp_price);
		
		lbl_qty=new JLabel("Quantity");
		lbl_qty.setBounds(20, 420, 100, 30);
		add(lbl_qty);
		
		txt_qty=new JTextField("1");
		txt_qty.setBounds(140,420, 100, 30);
		add(txt_qty);
		
		JLabel lbl_msg=new JLabel(" Press Enter to Verify Product ID");
		lbl_msg.setBounds(30,500, 200, 50);
		lbl_msg.setVisible(true);
		lbl_msg.setForeground(Color.BLACK);
		add(lbl_msg);
		
		lbl_summsg=new JLabel("Total Amount :");
		lbl_summsg.setBounds(710,470, 100, 30);
		add(lbl_summsg);
		
		lbl_sum=new JLabel();
		lbl_sum.setBounds(830,470, 100, 30);
		lbl_sum.setBorder(new LineBorder(Color.black));
		add(lbl_sum);
		
		//table block
		String [] columnNames= {"Name","ID","Price","Quantity","Total"};
		table=new JTable();
		model=new DefaultTableModel(null,columnNames);
		pane=new JScrollPane(table);
		table.setModel(model);
		add(pane);
		pane.setBounds(350,200,450,250);
		
		add_btn=new JButton("Add item");
		add_btn.setBounds(80, 470, 100, 30);
		add(add_btn);
		
		add_btn.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!txt_pid.getText().equals("")&& !txt_qty.getText().equals(""))
				{
					if(!disp_pname.getText().equals(""))
					{
						double totalprice=Double.parseDouble(disp_price.getText())*Integer.parseInt(txt_qty.getText());
						model.addRow(new Object[] {disp_pname.getText(),txt_pid.getText(),disp_price.getText(),txt_qty.getText(),totalprice} );
						ClearText();
						lbl_sum.setText(UpdateTable().toString());
					}
				}
			
			}
		
		});
		
		
		
		refresh_btn=new JButton("Clear item");
		refresh_btn.setBounds(830,200, 100, 30);
		add(refresh_btn);
		
		refresh_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			int rem_row=table.getSelectedRow();
			if(rem_row!=-1)
			{
				model.removeRow(rem_row);
				lbl_sum.setText(UpdateTable().toString());
			}
			else
			{
				System.out.println("empty row sel");
				JOptionPane.showMessageDialog(getComponent(0), "Please select a row","Null row selection", JOptionPane.WARNING_MESSAGE);
			}
			
			}});
		
		btn_logout=new JButton("Logout");
		btn_logout.setBounds(50,90,90,25);
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
		
		Confirm_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(model.getRowCount()==0)
				{
					System.out.println("blaaankkk");
					JOptionPane.showMessageDialog(getComponent(0), "No Items Added","ERROR", JOptionPane.ERROR_MESSAGE);
					
				}
				else {
					if(cust_name.getText().isBlank()||cust_no.getText().isBlank())
					{
						JOptionPane.showMessageDialog(getComponent(0), "Empty fields not allowed","ERROR", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(getComponent(0), "Submitted","Done", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new User(Username);
					}
				}
				
			}
		});
		
		setBounds(200,150, 1080, 600);
		setVisible(true);
	
	}
	
	void ClearText()
	{
		disp_pname.setText("");
		txt_pid.setText("");
		disp_price.setText("");
		txt_qty.setText("1");
	}
	
	Double UpdateTable()
	{
		String s;
		double t,sum=0;
		for(int i=0;i<table.getRowCount();i++)
		{
			
				t=(Double)table.getValueAt(i,4);
				sum=sum+t;
				
			
		}
		//System.out.println(sum);
		sum=(Double)sum;
		return sum;
	}
	
}
