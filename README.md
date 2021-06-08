# MIDI-Encryptor

The Midi encryptor is a program that lets you use a MIDI device to create an encyrption key.

The way this program works is by converting MIDI data to what is essentially a shorthand sheet music where each note (whole, half, quarter, etc, as well as A-G) is converted to a series of characters, which is fed through Java's AES encryption function. The program has a few quirks so please watch the video before using (sorry for the orientation). 

This could certainly be written in such a way to eliminate the need for you to enter your tempo information, and the program's control flow could definetly use some work, but I consider this project done as far as putting anymore work into it. Feel free to use this code however you want though, this couldn't have happened without kind internet people so please feel free to use this if you find it useful. 


Video Explanation: https://www.youtube.com/watch?v=Dn4Vkgp_29g&t=5s

Written Explanation:

WARNING: THIS PROGRAM HAS THE POTENTIAL TO MAKE YOUR FILES UNUSABLE IF YOU AREN’T CAREFUL. ONCE A FILE IS ENCRYPTED THE ONLY WAY TO GET IT BACK IS BY KNOWING YOUR SONG. THERE IS NO OTHER RECOVERY METHOD. IF YOU FORGET YOUR SONG, THE FILE IS GONE. THIS PROGRAM IS PROVIDED AS IS. USE AT YOUR OWN RISK!

Opening:

If on Windows, double click Encryptor.bat and the program should open automatically.

If on MacOS or Linux you’ll have to run the .jar file from the terminal. Cd to its directory and run “java -jar MIDIEncryptor.jar”

Running:

1. The program will begin by asking you to choose a file, any file can be chosen, but note that choosing larger executable files may take a while to load. 

2. The program will ask for the length of the song (count chords as their total number of notes. So if you play two triads as your song type “6”). 

3. The program will ask for the tempo of your song - No metronome is provided, type 100 if you are unsure, or are indifferent to knowing the tempo of the song. Note that you need to enter the same tempo for encryption and decryption of the same file. 

4. The program will ask if you wish to encrypt or decrypt the file. 

5. The encryption will always work if you get to that point. If you choose to decrypt the file and the song you played is not the same the song you used to encrypt it THE PROGRAM WILL NOT DECRYPT AND WILL TERMINATE. You may re-run the program to try again. 

The following citations and thank yous are also listed in the source code, here for completeness. 

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
