package dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name=Direccion.BUSACAR_TODOS, query = "SELECT d FROM Direccion d")
@NamedQuery(name=Direccion.BUSACAR_POR_CIUDAD, query = "SELECT d FROM Direccion d WHERE d.ciudad LIKE ?1")
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
    public static final String BUSACAR_TODOS = "Direccion.buscarTodos";
    public static final String BUSACAR_POR_CIUDAD = "Direccion.buscarPorCiudad";
//    --------------------------------------------------------------------------------------------------------------------

    public Direccion() {
    }
    public Direccion(String ciudad, String calle) {
        this.ciudad = ciudad;
        this.calle = calle;
    }
    public Direccion(int id, String ciudad, String calle) {
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
