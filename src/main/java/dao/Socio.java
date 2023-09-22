package dao;
import javax.persistence.*;

@Entity
public class Socio {
    @Id
    private int id;
    @Column(nullable = false)
    private String tipo;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Persona persona;
    public static final String BUSCAR_TODOS = "SELECT s FROM Socio s";
    public static final String FILTRAR_SOCIO_TIPO = "SELECT s FROM Socio s WHERE s.tipo LIKE :tipo";
    public static final String SOCIO_TIPO_ACTIVO = "activo";
    public static final String SOCIO_TIPO_SUSPENDIDO = "suspendido";
    public static final String SOCIO_TIPO_VITALICIO = "vitalicio";

//    --------------------------------------------------------------------------------------------------------------------
    public Socio() {
    }
    public Socio(String tipo, Persona persona) {
        this.tipo = tipo;
        this.persona = persona;

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Socio{" +
                "tipo='" + tipo + '\'' +
                ", persona=" + persona +
                '}';
    }
}
