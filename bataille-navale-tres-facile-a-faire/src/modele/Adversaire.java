package modele;

import java.util.*;

public class Adversaire extends AbstractJoueur{

  public Adversaire(){
    super("Barbe Noire");
  }
  //joue de manière random
  @Override
  public Coordonner chooseMove(BattleShip jeu){

    Random rand = new Random();

    int lon = rand.nextInt(10);
    int lat = rand.nextInt(10);
    while(this.getAffichage()[lon][lat] == "X" || this.getAffichage()[lon][lat] == "o"){
      lon = rand.nextInt(10);
      lat = rand.nextInt(10);
    }
    

    Coordonner coor = new Coordonner(lat,lon);
    
    this.coupGraphique(coor);
    return coor;
  }

}
