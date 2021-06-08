package reader;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import reader.CryptoUtils;

public class Main {
//This project is a software tool to encrypt files by playing a song. 
	public static void main(String[] args) {
		
		File selectedFile = new File(" ");
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to the MIDI Encryptor!");
		System.out.println("Choose the file you would like to encrypt or decrypt");
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.showOpenDialog(null);
		selectedFile = fileChooser.getSelectedFile();
		System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		Grabber grabs = new Grabber(selectedFile, scan);
		MidiHandler mh = new MidiHandler(grabs.getLimit(), grabs.getTempo(), selectedFile);	//This method will let the user know to start playing, and when to verify
		

	
	}
	public static void Fuse() {
		
	}
}

	
/**
 * Special thanks to (Credit for quoted code and assistance in the MIDI and
 * encryption writing): Encryption Code adapted from: A tester for the
 * CryptoUtils class, Crypto Exception and CryptUtilsTest are 100% his. Thank
 * you for sharing your code with the world.
 * 
 * @author Nam Ha Minh of www.codejava.net
 *
 *         Thanks to @author Yeti for
 *         https://stackoverflow.com/questions/1485307/java-midi-getting-data-from-piano
 *
 *         Thanks to @author sjlevine29
 *         forhttps://stackoverflow.com/questions/6937760/java-getting-input-from-midi-keyboard
 *
 *         The MidiController, MidiHandler and Scribe classes paraphrased from
 *         these two. Some of their original comments explain what's happening
 *         better than I could, and remain here for that reason.
 */
