package model;

public class Motorcycle extends Vehicle {

    public Motorcycle(String registrationCode, String model, Client proprietary) {
        super(registrationCode, model, VehicleType.MOTORCYCLE, proprietary);
    }

    @Override
    public float getPrice() {
        return 0;
    }

}
