package com.ajBinary.CharShow;

import com.ajBinary.CharShow.entity.Car;
import com.ajBinary.CharShow.entity.Owner;
import com.ajBinary.CharShow.repository.CarRepository;
import com.ajBinary.CharShow.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class CarShowApplication implements CommandLineRunner {
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private OwnerRepository ownerRepository;

	private static final Logger logger =  LoggerFactory.getLogger(CarShowApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(CarShowApplication.class, args);
		logger.info("Application started");

	}

	@Override
	public void run(String... args) throws Exception {

		Owner owner1 = new Owner("John","Doe");
		Owner owner2 = new Owner("Jack","Biden");
		ownerRepository.saveAll(Arrays.asList(owner1,owner2));
		List<Car>  cars = Arrays.asList(

				new Car("Ford","Mustang","Red","ADF_1121",2021,49000,owner1),
				new Car("Nissan","Leaf","Gray","EBF_1221",2020,32500,owner2),
				new Car("Toyota","Camry","Silver","CDF_3123",2021,32000,owner1),
				new Car("Toyota","Corolla","White","DDF_3421",2023,40000,owner2)
		);
		carRepository.saveAll(cars);
		carRepository.findAll().forEach(car -> logger.info(car.getBrand() +" "+car.getModel()));
	}
}