package dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("dao1") // default packageName.ClassName
@Primary
public class DAOImp implements IDAO{
    @Override
    public double getData() {
        System.out.println("DAOimp 1");
        return 100;
    }
}
