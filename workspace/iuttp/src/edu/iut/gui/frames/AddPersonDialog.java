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

public class AddPersonDialog extends JDialog{
	
	DefaultListModel<String> person;
	PersonFunction function;
	
	public AddPersonDialog(JDialog container, DefaultListModel<String> person, PersonFunction function){
		super(container,ApplicationSession.instance().getString("addperson"),true);
		this.person=person;
		this.function=function;
		this.setupUI();
		this.setSize(300, 150);
		this.setLocationRelativeTo(container);
		this.setVisible(true);
	}
	
	public void setupUI(){
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new GridLayout(4,2));
		
		panel.add(new JLabel(ApplicationSession.instance().getString("firstname")+" : " ));
		final JTextField firstname = new JTextField();
		panel.add(firstname);
		
		panel.add(new JLabel(ApplicationSession.instance().getString("lastname")+" : " ));
		final JTextField lastname = new JTextField();
		panel.add(lastname);
		
		panel.add(new JLabel("Email : "));
		final JTextField email = new JTextField();
		panel.add(email);
		
		panel.add(new JLabel(ApplicationSession.instance().getString("phone")+" : " ));
		final JTextField phone = new JTextField();
		panel.add(phone);
		
		this.add(panel,BorderLayout.CENTER);
		
		JButton accept = new JButton(ApplicationSession.instance().getString("add"));
		accept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!(firstname.getText().equals("") || lastname.getText().equals("") || email.getText().equals("") || phone.getText().equals(""))){
					Person newPerson = new Person(function,firstname.getText(),lastname.getText(),email.getText(),phone.getText());
					
					if(function==PersonFunction.STUDENT){
						ApplicationSession.instance().getAgenda().getStudents().add(newPerson);
					}
					else if(function==PersonFunction.JURY){
						ApplicationSession.instance().getAgenda().getJurys().add(newPerson);
					}
						
					person.addElement(newPerson.toString());
					
					CloseDialog();
				}
				else{
					JOptionPane.showMessageDialog(null,ApplicationSession.instance().getString("fillall"));				
				}
				
			}			
		});
		
		this.add(accept, BorderLayout.SOUTH);
	}
	
	public void CloseDialog(){
		super.dispose();
	}
}
