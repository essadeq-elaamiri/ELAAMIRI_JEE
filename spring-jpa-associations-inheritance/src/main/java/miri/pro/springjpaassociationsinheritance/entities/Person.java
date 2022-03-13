package miri.pro.springjpaassociationsinheritance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "D", length = 3)
public class Person {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) To
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String name;
    private String email;
}
