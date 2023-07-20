package com.guiodes.kafkaconsumer.listeners

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class MessageListener {

    @KafkaListener(
        topics = ["study-topic"],
        groupId = "study-group"
    )
    fun listen(message: String) {
        print("Thread: ${Thread.currentThread().id} ")
        println("Nova mensagem recebida: $message")
    }
}