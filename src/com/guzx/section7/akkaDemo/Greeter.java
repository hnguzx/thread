package com.guzx.section7.akkaDemo;

import akka.actor.UntypedAbstractActor;

import java.util.Optional;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/16 9:35
 * @describe
 */
public class Greeter extends UntypedAbstractActor {
    public static enum MsgType {
        GREET, DONE
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == MsgType.GREET) {
            System.out.println("444");
            System.out.println("Greeter 收到消息");
            // 向消息发送方回复
            getSender().tell(MsgType.DONE, getSelf());
        } else {
            unhandled(message);
        }
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("333");
    }

    @Override
    public void preRestart(Throwable reason, Optional<Object> message) throws Exception {
        super.preRestart(reason, message);
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
    }
}
