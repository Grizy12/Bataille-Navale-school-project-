package modele;

import java.util.*;


public abstract class AbstractJoueur extends AbstractBattleShipEcoutable{

  protected String name;
  protected String[][] affichage; //voir ses tirs
  protected String[][] grille; //voir ses bateaux
  protected ArrayList<Bateau> flotte;
  //sert pour la partie graphique
  protected Coordonner coupGraphique;
  protected ArrayList<Coordonner> coupsJouer;
  protected ArrayList<Bateau> bateauQuiNagentAvecLesPoissons;

  public AbstractJoueur(String n){
    this.name = n;
    this.flotte = new ArrayList<Bateau>();
    this.bateauQuiNagentAvecLesPoissons = new ArrayList<Bateau>();
    this.coupsJouer = new ArrayList<Coordonner>();
    this.affichage = new String[10][10];
    this.grille = new String[10][10];
    for (int i=0;i<10;i++){
      for(int j=0;j<10;j++){
          this.affichage[j][i]=".";
          this.grille[j][i]=".";
      }
    }
  }
  /**
   * Fait changer la vue car ce n'est plus battle ship qui possede l'abstractShipEcoutable
   */
  public void suivant(){
    fireChangement();
  }
  /**
   * Accesseur flotte(tout les bateaux)
   *@return l'attribut flote
   */
  public ArrayList<Bateau> getFlotte(){
    return this.flotte;
  }
  /**
   * Accesseur flotte(tout les bateaux)
   *@return l'attribut flote
   */
  public ArrayList<Bateau> getBateauQuiNagentAvecLesPoissons(){
    return this.bateauQuiNagentAvecLesPoissons;
  }
  /**
  * Accesseur coup Graphique
  *@return l'attribut coupGraphique
  */
  public Coordonner getCoupGraphique(){
    return this.coupGraphique;
  }
  /**
  * Accesseur coupJouer
  *@return l'attribut coupJouer
  */
  public ArrayList<Coordonner> getCoupsJouer(){
    return this.coupsJouer;
  }
  /**
   * remet le coup graphique à null
   */
  public void resetCoupGraphique(){
    this.coupGraphique = null;
  }
  /**
   * Accesseur Grille qui permet de voir ses bateaux
   * @return l'attribut Grille
  */
  public String[][] getGrille(){
    return this.grille;
  }
  /**
   * Accesseur Affichage qui permet de voir ses tirs
   * @return l'attribut Affichage
   */
  public String[][] getAffichage(){
    return this.affichage;
  }
  /**
   * Accesseur de Name
   * @return l'attribut Name
   */
  public String getName(){
    return this.name;
  }
  /**
   * donne une Coordonner à coupGraphique
   *@param c Coordonner du point
  */
  public void coupGraphique(Coordonner c){
    if (c!=null) {
      this.coupGraphique = c;
      this.coupsJouer.add(c);
    }
  }
  /**
  * ajoute un bateau à la flotte
  *@param b Bateau à ajouté à la ArrayList<Bateau>
  */
  public void ajouterBateau(Bateau b){
    this.flotte.add(b);
  }
  /**
  * mis a jour des bateau coule en les retirant de la ArrayList
  */
  public void misAjourFlotte(){
    Bateau b = new Bateau(new Coordonner(45,78),"NS",0); // création d'un bateau impossible retiré de la flotte car inexistant
    for (int i = 0;i<this.flotte.size() ;i++ ) {
      if(this.flotte.get(i).coule()){
        b = this.flotte.get(i);
        this.bateauQuiNagentAvecLesPoissons.add(b);
      }
    }
    this.flotte.remove(b);// si b n'a pas changé alors cette action n'a aucune conséquence
  }
  /**
  *modifit la grille 'grille' qui corespond à notre flote
  *@param c Coordonner de la case à modifier
  *@param ajout text par quoi modifier la case
  */
  public void modifierGrille(Coordonner c, String ajout){
    int lat = c.getLatitude();
    int lon = c.getLongitude();
    this.grille[lon][lat] = ajout;
  }
  /**
  *modifit la grille 'affichage' qui corespond aux tirs effectué
  *@param c Coordonner de la case à modifier
  *@param ajout text par quoi modifier la case
  */
  public void modifierAffichage(Coordonner c, String ajout){
    int lon = c.getLongitude();
    int lat = c.getLatitude();
    this.affichage[lon][lat] = ajout;
  }
  /**
  *demande au joueur d'entré son porcain tir et le converti en Coordonner
  *@param jeu BattleShip qui donne l'environement
  *@return Coordonner pour savoir où tiré
  */
  public abstract Coordonner chooseMove(BattleShip jeu);

}
