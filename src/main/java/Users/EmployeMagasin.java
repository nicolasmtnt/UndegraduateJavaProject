package Users;


public class EmployeMagasin implements IUtilisateur{
  public String pseudoUnique;
  public String passWord;

  public Client(String pseudoUnique, String passWord){
    this.pseudoUnique = pseudoUnique;
    this.passWord = passWord;

    //On enregistre le nouveau client dans un fihchier nous servant de Base de données
    String PseudoMdr = this.pseudoUnique + " " + this.passWord; //Dans un fichier on stock pseudo et mdp; ex : "Babar soleil31"
    BufferedWriter writer = new BufferedWriter(new FileWriter("../../../src/main/ressources/Users.txt"));
    writer.write(str);
    writer.close();
  }

  public String toString(){
    return "(EmployeMagasin)  Pseudo : " + this.pseudoUnique + "mdp : " + this.passWord;
  }



  public ArrayList<Magasin>() voirListeMagasin(){

  }

  public void choisirMagasin(){

  }

  public ArrayList<Rayon>() voirListeRayon(){

  }

  public void choisirRayon(){

  }

  public ArrayList<Produit>() voirListeProduit(){

  }

  public void choisirProduit(){

  }

  public void acheterProduit(){

  }

  public void ajouterProduitStock(){*

  }

  public void ajouterProduitRayon(){

  }
}

