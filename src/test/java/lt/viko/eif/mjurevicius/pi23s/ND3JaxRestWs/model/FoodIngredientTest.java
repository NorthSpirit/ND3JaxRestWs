package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodIngredientTest {

    @Test
    void getName() {
        FoodIngredient ingredient = new FoodIngredient();
        ingredient.setName("Sugar");
        assertEquals("Sugar", ingredient.getName());
    }

    @Test
    void setName() {
        FoodIngredient ingredient = new FoodIngredient();
        ingredient.setName("Rye");
        assertEquals("Rye", ingredient.getName());
        ingredient.setName("Sunflower Oil");
        assertEquals("Sunflower Oil", ingredient.getName());
    }

    @Test
    void getMeasurement() {
        FoodIngredient ingredient = new FoodIngredient();
        ingredient.setMeasurement("grams");
        assertEquals("grams", ingredient.getMeasurement());
    }

    @Test
    void setMeasurement() {
        FoodIngredient ingredient = new FoodIngredient();
        ingredient.setMeasurement("ml");
        assertEquals("ml", ingredient.getMeasurement());
        ingredient.setMeasurement("pieces");
        assertEquals("pieces", ingredient.getMeasurement());
    }

    @Test
    void getAmount() {
        FoodIngredient ingredient = new FoodIngredient();
        ingredient.setAmount(10);
        assertEquals(10, ingredient.getAmount());
    }

    @Test
    void setAmount() {
        FoodIngredient ingredient = new FoodIngredient();
        ingredient.setAmount(5);
        assertEquals(5, ingredient.getAmount());
        ingredient.setAmount(20);
        assertEquals(20, ingredient.getAmount());
    }

    @Test
    void testConstructorAndGetters() {
        FoodIngredient ingredient = new FoodIngredient();
        ingredient.setName("Sugar");
        ingredient.setMeasurement("teaspoons");
        ingredient.setAmount(2);

        assertEquals("Sugar", ingredient.getName());
        assertEquals("teaspoons", ingredient.getMeasurement());
        assertEquals(2, ingredient.getAmount());
    }
}