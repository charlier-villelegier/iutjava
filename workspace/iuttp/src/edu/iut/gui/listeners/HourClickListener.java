package edu.iut.gui.listeners;

import java.awt.Color;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

import edu.iut.app.ApplicationSession;
import edu.iut.app.DragDropEventTool;
import edu.iut.app.ExamEvent;
import edu.iut.gui.frames.AddExamEventFrame;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;

public class HourClickListener extends TransferHandler implements MouseListener, MouseMotionListener{

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
		//On foncit un peu le fond
		this.container.setBackground(new Color(200,210,255));
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//On retourne a la couleur de base
		this.container.setBackground(new Color(230,240,255));	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		//Je change la couleur
		this.container.setBackground(new Color(230,240,255));	
		
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
		if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
	      return false;
	    }
		return true;
	}

	 public boolean importData(TransferHandler.TransferSupport support){
		//On retient la date d'origine
		HourClickListener originListener = (HourClickListener) support.getComponent().getMouseListeners()[0];
		DragDropEventTool.destination = originListener.date;
		    
		//S'il n'y a rien a déplacer, que les données ne sont pas valides, ou qu'une soutenance est déjà en destination, on annule le drop     
		try {
			LinkedList<ExamEvent> examDest = ApplicationSession.instance().getAgenda().meetCriteriaDateEqual(DragDropEventTool.destination);
			if(!canImport(support) || ((String)support.getTransferable().getTransferData(DataFlavor.stringFlavor)).equals("") || !examDest.isEmpty())
			  return false;
		} catch (UnsupportedFlavorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		    
		    Transferable data = support.getTransferable();
		    String str = "";
		    
		    
		    
		    try {
		      str = (String)data.getTransferData(DataFlavor.stringFlavor);
		    } catch (UnsupportedFlavorException e){
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		         
		    JLabel lab = (JLabel)support.getComponent();
		    lab.setText(str);
		    
		    return true;
		  }
	
	public int getSourceActions(JComponent c) {
		  //Nous n'autorisons donc que le déplacement ici
		
	    
		  return MOVE;
		}
	
	 protected void exportDone(JComponent c, Transferable t, int action){
		    //Une fois le drop effectué nous effaçons le contenu de notre JLabel
		    if(action == MOVE)
		      ((JLabel)c).setText("");
		    
		    //Je retiens la date de destination et je swap les soutenances;
		    HourClickListener destListener = (HourClickListener) c.getMouseListeners()[0];
		    DragDropEventTool.source = destListener.date;
		    DragDropEventTool.swap();
		    
		    //On met a jour les vues
		    ApplicationSession.instance().getMyFrame().majView();
		   
		  }
	 
	 protected Transferable createTransferable(JComponent c) {
		    //On retourne un nouvel objet implémentant l'interface Transferable
		    //StringSelection implémente cette interface,  nous l'utilisons donc
		 
		    return new StringSelection(((JLabel)c).getText());
		  }
	
	

}
