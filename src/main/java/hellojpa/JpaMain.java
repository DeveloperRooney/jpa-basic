package hellojpa;

import hellojpa.domain.Address;
import hellojpa.domain.Member;
import hellojpa.domain.Period;
import hellojpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            List<Member> list = em.createQuery(
                    "select m from Member m where m.name like '%kim$'"
                    , Member.class
            ).getResultList();
            for (Member member : list) {
                System.out.println("member = " + member.getName());
            }

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
