package br.com.brokerbot.analizer.encog.prediction;
import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.encog.engine.network.activation.ActivationLinear;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.engine.network.activation.ActivationTANH;
import org.encog.ml.data.market.MarketDataDescription;
import org.encog.ml.data.market.MarketDataType;
import org.encog.ml.data.market.MarketMLDataSet;
import org.encog.ml.data.market.TickerSymbol;
import org.encog.ml.data.market.loader.LoadedMarketData;
import org.encog.ml.data.market.loader.MarketLoader;
import org.encog.ml.data.market.loader.YahooFinanceLoader;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.persist.EncogDirectoryPersistence;
import org.encog.util.simple.EncogUtility;

/**
 * Build the training data for the prediction and store it in an Encog file for
 * later training.
 * 
 * @author jeff
 * 
 */
public class MarketBuildTraining {

	
	
	public static void generate(List<LoadedMarketData> data,Calendar begin,Calendar end) {
		
		MarketLoader customLoader = new MarketLoader() {
			
			@Override
			public Collection<LoadedMarketData> load(TickerSymbol ticker, Set<MarketDataType> dataNeeded, Date from, Date to) {
				return data;
			}
		};
		
		final MarketMLDataSet market = new MarketMLDataSet(customLoader,
				Config.INPUT_WINDOW, Config.PREDICT_WINDOW);
		final MarketDataDescription desc = new MarketDataDescription(
				Config.TICKER, MarketDataType.ADJUSTED_CLOSE, true, true);
		market.addDescription(desc);

		/*Calendar begin = new GregorianCalendar(
                Config.TRAIN_BEGIN_YEAR,
                Config.TRAIN_BEGIN_MONTH-1,
                Config.TRAIN_BEGIN_DAY);

        Calendar end = new GregorianCalendar(
        		Config.TRAIN_END_YEAR,
        		Config.TRAIN_END_MONTH-1,
        		Config.TRAIN_END_DAY);*/
        market.load(begin.getTime(), end.getTime());
		market.generate();
		EncogUtility.saveEGB(new File(Config.MARKET_TRAIN), market);
 
		// create a network
		createNetwork(market);
	}

	private static void createNetwork(final MarketMLDataSet market) {

		BasicNetwork network = new BasicNetwork();
		network.addLayer(new BasicLayer(new ActivationLinear(), true,market.getInputSize()));// Input
		
		network.addLayer(new BasicLayer(new ActivationTANH(), true, Config.HIDDEN1_COUNT));
		network.addLayer(new BasicLayer(new ActivationTANH(), true, Config.HIDDEN2_COUNT));
		
		network.addLayer(new BasicLayer(new ActivationTANH(), false, market.getIdealSize()));// Output
		
		network.getStructure().finalizeStructure();
		network.reset();
		
		/*final BasicNetwork network2 = EncogUtility.simpleFeedForward(
				market.getInputSize(), 
				Config.HIDDEN1_COUNT, 
				Config.HIDDEN2_COUNT, 
				market.getIdealSize(), 
				true);
		network2.reset();*/
		// save the network and the training
		EncogDirectoryPersistence.saveObject(new File(Config.MARKET_NETWORK), network);
		//EncogDirectoryPersistence.saveObject(new File(Config.MARKET_NETWORK2), network2);
	}
}