## Implementation of Dependency injection using Spring framework

`Rule 1 : A perfect application must be closed to modification and open to extension (Une application parfaite doit être fermée à la modification et ouverte à l'extension), and to achieve that we use inverstion of control (IoC) and Dependency injection using a softdependency (Couplage faible) instead of hardependency (Couplage fort) which is limit the maintenablity and the extensensibility of our application `

### Structer to be implemented

![Uml class diagram ](screenshots/Screenshot_1.png).

### Using XML configuartion file

configuration XML file.
![Inject dependency using xml config file ](screenshots/spring_xml.JPG).
Injecting by property.
![Inject dependency using property ](screenshots/spring_xml_setter.JPG).
Injecting constructor.
![Inject dependency using constructor ](screenshots/spring_xml_const.JPG).

Main.

```
ApplicationContext context = new ClassPathXmlApplicationContext("ioc_config.xml");
        IMetier metier = (IMetier) context.getBean("metier");
        System.out.println("Resultat: "+ metier.calcul());
```

### Using Annotations

Component 1.

![Inject dependency using annotation ](screenshots/spring_annotions_comp.JPG).

Compoenent 2.

![Inject dependency using annotation ](screenshots/spring_annotions_comp2.JPG).

Injecting using @Autowired & @Qualifier().

![Inject dependency using annotation ](screenshots/spring_annotions_autowired.JPG).

![Inject dependency using annotation ](screenshots/spring_annotions_autowired2.JPG).

Main.

```
ApplicationContext context = new AnnotationConfigApplicationContext("dao", "metier"); // packages to look into
        IMetier metier = context.getBean(IMetier.class);
        // injection by constructor

        System.out.println("Result: "+ metier.calcul());
```
