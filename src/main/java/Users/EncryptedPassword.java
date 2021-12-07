package Users;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptedPassword {

    private String encryptedPassword;
    
    public EncryptedPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        this.encryptedPassword = new BigInteger(1, crypt.digest()).toString(16);
    }

    public String getEncryptedPassword(){
        return this.encryptedPassword;
    }

    public Boolean verify(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        return this.encryptedPassword.equals(new BigInteger(1, crypt.digest()).toString(16));
    }
}
