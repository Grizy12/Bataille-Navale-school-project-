package modele;

public class Coordonner{
  protected int latitude;
  protected int longitude;

  public Coordonner(int latitude,int longitude){
    this.latitude=latitude;// la latitude c'est le x sur un repere orthonome
    this.longitude=longitude;// la longitude c'est le y sur un repere orthonome
  }

  /**
   * Accesseur Latitude
   *@return l'attribut Latitude
  */
  public int getLatitude(){
    return this.latitude;
  }
  /**
   * Accesseur Longitude
   *@return l'attribut Longitude
  */
  public int getLongitude(){
    return this.longitude;
  }

  @Override
  public String toString(){
    String ch="";
    int lon = this.getLongitude();
    int lat = this.getLatitude();
    return ch+= "("+ lat +","+ lon +")";
  }
  /**
   *primordiale autrement les valeurs ne se retire pas de la liste
   *@param o l'objet que l'on veut comparré
   *@return true si l'objet est == au Coordonner et false sinon
  */
  @Override
  public boolean equals(Object o){
    if(o==this){
      return true;
    }
    Coordonner a = (Coordonner) o;
    if(a.getLatitude()==this.getLatitude() && a.getLongitude()==this.getLongitude()){
      return true;
    }
    return false;
  }

}
