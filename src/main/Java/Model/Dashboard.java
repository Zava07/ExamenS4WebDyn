package main.Java.Model;

public class Dashboard  {
    int idCredit;
    String libelle;
    float montantCredit;
    float montantDepense;
    float reste;
    public Dashboard(int idCredit, String libelle, float montantCredit, float montantDepense, float reste) {
        this.idCredit = idCredit;
        this.libelle = libelle;
        this.montantCredit = montantCredit;
        this.montantDepense = montantDepense;
        this.reste = reste;
    }
    public int getIdCredit() {
        return idCredit;
    }
    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    public float getMontantCredit() {
        return montantCredit;
    }
    public void setMontantCredit(float montantCredit) {
        this.montantCredit = montantCredit;
    }
    public float getMontantDepense() {
        return montantDepense;
    }
    public void setMontantDepense(float montantDepense) {
        this.montantDepense = montantDepense;
    }
    public float getReste() {
        return reste;
    }
    public void setReste(float reste) {
        this.reste = reste;
    }

}
