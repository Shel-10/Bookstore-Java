package bks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RemoveFrame {
	JFrame panel1;
	JLabel lbl_fname,lbl_lname,lbl_head,lbl_pass;
	JTextField txt_fname,txt_lname,txt_pass;
	PojoVerify ver=new PojoVerify();
	JButton remove;
	public RemoveFrame(String choice)
	{
		panel1=new JFrame();
		panel1.setLayout(null);
		
	
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
		
		lbl_lname=new JLabel("Password");
		lbl_lname.setBounds(100,140, 100, 30);
		panel1.add(lbl_lname);
		
		txt_lname=new JTextField();
		txt_lname.setBounds(200,140, 100, 30);
		panel1.add(txt_lname);
		
		remove=new JButton("Remove");
		remove.setBounds(200, 180, 100, 30);
		panel1.add(remove);
		
		remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!isBlankVerify()) {
				//database block
				Statement statment=null;
				try {
					statment=Dbcon.Conn().createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					statment.execute("delete from info where name='"+txt_fname.getText()+"'and sname='"+txt_lname.getText()+"'");
					
					statment.execute("delete from login where Username='"+txt_fname.getText()+"'and Password='"+txt_pass.getText()+"'");
					
					JOptionPane.showMessageDialog(panel1, "Employee Details removed", "Deletion", JOptionPane.INFORMATION_MESSAGE);
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
				}
				else {
					JOptionPane.showMessageDialog(panel1, "Blank fields not allowed", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		
		panel1.setBounds(300, 400,600,300);
		panel1.setResizable(false);
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
		if( txt_fname.getText().isBlank()||txt_lname.getText().isBlank()||txt_pass.getText().isBlank())
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

}
