package modele;

import java.util.*;

public class Joueur extends AbstractJoueur{

  public Joueur(String n){
    super(n);
  }
  /**
  *demande au joueur d'entré son porcain tir et le converti en Coordonner
  *@param jeu BattleShip qui donne l'environement
  *@return Coordonner pour savoir où tiré
  */
  @Override
  public Coordonner chooseMove(BattleShip jeu){
    Scanner scanner3 = new Scanner(System.in);
    System.out.println("\nAu tour de: "+jeu.jactuel.getName());
    situationToString();
    System.out.println("Quel latitude + Longitude ou frapper ?:");
    String c = scanner3.nextLine();
    while(!jeu.verifieCoup(c)){
      System.out.println(name +" indiquer nous l'endroit de votre tir ! sous format latitude (x) longitude (y)");
      c = scanner3.nextLine();
    }
    String[] cSplit = c.split(" ");
    Coordonner coor = new Coordonner(Integer.parseInt(cSplit[0]),Integer.parseInt(cSplit[1]));
    return coor;
  }


  /**
  * situation de la flote du joueur
  * @return la carte de la flote du joueur c'est un toString
  */
  public String situationBateau(){
    String chaine ="";
    chaine+="Voici le jeu de bateaux "+ this.name+"\n";
    for (int i=0;i<10;i++){
      for(int j=0;j<10;j++){
        chaine += grille[i][j]+" ";
      }
      //chaine+=System.lineSeparator();
      chaine+="\n";
    }
    return chaine;
  }
  /**
  * situation de la flote ennemi vue du joueur
  * @return la carte de la flote ennemi c'est un toString des tirs sur l'ennemi
  */
  public String situationTir(){
    String chaine ="";
    chaine+="Voici les tirs effectués "+ this.name+"\n";
    for (int i=0;i<10;i++){
      for(int j=0;j<10;j++){
        chaine += affichage[i][j]+" ";
      }
      //chaine+=System.lineSeparator();
      chaine+="\n";
    }
    return chaine;
  }
  /**
  * situation global
  * @return vide  mais affiche l'ensemble des deux situations precedentes dans le terminal
  */
  public void situationToString(){
    System.out.println("Voici vos bateaux et leur états: ");
    System.out.println(situationBateau());
    System.out.println("Voici vos tirs precedent: ");
    System.out.println(situationTir());
  }

}
