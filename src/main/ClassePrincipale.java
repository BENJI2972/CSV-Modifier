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
public class ClassePrincipale {

	    private static final String SAMPLE_CSV_FILE_PATH = "./usersSC.csv";
	    private static final String CSV_IN = "./usersSC.csv";
	    private static final String CSV_OUT = "./usersSCO.csv";
	    private static final String FOLDER_IN = "./CSVFiles/in/";
	    private static final String FOLDER_OUT = "./CSVFiles/out/";
	    /*
	    public enum Clock{
	    	Heure,
	    	Minute,
	    	Seconde;
	    }
	    
	    public static Integer returnClock(Clock clock) {
	    	switch(clock) {
	    	case Heure:
	    		break;
	    	case Minute:
	    		break;
	    	case Seconde:
	    		break;
	    	}
	    	return null;
	    }
	    */
	    
	    public static void writeAndFormatFile(File file) throws IOException {
	    	System.out.println("---> Path : "+file.getPath()+" Name : "+file.getName());
	    	
	    	//Reader init
	        Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
	    	CSVReader csvReader = new CSVReader(reader,';');
	    	
	    	//Writer init

	    	
	    	//final String outPath = file.getAbsolutePath()+"bis";
	    	final String outPath = FOLDER_OUT+file.getName().substring(0, file.getName().lastIndexOf('.'))+"bis";
	    	
	    	//StringWriter writer = new StringWriter();
	    	//CSVWriter csvWriter = new CSVWriter(writer, '#', '\'');
	    	//CSVWriter csvWriter = new CSVWriter(new FileWriter(outPath), ';');
	    	CSVWriter csvWriter = new CSVWriter(new FileWriter(outPath), ';');
	    	
	    
	        // Reading Records One by One in a String array
	        String[] nextRecord;
	        String currentDate = "";
	        int numbDate=0;
	        csvReader.readNext();
	        csvReader.readNext();
	        
	        while ((nextRecord = csvReader.readNext()) != null) {
	        	if(nextRecord[0]!=currentDate) {
	        		numbDate=0;
	        		currentDate=nextRecord[0];
	        	}
	        		
	        	nextRecord[0] = nextRecord[0]+numbDate;
	        	csvWriter.writeNext(nextRecord);
	        	
	        	numbDate++;
	        }
	        
	        
	        csvWriter.close();
	        
	        csvReader.close();
	        
	        System.out.println("Formatage du fichier CSV terminée !");
	    	
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
	            File arr[] = maindir.listFiles(); 
	              
	            System.out.println("**********************************************"); 
	            System.out.println("Files from main directory : " + maindir); 
	            System.out.println("**********************************************");
	            
	            return arr[0];
	            
	       }  
	    	return null;
	    }
	    public static void listeFichiers() throws IOException {
	    	File maindir = new File(FOLDER_IN);
	    	
	    	if(maindir.exists() && maindir.isDirectory()) 
	        { 
	            // array for files and sub-directories  
	            // of directory pointed by maindir 
	            File arr[] = maindir.listFiles(); 
	              
	            System.out.println("**********************************************"); 
	            System.out.println("Files from main directory : " + maindir); 
	            System.out.println("**********************************************");
	            
	            for(File i : arr)
	            	writeAndFormatFile(i);
	            
	       }  
	    	
	    }
	    
	    
	    public static void copieCsv() throws IOException {
	    	//Reader init
	        Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
	    	CSVReader csvReader = new CSVReader(reader,';');
	    	
	    	//Writer init
	    	
	    	//StringWriter writer = new StringWriter();
	    	//CSVWriter csvWriter = new CSVWriter(writer, '#', '\'');
	    	CSVWriter csvWriter = new CSVWriter(new FileWriter(CSV_OUT), ';');
	    
	        // Reading Records One by One in a String array
	        String[] nextRecord;
	        csvReader.readNext();
	        csvReader.readNext();
	        
	        while ((nextRecord = csvReader.readNext()) != null) {
	        	csvWriter.writeNext(nextRecord);
	        }
	        
	        
	        csvWriter.close();
	        csvReader.close();
	        
	        System.out.println("Copie du fichier CSV terminée !");

	    }
	    
	    
	    public static void main(String[] args) throws IOException, InterruptedException {
	    	System.out.println("Début ! ===================");
	    	
	    	//copieCsv();
	    	//listeFichiers();
	    	
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
			
	    }
	    
	
}
