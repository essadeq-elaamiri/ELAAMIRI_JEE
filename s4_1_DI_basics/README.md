## Implementation of Dependency injection based on static and dynamic instanciation

`Rule 1 : A perfect application must be closed to modification and open to extension (Une application parfaite doit être fermée à la modification et ouverte à l'extension), and to achieve that we use inverstion of control (IoC) and Dependency injection using a softdependency (Couplage faible) instead of hardependency (Couplage fort) which is limit the maintenablity and the extensensibility of our application `

### Structer to be implemented

![Uml class diagram ](screenshots/Screenshot_1.png).

### Using Static instanciation

Injecting by setter.
![Inject dependency using setter ](screenshots/stat_setter.JPG).
Result.
![Inject dependency using setter ](screenshots/stat_setter_res.JPG).
Injecting by constructor.

![Inject dependency using constructor ](screenshots/stat_const.JPG).

### Using dynamic instanciation

Injecting by setter.

![Inject dependency using setter ](screenshots/dynamique_setter.JPG).
Injecting by constructor.

![Inject dependency using constructor ](screenshots/dynamique_const.JPG).
