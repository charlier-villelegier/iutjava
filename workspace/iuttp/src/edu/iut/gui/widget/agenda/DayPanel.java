package edu.iut.gui.widget.agenda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import edu.iut.app.ApplicationSession;
import edu.iut.app.ExamEvent;
import edu.iut.gui.listeners.HourClickListener;
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
					exam.setText(examDate.get(0).getStudent().getFirstname() + " " + examDate.get(0).getStudent().getLastname());
				}
				if(activeView==ActiveView.DAY_VIEW)hour.add(heure,BorderLayout.WEST);
				hour.add(exam, BorderLayout.CENTER);
				hour.setBorder(BorderFactory.createLineBorder(new Color(0,0,128)));
				hour.setBackground(new Color(230,240,255));		
				
				//Je créer le listener
				hour.addMouseListener(new HourClickListener(d, hour));
				this.add(hour);
			}
			break;
		case MONTH_VIEW:
			JPanel hour = new JPanel();
			day = new JLabel();
			
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
			day.setText(day.getText() + "<html>");
			Collections.sort(afterDate);
			
			for(ExamEvent exam : afterDate){
				c.setTime(exam.getExamDate());
				day.setText(day.getText() + c.get(Calendar.HOUR_OF_DAY) + "h : " + exam.getStudent().toString() + "<br>");
			}
			day.setText(day.getText() + "</html>");
			
			hour.setBackground(new Color(230,240,255));
			
			hour.add(day);
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
