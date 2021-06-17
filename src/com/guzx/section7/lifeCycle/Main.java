package com.guzx.section7.lifeCycle;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/17 10:30
 * @describe
 */
public class Main {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("akkaActorSystem",
                ConfigFactory.load("demo.conf"));
        ActorRef worker = actorSystem.actorOf(Props.create(MyWorker.class), "worker");
        ActorRef watch = actorSystem.actorOf(Props.create(WatchActor.class, worker), "watch");

        worker.tell(MyWorker.MsgType.WORKING, ActorRef.noSender());
        worker.tell(MyWorker.MsgType.DONE, ActorRef.noSender());
        worker.tell(MyWorker.MsgType.CLOSE, ActorRef.noSender());
        worker.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }
}
