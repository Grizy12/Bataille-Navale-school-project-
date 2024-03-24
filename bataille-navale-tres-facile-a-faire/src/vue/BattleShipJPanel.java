package vue;
import modele.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.ArrayList;
import java.awt.event.*;


public class BattleShipJPanel extends JPanel implements EcouteurBattleShip {
    protected AbstractJoueur joueur;
    protected BattleShip galion;
    protected int dim = 50;
    protected ArrayList<Bateau> flotte;
    protected Coordonner kaze;
    protected ArrayList<Coordonner> coupAdverse;
    protected ArrayList<Bateau> flotteCoule;


    public BattleShipJPanel(AbstractJoueur joueur,BattleShip galion){
        this.galion = galion;
        this.joueur = joueur;

        
        joueur.ajoutEcouteur(this);
        if(joueur.getName() == galion.getJoueur2().getName()){
            addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent e){
                    Coordonner coupActuel = null;
                    int x =e.getX()/dim-1;
                    int y =e.getY()/dim-1;
                    if(x>=0 && x<=9 && y>=0 && y<=9){
                      coupActuel = new Coordonner(x,y);
                    }
                    galion.getJoueur1().coupGraphique(coupActuel);
                }
                @Override
                public void mouseExited(MouseEvent e){}
                public void mouseEntered(MouseEvent e){}
                public void mouseReleased(MouseEvent e){}
                public void mousePressed(MouseEvent e){}
            });
        }

    }

    @Override
    public void paintComponent(Graphics g){
        int dims = dim/4;
        int dimt = dim/10;
        super.paintComponent(g);
        char [] data = {'A','B','C','D','E','F','G','H','I','J'};//ya un symbole random au debut pour incrementer toute la liste de 1 (pratique pour la liste)
        String [] numb = {"1","2","3","4","5","6","7","8","9","10"};//10 c'est pas un caractere bonne chance pour l'implementer
        g.drawRect(dim,dim,dim*10,dim*10);
        g.setFont(new Font("Serif",Font.PLAIN,20));

        for(int i=0;i<10;i++){
            g.drawChars(data,i,1,dim*(i+1)+dim/2,dim-dim/2);//le liste choisie,l'element de la liste, le nombre de char à afficher,la position;
            g.drawString(numb[i],dim-dim/2,dim*(i+1)+dim/2);
            for(int j=0;j<10;j++){
                g.drawLine((i+1)*dim,(j+1)*dim,(i+1)*dim,11*dim);
                g.drawLine((j+1)*dim,(i+1)*dim,11*dim,(i+1)*dim);
            }

        }

        if (joueur==galion.getJoueur1()) {
          this.coupAdverse=galion.getJoueur2().getCoupsJouer();
        }else{
          this.coupAdverse=galion.getJoueur1().getCoupsJouer();
        }
        for (Coordonner position : this.coupAdverse) {
          int lat = position.getLatitude();
          int lon = position.getLongitude();
          if (joueur.getGrille()[lon][lat]=="X") {
            g.setColor(Color.RED);
            g.drawOval(dim*(1+lat)+dims,dim*(1+lon)+dims,dim/2,dim/2);
            g.fillOval(dim*(1+lat)+dims,dim*(1+lon)+dims,dim/2,dim/2);
          }else{
            g.setColor(Color.GREEN);
            g.drawOval(dim*(1+lat)+dims,dim*(1+lon)+dims,dim/2,dim/2);
            g.fillOval(dim*(1+lat)+dims,dim*(1+lon)+dims,dim/2,dim/2);
          }
        }
        g.setColor(Color.BLACK);



        if(joueur == galion.getJoueur1()){//changer le nom pour changer quel coté de la grille est le bot et l'autre le joueurs
            this.flotte = joueur.getFlotte();//j'obtient la liste des bateaux du joueur
            for(Bateau ensemble : this.flotte){//je le parcours
                this.kaze = ensemble.getCoordonner();//j'obtient la liste des coordonnées des bateaux du joueur
                if(ensemble.getSens().equals("EW")){//le dimt est une variation de 3 pixel qui permet de ne pas etre superposée avec la grille de jeux
                    g.drawRoundRect(dim*(1+this.kaze.getLatitude())+dimt,dim*(1+this.kaze.getLongitude())+dimt,dim*ensemble.getTaille()-dimt*2,dim-dimt*2,dim,dim);
                }
                else{
                    g.drawRoundRect(dim*(1+this.kaze.getLatitude())+dimt,dim*(1+this.kaze.getLongitude())+dimt,dim-dimt*2,dim*ensemble.getTaille()-dimt*2,dim,dim);
                }

            }
        }


        this.flotteCoule = joueur.getBateauQuiNagentAvecLesPoissons();

        for(Bateau ensemble :this.flotteCoule){
            if(ensemble.getSens().equals("EW")){
                g.drawRoundRect(dim*(1+ensemble.getCoordonner().getLatitude())+dimt,dim*(1+ensemble.getCoordonner().getLongitude())+dimt,dim*ensemble.getTaille()-dimt*2,dim-dimt*2,dim,dim);
            }
            else{
                g.drawRoundRect(dim*(1+ensemble.getCoordonner().getLatitude())+dimt,dim*(1+ensemble.getCoordonner().getLongitude())+dimt,dim-dimt*2,dim*ensemble.getTaille()-dimt*2,dim,dim);
            }
        }
    }

    @Override
    public void modeleMisAJour(Object joueur){
        repaint();

    }

    public Dimension getPreferredSize(){
        return new Dimension(dim*12,dim*12);
    }


}
