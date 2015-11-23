package edu.iut.gui.widget.agenda;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerModel;

import edu.iut.app.ApplicationSession;

public class ControlAgendaViewPanel extends JPanel {

	CardLayout agendaViewLayout;
	JPanel contentPane;
	
	int selectedYear;
	int selectedMonth;
	int selectedDay;
	
	public ControlAgendaViewPanel(CardLayout layerLayout, final JPanel contentPane) {

		this.agendaViewLayout = layerLayout;
		this.contentPane = contentPane;
		
		/** EX3: REMPLACEMENT DU BOUTON NEXT */
		Calendar c = Calendar.getInstance();
		int actualYear = c.get(c.YEAR);
		JPanel panelDate = new JPanel(new GridLayout(3,1));
		
		
		SpinnerModel yearModel = new SpinnerNumberModel(actualYear, actualYear-5, actualYear+5, 1);
		JSpinner year = new JSpinner(yearModel);
		JComboBox month = new JComboBox(ApplicationSession.instance().getMonths());
		JComboBox day = new JComboBox(ApplicationSession.instance().getDays());
		
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
