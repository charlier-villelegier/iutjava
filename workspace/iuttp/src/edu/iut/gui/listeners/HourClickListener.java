package edu.iut.gui.listeners;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import javax.imageio.ImageIO;
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
			//Si on reclique sur la soutenance que l'on déplace on annule le déplacement
			if(examDate.getFirst()==DragDropEventTool.movingExam){
				DragDropEventTool.movingExam=null;
				ApplicationSession.instance().getMyFrame().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));;
			}
			
			//Si on clique droit
			if(SwingUtilities.isRightMouseButton(e)){
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
				
				JMenuItem move = new JMenuItem(ApplicationSession.instance().getString("move"));
				move.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						Toolkit toolkit = Toolkit.getDefaultToolkit();
						
						String student = examDate.getFirst().getStudent().toString();

				        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
				        Graphics2D g2d = img.createGraphics();
				        Font font = new Font("Arial", Font.PLAIN, 20);
				        g2d.setFont(font);
				        FontMetrics fm = g2d.getFontMetrics();
				        int width = fm.stringWidth(student)+20;
				        int height = fm.getHeight();
				        g2d.dispose();

				        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
				        g2d = img.createGraphics();
				        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
				        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
				        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
				        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
				        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
				        g2d.setFont(font);
				        fm = g2d.getFontMetrics();
				        g2d.setColor(Color.BLACK);
				        BufferedImage image = null;
				        try {
				            image = ImageIO.read(new File("cursor.png"));
				        } catch (IOException ex) {
				        }	
				        g2d.drawImage(image,0,0,16,16,ApplicationSession.instance().getMyFrame());
				        g2d.drawString(student, 16, 16);
				        g2d.dispose();
				        
				        
						Cursor c = toolkit.createCustomCursor(img , new Point(0,0), "img");
						
						
						ApplicationSession.instance().getMyFrame().setCursor(c);
						DragDropEventTool.movingExam=examDate.getFirst();
						
						
					}			
				});
				
				menu.add(modif);
				menu.add(move);
				menu.addSeparator();
				menu.add(delete);
				
				
				menu.show(this.container, e.getX(), e.getY());
			}
			
		}
		else{
			if(DragDropEventTool.movingExam==null){
				AddExamEventFrame dialog = new AddExamEventFrame(this.date);
			}
			else{
				DragDropEventTool.destination=this.date;
				ApplicationSession.instance().getMyFrame().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				DragDropEventTool.moveExam();
				ApplicationSession.instance().getMyFrame().majView();
			}		
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
			if(!canImport(support) || ((String)support.getTransferable().getTransferData(DataFlavor.stringFlavor)).equals(""))
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
