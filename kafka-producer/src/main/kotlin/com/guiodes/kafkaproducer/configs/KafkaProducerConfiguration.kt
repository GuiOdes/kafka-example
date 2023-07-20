package com.guiodes.kafkaproducer.configs

import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafkaProducerConfiguration(
    private val kafkaProperties: KafkaProperties
) {

    /*
     Cria um producer factory que produzirá mensagens com chave e valor
     do tipo String
    */
    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        val configs = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers, // Configura as url dos servidores
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java, // Configura serializador de chave
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java // Configura serializador de valor
        )

        return DefaultKafkaProducerFactory(configs)
    }

    // Configura kafka template para chave e valor do tipo String também
    @Bean
    fun kafkaTemplate() = KafkaTemplate(producerFactory())

    // OPCIONAL A PARTIR DA PRÓXIMA LINHA


    // Permite que a aplicação consiga criar tópicos como admin do kafka
    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val configs = mapOf(
            AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers
        )

        return KafkaAdmin(configs)
    }

    // Criação do tópico em si
    @Bean
    fun topicoDeEstudos(): KafkaAdmin.NewTopics {
        return KafkaAdmin.NewTopics(
            TopicBuilder
                .name("study-topic")
                .partitions(10)
                .build(),
//            TopicBuilder
//                .name("other-topic")
//                .build(),
        )
    }
}
