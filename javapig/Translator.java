package javapig;

import java.io.BufferedInputStream;
import java.util.Scanner;


public class Translator {

	private static final String exitString = "exit";
	private static final String prompt = "--> ";
	
	public static void main(String[] args) {
		Translator translator = new Translator();
		
		translator.translate();
	}

	public Translator() {
	}
	
	public void translate() {
		String inputLine;
		String outputLine;
		Scanner scanner; 
		
		System.out.print(Translator.prompt);
		
		scanner = new Scanner(new BufferedInputStream(System.in));
		
		// read the first line
		inputLine = scanner.nextLine();
		
		while(!inputLine.equals(exitString)) {
			if(inputLine != null
					&& !inputLine.isEmpty()) {
				outputLine = translateLine(inputLine);
				
				if(outputLine != null
						&& !outputLine.isEmpty()) {
					System.out.println(outputLine);
					System.out.print(Translator.prompt);
				}
					
			}
			
			inputLine = scanner.nextLine();
		}
	}

	private String translateLine(String inputLine) {
		String translatedLine = "";
		String[] rawWordArray;
		Word word;
		
		if(inputLine != null
				&& !inputLine.isEmpty()) {
			rawWordArray = inputLine.split(" ");
			if(rawWordArray.length > 0) {
			
				for(int idx = 0;idx < rawWordArray.length;idx++) {
					word = Word.splitRawWord(rawWordArray[idx]);
					translatedLine += word.toString();
					if(idx < rawWordArray.length -1) {
						translatedLine += " ";
					}
				}
			}
		}
		
		return translatedLine;
	}
	
	
	
	

}
