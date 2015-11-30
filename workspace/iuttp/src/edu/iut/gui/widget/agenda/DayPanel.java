package edu.iut.gui.widget.agenda;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import edu.iut.app.ApplicationSession;
import edu.iut.app.ExamEvent;
import edu.iut.gui.listeners.HourClickListener;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;
import edu.iut.gui.widget.agenda.WeekPanel.WeekDayNames;

public class DayPanel extends EventPanel {

	public DayPanel(ActiveView activeView,WeekDayNames weekDayNames) {
		super(activeView);
		switch (activeView) {
		case DAY_VIEW:
		case WEEK_VIEW:
			GridLayout daysLayout;
			switch(weekDayNames) {
			case EMPTYDAY:
				daysLayout = new GridLayout(24,1);
				this.setLayout(daysLayout);
				break;
			default:
				daysLayout = new GridLayout(25,1);
				this.setLayout(daysLayout);
				this.add(new JLabel(weekDayNames.toString() + " " + ApplicationSession.instance().getDateSelected().get(Calendar.DAY_OF_MONTH)));
			}
			
			for (int hi = 0;hi<24;hi++) {
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
				hour.add(heure,BorderLayout.WEST);
				hour.add(exam, BorderLayout.CENTER);
				
				
				
				//Je créer le listener
				hour.addMouseListener(new HourClickListener(d, hour));
				this.add(hour);
			}
			break;
		case MONTH_VIEW:
			JPanel hour = new JPanel();
			hour.add(new JLabel(weekDayNames.getShortName() + " " + ApplicationSession.instance().getDateSelected().get(Calendar.DAY_OF_MONTH)));
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
