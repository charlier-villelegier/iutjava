package edu.iut.gui.frames;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.iut.app.ApplicationSession;
import edu.iut.app.Classroom;
import edu.iut.app.Document;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;

/**
 * <b>RessourceManagerFrame est la classe graphique permettant de g�rer (ajouter/modifier/supprimer)
 * l'ensemble des donn�es de l'application (�tudiants, jurys, etc).</b>
 */
public class RessourceManagerFrame extends JDialog{

	public RessourceManagerFrame(){
		super(ApplicationSession.instance().getMyFrame(),ApplicationSession.instance().getString("ressourcemanager"),true);
		
		this.setupUI();
		
		this.pack();
		this.setLocationRelativeTo(ApplicationSession.instance().getMyFrame());
		this.setVisible(true);
	}
	
	public void setupUI(){
		JTabbedPane tabPane = new JTabbedPane();
		
		/**Student Tab**/
		JPanel tabStudent = new JPanel(new BorderLayout());
		JPanel center = new JPanel(new GridLayout(1,2));
		final JTextField firstName = new JTextField();
		final JTextField lastName = new JTextField();
		final JTextField email = new JTextField();
		final JTextField phone = new JTextField();
		final JButton save = new JButton(ApplicationSession.instance().getString("save"));
		JButton delete = new JButton(ApplicationSession.instance().getString("delete"));
		JButton add = new JButton(ApplicationSession.instance().getString("add"));
		
		final DefaultListModel<String> studentModele = new DefaultListModel<>();
		for(Person stud : ApplicationSession.instance().getAgenda().getStudents()){
			studentModele.addElement(stud.toString());
		}
		
		final JList<String>student = new JList<String>(studentModele);
		student.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!student.isSelectionEmpty()){
                	Person changingPerson = ApplicationSession.instance().getAgenda().getStudents().get(student.getSelectedIndex());
                	firstName.setText(changingPerson.getFirstname());
                	lastName.setText(changingPerson.getLastname());
                	email.setText(changingPerson.getEmail());
                	phone.setText(changingPerson.getPhone());
                }
            }
        });
		center.add(student);
	
		JPanel panelModif = new JPanel(new GridLayout(4,2));
		panelModif.add(new JLabel(ApplicationSession.instance().getString("firstname")+" : " ));
		panelModif.add(firstName);
		firstName.addKeyListener(new KeyListener()
        {
            @Override public void keyTyped(KeyEvent e) {if(!student.isSelectionEmpty())save.setEnabled(true);}
            @Override public void keyPressed(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
        });
		
		panelModif.add(new JLabel(ApplicationSession.instance().getString("lastname")+" : " ));
		panelModif.add(lastName);
		lastName.addKeyListener(new KeyListener()
        {
            @Override public void keyTyped(KeyEvent e) {if(!student.isSelectionEmpty())save.setEnabled(true);}
            @Override public void keyPressed(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
        });
		
		panelModif.add(new JLabel("Email : "));
		panelModif.add(email);
		email.addKeyListener(new KeyListener()
        {
            @Override public void keyTyped(KeyEvent e) {if(!student.isSelectionEmpty())save.setEnabled(true);}
            @Override public void keyPressed(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
        });
		
		panelModif.add(new JLabel(ApplicationSession.instance().getString("phone")+" : " ));
		panelModif.add(phone);
		phone.addKeyListener(new KeyListener()
        {
            @Override public void keyTyped(KeyEvent e) {if(!student.isSelectionEmpty())save.setEnabled(true);}
            @Override public void keyPressed(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
        });
		
		center.add(panelModif);
		
		JPanel panelSouth = new JPanel(new GridLayout(1,3));
		
		panelSouth.add(delete);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!student.isSelectionEmpty()){
					if(JOptionPane.showConfirmDialog (RessourceManagerFrame.this, ApplicationSession.instance().getString("deletemessage"),"Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
						LinkedList<ExamEvent> exam = ApplicationSession.instance().getAgenda().meetCriteriaStudent(ApplicationSession.instance().getAgenda().getStudents().get(student.getSelectedIndex()));
						if(!exam.isEmpty()){
							if(JOptionPane.showConfirmDialog(RessourceManagerFrame.this, ApplicationSession.instance().getString("examattachedstudent"), "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
								ApplicationSession.instance().getAgenda().remove(exam.getFirst());
								ApplicationSession.instance().getAgenda().getStudents().remove(student.getSelectedIndex());
								studentModele.remove(student.getSelectedIndex());
								firstName.setText("");
								lastName.setText("");
								email.setText("");
								phone.setText("");
								ApplicationSession.instance().getMyFrame().majView();
							}
						}
						else{
							ApplicationSession.instance().getAgenda().getStudents().remove(student.getSelectedIndex());
							studentModele.remove(student.getSelectedIndex());
							firstName.setText("");
							lastName.setText("");
							email.setText("");
							phone.setText("");
							ApplicationSession.instance().getMyFrame().majView();
						}
					}
				}
				
			}			
		});
		
		panelSouth.add(add);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddPersonDialog addStudent = new AddPersonDialog(RessourceManagerFrame.this, studentModele, PersonFunction.STUDENT);
			}			
		});
		panelSouth.add(save);
		save.setEnabled(false);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Person changingPerson = ApplicationSession.instance().getAgenda().getStudents().get(student.getSelectedIndex());
            	changingPerson.setFirstname(firstName.getText());
            	changingPerson.setLastname(lastName.getText());
            	changingPerson.setEmail(email.getText());
            	changingPerson.setPhone(phone.getText());
            	studentModele.set(student.getSelectedIndex(), changingPerson.toString());
            	save.setEnabled(false);
            	ApplicationSession.instance().getMyFrame().majView();
			}			
		});
		
		
		tabStudent.add(center, BorderLayout.CENTER);
		tabStudent.add(panelSouth, BorderLayout.SOUTH);
		
		tabPane.add(ApplicationSession.instance().getString("student"), tabStudent);
		
		/**Jury tab**/
		JPanel tabJury = new JPanel(new BorderLayout());
		center = new JPanel(new GridLayout(1,2));
		final JTextField firstNameJury = new JTextField();
		final JTextField lastNameJury = new JTextField();
		final JTextField emailJury = new JTextField();
		final JTextField phoneJury = new JTextField();
		final JButton saveJury = new JButton(ApplicationSession.instance().getString("save"));
		delete = new JButton(ApplicationSession.instance().getString("delete"));
		add = new JButton(ApplicationSession.instance().getString("add"));
		
		final DefaultListModel<String> juryModele = new DefaultListModel<>();
		for(Person jur : ApplicationSession.instance().getAgenda().getJurys()){
			juryModele.addElement(jur.toString());
		}
		
		final JList<String>jury = new JList<String>(juryModele);
		jury.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!jury.isSelectionEmpty()){
                	Person changingPerson = ApplicationSession.instance().getAgenda().getJurys().get(jury.getSelectedIndex());
                	firstNameJury.setText(changingPerson.getFirstname());
                	lastNameJury.setText(changingPerson.getLastname());
                	emailJury.setText(changingPerson.getEmail());
                	phoneJury.setText(changingPerson.getPhone());
                }
            }
        });
		center.add(jury);
	
		panelModif = new JPanel(new GridLayout(4,2));
		panelModif.add(new JLabel(ApplicationSession.instance().getString("firstname")+" : " ));
		panelModif.add(firstNameJury);
		firstNameJury.addKeyListener(new KeyListener()
        {
            @Override public void keyTyped(KeyEvent e) {if(!jury.isSelectionEmpty())saveJury.setEnabled(true);}
            @Override public void keyPressed(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
        });
		
		panelModif.add(new JLabel(ApplicationSession.instance().getString("lastname")+" : " ));
		panelModif.add(lastNameJury);
		lastNameJury.addKeyListener(new KeyListener()
        {
            @Override public void keyTyped(KeyEvent e) {if(!jury.isSelectionEmpty())saveJury.setEnabled(true);}
            @Override public void keyPressed(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
        });
		
		panelModif.add(new JLabel("Email : "));
		panelModif.add(emailJury);
		emailJury.addKeyListener(new KeyListener()
        {
            @Override public void keyTyped(KeyEvent e) {if(!jury.isSelectionEmpty())saveJury.setEnabled(true);}
            @Override public void keyPressed(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
        });
		
		panelModif.add(new JLabel(ApplicationSession.instance().getString("phone")+" : " ));
		panelModif.add(phoneJury);
		phoneJury.addKeyListener(new KeyListener()
        {
            @Override public void keyTyped(KeyEvent e) {if(!jury.isSelectionEmpty())saveJury.setEnabled(true);}
            @Override public void keyPressed(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
        });
		
		center.add(panelModif);
		
		panelSouth = new JPanel(new GridLayout(1,3));
		
		panelSouth.add(delete);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!jury.isSelectionEmpty()){
					if(JOptionPane.showConfirmDialog (RessourceManagerFrame.this, ApplicationSession.instance().getString("deletemessage"),"Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
						LinkedList<ExamEvent> exam = ApplicationSession.instance().getAgenda().meetCriteriaJury(ApplicationSession.instance().getAgenda().getJurys().get(jury.getSelectedIndex()));
						
						if(!exam.isEmpty()){
							if(JOptionPane.showConfirmDialog(RessourceManagerFrame.this, ApplicationSession.instance().getString("examattachedjury"), "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
								for(ExamEvent examen : exam){
									examen.getJury().remove(ApplicationSession.instance().getAgenda().getJurys().get(jury.getSelectedIndex()));
								}
								ApplicationSession.instance().getAgenda().getJurys().remove(jury.getSelectedIndex());
								juryModele.remove(jury.getSelectedIndex());
								firstNameJury.setText("");
								lastNameJury.setText("");
								emailJury.setText("");
								phoneJury.setText("");
								ApplicationSession.instance().getMyFrame().majView();
							}
						}
						else{
							ApplicationSession.instance().getAgenda().getJurys().remove(jury.getSelectedIndex());
							juryModele.remove(jury.getSelectedIndex());
							firstNameJury.setText("");
							lastNameJury.setText("");
							emailJury.setText("");
							phoneJury.setText("");
							ApplicationSession.instance().getMyFrame().majView();
						}
					}
				}
				
			}			
		});
		
		panelSouth.add(add);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddPersonDialog addJury = new AddPersonDialog(RessourceManagerFrame.this, juryModele, PersonFunction.JURY);
			}			
		});
		panelSouth.add(saveJury);
		saveJury.setEnabled(false);
		saveJury.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Person changingPerson = ApplicationSession.instance().getAgenda().getJurys().get(jury.getSelectedIndex());
            	changingPerson.setFirstname(firstNameJury.getText());
            	changingPerson.setLastname(lastNameJury.getText());
            	changingPerson.setEmail(emailJury.getText());
            	changingPerson.setPhone(phoneJury.getText());
            	juryModele.set(jury.getSelectedIndex(), changingPerson.toString());
            	saveJury.setEnabled(false);
            	ApplicationSession.instance().getMyFrame().majView();
			}			
		});
		
		
		tabJury.add(center, BorderLayout.CENTER);
		tabJury.add(panelSouth, BorderLayout.SOUTH);
		
		tabPane.add(ApplicationSession.instance().getString("jury"), tabJury);
		
		/**Document tab**/
		JPanel tabDoc = new JPanel(new BorderLayout());
		center = new JPanel(new GridLayout(1,2));
		final JTextField docName = new JTextField();
		final JButton saveDoc = new JButton(ApplicationSession.instance().getString("save"));
		delete = new JButton(ApplicationSession.instance().getString("delete"));
		add = new JButton(ApplicationSession.instance().getString("add"));
		
		
		final DefaultListModel<String> docModele = new DefaultListModel<>();
		for(Document document : ApplicationSession.instance().getAgenda().getDocuments()){
			docModele.addElement(document.getDocumentURI());
		}
		final JList<String>doc = new JList<String>(docModele);

		doc.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!doc.isSelectionEmpty()){
                	Document changingDoc = ApplicationSession.instance().getAgenda().getDocuments().get(doc.getSelectedIndex());
                	docName.setText(changingDoc.getDocumentURI());
                	
                }
            }
        });
		center.add(doc);
		
		panelModif = new JPanel(new GridLayout(1,2));
		panelModif.add(new JLabel(ApplicationSession.instance().getString("lastname")+" : " ));
		panelModif.add(docName);
		docName.addKeyListener(new KeyListener()
        {
            @Override public void keyTyped(KeyEvent e) {if(!doc.isSelectionEmpty())saveDoc.setEnabled(true);}
            @Override public void keyPressed(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
        });
		
		center.add(panelModif);
		
		panelSouth = new JPanel(new GridLayout(1,3));
		
		panelSouth.add(delete);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!doc.isSelectionEmpty()){
					if(JOptionPane.showConfirmDialog (RessourceManagerFrame.this, ApplicationSession.instance().getString("deletemessage"),"Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
						LinkedList<ExamEvent> exam = ApplicationSession.instance().getAgenda().meetCriteriaDoc(ApplicationSession.instance().getAgenda().getDocuments().get(doc.getSelectedIndex()));
						
						if(!exam.isEmpty()){
							if(JOptionPane.showConfirmDialog(RessourceManagerFrame.this, ApplicationSession.instance().getString("examattacheddoc"), "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
								for(ExamEvent examen : exam){
									examen.getDocuments().remove(ApplicationSession.instance().getAgenda().getDocuments().get(doc.getSelectedIndex()));
								}
								ApplicationSession.instance().getAgenda().getDocuments().remove(doc.getSelectedIndex());
								docModele.remove(doc.getSelectedIndex());
								docName.setText("");
								ApplicationSession.instance().getMyFrame().majView();
							}
						}
						else{
							ApplicationSession.instance().getAgenda().getDocuments().remove(doc.getSelectedIndex());
							docModele.remove(doc.getSelectedIndex());
							docName.setText("");
							ApplicationSession.instance().getMyFrame().majView();
						}
					}
				}
				
			}			
		});
		
		panelSouth.add(add);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddDocumentDialog addDoc = new AddDocumentDialog(RessourceManagerFrame.this, docModele);
			}			
		});
		panelSouth.add(saveDoc);
		saveDoc.setEnabled(false);
		saveDoc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Document changingDoc = ApplicationSession.instance().getAgenda().getDocuments().get(doc.getSelectedIndex());
				changingDoc.setDocumentURI(docName.getText());
            	docModele.set(doc.getSelectedIndex(), changingDoc.getDocumentURI());
            	saveDoc.setEnabled(false);
            	ApplicationSession.instance().getMyFrame().majView();
			}			
		});
		
		
		tabDoc.add(center, BorderLayout.CENTER);
		tabDoc.add(panelSouth, BorderLayout.SOUTH);
		
		tabPane.add(ApplicationSession.instance().getString("document"), tabDoc);
		
		/**Classroom tab**/
		JPanel tabClassroom = new JPanel(new BorderLayout());
		center = new JPanel(new GridLayout(1,2));
		final JTextField roomName = new JTextField();
		final JButton saveRoom = new JButton(ApplicationSession.instance().getString("save"));
		delete = new JButton(ApplicationSession.instance().getString("delete"));
		add = new JButton(ApplicationSession.instance().getString("add"));
		
		
		final DefaultListModel<String> roomModele = new DefaultListModel<>();
		for(Classroom classr : ApplicationSession.instance().getAgenda().getClassrooms()){
			roomModele.addElement(classr.getClassRoomNumber());
		}
		final JList<String>room = new JList<String>(roomModele);

		room.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!room.isSelectionEmpty()){
                	Classroom changingRoom = ApplicationSession.instance().getAgenda().getClassrooms().get(room.getSelectedIndex());
                	roomName.setText(changingRoom.getClassRoomNumber());
                	
                }
            }
        });
		center.add(room);
		
		panelModif = new JPanel(new GridLayout(1,2));
		panelModif.add(new JLabel(ApplicationSession.instance().getString("lastname")+" : " ));
		panelModif.add(roomName);
		roomName.addKeyListener(new KeyListener()
        {
            @Override public void keyTyped(KeyEvent e) {if(!room.isSelectionEmpty())saveRoom.setEnabled(true);}
            @Override public void keyPressed(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
        });
		
		center.add(panelModif);
		
		panelSouth = new JPanel(new GridLayout(1,3));
		
		panelSouth.add(delete);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!room.isSelectionEmpty()){
					if(JOptionPane.showConfirmDialog (RessourceManagerFrame.this, ApplicationSession.instance().getString("deletemessage"),"Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
						LinkedList<ExamEvent> exam = ApplicationSession.instance().getAgenda().meetCriteriaClassroom(ApplicationSession.instance().getAgenda().getClassrooms().get(room.getSelectedIndex()));
						
						if(!exam.isEmpty()){
							if(JOptionPane.showConfirmDialog(RessourceManagerFrame.this, ApplicationSession.instance().getString("examattachedroom"), "Confirmation", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
								for(ExamEvent examen : exam){
									examen.setClassroom(null);
								}
								ApplicationSession.instance().getAgenda().getClassrooms().remove(room.getSelectedIndex());
								roomModele.remove(room.getSelectedIndex());
								roomName.setText("");
								ApplicationSession.instance().getMyFrame().majView();
							}
						}
						else{
							ApplicationSession.instance().getAgenda().getClassrooms().remove(room.getSelectedIndex());
							roomModele.remove(room.getSelectedIndex());
							roomName.setText("");
							ApplicationSession.instance().getMyFrame().majView();
						}
					}
				}
				
			}			
		});
		
		panelSouth.add(add);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddClassroomDialog addRoom = new AddClassroomDialog(RessourceManagerFrame.this, roomModele);
			}			
		});
		panelSouth.add(saveRoom);
		saveRoom.setEnabled(false);
		saveRoom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Classroom changingRoom = ApplicationSession.instance().getAgenda().getClassrooms().get(room.getSelectedIndex());
				changingRoom.setClassRoomNumber(roomName.getText());
            	roomModele.set(room.getSelectedIndex(), changingRoom.getClassRoomNumber());
            	saveRoom.setEnabled(false);
            	ApplicationSession.instance().getMyFrame().majView();
			}			
		});
		
		
		tabClassroom.add(center, BorderLayout.CENTER);
		tabClassroom.add(panelSouth, BorderLayout.SOUTH);
		
		tabPane.add(ApplicationSession.instance().getString("classroom"), tabClassroom);
		
		
		/**Ajout du tabPane remplis**/
		this.add(tabPane);
		
		
	}
}
