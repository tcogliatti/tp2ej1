package dao;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name=Persona.BUSCAR_TODOS, query="SELECT p FROM Persona p")
@NamedQuery(name=Persona.FILTRAR_POR_EDAD, query="SELECT p FROM Persona p WHERE p.edad BETWEEN ?1 AND ?2")
public class Persona {
    @Id
    private int id;
    @Column(name = "anios")
    private int edad;
    @Column(nullable = false)
    private String nombre;
    @ManyToOne
    private Direccion domicilio;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "jugadores")
    private List<Turno> turnos;
    public static final String BUSCAR_TODOS = "Persona.buscarTodos";
    public static final String FILTRAR_POR_EDAD = "Persona.filtraPorEdad";


//    --------------------------------------------------------------------------------------------------------------------

    public Persona() {
    }
    public Persona(int id, int edad, String nombre) {
        this.id = id;
        this.edad = edad;
        this.nombre = nombre;
    }
    public Persona(int id, int edad, String nombre, Direccion domicilio) {
        this.id = id;
        this.edad = edad;
        this.nombre = nombre;
        this.domicilio = domicilio;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Direccion domicilio) {
        this.domicilio = domicilio;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", edad=" + edad +
                ", nombre='" + nombre + '\'' +
                ", domicilio=" + domicilio +
                '}';
    }
}
