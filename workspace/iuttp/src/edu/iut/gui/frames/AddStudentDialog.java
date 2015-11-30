package edu.iut.gui.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.iut.app.ApplicationSession;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;

public class AddStudentDialog extends JDialog{
	
	DefaultListModel<String> students;
	
	public AddStudentDialog(JDialog container, DefaultListModel<String> students){
		super(container,"Ajouter un étudiant",true);
		this.students=students;
		this.setupUI();
		this.setSize(300, 150);
		this.setLocationRelativeTo(container);
		this.setVisible(true);
	}
	
	public void setupUI(){
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new GridLayout(4,2));
		
		panel.add(new JLabel("Prénom : "));
		final JTextField firstname = new JTextField();
		panel.add(firstname);
		
		panel.add(new JLabel("Nom : "));
		final JTextField lastname = new JTextField();
		panel.add(lastname);
		
		panel.add(new JLabel("Email : "));
		final JTextField email = new JTextField();
		panel.add(email);
		
		panel.add(new JLabel("Téléphone : "));
		final JTextField phone = new JTextField();
		panel.add(phone);
		
		this.add(panel,BorderLayout.CENTER);
		
		JButton accept = new JButton("Ajouter");
		accept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!(firstname.getText().equals("") || lastname.getText().equals("") || email.getText().equals("") || phone.getText().equals(""))){
					Person newStudent = new Person(PersonFunction.STUDENT,firstname.getText(),lastname.getText(),email.getText(),phone.getText());
					ApplicationSession.instance().getStudents().add(newStudent);	
					students.addElement(newStudent.toString());
					
					CloseDialog();
				}
				else{
					JOptionPane.showMessageDialog(null,"Veuillez remplir toutes les informations");				
				}
				
			}			
		});
		
		this.add(accept, BorderLayout.SOUTH);
	}
	
	public void CloseDialog(){
		super.dispose();
	}
}
