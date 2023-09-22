import dao.Direccion;
import dao.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Select{
    protected final static String PERSISTENCE = "tp2e1";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE);
    protected static EntityManager em = emf.createEntityManager();
    public static void main(String[] args) {
        // coneccion
        em.getTransaction().begin();

        Persona j = em.find(Persona.class, 1);
        System.out.println(j);

        // -------------------------------------- CONSULTAS POR PERSONAS
        // GET ALL
        @SuppressWarnings("unchecked")
        Query personaQuery = em.createNamedQuery(Persona.BUSCAR_TODOS);
        List<Persona> personaList = personaQuery.getResultList();
        System.out.println("\nLista de todos las Personas");
        for (Persona per : personaList) {
            System.out.println(per);
        }
        // FILTRADO POR EDAD
        int min = 18;
        int max = 44;
        personaQuery = em.createNamedQuery(Persona.FILTRAR_POR_EDAD);
        personaQuery.setParameter(1, min);
        personaQuery.setParameter(2, max);
        personaList = personaQuery.getResultList();
        System.out.println("Lista de Personas entre " + min + " y " + max);
        for (Persona per : personaList) {
            System.out.println(per);
        }

        // -------------------------------------- CONSULTAS POR PERSONAS
        // GET ALL
        @SuppressWarnings("unchecked")
        Query direccionQuery = em.createNamedQuery(Direccion.BUSACAR_TODOS);
        List<Direccion> direcciones = direccionQuery.getResultList();
        System.out.println("\nLista de todas las direcciones");
        for(Direccion dir : direcciones){
            System.out.println(dir);
        }

        // CLOSE
        em.close();
        emf.close();
    }
}
