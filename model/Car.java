package model;

/**
 * Car class representing a car in the workshop.
 * Billing rate: 25€/hour with no fixed difficulty fee.
 */
public class Car extends Vehicle {

    private static final float HOURLY_RATE = 25f;
    private static final float FIXED_FEE = 0f;

    public Car(String registrationCode, String model, Client proprietary) {
        super(registrationCode, model, VehicleType.CAR, proprietary);
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
