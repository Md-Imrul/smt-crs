package edu.umkc.imrul.crs.gui;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import edu.umkc.imrul.crs.datasources.IDataSource;
import edu.umkc.imrul.crs.datasources.TextFileDataSource;

//import Operation.ReadFile;



public class MainGUI extends JFrame implements ActionListener {

	
	static int c;
	
	String beginPeriod;
	String endPeriod ;
	//String NQFfilename ;
	
	
	 JButton Button = new JButton("Student");
	  JButton Button2 = new JButton("Instructor");
	  JButton Button3 = new JButton("Administrator");
	  
	  
	   JLabel label1;
	   
	   JLabel patientAgeLabel;
	   JLabel patientGenderLabel;
	   JLabel patientAttriRecordTimeLabel;
	   
	   JTextField patientAgeText;
	   JTextField patientGenderText;
	   JTextField patientAttriRecordTimeText;
	   
	   
	   BufferedImage myPicture;
	 
	
	JMenuBar menu;
	 JMenu file;
	 JMenuItem closeMenu;
      
	 String studentfile = "studentManageFile.txt";
	 String commonfilelist = "commonClassList";
	// String adminlist = "AdminClassList";
	 
	 
	 
	
	
	 public MainGUI() {		
		 
		  menu = new JMenuBar();
		  file = new JMenu("Class Enrollment System");
		  closeMenu = new JMenuItem("Close");
		  closeMenu.addActionListener(this);

		  Button.addActionListener(this);
		  Button2.addActionListener(this);
		  Button3.addActionListener(this);
		
		  
		  JPanel closePanel = new JPanel();
		  JPanel closePanel2 = new JPanel();
		  closePanel.setLayout(new FlowLayout());

		  closePanel.add(Button);
		  closePanel.add(Button2);
		  closePanel.add(Button3);
		  

		File pic=new File("./umkc.jpg");
		   
        Icon icon=new ImageIcon(pic.toString());
	
		 JLabel picLabel = new JLabel(icon);
		
		
		 
		  //Font font=new  Font("Serief", Font.BOLD, 22);
		  //picLabel.setFont(font);
		//  picLabel.setForeground(Color.RED);
	        
	      
		  closePanel2.add(picLabel);
		
		  this.add(closePanel2,BorderLayout.SOUTH);
		  this.add(closePanel, BorderLayout.CENTER);
		  this.add(menu, BorderLayout.NORTH);
		  menu.add(file);
		  file.add(closeMenu);
		  this.setBounds(200, 200, 400, 400);
		  this.setVisible(true);
		  this.setResizable(false);
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	
	
			
	}
	
	
	 public void actionPerformed1(ActionEvent e) {
		 		
		  System.exit(0);
		 }	
	 /**
	  * Action on Student button.
	  */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 if(e.getSource()==Button)
		 {
			 System.out.println("you have chosen student option");
			  JFrame frame = new JFrame("Student Access");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        
//		        ReadFile readfile = new ReadFile();
		        IDataSource dataSource = new TextFileDataSource();
		        List<String> commonfilelistArray = new ArrayList();
//		        commonfilelistArray = readfile.ReadFile(commonfilelist);
		        commonfilelistArray = dataSource.getAllCourses();
		        
		        String[] startInit = new String[commonfilelistArray.size()];
		        startInit = commonfilelistArray.toArray(startInit);
		        
		        /*
		        String send = "";
		        for(int op=0;op<startInit.length;op++)
		        {
		        	if(!"".equals(startInit[op])){
		        	String []temp = startInit[op].split("\\[");
		        	String []temp2 = temp[1].split("\\]");
		        	send = send + temp2[0]+",";
		        	}
		        }
		        
		        String [] startInitTrue = send.split(",");
		        */
		        
		        
		        StudentGUI dlg = new StudentGUI(new JFrame(), "Student Account", startInit);
			 
		 }
		 if(e.getSource()==Button2)
		 {
			 System.out.println("you have chosen instructor option");
//			  JFrame frame = new JFrame("Instructor Access");
//		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		        
//		        ReadFile readfile = new ReadFile();
//		        ArrayList <String> insfilelistArray = new ArrayList();
//		        insfilelistArray = readfile.ReadFile(commonfilelist);
//
//		      
//
//		        String[] startInit1 = new String[insfilelistArray.size()];
//		        startInit1 = insfilelistArray.toArray(startInit1);
//		        
//		       
//		        InstructorGUI dlg = new InstructorGUI(new JFrame(), "Instructor Account", startInit1);
		 }
		 if(e.getSource()==Button3)
		 {
			 System.out.println("you have chosen administrator option");
//			  JFrame frame = new JFrame("Admin Access");
//		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		        
//		        ReadFile readfile = new ReadFile();
//		        ArrayList <String> adminfilelistArray = new ArrayList();
//		        adminfilelistArray = readfile.ReadFile(commonfilelist);
//		        
//		        String[] startInit = new String[adminfilelistArray.size()];
//		        startInit = adminfilelistArray.toArray(startInit);
//
//		        AdminGUI dlg = new AdminGUI(new JFrame(), "Admin Account", startInit);
			 
		 }
		 
	}
	
	
	
	
	
	

}
