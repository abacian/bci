package cl.bci.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "cl.bci.example")
public class Launcher
implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger (Launcher.class);

    public static void main (String... strings) {

        SpringApplication.run (Launcher.class, strings);

    }

    @Override
    public void run (ApplicationArguments applicationArguments) {

        logger.info ("BCI example on ready");

    }

}