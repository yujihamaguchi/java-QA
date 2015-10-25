package hu2;

import java.io.*;
import java.math.*;
import java.net.*;

public class YahooFinance {
  public static BigDecimal getPrice(final String ticker) {
    try {
      final URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker);
      final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
      final String data = reader.lines().skip(1).findFirst().get();
      final String[] dataItems = data.split(",");
      return new BigDecimal(dataItems[dataItems.length - 1]);
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  }
}
