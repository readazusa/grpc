package club.lovety.spring.rpc.service;

import club.lovety.rpc.helloworld.GreeterGrpc;
import club.lovety.rpc.helloworld.HelloReply;
import club.lovety.rpc.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by 念梓  on 2016/12/6.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    private static final Logger log = LogManager.getLogger(GreeterImpl.class);

    public GreeterImpl() {
    }

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        log.debug("读取客户端的数据: {}",request.getName());
        HelloReply helloReply = HelloReply.newBuilder().setMessage("你好啊").build();
        responseObserver.onNext(helloReply);
        responseObserver.onCompleted();
    }
}
