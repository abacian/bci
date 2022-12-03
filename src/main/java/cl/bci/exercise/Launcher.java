package cl.bci.exercise;

import cl.bci.exercise.entities.AdminEntity;
import cl.bci.exercise.repositories.AdminRepository;
import cl.bci.exercise.utilities.LoggerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "cl.bci.exercise")
public class Launcher
extends LoggerUtility
implements ApplicationRunner {

    private AdminRepository adminRepository;

    public static void main (String... strings) {

        SpringApplication.run (Launcher.class, strings);

    }

    @Override
    public void run (ApplicationArguments applicationArguments) {

        logger.info ("BCI Exercise is ready");

        var adminEntity = new AdminEntity ();

        adminEntity.setEmail ("abacian@gmail.com");
        adminEntity.setUsername ("Alexis Bacian");
        adminEntity.setPassword ("$2a$10$1jlJRoe.ZG0V4YJzE9bGG.JFYt3fQT8H0JUPmwk71UMyhiYDEQ5rm");

        adminRepository.save (adminEntity);

        adminEntity = new AdminEntity ();

        adminEntity.setEmail ("reinaldo.horie@bci.cl");
        adminEntity.setUsername ("Reinaldo Horie");
        adminEntity.setPassword ("$2a$10$1jlJRoe.ZG0V4YJzE9bGG.JFYt3fQT8H0JUPmwk71UMyhiYDEQ5rm");

        adminRepository.save (adminEntity);

    }

    @Autowired
    public void setAdminRepository (AdminRepository adminRepository) {

        this.adminRepository = adminRepository;

    }

}