package model;

/**
 * Motorcycle class representing a motorcycle in the workshop.
 * Billing rate: 20€/hour with no fixed difficulty fee.
 */
public class Motorcycle extends Vehicle {

    private static final float HOURLY_RATE = 20f;
    private static final float FIXED_FEE = 0f;

    public Motorcycle(String registrationCode, String model, Client proprietary) {
        super(registrationCode, model, VehicleType.MOTORCYCLE, proprietary);
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
