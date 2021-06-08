package reader;

public class Record {		
	
	long start = 0;
	long end = 0;
	int value = 0;	//The "note", A-G#, as a numerical value according to MIDI spec
	long length = 0; 	//The length of the 
	
	public Record() {
		
	}
	
	public Record(int length, long starts[], long ends[],int notes[]) {
		System.out.println("REACHED!");
	}	
	

	public void setStart(long start) {
		this.start = start;
	}
	
	public void setEnd(long end) {
		this.end = end;
	}
	
	public void setValue(int value) {
		this.value = value;  
	}
	
	public void challenge() {
		
	}
	
	
	
}
