package main;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
public class ClassePrincipale3 {

	    private static final String FOLDER_IN = "./CSVFiles/datalogd/";
	    private static final String FOLDER_OUT = "./CSVFiles/datalogdOut/";
	    
	    private static final boolean KEEP_HEADER = false;
	    
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
	    	
	    	
	    	System.out.println("=================== Début du formatage  ===================");
	    	System.out.println("=> Dossier d'entrée : "+new java.io.File( FOLDER_IN ).getCanonicalPath());
	    	System.out.println("=> Dossier de sortie : "+new java.io.File( FOLDER_OUT ).getCanonicalPath());	    	
	    	
	    	// Calcul colonnes à sauvegarder
	    	int[] colonnes = new int[COLUMNS_TO_SAVE.length];
		    char[] temp;
		    for(int jo=0; jo<colonnes.length; jo++) {
		    	temp = COLUMNS_TO_SAVE[jo].toCharArray();
		    	if(temp.length==1)
		    		colonnes[jo]=(temp[0]-64-1);
		    	else
		    		colonnes[jo]=(temp[1]-64+(26*(temp[0]-64))-1);
		    }
		    
		    // Calcul colonnes à multiplier par 10
		    
		    //	Affichage colonnes à sauvegarder
		    /*
		    System.out.println("Colonnes à sauvegarder -> ");
		    for(int ij=0;ij<colonnes.length; ij++)
		    	System.out.println(colonnes[ij] + " "+ COLUMNS_TO_SAVE[ij]);
		    */
		    
	    	
	    	// Suppression des fichiers déjà présent dans le dossier "out"
	    	for(File fle: new File(FOLDER_OUT).listFiles()) 
	    	    if (!fle.isDirectory()) 
	    	        fle.delete();
	    	
	    	for(int i=0; i<files.length; i++) {
	    		File file = files[i];
	    		final String outPath = FOLDER_OUT+file.getName().substring(0, file.getName().lastIndexOf('.'))+"Modified.csv";
	    		
	    		System.out.println("Fichier en cours : "+file.getName()+" -> "+(i+1)+"/"+files.length);
	    		
	    		String date = "2007-07-"+file.getName().charAt(0)+""+file.getName().charAt(1)+" ";
	    		
	    		//System.out.println("date"+date);
	    		
	    		BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
		    	BufferedWriter bw = new BufferedWriter(new FileWriter(outPath));
		    	
		    	//ArrayList<String[]> lignes= new ArrayList<String[]>();
	    		
		    	String line = "";
		    	
		    	int count=0;
		    	String current = "couocu";
		    	
		    	// skip 2 line-header
		    	if(!KEEP_HEADER) {
		    		br.readLine();
		    		br.readLine();
		    	}
		    	//br.readLine();
		    	
		    	
		    	
				while((line = br.readLine()) != null) {
					
					// Séparateur de colonnes
				    String[] vals = line.split(";");
				    
				    // TODO
				    // => Modifications à apporter au fichier
				    	//2. Suppression des colonnes non utilisées
				    	//		( ajout uniquement des colonnes utilisées donc)
				    	
				    

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
				    // => Modifications à apporter au fichier
				    	//1. Mise en place des secondes
				    	//System.out.println(lignes.get(0)[0]);
				    
				    if(!values.get(0).equals(current)) {
				    	current = values.get(0);
				    	values.set(0,date+values.get(0)+".00");
				    	count = 4;
				    }else {
				    	if(count<=8)	//	Cas 0 en décimale
				    		values.set(0, date+values.get(0)+".0"+count);
				    	else if(count>=100)
				    		line=null;	//	On annule l'écriture de la ligne courante en renvoyant null dans sa variable
				    	else			//	Cas écriture
				    		values.set(0, date+values.get(0)+"."+count);
				    	count+=4;
				    }
				    
				    
				    		
				    	
				    
							    
				    if(line != null) {	//	On vérifie que la ligne n'est pas vide avant d'écrire.
				    					//		cela permet de vérifier que la ligne a bien été validée précedemment.
					    String lineText = "";
					    	
					    // Remise en place des points virgules sauf la dernière ligne
					    for(int j = 0; j<values.size()-1; j++)
					    	values.set(j, values.get(j)+";");
					    // Définition d'une ligne
					    for(String var : values)
					    	lineText += var;
					    
					    lineText = lineText.replaceAll(",", ".");				    
					    
					    
					    /*
					    System.out.println("taille : "+values.size());
					    for(int jo=0; jo<values.size(); jo++)
					    	System.out.println(jo+" : "+values.get(jo));
					    Thread.sleep(20000);
					    */
					    
					    
					    
					    
					    bw.write(lineText);
					    bw.newLine();
				    }
				    
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
	            	System.out.println(" -> "+file.getName()+" : "+file.length()/(1024*1024)+" MB");
	            	total_space+=file.length()/(1024*1024);
	            }
	            
	            System.out.println("First file size "+files[0].getName()+" -> "+files[0].length()/(1024*1024));
	            System.out.println("Total files size : "+total_space+" MB");
	            
	            
	            
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
	    	    	
	    	readWriteAndFormatFile(listeFichiers());
	    	
	    }
	    
	
}
