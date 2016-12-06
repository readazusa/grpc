package club.lovety.grpc;

import club.lovety.rpc.helloworld.GreeterGrpc;
import club.lovety.rpc.helloworld.HelloReply;
import club.lovety.rpc.helloworld.HelloRequest;
import club.lovety.rpc.model.User;
import club.lovety.rpc.service.LoadUserGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import io.grpc.stub.StreamObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by 念梓  on 2016/12/6.
 * Email:sunmch@163.com
 * author: 念梓
 * des: grpc服务端
 */
public class HelloWorldServer {

    private static final Logger log = LogManager.getLogger(HelloWorldServer.class);

    private int port = 6000;

    private Server server = null;


    //开启一个rpc服务
    private void start(){
        try {
            server = ServerBuilder.forPort(port).addService(new GreeterImpl()).addService(new UserServiceImpl()).build().start();
            log.debug("开启了一个rpc服务");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // block 一直到退出程序
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {

        HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutdown();
    }


    //对外提供的服务
    private class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
//            System.out.println("service:"+req.getName());
            log.debug("service: {}",req.getName());
            HelloReply reply = HelloReply.newBuilder().setMessage(("Hello: " + req.getName())).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }

    private class UserServiceImpl extends LoadUserGrpc.LoadUserImplBase{
        @Override
        public void load(User.UserRequest request, StreamObserver<User.UserResponse> responseObserver) {
            log.debug("开启一个服务,username={}",request.getUsername());
            User.UserResponse response = User.UserResponse.newBuilder().setAge(10).setMobile("13412341234").setUsername("糖糖").build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();


        }
    }

}
