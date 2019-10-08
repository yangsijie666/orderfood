package xyz.diaodeyipi.orderfood;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("xyz.diaodeyipi.orderfood.dao")
public class OrderfoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderfoodApplication.class, args);
    }

}
