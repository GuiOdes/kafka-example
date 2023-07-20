package com.guiodes.kafkaconsumer.configs

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@EnableKafka // Só é necessário na aplicação consumidora
@Configuration
class KafkaConsumerConfiguration(
    private val kafkaProperties: KafkaProperties
) {

    // Configura o consumer para deserializar as mensagens do tipo String String
    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        val configs = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers, // Configura as url dos servidores
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java, // Configura deserializador de chave
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java // Configura deserializador de valor
        )

        return DefaultKafkaConsumerFactory(configs)
    }

    // Configura o conteiner dos listeners
    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = consumerFactory()

        return factory
    }
}