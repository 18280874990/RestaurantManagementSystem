package cn.edu.uestc.rms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.uestc.rms.dao")
public class RmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RmsApplication.class, args);
	}
}
