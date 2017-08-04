package to.ogarne.ogarneblog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import to.ogarne.ogarneblog.dao.StorageDao;
import to.ogarne.ogarneblog.service.tools.BufferedImageThumbnailer;

import java.awt.image.BufferedImage;

/**
 * Created by jedrz on 16.07.2017.
 */
@ComponentScan
@EnableAutoConfiguration
public class Application {



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


    }

    @Bean
    public CommandLineRunner init(StorageDao storageDao) {
        return (args) -> {
            storageDao.init();
        };
    }

}
