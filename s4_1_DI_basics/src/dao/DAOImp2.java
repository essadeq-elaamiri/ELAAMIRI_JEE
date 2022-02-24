package dao;

public class DAOImp2 implements IDAO{
    @Override
    public double getData() {
        System.out.println("DAOImp 2");
        return 1001;
    }
}
