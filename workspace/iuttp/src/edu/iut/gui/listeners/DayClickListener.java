package edu.iut.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import edu.iut.app.ApplicationSession;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;

public class DayClickListener implements MouseListener{

	Date jour;
	
	public DayClickListener(Date d) {
		this.jour=d;
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		ApplicationSession.instance().getDateSelected().setTime(jour);
		ApplicationSession.instance().setActualView(ActiveView.DAY_VIEW);
		ApplicationSession.instance().getMyFrame().majView();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
