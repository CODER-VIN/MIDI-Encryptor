package reader;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class MidiController {
		//Lowercase chars are the base note, capital is the sharp. So 'c' is "C" and 'C' is "C sharp"
		final int VELOCITY = 600;
		Synthesizer synth = MidiSystem.getSynthesizer();		
		final MidiChannel[] midiChannel = synth.getChannels();
		
		
		
		public MidiController() throws MidiUnavailableException {			
			synth.open(); 
			Instrument[] inst = synth.getDefaultSoundbank().getInstruments();
			synth.loadInstrument(inst[90]);
			
		}
		
		//The integer 60 is middle C
		public void playNote(int note) {
			midiChannel[0].noteOn(note, VELOCITY);
		}
		
		public void stopNote(int note) {
			midiChannel[0].noteOff(note);;
		}
		
		
			
			
}
