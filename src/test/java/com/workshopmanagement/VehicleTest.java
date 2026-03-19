package src.test.java.com.workshopmanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.main.java.com.workshopmanagement.model.Car;
import src.main.java.com.workshopmanagement.model.Client;
import src.main.java.com.workshopmanagement.model.Motorcycle;
import src.main.java.com.workshopmanagement.model.Truck;
import src.main.java.com.workshopmanagement.model.Van;
import src.main.java.com.workshopmanagement.model.Vehicle;
import src.main.java.com.workshopmanagement.model.WorkshopTask;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Vehicle classes and billing calculations.
 */
public class VehicleTest {

    private Client client;
    private Motorcycle motorcycle;
    private Car car;
    private Van van;
    private Truck truck;

    @BeforeEach
    public void setUp() {
        client = new Client("John", "Doe", "Smith", "12345678A", "john@example.com", "555-1234", 1);
        motorcycle = new Motorcycle("MOTO-001", "Honda CB500", client);
        car = new Car("CAR-001", "Toyota Corolla", client);
        van = new Van("VAN-001", "Volkswagen Transporter", client);
        truck = new Truck("TRUCK-001", "MAN TGX", client);
    }

    @Test
    public void testMotorcycleHourlyRate() {
        assertEquals(20f, motorcycle.getHourlyRate());
    }

    @Test
    public void testCarHourlyRate() {
        assertEquals(25f, car.getHourlyRate());
    }

    @Test
    public void testVanHourlyRate() {
        assertEquals(30f, van.getHourlyRate());
    }

    @Test
    public void testTruckHourlyRate() {
        assertEquals(40f, truck.getHourlyRate());
    }

    @Test
    public void testMotorcyclePriceCalculation() {
        // 5 hours at 20€/hour = 100€
        assertEquals(100f, motorcycle.calculatePrice(5f));
    }

    @Test
    public void testCarPriceCalculation() {
        // 4 hours at 25€/hour = 100€
        assertEquals(100f, car.calculatePrice(4f));
    }

    @Test
    public void testVanPriceCalculation() {
        // 2 hours at 30€/hour + 30€ fixed = 90€
        assertEquals(90f, van.calculatePrice(2f));
    }

    @Test
    public void testTruckPriceCalculation() {
        // 2 hours at 40€/hour + 50€ fixed = 130€
        assertEquals(130f, truck.calculatePrice(2f));
    }

    @Test
    public void testZeroHoursPriceCalculation() {
        assertEquals(0f, motorcycle.calculatePrice(0f));
        assertEquals(30f, van.calculatePrice(0f)); // Only fixed fee
        assertEquals(50f, truck.calculatePrice(0f)); // Only fixed fee
    }

    @Test
    public void testNegativeHoursThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> motorcycle.calculatePrice(-5f));
        assertThrows(IllegalArgumentException.class, () -> truck.calculatePrice(-1f));
    }

    @Test
    public void testVehicleRegistration() {
        assertEquals("MOTO-001", motorcycle.getRegistrationCode());
        assertEquals("CAR-001", car.getRegistrationCode());
    }

    @Test
    public void testVehicleModel() {
        assertEquals("Honda CB500", motorcycle.getModel());
        assertEquals("Toyota Corolla", car.getModel());
    }

    @Test
    public void testVehicleType() {
        assertEquals(Vehicle.VehicleType.MOTORCYCLE, motorcycle.getType());
        assertEquals(Vehicle.VehicleType.CAR, car.getType());
        assertEquals(Vehicle.VehicleType.VAN, van.getType());
        assertEquals(Vehicle.VehicleType.TRUCK, truck.getType());
    }

    @Test
    public void testVehicleProprietor() {
        assertEquals(client, motorcycle.getProprietary());
        assertEquals(client, truck.getProprietary());
    }

    @Test
    public void testAddWorkshopTask() {
        WorkshopTask task = new WorkshopTask("Oil change", 1, "2025-03-10 09:00", client, car, null);
        assertTrue(car.addWorkshopTask(task));
        assertEquals(1, car.getWorkshopTasksCount());
    }

    @Test
    public void testRemoveWorkshopTask() {
        WorkshopTask task = new WorkshopTask("Oil change", 1, "2025-03-10 09:00", client, car, null);
        car.addWorkshopTask(task);
        assertTrue(car.removeWorkshopTask(task));
        assertEquals(0, car.getWorkshopTasksCount());
    }

    @Test
    public void testVehicleComparison() {
        Vehicle v1 = new Car("AAA-001", "Tesla Model 3", client);
        Vehicle v2 = new Car("BBB-001", "BMW 320", client);
        assertTrue(v1.compareTo(v2) < 0);
    }

    @Test
    public void testGetCompletionPercentageNoTasks() {
        assertEquals(0f, car.getCompletionPercentage());
    }

    @Test
    public void testVehicleToString() {
        String str = motorcycle.toString();
        assertNotNull(str);
        assertTrue(str.contains("MOTO-001"));
    }
}
