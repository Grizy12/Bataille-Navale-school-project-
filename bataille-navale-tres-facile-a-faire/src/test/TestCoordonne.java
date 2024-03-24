package test;

import org.junit.*;
import static org.junit.Assert.*;
import modele.*;
import java.util.*;

public class TestCoordonne{

  public TestCoordonne(){}

  @Test
  public void tout_testCoordonne(){
    Coordonner coor = new Coordonner(2,5);
    Coordonner coor2 = new Coordonner(2,5);
    Coordonner coor3 = new Coordonner(5,5);
    assertTrue(coor.equals(coor2));
    assertFalse(coor.equals(coor3));
  }

}
