package pcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabarcPCMApplication {
	
	public static final String PCM_CONF_FILE_NOT_FOUND_MSG = "There is no pcm configuration file";

	public static void main(String[] args) {
		SpringApplication.run(LabarcPCMApplication.class, args);
	}
}
