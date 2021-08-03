package com.example.demo;

import com.example.demo.time.TimeZoneVietnamese;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class Demo2Application {
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone(TimeZoneVietnamese.timezone));
    }
    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }
}
