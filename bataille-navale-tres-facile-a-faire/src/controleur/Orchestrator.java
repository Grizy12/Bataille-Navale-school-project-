package controleur;

import modele.*;
import vue.*;
import java.util.* ;
//import java.lang.*; ca marche sans

public class Orchestrator{
  protected BattleShip jeu;
  protected Coordonner coups;
  protected int jouer;

  public Orchestrator(BattleShip jeu){
    this.jeu = jeu;
    this.jouer = 1;
  }
  public int getJouer(){
    return this.jouer;
  }

//gère la partie
  public void play(int affichage){

    Coordonner coups = null;
    //partie graphique
    if (affichage == 1) {

      BattleShipJFrame visu = new BattleShipJFrame(jeu);

      while (!jeu.partieFinie()) {
        jeu.changePlayer();
        if(jeu.jactuel==jeu.getJoueur1()){//ca va marcher que si on a une interface graphique
          while(this.coups == null){
            this.coups = jeu.getJoueur1().getCoupGraphique();
          }
        }else{
          this.coups = jeu.getJoueur2().chooseMove(jeu);
        }
        jeu.coup(this.coups); //joue le coup

        jeu.jactuel.suivant();
        this.coups = null;
        jeu.jactuel.resetCoupGraphique();
      }
      visu.dispose();
      // rejouer tant que joueur clique sur oui
      FinGraphique fin = new FinGraphique(jeu.nameWinner());
      fin.setVisible(true);
      while(fin.getReponse()==0){
        this.jouer = fin.getReponse();
      }
    }
    //partie dans le terminal
    else{
      while (!jeu.partieFinie()) {
        jeu.changePlayer();
        coups = jeu.jactuel.chooseMove(jeu);
        jeu.coup(coups);
      }
      System.out.println("Félicitations "+ jeu.nameWinner()+" pour ta victoire!");
      // rejouer tant que joueur entre 1
      System.out.println(jeu.getJoueur1().getName()+ " si vous souhaitez rejouer 1 sinon tapez 2");
      Scanner scanner = new Scanner(System.in);
      this.jouer = scanner.nextInt();
      while(this.jouer != 1 && jouer!= 2){
        System.out.println("S'il vous plaît veuillez entre un chifre valide. 1 pour rejouer et 2 pour arreter");
        this.jouer = scanner.nextInt();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException {
    String nom = null;
    int jouer = 1;
    Scanner scanner1 = new Scanner(System.in);

    while(jouer==1){
      Random rand = new Random();
      //int var = Math.abs(rand.nextInt());

//choix de l'affichage
      System.out.println("Bonjour si vous souhaitez jouer avec une interface graphique tapez 1 sinon tapez 2");
      int typeAffichage = scanner1.nextInt();
      while(typeAffichage != 1 && typeAffichage!= 2){
        System.out.println("S'il vous plaît veuillez entre un chifre valide. 1 pour avoir une interface 2 pour rester dans le terminal");
        typeAffichage = scanner1.nextInt();
      }
//choix du nom
      if (typeAffichage==1) {
        PrenomGraphique prenom = new PrenomGraphique();
        prenom.setVisible(true);
        while(prenom.enCours){
          Thread.sleep(1000);
        }
        nom = prenom.getName();
        prenom.dispose();
      }else{
        System.out.println("Nom du Joueur ?");
        nom = scanner1.next();
      }

      //creation de la partie
      Joueur joueur1 = new Joueur(nom);
      Adversaire joueur2 = new Adversaire();
      BattleShip jeu = new BattleShip(joueur1,joueur2);//J1
      Orchestrator partie = new Orchestrator(jeu);

      partie.jeu.creeFlotteRandom(joueur1);
      partie.jeu.changePlayer();
      partie.jeu.creeFlotteRandom(joueur2);
      partie.play(typeAffichage);

      jouer = partie.getJouer();

    }
  }
}
