package reader;

import java.io.File;
import java.util.Scanner;

public class Encryptor {
	
	
	public Encryptor() {
		
	}
	
	public void encrypt(Scanner scan, String key, File selectedFile) {
		System.out.println("Would you like to encrypt or decrypt this file? (Type 1 for Encrypt, 2 for Decrypt");
		int option = scan.nextInt();
		while (option != 1 && option != 2) {
			System.out.println("Invalid option, please enter 1 to Encrypt or 2 to Decrypt");
			option = scan.nextInt();
		}
		

		
		if (option == 1) {
			
			try {
				CryptoUtils.encrypt(key, selectedFile, selectedFile);
				System.out.println("File Encrypted Successfully! To Decrypt reopen the program and choose option 2");
				System.out.println("Now Closing the Program...");
				System.exit(0);
			
			} catch (CryptoException ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
				System.out.println("Error occured, the file remains unchanged, exiting program...");
				System.exit(0);
			}
		} else if (option == 2) {

			try {

				CryptoUtils.decrypt(key, selectedFile, selectedFile);
				System.out.println("File Decrypted Successfully! To Encrypt reopen the program and choose option 1");
				System.out.println("Now Closing the Program...");
				System.exit(0);
			} catch (CryptoException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Wrong Encryption Key, the file remains unchanged. You'll have to start over!");
				System.out.println("Now Closing the Program...");
				System.exit(0);
				ex.printStackTrace();
			}
		}
	}
}
