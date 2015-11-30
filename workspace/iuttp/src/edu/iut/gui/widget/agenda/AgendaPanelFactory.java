package edu.iut.gui.widget.agenda;


import javax.swing.JPanel;

import edu.iut.app.ApplicationSession;
import edu.iut.gui.widget.agenda.WeekPanel.WeekDayNames;

public class AgendaPanelFactory {

	public enum ActiveView{
		MONTH_VIEW("Month View"),
		WEEK_VIEW("Week View"),
		DAY_VIEW("Day View");
		
		private String activeView;
		
		ActiveView(String activeView) {
			this.activeView = activeView;
		}
		
		public String toString() {
			return activeView;
		}
	
	}
	
	public AgendaPanelFactory() {
	}
	
	
	public JPanel getAgendaView(ActiveView activeView) {
		JPanel agendaView = null;
		switch (activeView) {
			case MONTH_VIEW:
				MonthPanel monthPanel = new MonthPanel();
				agendaView = monthPanel;
				break;
			case WEEK_VIEW:
				WeekPanel weekPanel = new WeekPanel();
				agendaView = weekPanel;
				break;
			case DAY_VIEW:
				DayPanel dayPanel = new DayPanel(activeView,WeekDayNames.EMPTYDAY);
				agendaView = dayPanel;
				break;
			default:
				break;				
		}
		return agendaView;
	}

	
}
