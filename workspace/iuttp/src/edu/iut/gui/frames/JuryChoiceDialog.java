package edu.iut.gui.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;

import edu.iut.app.ApplicationSession;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;

public class JuryChoiceDialog extends JDialog{

	JButton juryButton;
	ExamEvent exam;
	
	public JuryChoiceDialog(JDialog container, JButton jury, ExamEvent exam){
		super(container,"Choisir les membre du jury",true);
		this.juryButton=jury;
		this.exam=exam;
		
		this.setupUI();
		
		this.setSize(600, 400);
		this.setLocationRelativeTo(container);
		this.setVisible(true);
		
		
	}

	public void setupUI(){
		
		JPanel panel = new JPanel(new BorderLayout());
	
		final DefaultListModel<String> name = new DefaultListModel<>();
		final DefaultListModel<String> choosenName = new DefaultListModel<>();
		
		for (Person jury : ApplicationSession.instance().getAgenda().getJurys()){
			name.addElement(jury.toString());
		}
		
		if(this.exam.getJury()!=null){
			for (Person jury : exam.getJury()){
				name.removeElement(jury.toString());
				choosenName.addElement(jury.toString());
			}
		}
		
		final JList<String>jury = new JList<String>(name);
		final JList<String>choosenJury = new JList<String>(choosenName);
		
		JButton add = new JButton(">>");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(jury.getSelectedIndex()!=-1){
					choosenName.addElement(name.getElementAt(jury.getSelectedIndex()));
					name.remove(jury.getSelectedIndex());
				}
			}			
		});
		
		JButton remove = new JButton("<<");
		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(choosenJury.getSelectedIndex()!=-1){
					name.addElement(choosenName.getElementAt(choosenJury.getSelectedIndex()));
					choosenName.remove(choosenJury.getSelectedIndex());	
				}
			}			
		});

		JPanel panelGauche = new JPanel(new BorderLayout());
		JPanel panelGaucheButton = new JPanel(new GridLayout(2,1));
		JPanel panelCentre = new JPanel(new GridLayout(1,2));
		
		
		panelGaucheButton.add(add);
		panelGaucheButton.add(remove);
		
		panelGauche.add(panelGaucheButton,BorderLayout.EAST);
		panelGauche.add(jury, BorderLayout.CENTER);
		
		
		panelCentre.add(panelGauche);
		panelCentre.add(choosenJury);
		
		panel.add(panelCentre, BorderLayout.CENTER);
		
		
		JButton addJury = new JButton(ApplicationSession.instance().getString("addjury"));
		addJury.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddPersonDialog dialog = new AddPersonDialog(JuryChoiceDialog.this,name,PersonFunction.JURY);		
			}			
		});
		
		JButton save = new JButton(ApplicationSession.instance().getString("save"));
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				juryButton.setText(choosenName.size() + " " + ApplicationSession.instance().getString("member"));
				ArrayList<Person> listeMembre = new ArrayList<Person>();
				for (Person unJury : ApplicationSession.instance().getAgenda().getJurys()){
					if(choosenName.contains(unJury.toString())){
						listeMembre.add(unJury);
					}
				}
				for (Person jurytest : listeMembre)System.out.println(jurytest.toString()); 
				exam.setJury(listeMembre);
				CloseDialog();		
			}			
		});
		
		JPanel panelSouth = new JPanel(new GridLayout(1,2));
		
		panelSouth.add(addJury);
		panelSouth.add(save);
		
		panel.add(panelSouth, BorderLayout.SOUTH);
		
		this.add(panel);
	}
	
	public void CloseDialog(){
	    super.dispose();
	}
	
}
