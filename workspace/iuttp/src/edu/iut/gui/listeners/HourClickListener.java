package edu.iut.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import edu.iut.app.ApplicationSession;
import edu.iut.app.ExamEvent;
import edu.iut.gui.frames.AddExamEventFrame;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;

public class HourClickListener implements MouseListener{

	protected Date date;
	protected JPanel container;
	
	public HourClickListener(Date d, JPanel cont){
		this.date=d;
		this.container=cont;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		final LinkedList<ExamEvent> examDate = ApplicationSession.instance().getAgenda().meetCriteriaDateEqual(this.date);
		if(!examDate.isEmpty()){
			JPopupMenu menu = new JPopupMenu();
			
			
			JMenuItem modif = new JMenuItem(ApplicationSession.instance().getString("edit"));
			modif.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AddExamEventFrame dialog = new AddExamEventFrame(HourClickListener.this.date);
				}			
			});
			
			
			JMenuItem delete = new JMenuItem(ApplicationSession.instance().getString("delete"));
			delete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(JOptionPane.showConfirmDialog (container, ApplicationSession.instance().getString("deletemessage"),"Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
						ApplicationSession.instance().getAgenda().remove(examDate.getFirst());
						ApplicationSession.instance().getMyFrame().majView();
					}
					
				}			
			});
			
			menu.add(modif);
			menu.add(delete);
			
			menu.show(this.container, e.getX(), e.getY());
		}
		else{
			AddExamEventFrame dialog = new AddExamEventFrame(this.date);				
		}
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
