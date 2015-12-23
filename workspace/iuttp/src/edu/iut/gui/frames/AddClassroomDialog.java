package edu.iut.gui.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.iut.app.ApplicationSession;
import edu.iut.app.Classroom;
import edu.iut.app.Document;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;

public class AddClassroomDialog extends JDialog{
	DefaultListModel<String> classrooms;
	
	public AddClassroomDialog(JDialog container, DefaultListModel<String> classrooms){
		super(container,ApplicationSession.instance().getString("addclassroom"),true);
		this.classrooms=classrooms;
		this.setupUI();
		this.setSize(300, 80);
		this.setLocationRelativeTo(container);
		this.setVisible(true);
	}
	
	public void setupUI(){
		
		this.setLayout(new BorderLayout());
		
		JPanel panel = new JPanel(new GridLayout(1,2));
		
		panel.add(new JLabel(ApplicationSession.instance().getString("lastname")+" : " ));
		final JTextField roomName = new JTextField();
		panel.add(roomName);
		
		this.add(panel,BorderLayout.CENTER);
		
		JButton accept = new JButton(ApplicationSession.instance().getString("add"));
		accept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!(roomName.getText().equals(""))){
					Classroom newRoom = new Classroom(roomName.getText());
					
					ApplicationSession.instance().getAgenda().getClassrooms().add(newRoom);
						
					classrooms.addElement(newRoom.getClassRoomNumber());
					
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
