package edu.iut.tools;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import edu.iut.app.Agenda;
import edu.iut.app.ApplicationSession;
import edu.iut.app.CommandLineOption;
import edu.iut.app.CommandLineParser;
import edu.iut.app.Document;
import edu.iut.gui.frames.SchedulerFrame;
import edu.iut.io.XMLProjectReader;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * <b>IUTScheduler est la classe contenant le main et permet donc de lancer l'application
 * en initialisant tous les composants.</b>
 */
public class IUTScheduler {
	public static void main(String[] args) {
		Locale.setDefault(Locale.FRANCE);
		
		
		
		CommandLineParser commandLineParser = new CommandLineParser();
		CommandLineOption<java.io.File> configOption = new CommandLineOption<java.io.File>(CommandLineOption.OptionType.FILE, 
				                                                                           "config","configuration file",
				                                                                           new java.io.File("/tmp"));
		CommandLineOption<String> localeOption = new CommandLineOption<String>(CommandLineOption.OptionType.STRING, 
																			   "locale","en|fr",
																			   new String("fr"));

		CommandLineOption<java.io.File> projectOption = new CommandLineOption<java.io.File>(CommandLineOption.OptionType.FILE, 
				   																			"project","xml project file",
				   																		    new java.io.File("/tmp"));
		
		commandLineParser.addOption(configOption);
		commandLineParser.addOption(localeOption);
		commandLineParser.addOption(projectOption);
		
		
		commandLineParser.parse(args);
		
		ApplicationSession.instance().setCommandLineParser(commandLineParser);
		
		XMLProjectReader xmlreader = new XMLProjectReader();
		try {
			ApplicationSession.instance().setAgenda((Agenda)xmlreader.load(new File(commandLineParser.getOption("project").getValue().toString())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.err.println("Option:"+commandLineParser.getOption("config").getValue());
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        SchedulerFrame mainFrame = new edu.iut.gui.frames.SchedulerFrame("IUT Scheduler");
		        mainFrame.setVisible(true);		        
		    }
		});
	}
	
}
