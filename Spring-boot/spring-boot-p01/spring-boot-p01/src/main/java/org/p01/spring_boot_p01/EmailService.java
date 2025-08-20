package org.p01.spring_boot_p01;

import org.springframework.stereotype.Service;

@Service
public class EmailService implements MessageService{
    @Override
    public String sendMessage() {
        return "Email: you got a new mail!";
    }
}
