package com.ajBinary.CharShow.web;

import com.ajBinary.CharShow.entity.Car;
import com.ajBinary.CharShow.service.CarService;
import com.ajBinary.CharShow.web.CarController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class CarControllersTest {
    @InjectMocks// it allows to test the actual response
        private MockMvc mockMvc;
    @Mock
    private CarService carService;
    @InjectMocks
    private CarController carController;
  /*  @BeforeEach
    void setUpMocks(){
        //ststic import to Mockito for the "when" method
        when(carService.getCarById(1L)).thenReturn
                (new Car(1L,"Ford","F-350","White","123dfgt2",2010,
               12345 ));
    }*/
    //This is not functioning
    @Test
    public void getByIdshouldReturnOKIfExists(){
        ResponseEntity<Car> respEntity=carController.getCar(1L);
        assertEquals(HttpStatusCode.valueOf(200),respEntity.getStatusCode());
        assertEquals("F-350",respEntity.getBody().getModel());
    }
    @Test
    public void getAllCarsShouldReturnArrayAndStatusOK() throws Exception {
        when(carService.getCarById(1L)).thenReturn
                (new Car(1L,"Ford","F-350","White","123dfgt2",2010,
                        12345 ));
        when(carService.getCars()).thenReturn(new ArrayList<Car>());
        HttpHeaders head = new HttpHeaders();
        head.setBasicAuth("admin","adminPass");
        mockMvc.perform(get("/api/v1/cars/all").headers(head))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(startsWith("[")));
    }
}
