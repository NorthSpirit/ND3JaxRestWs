package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model;

/**
 * Na ingredient used in a food item, meant to be seen by customers, not detailed for chefs.
 */
public class FoodIngredient {

    /**
     * Name of ingredient, like dziugas cheese in a pizza.
     */
    private String name;
    /**
     * Unit of measurement, like grams, teaspoons, units.
     */
    private String measurement;
    /**
     * And amount of measurement units of ingredients like 2 apples, 50 grams, 2 teaspoons.
     */
    private int amount;

    /**
     * Default constructor for creating a new FoodIngredient instance.
     */
    public FoodIngredient() {
    }

    /**
     * Gets the name of the food ingredient.
     *
     * @return The name of the ingredient.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the food ingredient.
     *
     * @param name The name to set for the ingredient.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the unit of measurement for the ingredient.
     *
     * @return The measurement unit of the ingredient.
     */
    public String getMeasurement() {
        return measurement;
    }

    /**
     * Sets the unit of measurement for the ingredient.
     *
     * @param measurement The measurement unit to set for the ingredient.
     */
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    /**
     * Gets the amount of the ingredient.
     *
     * @return The quantity of the ingredient.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the ingredient.
     *
     * @param amount The quantity to set for the ingredient.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }


}
