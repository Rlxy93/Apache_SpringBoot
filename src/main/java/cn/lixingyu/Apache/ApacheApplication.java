package cn.lixingyu.Apache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.lixingyu.Apache.repository")
public class ApacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApacheApplication.class, args);
    }

}
