package edu.iut.gui.widget.agenda;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.iut.app.ApplicationSession;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;
import edu.iut.gui.widget.agenda.WeekPanel.WeekDayNames;

public class ControlAgendaViewPanel extends JPanel {

	CardLayout agendaViewLayout;
	JPanel contentPane;
	
	int selectedYear;
	int selectedMonth;
	int selectedDay;
	
	public ControlAgendaViewPanel(final CardLayout layerLayout, final JPanel contentPane) {

		this.agendaViewLayout = layerLayout;
		this.contentPane = contentPane;
		
		/** EX3: REMPLACEMENT DU BOUTON NEXT */
		final Calendar c = Calendar.getInstance();
		Calendar actualCalendar = Calendar.getInstance();
		c.setFirstDayOfWeek(c.MONDAY);
		
		int actualYear = c.get(c.YEAR);
		JPanel panelDate = new JPanel(new GridLayout(3,1));
		
		final JComboBox day = new JComboBox();
		final JComboBox month = new JComboBox(ApplicationSession.instance().getMonths());
		SpinnerModel yearModel = new SpinnerNumberModel(actualYear, actualYear-5, actualYear+5, 1);
		final JSpinner year = new JSpinner(yearModel);
		year.addChangeListener((new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e){
				day.removeAllItems();
				c.set((Integer)year.getValue(), month.getSelectedIndex(), 1, 0, 0, 0);
				int firstDay=c.get(c.DAY_OF_WEEK);
				for(int i=0; i<c.getActualMaximum(c.DAY_OF_MONTH);i++){
					day.addItem((ApplicationSession.instance().getDays()[(firstDay+i+5)%7])+" "+String.valueOf(i+1) + " " + ApplicationSession.instance().getMonths()[month.getSelectedIndex()]);;
				}
			}			
		}));
		
		
		month.setSelectedIndex(c.get(c.MONTH));
		month.addItemListener((new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e){
				day.removeAllItems();
				c.set((Integer)year.getValue(), month.getSelectedIndex(), 1, 0, 0, 0);
				int firstDay=c.get(c.DAY_OF_WEEK);
				for(int i=0; i<c.getActualMaximum(c.DAY_OF_MONTH);i++){
					day.addItem((ApplicationSession.instance().getDays()[(firstDay+i+5)%7])+" "+String.valueOf(i+1) + " " + ApplicationSession.instance().getMonths()[month.getSelectedIndex()]);;
				}
			}			
		}));
		
		
		
		c.set(c.get(c.YEAR), c.get(c.MONTH), 1, 0, 0, 0);
		int firstDay=c.get(c.DAY_OF_WEEK);
		for(int i=0; i<c.getActualMaximum(c.DAY_OF_MONTH);i++){
			day.addItem((ApplicationSession.instance().getDays()[(firstDay+i+5)%7])+" "+String.valueOf(i+1) + " " + ApplicationSession.instance().getMonths()[month.getSelectedIndex()]);;
		}
		day.setSelectedIndex((actualCalendar.get(actualCalendar.DAY_OF_MONTH))-1);
		day.addItemListener((new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e){
				AgendaPanelFactory agendaPanelFactory = new AgendaPanelFactory();
				
				//Mise à jour de la date séléctionnée
				Calendar cal=Calendar.getInstance();
				cal.set(Calendar.YEAR, (Integer)year.getValue());
				cal.set(Calendar.MONTH, month.getSelectedIndex());
				cal.set(Calendar.DATE, day.getSelectedIndex()+1);
				//On définit l'heure
				cal.set(Calendar.MINUTE,0);
				cal.set(Calendar.SECOND,0);
				cal.set(Calendar.MILLISECOND,0);
				//On définit la date séléctionnée
				ApplicationSession.instance().setDateSelected(cal);
				
				
				
				//On créer les trois nouvelles vues Week, Month et Day
				contentPane.add(agendaPanelFactory.getAgendaView(ActiveView.DAY_VIEW),ActiveView.DAY_VIEW.name());
				ApplicationSession.instance().setDateSelected(cal);
				contentPane.add(agendaPanelFactory.getAgendaView(ActiveView.WEEK_VIEW),ActiveView.WEEK_VIEW.name());
				ApplicationSession.instance().setDateSelected(cal);
				contentPane.add(agendaPanelFactory.getAgendaView(ActiveView.MONTH_VIEW),ActiveView.MONTH_VIEW.name());
				ApplicationSession.instance().setDateSelected(cal);
				
				//On réaffiche la bonne vue
				
				layerLayout.show(contentPane,ActiveView.WEEK_VIEW.name());
				
			}			
		}));
		
		
		panelDate.add(year);
		panelDate.add(month);
		panelDate.add(day);
		
		this.add(panelDate);
		
		}
	
	public int getYear() {
		return selectedYear;
	}
	public int getMonth() {
		return selectedMonth;
	}
	public int getDay() {
		return selectedDay;
	}
	
}
