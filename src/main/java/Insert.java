import dao.Persona;
import dao.Direccion;

import dao.Socio;
import dao.Turno;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class Insert extends Conn {
    private static String path = "./src/main/java/csv/";
    private static final String DIRECCIONES_FILE = "domicilios.csv";
    private static final String PERSONAS_FILE = "personas.csv";
    private static final String SOCIO_FILE = "socio.csv";
    private static final int CANTIDAD_TURNOS = 20;
    private static final int CANTIDAD_JUGADORES_TURNOS = 10;

    public static void main(String[] args) {
        em.getTransaction().begin();

        // carga de direcciones
        cargarDireccion();

        // carga de personas
        List<Direccion> dir = em.createQuery("SELECT d FROM Direccion d").getResultList();
        cargarPersonas(dir);

        // carga de socios
        List<Persona> per = em.createQuery("SELECT p FROM Persona p").getResultList();
        cargarSocio(per);

        // carga de turnos
        cargarTurno(per);

        // enviando la transacción
        em.getTransaction().commit();

        // cierre
        em.close();
        emf.close();
    }

    public static void cargarDireccion() {
        Direccion dir = null;
        CSVParser parser = null;
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path + DIRECCIONES_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Cargando Dirección --> ");
        for (CSVRecord row : parser) {
            dir = new Direccion(row.get("ciudad"), row.get("calle"));
            em.persist(dir);
            System.out.print(".");
        }
        System.out.println("\n                  --> proceso terminado /_");
    }

    public static void cargarPersonas(List<Direccion> domList) {
        Persona per = null;
        CSVParser parser = null;
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path + PERSONAS_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Cargando Personas --> ");
        for (CSVRecord row : parser) {
            per = new Persona(Integer.parseInt(row.get("id")), Integer.parseInt(row.get("edad")), row.get("nombre"));
            per.setDomicilio(obtenerDireccionAleatoria(domList));
            em.persist(per);
            System.out.print(".");
        }
        System.out.println("\n                  --> proceso terminado /_");
    }

    public static Direccion obtenerDireccionAleatoria(List<Direccion> domList) {
        Random rand = new Random();
        int indexRandom = rand.nextInt(domList.size());
        Direccion dirRandom = domList.get(indexRandom);
        return dirRandom;
    }

    public static void cargarSocio(List<Persona> personas) {
        Socio soc = null;
        ArrayList<String> tiposSocio = new ArrayList<String>();
        CSVParser parser = null;
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path + SOCIO_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Cargando Socios --> ");
        for (CSVRecord row : parser) {
            tiposSocio.add(row.get("tipo"));
        }
        for (Persona p : personas) {
            soc = new Socio(obtenerTipoAleatoria(tiposSocio), p);
            em.persist(soc);
            System.out.print(".");
        }

        System.out.println("\n                  --> proceso terminado /_");
    }

    public static String obtenerTipoAleatoria(List<String> socList) {
        Random rand = new Random();
        int indexRandom = rand.nextInt(socList.size());
        String socRandom = socList.get(indexRandom);
        return socRandom;
    }

    public static void cargarTurno(List<Persona> personas) {
        Turno tur = null;
        ArrayList<String> tiposSocio = new ArrayList<String>();
        CSVParser parser = null;
        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path + SOCIO_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int i = 0;
        ArrayList<Persona> jugadores;
        System.out.print("Cargando Turnos --> ");
        while (i < CANTIDAD_TURNOS) {
            ArrayList<Persona> candidatos = new ArrayList<>(personas);
            jugadores = new ArrayList<Persona>();
            // creando lista de jugadores
            for (int j = 0; j < CANTIDAD_JUGADORES_TURNOS; j++) {
                jugadores.add(obtenerPersonaAleatoria(candidatos));
            }
            Date date = new Date();
            tur = new Turno(new Timestamp(date.getTime()), jugadores);
            em.persist(tur);
            System.out.print(".");
            i++;
        }
        System.out.println("\n                  --> proceso terminado /_");
    }

    public static Persona obtenerPersonaAleatoria(ArrayList<Persona> perList) {
        Random rand = new Random();
        int indexRandom = rand.nextInt(perList.size());
        Persona personaRandom = perList.get(indexRandom);
        perList.remove(indexRandom);
        return personaRandom;
    }
}
