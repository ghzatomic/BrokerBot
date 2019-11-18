package br.com.brokerbot.data.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import br.com.brokerbot.data.entity.DadosPregaoEntity;

@Transactional
public interface DadosPregaoDAO extends IBaseRepository<DadosPregaoEntity, Long> {

	public List<DadosPregaoEntity> findAllByCodigoNegociacaoAndAnoPregao(String cod,Integer ano);
	public List<DadosPregaoEntity> findAllByAnoPregaoBetweenAndMesPregaoBetween(Integer anoIni,Integer anoFim,Integer mesIni, Integer mesFim);
	public List<DadosPregaoEntity> findAllByCodigoNegociacaoAndAnoPregaoBetweenAndMesPregaoBetween(String cod,Integer anoIni,Integer anoFim,Integer mesIni, Integer mesFim);
	public List<DadosPregaoEntity> findAllByAnoPregaoBetweenAndMesPregaoBetweenAndDiaPregaoBetween(Integer anoIni,Integer anoFim,Integer mesIni, Integer mesFim,Integer diaIni, Integer diaFim);
	public List<DadosPregaoEntity> findAllByCodigoNegociacaoAndAnoPregaoBetweenAndMesPregaoBetweenAndDiaPregaoBetween(String cod,Integer anoIni,Integer anoFim,Integer mesIni, Integer mesFim,Integer diaIni, Integer diaFim);
	
}