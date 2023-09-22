import dao.Direccion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class Update {
    protected final static String PERSISTENCE = "tp2e1";
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE);
    protected static EntityManager em = emf.createEntityManager();
    public static void main(String[] args) {
        // comenzar transaccion
        em.getTransaction().begin();

        // Actualizar direciones
        // se actualiza la tabla porque los datos de la columna ciudad y dirección estaban cruzados
        List<Direccion> direcciones = getAllDirections(em);
        updateDireccion(em, direcciones);

        // commit
        em.getTransaction().commit();

        // cierre
        em.close();
        emf.close();
    }

    ///////////////////////////////////////////////////////////     UPDATE DIRECCIONES MAL CARGADAS
    public static void updateDireccion(EntityManager em, List<Direccion> direcciones) {
        Iterator<Direccion> itDir = direcciones.iterator();
        Direccion dir = null;
        System.out.print("Actualizando Dirección --> ");

        while (itDir.hasNext()) {
            dir = itDir.next();
            String calle = dir.getCalle();
            dir.setCalle(dir.getCiudad());
            dir.setCiudad(calle);
            em.merge(dir);
            System.out.print(".");
        }
        System.out.println("\n                  --> proceso update terminado /_");
    }
    public static List<Direccion> getAllDirections(EntityManager em) {
        @SuppressWarnings("unchecked")
        Query direccionQuery = em.createNamedQuery(Direccion.BUSACAR_TODOS);
        return direccionQuery.getResultList();
    }
}
