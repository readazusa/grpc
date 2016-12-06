package club.lovety.spring;

import club.lovety.spring.listener.RpcBootListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by 念梓  on 2016/12/6.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
@SpringBootApplication
public class AppMain {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AppMain.class);
        springApplication.addListeners(new RpcBootListener());
        springApplication.run();
    }

}
