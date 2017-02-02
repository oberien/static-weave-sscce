package foo.bar.sscce;

import foo.bar.sscce.jpa.SampleDbCreator;
import foo.bar.sscce.jpa.entities.Customer;
import foo.bar.sscce.jpa.entities.Sale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Member;
import java.util.Map;


public class Main {
    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager em;

    public static void main(String[] args) throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("sscce");
        SampleDbCreator dbCreator = new SampleDbCreator(entityManagerFactory);
        dbCreator.initDatabase();

        EntityManager em = entityManagerFactory.createEntityManager();
        Metamodel m = em.getMetamodel();
        ManagedType<Customer> customer = m.managedType(Customer.class);
        for (SingularAttribute<Customer, ?> singularAttrib : customer.getDeclaredSingularAttributes()) {
            System.out.println(singularAttrib.getName());
        }
        ManagedType<Sale> sale = m.managedType(Sale.class);
        for (SingularAttribute<Sale, ?> singularAttrib : sale.getDeclaredSingularAttributes()) {
            System.out.println("SingularAttribute::getName: " + singularAttrib.getName());
            Member javaMember = singularAttrib.getJavaMember();
            System.out.println("Member::getName:            " + javaMember.getName());
        }
    }
}
