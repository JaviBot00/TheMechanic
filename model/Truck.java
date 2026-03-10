package model;

public class Truck extends Vehicle {

    private final int fijoDificultad = 40;

    public Truck(String registrationCode, String model, Client proprietary) {
        super(registrationCode, model, VehicleType.TRUCK, proprietary);
    }

    public int getFijoDificultad() {
        return fijoDificultad;
    }

    @Override
    public float getPrice() {
        return fijoDificultad;
    }
}
