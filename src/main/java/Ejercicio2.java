import dao.Persona;
import dao.Socio;
import dao.Turno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Ejercicio2 {
    protected final static String PERSISTENCE = "tp2e1";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE);
    protected static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {
        // OPEN CONNECTION
        em.getTransaction().begin();

        /*
            (a) recuperar todas las personas asignadas a un turno.
         */
        int idTurno = 60419;
        Turno t = em.find(Turno.class, idTurno);
        for (Persona per : personasDeUnTurno(t)) {
            System.out.println(per);
        }

        /*
            (b) recuperar todas las personas asignadas a un turno,
                y marcar cuales de ellas son socios.
        */
        for (Persona per : personasDeUnTurno(t)) {
            String tipo = tipoSocio(per);
            if (!tipo.equals(Socio.SOCIO_TIPO_SUSPENDIDO))
                System.out.println(per + "\t\t <-- " + tipo);
            else
                System.out.println(per);
        }

        /*
            (c) recuperar todas las personas que viven en una ciudad predeterminada.
         */
        String ciudad = "tandil";
        for (Persona per : personasPorCiudad(ciudad)) {
            System.out.println(per);
        }

        // CLOSE
        em.close();
        emf.close();
    }

    // (a) FUNCTION
    public static List<Persona> personasDeUnTurno(Turno tBuscado) {
        Query query = em.createQuery(
                "SELECT p FROM Persona p " +
                        "JOIN p.turnos t " +
                        "WHERE t.id = :turnoBuscado");
        query.setParameter("turnoBuscado", tBuscado.getId());
        return query.getResultList();
    }

    // (b) FUNCTION
    public static List<Persona> sociosDeUnTurno(Turno tBuscado) {
        Query query = em.createQuery(
                "SELECT s FROM Persona p " +
                        "JOIN s.persona p " +
                        "JOIN p.turnos t " +
                        "WHERE s.tipo LIKE 'suspendido' " +
                        "AND t.id = :turnoBuscado ");
        query.setParameter("turnoBuscado", tBuscado.getId());
        return query.getResultList();
    }

    // otra forma de ahcer esto es mapoear en el socio la clave de persona para hacer un
    // joint entre Persona Socio y Turno, y de esa forma resolverlo en una consulta SQL
    public static String tipoSocio(Persona p) {
        Query query = em.createQuery(
                "SELECT s FROM Socio s " +
                        "JOIN s.persona p " +
                        "WHERE p.id = :idPersona");
        query.setParameter("idPersona", p.getId());
        List<Socio> res = query.getResultList();
        return res.get(0).getTipo();
    }

    // (c) FUNCTION
    public static List<Persona> personasPorCiudad(String ciudad) {
        Query query = em.createQuery(
                "SELECT p FROM Persona p " +
                        "JOIN p.domicilio d " +
                        "WHERE d.ciudad = :ciudad");
        query.setParameter("ciudad", ciudad);
        return query.getResultList();
    }
}
