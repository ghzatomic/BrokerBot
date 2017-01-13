package br.com.brokerbot.analizer.encog.prediction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.math3.analysis.function.Constant;
import org.encog.Encog;
import org.encog.ml.data.market.MarketDataDescription;
import org.encog.ml.data.market.MarketDataType;
import org.encog.ml.data.market.MarketMLDataSet;
import org.encog.ml.data.market.TickerSymbol;
import org.encog.ml.data.market.loader.LoadedMarketData;
import org.encog.ml.data.market.loader.MarketLoader;
import org.encog.ml.data.market.loader.YahooFinanceLoader;
import org.encog.neural.networks.BasicNetwork;
import org.encog.persist.EncogDirectoryPersistence;
import org.encog.util.simple.EncogUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.brokerbot.data.dto.LeitorDTO;
import br.com.brokerbot.data.entity.DadosPregaoEntity;
import br.com.brokerbot.data.repository.DadosPregaoDAO;

@RestController
public class CaioTreinamento {

	@Autowired
	private DadosPregaoDAO dadosPregaoDAO;
	
	@RequestMapping( method = {RequestMethod.GET}, value = "/treinar")
	public void importaArquivos(){
		
		Calendar begin = new GregorianCalendar(
                Config.TRAIN_BEGIN_YEAR,
                Config.TRAIN_BEGIN_MONTH-1,
                Config.TRAIN_BEGIN_DAY);

        Calendar end = new GregorianCalendar(
        		Config.TRAIN_END_YEAR,
        		Config.TRAIN_END_MONTH-1,
        		Config.TRAIN_END_DAY);
		
		List<DadosPregaoEntity> dados = dadosPregaoDAO.findAllByCodigoNegociacaoAndAnoPregaoBetweenAndMesPregaoBetween(Config.TICKER_NAME,
				Config.TRAIN_BEGIN_YEAR, 
				Config.TRAIN_END_YEAR, 
				Config.TRAIN_BEGIN_MONTH, 
				Config.TRAIN_END_MONTH);
		
		List<LoadedMarketData> dadosCarregadosMarket = new ArrayList<>();
		dados.forEach(dado->{
			LoadedMarketData data = new LoadedMarketData(new GregorianCalendar(dado.getAnoPregao(),dado.getMesPregao()-1,dado.getDiaPregao()).getTime(), new TickerSymbol(dado.getCodigoNegociacao().trim()));
			data.setData(MarketDataType.ADJUSTED_CLOSE, dado.getPrecoMedio());
            data.setData(MarketDataType.OPEN, dado.getPrecoAbertura());
            data.setData(MarketDataType.CLOSE, dado.getPrecoUltimoNegocio());
            data.setData(MarketDataType.HIGH, dado.getPrecoMaximo());
            data.setData(MarketDataType.LOW, dado.getPrecoMinimo());
            data.setData(MarketDataType.VOLUME, dado.getVolume());
            dadosCarregadosMarket.add(data);
		});

		
		MarketBuildTraining.generate(dadosCarregadosMarket,begin,end);
		MarketTrain.train();
		//MarketEvaluate.evaluate();
		//MarketPrune.incremental();
		
		Encog.getInstance().shutdown();
		
        
	}
	
}