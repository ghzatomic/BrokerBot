package br.com.brokerbot.analizer.encog.prediction;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.encog.ml.data.market.TickerSymbol;

/**
 * Basic config info for the market prediction example.
 * 
 * @author jeff
 * 
 */
public class Config {
	
	public static String dataPrefix = "E:/Public/Bolsa/encogData/";
    public static String MARKET_NETWORK = dataPrefix+"market-network";
    public static String MARKET_NETWORK2 = dataPrefix+"market-network2";
    public static String MARKET_TRAIN = dataPrefix+"market-train";
    public static int TRAINING_MINUTES = 1; // 15
    public static int HIDDEN1_COUNT = 30;//30; // 20
    public static int HIDDEN2_COUNT = 10;//10; // 0

    public static int TRAIN_BEGIN_YEAR = 2011;
    public static int TRAIN_BEGIN_MONTH = 01;
    public static int TRAIN_BEGIN_DAY = 01;

    public static int TRAIN_END_YEAR = 2011;
    public static int TRAIN_END_MONTH = 12;
    public static int TRAIN_END_DAY = 30;

    public static int PREDICT_BEGIN_YEAR = 2012;
    public static int PREDICT_BEGIN_MONTH = 01;
    public static int PREDICT_BEGIN_DAY = 01;

    public static int PREDICT_END_YEAR = 2012;
    public static int PREDICT_END_MONTH = 02;
    public static int PREDICT_END_DAY = 30;
    
    public static int INPUT_WINDOW = 20;
    public static int PREDICT_WINDOW = 1;
    public static String TICKER_NAME = "GFSA3";
    public static TickerSymbol TICKER = new TickerSymbol(TICKER_NAME);
}