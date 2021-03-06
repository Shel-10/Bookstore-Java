package bks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Clock extends JFrame implements ActionListener {
	
	String time;
	SimpleDateFormat dateFormat =new SimpleDateFormat("hh:mm:ss");
	SimpleDateFormat dateformat=new SimpleDateFormat("dd/MM/yyyy");
	JLabel ctime;
	JLabel d_time;
	public Clock()
	{
		/*
		 * setSize(400, 600); setVisible(true); setLayout(new FlowLayout());
		 */
		ctime=new JLabel();
		d_time=new JLabel();
		//add(d_time);
		updateClock();
		new Timer(1000,this).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		updateClock();
	}

	public void updateClock() {
		ctime.setText("Time  "+dateFormat.format(Calendar.getInstance().getTime()));
		d_time.setText("Date  "+dateformat.format(Calendar.getInstance().getTime()));
		
	}
	
	/*
	 * public static void main(String[] args) { new Clock(); }
	 */
}
