package dao;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private Timestamp fecha;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Persona> jugadores;

    public Turno() {
    }

    public Turno(Timestamp fecha, List<Persona> jugadores) {
        this.fecha = fecha;
        this.jugadores = jugadores;
    }

    public Turno(int id, Timestamp fecha) {
        this.id = id;
        this.fecha = fecha;
        this.jugadores = new ArrayList<Persona>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public List<Persona> getJugadores() {
        return jugadores;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", jugadores=" + jugadores +
                '}';
    }
}
