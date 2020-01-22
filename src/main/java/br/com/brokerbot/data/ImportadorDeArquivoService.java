package br.com.brokerbot.data;

import java.io.BufferedReader;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.brokerbot.data.converter.LeitorDTOToDadosPregaoEntity;
import br.com.brokerbot.data.dto.LeitorDTO;
import br.com.brokerbot.data.entity.DadosPregaoEntity;
import br.com.brokerbot.data.repository.DadosPregaoDAO;

@Component
public class ImportadorDeArquivoService {

	@Autowired
	private DadosPregaoDAO dadosPregaoDAO;

	public void importaArquivos(BufferedReader bfr, Integer ano) {
		//String caminhoPadrao = "E:\\git\\cotacoes\\" + "COTAHIST_A2019.txt";
		dadosPregaoDAO.deleteByAnoPregao(ano);
		List<String> lines = bfr.lines().collect(Collectors.toList());
		List<DadosPregaoEntity> lista = lines.parallelStream()
				.map(str -> LeitorDTOToDadosPregaoEntity.dtoToEntity(LeitorDTO.stringToLeitor(str)))
				.filter(data -> data != null).collect(Collectors.toList());
		System.out.println("Inserindo " + lista.size() + " dados ");
		final AtomicInteger counter = new AtomicInteger(0);
		lista.stream().collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 10000)).values().parallelStream()
				.forEach(pagina -> {
					System.out.println("Salvando pagina de " + pagina.size());
					dadosPregaoDAO.saveAll(pagina);
				});
		System.out.println("Finalizado !");

	}

}
