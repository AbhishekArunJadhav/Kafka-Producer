package com.kafka.demo.controller;

import com.kafka.demo.service.KafkaMsgProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMsgProducerService publisher;

    @GetMapping("/publish/{msg}")
    public ResponseEntity<?> publishMessage(@PathVariable String msg){
       try{
           for (int i = 0; i < 10000; i++) {
               publisher.sendMsgToTopic(msg+" : "+i);
           }
           return ResponseEntity.ok("Message published Successfully...");
       }catch (Exception ex){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }

    }
}
