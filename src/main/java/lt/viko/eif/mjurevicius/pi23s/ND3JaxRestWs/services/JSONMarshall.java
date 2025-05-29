package lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model.FoodMenus;
import lt.viko.eif.mjurevicius.pi23s.ND3JaxRestWs.model.FoodOrders;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * Utility class responsible for marshalling (writing) and unmarshalling (reading)
 * FoodMenus and FoodOrders objects to and from JSON files.
 * It uses the Jackson library for JSON processing and handles Java 8 Date and Time API.
 */
public class JSONMarshall {

    /**
     * A static instance of ObjectMapper configured to handle Java 8 Date and Time API
     * and to disable writing dates as timestamps for better readability.
     */
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    /**
     * Writes a FoodMenus object to a JSON file.
     *
     * @param foodMenus The FoodMenus object to write.
     * @param fileName  The name of the JSON file.
     * @return true if writing was successful, false otherwise.
     */
    public static boolean writeJsonToFile(FoodMenus foodMenus, String fileName) {
        if (!fileName.endsWith(".json")) {
            fileName += ".json";
        }
        try {
            File file = new File(fileName); // Create a File object in the working directory
            objectMapper.writeValue(file, foodMenus);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing FoodMenus to JSON file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Writes a FoodOrders object to a JSON file.
     *
     * @param foodOrders The FoodOrders object to write.
     * @param fileName  The name of the JSON file to write to.
     * @return true if writing was successful, false otherwise.
     */
    public static boolean writeJsonToFile(FoodOrders foodOrders, String fileName) {
        if (!fileName.endsWith(".json")) {
            fileName += ".json";
        }
        try {
            File file = new File(fileName); // Create a File object in the working directory
            objectMapper.writeValue(file, foodOrders);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing FoodOrders to JSON file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Unmarshalls a JSON string from a file to a FoodMenus object.
     *
     * @param fileName The name of the JSON file.
     * @return The FoodMenus object, or null on error.
     */
    public static FoodMenus unmashallFoodMenu(String fileName) {

        if (!fileName.endsWith(".json")) {
            fileName += ".json";
        }
        try {
            ClassPathResource resource = new ClassPathResource(fileName);
            return objectMapper.readValue(resource.getFile(), FoodMenus.class);
        } catch (IOException e) {
            System.out.println("Error unmarshalling FoodMenus: " + e.getMessage());
            return null;
        }
    }

    /**
     * Unmarshalls a JSON string from a file to a FoodOrder object.
     *
     * @param fileName The name of the JSON file.
     * @return The FoodOrder object, or null on error.
     */
    public static FoodOrders unmarshallFoodOrder(String fileName) {
        if (!fileName.endsWith(".json")) {
            fileName += ".json";
        }
        try {
            ClassPathResource resource = new ClassPathResource(fileName);
            return objectMapper.readValue(resource.getFile(), FoodOrders.class);
        } catch (IOException e) {
            System.out.println("Error unmarshalling FoodOrder: " + e.getMessage());
            return null;
        }
    }

}
