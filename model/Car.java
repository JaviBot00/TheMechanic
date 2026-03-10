package model;

public class Car extends Vehicle {

    public Car(String registrationCode, String model, Client proprietary) {
        super(registrationCode, model, VehicleType.CAR, proprietary);
    }

    @Override
    public float getPrice() {
        return 0;
    }

}
