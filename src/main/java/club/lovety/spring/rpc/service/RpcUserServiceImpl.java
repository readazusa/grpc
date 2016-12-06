package club.lovety.spring.rpc.service;

import club.lovety.rpc.model.User;
import club.lovety.rpc.service.LoadUserGrpc;
import io.grpc.stub.StreamObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by 念梓  on 2016/12/6.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
public class RpcUserServiceImpl extends LoadUserGrpc.LoadUserImplBase {

    private static final Logger log = LogManager.getLogger(RpcUserServiceImpl.class);

    public RpcUserServiceImpl() {
    }

    @Override
    public void load(User.UserRequest request, StreamObserver<User.UserResponse> responseObserver) {
        log.debug("接收客户端的传输数据: {}",request.getUsername());
        User.UserResponse response = User.UserResponse.newBuilder().setMobile("13411114321").setUsername("好好").setAge(12).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
