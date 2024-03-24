package test;

import org.junit.*;
import static org.junit.Assert.*;
import modele.*;
import java.util.*;

public class TestBateau{

  public TestBateau(){}

  private Joueur jou = new Joueur("test");
  private Coordonner pos1 = new Coordonner(2,3);
  private Coordonner pos2 = new Coordonner(0,2);
  private Coordonner pos3 = new Coordonner(4,2);
  private Coordonner pos4 = new Coordonner(7,2);
  private Coordonner pos5 = new Coordonner(2,0);
  private Bateau bateau1 = new Bateau(pos1,"NS",5);
  private Bateau bateau2 = new Bateau(pos2,"EW",4);
  private Bateau bateau3 = new Bateau(pos3,"EW",3);
  private Bateau bateau4 = new Bateau(pos4,"EW",3);
  private Bateau bateau5 = new Bateau(pos5,"NS",2);

  @Test
  private void testPlacementBateau(){
    bateau1.placementBateau(jou);
    bateau2.placementBateau(jou);
    bateau3.placementBateau(jou);
    bateau4.placementBateau(jou);
    bateau5.placementBateau(jou);
    for(int i=0; i<10; i++)
      assertEquals("O",jou.getGrille()[2][i]);
    for(int i=0; i<8; i++)
      assertEquals("O",jou.getGrille()[i][2]);
  }

  @Test
  private void testTireSurBateau_Coule(){
    bateau1.placementBateau(jou);
    bateau2.placementBateau(jou);
    bateau3.placementBateau(jou);
    bateau4.placementBateau(jou);
    bateau5.placementBateau(jou);

    Coordonner coor1 = new Coordonner(2,0);
    Coordonner coor2 = new Coordonner(2,1);

    int j = 5;
    for(int i=3; i<8; i++){
      Coordonner coor = new Coordonner(2,i);
      assertEquals(bateau1.getNombreDeKazeRestante(),j);
      bateau1.tireSurBateau(coor);
      j--;
    }

    bateau5.tireSurBateau(coor1);
    assertEquals(bateau5.getNombreDeKazeRestante(),1);
    assertFalse(bateau5.coule());
    bateau5.tireSurBateau(coor2);
    assertEquals(bateau5.getNombreDeKazeRestante(),0);
    assertTrue(bateau5.coule());

  }

  @Test
  public void tout_testBateau(){
    testPlacementBateau();
    testTireSurBateau_Coule();
  }

}
