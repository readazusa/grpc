package club.lovety.grpc;

import club.lovety.rpc.helloworld.GreeterGrpc;
import club.lovety.rpc.helloworld.HelloReply;
import club.lovety.rpc.helloworld.HelloRequest;
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
public class HelloWorldClient {

    private static final Logger log = LogManager.getLogger(HelloWorldClient.class);

    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    public HelloWorldClient(String host, int port){
        channel = ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext(true)
                .build();
        log.debug("开启了一个rpc客户端");
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public  void greet(String name){
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response = blockingStub.sayHello(request);
        System.out.println(response.getMessage());
    }

    public static void main(String[] args) throws InterruptedException {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1",8000);
        for(int i=0;i<5;i++){
            client.greet("world:"+i);
        }
        client.shutdown();
    }

}
