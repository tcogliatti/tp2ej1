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
