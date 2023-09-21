import dao.Persona;
import dao.Socio;
import dao.Turno;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class Select extends Conn {
    public static void main(String[] args) {
        em.getTransaction().begin();

        Persona j = em.find(Persona.class, 1);
        System.out.println(j);

        // -------------------------------------- CONSULTAS POR PERSONAS
        // GET ALL
        @SuppressWarnings("unchecked")
        Query personaQuery = em.createNamedQuery(Persona.BUSCAR_TODOS);
        List<Persona> personaList = personaQuery.getResultList();
        System.out.println("Lista de todos las Personas");
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


        // CLOSE
        em.close();
        emf.close();
    }


    ///////////////////////////////////////////////////////////     QUERIES TURNOS
    ///////////////////////////////////////////////////////////     QUERIES DIRECCIONES

    ///////////////////////////////////////////////////////////     QUERIES SOCIOS

    ///////////////////////////////////////////////////////////     QUERIES PERSONAS

}
