package Users;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import Interface.Searchable;

public abstract class User implements Searchable{

    protected String username;
    protected EncryptedPassword encryptedPassword;


    @Override
    public String toString() {
        return this.username+","+this.encryptedPassword.getEncryptedPassword();
    }


    public User(String username, String password, Boolean encrypt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if(encrypt){
            this.encryptedPassword = new EncryptedPassword(password, true);
            this.username = username;
        } else {
            this.encryptedPassword = new EncryptedPassword(password, false);
            this.username = username;
        }
    }

    public User(String username, String password, int x) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.encryptedPassword = new EncryptedPassword(password, true);
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public void changePassword(String newpassword) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        this.encryptedPassword = new EncryptedPassword(newpassword, true);
    }

    static void nextCommand(){
        System.out.print("\nNouvelle commande : ");
    }

    public abstract void interact(String password) throws NumberFormatException, IOException, NoSuchAlgorithmException;
}
