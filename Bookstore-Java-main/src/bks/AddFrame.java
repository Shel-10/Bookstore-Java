package bks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class AddFrame {
	JFrame panel1=new JFrame();
	String[] item= {"User","Admin"};
	JTextField txt_fname,txt_lname,txt_contact,txt_address,txt_pass;
	JComboBox box=new JComboBox(item);
	PojoVerify ver=new PojoVerify();
	public AddFrame(String choice)
	{

		panel1.setLayout(null);
		JLabel lbl_fname,lbl_lname,lbl_contact,lbl_address,lbl_head,lbl_pass;
		
		
		JButton add;
		
		lbl_head=new JLabel("--"+choice+" EMPLOYEE DETAILS--");
		lbl_head.setBounds(240, 10, 240, 40);
		panel1.add(lbl_head);
		
		lbl_fname=new JLabel("Name");
		lbl_fname.setBounds(100,60, 100, 30);
		panel1.add(lbl_fname);
		
		txt_fname=new JTextField();
		txt_fname.setBounds(200,60, 100, 30);
		panel1.add(txt_fname);
		
		lbl_lname=new JLabel("Surname");
		lbl_lname.setBounds(100,100, 100, 30);
		panel1.add(lbl_lname);
		
		txt_lname=new JTextField();
		txt_lname.setBounds(200,100, 100, 30);
		panel1.add(txt_lname);
		
		lbl_contact=new JLabel("Contact");
		lbl_contact.setBounds(100,140, 100, 30);
		panel1.add(lbl_contact);
		
		txt_contact=new JTextField();
		txt_contact.setBounds(200,140, 100, 30);
		panel1.add(txt_contact);
		
		lbl_address=new JLabel("Address");
		lbl_address.setBounds(100,180, 100, 30);
		panel1.add(lbl_address);
		
		txt_address=new JTextField();
		txt_address.setBounds(200,180, 100, 30);
		panel1.add(txt_address);
		
		lbl_pass=new JLabel("Password");
		lbl_pass.setBounds(100,220, 100, 30);
		panel1.add(lbl_pass);
		
		txt_pass=new JTextField();
		txt_pass.setBounds(200,220, 100, 30);
		panel1.add(txt_pass);
		
		box.setBounds(150,280, 100, 30);
		panel1.add(box);
		
		add=new JButton("Submit");
		add.setBounds(200, 350, 100,30);
		panel1.add(add);
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(isBlankVerify()) {
					JOptionPane.showMessageDialog(panel1, "Blank fields not allowed", "Error",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(choice.equalsIgnoreCase("add"))
					{
						add();
					}
					if(choice.equalsIgnoreCase("update"))
					{
						update();
					}
					
				}
			}
		});
		

		
		panel1.setBounds(300,200,500,500);
		panel1.setVisible(true);
		
		panel1.addWindowListener(new WindowAdapter()
				{
			@Override
			public void windowClosing(WindowEvent e) {
				int input=JOptionPane.showConfirmDialog(panel1, "Discard", "exit",JOptionPane.OK_CANCEL_OPTION);
				if(input==0)
				{
					ver.setSet(false);
					panel1.setDefaultCloseOperation(panel1.DISPOSE_ON_CLOSE);
				}
				else if(input==2)
				{
					System.out.println("cancelllll");
					panel1.setDefaultCloseOperation(panel1.DO_NOTHING_ON_CLOSE);
				}
				super.windowClosing(e);
			}
				});
	}
	boolean isBlankVerify()
	{
		if( txt_fname.getText().isBlank()||txt_lname.getText().isBlank()||txt_contact.getText().isBlank()||txt_address.getText().isBlank()||txt_pass.getText().isBlank())
		{
			System.out.println("blaaankkk");
			return true;
		}
		else
		{
			return false;
		}
			//,,txt_contact,txt_address,txt_pass	
	}
	
	void add()
	{
		Statement statement=null;
		try {
			statement=Dbcon.Conn().createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		ResultSet rs=null;
		try {
			rs = statement.executeQuery("select * from info where name='"+txt_fname.getText()+"'and sname='"+txt_lname.getText()+"'");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		try {
			if(rs.next()==false)//not existing clause
			{
				System.out.println("n exists");
				PreparedStatement stat=null;
				PreparedStatement exec=null;
				try {
					stat = Dbcon.Conn().prepareStatement("insert into info values(?,?,?,?,?,?)");
					stat.setString(1, txt_fname.getText());
					stat.setString(2, txt_lname.getText());
					stat.setString(3, txt_contact.getText());
					stat.setString(4, txt_address.getText());
					stat.setString(5, txt_pass.getText());
					stat.setString(6, box.getSelectedItem().toString());
					
					exec=Dbcon.Conn().prepareStatement("insert into login values(?,?,?)");
					exec.setString(1, txt_fname.getText());
					exec.setString(2, txt_pass.getText());
					if(box.getSelectedItem().equals("Admin"))
					{
						exec.setString(3, "true");
					}
					else
					{
						exec.setString(3, "false");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					stat.execute();
					exec.execute();
					JOptionPane.showMessageDialog(panel1, "Employee Details Added", "New Record Added", JOptionPane.INFORMATION_MESSAGE);
	
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println( box.getSelectedItem());
				
				
			}
			else {JOptionPane.showMessageDialog(panel1, "Record Exists", "Error", JOptionPane.ERROR_MESSAGE);}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	void update()
	{
		Statement statement=null;
		try {
			statement=Dbcon.Conn().createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		ResultSet rs=null;
		try {
			rs = statement.executeQuery("select * from info where name='"+txt_fname.getText()+"'and sname='"+txt_lname.getText()+"'");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(rs.next())
			{
				System.out.println(" exists");
				
				PreparedStatement stat=null;
				PreparedStatement exec=null;
				try {
					stat = Dbcon.Conn().prepareStatement("update info set con=?,address=?,pass=?,status=? where name=? and sname=?");
					stat.setString(5, txt_fname.getText());
					stat.setString(6, txt_lname.getText());
					stat.setString(1, txt_contact.getText());
					stat.setString(2, txt_address.getText());
					stat.setString(3, txt_pass.getText());
					stat.setString(4, box.getSelectedItem().toString());
					
					exec=Dbcon.Conn().prepareStatement("update login set Password=? where name=?");
					exec.setString(1, txt_pass.getText());
					exec.setString(2, txt_fname.getText());
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stat.execute();
				exec.execute();
				JOptionPane.showMessageDialog(panel1, "Employee Details Updated", "Updation", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(panel1, "Record doesn't Exists", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Dbcon.Conn().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
