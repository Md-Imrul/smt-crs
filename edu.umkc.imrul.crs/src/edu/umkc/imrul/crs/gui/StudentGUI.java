package edu.umkc.imrul.crs.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.umkc.imrul.crs.confirmation.*;
import edu.umkc.imrul.crs.exception.*;;

public class StudentGUI extends JDialog implements ActionListener {
	
	
	 JLabel studentIDLabel;
	
	 JTextField studentIDText;
	 
	 JTextArea messagesTextArea;
	 JList list;
	 JButton AddBtn;
	 JButton MyListBtn;
	 JButton viewSyllabusBtn;
	 
	 String filename = "studentManageFile.txt";
	 
	 ArrayList<String> StudentEnrollList = new ArrayList<String>();
	  
	
  public StudentGUI(JFrame parent, String title, String [] message) {
    super(parent, title, true);
    if (parent != null) {
      Dimension parentSize = parent.getSize(); 
      Point p = parent.getLocation(); 
    }
    
    studentIDLabel = new JLabel("Student ID: ");
    studentIDText = new JTextField(15);
	
	//Add Student ID Text
    JPanel messagePane = new JPanel();
    messagePane.add(studentIDLabel);
    messagePane.add(studentIDText);
    messagePane.setBounds(200, 200, 800, 490);
   
    
    
    //Add list
   // String[] data = {"CS5590/490-Software Method and Tools","CS5520-Network ArchitectureI", "CS5521-Network ArchitectureII", "CS5551-Advanced Software Engineering", "CS5567-Machine Learning in Bilinformatics","CS5570-Architecture Of Database Management System","CS5573-Information Security and Assurance","CS5560-Knowledge Discovery and Management"};
    list = new JList(message);
    list.addListSelectionListener(new SelectionHandler());
    JScrollPane jsp = new JScrollPane(list);
    jsp.setSize(100, 100);
    
    

    //Add button
    JPanel buttonPane = new JPanel();
    AddBtn = new JButton("Add Class");
    MyListBtn = new JButton("My Class List");
    viewSyllabusBtn = new JButton("View Syllabus");
    buttonPane.add(AddBtn); 
    buttonPane.add(MyListBtn); 
    buttonPane.add(viewSyllabusBtn); 
    AddBtn.addActionListener(this);
    MyListBtn.addActionListener(this);
    viewSyllabusBtn.addActionListener(this);
    

    getContentPane().add(buttonPane, BorderLayout.SOUTH);
    getContentPane().add(jsp,BorderLayout.CENTER);
    getContentPane().add(messagePane,BorderLayout.NORTH);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack(); 
    setBounds(200, 200, 400, 400);
    setVisible(true);
   
    

    
    
    
 
  }
  public void actionPerformed(ActionEvent e) {
	  
	  if(e.getSource()==AddBtn)
		 {
			 System.out.println("you have chosen Add Class Button");
			 
			 String courseEntry = (String)(list.getSelectedValue());
			 
			 //Check if ID is in correct format
			if (studentIDText.getText().toString().length() == 0) {
				WarningBox idWarning1 = new WarningBox(new JFrame(),
						"ID Warning", "Please input ID");
				return;
			} else if ((studentIDText.getText().toString().length() != 8)
					&& (studentIDText.getText().toString().length() != 0)) {
				WarningBox idWarning2 = new WarningBox(new JFrame(),
						"ID Warning", "Student ID must be 8 digit");
				return;
			} else if ((courseEntry == "") || (courseEntry == null)) {
				WarningBox NoClassWarning = new WarningBox(new JFrame(),
						"No Class Warning", "Must choose one class to add");
				return;
			}				 
			 
			 
			int checkFlag=0;
			 
			 // Check if such student has choose more than 4 courses
//			 ReadFile readobj = new ReadFile();
//			 StudentEnrollList = readobj.ReadFile(filename);
			
			 if(StudentEnrollList.size()>0){
			 for(int i=0;i<StudentEnrollList.size();i++)
			 {
				 if(!StudentEnrollList.get(i).equals("")){
				 String []tempArray = StudentEnrollList.get(i).split(":");
				 String id = tempArray[0];
				 //student already select classes
				 if(id.equals(studentIDText.getText().toString()))
				 {
					 checkFlag=1;
					 String []Array = StudentEnrollList.get(i).split(":");
					 String ClassesString = Array[1];
					 String []ClassesArray = ClassesString.split(",");
					 
					 //class # > 4
					 if(ClassesArray.length>=4)
					 {
						 WarningBox stuWarning = new WarningBox(new JFrame(), "Enroll Warning", "Can't select more than 4 classes");
						 
						 return;
					 }
					 else if(ClassesString.contains(courseEntry))//choose the same class again 
					 {
						 WarningBox dupWarning = new WarningBox(new JFrame(), "Enroll Warning", "Can't select same class more than once");
					 }
					 else
					 {
						 ClassesString = ClassesString+","+courseEntry;
						 String updateString = Array[0]+":"+ClassesString;
						 StudentEnrollList.set(i, updateString);
//						 WriteFile writeobj = new WriteFile();
//						 writeobj.write(StudentEnrollList, filename);
						 
						 ConfirmationBox confirmation = new ConfirmationBox (new JFrame(), "Success Message", "Course Added Successfully");
						 return;
						 
					 }
				 }
				
			 }
			 }
			 
			 if(checkFlag==0)
			 {
				
					 String statement = studentIDText.getText().toString()+":"+courseEntry;
//					 WriteFile writeobj = new WriteFile();
//					 writeobj.append(statement, filename);
					 ConfirmationBox confirmation = new ConfirmationBox (new JFrame(), "Success Message", "Course Added Successfully");

			 }
			 
			 
			 }
			 else
			 {
				 String statement = studentIDText.getText().toString()+":"+courseEntry;
//				 WriteFile writeobj = new WriteFile();
//				 writeobj.writeWhenEmpty(statement, filename);
				 ConfirmationBox confirmation = new ConfirmationBox (new JFrame(), "Success Message", "Course Added Successfully");
				
			 }

		 }
	  
	  if(e.getSource()==MyListBtn)
	  {
		  if(studentIDText.getText().toString().length()==0)
			 {
				 WarningBox idWarning1 = new WarningBox(new JFrame(), "ID Warning", "Please input ID");
				 return;
			 }
			 else if((studentIDText.getText().toString().length()!=8)&&(studentIDText.getText().toString().length()!=0))
			 {
				 WarningBox idWarning2 = new WarningBox(new JFrame(), "ID Warning", "Student ID must be 8 digit");
				 return;
			 }
			 
		  
		  
		  
		  System.out.println("you have chosen My List Button");
		  JFrame frame = new JFrame("My List Access");
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
//	        StudentGUIMyList mylist = new StudentGUIMyList(new JFrame(), "My List", studentIDText.getText().toString());
		 
	  }
	  
	  if(e.getSource()==viewSyllabusBtn)
	  {
		  String courseEntry = (String) (list.getSelectedValue());
		  if((courseEntry == "")||(courseEntry == null))
			 {
				 WarningBox NoClassWarning = new WarningBox(new JFrame(), "No Class Warning", "Must choose one class to view");
				 return;
			 }
		  
		  File fileSyllabus = new File(courseEntry);
		  if(!fileSyllabus.exists())
		  {
			  WarningBox noSyllabusWarning = new WarningBox(new JFrame(), "No Syllabus Warning", "This class does not have syllabus yet"); 
		      return;
		  }
		  else if(fileSyllabus.exists())
		  {
//			  ViewSyllabusGUI dlg = new ViewSyllabusGUI(new JFrame(), "ViewSyllabus", courseEntry);
			  
		  }
		  
		 
	  }
    
  }
  
  private class SelectionHandler implements ListSelectionListener {

      @Override
      public void valueChanged(ListSelectionEvent e) {
          if (!e.getValueIsAdjusting()) {
              System.out.println(list.getSelectedValue());
          }
      }
  }
  

  
}