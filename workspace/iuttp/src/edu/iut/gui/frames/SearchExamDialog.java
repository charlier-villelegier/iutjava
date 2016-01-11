package edu.iut.gui.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.iut.app.ApplicationSession;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;

public class SearchExamDialog extends JDialog{
	
	public SearchExamDialog(){
		super(ApplicationSession.instance().getMyFrame(),ApplicationSession.instance().getString("searchexam"),true);
		this.setupUI();
		this.setSize(500,300);
		this.setLocationRelativeTo(ApplicationSession.instance().getMyFrame());
		this.setVisible(true);
	}
	
	public void setupUI(){
		this.setLayout(new BorderLayout());
		JPanel top = new JPanel(new GridLayout(1,2));
		
		final DefaultListModel<String> examModele = new DefaultListModel<>();
		final JList<String>exam = new JList<String>(examModele);
		final LinkedList<ExamEvent> goodExam = new LinkedList<ExamEvent>();
		
		for (ExamEvent ex : ApplicationSession.instance().getAgenda()){
			goodExam.add(ex);
		    
			Calendar c = Calendar.getInstance();
			c.setTime(ex.getExamDate());
			
			String date =   ApplicationSession.instance().getDays()[(c.get(Calendar.DAY_OF_WEEK)+5)%7] 
		    				+ " " 
		    				+ c.get(Calendar.DAY_OF_MONTH) 
		    				+ " " 
		    				+ ApplicationSession.instance().getMonths()[c.get(Calendar.MONTH)]
		    				+ " - "
		    				+ c.get(Calendar.HOUR_OF_DAY)
		    				+ "h";
			
			String jury = "";
			if(ex.getJury()!=null){
				jury+=" || Jury : ";
				for(Person p : ex.getJury()){
					jury+= p.toString();
					if (ex.getJury().lastIndexOf(p)!=ex.getJury().size()-1){
						jury+= " - ";
					}
				}
			}
			
			
			examModele.addElement(date + " : " + ex.getStudent().toString() + jury);
		}
		
		
		
		final JComboBox<String> juryOrStudent = new JComboBox<String>();
		final JTextField nameText = new JTextField();
		
		nameText.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				LinkedList<ExamEvent> goodExamFirstName = null;
				LinkedList<ExamEvent> goodExamLastName = null;
				
				//On vide les listes
				examModele.removeAllElements();
				goodExam.clear();
				
				//On séléctionne les bons exam suivant jury ou étudiant
				if(juryOrStudent.getSelectedItem().toString().equals(ApplicationSession.instance().getString("student"))){
					goodExamFirstName = ApplicationSession.instance().getAgenda().meetCriteriaFirstName(PersonFunction.STUDENT, nameText.getText());
					goodExamLastName = ApplicationSession.instance().getAgenda().meetCriteriaLastName(PersonFunction.STUDENT, nameText.getText());
					
				}
				else if(juryOrStudent.getSelectedItem().toString().equals(ApplicationSession.instance().getString("jury"))){
					goodExamFirstName = ApplicationSession.instance().getAgenda().meetCriteriaFirstName(PersonFunction.JURY, nameText.getText());
					goodExamLastName = ApplicationSession.instance().getAgenda().meetCriteriaLastName(PersonFunction.JURY, nameText.getText());
					
				}
				
				//On retire les doublons
				goodExam.addAll(goodExamFirstName);
				goodExam.addAll(goodExamLastName);
				Set<ExamEvent> hs = new HashSet<>();
				hs.addAll(goodExam);
				goodExam.clear();
				goodExam.addAll(hs);
				
				//On trie par la date
				Collections.sort(goodExam);
				
				//On affiche les bons exam
				for(ExamEvent ex : goodExam){
					Calendar c = Calendar.getInstance();
					c.setTime(ex.getExamDate());
					
					String date =   ApplicationSession.instance().getDays()[(c.get(Calendar.DAY_OF_WEEK)+5)%7] 
				    				+ " " 
				    				+ c.get(Calendar.DAY_OF_MONTH) 
				    				+ " " 
				    				+ ApplicationSession.instance().getMonths()[c.get(Calendar.MONTH)]
				    				+ " - "
				    				+ c.get(Calendar.HOUR_OF_DAY)
				    				+ "h";
					
					
					String jury = "";
					if(ex.getJury()!=null){
						 jury+=" || Jury : ";
						 for(Person p : ex.getJury()){
							 jury+= p.toString();
							 if (ex.getJury().lastIndexOf(p)!=ex.getJury().size()-1){
								 jury+= " - ";
							 }
						 }
					}
					
					examModele.addElement(date + " : " + ex.getStudent().toString() + jury);
					
				}
				
			}
	         
	       });
		
		juryOrStudent.addItem(ApplicationSession.instance().getString("student"));
		juryOrStudent.addItem(ApplicationSession.instance().getString("jury"));
		
		top.add(nameText);
		top.add(juryOrStudent);
		
		this.add(top,BorderLayout.NORTH);
		
		exam.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(!exam.isSelectionEmpty() && e.getClickCount()==2){
					Calendar c = Calendar.getInstance();
					c.setTime(goodExam.get(exam.getSelectedIndex()).getExamDate());
					
					ApplicationSession.instance().getDateSelected().set(Calendar.YEAR, c.get(Calendar.YEAR));
					ApplicationSession.instance().getDateSelected().set(Calendar.MONTH, c.get(Calendar.MONTH));
					ApplicationSession.instance().getDateSelected().set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
					
					ApplicationSession.instance().getMyFrame().majView();
					
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
		this.add(exam, BorderLayout.CENTER);
		
		
	}
	
	public void CloseDialog(){
	    super.dispose();
	}
	
		

}
