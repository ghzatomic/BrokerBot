package br.com.brokerbot.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.brokerbot.data.converter.LeitorDTOToDadosPregaoEntity;
import br.com.brokerbot.data.dto.LeitorDTO;
import br.com.brokerbot.data.repository.DadosPregaoDAO;

@RestController
public class ImportadorDeArquivos {

	@Autowired
	private DadosPregaoDAO dadosPregaoDAO;
	
	@RequestMapping( method = {RequestMethod.GET}, value = "/importaArquivos")
	public void importaArquivos(){
		List<String> arquivos = new ArrayList<>(Arrays.asList(
				/*"E:/Public/Bolsa/COTAHIST_A2016/COTAHIST_A2016.txt",
				"E:/Public/Bolsa/COTAHIST_A2015/COTAHIST_A2015.txt",
				"E:/Public/Bolsa/COTAHIST_A2014/COTAHIST_A2014.txt",
				"E:/Public/Bolsa/COTAHIST_A2013/COTAHIST_A2013.txt",*/
				"E:/Public/Bolsa/COTAHIST_A2012/COTAHIST_A2012.txt"/*,
				"E:/Public/Bolsa/COTAHIST_A2011/COTAHIST_A2011.txt"*/
				));
		System.out.println("Iniciando ...");
		arquivos.forEach(string->{
			try (Stream<String> stream = Files.lines(Paths.get(string))) {

				stream.forEach(str->{
					try {
						LeitorDTO leitor = LeitorDTO.stringToLeitor(str);
						if (leitor != null){
							//System.out.println("Inserindo : "+str);
							dadosPregaoDAO.save(LeitorDTOToDadosPregaoEntity.dtoToEntity(leitor));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		System.out.println("Finalizado !");

	}
	
}
