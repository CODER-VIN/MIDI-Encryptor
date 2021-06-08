package reader;

public class Metronome {
	float interval;
	int count;
	
	
	public Metronome(float bpm) {
		interval = 44100; // 1 beat per second, by default
		count = 0;
		
	}
	void setBPM( float bpm ) {
	    interval = ( bpm / 60 ) * 44100 ;
	}
	void generateMetronomeSamples( short[] s ) {
	    for( int i=0; i<s.length; ++i ) {
	       s[i] = 0;
	       ++count;
	       if( count == 0 ) {
	          s[i] = Short.MAX_VALUE;
	       }
	       if( count == interval ) {
	          count = 0;
	       }
	    }
	}
}
