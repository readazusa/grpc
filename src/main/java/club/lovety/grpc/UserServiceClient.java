package club.lovety.grpc;

import club.lovety.rpc.model.User;
import club.lovety.rpc.service.LoadUserGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created by 念梓  on 2016/12/6.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
public class UserServiceClient {

    private static final Logger log = LogManager.getLogger(UserServiceClient.class);

    private final ManagedChannel managedChannel;

    private final LoadUserGrpc.LoadUserBlockingStub blockingStub;

    public UserServiceClient(String host, int port) {
        managedChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
        log.debug("开启一个service客户端");
        blockingStub = LoadUserGrpc.newBlockingStub(managedChannel);
    }

    public void start(String host, int port) {

    }

    public void shutdown(){
        try {
            managedChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void loadUser(String username){
        User.UserRequest request =   User.UserRequest.newBuilder().setUsername(username).build();
        User.UserResponse response = blockingStub.load(request);
        log.debug("服务端的返回信息,age={},mobile={},username={}",response.getAge(),response.getMobile(),response.getUsername());
    }


    public static void main(String[] args) {
        UserServiceClient userServiceClient = new UserServiceClient("127.0.0.1",8000);
//        userServiceClient.start("127.0.0.1",6000);
        for(int i=0;i<10;i++){
            userServiceClient.loadUser("好好"+i);
        }
        userServiceClient.shutdown();
    }

}
