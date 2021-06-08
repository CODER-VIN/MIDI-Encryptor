package reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.ShortMessage;

/**Writes the relevant MIDI data down*/
public class Scribe {
	static ArrayList<MusicNote> password;	
	static ArrayList<MusicNote> challenge;
	static Encryptor gopher;
	static int notePlayed = 0;	//The literal note
	static  int noteTracker = 0;	//The number of notes from the start of the read process that have been recorded    
	static int characterLimit; 
	static int tick = 0; 	//The step of the program we're on
	static boolean matched = false; 
	static long startTimes[];
	static long endTimes[];
	static int noteValues[];	//The array of Literal Notes
	static double pwSpaceTimes[];	//The spaceTimes are recorded AFTER the notes are recorded, too much hassle to do it all at once
	static double chSpaceTimes[];
	long holder = 0;
	static Grabber grabbie;
	File theFile;
	int BPM = 120; 
	MidiController mc;
	Encoder encoder; 
	Grabber grabs;
	String theCH;
	Scanner scan; 
	public Scribe(int capacity, int tempo, File file) {
		System.out.println("****************************");
		System.out.println("Please wait while your MIDI devices are loaded");
		password = new ArrayList<MusicNote>(capacity);
		challenge = new ArrayList<MusicNote>(capacity); 
		startTimes = new long[capacity];
		endTimes = new long[capacity];
		noteValues = new int[capacity]; 	
		pwSpaceTimes = new double[capacity - 1];
		chSpaceTimes = new double[capacity - 1];
		characterLimit = capacity; 
		BPM = tempo;
		encoder = new Encoder(); 
		scan = new Scanner(System.in);
		theFile = file;
		gopher = new Encryptor();
		
		try {
			mc = new MidiController();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/** Handles MIDI Logic, ie. when to start and stop taking data*/
	public void read(ShortMessage sm, long timeStamp) {
		//if (noteTracker >= characterLimit) System.out.println("Right here boss");
		//System.out.println("T minus " + noteTracker);
		if(sm.getCommand() == 144) {
			notePlayed = sm.getData1();
    		noteValues[noteTracker] = notePlayed; 
			startTimes[noteTracker] = timeStamp;
			mc.playNote(notePlayed);
			
		}else if (sm.getCommand() == 128) {  
    			endTimes[noteTracker] = timeStamp;
    			mc.stopNote(notePlayed);
    			if (tick == 0) { 
    				writePassword(new MusicNote(startTimes[noteTracker], endTimes[noteTracker], noteValues[noteTracker])); 
    			}
    			if (tick == 1) {
    				writeChallenge(new MusicNote(startTimes[noteTracker], endTimes[noteTracker], noteValues[noteTracker]));
    			}
    		
    			noteTracker++;
    			
    			
    			if (noteTracker >= characterLimit && tick == 0) {
					challenge();
				}
    			if (noteTracker >= characterLimit && tick == 1) {
    				String thePW = encoder.encode(password, pwSpaceTimes, BPM);
    				theCH = encoder.encode(challenge, chSpaceTimes, BPM);
    				if(thePW.equals(theCH)) {
    					System.out.println("The passwords are the same");
    					matched = true; 
    					gopher.encrypt(scan, thePW, theFile);
    					
    				}	else {
    					System.out.println("The passwords were not the same, please try again");
    					tick = 0;
    					noteTracker = 0;
    					thePW = "";
    					theCH = "";
    					password = new ArrayList<MusicNote>(characterLimit);
    					challenge = new ArrayList<MusicNote>(characterLimit); 
    					startTimes = new long[characterLimit];
    					endTimes = new long[characterLimit];
    					noteValues = new int[characterLimit]; 	
    					pwSpaceTimes = new double[characterLimit - 1];
    					chSpaceTimes = new double[characterLimit - 1];
    				}
    				/*System.out.println(thePW);
    				System.out.println(theCH);
    				System.out.println("Thank you for using this program... Terminating");
    				System.exit(1);
    				*/
    			}
    			
    		}
    		
      //  System.out.println("midi received" + " " + sm.getStatus() + " " + timeStamp/1000  + " " + notePlayed + " tracker: " + noteTracker );  
        
	}
	
	
	public void writePassword(MusicNote note) {
			password.add(note);
	
		
	}
	
	public void writeChallenge(MusicNote note) {

			challenge.add(note);

	}
	
	public String getKey() {
		return theCH;
	}
	
	public void challenge() {
		System.out.println("Please Verify your song password by playing it again"); 
		tick++;
		noteTracker = 0;
		//System.out.println(" The values are + " + tick + " " + "noteTracker " + noteTracker);
		
	}


	
	public ArrayList<MusicNote> getPassword() {
		return password;
	}
}
