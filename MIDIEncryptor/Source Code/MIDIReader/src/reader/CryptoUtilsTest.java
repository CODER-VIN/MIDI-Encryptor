package reader;

import java.io.File;
import java.security.NoSuchAlgorithmException;



/**
 * A tester for the CryptoUtils class.
 * @author www.codejava.net
 *
 */
public class CryptoUtilsTest {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String key = "Mary has one cat1Mary has one ca"; //This has to be of length 32
        //This is to put it into the user folder
        String home = System.getProperty("user.home");
        //NOTE: This code affects existing files, it doesn't write one if it isn't there. All three stay, 
        //For production, "document.txt" will have to be the input (and only) file. 
       
        
        File inputFile = new File(home + File.separator + "Desktop" + File.separator + "document.txt");
        File encryptedFile = new File(home + File.separator + "Desktop" + File.separator + "document.encrypted");
        File decryptedFile = new File(home + File.separator + "Desktop" + File.separator + "document.decrypted");
         
        try {
            CryptoUtils.encrypt(key, inputFile, encryptedFile);
            CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
            System.out.println("I ran");
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

}