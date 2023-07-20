package com.guiodes.kafkaproducer.controllers

import com.guiodes.kafkaproducer.producers.StudyProducer
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/send")
class ProducerController(
    private val studyProducer: StudyProducer
) {

    @PostMapping
    fun sendMessage(message: String, times: Int) {
        for (i in 0..times) studyProducer.produce(message+i)
    }
}