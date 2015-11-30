package edu.iut.gui.widget.agenda;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JPanel;

import edu.iut.app.ApplicationSession;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;
import edu.iut.gui.widget.agenda.WeekPanel.WeekDayNames;

public class MonthPanel extends EventPanel {

	public MonthPanel() {
		super(ActiveView.MONTH_VIEW);
		GridLayout daysOfMonthLayout = new GridLayout(7,5);		
		this.setLayout(daysOfMonthLayout);
		
		//Position le calendrier sur le premier jour du mois de la date séléctionnée
		Calendar c = ApplicationSession.instance().getDateSelected();
		ApplicationSession.instance().getDateSelected().set(Calendar.DATE, c.get(Calendar.DAY_OF_MONTH)-(c.get(Calendar.DAY_OF_MONTH)-1));
		
		
		for (int di = 0;di<c.getActualMaximum(Calendar.DAY_OF_MONTH);di++) {
			JPanel day = new DayPanel(ActiveView.MONTH_VIEW,WeekDayNames.values()[((ApplicationSession.instance().getDateSelected().get(Calendar.DAY_OF_WEEK)+5)%7)+1]);
			this.add(day);
		}
		
		
	}
}
