package pl.mr.springannotations.service;

import pl.mr.springannotations.model.Car;

public class CarService {

    private Car car;

    public CarService(Car car) {
        this.car = car;
    }

    public void getCarName (){
        car.setName("SEAT");
        System.out.println(car.getName());
    }
}
