package org.nju.sesta.sams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SamsApplication.class, args);
        System.out.println("启动成功");
    }

}

