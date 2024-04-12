package com.vsh8k.mushop.model.AccountSystem;
import com.mysql.cj.exceptions.StreamingNotifiable;
import com.vsh8k.mushop.model.Database.DBConnector;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class Hash {

    // Method to create a hashed password
    public static String createHash(String password) {
        int iterations = 10000; // Number of iterations
        int saltLength = 32;    // Salt length in bytes
        int keyLength = 256;    // Key length in bits

        try {
            // Generate a random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[saltLength];
            random.nextBytes(salt);

            // Hash the password
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();

            // Combine salt and hash
            byte[] combined = new byte[salt.length + hash.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(hash, 0, combined, salt.length, hash.length);

            // Convert to Base64 representation
            return Base64.getEncoder().encodeToString(combined);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to verify the hashed password
    public static boolean verifyPassword(String password, String storedHash) {
        try {
            // Decode the stored hash
            byte[] combined = Base64.getDecoder().decode(storedHash);

            // Extract the salt and hash
            byte[] salt = new byte[32];
            byte[] hash = new byte[combined.length - 32];
            System.arraycopy(combined, 0, salt, 0, 32);
            System.arraycopy(combined, 32, hash, 0, combined.length - 32);

            // Hash the password with the extracted salt
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 10000, 256);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] testHash = skf.generateSecret(spec).getEncoded();

            // Compare the stored hash with the generated hash
            if (testHash.length != hash.length) return false;
            for (int i = 0; i < testHash.length; i++) {
                if (testHash[i] != hash[i]) return false;
            }
            return true;

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getStoredHash(String username, DBConnector db) throws Exception {
        ResultSet rs = db.query("SELECT hash FROM users WHERE login = '" + username + "';");
        if (rs.next()) {
            String server_hash = rs.getString("hash");
            return server_hash;
        }
        else {
            throw new Exception("Wrong credentials!");
        }
    }
}
