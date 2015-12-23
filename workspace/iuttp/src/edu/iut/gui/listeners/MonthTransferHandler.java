package edu.iut.gui.listeners;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

import edu.iut.app.ApplicationSession;
import edu.iut.app.DragDropEventTool;
import edu.iut.app.ExamEvent;

public class MonthTransferHandler extends TransferHandler implements MouseMotionListener{

	Date dateExam;
	
	public MonthTransferHandler(Date date){
		dateExam=date;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		//On récupère le JComponent            
		JComponent lab = (JComponent)e.getSource();
		//Du composant, on récupère l'objet de transfert : le nôtre
		TransferHandler handle = lab.getTransferHandler();
		//On lui ordonne d'amorcer la procédure de drag'n drop
		handle.exportAsDrag(lab, e, TransferHandler.MOVE);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//TransfertHandler
	public boolean canImport(TransferHandler.TransferSupport info) {
		return true;
	}

	 public boolean importData(TransferHandler.TransferSupport support){
		//On retient la date d'origine
		MonthTransferHandler originListener = (MonthTransferHandler) support.getComponent().getMouseMotionListeners()[0];
		DragDropEventTool.destination = originListener.dateExam;
		DragDropEventTool.majDestinationMonth();
			        
		return true;
	 }
		
	public int getSourceActions(JComponent c) {
		//Nous n'autorisons donc que le déplacement ici
		return MOVE;
	}
		
	 protected void exportDone(JComponent c, Transferable t, int action){
	    	    
	    //Je retiens la date de destination et je swap les soutenances;
	    MonthTransferHandler destListener = (MonthTransferHandler) c.getMouseMotionListeners()[0];
	    DragDropEventTool.source = destListener.dateExam;
	    DragDropEventTool.majDestinationMonth();
	    
	    DragDropEventTool.swap();
			    
	    //On met a jour les vues
	    ApplicationSession.instance().getMyFrame().majView();
			   
	 }
		 
	protected Transferable createTransferable(JComponent c) {
		//On retourne un nouvel objet implémentant l'interface Transferable
		//StringSelection implémente cette interface,  nous l'utilisons donc
		if(c instanceof JLabel){
			return new StringSelection(((JLabel)c).getText());
		}
		return null;
	}
		
		

}
