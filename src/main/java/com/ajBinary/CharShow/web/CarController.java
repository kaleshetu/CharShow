package com.ajBinary.CharShow.web;

import java.util.List;

import com.ajBinary.CharShow.entity.Car;
import com.ajBinary.CharShow.repository.CarRepository;
import com.ajBinary.CharShow.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/cars")
public class CarController {
    @Autowired
    private CarService carService;
    @GetMapping("/all")
    public ResponseEntity<List<Car>> getCars(){
        return new ResponseEntity<>(carService.getCars(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar( @PathVariable Long id){
        return new ResponseEntity<>(carService.getCarById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Car> saveCar(@RequestBody Car car){
        return  new ResponseEntity<>(carService.saveCar(car),HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id,Car car){
        return new ResponseEntity<>(carService.updateCar(id,car),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}