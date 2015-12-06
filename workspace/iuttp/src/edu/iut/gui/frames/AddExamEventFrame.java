package edu.iut.gui.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.iut.app.ApplicationSession;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;

public class AddExamEventFrame extends JDialog{
	
	Date dateExam;
	
	public AddExamEventFrame(Date d){
		super(ApplicationSession.instance().getMyFrame(),ApplicationSession.instance().getString("addexam"),true);
		
		this.dateExam=d;
		
		this.setupUI();
		
		this.pack();
		this.setLocationRelativeTo(ApplicationSession.instance().getMyFrame());
		this.setVisible(true);
		
	}
	
	public void setupUI(){
		final ExamEvent exam;
		final LinkedList<ExamEvent> existingExam = ApplicationSession.instance().getAgenda().meetCriteriaDateEqual(dateExam);
		if(existingExam.isEmpty()){
			exam = new ExamEvent(this.dateExam,null,null,null,null);
		}
		else{
			exam = existingExam.getFirst();
			
		}
		
		//Listener Not implemented
		ActionListener notImplemented = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,ApplicationSession.instance().getString("notimplemented"));				
			}			
		};
	
		
		//Initialisation de la date avec l'internationnalisation
		JLabel date = new JLabel();
		Calendar c = Calendar.getInstance();
		c.setTime(this.dateExam);
		date.setText(
				ApplicationSession.instance().getDays()[(c.get(Calendar.DAY_OF_WEEK)+5)%7] 
				+ " " 
				+ c.get(Calendar.DAY_OF_MONTH) 
				+ " " 
				+ ApplicationSession.instance().getMonths()[c.get(Calendar.MONTH)]
				+ " : "
				+ c.get(Calendar.HOUR_OF_DAY)
				+ "h");
		
		this.setLayout(new BorderLayout());
		
		JPanel centre = new JPanel(new GridLayout(5,2));
		centre.add(new JLabel(ApplicationSession.instance().getString("date")));
		centre.add(date);
		
		//Choix de l'étudiant
		centre.add(new JLabel(ApplicationSession.instance().getString("student")+" : "));
		final JButton chooseStudent;
		if(exam.getStudent()==(null)){
			 chooseStudent = new JButton(ApplicationSession.instance().getString("choosestudent"));
		}
		else{
			chooseStudent = new JButton(exam.getStudent().toString());
		}
		chooseStudent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StudentChoiceDialog dialog = new StudentChoiceDialog(AddExamEventFrame.this,chooseStudent,exam);		
			}			
		});
		centre.add(chooseStudent);
		
		//Choix du jury
		centre.add(new JLabel("Jury : "));
		final JButton chooseJury;
		if(exam.getJury()==(null)){
			 chooseJury = new JButton(ApplicationSession.instance().getString("choosemember"));
		}
		else{
			chooseJury = new JButton(exam.getJury().size() + " " + ApplicationSession.instance().getString("member"));
		}
		chooseJury.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JuryChoiceDialog dialog = new JuryChoiceDialog(AddExamEventFrame.this,chooseJury,exam);		
			}			
		});
		centre.add(chooseJury);
		
		//Choix de la salle
		centre.add(new JLabel(ApplicationSession.instance().getString("classroom") + " : "));
		JComboBox classroom = new JComboBox();
		classroom.addActionListener(notImplemented);
		centre.add(classroom);
		
		//Choix des documents
		centre.add(new JLabel(ApplicationSession.instance().getString("documents") + " : "));
		JButton documents = new JButton(ApplicationSession.instance().getString("choosedocuments"));
		documents.setEnabled(false);
		documents.setText(ApplicationSession.instance().getString("notimplemented"));
		centre.add(documents);
		
		this.add(centre, BorderLayout.CENTER);
		
		JPanel sud = new JPanel(new GridLayout(1,1));
		
		//Ajouter la soutenance
		JButton ajouter = new JButton(ApplicationSession.instance().getString("save"));
		ajouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!existingExam.isEmpty())ApplicationSession.instance().getAgenda().remove(exam);
				ApplicationSession.instance().getAgenda().add(exam);
				ApplicationSession.instance().getMyFrame().majView();
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
