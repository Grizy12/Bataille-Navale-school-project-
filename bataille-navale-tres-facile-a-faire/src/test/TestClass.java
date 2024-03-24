package test;


public class TestClass {

    public static void main(String... args){

      TestJoueur joueurTest = new TestJoueur();
      TestBateau bateauTest = new TestBateau();
      TestCoordonne coordonneTest = new TestCoordonne();
      TestBattleShip BattleShipTest = new TestBattleShip();

      joueurTest.tout_testJoueur();
      System.out.println("All tests joueur -> OK");
      bateauTest.tout_testBateau();
      System.out.println("All tests bateau -> OK");
      coordonneTest.tout_testCoordonne();
      System.out.println("All tests coordonné -> OK");
      BattleShipTest.tout_testBattleShip();
      System.out.println("All tests BattleShip -> OK");
    }

}
