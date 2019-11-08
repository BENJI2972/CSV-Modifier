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
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
public class ClassePrincipaleWithHeader {

	    private static final String FOLDER_IN = "./CSVFiles/in/";
	    private static final String FOLDER_OUT = "./CSVFiles/out/";
	    
	    private static final String[] COLUMNS_TO_SAVE = {"A"
    			,"B"
    			,"D"
    			,"E"
    			,"F"
    			,"G"
    			,"H"
    			,"J"
    			,"L"
    			,"O"
    			,"P"
    			,"R"
    			,"S"
    			,"T"
    			,"U"
    			,"V"
    			,"W"
    			,"X"
    			,"Y"
    			,"AA"
    			,"AB"
    			,"AC"
    			,"AD"
    			,"AE"
    			,"AF"
    			,"AG"
    			,"AH"
    			,"AI"
    			,"AJ"
    			,"AL"
    			,"AN"
    			,"AO"
    			,"AS"
    			,"AV"
    			,"BD"
    			,"BS"
    			,"BT"
    			,"BU"
    			,"CA"
    			,"CB"
    			,"CC"
    			,"CD"
    			,"CE"
    			,"CF"
    			,"CG"
    			,"CH"
    			,"CI"
    			,"CJ"
    			,"CK"
    			,"CL"};
	    
	    public static void readWriteAndFormatFile(File[] files) throws IOException, InterruptedException {
	    	
	    	
	    	Instant start = Instant.now();
	    	
	    	
	    	System.out.println("=================== D�but du formatage  ===================");
	    	System.out.println("=> Dossier d'entr�e : "+new java.io.File( FOLDER_IN ).getCanonicalPath());
	    	System.out.println("=> Dossier de sortie : "+new java.io.File( FOLDER_OUT ).getCanonicalPath());	    	
	    	
	    	// Calcul colonnes � sauvegarder
	    	int[] colonnes = new int[COLUMNS_TO_SAVE.length];
		    char[] temp;
		    for(int jo=0; jo<colonnes.length; jo++) {
		    	temp = COLUMNS_TO_SAVE[jo].toCharArray();
		    	if(temp.length==1)
		    		colonnes[jo]=(temp[0]-64-1);
		    	else
		    		colonnes[jo]=(temp[1]-64+(26*(temp[0]-64))-1);
		    }
		    System.out.println("Colonnes � sauvegarder -> ");
		    for(int ij=0;ij<colonnes.length; ij++)
		    	System.out.println(colonnes[ij] + " "+ COLUMNS_TO_SAVE[ij]);
		    
	    	
	    	// Suppression des fichiers d�j� pr�sent dans le dossier "out"
	    	for(File fle: new File(FOLDER_OUT).listFiles()) 
	    	    if (!fle.isDirectory()) 
	    	        fle.delete();
	    	
	    	for(int i=0; i<files.length; i++) {
	    		File file = files[i];
	    		final String outPath = FOLDER_OUT+file.getName().substring(0, file.getName().lastIndexOf('.'))+"Modified.csv";
	    		
	    		System.out.println("Fichier en cours : "+file.getName()+" -> "+(i+1)+"/"+files.length);
	    		
	    		String date = file.getName().charAt(0)+""+file.getName().charAt(1)+"-07-07 ";
	    		
	    		//System.out.println("date"+date);
	    		
	    		BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
		    	BufferedWriter bw = new BufferedWriter(new FileWriter(outPath));
		    	
		    	//ArrayList<String[]> lignes= new ArrayList<String[]>();
	    		
		    	String line = "";
		    	
		    	int count=0;
		    	String current = "couocu";
		    	
		    	// skip 2 line-header
		    	//br.readLine();
		    	//br.readLine();
		    	//br.readLine();
		    	
		    	
		    	
				while((line = br.readLine()) != null) {
					
				    String[] vals = line.split(";|,");
				    
				    // TODO
				    // => Modifications � apporter au fichier
				    	//2. Suppression des colonnes non utilis�es
				    	//		( ajout uniquement des colonnes utilis�es donc)
				    	
				    

				    ArrayList<String> values = new ArrayList<String>();
				    for(int k: colonnes)
			    		values.add(vals[k]);
				    /*
				     * // transfert dans ArrayList
				     * for(String string : vals)
				     * values.add(string);
				     * ArrayList<String> values = new ArrayList<String>();
				     */
				    	
				    
				    //lignes.add(values);

				    // TODO
				    // => Modifications � apporter au fichier
				    	//1. Mise en place des secondes
				    	//System.out.println(lignes.get(0)[0]);
				    
				    if(!values.get(0).equals(current)) {
				    	current = values.get(0);
				    	values.set(0,date+values.get(0)+".00");
				    	
				    	count = 1;
				    }else {
				    	if(count<10)
				    		values.set(0, date+values.get(0)+".0"+count);
				    	else
				    		values.set(0, date+values.get(0)+"."+count);
				    	count++;
				    }
				    
				    
				    		
				    	
				    
							    
							    
				    
				    String lineText = "";
				    	
				    // Remise en place des points virgules
				    for(int j = 0; j<values.size(); j++)
				    	values.set(j, values.get(j)+";");
				    // D�finition d'une ligne
				    for(String var : values)
				    	lineText += var;
				    
				    
				    
				    
				    /*
				    System.out.println("taille : "+values.size());
				    for(int jo=0; jo<values.size(); jo++)
				    	System.out.println(jo+" : "+values.get(jo));
				    Thread.sleep(20000);
				    */
				    
				    
				    
				    
				    bw.write(lineText);
				    bw.newLine();
				}
				
				br.close();
				bw.close();
	    		
	    	}
	    	    	
	    	System.out.println("=================== Fin du formatage ===================");
	    	
	    	Instant end = Instant.now();
	    	System.out.println("Temps execution => "+Duration.between(start, end).getSeconds()+" secondes");
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
	            
	            int total_space=0;
	            
	            for(File file : files) {
	            	System.out.println(" -> "+file.getName());
	            	total_space+=file.length();
	            }
	            
	            System.out.println("Total files size : "+total_space/(1024*1024)+" MB");
	            
	            
	            
	            return files;
	       }
	    	
	    	return null;
	    
	    }
	    
	    
	    public static void copieCsv() throws IOException {
	    	System.out.println("=================== D�but copie test ===================");
	    		    	
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
			    // D�finition d'une ligne
			    for(String var : values)
			    	lineText += var;
			    
			    bw.write(lineText);
			    bw.newLine();
			}
			
			br.close();
			bw.close();
			
			System.out.println("=================== Fin copie test ===================");
			System.out.println(lignes.get(0)[0]);
	        
	        System.out.println("Copie du fichier CSV termin�e !");

	    }
	    
	    
	    public static void main(String[] args) throws IOException, InterruptedException {
	    	    	
	    	readWriteAndFormatFile(listeFichiers());
	    	
	    }
	    
	
}
