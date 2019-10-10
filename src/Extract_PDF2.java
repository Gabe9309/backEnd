
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class Extract_PDF2 {
	
	Database database;
	private final String FILE_NAME = "Downloaded_English.pdf";
	private final int TOTAL_QUESTIONS = 100;
	private Storage[] storage_Object;
	private String pdfFileInText;
	File file;
	private String full_Path;
	private Scanner scanner;
	PDDocument document;
	
	//ese an arrayLIst type object, it just looks more skilled lol
	
	Extract_PDF2() throws IOException, ClassNotFoundException, SQLException{
		storage_Object = new Storage[TOTAL_QUESTIONS];
		file = new File(FILE_NAME);
		full_Path = file.getAbsolutePath();
		file = new File(full_Path);
		objectMemoryInitializer();
		loadPDF();
		tab_Cleaner(); //The parsed code came with a bunch of tabs in the sentences that needed to be eliminated.
		parse();
		printer();
		save();
		
	}

//-------------------------------------------------------------------------------------------------------------------------------
	
	private void objectMemoryInitializer() {
		for(int i = 0; i < storage_Object.length; i++) {
			storage_Object[i] = new Storage();
		}
	}
	
	private void loadPDF() throws IOException{
		document = PDDocument.load(file);
		document.getClass();
		
		if(!document.isEncrypted()) {
			PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);

            PDFTextStripper tStripper = new PDFTextStripper();

            pdfFileInText = tStripper.getText(document);
		}
	}
	
//-------------------------------------------------------------------------------------------------------------------------------
	
	private void parse() {
		int index = -1;
		int firstLetterIndex = 0;
		String currentLine;
		String sub_CurrentLine_Five_Chars;
		String sub_CurrentLine_Four_Chars;
		String sub_CurrentLine_Three_Chars;
			
		scanner = new Scanner(pdfFileInText);
	
		while(scanner.hasNext()) {
				
			currentLine = scanner.nextLine();
			
			if(currentLine.length() < 5) //Avoids NullPointer due to empty strings
				continue;
			
			// gotta find a better way to do this
			// TODO find a better more readable way to implement the pattern
			// maybe use a * to cover all the chars
			sub_CurrentLine_Three_Chars = currentLine.substring(0, 3);
			sub_CurrentLine_Four_Chars = currentLine.substring(0, 4);
			sub_CurrentLine_Five_Chars = currentLine.substring(0, 5);
			if((Pattern.matches("\\p{Digit}\\p{Punct}\\p{Space}",sub_CurrentLine_Three_Chars))
					|| (Pattern.matches("\\p{Digit}\\p{Digit}\\p{Punct}\\p{Space}",sub_CurrentLine_Four_Chars))
					|| (Pattern.matches("\\p{Digit}\\p{Digit}\\p{Digit}\\p{Punct}\\p{Space}",sub_CurrentLine_Five_Chars))) {
					index++;
					for(int i = 0; i < currentLine.length(); i++) {
						if(Character.isLetter(currentLine.charAt(i))) {
								firstLetterIndex = i;
								break;
						}
					}
					
					currentLine = currentLine.substring(firstLetterIndex);
					storage_Object[index].add_Question(currentLine);
					//System.out.println(currentLine);
					continue;
			}
			for(int i = 0; i < currentLine.length(); i++) {
				if(currentLine.charAt(i) == '▪') {
				storage_Object[index].add_Answer(currentLine);
				//System.out.println(currentLine);
				}
			}
		}
	}
	
//-------------------------------------------------------------------------------------------------------------------------------
	
	private void printer() {
			for(int i = 0; i < storage_Object.length; i++) {
				System.out.println(storage_Object[i].get_Question());
				
				Iterator<String> iterator = storage_Object[i].get_Answers_Iterator();
				while(iterator.hasNext()) {
					System.out.println(iterator.next());
				}
			}
	}
	
//-------------------------------------------------------------------------------------------------------------------------------
	
	private void tab_Cleaner() {
		pdfFileInText = pdfFileInText.replaceAll("\\t", " ");
	}
	
//-------------------------------------------------------------------------------------------------------------------------------
	
	public void save() throws ClassNotFoundException, SQLException {
		database = new Database_Creator(storage_Object);
	}
	
//-------------------------------------------------------------------------------------------------------------------------------


}
