package Users;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import Interface.Searchable;

public abstract class User implements Searchable{

    protected String username;
    protected EncryptedPassword encryptedPassword;

    public User(String username, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.encryptedPassword = new EncryptedPassword(password);
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public void changePassword(String newpassword) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        this.encryptedPassword = new EncryptedPassword(newpassword);
    }

    static void nextCommand(){
        System.out.println("\nNouvelle commande :");
    }

    public abstract void interact(String password) throws NumberFormatException, IOException, NoSuchAlgorithmException;
}
