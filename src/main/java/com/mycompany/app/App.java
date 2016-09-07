package com.mycompany.app;

public class App {
    public static void main( String[] args ) {

        TestConsumer consumer1 = new TestConsumer();
        TestProducer producer1 = new TestProducer();

        producer1.configProducer();
        consumer1.configConsumer("fixo");

        while(true) {
            producer1.msgProduce("fixo");
            consumer1.msgConsume();
        }
    }
}