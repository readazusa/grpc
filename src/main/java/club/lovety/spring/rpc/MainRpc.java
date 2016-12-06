package club.lovety.spring.rpc;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by 念梓  on 2016/12/6.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
public class MainRpc {

    
    private static  final Logger log = LogManager.getLogger(MainRpc.class);

    private Server server;

    public void start(int port,Object ... src){
        ServerBuilder serverBuilder = ServerBuilder.forPort(port);
        if(null != src){
             for(Object obj : src){
                 serverBuilder.addService((BindableService)obj);
             }
        }
        try {
            server =  serverBuilder.build().start();
            log.debug("开启grcp服务成功");
        } catch (IOException e) {
            log.error("开启grcp服务失败: ",e);
        }
    }
}
