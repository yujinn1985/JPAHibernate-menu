package ua.i.licit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.*;

public class MenuController implements Controller<Menu> {
    private EntityManager em;
    private EntityManagerFactory emf;

    public MenuController() {
        emf = Persistence.createEntityManagerFactory("JPAHibernateTest");
        em = emf.createEntityManager();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addRow() {
        em.getTransaction().begin();
        try {
            Menu menu = createMenu();
            em.persist(menu);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            em.getTransaction().rollback();
        }
        System.out.println("The meal has been successfully added.");

    }

    @Override
    public List<Menu> selectByPrice() {
        for (; ; ) {
            Scanner sc = new Scanner(System.in);
            try {
                System.out.println("Enter a start price of the meal: ");
                Double startPrice = sc.nextDouble();
                System.out.println("Enter a final price of the meal: ");
                Double finalPrice = sc.nextDouble();
                return selectByPrice(startPrice, finalPrice);
            } catch (InputMismatchException ex) {
                System.out.println(ex);
                System.out.println("try again...");
            }
        }
    }

    @Override
    public List<Menu> selectByDiscount() {
        Query query = em.createQuery("SELECT m FROM Menu m WHERE m.sale=true", Menu.class);
        return (List<Menu>) query.getResultList();
    }

    @Override
    public List<Menu> pickMealsKilogramm() {
        Query query = em.createQuery("SELECT m FROM Menu m", Menu.class);
        List<Menu> list = (List<Menu>) query.getResultList();
        List<Menu> newlist = new ArrayList<>();
        double sum = 0;
        for (Menu m : list) {
            if ((sum + m.getWeight()) <= 1.0) {
                newlist.add(m);
                sum += m.getWeight();
            }
        }
        return newlist;
    }

    @Override
    public void viewMeals() {
        Query query = em.createQuery("SELECT m FROM Menu m", Menu.class);
        List<Menu> list = (List<Menu>) query.getResultList();
        for (Menu m :
                list) {
            System.out.println(m);
        }

    }

    @Override
    public void closeConnection() {
        try {
            em.close();
            emf.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Menu createMenu() {
        boolean sale;
        for (; ; ) {
            Scanner sc = new Scanner(System.in);
            try {
                System.out.println("Enter a name of the meal: ");
                String name = sc.nextLine();
                System.out.println("Does it have a discount? ");
                System.out.print("yes/no: ");
                String text = sc.nextLine();
                System.out.println("Enter a price of the meal: ");
                Double price = sc.nextDouble();
                System.out.println("Enter a weight of the meal: ");
                Double weight = sc.nextDouble();
                if (text.equals("yes")) {
                    sale = true;
                } else if (text.equals("no")) {
                    sale = false;
                } else {
                    System.out.println("try again...");
                    continue;
                }
                return new Menu(name, price, weight, sale);
            } catch (InputMismatchException ex) {
                System.out.println(ex);
                System.out.println("try again...");
            }
        }
    }

    private List<Menu> selectByPrice(Double from, Double to) {
        Query query = em.createQuery("SELECT m FROM Menu m WHERE m.price>=:fromPrice and m.price<=:toPrice", Menu.class);
        query.setParameter("fromPrice", from);
        query.setParameter("toPrice", to);
        return (List<Menu>) query.getResultList();

    }
}
