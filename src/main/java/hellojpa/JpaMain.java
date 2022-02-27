package hellojpa;

import hellojpa.domain.Item;
import hellojpa.domain.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Movie movie = new Movie();
            movie.setName("iron man");
            movie.setDirector("dr");
            movie.setActor("ac");
            movie.setPrice(12000);

            em.persist(movie);

            em.flush();
            em.clear();

            Movie fmovie = em.find(Movie.class, movie.getId());
            System.out.println("=========" + fmovie.getName() + "==========");

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
