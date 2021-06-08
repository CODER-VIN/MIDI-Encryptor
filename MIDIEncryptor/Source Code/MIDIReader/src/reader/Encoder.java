package reader;

import java.util.ArrayList;

//Takes a song and encodes it into a string of length 32 to be used as an AES key
/**
 * 
 * @author vincentgrasty
 *It does this by having a table of unicode characters, the attributes of the song file are as follows:
 *NoteValue, startTime, endTime. int long long
 *The song is an ArrayList<> of Note objects each containing those three values. Each song has associated with it a list
 *of empty space times equal to the number of notes in the song minus one 
 *The "TOLERANCE" can be applied by having each of the three temporally relevant values (start, end, blankSpace) be saved as two values
 *I'm gonna try to use "Basic Latin Script" -The ASCII subset consisting of 95 characters. 	
 */
public class Encoder {
	
	public Encoder() {
		
	}
	//TODO: So once the tenth note is pressed, run the current key through some kind of algorithm based on that note
	//Encoded picking arbitrary characters to represent the values from sixteenth note up to [any value greater than 5 and 14/16ths] notes (0-93,
	//giving me 94 values UTC +0021 to UTC +007E) decending
	//NOTE: dur is already in milliseconds by the time it gets here
	/**
	 * 
	 * @param dur The length of time to be encoded
	 * @param BPM
	 * @return
	 */
	private char getRef(double dur, int BPM) {

		double beathold = 60.0/BPM * 1000;
		long beat = (long) beathold;	//The length of the beat in ms
		long bar = beat * 4; 
		
		/* 92 because the usable Unicode values for this are 34-126,  33 is '!' which already
		 *  has a meaning (new note) and 32 is ' '. 127 and up are reserved for commands, 
		 *  and there's some values higher but the continuity is broken. */
		
		if(dur <= bar/32.0) return '!';	//If the note is shorter than this min (32nd note)
		if(dur >= beat * (91.5/16)) return ' ';	//If the note is longer than 91.5 subdivisions, it's too long. 
		
		for(int i = 92; i > 0; i--) {
			//This calculation counts the duration of the note in terms  of 16th notes, given the beat, with a <50% swing allowed between notes)
			double q = i - 0.5; 
			if(dur >= bar * (q/16.0)) {
				return (char) (34 + i);
				
			}
		}
		return '¢';
	}
	
	
	/*A piano's highest key is MIDI 108, lowest is MIDI 21. That's 88 keys but 92 are usable. So I'll shift them down. 
	*A0 is 21 and C8 is 108.  126-108 = 18. So I'm adding 18 to these values. This doesn't change the midi that was being read at all, but just
	*makes it so that I can encode the number in a single value. I'll have to pad vaues that are higher, right now it just prints an error
	*If you try to go out of bounds. 
	*
	noteLengthRestnoteLengthRest...*/
	public String encode(ArrayList<MusicNote> song, double[] spaceTimes, int BPM) {
		String text = "";
		int listLength = song.size(); 
		for(int i = 0; i < listLength; i++) {
			text = text.concat(String.valueOf((char)(song.get(i).value + 18))); 
			text = text.concat(String.valueOf(getRef(song.get(i).msLength, BPM)));
			if(i < listLength - 1) text = text.concat(String.valueOf(getRef(spaceTimes[i], BPM)));
		}

		return generateKey(text); 
	}
	
	/**
	 * Generates a 32-byte key based on the song
	 * @return
	 */
	private String generateKey(String song) {
		String key = song; 
		String complexKey = "";
		if(song.length() < 32) {
			for(int i = song.length(); i < 32; i++) {
				key = key.concat("!");
			}
			return key; 
		}
		if(song.length() == 32) {
			return song; 
		}
		
		char digit = 'µ'; 
		if(song.length() > 32) {
			for(int i = 0; i < song.length(); i++) {
				
				if(i % 2 == 0) digit = key.charAt(i);
				else digit = key.charAt(song.length() - 1 - i);
				complexKey = complexKey.concat(String.valueOf(digit));
			}
			String finalKey = "";
			for(int i = 0; i < 32; i++) {
				finalKey = finalKey.concat(String.valueOf(complexKey.charAt(i)));
			}
			
			return finalKey; 
			
		}
		
		System.out.println("Strange error, this code was supposed to be unreachable!");
		return null;
	}
	
}












