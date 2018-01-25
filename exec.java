package experimentcount;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

import java.net.URL;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class exec extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame frame=new JFrame();
    JComboBox resttime;
    JComboBox expertime;
    JComboBox preexpertime;
    JTextField expernum;
    JLabel resttimelabel;
    JLabel expertimelabel;
    JLabel preexpertimelabel;
    JButton startb;
    JButton endb;
    JLabel countpanel;
    
    String rest=new String();
    String exper=new String();
    String preexper=new String();
    String expnum=new String();
    String currenttext=new String();
    int restt=0;
    int expert=0;
    int preexpert=0;
    int num=0;
    int ct=0;
    boolean stopsign=false;
    
	public exec()
	{
		frame.setLayout(null);
		
        String str1[]={"0","1","2","3","4","5"};
		preexpertime=new JComboBox(str1);
		frame.add(preexpertime);
		preexpertime.setBounds(30,50,100,30);
		
		preexpertimelabel=new JLabel("pre-exp time(sec)");
		frame.add(preexpertimelabel);
		preexpertimelabel.setBounds(30,15,120,30);
		
        String str2[]={"0","1","2","3","4","5","6","7","8","9"};
		expertime=new JComboBox(str2);
		frame.add(expertime);
		expertime.setBounds(160,50,100,30);
		
		expertimelabel=new JLabel("exp time(sec)");
		frame.add(expertimelabel);
		expertimelabel.setBounds(290,15,120,30);
		
        String str3[]={"0","1","2","3","4","5","6","7","8","9"};
		resttime=new JComboBox(str3);
		frame.add(resttime);
		resttime.setBounds(290,50,100,30);
		
		resttimelabel=new JLabel("rest time(sec)");
		frame.add(resttimelabel);
		resttimelabel.setBounds(160,15,120,30);
		
		expernum=new JTextField("number of exp");
		frame.add(expernum);
		expernum.setBounds(290,100,100,30);
		
		startb=new JButton("start");
		frame.add(startb);
		startb.setBounds(30,100,100,30);
		
		endb=new JButton("end");
		frame.add(endb);
		endb.setBounds(160,100,100,30);
		
		countpanel=new JLabel("Ready",JLabel.CENTER);
		countpanel.setFont(new Font("Arial", Font.PLAIN, 30));
		countpanel.setForeground(Color.red);
		frame.add(countpanel);
		countpanel.setBounds(420,15,145,145);
		
		frame.setSize(575,200); //base container size
		frame.setResizable(false); //game window cannot change size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// get resolution
		frame.setLocation(screenSize.width/2-285,screenSize.height/2-100);// determine the base container's position on the screen
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		startb.addActionListener(this);
		endb.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getSource()==startb)
		{
			preexper=(String)preexpertime.getSelectedItem();
			preexpert=Integer.parseInt(preexper);
			exper=(String)expertime.getSelectedItem();
			expert=Integer.parseInt(exper);
			rest=(String)resttime.getSelectedItem();
			restt=Integer.parseInt(rest);
			
			expnum=expernum.getText();
			for (int i = 0; i < expnum.length(); i++)
			{
				  if (!Character.isDigit(expnum.charAt(i)))
				  {
				      num=1;
				  }
				  else
				  {
					  num=Integer.parseInt(expnum);
				  }
			}
			
			experiment_count(preexpert,expert,restt,num);



		}
		if(e.getSource()==endb)
		{
			stopsign=true;
            num=0;

		}

	}
	
	public void experiment_count(int t1, int t2, int t3, int num)
	{
            ct=t1+(t2+1+t3+1)*num;//+1 for 'action' and 'rest'
            int []ctarray=new int[ct];
            System.out.println(ctarray.length);
            int initcount=0;
            
            for(int j=0; j<t1; j++)
            {
            	ctarray[j]=t1-j;
            }
            
            initcount=initcount+t1;
            
            for(int n=0;n<num;n++)
            {
            	for(int j=0;j<t2;j++)
            	{
            		ctarray[j+initcount]=(j+1)*10;
            	}
            	
            	ctarray[initcount+t2]=42;
            	initcount=initcount+t2+1;
            	
            	for(int j=0;j<t3;j++)
            	{
            		ctarray[j+initcount]=(j+1)*100;
            	}
            	
            	ctarray[initcount+t3]=24;
            	initcount=initcount+t3+1;
            }
            
            //init end
            
			Timer timer=new Timer();
			timer.schedule(new TimerTask(){

				public void run() {
					if(ct==0||stopsign==true)
					{						
					    rest=new String();
					    exper=new String();
					    preexper=new String();
					    expnum=new String();
					    currenttext=new String();
					    restt=0;
					    expert=0;
					    preexpert=0;
					    ct=0;
					    stopsign=false;
					    
						timer.cancel();
						
						countpanel.setText("Ready");
						countpanel.setFont(new Font("Arial", Font.PLAIN, 30));
						countpanel.setForeground(Color.red);
					}
					else
					{						
						int time=t1+(t2+1+t3+1)*num-ct;
						
						if(ctarray[time]>=0&&ctarray[time]<=6)
						{
						currenttext=Integer.toString(ctarray[time]);
						countpanel.setText(currenttext);
						countpanel.setFont(new Font("Arial", Font.PLAIN, 46));
						countpanel.setForeground(Color.red);						

						}
						
						if(ctarray[time]>=10&&ctarray[time]<=90&&ctarray[time]!=42&&ctarray[time]!=24)
						{
						currenttext=Integer.toString(ctarray[time]/10);
						countpanel.setText(currenttext);
						countpanel.setFont(new Font("Arial", Font.PLAIN, 46));
						countpanel.setForeground(Color.green);
						
						String file = "sound/"+Integer.toString(ctarray[time]/10)+".wav";
						URL cb;
						File f = new File(file);
						System.out.println(file);
						try {
							 cb=f.toURL();
							 AudioClip aau; 
							 aau = Applet.newAudioClip(cb); 
							 aau.play();//
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
						
						if(ctarray[time]>=100&&ctarray[time]<=900)
						{
						currenttext=Integer.toString(ctarray[time]/100);
						countpanel.setText(currenttext);
						countpanel.setFont(new Font("Arial", Font.PLAIN, 46));
						countpanel.setForeground(Color.blue);
						
						String file = "sound/"+Integer.toString(ctarray[time]/100)+".wav";
						URL cb;
						File f = new File(file);
						try {
							 cb=f.toURL();
							 AudioClip aau; 
							 aau = Applet.newAudioClip(cb); 
							 aau.play();//
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
						
						if(ctarray[time]==42)
						{
						currenttext="action";
						countpanel.setText(currenttext);
						countpanel.setFont(new Font("Arial", Font.PLAIN, 30));
						countpanel.setForeground(Color.green);
						
						String file = "sound/action.wav";
						URL cb;
						File f = new File(file);
						try {
							 cb=f.toURL();
							 AudioClip aau; 
							 aau = Applet.newAudioClip(cb); 
							 aau.play();//
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						}
						
						if(ctarray[time]==24)
						{
						currenttext="rest";
						countpanel.setText(currenttext);
						countpanel.setFont(new Font("Arial", Font.PLAIN, 30));
						countpanel.setForeground(Color.blue);
						
						String file = "sound/rest.wav";
						URL cb;
						File f = new File(file);
						try {
							 cb=f.toURL();
							 AudioClip aau; 
							 aau = Applet.newAudioClip(cb); 
							 aau.play();//
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}

						ct=ct-1;
					}
					
				}
			    				    
				}, 10, 1000);
					
	}

}
