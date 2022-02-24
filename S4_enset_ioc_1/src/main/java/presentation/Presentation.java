package presentation;

import dao.DAOImp;
import dao.IDAO;
import metier.IMetier;
import metier.MetierImp;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation {
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // injection de dependance en utilisant l'instanciation statique.
    /*
        IDAO idao = new DAOImp();
        IMetier iMetier = new MetierImp(idao); // injection using constructor
        //((MetierImp) iMetier).setDao(idao); // injection using setter

        System.out.println("Resultat: "+ iMetier.calcul());
    */


        //injection avec instanciation dyncamique
        //lire le fichier qui contient les noms des classes
        Scanner scanner = new Scanner(new File("config.txt"));
        String daoImpClassName = scanner.nextLine();
        String metierImpClassName = scanner.nextLine();

        // charger les classes dans la memoire
        Class cDAOImp = Class.forName(daoImpClassName);
        Class cMetiermp = Class.forName(metierImpClassName);

        // instancier les classes
        IDAO dao = (IDAO)cDAOImp.newInstance();
        //IMetier metier = (IMetier) cMetiermp.newInstance();

        //appeler le setter de metier pour faire l'injection,
        //Method setDaoMethod = cMetiermp.getMethod("setDao", IDAO.class);
        //setDaoMethod.invoke(metier, dao);
        // Injection dynamique en utilisant le constructeur
        Constructor daoImpConstructor = cMetiermp.getConstructor(IDAO.class);
        IMetier metier = (IMetier) daoImpConstructor.newInstance(dao);

        System.out.println("Resultat: " + metier.calcul());


    }
}
