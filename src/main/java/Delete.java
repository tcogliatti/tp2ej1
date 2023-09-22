import dao.Direccion;
import dao.Turno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Delete {
    protected final static String PERSISTENCE = "tp2e1";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE);
    protected static EntityManager em = emf.createEntityManager();
    public static void main(String[] args) {
        // coneccion
        em.getTransaction().begin();

        // Eliminar un turno id: 60418
        int idTurno = 60418;
        Turno turno = em.find(Turno.class, idTurno);

        // eliminar turno
        if (turno != null) {
            em.remove(turno);
            em.getTransaction().commit();
            System.out.println("El turno ID: " + idTurno + " se elimino correctamente");
        }else{
            System.out.println("El turno id:" + idTurno + " no fue encontrado.");
        }

        // cierre
        em.close();
        emf.close();
    }
}
