package pl.mr.springannotations.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mr.springannotations.controller.CarController;
import pl.mr.springannotations.controller.PostController;
import pl.mr.springannotations.model.Car;
import pl.mr.springannotations.service.CarService;
import pl.mr.springannotations.service.PostService;

@Configuration
public class SpringAnnotationConf {


    @Bean
    Car car () {
        return new Car();
    }

    @Bean
    CarService carService () {
        return new CarService(car());
    }

    @Bean
    CarController carController () {
        return new CarController(carService());
    }
}
