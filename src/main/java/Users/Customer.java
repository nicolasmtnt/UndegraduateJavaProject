package Users;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Customer{
    
    private EncryptedPassword encryptedPassword;
    private String username;

    public Customer(String username, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.encryptedPassword = new EncryptedPassword(password);
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public void changeUsername(String newusername, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if(encryptedPassword.verify(password)){
            this.username = newusername;
        }
    }

    public void changePassword(String newpassword, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if(encryptedPassword.verify(password)){
            this.encryptedPassword = new EncryptedPassword(password);
        }
    }

    public void searchProduct(){
        //like seller
    }

}