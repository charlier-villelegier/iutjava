package edu.iut.gui.widget.agenda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.MatteBorder;

import edu.iut.app.ApplicationSession;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.gui.listeners.DayClickListener;
import edu.iut.gui.listeners.HourClickListener;
import edu.iut.gui.listeners.MonthTransferHandler;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;
import edu.iut.gui.widget.agenda.WeekPanel.WeekDayNames;

public class DayPanel extends EventPanel {

	public DayPanel(ActiveView activeView,WeekDayNames weekDayNames) {
		super(activeView);
		JLabel day = new JLabel();
		switch (activeView) {
		case DAY_VIEW:
			day.setText(" " + ApplicationSession.instance().getMonths()[ApplicationSession.instance().getDateSelected().get(Calendar.MONTH)]);
		case WEEK_VIEW:
			GridLayout daysLayout;
			
			daysLayout = new GridLayout(14,1);
			this.setLayout(daysLayout);
			day.setText(weekDayNames.toString() + " " + ApplicationSession.instance().getDateSelected().get(Calendar.DAY_OF_MONTH) + day.getText());
			
			JPanel dayPanel = new JPanel();
				
			dayPanel.setBackground(new Color(50,100,200));
			dayPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
			day.setForeground(Color.WHITE);
			day.setFont(new Font("Calibri",Font.PLAIN,20));
				
			dayPanel.add(day);
			dayPanel.addMouseListener(new DayClickListener(ApplicationSession.instance().getDateSelected().getTime()));	
			this.add(dayPanel);
			
			
			for (int hi = 7;hi<20;hi++) {
				//J'incrémente l'heure du calendar
				ApplicationSession.instance().getDateSelected().set(Calendar.HOUR_OF_DAY, hi);
				Date d=ApplicationSession.instance().getDateSelected().getTime();
				
				//J'affiche l'heure
				JPanel hour = new JPanel(new BorderLayout());
				JLabel heure = new JLabel(new Integer(hi).toString(), SwingConstants.CENTER);
				JLabel exam = new JLabel("", SwingConstants.CENTER);
				
				
				
				//Si il y a une soutenance à cette heure on l'affiche
				LinkedList<ExamEvent> examDate = ApplicationSession.instance().getAgenda().meetCriteriaDateEqual(d);
				if(!examDate.isEmpty()){
					exam.setText(examDate.getFirst().getStudent().toString());
					
					//On affiche plus d'info si on est en mode DayView
					if(activeView==ActiveView.DAY_VIEW){
						exam.setText(ApplicationSession.instance().getString("student") 
								+ " : " 
								+ exam.getText());
						if(examDate.getFirst().getJury()!=null){
							if(!examDate.getFirst().getJury().isEmpty()){
								exam.setText(exam.getText()
										+ " | "
										+ ApplicationSession.instance().getString("jury") + " : ");
								for(Person p : examDate.getFirst().getJury()){
									exam.setText(exam.getText() + p.toString());
									if (examDate.getFirst().getJury().lastIndexOf(p)!=examDate.getFirst().getJury().size()-1){
										exam.setText(exam.getText()	+ " - ");
									}
								}
							}
						}
						if(examDate.getFirst().getClassroom()!=null){
							exam.setText(exam.getText()
									+ " | "
									+ ApplicationSession.instance().getString("classroom")
									+ " : "
									+ examDate.getFirst().getClassroom().getClassRoomNumber());
							
						}
						
					}
							
					
				}
				if(activeView==ActiveView.DAY_VIEW)hour.add(heure,BorderLayout.WEST);
				hour.add(exam, BorderLayout.CENTER);
				hour.setBorder(BorderFactory.createLineBorder(new Color(0,0,128)));
				hour.setBackground(new Color(230,240,255));		
				
				//Je créer le listener
				HourClickListener listener = new HourClickListener(d, hour);
				exam.addMouseListener(listener);
				exam.addMouseMotionListener(listener);
				exam.setTransferHandler(listener);
				this.add(hour);
			}
			break;
		case MONTH_VIEW:
			JPanel hour = new JPanel(new GridLayout(0,1));
			day = new JLabel("");
			
			Calendar c = Calendar.getInstance();
			c.setTime(ApplicationSession.instance().getDateSelected().getTime());
			c.set(Calendar.HOUR_OF_DAY, 0);
			Date d = c.getTime();
			
			
			
			//Je fais l'intersetion entre la liste des soutenances après ce jour la et avant le jour d'après pour avoir toutes les soutenances du jour
			LinkedList<ExamEvent> afterDate = ApplicationSession.instance().getAgenda().meetCriteriaDateAfter(d);
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)+1);
			
			d=c.getTime();
			
			
			
			LinkedList<ExamEvent> beforeDate = ApplicationSession.instance().getAgenda().meetCriteriaDateBefore(d);
			afterDate.retainAll(beforeDate);
			
			Collections.sort(afterDate);
			
			if(!afterDate.isEmpty()){
				for(ExamEvent exam : afterDate){
					day = new JLabel();
					c.setTime(exam.getExamDate());
					MonthTransferHandler listener = new MonthTransferHandler(c.getTime());
					day.addMouseMotionListener(listener);
					day.setTransferHandler(listener);
					day.setText(day.getText() + c.get(Calendar.HOUR_OF_DAY) + "h : " + exam.getStudent().toString());
					hour.add(day);
				}
			}
			
			
			hour.setBackground(new Color(230,240,255));
			
			ApplicationSession.instance().getDateSelected().add(Calendar.DAY_OF_MONTH, 1);
			this.add(hour);
		
		}
	}
	
	protected void setupUIDayView() {
		
	}
	protected void setupUIWeekView() {
		
	}
	protected void setupUIMonthView() {
		
	}


}
