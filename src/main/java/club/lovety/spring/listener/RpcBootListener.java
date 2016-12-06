package club.lovety.spring.listener;

import club.lovety.spring.rpc.MainRpc;
import club.lovety.spring.rpc.service.GreeterImpl;
import club.lovety.spring.rpc.service.RpcUserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by 念梓  on 2016/12/6.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
public class RpcBootListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger log = LogManager.getLogger(RpcBootListener.class);


    //开启rpc的主服务
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        MainRpc mainRpc=  applicationReadyEvent.getApplicationContext().getBean(MainRpc.class);
        GreeterImpl greeter = new GreeterImpl();
        RpcUserServiceImpl userService = new RpcUserServiceImpl();
        mainRpc.start(8000,greeter,userService);
        log.debug("=========================");
    }

}
