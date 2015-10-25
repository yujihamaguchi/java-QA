package hu2;

import hu2.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.math.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.awt.*;
import static java.util.stream.Collectors.*;

/*
 * This Java source file was auto generated by running 'gradle init --type java-library'
 * by 'root' at '6/13/15 6:15 AM' with Gradle 2.4
 *
 * @author root, @date 6/13/15 6:15 AM
 */
@SuppressWarnings("unchecked")
public class LibraryTest {

  // Set up
  Library sut = new Library();

  @Test public void test001() {
    assertEquals(
      33
      ,sut.getLongWordCount()
    );
  }
  @Test public void test002() {
    assertEquals(
      Arrays.asList('y', 'o', 'u', 'r', 'b', 'o', 'a', 't')
      ,sut.getFlatCharacterStream().collect(toList())
    );
  }
  @Test public void test003() {
    assertEquals(
      100
      ,sut.get100RandomNumber().count()
    );
  }
  @Test public void test004() {
    assertEquals(
      Arrays.asList(1,2,3,4)
      ,sut.drop1()
    );
  }
  @Test public void test005() {
    assertEquals(
      Arrays.asList('H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd')
      ,sut.concatStream().collect(toList())
    );
  }
  @Test public void test006() {
    assertEquals(
      "unenforceability"
      ,sut.getLongestWord()
    );
  }
  @Test public void test007() {
    assertEquals(
      "zip"
      ,sut.getLargestWord()
    );
  }
  @Test public void test008() {
    assertEquals(
      true
      ,sut.existsWordStartWithQ()
    );
  }
  @Test public void test009() {
    assertEquals(
      Optional.of(0.2)
      ,Library.inverse(5.0)
    );
    assertEquals(
      Optional.empty()
      ,Library.inverse(0.0)
    );
  }
  @Test public void test010() {
    assertEquals(
      Optional.of(2.0)
      ,Library.squareRoot(4.0)
    );
    assertEquals(
      Optional.empty()
      ,Library.squareRoot(-1.0)
    );
  }
  @Test public void test011() {
    assertEquals(
      Optional.of(0.5)
      ,Library.inverseAndSquareRoot(4.0)
    );
    assertEquals(
      Optional.empty()
      ,Library.inverseAndSquareRoot(-1.0)
    );
    assertEquals(
      Optional.empty()
      ,Library.inverseAndSquareRoot(0.0)
    );
  }
  @Test public void test012() {
    assertEquals(
      122988
      ,sut.sumWordLength()
    );
  }
  @Test public void test013() {
    assertEquals(
      4.042998027613412
      ,sut.getAverageWordLength()
      ,0.0
    );
  }
  @Test public void test014() {
    assertEquals(
      "Suzuki"
      ,sut.getIdToName().get(2)
    );
  }
  @Test public void test015() {
    assertEquals(
      new People(3, "Sato")
      ,sut.getIdToPeople().get(3)
    );
  }
  @Test public void test016() {
    assertEquals(
      "Deutsch"
      ,sut.getDefaultToLocaleSpecificLanguageName().get("German")
    );
  }
  @Test public void test017() {
    assertEquals(
      new HashSet<String>(Arrays.asList("Italian", "French", "German"))
      ,sut.getCountryToLanguages().get("Switzerland")
    );
  }
  @Test public void test018() {
    assertEquals(
      Arrays.asList(
        new Locale("fr", "CH")
        ,new Locale("de", "CH")
        ,new Locale("it", "CH"))
      ,sut.getSwissLocales()
    );
  }
  @Test public void test019() {
    assertEquals(
      Arrays.asList(
        new Locale("en", "US")
        ,new Locale("en", "SG")
        ,new Locale("en", "MT")
        ,new Locale("en")
        ,new Locale("en", "PH")
        ,new Locale("en", "NZ")
        ,new Locale("en", "ZA")
        ,new Locale("en", "AU")
        ,new Locale("en", "IE")
        ,new Locale("en", "CA")
        ,new Locale("en", "IN")
        ,new Locale("en", "GB"))
      ,sut.getEnglishLocales()
    );
  }
  @Test public void test020() {
    assertEquals(
      9733274.0
      ,sut.getStateToPopulationSummary().get("NY")
      ,0.0
    );
  }
  @Test public void test021() {
    assertEquals(
      "New York"
      ,sut.getStateToLargestCity().get("NY").get().getName()
    );
  }
  @Test public void test022() {
    assertEquals(
      "Niagara Falls"
      ,sut.getStateToLongestCityName().get("NY").get()
    );
  }
  @Test public void test023() {
    assertEquals(
      "New York,Buffalo,Rochester,Yonkers,Syracuse,Albany,New Rochelle,Mount Vernon,Schenectady,Utica,White Plains,Hempstead,Troy,Niagara Falls"
      ,sut.getStateToCityNamesCSV().get("NY")
    );
  }

  @Test public void test024() {
    Library sut = new Library();
    assertEquals(BigDecimal.valueOf(67.5), sut.getTotalOfDiscountedPrices());
  }
  @Test public void test025() {
    Library sut = new Library();
    assertEquals(Arrays.asList("BRIAN", "NATE", "NEAL", "RAJU", "SARA", "SCOTT"), sut.getUpperCaseFriendNames());
  }
  @Test public void test026() {
    Library sut = new Library();
    assertEquals(2, sut.countFriendsStartN());
    assertEquals(1, sut.countFriendsStartB());
  }
  @Test public void test027() {
    Library sut = new Library();
    assertEquals("Sara", sut.pickName("S"));
    assertEquals("Not found.", sut.pickName("X"));
  }
  @Test public void test028() {
    Library sut = new Library();
    assertEquals("Brian", sut.pickLongestName());
  }
  @Test public void test029() {
    Library sut = new Library();
    assertEquals("Brian,Nate,Neal,Raju,Sara,Scott", sut.getNamesAsCSV());
  }
  @Test public void test030() {
    Library sut = new Library();
    assertEquals(Arrays.asList('w', '0', '0', 't'), sut.string2CharList("w00t"));
    assertEquals(Arrays.asList('濱', '口'), sut.string2CharList("濱口"));
  }
  @Test public void test031() {
    Library sut = new Library();
    assertEquals("John,Jane,Sara,Greg", sut.sortPersonByAgeThenName());
  }
  @Test public void test032() {
    Library sut = new Library();
    assertEquals("{35=[Greg - 35], 20=[John - 20], 21=[Sara - 21, Jane - 21]}", sut.groupPersonByAge());
  }
  @Test public void test033() {
    Library sut = new Library();
    assertEquals("{35=[Greg], 20=[John], 21=[Sara, Jane]}", sut.groupPersonNameByAge());
  }
  @Test public void test034() {
    Library sut = new Library();
    assertEquals("{S=Optional[Sara - 21], G=Optional[Greg - 35], J=Optional[Jane - 21]}", sut.oldestPersonOfEachLetter());
  }
  @Test public void test035() {
    Library sut = new Library();
    assertEquals(Arrays.asList("foo.java", "bar.java"), sut.listSelectFiles());
  }
  @Test public void test036() {
    Library sut = new Library();
    assertEquals(Arrays.asList("foo.java", "baz.java", "bar.java"), sut.listFilesContainedDirAndChildDir());
  }
  @Test public void test037() {
    Library sut = new Library();
    assertEquals(10000, sut.totalAssetValues());
    assertEquals(10000, sut.totalAssetValues(asset -> true));
  }
  @Test public void test038() {
    Library sut = new Library();
    assertEquals(3000, sut.totalAssetValues(asset -> asset.getType() == Asset.AssetType.BOND));
    assertEquals(7000, sut.totalAssetValues(asset -> asset.getType() == Asset.AssetType.STOCK));
  }
  @Test public void test039() {
    CalculateNAV calculateNAV = new CalculateNAV(ticker -> new BigDecimal("6.01"));
    BigDecimal expected = new BigDecimal("6010.00");
    assertEquals(0, calculateNAV.computeStockWorth("GOOG", 1000).compareTo(expected));
    calculateNAV = new CalculateNAV(YahooFinance::getPrice);
    expected = new BigDecimal("6010.00");
    assertEquals(1, calculateNAV.computeStockWorth("GOOG", 100).compareTo(expected));
  }
  @Test public void test040() {
    Camera camera = new Camera();
    assertEquals(new Color(200, 100, 200), camera.capture(new Color(200, 100, 200)));
    camera.setFilters(Color::brighter);
    assertEquals(new Color(255, 142, 255), camera.capture(new Color(200, 100, 200)));
    camera.setFilters(Color::darker);
    assertEquals(new Color(140, 70, 140), camera.capture(new Color(200, 100, 200)));
    camera.setFilters(Color::brighter, Color::darker);
    assertEquals(new Color(200, 100, 200), camera.capture(new Color(200, 100, 200)));
  }
}
