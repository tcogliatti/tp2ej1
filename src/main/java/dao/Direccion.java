package dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String ciudad;
    @Column
    private String calle;
    @OneToMany(mappedBy = "domicilio", fetch = FetchType.LAZY)
    private List<Persona> habitantes;

    public Direccion() {
    }
    public Direccion(String calle, String ciudad) {
        this.ciudad = ciudad;
        this.calle = calle;
    }
    public Direccion(int id, String calle, String ciudad) {
        this.id = id;
        this.calle = calle;
        this.ciudad = ciudad;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Persona> getHabitantes() {
        return habitantes;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "id=" + id +
                ", ciudad='" + ciudad + '\'' +
                ", calle='" + calle + '\'' +
                '}';
    }
}
