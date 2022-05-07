package lab9.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "countries1")
public class country implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private int code;
    @Column(name = "continent")
    private String continent;
    public country()
    {}
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Override
    public String toString() {
        return "countries1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", continent='" + continent + '\'' +
                '}';
    }
}
