package model;

public class Van extends Vehicle {

    private final int fijoDificultad = 30;

    public Van(String registrationCode, String model, Client proprietary) {
        super(registrationCode, model, VehicleType.VAN, proprietary);
    }

    public int getFijoDificultad() {
        return fijoDificultad;
    }

    @Override
    public float getPrice() {
        return 0;
    }
}
