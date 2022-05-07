package lab9.entities;

import javax.persistence.*;

@Entity
@Table(name = "continents")
@NamedQueries({
        @NamedQuery(name = "Continent.findAll",
                query = "select e from continent e order by e.name"),
})

public class continent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
    @Column(name = "id")
    private int id;
    @Column(name="name")
    private String name;

    public continent(String europe) {
        this.name=europe;
    }

    public continent() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "continents{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
