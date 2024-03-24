package modele;
import java.util.ArrayList;

public class Bateau {
  private Coordonner pos;
  private String sens;
  private int taille;
  private ArrayList<Coordonner> kaze = new ArrayList<>();

  public Bateau(Coordonner pos, String sens, int taille){
    this.pos = pos;
    this.sens = sens;
    this.taille = taille;
    int lat = this.pos.getLatitude();
    int lon = this.pos.getLongitude();
    if(sens.equals("EW")){
      for (int i=lat;i<lat+taille; i++) {
        kaze.add(new Coordonner(i,lon));
      }
    }else{
      for (int i=lon;i<lon+taille; i++) {
        kaze.add(new Coordonner(lat,i));
      }
    }
  }

  /**
  * Accesseur pos
  *@return l'attribut pos
  */
  public Coordonner getCoordonner(){
    return this.pos;
  }
  /**
  * Accesseur Sens du bateau 'NS' ou 'EW'
  *@return l'attribut Sens
  */
  public String getSens(){
    return this.sens;
  }
  /**
  * Accesseur Taille du bateau
  *@return l'attribut taille
  */
  public int getTaille(){
    return this.taille;
  }
  /**
  * Accesseur de toutes les case du bateau(nomé 'kase' car le compilatur le prenais pour 'case')
  *@return l'attribut Kaze
  */
  public ArrayList<Coordonner> getKaze(){
    return this.kaze;
  }
  /**
  *donne le nombre de caze restante dans la ArrayList
  *@return taille de kaze
  */
  public int getNombreDeKazeRestante(){
    return kaze.size();
  }
  /**
  *donne le bateau à un joueur
  *@param j le joueur à qui s'aplique la fonction
  */
  public void placementBateau(AbstractJoueur j){
    for (Coordonner c : kaze) {
      j.modifierGrille(c,"O");
    }
    j.ajouterBateau(this);
  }
  /**
  *dit si un bateau est coulé
  *@return true si le bateau a coulé et false sinon
  */
  public boolean coule(){
    if (getNombreDeKazeRestante() == 0) {
      System.out.println("coulé!");
      return true;
    }
    return false;
  }
  /**
  *simule le faitt de tiré sur un bateau en enlèvent une des case du bateau
  *@param c Coordonner de la case à enlever
  */
  public void tireSurBateau(Coordonner c){
    this.kaze.remove(c);
  }


  public String toString(){
    return "position: "+this.pos.toString() +" sens : "+this.sens;
  }

}
