package com.mycompany.app;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.util.Collections;
import java.util.Properties;

class TestConsumer {

    private KafkaConsumer<String, String> consumer;

    //confugura e cria um consumer no tópico recebido
    void configConsumer(String topic) {

        //Definição dos parâmetros de configuração
        //Lista de parâmetros: https://kafka.apache.org/documentation.html#newconsumerconfigs
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        //Criação do cunsumer com as configurações definidas
        consumer = new KafkaConsumer<>(props);

        //seleção do tópico
        consumer.subscribe(Collections.singletonList(topic));
    }

    void msgConsume() {
        ConsumerRecords<String, String> records = consumer.poll(1000); //procura por dados nos tópicos especificados
        for (ConsumerRecord<String, String> record : records)
            System.out.printf("offset = %d, key = %s, value = %s\n\n" , record.offset(), record.key(), record.value()); //print offset key e mesagem
    }
}

