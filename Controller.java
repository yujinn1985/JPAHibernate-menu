package ua.i.licit;

import java.util.List;

public interface Controller<E> {
    void addRow();

    List<E> selectByPrice();

    List<E> selectByDiscount();

    List<E> pickMealsKilogramm();

    void viewMeals();

    void closeConnection();

}
