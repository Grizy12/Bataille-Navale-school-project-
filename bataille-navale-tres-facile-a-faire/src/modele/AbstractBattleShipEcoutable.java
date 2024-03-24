package modele;
import java.util.*;

public class AbstractBattleShipEcoutable{
    public ArrayList<EcouteurBattleShip> ecouteurs;

    public AbstractBattleShipEcoutable(){
        this.ecouteurs = new ArrayList<>();
    }

    public void ajoutEcouteur(EcouteurBattleShip e){
        ecouteurs.add(e);
    }

    public void retraitEcouteur(EcouteurBattleShip e){
        ecouteurs.remove(e);
    }

    public void fireChangement(){
        for(EcouteurBattleShip e:ecouteurs){
            e.modeleMisAJour(this);
        }
    }

}