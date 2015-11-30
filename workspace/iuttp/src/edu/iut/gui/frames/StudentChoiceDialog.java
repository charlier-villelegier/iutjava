package edu.iut.gui.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;

import edu.iut.app.ApplicationSession;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;

public class StudentChoiceDialog extends JDialog{
	
	
	JButton studentButton;
	ExamEvent exam;
	
	public StudentChoiceDialog(JDialog container, JButton student, ExamEvent exam){
		super(container,"Choisir un étudiant",true);
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
		
		for (Person stud : ApplicationSession.instance().getStudents())
		    name.addElement(stud.toString());
	
		final JList<String>students = new JList<String>(name);
		students.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(!students.isSelectionEmpty() && e.getClickCount()==2){
					studentButton.setText(students.getSelectedValue());
					exam.setStudent(ApplicationSession.instance().getStudents().get(students.getSelectedIndex()));
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
		
		JButton addStudent = new JButton("Ajouter un étudiant");
		addStudent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddStudentDialog dialog = new AddStudentDialog(StudentChoiceDialog.this,name);		
			}			
		});
		panel.add(addStudent, BorderLayout.SOUTH);
		
		this.add(panel);
	}
	
	public void CloseDialog(){
	    super.dispose();
	}
	

}
