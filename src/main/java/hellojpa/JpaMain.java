package hellojpa;

import hellojpa.domain.Member;
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

            Child child1 = new Child();
            Child child2 = new Child();
            Parent parent = new Parent();
            parent.addCilid(child1);
            parent.addCilid(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

    private static void pringMemberAndTeam(Member member) {
        String userName = member.getName();
        System.out.println("MEMBER NAME = " + userName);

        String teamName = member.getTeam().getName();
        System.out.println("TEAM NAME = " + teamName);
    }
}
