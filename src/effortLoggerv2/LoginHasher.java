package effortLoggerv2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
//worked on by luz
public class LoginHasher {
	
	//array of usernames and passwords, each one corresponds to the one that
	//shares the same index
	protected String[] usernames = {"lerodr20", "philswift", "joshli"};
    protected String[] passwords = {"123456", "7892534", "jill335812"};

  
    // Generate a random salt
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hash a password with the provided salt using the SHA-256 algorithm
    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt.getBytes());
            byte[] hashedBytes = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Verify a password against the stored hash and salt
    public static boolean verifyPassword(String providedPassword, String storedSalt, String storedHashedPassword) {
        String hashedPassword = hashPassword(providedPassword, storedSalt);
        if(hashedPassword.equals(storedHashedPassword)) {
        	return true;
        }
        return false;
    }
    //Verify if the user exists
    public boolean verifyUsername(String username) {
    	for(int i=0;i<3;i++) {
    		if(username==usernames[i]) {
    			return true;
    		}
    	}
    	return false;
    }
}