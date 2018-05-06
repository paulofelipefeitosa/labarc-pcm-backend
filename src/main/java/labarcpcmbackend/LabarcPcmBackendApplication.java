package labarcpcmbackend;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import labarcpcmbackend.core.exception.PCMException;

@SpringBootApplication
public class LabarcPcmBackendApplication {
	
	private static final String ADMIN_CONFIGURARION_FILE = "config/pcm.conf";
	
	public static final String PCM_CONF_FILE_NOT_FOUND_MSG = "There is no pcm configuration file";

	public static void main(String[] args) throws PCMException, IOException {
		SpringApplication.run(LabarcPcmBackendApplication.class, args);
		
		File file = new File(LabarcPcmBackendApplication.ADMIN_CONFIGURARION_FILE);
		if (!file.exists()) {
			throw new PCMException(LabarcPcmBackendApplication.PCM_CONF_FILE_NOT_FOUND_MSG);
		}
		InputStream inputStream = new FileInputStream(file);
		
		Properties properties = new Properties();
		properties.load(inputStream);
	}
}
