package br.com.brokerbot.data.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.brokerbot.data.entity.DadosPregaoEntity;

public interface DadosPregaoDAO extends MongoRepository<DadosPregaoEntity, String> {
	
	Long deleteByAnoPregao(Integer ano);

}