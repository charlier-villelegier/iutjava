package edu.iut.gui.widget.agenda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;
import edu.iut.app.ApplicationSession;

public class WeekPanel extends EventPanel {

	public enum WeekDayNames {
		EMPTYDAY("",""),
		MONDAY(ApplicationSession.instance().getString("monday"),ApplicationSession.instance().getString("mon")),
		TUESDAY(ApplicationSession.instance().getString("tuesday"),ApplicationSession.instance().getString("tue")),
		WEDNESDAY(ApplicationSession.instance().getString("wednesday"),ApplicationSession.instance().getString("wed")),
		THURSDAY(ApplicationSession.instance().getString("thursday"),ApplicationSession.instance().getString("thu")),
		FRIDAY(ApplicationSession.instance().getString("friday"),ApplicationSession.instance().getString("fri")),
		SATURDAY(ApplicationSession.instance().getString("saturday"),ApplicationSession.instance().getString("sat")),
		SUNDAY(ApplicationSession.instance().getString("sunday"),ApplicationSession.instance().getString("sun"));
		
		private String name;
		private String shortName;
		
		WeekDayNames(String name,String shortName) {
			this.name = name;
			this.shortName = shortName;
		}
		
		public String getShortName() {
			return shortName;
		}
		
		public String toString() {
			return name;
		}
	}
	
	public WeekPanel() {
		super(ActiveView.WEEK_VIEW);
		GridLayout daysOfWeekLayout = new GridLayout(1,7);
		JPanel centre = new JPanel(daysOfWeekLayout);
		this.setLayout(new BorderLayout());
		JPanel west = new JPanel(new GridLayout(14,1));
		
		//Position le calendrier sur le premier lundi de la semaine de la date séléctionnée
		Date d = ApplicationSession.instance().getDateSelected().getTime();
		
		ApplicationSession.instance().getDateSelected().set(Calendar.DATE, ApplicationSession.instance().getDateSelected().get(Calendar.DAY_OF_MONTH)-(ApplicationSession.instance().getDateSelected().get(Calendar.DAY_OF_WEEK)+5)%7);
		
		//Compense l'écart
		west.add(new JLabel(""));
		
		//On ajoute les heures
		for(int i=7;i<20;i++){
			
			JLabel hour = new JLabel(String.valueOf(i));
			hour.setVerticalAlignment(JLabel.TOP);
			hour.setBorder(BorderFactory.createLineBorder(Color.black));
		
			west.add(hour);
		}
		
		for (int di = 0;di<7;di++)	{
			centre.add(new DayPanel(ActiveView.WEEK_VIEW,WeekDayNames.values()[di+1]));
			ApplicationSession.instance().getDateSelected().add(Calendar.DAY_OF_MONTH, 1);
		}
		ApplicationSession.instance().getDateSelected().setTime(d);
		
		this.add(west, BorderLayout.WEST);
		this.add(centre, BorderLayout.CENTER);
		
	}
}
