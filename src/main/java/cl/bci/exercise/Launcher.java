package cl.bci.exercise;

import cl.bci.exercise.utilities.LoggerUtility;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "cl.bci.exercise")
public class Launcher
extends LoggerUtility
implements ApplicationRunner {

    public static void main (String... strings) {

        SpringApplication.run (Launcher.class, strings);

    }

    @Override
    public void run (ApplicationArguments applicationArguments) {

        logger.info ("BCI Exercise is ready");

    }

}