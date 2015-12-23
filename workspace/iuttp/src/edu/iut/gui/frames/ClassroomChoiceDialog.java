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
import edu.iut.app.Classroom;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;

/**
 * <b>StudentChoiceDialog est la classe graphique permettant d'associer une salle à une soutenance donnée.</b>
 */
public class ClassroomChoiceDialog extends JDialog{
	
	
	JButton classroomButton;
	ExamEvent exam;
	
	public ClassroomChoiceDialog(JDialog container, JButton classroom, ExamEvent exam){
		super(container,ApplicationSession.instance().getString("chooseclassroom"),true);
		this.classroomButton=classroom;
		this.exam=exam;
		
		this.setupUI();
		
		this.setSize(300, 200);
		this.setLocationRelativeTo(container);
		this.setVisible(true);
		
		
	}
	
	public void setupUI(){
		JPanel panel = new JPanel(new BorderLayout());
	
		final DefaultListModel<String> name = new DefaultListModel<>();
		
		for (Classroom room : ApplicationSession.instance().getAgenda().getClassrooms()){
		    name.addElement(room.getClassRoomNumber());
		}
		final JList<String>room = new JList<String>(name);
		room.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(!room.isSelectionEmpty() && e.getClickCount()==2){
					classroomButton.setText(room.getSelectedValue());
					for (Classroom classr : ApplicationSession.instance().getAgenda().getClassrooms()){
						if(classr.getClassRoomNumber().equals(room.getSelectedValue())){
							exam.setClassroom(classr);
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
		
		panel.add(room, BorderLayout.CENTER);
		
		JButton addClassroom = new JButton(ApplicationSession.instance().getString("add"));
		addClassroom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddClassroomDialog dialog = new AddClassroomDialog(ClassroomChoiceDialog.this,name);		
			}			
		});
		panel.add(addClassroom, BorderLayout.SOUTH);
		
		this.add(panel);
	}
	
	public void CloseDialog(){
	    super.dispose();
	}
	

}
