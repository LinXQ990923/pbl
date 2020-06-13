package fudan.edu.pbl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("fudan.edu.pbl.mapper")
public class PblApplication {

    public static void main(String[] args) {
        SpringApplication.run(PblApplication.class, args);
    }

}
