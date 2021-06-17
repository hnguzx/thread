package com.guzx.section7.lifeCycle;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedAbstractActor;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/17 10:21
 * @describe
 */
public class WatchActor extends UntypedAbstractActor {
    public WatchActor(ActorRef actorRef) {
        getContext().watch(actorRef);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Terminated) {
            System.out.println(String.format("%s has terminated,shutting down system",
                    (((Terminated) message).getActor().path())));
            getContext().system().terminate();
        } else {
            unhandled(message);
        }
    }
}
