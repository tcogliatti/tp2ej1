import dao.Persona;

import java.util.List;

public class Select extends Conn{
    public static void main(String[] args) {
        em.getTransaction().begin();

        Persona j = em.find(Persona.class, 1);
        System.out.println(j);

        @SuppressWarnings("unchecked")
        List<Persona> pers = em.createQuery("SELECT p FROM Persona p").getResultList();
        for (Persona per : pers) {
            System.out.println(per);
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
