package br.com.brokerbot.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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
		String caminhoPadrao = "E:\\git\\cotacoes\\";
		List<String> arquivos = new ArrayList<>(Arrays.asList(
				//caminhoPadrao+"COTAHIST_A2014.txt"
				/*caminhoPadrao+"COTAHIST_A2019.txt",
				caminhoPadrao+"COTAHIST_A2018.txt",
				caminhoPadrao+"COTAHIST_A2017.txt",
				caminhoPadrao+"COTAHIST_A2016.TXT",
				caminhoPadrao+"COTAHIST_A2015.TXT",
				caminhoPadrao+"COTAHIST_A2015.TXT",*/
				caminhoPadrao+"COTAHIST_A2014.TXT",
				caminhoPadrao+"COTAHIST_A2013.TXT",
				caminhoPadrao+"COTAHIST_A2012.TXT",
				caminhoPadrao+"COTAHIST_A2011.TXT",
				caminhoPadrao+"COTAHIST_A2010.TXT",
				caminhoPadrao+"COTAHIST_A2009.TXT",
				caminhoPadrao+"COTAHIST_A2008.TXT",
				caminhoPadrao+"COTAHIST_A2007.TXT",
				caminhoPadrao+"COTAHIST_A2006.TXT"
				));
		System.out.println("Iniciando ...");
		arquivos.stream().forEach(string -> {
				try {
					System.out.println("Processando : "+string);
					CharsetDecoder dec=StandardCharsets.UTF_8.newDecoder()
			                  .onMalformedInput(CodingErrorAction.IGNORE);
					Path path=Paths.get(string);
					List<String> lines;
					try(Reader r=Channels.newReader(FileChannel.open(path), dec, -1);
					    BufferedReader br=new BufferedReader(r)) {
					        lines=br.lines()
					                .collect(Collectors.toList());
					}
					List<DadosPregaoEntity> lista = lines.parallelStream().map(str -> LeitorDTOToDadosPregaoEntity.dtoToEntity(LeitorDTO.stringToLeitor(str))).filter(data -> data != null).collect(Collectors.toList());
					System.out.println("Inserindo "+lista.size()+" dados ");
					final AtomicInteger counter = new AtomicInteger(0);
					lista.stream().collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 10000)).values().parallelStream().forEach(pagina->{
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
