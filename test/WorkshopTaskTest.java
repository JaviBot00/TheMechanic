package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Car;
import model.Client;
import model.Mechanic;
import model.Van;
import model.Vehicle;
import model.WorkshopTask;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for WorkshopTask class.
 */
public class WorkshopTaskTest {

    private Client client;
    private Vehicle car;
    private Mechanic mechanic;
    private WorkshopTask task;

    @BeforeEach
    public void setUp() {
        client = new Client("Jane", "Smith", "Johnson", "87654321B", "jane@example.com", "555-5678", 2);
        car = new Car("CAR-002", "Honda Civic", client);
        mechanic = new Mechanic("Carlos", "Garcia", "Lopez", "11111111C", "carlos@example.com", "555-9999",
                "2020-01-15", "General Mechanics");
        task = new WorkshopTask("Engine diagnostic", 4f, "2025-03-10 10:00", client, car, mechanic);
    }

    @Test
    public void testTaskCreation() {
        assertNotNull(task);
        assertEquals("Engine diagnostic", task.getDiagnostic());
        assertEquals(4f, task.getPreviewHours());
        assertFalse(task.isFinished());
        assertFalse(task.isPaid());
    }

    @Test
    public void testEstimatedCost() {
        // Car: 25€/hour, estimated 4 hours = 100€
        assertEquals(100f, task.getEstimatedCost());
    }

    @Test
    public void testTotalCostWhenNotFinished() {
        // Cost should be 0 until task is finished
        assertEquals(0f, task.getTotalCost());
    }

    @Test
    public void testAddHours() {
        task.addHours(2f);
        assertEquals(2f, task.getRealHours());

        task.addHours(1.5f);
        assertEquals(3.5f, task.getRealHours());
    }

    @Test
    public void testAddHoursThrowsWhenFinished() {
        task.finish();
        assertThrows(IllegalStateException.class, () -> task.addHours(1f));
    }

    @Test
    public void testAddZeroHoursThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> task.addHours(0f));
    }

    @Test
    public void testAddNegativeHoursThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> task.addHours(-1f));
    }

    @Test
    public void testFinishTask() {
        assertFalse(task.isFinished());
        task.finish();
        assertTrue(task.isFinished());
    }

    @Test
    public void testTotalCostAfterFinishing() {
        task.addHours(4f);
        task.finish();

        // Car: 25€/hour × 4 hours = 100€
        assertEquals(100f, task.getTotalCost());
    }

    @Test
    public void testGetProgress() {
        assertEquals(0f, task.getProgress());

        task.addHours(1f);
        // 1 hour out of 4 = 25%
        assertEquals(25f, task.getProgress());

        task.addHours(3f);
        // 4 hours out of 4 = 100%
        assertEquals(100f, task.getProgress());
    }

    @Test
    public void testProgressCappedAt100Percent() {
        task.addHours(5f); // More than estimated
        assertEquals(100f, task.getProgress());
    }

    @Test
    public void testProgressWithZeroEstimate() {
        WorkshopTask zeroTask = new WorkshopTask("Test", 0f, "2025-03-10", client, car, mechanic);
        assertEquals(0f, zeroTask.getProgress());
    }

    @Test
    public void testMarkAsPaid() {
        task.addHours(4f);
        task.finish();
        task.markAsPaid();
        assertTrue(task.isPaid());
    }

    @Test
    public void testMarkAsPaidThrowsWhenNotFinished() {
        assertThrows(IllegalStateException.class, () -> task.markAsPaid());
    }

    @Test
    public void testMarkAsUnpaid() {
        task.addHours(4f);
        task.finish();
        task.markAsPaid();
        task.markAsUnpaid();
        assertFalse(task.isPaid());
    }

    @Test
    public void testGetStatus() {
        assertEquals("Pending", task.getStatus());

        task.addHours(1f);
        assertEquals("In Progress", task.getStatus());

        task.finish();
        assertEquals("Finished", task.getStatus());

        task.markAsPaid();
        assertEquals("Paid", task.getStatus());
    }

    @Test
    public void testSetDiagnostic() {
        task.setDiagnostic("Transmission fluid leak");
        assertEquals("Transmission fluid leak", task.getDiagnostic());
    }

    @Test
    public void testSetDiagnosticIgnoresBlank() {
        String original = task.getDiagnostic();
        task.setDiagnostic("   ");
        assertEquals(original, task.getDiagnostic());
    }

    @Test
    public void testSetSolution() {
        task.setSolution("Replace transmission seals");
        assertEquals("Replace transmission seals", task.getSolution());
    }

    @Test
    public void testSetPreviewHours() {
        task.setPreviewHours(6f);
        assertEquals(6f, task.getPreviewHours());
    }

    @Test
    public void testSetPreviewHoursIgnoresNegative() {
        float original = task.getPreviewHours();
        task.setPreviewHours(-5f);
        assertEquals(original, task.getPreviewHours());
    }

    @Test
    public void testTaskComparison() {
        WorkshopTask task1 = new WorkshopTask("Test1", 2, "2025-03-01", client, car, mechanic);
        WorkshopTask task2 = new WorkshopTask("Test2", 3, "2025-03-05", client, car, mechanic);
        assertTrue(task1.compareTo(task2) < 0);
    }

    @Test
    public void testNotes() {
        String notes = "Customer mentioned unusual noise when braking";
        task.setNotes(notes);
        assertEquals(notes, task.getNotes());
    }

    @Test
    public void testDifferentVehiclePrices() {
        // Van task: 30€/hour + 30€ fixed
        WorkshopTask vanTask = new WorkshopTask("Service", 3f, "2025-03-10", client,
                new Van("VAN-002", "Mercedes Sprinter", client), mechanic);
        vanTask.addHours(3f);
        vanTask.finish();
        assertEquals(120f, vanTask.getTotalCost()); // (3 * 30) + 30
    }

    @Test
    public void testCompleteWorkflow() {
        // Task created
        assertEquals("Pending", task.getStatus());
        assertEquals(0f, task.getTotalCost());

        // Work starts
        task.addHours(2f);
        assertEquals("In Progress", task.getStatus());
        assertEquals(50f, task.getProgress()); // 2/4

        // More work
        task.addHours(2f);
        assertEquals(100f, task.getProgress());

        // Task completed
        task.finish();
        assertEquals("Finished", task.getStatus());
        assertEquals(100f, task.getTotalCost()); // 4 * 25

        // Payment processed
        task.markAsPaid();
        assertEquals("Paid", task.getStatus());
    }
}
