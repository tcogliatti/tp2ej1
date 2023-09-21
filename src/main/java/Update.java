import dao.Persona;

public class Update extends Conn{
    public static void main(String[] args) {

        em.getTransaction().begin();

        Persona p = em.find(Persona.class, 1);

        p.setNombre("Silvio");
        p.setEdad(55);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
