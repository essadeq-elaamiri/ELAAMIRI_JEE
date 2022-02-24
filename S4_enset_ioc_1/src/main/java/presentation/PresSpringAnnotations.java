package presentation;

import dao.IDAO;
import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PresSpringAnnotations {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("dao", "metier"); // packages to look into
        IMetier metier = context.getBean(IMetier.class);
        // injection by constructor

        System.out.println("Result: "+ metier.calcul());
    }
}
