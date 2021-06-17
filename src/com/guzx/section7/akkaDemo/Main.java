package com.guzx.section7.akkaDemo;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/16 9:47
 * @describe
 */
public class Main {
    public static void main(String[] args) {
        // actor系统，一般一个应用只有一个
        ActorSystem actorSystem = ActorSystem.create("akkaActorSystem", ConfigFactory.load("demo.conf"));
        // 顶级actor
        System.out.println("111");
        ActorRef actorRef = actorSystem.actorOf(Props.create(AkkaResp.class), "akkaResp");
        System.out.println("顶级actor path:" + actorRef.path());

    }
}
