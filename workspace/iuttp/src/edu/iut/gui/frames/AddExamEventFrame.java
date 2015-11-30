package edu.iut.gui.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.iut.app.ApplicationSession;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;

public class AddExamEventFrame extends JDialog{
	
	Date dateExam;
	
	public AddExamEventFrame(Date d){
		super(ApplicationSession.instance().getMyFrame(),"Ajouter un examen",true);
		
		this.dateExam=d;
		
		this.setupUI();
		
		this.pack();
		this.setLocationRelativeTo(ApplicationSession.instance().getMyFrame());
		this.setVisible(true);
		
	}
	
	public void setupUI(){
		
		final ExamEvent exam = new ExamEvent(this.dateExam,null,null,null,null);
		
		//Initialisation de la date avce l'internationnalisation
		JLabel date = new JLabel();
		Calendar c = Calendar.getInstance();
		c.setTime(this.dateExam);
		date.setText(
				ApplicationSession.instance().getDays()[(c.get(Calendar.DAY_OF_WEEK)+5)%7] 
				+ " " 
				+ c.get(Calendar.DAY_OF_MONTH) 
				+ " " 
				+ ApplicationSession.instance().getMonths()[c.get(Calendar.MONTH)]
				+ " à "
				+ c.get(Calendar.HOUR_OF_DAY)
				+ "h");
		
		this.setLayout(new BorderLayout());
		
		JPanel centre = new JPanel(new GridLayout(5,2));
		centre.add(new JLabel("Date : "));
		centre.add(date);
		
		centre.add(new JLabel("Etudiant : "));
		final JButton chooseStudent = new JButton("Choisissez un étudiant");
		chooseStudent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StudentChoiceDialog dialog = new StudentChoiceDialog(AddExamEventFrame.this,chooseStudent,exam);		
			}			
		});
		centre.add(chooseStudent);
		
		centre.add(new JLabel("Jury : "));
		centre.add(new JButton("Choisissez les membres"));
		
		centre.add(new JLabel("Salle : "));
		centre.add(new JComboBox());
		
		centre.add(new JLabel("Documents : "));
		centre.add(new JButton("Choisissez les documents"));
		
		this.add(centre, BorderLayout.CENTER);
		
		JPanel sud = new JPanel(new GridLayout(1,1));
		
		JButton ajouter = new JButton("Ajouter");
		ajouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ApplicationSession.instance().getAgenda().add(exam);
				CloseDialog();
			}			
		});
		sud.add(ajouter);
		
		this.add(sud, BorderLayout.SOUTH);
		
		System.out.println(this.dateExam.toString());
	}
	
	public void CloseDialog(){
	    super.dispose();
	}

}
