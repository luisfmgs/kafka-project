package com.mycompany.app;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;
import java.util.Scanner;

 class TestProducer {

    private KafkaProducer<String, String> producer;

    //create the messages offset
    private int key = 0;

    //configura e cria um producer
    void configProducer() {

        //Definição dos parâmetros de configuração
        //Lista de parâmetros: https://kafka.apache.org/documentation.html#producerconfigs
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);
    }

    //pega entrada do usuário e publica no tópico especificado
    void msgProduce(String topic) {
        System.out.println("Digite sua msg:");
        Scanner input = new Scanner(System.in);
        String msg = input.nextLine();
        producer.send(new ProducerRecord<>(topic, Integer.toString(key), msg)); //parâmetros: tópico,key,value
        key += 1;
    }
}
