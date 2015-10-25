package hu2;

import java.math.*;
import java.util.function.*;

public class CalculateNAV {

  private Function<String, BigDecimal> priceFinder;

  public CalculateNAV(final Function<String, BigDecimal> aPriceFinder) {
    priceFinder = aPriceFinder;
  }

  public BigDecimal computeStockWorth(final String ticker, final int shares) {
    return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
  }

}
