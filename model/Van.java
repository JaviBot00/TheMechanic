package model;

/**
 * Van class representing a van in the workshop.
 * Billing rate: 30€/hour + 30€ fixed difficulty fee.
 */
public class Van extends Vehicle {

    private static final float HOURLY_RATE = 30f;
    private static final float FIXED_FEE = 30f;

    public Van(String registrationCode, String model, Client proprietary) {
        super(registrationCode, model, VehicleType.VAN, proprietary);
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
