package metier;

import dao.IDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// code logic rotten by developer
public class MetierImp implements IMetier{
    // dependance
    // couplge faible

    //@Autowired //
    //@Qualifier("dao2")
    private IDAO dao; // sans new (new == couplage fort)

    //public MetierImp(){}
    public MetierImp(IDAO dao){
        this.dao = dao;
    }
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
