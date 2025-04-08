package main.Java.Model;

public class Credit extends BaseModel{
    String libelle ;
    float montant;

    public Credit(int id, String libelle, float montant) {
        super(id);
        this.libelle = libelle;
        this.montant = montant;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public float getMontant() {
        return montant;
    }
    public void setMontant(float montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Credit{" +
            "idCredit=" + id +
            ", libelle='" + libelle + '\'' +
            ", montant" + montant +
            '}';
    }

}
