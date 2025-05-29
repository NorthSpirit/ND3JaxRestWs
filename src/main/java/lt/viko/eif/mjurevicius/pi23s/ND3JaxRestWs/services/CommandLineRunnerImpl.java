package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * A Spring component that implements the {@code CommandLineRunner} interface.
 * This runner is executed after the Spring application context has been initialized.
 * It's typically used to perform initialization tasks or run specific code at startup.
 */
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    /**
     * Callback used to run the bean logic after Spring context initialization.
     * In this implementation, it prints simple messages to the console indicating
     * that the application has started.
     *
     * @param args Array of command line arguments passed to the application.
     * @throws Exception if any exception is thrown during the execution of this method.
     */
    @Override
    public void run(String... args) throws Exception {

        //FoodMenus foodMenus = JSONMarshall.unmashallFoodMenu("FoodMenus");
        //FoodOrders foodOrders = JSONMarshall.unmarshallFoodOrder("FoodOrders");

        System.out.println("So far, so good...");
        System.out.println("...I hope.");
    }
}