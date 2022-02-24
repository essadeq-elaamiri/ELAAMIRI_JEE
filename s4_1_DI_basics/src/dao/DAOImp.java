package dao;

public class DAOImp implements IDAO{
    @Override
    public double getData() {
        System.out.println("DAOimp 1");
        return 100;
    }
}
