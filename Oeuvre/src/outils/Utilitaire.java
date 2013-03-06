package outils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilitaire {

	public Utilitaire() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static  Date StrToDate(String unedate, String unformat) 
	// le format est une combinaison de MM dd yyyy avec / ou – 
	// exemple  dd/MM/yyyy
	     throws Exception 
	 {
	     Date datesortie;
	   // on définit un format de sortie  
	   SimpleDateFormat defFormat = new SimpleDateFormat(unformat);
	   datesortie = defFormat.parse(unedate);
	   return datesortie;
	}
}
