package net.afyer.afybroker.server.processor;

import com.alipay.remoting.AsyncContext;
import com.alipay.remoting.BizContext;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import net.afyer.afybroker.core.message.BrokerClientInfoMessage;
import net.afyer.afybroker.server.proxy.BrokerClientProxy;

/**
 * @author Nipuru
 * @since 2022/7/30 17:24
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterBrokerClientInfoBrokerProcessor extends BrokerAsyncUserProcessor<BrokerClientInfoMessage> {

    @Override
    public void handleRequest(BizContext bizCtx, AsyncContext asyncCtx, BrokerClientInfoMessage request) {
        request.setAddress(bizCtx.getRemoteAddress());

        BrokerClientProxy brokerClientProxy = new BrokerClientProxy(request, brokerServer.getRpcServer());
        brokerServer.getBrokerClientProxyManager().register(brokerClientProxy);

        log.info("BrokerClient remoteAddress : {} successfully registered", bizCtx.getRemoteAddress());
    }

    @Override
    public String interest() {
        return BrokerClientInfoMessage.class.getName();
    }

}
