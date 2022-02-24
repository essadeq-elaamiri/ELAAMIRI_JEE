package metier;

import dao.IDAO;

// code logic rotten by developer
public class MetierImp implements IMetier{
    // dependance
    // couplge faible
    private IDAO dao; // sans new (new == couplage fort)
    @Override
    public double calcul() {
        System.out.println("Imp 1");
        return dao.getData() * Math.random()*13;
    }

    // pour faire affecter une instance à l'objet dao; (injection de dependance)
    public void setDao(IDAO dao) {
        this.dao = dao;
    }
}
