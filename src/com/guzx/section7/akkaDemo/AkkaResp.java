package com.guzx.section7.akkaDemo;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/16 9:40
 * @describe
 */
public class AkkaResp extends UntypedAbstractActor {
    ActorRef actorRef;

    @Override
    public void preStart() throws Exception {
        System.out.println("222");
        // akka的初始化
        actorRef = getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Greeter Actor Path:" + actorRef.path());
        actorRef.tell(Greeter.MsgType.GREET, getSelf());
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == Greeter.MsgType.DONE) {
            System.out.println("555");
            // 作为接收方接收到消息，然后回复发送方
            getSender().tell(Greeter.MsgType.GREET, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(message);
        }
    }
}
