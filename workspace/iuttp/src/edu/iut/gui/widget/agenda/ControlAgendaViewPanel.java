package edu.iut.gui.widget.agenda;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.iut.app.ApplicationSession;
import edu.iut.app.Document;
import edu.iut.gui.frames.SearchExamDialog;

public class ControlAgendaViewPanel extends JPanel {

	CardLayout agendaViewLayout;
	JPanel contentPane;
	
	int selectedYear;
	int selectedMonth;
	int selectedDay;
	
	public ControlAgendaViewPanel(final CardLayout layerLayout, final JPanel contentPane) {

		this.agendaViewLayout = layerLayout;
		this.contentPane = contentPane;
		
		final Calendar c = Calendar.getInstance();
		Calendar actualCalendar = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		
		int actualYear = c.get(Calendar.YEAR);
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel centre = new JPanel(new GridLayout(1,2));
		JPanel eastPanel = new JPanel(new BorderLayout());
		JPanel panelDate = new JPanel(new GridLayout(1,3));
		
		eastPanel.add(centre, BorderLayout.CENTER);
		
		final JComboBox<String> day = new JComboBox<String>();
		final JComboBox<String> month = new JComboBox<String>(ApplicationSession.instance().getMonths());
		SpinnerModel yearModel = new SpinnerNumberModel(actualYear, actualYear-5, actualYear+5, 1);
		final JSpinner year = new JSpinner(yearModel);
		year.setEditor(new JSpinner.NumberEditor(year,"#"));
		year.addChangeListener((new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e){
				day.removeAllItems();
				c.set((Integer)year.getValue(), month.getSelectedIndex(), 1, 0, 0, 0);
				int firstDay=c.get(Calendar.DAY_OF_WEEK);
				for(int i=0; i<c.getActualMaximum(Calendar.DAY_OF_MONTH);i++){
					day.addItem((ApplicationSession.instance().getDays()[(firstDay+i+5)%7])+" "+String.valueOf(i+1) + " " + ApplicationSession.instance().getMonths()[month.getSelectedIndex()]);;
				}
			}			
		}));
		
		
		month.setSelectedIndex(c.get(Calendar.MONTH));
		month.addItemListener((new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e){
				day.removeAllItems();
				c.set((Integer)year.getValue(), month.getSelectedIndex(), 1, 0, 0, 0);
				int firstDay=c.get(Calendar.DAY_OF_WEEK);
				for(int i=0; i<c.getActualMaximum(Calendar.DAY_OF_MONTH);i++){
					day.addItem((ApplicationSession.instance().getDays()[(firstDay+i+5)%7])+" "+String.valueOf(i+1) + " " + ApplicationSession.instance().getMonths()[month.getSelectedIndex()]);;
				}
			}			
		}));
		
		
		
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1, 0, 0, 0);
		int firstDay=c.get(Calendar.DAY_OF_WEEK);
		for(int i=0; i<c.getActualMaximum(Calendar.DAY_OF_MONTH);i++){
			day.addItem((ApplicationSession.instance().getDays()[(firstDay+i+5)%7])+" "+String.valueOf(i+1) + " " + ApplicationSession.instance().getMonths()[month.getSelectedIndex()]);;
		}
		day.setSelectedIndex((actualCalendar.get(Calendar.DAY_OF_MONTH))-1);
		day.addItemListener((new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e){
				
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
				
				
				
				//On met à jour
				ApplicationSession.instance().getMyFrame().majView();
				
			}			
		}));
		
		
		panelDate.add(year);
		panelDate.add(month);
		panelDate.add(day);
		
		JButton next = new JButton(">>");
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch(ApplicationSession.instance().getActualView()){
					case DAY_VIEW:
						ApplicationSession.instance().getDateSelected().add(Calendar.DAY_OF_MONTH, 1);
						break;
					case WEEK_VIEW:
						ApplicationSession.instance().getDateSelected().add(Calendar.WEEK_OF_YEAR, 1);
						break;
					case MONTH_VIEW:
						ApplicationSession.instance().getDateSelected().add(Calendar.MONTH, 1);
						break;
				}
				ApplicationSession.instance().getMyFrame().majView();
			}			
		});
		
		JButton previous = new JButton("<<");
		previous.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch(ApplicationSession.instance().getActualView()){
					case DAY_VIEW:
						ApplicationSession.instance().getDateSelected().add(Calendar.DAY_OF_MONTH, -1);
						break;
					case WEEK_VIEW:
						ApplicationSession.instance().getDateSelected().add(Calendar.WEEK_OF_YEAR, -1);
						break;
					case MONTH_VIEW:
						ApplicationSession.instance().getDateSelected().add(Calendar.MONTH, -1);
						break;
				}
				ApplicationSession.instance().getMyFrame().majView();
			}			
		});
		
		centre.add(previous);
		centre.add(next);
		
		
		JButton search = new JButton(ApplicationSession.instance().getString("searchexam"));
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SearchExamDialog monDialog = new SearchExamDialog();
			}			
		});
		
		eastPanel.add(search,BorderLayout.EAST);
		
		
		
		mainPanel.add(panelDate, BorderLayout.CENTER);
		mainPanel.add(eastPanel, BorderLayout.EAST);
		
		this.add(mainPanel);
		
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
