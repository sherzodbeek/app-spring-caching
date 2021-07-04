package uz.pdp.appspringcaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AppSpringCachingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppSpringCachingApplication.class, args);
    }

}
