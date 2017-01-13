package br.com.brokerbot.data.converter;

import java.util.Date;

import br.com.brokerbot.data.dto.LeitorDTO;
import br.com.brokerbot.data.entity.DadosPregaoEntity;

public class LeitorDTOToDadosPregaoEntity {

	public static DadosPregaoEntity dtoToEntity(LeitorDTO dto){
		if (dto == null){
			return null;
		}
		DadosPregaoEntity dadosPregaoEntity = new DadosPregaoEntity();
		
		dadosPregaoEntity.setTipoRegistro(dto.getTipoRegistro());
		dadosPregaoEntity.setDiaPregao(dto.getDiaPregao());
		dadosPregaoEntity.setMesPregao(dto.getMesPregao());
		dadosPregaoEntity.setAnoPregao(dto.getAnoPregao());
		dadosPregaoEntity.setDataPregao(dto.getDataPregao());
		dadosPregaoEntity.setCodigoNegociacao(dto.getCodigoNegociacao().trim());
		dadosPregaoEntity.setNomeResumido(dto.getNomeResumido().trim());
		dadosPregaoEntity.setMoedaReferencia(dto.getMoedaReferencia());
		dadosPregaoEntity.setPrecoAbertura(dto.getPrecoAbertura());
		dadosPregaoEntity.setPrecoMaximo(dto.getPrecoMaximo());
		dadosPregaoEntity.setPrecoMinimo(dto.getPrecoMinimo());
		dadosPregaoEntity.setPrecoMedio(dto.getPrecoMedio());
		dadosPregaoEntity.setPrecoUltimoNegocio(dto.getPrecoUltimoNegocio());
		dadosPregaoEntity.setPrecoMelhorOfertaCompra(dto.getPrecoMelhorOfertaCompra());
		dadosPregaoEntity.setPrecoMelhorOfertaVenda(dto.getPrecoMelhorOfertaVenda());
		dadosPregaoEntity.setVolume(dto.getVolume());
		return dadosPregaoEntity;
	}
	
}
