package Users;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import Interface.Searchable;

public class Customer implements Searchable{
    
    private EncryptedPassword encryptedPassword;
    private String username;
    private Cart cart = new Cart();

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

    public void addToCart(UUID uuid,Integer quantity){
        cart.addProduct(uuid, quantity);
    }


    

}