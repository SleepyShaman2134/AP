package lab9.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lab9.CountryDAO;
import lab9.entities.city;
import lab9.entities.continent;
import lab9.entities.country;

public class Singleton {
    private int instance=0;

   public Singleton(){
       EntityManagerFactory emf =
               Persistence.createEntityManagerFactory("123");
       EntityManager em = emf.createEntityManager();

       em.getTransaction().begin();
       continent continent = new continent("Europe");
       em.persist(continent);

       continent c = (continent)em.createQuery(
                       "select e from continent e where e.name='Europe'")
               .getSingleResult();
       c.setName("Africa");
       em.getTransaction().commit();
       em.close();
       emf.close();

   }


}
