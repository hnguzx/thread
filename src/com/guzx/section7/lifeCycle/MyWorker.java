package com.guzx.section7.lifeCycle;

import akka.actor.UntypedAbstractActor;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/17 10:14
 * @describe
 */
public class MyWorker extends UntypedAbstractActor {
    public static enum MsgType {
        WORKING, DONE, CLOSE
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("MyWorker is starting");
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("MyWorker is stopping");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message == MsgType.WORKING) {
            System.out.println("I'm working");
        } else if (message == MsgType.DONE) {
            System.out.println("Stop working");
        } else if (message == MsgType.CLOSE) {
            System.out.println("I'm shutdown");
            getSender().tell(MsgType.CLOSE, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(message);
        }
    }


}
