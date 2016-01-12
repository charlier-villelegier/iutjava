package edu.iut.gui.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.iut.app.ApplicationSession;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;

/**
 * <b>AddPersonDialog est la classe graphique permettant l'ajout d'une personne (juri ou �tudiant) via le planing.</b>
 */
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
		final JTextField email = new JTextField("@u-psud.fr");
		final JTextField lastname = new JTextField();
		
		panel.add(new JLabel(ApplicationSession.instance().getString("firstname")+" : " ));
		final JTextField firstname = new JTextField();
		firstname.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				email.setText(firstname.getText().toLowerCase() + "." + lastname.getText().toLowerCase()+"@u-psud.fr");
				
			}
	         
	       });
		
		
		panel.add(firstname);
		
		panel.add(new JLabel(ApplicationSession.instance().getString("lastname")+" : " ));
		lastname.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				email.setText(firstname.getText().toLowerCase() + "." + lastname.getText().toLowerCase()+"@u-psud.fr");
				
			}
	         
	       });
		panel.add(lastname);
		
		panel.add(new JLabel("Email : "));
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
