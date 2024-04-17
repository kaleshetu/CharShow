package com.ajBinary.CharShow.service;

import com.ajBinary.CharShow.entity.Car;
import com.ajBinary.CharShow.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarRepository carRepository;

    @Override
    public Car updateCar(Long id, Car car) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if(optionalCar.isPresent()){
            optionalCar.get().setBrand(car.getBrand());
            optionalCar.get().setModel(car.getModel());
            optionalCar.get().setColor(car.getColor());
            optionalCar.get().setPrice(car.getPrice());
            optionalCar.get().setYear(car.getYear());
            optionalCar.get().setOwner(car.getOwner());

            carRepository.save(optionalCar.get());

            return optionalCar.get();
        } else {
            throw new ResourceNotFoundException("Car with"+id+" does not exits");}}

    @Override
    public List<Car> getCars() {
        return (List<Car>) carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return null;
    }

    @Override
    public Car saveCar(Car car) {
        return null;
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}




