package br.com.brokerbot;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.brokerbot.data.ImportadorDeArquivoService;

@RestController
public class ImportadorRest {
	
	@Autowired
	private ImportadorDeArquivoService importador;

	@RequestMapping(value = "/importa", method = RequestMethod.POST)
	public String create(@RequestParam("file") MultipartFile file,@RequestParam("ano") Integer ano) throws IOException {    
		ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes());
		BufferedReader bfr = new BufferedReader(new InputStreamReader(bais));
		importador.importaArquivos(bfr,ano);
        return "file succesfully received!";
	}
	
}
