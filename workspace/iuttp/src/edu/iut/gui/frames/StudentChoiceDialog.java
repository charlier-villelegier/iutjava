package edu.iut.gui.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;

import edu.iut.app.ApplicationSession;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;

public class StudentChoiceDialog extends JDialog{
	
	
	JButton studentButton;
	ExamEvent exam;
	
	public StudentChoiceDialog(JDialog container, JButton student, ExamEvent exam){
		super(container,ApplicationSession.instance().getString("choosestudent"),true);
		this.studentButton=student;
		this.exam=exam;
		
		this.setupUI();
		
		this.setSize(300, 200);
		this.setLocationRelativeTo(container);
		this.setVisible(true);
		
		
	}
	
	public void setupUI(){
		JPanel panel = new JPanel(new BorderLayout());
	
		final DefaultListModel<String> name = new DefaultListModel<>();
		
		for (Person stud : ApplicationSession.instance().getAgenda().getStudents()){
		    if(ApplicationSession.instance().getAgenda().meetCriteriaFirstName(PersonFunction.STUDENT, stud.getFirstname()).isEmpty()){
		    	if(ApplicationSession.instance().getAgenda().meetCriteriaLastName(PersonFunction.STUDENT, stud.getLastname()).isEmpty()){
		    		name.addElement(stud.toString());
			    }
		    }
		}
		final JList<String>students = new JList<String>(name);
		students.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(!students.isSelectionEmpty() && e.getClickCount()==2){
					studentButton.setText(students.getSelectedValue());
					for (Person stud : ApplicationSession.instance().getAgenda().getStudents()){
						if(stud.toString().equals(students.getSelectedValue())){
							exam.setStudent(stud);
						}
					}
					CloseDialog();
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

					
		});
		
		panel.add(students, BorderLayout.CENTER);
		
		JButton addStudent = new JButton(ApplicationSession.instance().getString("addstudent"));
		addStudent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddPersonDialog dialog = new AddPersonDialog(StudentChoiceDialog.this,name,PersonFunction.STUDENT);		
			}			
		});
		panel.add(addStudent, BorderLayout.SOUTH);
		
		this.add(panel);
	}
	
	public void CloseDialog(){
	    super.dispose();
	}
	

}
