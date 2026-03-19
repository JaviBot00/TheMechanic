package src.main.java.com.workshopmanagement.model;

/**
 * Truck class representing a truck in the workshop.
 * Billing rate: 40€/hour + 50€ fixed difficulty fee.
 */
public class Truck extends Vehicle {

    private static final float HOURLY_RATE = 40f;
    private static final float FIXED_FEE = 50f;

    public Truck(String registrationCode, String model, Client proprietary) {
        super(registrationCode, model, VehicleType.TRUCK, proprietary);
    }

    @Override
    public float getHourlyRate() {
        return HOURLY_RATE;
    }

    @Override
    public float getFixedDifficultyFee() {
        return FIXED_FEE;
    }
}
