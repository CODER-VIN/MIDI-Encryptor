package reader;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
    public class MidiHandler
{   
    	

    	public Scribe writer;
    public MidiHandler(int charLimit, int tempo, File file)
    {
    	
    	writer = new Scribe(charLimit, tempo, file);
    	MidiDevice device;
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        for (int i = 0; i < infos.length; i++) {
            try {
            device = MidiSystem.getMidiDevice(infos[i]);
            //does the device have any transmitters?
            //if it does, add it to the device list
           // System.out.println(infos[i]);

            //get all transmitters
            List<Transmitter> transmitters = device.getTransmitters();
            //and for each transmitter

            for(int j = 0; j<transmitters.size();j++) {
                //create a new receiver
                transmitters.get(j).setReceiver(
                        //using my own MidiInputReceiver
                        new MidiInputReceiver(device.getDeviceInfo().toString())
                );
            }

            Transmitter trans = device.getTransmitter();
            trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));

            //open each device
            device.open();
            //if code gets this far without throwing an exception
            //print a success message
           System.out.println(device.getDeviceInfo()+" is ready" );


        } catch (MidiUnavailableException e) {}
    }
        System.out.println("****************************");
        System.out.println("Remember your song is " + charLimit + " notes!");
        System.out.println("Play your song! When you are done the program will automatically move on.");


}
//tried to write my own class. I thought the send method handles an MidiEvents sent to it
public class MidiInputReceiver implements Receiver {	//TODO: Prompt to enter song length, make record object with those values 
    public String name;      
    public MidiInputReceiver(String name) {
        this.name = name;
    }
    //144 is note one, 128 is note off
    public void send(MidiMessage msg, long timeStamp) {
    	ShortMessage sm = (ShortMessage) msg;
    	int channel = sm.getChannel();
    	writer.read(sm, timeStamp);
    	      
    }
    
    public void close() {}
    }
}