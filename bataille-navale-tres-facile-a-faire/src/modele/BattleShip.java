package modele;
import java.util.*;

public class BattleShip {
    public AbstractJoueur jactuel;
    public AbstractJoueur jNactuel; // represente le joueur dont ce n'est pas le tour
    public Joueur joueur1;
    public Adversaire joueur2;

    public BattleShip(Joueur joueur1 , Adversaire joueur2){
        this.jactuel = joueur1;
        this.jNactuel = joueur2;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    public void changePlayer(){
        if(this.jactuel==joueur1){
            this.jactuel=joueur2;
            this.jNactuel = joueur1;
        }
        else{
            this.jactuel = joueur1;
            this.jNactuel = joueur2;
        }
    }


    public Joueur getJoueur1(){
      return this.joueur1;
    }

    public AbstractJoueur getJactuel(){
      return this.jactuel;
    }

    public AbstractJoueur getJNactuel(){
      return this.jNactuel;
    }

    public Adversaire getJoueur2(){
      return this.joueur2;
    }

    public boolean verifpositionnement(Coordonner axe,int iteration,String direction){//iteration c'est la taille du bateau
      int lat = axe.getLatitude();
      int lon = axe.getLongitude();
      if(direction.equals("NS")){//pour North South
        for(int i=0;i<iteration;i++){
          if(!(placementBateauValid(lat,lon))){
            return false;
          }
          //pour s'assurer que il n'y a pas de bateau autour
          ArrayList<Coordonner>caseAutour = pointAutour(new Coordonner (lat,lon));
          for(int index=0;index<caseAutour.size();index++){
            int l = caseAutour.get(index).getLatitude();
            int lo = caseAutour.get(index).getLongitude();
            if(!(placementBateauValid(l,lo))){
              return false;
            }
          }
          lon++;
        }
      }
      else if(direction.equals("EW")){//pour East to West
        for(int j=0;j<iteration;j++){
          if(!(placementBateauValid(lat,lon))){
            return false;
          }
          //pour s'assurer que il n'y a pas de bateau autour
          ArrayList<Coordonner>caseAutour = pointAutour(new Coordonner (lat,lon));
          for(int index=0;index<caseAutour.size();index++){
            int l = caseAutour.get(index).getLatitude();
            int lo = caseAutour.get(index).getLongitude();
            if(!(placementBateauValid(l,lo))){
              return false;
            }
          }
          lat++;
        }
      }else{
        System.out.println("Direction Incorrecte");
        return false;
      }
      return true;
    }

    // joue le coup et le renvoie
    public Coordonner coup(Coordonner axe){
      if(jNactuel.getGrille()[axe.getLongitude()][axe.getLatitude()]=="O"){//test si on a touché un bateau;
        jNactuel.modifierGrille(axe, "X");
        System.out.println("Touché ");
        jNactuel.getFlotte().forEach((b) -> b.tireSurBateau(axe));
        jNactuel.misAjourFlotte();
        //jNactuel.accesseurCoordonneAdverseTouche(axe);//remplacement possible par une liste de coups dans Adversaire ou meme mieux fonctionnement identique a joueur
        jactuel.modifierAffichage(axe, "X");
      }
      else{
        System.out.println("A l'eau !");
        jactuel.modifierAffichage(axe, "o");
        //jNactuel.accesseurCoordonneAdverseRater(axe);//remplacement par la meme liste que ci dessus
      }

      return axe;
    }

    public boolean partieFinie(){
      return this.joueur1.getFlotte().size() == 0 || this.joueur2.getFlotte().size() == 0;
    }

    public String nameWinner(){
        return this.jactuel.getName();

    }

    public boolean verifieCoup(String text){
      String[] travail = text.split(" ");
      if(travail.length!= 2){
        System.out.println("Mauvaise Syntaxe! format demandé : x y");
        return false;
      }
      try {
        int lat = Integer.parseInt(travail[0]);
        int lon = Integer.parseInt(travail[1]);
      }catch ( Exception e) {
        System.out.println("vous devez entrez des entiers");
        return false;
      }
      int lat = Integer.parseInt(travail[0]);
      int lon = Integer.parseInt(travail[1]);
      if(lon > 9 ||lat >9 ||lon < 0 ||lat < 0){
        System.out.println("tir dans la grille !");
        return false;
      }
      return true;
    } //pour un tir

    public boolean placementBateauValid(int latitude, int longitude){
      if (latitude<0 || latitude>9) {
        return false;
      }if (longitude<0 || longitude>9) {
        return false;
      }
      return(jactuel.getGrille()[longitude][latitude]==".");
    }

    /**
    *cree une flote random a un jooueur
    */
    public void creeFlotteRandom(AbstractJoueur joueur){
      int[] tab =  {5,4,3,3,2};
      Random rand = new Random();

      for(int i: tab){
        boolean validite = false;

        while(validite == false){
          Coordonner coor = new Coordonner(rand.nextInt(10), rand.nextInt(10));
          String axe = "";
          if(rand.nextBoolean())
            axe = "NS";
          else
            axe = "EW";

          if(verifpositionnement(coor,i,axe)){
            Bateau bateau = new Bateau(coor,axe,i);
            bateau.placementBateau(joueur);
            validite = true;
          }
        }
      }
    }

    /**
    *fonction qui donne les cases autour de la case entré
    */
    private ArrayList<Coordonner> pointAutour(Coordonner coor){
      ArrayList<Coordonner> liste = new ArrayList<>();
      if(coor.getLatitude()>0){
        Coordonner coorGauche = new Coordonner(coor.getLatitude()-1,coor.getLongitude());
        liste.add(coorGauche);
      }
      if(coor.getLatitude()<9){
        Coordonner coorDroit = new Coordonner(coor.getLatitude()+1,coor.getLongitude());
        liste.add(coorDroit);
      }
      if(coor.getLongitude()>0){
        Coordonner coorHaut = new Coordonner(coor.getLatitude(),coor.getLongitude()-1);
        liste.add(coorHaut);
      }
      if(coor.getLongitude()<9){
        Coordonner coorBas = new Coordonner(coor.getLatitude(),coor.getLongitude()+1);
        liste.add(coorBas);
      }
      return liste;
    }


}
//penser a verifier que les trucs invalide ne s'ajoute pas a la grille
