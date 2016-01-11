package edu.iut.gui.frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.iut.app.ApplicationSession;
import edu.iut.app.Document;
import edu.iut.app.ExamEvent;
import edu.iut.app.Person;
import edu.iut.app.Person.PersonFunction;

/**
 * <b>JuryChoiceDialog est la classe graphique permettant de déterminer les différents documents remis pour une soutenance.</b>
 */
public class DocumentChoiceDialog extends JDialog{

	JButton docButton;
	ExamEvent exam;
	ArrayList<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
	
	public DocumentChoiceDialog(JDialog container, JButton doc, ExamEvent exam){
		super(container,ApplicationSession.instance().getString("choosestudent"),true);
		this.docButton=doc;
		this.exam=exam;
		
		this.setupUI();
		
		this.pack();
		this.setLocationRelativeTo(container);
		this.setVisible(true);
			
	}
	
	public void setupUI(){
		
		this.setLayout(new BorderLayout());
		
		JPanel centre = new JPanel(new GridLayout(0,1));
		
		
		for(Document doc : ApplicationSession.instance().getAgenda().getDocuments()){
			JCheckBox chkBox = new JCheckBox(doc.getDocumentURI());
			if(exam.getDocuments()!=null)
				if(exam.getDocuments().contains(doc))chkBox.setSelected(true);
			this.checkboxes.add(chkBox);
			centre.add(chkBox);
		}
		
		this.add(centre, BorderLayout.CENTER);
		
		JPanel south = new JPanel();
		JButton save = new JButton(ApplicationSession.instance().getString("save"));
		
		
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int nbDoc = 0;
				int i=0;
				ArrayList<Document> listeDoc = new ArrayList<Document>();
				
				for(JCheckBox checkbox : checkboxes){
					if(checkbox.isSelected()){
						nbDoc++;
						listeDoc.add(ApplicationSession.instance().getAgenda().getDocuments().get(i));
					}
					i++;
				}
				docButton.setText(String.valueOf(nbDoc) + " " + ApplicationSession.instance().getString("documents"));
				 
				exam.setDocuments(listeDoc);
				CloseDialog();		
			}			
		});
		
		
		south.add(save);
		
		this.add(south, BorderLayout.SOUTH);
		
	}
	
	public void CloseDialog(){
	    super.dispose();
	}
	
}
