package org.p01.spring_boot_p01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootP01Application implements CommandLineRunner {

	Notification notification;

	public SpringBootP01Application(Notification notification){
		this.notification = notification;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootP01Application.class, args);
    }


	@Override
	public void run(String... args) throws Exception {
		notification.notifyUser();
	}
}
