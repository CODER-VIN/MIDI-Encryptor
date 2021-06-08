package reader;

public class MusicNote {
	long start;
	long end;
	int value; 
	long msLength;
	
	public MusicNote(long start, long end, int value) {
		this.start = start;
		this.end = end;
		this.value = value; 
		msLength = (end - start)/1000; 
	}
	
	public long getStart() {
		return start;
	}
	
	public long getEnd() {
		return end;
	}
	
	//If the MIDI value is under 10 shift the note up an octave, if it's over 99(Eb7) shift it down 3 octaves. 
	//The compression algorithm is expecting a length of 2 for this int so I'm forcing it to be that way here. 
	public int getValue() {
		if(value < 10) {
			return value + 12;
		}
		if(value > 99) {
			return value - 36;	
		}
		return value; 
	}
}
