package br.com.brokerbot.data.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LeitorDTO {
	
	//00 - 02
	public String tipoRegistro;
	//08 - 10
	public Integer diaPregao;
	//06 - 08
	public Integer mesPregao;
	//02 - 06
	public Integer anoPregao;
	//02 - 10
	public Date dataPregao;
	//12 - 24
	public String codigoNegociacao;
	//27 - 39
	public String nomeResumido;
	//52 - 56
	public String moedaReferencia;
	//56 - 69
	public Double precoAbertura;
	//69 - 82
	public Double precoMaximo;
	//82 - 95
	public Double precoMinimo;
	//95 - 108
	public Double precoMedio;	
	//108 - 121
	public Double precoUltimoNegocio;
	//121 - 134
	public Double precoMelhorOfertaCompra;
	//134 - 147	
	public Double precoMelhorOfertaVenda;
	//170 - 188
	public Double volume;
	
	public String getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public Integer getDiaPregao() {
		return diaPregao;
	}
	public void setDiaPregao(Integer diaPregao) {
		this.diaPregao = diaPregao;
	}
	public Integer getMesPregao() {
		return mesPregao;
	}
	public void setMesPregao(Integer mesPregao) {
		this.mesPregao = mesPregao;
	}
	public Integer getAnoPregao() {
		return anoPregao;
	}
	public void setAnoPregao(Integer anoPregao) {
		this.anoPregao = anoPregao;
	}
	public Date getDataPregao() {
		return dataPregao;
	}
	public void setDataPregao(Date dataPregao) {
		this.dataPregao = dataPregao;
	}
	public String getCodigoNegociacao() {
		return codigoNegociacao;
	}
	public void setCodigoNegociacao(String codigoNegociacao) {
		this.codigoNegociacao = codigoNegociacao;
	}
	public String getNomeResumido() {
		return nomeResumido;
	}
	public void setNomeResumido(String nomeResumido) {
		this.nomeResumido = nomeResumido;
	}
	public String getMoedaReferencia() {
		return moedaReferencia;
	}
	public void setMoedaReferencia(String moedaReferencia) {
		this.moedaReferencia = moedaReferencia;
	}
	public Double getPrecoAbertura() {
		return precoAbertura;
	}
	public void setPrecoAbertura(Double precoAbertura) {
		this.precoAbertura = precoAbertura;
	}
	public Double getPrecoMaximo() {
		return precoMaximo;
	}
	public void setPrecoMaximo(Double precoMaximo) {
		this.precoMaximo = precoMaximo;
	}
	public Double getPrecoMinimo() {
		return precoMinimo;
	}
	public void setPrecoMinimo(Double precoMinimo) {
		this.precoMinimo = precoMinimo;
	}
	public Double getPrecoMedio() {
		return precoMedio;
	}
	public void setPrecoMedio(Double precoMedio) {
		this.precoMedio = precoMedio;
	}
	public Double getPrecoUltimoNegocio() {
		return precoUltimoNegocio;
	}
	public void setPrecoUltimoNegocio(Double precoUltimoNegocio) {
		this.precoUltimoNegocio = precoUltimoNegocio;
	}
	public Double getPrecoMelhorOfertaCompra() {
		return precoMelhorOfertaCompra;
	}
	public void setPrecoMelhorOfertaCompra(Double precoMelhorOfertaCompra) {
		this.precoMelhorOfertaCompra = precoMelhorOfertaCompra;
	}
	public Double getPrecoMelhorOfertaVenda() {
		return precoMelhorOfertaVenda;
	}
	public void setPrecoMelhorOfertaVenda(Double precoMelhorOfertaVenda) {
		this.precoMelhorOfertaVenda = precoMelhorOfertaVenda;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public static LeitorDTO stringToLeitor(String str) throws Exception{
		if (str == null || str.isEmpty()){
			return null;
		}
		LeitorDTO ret = new LeitorDTO();
		
		ret.setTipoRegistro(str.substring(0,2));
		if (!"01".equals(ret.getTipoRegistro())){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		ret.setDiaPregao(Integer.valueOf(str.substring(8, 10)));
		ret.setMesPregao(Integer.valueOf(str.substring(6, 8)));
		ret.setAnoPregao(Integer.valueOf(str.substring(2, 6)));
		ret.setDataPregao(sdf.parse(str.substring(2,10)));
		ret.setCodigoNegociacao(str.substring(12,24));
		ret.setNomeResumido(str.substring(27,39));
		ret.setMoedaReferencia(str.substring(52,56));
		ret.setPrecoAbertura(Double.valueOf(str.substring(56,67)+"."+str.substring(67,69)));
		ret.setPrecoMaximo(Double.valueOf(str.substring(69,80)+"."+str.substring(80,82)));
		ret.setPrecoMinimo(Double.valueOf(str.substring(82,93)+"."+str.substring(93,95)));
		ret.setPrecoMedio(Double.valueOf(str.substring(95,106)+"."+str.substring(106,108)));
		ret.setPrecoUltimoNegocio(Double.valueOf(str.substring(108,119)+"."+str.substring(119,121)));
		ret.setPrecoMelhorOfertaCompra(Double.valueOf(str.substring(121,132)+"."+str.substring(132,134)));
		ret.setPrecoMelhorOfertaVenda(Double.valueOf(str.substring(134,145)+"."+str.substring(145,147)));
		ret.setVolume(Double.valueOf(str.substring(170,188)));
		return ret;
	}
	
}
