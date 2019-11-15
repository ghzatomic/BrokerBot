package br.com.brokerbot.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.brokerbot.data.converter.LeitorDTOToDadosPregaoEntity;
import br.com.brokerbot.data.dto.LeitorDTO;
import br.com.brokerbot.data.entity.DadosPregaoEntity;
import br.com.brokerbot.data.repository.DadosPregaoDAO;

@Configuration
public class ImportadorDeArquivos implements CommandLineRunner {

	@Autowired
	private DadosPregaoDAO dadosPregaoDAO;

	public void importaArquivos() {
		List<String> arquivos = new ArrayList<>(
				Arrays.asList("C:/git/BrokerData/COTAHIST_A2019.txt", "C:/git/BrokerData/COTAHIST_A2018.txt"
				/*
				 * "C:/git/BrokerData/COTAHIST_A2014.txt",
				 * "C:/git/BrokerData/COTAHIST_A2013.txt",
				 * "C:/git/BrokerData/COTAHIST_A2012.txt",
				 * "C:/git/BrokerData/COTAHIST_A2011.txt"
				 */
				));
		System.out.println("Iniciando ...");
		arquivos.stream().forEach(string -> {
				try {
					List<DadosPregaoEntity> lista = Files.lines(Paths.get(string)).parallel().map(str -> LeitorDTOToDadosPregaoEntity.dtoToEntity(LeitorDTO.stringToLeitor(str))).filter(data -> data != null).collect(Collectors.toList());
					System.out.println("Inserindo "+lista.size()+" dados ");
					final AtomicInteger counter = new AtomicInteger(0);
					lista.stream().collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 5000)).values().parallelStream().forEach(pagina->{
						System.out.println("Salvando pagina de "+pagina.size());
						dadosPregaoDAO.save(pagina);
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
		});

		System.out.println("Finalizado !");

	}

	@Override
	public void run(String... args) throws Exception {
		importaArquivos();
	}

}
