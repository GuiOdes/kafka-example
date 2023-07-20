package com.guiodes.kafkaproducer.producers

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class StudyProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    fun produce(message: String) {
        kafkaTemplate.send("study-topic", message)
    }
}