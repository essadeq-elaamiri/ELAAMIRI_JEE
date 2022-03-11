package miri.pro.jpaimplimentation1.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity // make a JPA entity
@Data // (lombok) generate getters and setters + non-args constructor
@AllArgsConstructor // generate all args constructor
@NoArgsConstructor // generate no args constructor
public class Student {
    @Id // primary key is necessary
    // The value of id will be generated
    // AUTO-INCREMENT
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "FNAME", length = 50)
    private String firstName;
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date birthDay;
    private int age;
    private boolean graduated;


}
