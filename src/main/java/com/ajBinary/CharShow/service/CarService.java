package com.ajBinary.CharShow.service;

import com.ajBinary.CharShow.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    Car updateCar(Long id, Car car);

    // get all cars
    List<Car> getCars();
    // find car by id
    Car getCarById(Long id);
    Car saveCar(Car car);

    void deleteCar(Long id);
}