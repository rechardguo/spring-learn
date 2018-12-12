package rechard.learn.springcloud.kafka.raw.api;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaProducerDemo {


    public static void main(String[] args) {
        sendMsg();
    }

    public static void sendMsg(){

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.1.111:9092");
        Serializer keySerializer = new StringSerializer();
        Serializer valueSerializer = new StringSerializer();
        KafkaProducer producer = new KafkaProducer(properties,keySerializer,valueSerializer);

        String topic = "mykafka";
        Integer partition =0;
        Long timestamp = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
           // send(producer, topic, partition, timestamp, i);
            send2(producer, topic, partition, timestamp, i);
        }
    }

    //
    private static void send2(KafkaProducer producer, String topic, Integer partition, Long timestamp, int i) {
        ProducerRecord record = new ProducerRecord(topic,partition,timestamp,"key","value"+i);
        Future<RecordMetadata> future = producer.send(record,(recordMetadata,e)->{
            System.out.println("sync-offset:"+recordMetadata.offset()+
                    "->partition"+recordMetadata.partition());
        });
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void send(KafkaProducer producer, String topic, Integer partition, Long timestamp, int i) {
        ProducerRecord record = new ProducerRecord(topic,partition,timestamp,"key","value"+i);
        Future<RecordMetadata> future = producer.send(record);
        try {
            RecordMetadata recordMetadata = future.get();
            System.out.println("sync-offset:"+recordMetadata.offset()+
                    "->partition"+recordMetadata.partition());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
