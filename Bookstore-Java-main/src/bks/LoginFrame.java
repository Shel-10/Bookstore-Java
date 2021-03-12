package bks;

import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class LoginFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4036874179043060747L;
	JLabel lbl_user,lbl_pass;
	JTextField txt_user,txt_pass;
	JButton btn_login,btn_refresh;
	JRadioButton rbtn;
	public LoginFrame() {
		
		setLayout(null);
		//setSize(400,500);
		setResizable(false);
		lbl_user=new JLabel("Username");
		lbl_user.setBounds(100, 150, 100, 50);
		lbl_pass=new JLabel("Password");
		lbl_pass.setBounds(100, 200, 100, 50);
		
		
		try {
			setContentPane(new JLabel(new ImageIcon(LoginFrame.class.getResource("../loginpage.png"))));
			
		} catch (NullPointerException e) {
			// TODO: handle exception
		}
		
		
		
		
		txt_user=new JTextField();
		txt_user.setBounds(180, 160, 100, 30);
		
		txt_pass=new JTextField();
		txt_pass.setBounds(180, 210, 100, 30);
		
		rbtn=new JRadioButton();
		rbtn.setText("Admin");
		rbtn.setBounds(180, 245, 100, 30);
		
		btn_login=new JButton("Login");
		btn_login.setBounds(135,300 ,100, 30);
		
		
		
		
		
		/* DATABASE CONNECTION AHEAD!!!!! */
		
		
		btn_login.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Statement stat=null;
				try 
				{
					stat = Dbcon.Conn().createStatement();
				} catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				ResultSet rs=null;
				
				
				  try { 
					  rs=stat.executeQuery("select * from login where Username = '"+txt_user.getText()+"'");
					  } 
				  catch (SQLException e1) 
				  	{ 
					  // TODO Auto-generated catch block 
					  	e1.printStackTrace(); 
					 }
				 
				try 
				{
					
					
					while(rs.next())
					{
						if(txt_user.getText().equalsIgnoreCase(rs.getString("Username")) && txt_pass.getText().equals(rs.getString("Password")))
						{
							if(rbtn.isSelected())
							{
								if(rs.getString("Status").equals("Admin"))
								{
									dispose();
									new Admin(txt_user.getText());
								}
								else
								{
									JOptionPane.showMessageDialog(getComponent(0),"Not Authorized","ERROR", JOptionPane.ERROR_MESSAGE);
								}
							}
							else
							{
								dispose();
								new User(txt_user.getText());
							}
						}
						else
						{
							JOptionPane.showMessageDialog(getComponent(0),"Incorrect Credentials","WARNING",JOptionPane.WARNING_MESSAGE);
						}
					}
				} catch (SQLException e1)
				{
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
			
			
		});
		
		
		
		
		
		
		/* DATABASE CONNECTION ENDS!!!!! */
		
		

		add(lbl_user);
		add(lbl_pass);
		add(txt_user);
		add(txt_pass);
		add(rbtn);
		add(btn_login);
		
		setBounds(500,200, 400, 500);
		
		
		setVisible(true);
		
	}
	
}
