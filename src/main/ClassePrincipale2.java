package main;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
public class ClassePrincipale2 {

	    private static final String FOLDER_IN = "./CSVFiles/in/";
	    private static final String FOLDER_OUT = "./CSVFiles/out/";
	    
	    public static void readWriteAndFormatFile(File[] files) throws IOException {
	    	
	    	System.out.println("=================== Début du formatage  ===================");
	    	System.out.println("=> Dossier d'entrée : "+new java.io.File( FOLDER_IN ).getCanonicalPath());
	    	System.out.println("=> Dossier de sortie : "+new java.io.File( FOLDER_OUT ).getCanonicalPath());

	    	
	    	
	    	for(int i=0; i<files.length; i++) {
	    		File file = files[i];
	    		final String outPath = FOLDER_OUT+file.getName().substring(0, file.getName().lastIndexOf('.'))+"Modified.csv";
	    		
	    		System.out.println("Fichier en cours : "+file.getName()+" -> "+(i+1)+"/"+files.length);
	    		
	    		BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
		    	BufferedWriter bw = new BufferedWriter(new FileWriter(outPath));
		    	
		    	ArrayList<String[]> lignes= new ArrayList<String[]>();
	    		
		    	String line = "";
		    	
		    	int count=0;
		    	String current = "couocu";
		    	
		    	// skip 2 line-header
		    	br.readLine();br.readLine();
		    	
				while((line = br.readLine()) != null) {
					
				    String[] values = line.split(";");
				    ArrayList<String> vals = new ArrayList<String>();
				    
				    //TEST
				    for(String string : values)
				    	vals.add(string);
				    
				    //lignes.add(values);

				    // TODO
				    // => Modifications à apporter au fichier
				    	//1. Mise en place des secondes
				    	//System.out.println(lignes.get(0)[0]);
				    
				    if(!values[0].equals(current)) {
				    	current = values[0];
				    	values[0]+=".00";
				    	count = 1;
				    }else {
				    	if(count<10)
				    		values[0]+=".0"+count;
				    	else
				    		values[0]+="."+count;
				    	count++;
				    }
				    
				    // TODO
				    // => Modifications à apporter au fichier
				    	//2. Suppression des champs inutile
				    	//System.out.println(lignes.get(0)[0]);
				    
				    	
				    
				    String lineText = "";
				    	
				    // Remise en place des points virgules
				    for(int j = 0; j<values.length; j++)
				    	values[j]+=";";
				    // Définition d'une ligne
				    for(String var : values)
				    	lineText += var;
				    
				    bw.write(lineText);
				    bw.newLine();
				}
				
				br.close();
				bw.close();
	    		
	    	}
	    	    	
	    	System.out.println("=================== Fin du formatage ===================");
	    }
	    
	    public static String nameOfFile(File file) {
	    	if(!file.exists())
	    		return null;
	    	    	
	    	String name = file.getName();
	    	
			return name.substring(0, name.lastIndexOf('.'));
	    }
	    
	    public static File getFirstFile() throws IOException {
	    	File maindir = new File(FOLDER_IN);
	    	
	    	if(maindir.exists() && maindir.isDirectory()) 
	        { 
	            // array for files and sub-directories  
	            // of directory pointed by maindir 
	            File files[] = maindir.listFiles(); 
	              
	            System.out.println("**********************************************"); 
	            System.out.println("File from main directory : " + maindir); 
	            System.out.println("**********************************************");
	            
	            System.out.println(" -> "+files[0].getName());
	            
	            return files[0];
	            
	       }  
	    	return null;
	    }
	    
	    public static File[] listeFichiers() throws IOException {
	    	File maindir = new File(FOLDER_IN);
	    	
	    	if(maindir.exists() && maindir.isDirectory()) 
	        { 
	            // array for files and sub-directories  
	            // of directory pointed by maindir 
	            File files[] = maindir.listFiles(); 
	              
	            System.out.println("**********************************************"); 
	            System.out.println("Files from main directory : " + maindir); 
	            System.out.println("**********************************************");
	            
	            for(File file : files)
	            	System.out.println(" -> "+file.getName());
	            
	            return files;
	       }
	    	
	    	return null;
	    
	    }
	    
	    
	    public static void copieCsv() throws IOException {
	    	System.out.println("=================== Début copie test ===================");
	    		    	
	    	File file = getFirstFile();
	    	final String outPath = FOLDER_OUT+file.getName().substring(0, file.getName().lastIndexOf('.'))+"bis.csv";
	    	BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
	    	BufferedWriter bw = new BufferedWriter(new FileWriter(outPath));
	    	
	    	ArrayList<String[]> lignes= new ArrayList<String[]>();
	    	
			String line = "";
			while((line = br.readLine()) != null) {
			    String[] values = line.split(";");
			    lignes.add(values);

			    String lineText = "";
			    
			    // Remise en place des points virgules
			    for(int i = 0; i<values.length; i++)
			    	values[i]+=";";
			    // Définition d'une ligne
			    for(String var : values)
			    	lineText += var;
			    
			    bw.write(lineText);
			    bw.newLine();
			}
			
			br.close();
			bw.close();
			
			System.out.println("=================== Fin copie test ===================");
			System.out.println(lignes.get(0)[0]);
	        
	        System.out.println("Copie du fichier CSV terminée !");

	    }
	    
	    
	    public static void main(String[] args) throws IOException, InterruptedException {
	    	
	    	
	    	//copieCsv();
	    	//listeFichiers();
	    	
	    	/*
	    	
	    	File file = getFirstFile();
	    	final String outPath = FOLDER_OUT+file.getName().substring(0, file.getName().lastIndexOf('.'))+"bis.csv";
	    	BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
	    	BufferedWriter bw = new BufferedWriter(new FileWriter(outPath));
	    	
	    	ArrayList<String[]> lignes= new ArrayList<String[]>();
	    	
			String line = "";
			while((line = br.readLine()) != null) {
			    String[] values = line.split(";");
			    lignes.add(values);

			    String lineText = "";
			    
			    // Remise en place des points virgules
			    for(int i = 0; i<values.length; i++)
			    	values[i]+=";";
			    // Définition d'une ligne
			    for(String var : values)
			    	lineText += var;
			    
			    bw.write(lineText);
			    bw.newLine();
			}
			
			br.close();
			bw.close();
			
			System.out.println("Terminé ! ===================");
			System.out.println(lignes.get(0)[0]);
			
			*/
			
	    	readWriteAndFormatFile(listeFichiers());
	    }
	    
	
}
