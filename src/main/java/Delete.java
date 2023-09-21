import dao.Direccion;
import dao.Turno;

import java.util.List;

public class Delete extends Conn {
    public static void main(String[] args) {
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
