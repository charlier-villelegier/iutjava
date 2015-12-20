package edu.iut.gui.widget.agenda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import edu.iut.app.ApplicationSession;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;
import edu.iut.gui.widget.agenda.WeekPanel.WeekDayNames;

public class MonthPanel extends EventPanel {

	public MonthPanel() {
		super(ActiveView.MONTH_VIEW);
		JPanel daysOfMonth = new JPanel(new GridLayout(0,7));
		JPanel daysOfWeek = new JPanel(new GridLayout(1,7));
		this.setLayout(new BorderLayout());
		this.add(daysOfMonth, BorderLayout.CENTER);
		this.add(daysOfWeek, BorderLayout.NORTH);
		
		
		
		//Position le calendrier sur le premier jour du mois de la date séléctionnée
		Date d = ApplicationSession.instance().getDateSelected().getTime();
		ApplicationSession.instance().getDateSelected().set(Calendar.DATE, ApplicationSession.instance().getDateSelected().get(Calendar.DAY_OF_MONTH)-(ApplicationSession.instance().getDateSelected().get(Calendar.DAY_OF_MONTH)-1));
		
		//On écrit les jours de la semaine au nord
		for (int di = 0;di<7;di++)	{
			JLabel day = new JLabel(ApplicationSession.instance().getDays()[di]);
			JPanel dayPanel = new JPanel();
			
			dayPanel.setBackground(new Color(50,100,200));
			dayPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				
			day.setForeground(Color.WHITE);
			day.setFont(new Font("Calibri",Font.PLAIN,20));
				
			dayPanel.add(day);
			daysOfWeek.add(dayPanel);
			
		}
		
		//On rajoute le nombre de blanc nécessaire pour aligner les jours du mois
		for(int i=ApplicationSession.instance().getDateSelected().get(Calendar.DAY_OF_WEEK); i>2; i--){
			daysOfMonth.add(new JLabel(""));
		}
		
		for (int di = 0;di<ApplicationSession.instance().getDateSelected().getActualMaximum(Calendar.DAY_OF_MONTH);di++) {
			WeekDayNames weekday=WeekDayNames.values()[((ApplicationSession.instance().getDateSelected().get(Calendar.DAY_OF_WEEK)+5)%7)+1];
			String dayname=weekday.getShortName() + " " + ApplicationSession.instance().getDateSelected().get(Calendar.DAY_OF_MONTH);
			JPanel day = new DayPanel(ActiveView.MONTH_VIEW,WeekDayNames.values()[((ApplicationSession.instance().getDateSelected().get(Calendar.DAY_OF_WEEK)+5)%7)+1]);
			day.setBorder(new TitledBorder(dayname));
			daysOfMonth.setBackground(new Color(230,240,255));
			day.setBackground(new Color(230,240,255));
			daysOfMonth.add(day);
		}
		
		ApplicationSession.instance().getDateSelected().setTime(d);
	}
}
