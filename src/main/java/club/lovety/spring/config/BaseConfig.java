package club.lovety.spring.config;

import club.lovety.spring.rpc.MainRpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 念梓  on 2016/12/6.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
@Configuration
public class BaseConfig {

    @Bean
    public MainRpc loadMainRcp(){
        MainRpc mainRpc = new MainRpc();
        return mainRpc;
    }

}
