package br.com.brokerbot.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
	public void importaArquivos(){
		String caminhoPadrao = "/git/TraderData/";
		List<String> arquivos = new ArrayList<>(Arrays.asList(
				//caminhoPadrao+"COTAHIST_A2014.txt",
				caminhoPadrao+"COTAHIST_A2019.txt",
				caminhoPadrao+"COTAHIST_A2018.txt",
				caminhoPadrao+"COTAHIST_A2017.txt",
				caminhoPadrao+"COTAHIST_A2016.TXT",
				caminhoPadrao+"COTAHIST_A2015.TXT"
				));
		System.out.println("Iniciando ...");
		arquivos.parallelStream().forEach(string -> {
				try {
					System.out.println("Processando : "+string);
					List<DadosPregaoEntity> lista = Files.lines(Paths.get(string)).parallel().map(str -> LeitorDTOToDadosPregaoEntity.dtoToEntity(LeitorDTO.stringToLeitor(str))).filter(data -> data != null).collect(Collectors.toList());
					System.out.println("Inserindo "+lista.size()+" dados ");
					final AtomicInteger counter = new AtomicInteger(0);
					lista.stream().collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 5000)).values().parallelStream().forEach(pagina->{
						System.out.println("Salvando pagina de "+pagina.size());
						dadosPregaoDAO.saveAll(pagina);
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
