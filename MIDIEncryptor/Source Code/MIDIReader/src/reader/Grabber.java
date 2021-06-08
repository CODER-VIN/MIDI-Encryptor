package reader;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Grabber {
	int charLimit;
	int tempo;
	static File selectedFile;
	public Grabber(File selectedFile, Scanner scan) {
		this.selectedFile = selectedFile; 
		System.out.println("What is the length of the song (how many notes)? Then Press Enter.");
		charLimit = scan.nextInt();
		System.out.println("Enter a tempo for your song (30-200) and press Enter. If unsure type 100.");
		tempo = scan.nextInt();
		//System.out.println("Metronome Playing...");
		//TODO: Metronome Code
	}
	
	
	public int getLimit() {
		return charLimit;
	}

	public int getTempo() {
		return tempo;
	}
}

	
