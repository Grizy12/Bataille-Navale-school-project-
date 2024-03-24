package test;

import org.junit.*;
import static org.junit.Assert.*;
import modele.*;
import java.util.*;

public class TestJoueur{

  public TestJoueur(){}

  private Joueur joueur1 = new Joueur("test");
  private Adversaire joueur2 = new Adversaire();
  private BattleShip plateau = new BattleShip(joueur1,joueur2);

  /**
  *transforme les Coordonner en String
  *@param coor Coordonner à transformé
  *@return les Coordonner en string
  */
  private String coordonnerToString(Coordonner coor){
    String var = "";
    var += coor.getLatitude();
    var += " ";
    var += coor.getLongitude();
    return var;
  }

  @Test
  private void testAccesseur(){
    Coordonner coor1 = new Coordonner(3,4);
    Coordonner coor2 = new Coordonner(2,4);
    Coordonner coor3 = new Coordonner(4,4);

    joueur1.coupGraphique(coor1);
    joueur1.coupGraphique(coor2);
    joueur1.coupGraphique(coor3);
    joueur1.coupGraphique(null);

    joueur2.coupGraphique(coor3);
    joueur2.resetCoupGraphique();

    assertEquals(coor1,joueur1.getCoupsJouer().get(0));
    assertEquals(coor2,joueur1.getCoupsJouer().get(1));
    assertEquals(coor3,joueur1.getCoupsJouer().get(2));
    assertEquals(3,joueur1.getCoupsJouer().size());

    assertEquals(coor3,joueur2.getCoupsJouer().get(0));
    assertEquals(null,joueur2.getCoupGraphique());

    assertEquals(coor3,joueur1.getCoupGraphique());
  }

  @Test
  private void testGrille(){

    String[] alpha = {"a","b","c","d","e","m","r","w","z",",",";","/","&","é",")","#",">","¤","$","µ"};

    Coordonner coor1 = new Coordonner(3,4);
    Coordonner coor2 = new Coordonner(2,4);
    Coordonner coor3 = new Coordonner(4,4);


    int a = 0;

    for(int i=0; i<2; i++){
      for(int j=0; j<10; j++){

          Coordonner c = new Coordonner(i,j);
          joueur1.modifierGrille(c,alpha[a]);
          joueur1.modifierAffichage(c,alpha[a]);
          if(a<20)
            a++;
      }
    }

    a = 0;
    for(int k=0; k<2; k++){
      for(int l=0; l<10; l++){
        assertEquals(alpha[a],joueur1.getGrille()[l][k]);
        assertEquals(alpha[a],joueur1.getAffichage()[l][k]);
        if(a<20)
          a++;
      }
    }

  }

  @Test
  private void testAjouterBateau(){
    Coordonner coor1 = new Coordonner(2,4);
    Coordonner coor2 = new Coordonner(4,4);
    Bateau bateau1 = new Bateau(coor1,"NS",2);
    Bateau bateau2 = new Bateau(coor2,"EW",2);

    joueur1.ajouterBateau(bateau1);
    joueur1.ajouterBateau(bateau2);

    assertEquals(joueur1.getFlotte().get(0),bateau1);
    assertEquals(joueur1.getFlotte().get(1),bateau2);

  }

  @Test
  private void testCoupRandom(){
    for(int i=0; i<1000; i++){
      Coordonner coor = joueur2.chooseMove(plateau);

      String var = coordonnerToString(coor);
      assertTrue(plateau.verifieCoup(var));
    }
  }

  @Test
  private void testMisAjourFlotte(){
    Coordonner coor1 = new Coordonner(9,0);
    Coordonner coor2 = new Coordonner(5,3);
    Bateau bateau1 = new Bateau(coor1,"NS",2);
    Bateau bateau2 = new Bateau(coor2,"EW",3);
    ArrayList<Bateau> l1 = new ArrayList<Bateau>();
    l1.add(bateau2);

    joueur2.ajouterBateau(bateau1);
    joueur2.ajouterBateau(bateau2);
    bateau1.tireSurBateau(coor1);
    bateau1.tireSurBateau(new Coordonner(9,1));

    joueur2.misAjourFlotte();

    assertEquals(joueur2.getFlotte(),l1);
    assertEquals(joueur2.getBateauQuiNagentAvecLesPoissons().get(0),bateau1);

  }

  @Test
  public void tout_testJoueur(){
    testAccesseur();
    testGrille();
    testAjouterBateau();
    testMisAjourFlotte();
    testCoupRandom();
  }



}
