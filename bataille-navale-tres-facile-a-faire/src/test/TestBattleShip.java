package test;

import org.junit.*;
import static org.junit.Assert.*;
import modele.*;
import java.util.*;

public class TestBattleShip{

  public TestBattleShip(){}

  private Joueur joueur1 = new Joueur("test1");
  private Adversaire joueur2 = new Adversaire();
  private BattleShip BattleShipTest = new BattleShip(joueur1,joueur2);

  @Test
  private void testChangePlayer(){
    String nomJactuel = BattleShipTest.getJactuel().getName();
    String nomJNactuel = BattleShipTest.getJNactuel().getName();
    BattleShipTest.changePlayer();
    assertEquals(nomJNactuel,BattleShipTest.getJactuel().getName());
    assertEquals(nomJactuel,BattleShipTest.getJNactuel().getName());
  }

  @Test
  private void testPlacementBateauValid(){
    for(int i=(-7); i<14; i++){
      for(int j=(-7); j<14; j++){
        if(i>=0 && i<=9 && j>=0 && j<=9){
          assertTrue(BattleShipTest.placementBateauValid(i,j));
        }
        else{
          assertFalse(BattleShipTest.placementBateauValid(i,j));
        }
      }
    }
  }

  @Test
  private void testVerifpositionnement(){
    for(int i=(-7); i<14; i++){
      for(int j=(-7); j<14; j++){
        Coordonner coor = new Coordonner(i,j);
        if(i>=1 && i<=8 && j>=1 && j<=8){
          assertTrue(BattleShipTest.verifpositionnement(coor,2,"NS"));
          assertTrue(BattleShipTest.verifpositionnement(coor,2,"EW"));
        }
      }
    }
  }

  @Test
  private void testPartieFinie(){
    assertTrue(BattleShipTest.partieFinie());
    BattleShipTest.creeFlotteRandom(joueur1);
    BattleShipTest.creeFlotteRandom(joueur2);
    assertFalse(BattleShipTest.partieFinie());
  }

  @Test
  private void testcoup(){
    Coordonner coor1 = new Coordonner(2,4);
    Coordonner coor2 = new Coordonner(5,6);
    Coordonner coor3 = new Coordonner(7,0);
    Coordonner coor4 = new Coordonner(2,5);
    Coordonner coor5 = new Coordonner(4,2);
    Coordonner coorbat1 = new Coordonner(8,0);
    Coordonner coorbat2 = new Coordonner(5,3);
    Coordonner coorbat3 = new Coordonner(0,6);
    Coordonner coorbat4 = new Coordonner(9,5);
    Coordonner coorbat5 = new Coordonner(3,9);

    assertEquals(BattleShipTest.coup(coor1),coor1);
    assertEquals(BattleShipTest.coup(coor2),coor2);
    assertEquals(BattleShipTest.coup(coor3),coor3);
    assertEquals(BattleShipTest.coup(coor4),coor4);
    assertEquals(BattleShipTest.coup(coor5),coor5);

  }

  @Test
  private void testcreeFlotteRandom(){
    Joueur j1 = new Joueur("t1");
    Adversaire j2 = new Adversaire();
    BattleShip BattleShipT = new BattleShip(j1,j2);

    BattleShipT.creeFlotteRandom(j1);
    BattleShipT.creeFlotteRandom(j2);
    assertEquals(j1.getFlotte().size(),5);
    assertEquals(j2.getFlotte().size(),5);
  }

  @Test
  private void testVerifieCoup(){
    Joueur p1 = new Joueur("nom");
    Adversaire p2 = new Adversaire();
    BattleShip bataille = new BattleShip(p1,p2);
    assertFalse(bataille.verifieCoup("dfdvfdv"));
    assertFalse(bataille.verifieCoup("z  f"));
    assertFalse(bataille.verifieCoup("34 54"));
    assertFalse(bataille.verifieCoup("3 r"));
    assertFalse(bataille.verifieCoup("45 8"));
    assertFalse(bataille.verifieCoup(""));
    assertTrue(bataille.verifieCoup("3 5"));
    assertTrue(bataille.verifieCoup("0 7"));
    assertTrue(bataille.verifieCoup("9 5"));
  }
  @Test
  private void testNameWinner(){
    assertEquals(BattleShipTest.nameWinner(),BattleShipTest.getJactuel().getName());
  }


  @Test
  public void tout_testBattleShip(){
    testChangePlayer();
    testPlacementBateauValid();
    testVerifpositionnement();
    testPartieFinie();
    testcreeFlotteRandom();
    testcoup();
    testVerifieCoup();
    testNameWinner();
  }


}
