package org.p01.spring_boot_p01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Notification {
    @Autowired
    MessageService messageService;

    Notification(){

    }

    Notification(MessageService messageService){
        this.messageService = messageService;
    }

    public void notifyUser(){
        System.out.println(messageService.sendMessage());
    }
}
