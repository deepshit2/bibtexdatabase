package wad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import wad.profiles.DevProfile;
import wad.profiles.ProdProfile;

@SpringBootApplication
@Import({DevProfile.class, ProdProfile.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
