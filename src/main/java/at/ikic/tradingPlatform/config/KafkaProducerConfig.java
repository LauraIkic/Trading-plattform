package at.ikic.tradingPlatform.config;

import at.ikic.tradingPlatform.entity.Coin;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.List;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Bean
    public NewTopic createTopic() {
        return new NewTopic("cryptoCoinTopic",3, (short) 1 );
    }

   @Bean
    public KafkaTemplate<String, List<Coin>> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, List<Coin>> producerFactory() {
        var producerProps = new java.util.HashMap<String, Object>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(producerProps);
    }
}
