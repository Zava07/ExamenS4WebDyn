package main.Java.Model;

public class Depense extends BaseModel {
    float montantDepense;
    int idCredit;
    
    public Depense(int id, float montantDepense, int idCredit) {
        super(id);
        this.montantDepense = montantDepense;
        this.idCredit = idCredit;
    }
    public float getMontantDepense() {
        return montantDepense;
    }
    public void setMontantDepense(float montantDepense) {
        this.montantDepense = montantDepense;
    }
    public int getIdCredit() {
        return idCredit;
    }
    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }


}
