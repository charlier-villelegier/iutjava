package edu.iut.app;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;

/**
 * <b>CommandLineParser est la classe qui permet de gérer les options dans la ligne de commande.</b>
 * <p>Un parser est caractérisé par :
 * <ul>
 * <li>La liste des options en arguments</li>
 * <li>La liste des erreurs</li>
 * </ul>
 * </p>
 */
public class CommandLineParser {
	
	protected HashMap<String, CommandLineOption<?>> options;
	protected ArrayList<String> parseErrors;
	
	/**
	 * Constructeur vide CommandLineParser.
	 * 
	 * <p>
	 * Ce constructeur crée une instance de CommandLineParser et initialise les deux attributs de la classe.
	 * </p>
	 */
	public CommandLineParser() {
		options = new HashMap<String, CommandLineOption<?> >();
		parseErrors = new ArrayList<String>();
	}
	
	/**
	 * Ajoute une option à la liste des options.
	 * 
	 * @param option
	 * 			L'option à ajouter.
	 */
	public void addOption(CommandLineOption<?> option) {
		if (option != null) {
			options.put(option.getKey(),option);
		}
	}
	
	/**
	 * Divise et lance les différentes options passées en argument.
	 * 
	 * @param args
	 * 			La liste des options passées dans la ligne de commande.
	 */
	public void parse(String[] args) {
		for (String argument: args) {
			String[] keyValue=argument.split("=");
			if (options.containsKey(keyValue[0])) {
				switch (((CommandLineOption<?>)options.get(keyValue[0])).getOptionType()) {
					case FILE:
						CommandLineOption<File> fileOption = (CommandLineOption<File>)options.get(keyValue[0]);
						if (keyValue.length == 2) {
							fileOption.setValue(new File(keyValue[1]));
						}
						else {
							parseErrors.add("Option should have a key and a value.");
						}
						break;
					case STRING:
						CommandLineOption<String> stringOption = (CommandLineOption<String>)options.get(keyValue[0]);
						if (keyValue.length == 2) {
							stringOption.setValue((new String(keyValue[1])));
						}
						else {
							parseErrors.add("Option should have a key and a value.");
						}
						break;
					case INTEGER:
						CommandLineOption<Integer> integerOption = (CommandLineOption<Integer>)options.get(keyValue[0]);
						if (keyValue.length == 2) {
							integerOption.setValue(new Integer(keyValue[1]));
						}
						else {
							parseErrors.add("Option should have a key and a value.");
						}
						break;
					case DOUBLE:
						CommandLineOption<Double> doubleOption = (CommandLineOption<Double>)options.get(keyValue[0]);
						if (keyValue.length == 2) {
							doubleOption.setValue(new Double(keyValue[1]));
						}
						else {
							parseErrors.add("Option should have a key and a value.");
						}
						break;

					default:
						parseErrors.add("Unrecognize option type.");						
				}
			}
		}
	}
	
	/**
	 * Retourne la clé de l'option si elle en contient une.
	 * 
	 * @param key
	 * 			La clé de l'option.
	 * @return La clé de l'option.
	 */
	public CommandLineOption<?> getOption(String key) {
		if (options.containsKey(key)) {
			return options.get(key);
		}
		return null;
	}
	
	/**
	 * Retourne la liste des erreurs de parse.
	 * 
	 * @return La liste d'erreurs.
	 */
	public ArrayList<String> getErrors() {
		return parseErrors;
	}
	
}
