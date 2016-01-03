package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

import spittr.data.SpitterRepository;

@Configuration
public class RmiConfig {

	@Bean
	public RmiServiceExporter rmiExporter(SpitterRepository spitterRepository) {
	RmiServiceExporter rmiExporter = new RmiServiceExporter();
	rmiExporter.setService(spitterRepository);
	rmiExporter.setServiceName("SpitterRepository");
	rmiExporter.setServiceInterface(SpitterRepository.class);
/*	domyślnie na porcie 1099 lokalnego komputera
 * rmiExporter.setRegistryHost("rmi.spitter.com");
	rmiExporter.setRegistryPort(1199);*/
	return rmiExporter;
	}
}
