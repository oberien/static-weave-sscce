package foo.bar.sscce.jpa.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "uiae")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
}
