package edu.iut.gui.frames;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import edu.iut.app.ApplicationSession;
import edu.iut.gui.widget.agenda.AgendaPanelFactory;
import edu.iut.gui.widget.agenda.ControlAgendaViewPanel;
import edu.iut.gui.widget.agenda.AgendaPanelFactory.ActiveView;


public class SchedulerFrame extends JFrame {
	JPanel contentPane;
	CardLayout layerLayout;
	AgendaPanelFactory agendaPanelFactory;	
	JPanel dayView;
	JPanel weekView;
	JPanel monthView;
	
	protected void setupUI() {
		
		contentPane = new JPanel();
		layerLayout = new CardLayout();
		contentPane.setLayout(layerLayout);
		ControlAgendaViewPanel agendaViewPanel = new ControlAgendaViewPanel(layerLayout,contentPane);
		agendaPanelFactory = new AgendaPanelFactory();
		dayView = agendaPanelFactory.getAgendaView(ActiveView.DAY_VIEW);
		weekView = agendaPanelFactory.getAgendaView(ActiveView.WEEK_VIEW);
		monthView = agendaPanelFactory.getAgendaView(ActiveView.MONTH_VIEW);
		
		contentPane.add(dayView,ActiveView.DAY_VIEW.name());
		contentPane.add(weekView,ActiveView.WEEK_VIEW.name());
		contentPane.add(monthView,ActiveView.MONTH_VIEW.name());
		
		ActionListener notImplemented = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,ApplicationSession.instance().getString("notimplemented"));				
			}			
		};
	
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,agendaViewPanel, contentPane);
		this.setContentPane(splitPane);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;		
		JMenuItem menuItem;
		JMenu sousMenuItem;
		
		/* File Menu */
		menu = new JMenu(ApplicationSession.instance().getString("file"));
		
		menuItem=new JMenuItem(ApplicationSession.instance().getString("load"));
		menuItem.addActionListener(notImplemented);
		menu.add(menuItem);
		menuItem=new JMenuItem(ApplicationSession.instance().getString("save"));
		menuItem.addActionListener(notImplemented);
		menu.add(menuItem);
		menuItem=new JMenuItem(ApplicationSession.instance().getString("quit"));
		menuItem.addActionListener(notImplemented);
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		/* Edit Menu */
		menu = new JMenu(ApplicationSession.instance().getString("edit"));
		
		sousMenuItem=new JMenu(ApplicationSession.instance().getString("view"));
		
		menuItem=new JMenuItem(ApplicationSession.instance().getString("month"));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				layerLayout.show(contentPane,ActiveView.MONTH_VIEW.name());		
				ApplicationSession.instance().setActualView(ActiveView.MONTH_VIEW);
			}			
		});
		sousMenuItem.add(menuItem);
		
		menuItem=new JMenuItem(ApplicationSession.instance().getString("week"));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				layerLayout.show(contentPane,ActiveView.WEEK_VIEW.name());
				ApplicationSession.instance().setActualView(ActiveView.WEEK_VIEW);
			}			
		});
		sousMenuItem.add(menuItem);
		
		menuItem=new JMenuItem(ApplicationSession.instance().getString("day"));
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				layerLayout.show(contentPane,ActiveView.DAY_VIEW.name());
				ApplicationSession.instance().setActualView(ActiveView.DAY_VIEW);
			}			
		});
		sousMenuItem.add(menuItem);
		
		menu.add(sousMenuItem);
		
		menuBar.add(menu);
		
		/* Help Menu */
		menu = new JMenu(ApplicationSession.instance().getString("help"));
		
		menuItem=new JMenuItem(ApplicationSession.instance().getString("display"));
		menuItem.addActionListener(notImplemented);
		menu.add(menuItem);
		menuItem=new JMenuItem(ApplicationSession.instance().getString("about"));
		menuItem.addActionListener(notImplemented);
		menu.add(menuItem);
		
		menuBar.add(menu);
		
		
		this.setJMenuBar(menuBar);
		this.pack();
	}
	
	public SchedulerFrame() {
		super();
		
		addWindowListener (new WindowAdapter(){
			public void windowClosing (WindowEvent e){
				System.exit(0);
			}
		});
		contentPane = null;
		dayView = null;
		weekView = null;
		monthView = null;
		agendaPanelFactory = null;
		setupUI();

	}
	public SchedulerFrame(String title) {
		super(title);
		addWindowListener (new WindowAdapter(){
			public void windowClosing (WindowEvent e){
				System.exit(0);
			}
		});
		setupUI();
	}
	
	public void majView(){
		ActiveView actualView = ApplicationSession.instance().getActualView();
		contentPane.add(agendaPanelFactory.getAgendaView(ActiveView.DAY_VIEW),ActiveView.DAY_VIEW.name());
		contentPane.add(agendaPanelFactory.getAgendaView(ActiveView.WEEK_VIEW),ActiveView.WEEK_VIEW.name());
		contentPane.add(agendaPanelFactory.getAgendaView(ActiveView.MONTH_VIEW),ActiveView.MONTH_VIEW.name());
		layerLayout.show(contentPane,actualView.name());
	}
	
}
