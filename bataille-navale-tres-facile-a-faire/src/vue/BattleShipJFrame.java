package vue;
import modele.*;
import controleur.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class BattleShipJFrame extends JFrame{
    private BattleShip galion;

    public BattleShipJFrame(){
        this(new BattleShip(new Joueur("test"),new Adversaire()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public BattleShipJFrame(BattleShip galion){
        this.galion = galion;
        setLayout(new BorderLayout());
        add(new BattleShipJPanel(galion.getJoueur1(),galion),BorderLayout.CENTER);//gere le joueur
        add(new BattleShipJPanel(galion.getJoueur2(),galion),BorderLayout.EAST);//gere le bot
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
