package ua.i.licit;

import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "menu_id")
    private long id;

    @Column(name = "meal_name", nullable = false)
    private String name;

    @Column(name = "meal_price")
    private Double price;

    @Column(name = "meal_weight")
    private Double weight;

    private boolean sale;

    public Menu(String name, Double price, Double weight, boolean sale) {
        super();
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.sale = sale;
    }

    public Menu() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", sale=" + sale +
                '}';
    }
}
