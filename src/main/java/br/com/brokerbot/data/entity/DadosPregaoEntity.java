package br.com.brokerbot.data.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class DadosPregaoEntity {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	public String tipoRegistro;
	public Integer diaPregao;
	public Integer mesPregao;
	public Integer anoPregao;
	public Date dataPregao;
	public String codigoNegociacao;
	public String nomeResumido;
	public String moedaReferencia;
	public Double precoAbertura;
	public Double precoMaximo;
	public Double precoMinimo;
	public Double precoMedio;
	public Double precoUltimoNegocio;
	public Double precoMelhorOfertaCompra;
	public Double precoMelhorOfertaVenda;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
