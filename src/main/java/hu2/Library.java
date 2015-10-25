package hu2;

import hu2.*;
import java.io.*;
import java.math.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.util.regex.*;
import java.nio.file.*;
import java.nio.charset.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

@SuppressWarnings("unchecked")
public class Library {
  // Q001: Write a function named "getLongWordCount" using stream API which is same function below.
  // int count = 0;
  // for (String w : words) {
  //   if (w.length() > 12) count++;
  // }
  // return count;
  private static String getContents() {
    String contents = null;
    try{
      contents = new String(
        Files.readAllBytes(Paths.get("/root/training/java/java-QA/src/main/resources/alice.txt"))
        ,StandardCharsets.UTF_8);
    } catch (Exception e) {
    }
    return contents;
  }
//  List<String> words = Arrays.asList(getContents().split("[\\P{L}]+"));
  List<String> words = Pattern.compile("[\\P{L}]+").splitAsStream(getContents()).collect(toList());
  public long getLongWordCount() {
//    return words.stream().filter(w -> w.length() > 12).count();
//    return words.parallelStream().filter(w -> w.length() > 12).count(); // parallel
    return words.stream().parallel().filter(w -> w.length() > 12).count(); // parallel
  }

  // Q002: Write a function named "getFlatCharacterStream" which get flat character stream from word list.
  List<String> wordList = Arrays.asList("your", "boat");
  public static Stream<Character> characterStream(String s) {
    List<Character> result = new ArrayList<>();
    for (char c : s.toCharArray())
      result.add(c);
    return result.stream();
  }

  public Stream<Character> getFlatCharacterStream() {
    return wordList.stream().flatMap(w -> characterStream(w));
  }

  // Q003: Write a function named "get100RandomNumber" which generate infinite random number and get 100 number from those.
  public Stream<Double> get100RandomNumber() {
    return Stream.generate(Math::random).limit(100);
  }

  // Q004: Write a function named "drop1" which drop 1 attribute from list "list0To4" and get list [1,2,3,4].
  List<Integer> list0To4 = Arrays.asList(0, 1, 2, 3, 4);

  public List<Integer> drop1() {
    return list0To4.stream().skip(1).collect(toList());
  }

  // Q005: Write a function named "concatStream" which concat 2 streams.
  Stream<Character> sc1 = characterStream("Hello");
  Stream<Character> sc2 = characterStream("World");

  public Stream<Character> concatStream() {
    return Stream.concat(sc1, sc2);
  }

  // Q006: Write a function named "getLongestWord" which get longest word from words in "alice.txt" using "sorted" and "findFirst" methods (if not present, return "No result").
  public String getLongestWord() {
    return words.stream()
                .sorted(Comparator.comparing(String::length).reversed())
                .findFirst()
                .orElse("No result");
  }

  // Q007: Write a function named "getLargestWord" which get largest word(ignore case) from words in "alice.txt" using "Optional" type as intermediate variable and "max" and "isPresent" methods.
  public String getLargestWord() {
    Optional<String> largest = words.stream().max(String::compareToIgnoreCase);
    return largest.isPresent() ? largest.get() : "No result";
  }

  // Q008: Write a function named "existsWordStartWithQ" that verify the existance of a word which starts with "Q".
  public boolean existsWordStartWithQ() {
    return words.stream()
                .parallel()
                .anyMatch(w -> w.startsWith("Q"));
  }

  // Q009: Write a static function named "inverse" which get inverse of Double type parameter.
  public static Optional<Double> inverse(Double x) {
    return x == 0 ? Optional.empty() : Optional.of(1 / x);
  }

  // Q010: Write a static function named "squareRoot" which get square root of parameter.
  public static Optional<Double> squareRoot(Double x) {
    return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
  }

  // Q011: Write a static function named "inverseAndSquareRoot" which call functions "inverse" and "squareRoot" in order.
  public static Optional<Double> inverseAndSquareRoot(Double x) {
    //return inverse(x).flatMap(y -> squareRoot(y));
    return Optional.of(x).flatMap(Library::inverse).flatMap(Library::squareRoot);
  }

  // Q012: Write a function named "sumWordLength" which sum word's length in "alice.txt".
  //       *Write 2 petterns: 1. boxing(using reduce method with parameter(supplier(identity), accumulator, combiner))
  //                          2. not boxing
  public int sumWordLength() {
    //return words.stream().reduce(0, (total, word) -> total + word.length(), (total1, total2) -> total1 + total2);
    return words.stream().mapToInt(String::length).sum(); // not boxing
  }

  // Q013: Write a function named "getAverageWordLength" which average word length in "alice.txt".
  public double getAverageWordLength() {
    return words.stream().collect(Collectors.summarizingDouble(String::length)).getAverage();
  }

  // Q014: Write a function named "getIdToName" which gets map(id -> name) from peoples(list).
  List<People> peoples = Arrays.asList(
    new People(1, "Tanaka")
    ,new People(2, "Suzuki")
    ,new People(3, "Sato"));

  public Map<Integer, String> getIdToName() {
    return peoples.stream().collect(toMap(People::getId, People::getName));
  }

  // Q015: Write a function named "getIdToPeople" which gets map(id -> people's instance) from peoples(list).
  public Map<Integer, People> getIdToPeople() {
    return peoples.stream().collect(toMap(People::getId, Function.identity()));
  }

  // Q016: Write a function named "getDefaultToLocaleSpecificLanguageName" which gets map(default display language name -> locale specific display language name) from locales(list).
  Locale[] locales = Locale.getAvailableLocales();

  public Map<String, String> getDefaultToLocaleSpecificLanguageName() {
    return Stream.of(locales).collect(Collectors.toMap(
                                                  l -> l.getDisplayLanguage()
                                                  ,l -> l.getDisplayLanguage(l)
                                                  ,(existingMap, newMap) -> existingMap));
  }
                                       
  // Q017: Write a function named "getCountryToLanguages" which gets map(display country name -> display languages(set)) from locales(list). (using downstream collector)
  public Map<String, Set<String>> getCountryToLanguages() {
    return Stream.of(locales).collect(
                               Collectors.groupingBy(
                                 l -> l.getDisplayCountry()
                                 ,Collectors.mapping(l -> l.getDisplayLanguage(), Collectors.toSet())));
  }

  // Q018: Write a function named "getSwissLocales" which gets locale list by swiss's country code "CH".
  public List<Locale> getSwissLocales() {
    return Stream.of(locales).collect(Collectors.groupingBy(Locale::getCountry)).get("CH");
  }

  // Q019: Write a function named "getEnglishLocales" which gets locale list by english lauguage code "en".
  public List<Locale> getEnglishLocales() {
    return Stream.of(locales).collect(partitioningBy(l -> l.getLanguage().equals("en"))).get(true);
  }
//  public List<Locale> getEnglishLocales() {
//    return Stream.of(locales).collect(Collectors.partitioningBy(l -> l.getLanguage().equals("en"))).get(true);
//  }

  // Q020: Write a function named "getStateToPopulationSummary" which gets map(state -> city population summary) from "cities.txt".
  public static Stream<City> readCities() {
    Stream<City> result = Stream.empty();
      try {
        result = Files.lines(Paths.get("/root/training/java/java-QA/src/main/resources/cities.txt"))
                      .map(l -> l.split(", "))
                      .map(a -> new City(a[0], a[1], Integer.parseInt(a[2]))); 
      } catch (IOException e) {
      }
      return result;
  }

  final Stream<City> cities = readCities();

  public Map<String, Integer> getStateToPopulationSummary() {
    return cities.collect(groupingBy(City::getState, summingInt(City::getPopulation)));
  }

  // Q021: Write a function named "getStateToLargestCity" which gets map(state -> a city which has largest population) from "cities.txt".
  public Map<String, Optional<City>> getStateToLargestCity() {
    return cities.collect(groupingBy(City::getState, maxBy(comparing(City::getPopulation))));
  }

  // Q022: Write a function named "getStateToLongestCityName" which gets map(state -> a city which has longest city name) from "cities.txt".
  public Map<String, Optional<String>> getStateToLongestCityName() {
    return cities.collect(groupingBy(City::getState, mapping(City::getName, maxBy(comparing(String::length)))));
  }

  // Q023: Write a function named "getStateToCityNamesCSV" which gets map(state -> city names(csv)) from "cities.txt".
  public Map<String, String> getStateToCityNamesCSV() {
    return cities.collect(groupingBy(City::getState, mapping(City::getName, joining(","))));
  }
  
  // Q024: Write a function nemed "getTotalOfDiscountedPrices" that sum only discounted(-10%) prices if the price is higher than $20.

  final List<BigDecimal> prices = Arrays.asList(
    new BigDecimal("10")
    ,new BigDecimal("30")
    ,new BigDecimal("17")
    ,new BigDecimal("20")
    ,new BigDecimal("15")
    ,new BigDecimal("18")
    ,new BigDecimal("45")
    ,new BigDecimal("12"));

  public BigDecimal getTotalOfDiscountedPrices() {
    return prices.stream()
                 .filter(price -> price.compareTo(BigDecimal.valueOf(20)) > 0)
                 .map(price -> price.multiply(BigDecimal.valueOf(0.9)))
                 .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  // Q025: Write a function named "getUpperCaseFriendNames" that convert friendi's names to uppercase.

  final List<String> friends = Arrays.asList(
    "Brian"
    ,"Nate"
    ,"Neal"
    ,"Raju"
    ,"Sara"
    ,"Scott");

  public List<String> getUpperCaseFriendNames() {
    return friends.stream()
                  .map(String::toUpperCase)
                  .collect(toList());
  }

  // Q026: Write functions named "countFriendsStartN"/"countFriendsStartB"
  //       that filter weather or not starts with a indicated character
  //       and return filtered element's count.
  //       Before that, define the variable named "startsWithLetter"
  //       which is lambda function that receives letter and name(friend) as parameter
  //       and return predicate function which the name starts with the letter.
  final Function<String, Predicate<String>> startsWithLetter = letter -> name -> name.startsWith(letter);
  public long countFriendsStartN() {
    return friends.stream().filter(startsWithLetter.apply("N")).count();
  }
  public long countFriendsStartB() {
    return friends.stream().filter(startsWithLetter.apply("B")).count();
  }

  // Q027: Write a function named "pickName" that find a friend's name which starts a indicated letter.
  //       (if no name is matched, return "Not found.")
  public String pickName(String letter) {
    return friends.stream()
                  .filter(startsWithLetter.apply(letter))
                  .findFirst()
                  .orElse("Not found.");
  }

  // Q028: Write a function named "pickLongestName" that pick a longest name. (default name is "Bob")
//  public String pickLongestName() {
//    return friends.stream().reduce("Bob", (name1, name2) -> name1.length() >= name2.length() ? name1 : name2);
//  }
public String pickLongestName() {
  return friends.stream().reduce("Bob", (name1, name2) -> name1.length() >= name2.length() ? name1 : name2);
}

  // Q029: Write a function named "getNamesAsCSV" that return CSV string(quote is not neccesary).
  public String getNamesAsCSV() {
    return friends.stream().collect(joining(","));
  }

  // Q030: Write a function named "string2CharList" that convert string to character list.
public List<Character> string2CharList(String s) {
  return s.chars().mapToObj(c -> Character.valueOf((char) c)).collect(toList());
}

  // Q031: Write a function named "sortPersonByAgeThenName" that sort persons by age then name and return names as csv.

  final List<Person> persons = Arrays.asList(
    new Person("John", 20)
    ,new Person("Sara", 21)
    ,new Person("Jane", 21)
    ,new Person("Greg", 35));

  final Function<Person, Integer> byAge = person -> person.getAge();
  final Function<Person, String> byName = person -> person.getName();

  public String sortPersonByAgeThenName() {
    return persons.stream()
                  .sorted(comparing(byAge).thenComparing(byName))
                  .map(byName)
                  .collect(joining(","));    
  }


  // Q032: Write a function named "groupPersonByAge" that group persons by age and return as String.
  public String groupPersonByAge() {
    return persons.stream()
                  .collect(groupingBy(Person::getAge))
                  .toString();
  }

  // Q033: Write a function named "groupPersonNameByAge" that group person's name by age and return as String.
//  public String groupPersonNameByAge() {
//    return persons.stream()
//                  .collect(groupingBy(Person::getAge, mapping(Person::getName, toList())))
//                  .toString();
//  }
public String groupPersonNameByAge() {
  return persons.stream().collect(groupingBy(Person::getAge, mapping(Person::getName, toList()))).toString();
}

  // Q034: Write a function named "oldestPersonOfEachLetter" that group person's capital letter and extract max aged person.
  //       expected: "{S=Optional[Sara - 21], G=Optional[Greg - 35], J=Optional[Jane - 21]}"
  public String oldestPersonOfEachLetter() {
    return persons.stream()
                  .collect(groupingBy(person -> person.getName().charAt(0)
                                      ,reducing(BinaryOperator.maxBy(comparing(Person::getAge)))))
                  .toString();
  }
//public String oldestPersonOfEachLetter() {
//  return persons.stream()
//                .collect(
//                  groupingBy(p -> p.getName().charAt(0),
//                             reducing((p1, p2) -> p1.getAge() >= p2.getAge() ? p1 : p2)
//                            )
//                ).toString();
//}

//  Comparator<Person> byAge2 = Comparator.comparing(Person::getAge);
//  public String oldestPersonOfEachLetter() {
//    return persons.stream()
//                  .collect(groupingBy(person -> person.getName().charAt(0)
//                                      ,reducing(BinaryOperator.maxBy(byAge2))))
//                  .toString();
//  }

  // Q035: Write a function named "listSelectFiles" that list java source file names in the directory "/root/training/java/java-QA/src/main/resources".
  public List<String> listSelectFiles() {
    return Stream.of(new File("/root/training/java/java-QA/src/main/resources").listFiles())
                 .filter(file -> file.getName().endsWith(".java"))
                 .map(file -> file.getName())
                 .collect(toList());
  }

  // Q036: Write a function named "listFilesContainedDirAndChildDir" that list files in the directory "/root/training/java/java-QA/src/main/resources" and the child directory also.
  public List<String> listFilesContainedDirAndChildDir() {
    return Stream.of(new File("/root/training/java/java-QA/src/main/resources").listFiles())
                 .flatMap(file -> file.isDirectory() ? Stream.of(file.listFiles()) : Stream.of(file))
                 .filter(file -> file.getName().endsWith(".java"))
                 .map(file -> file.getName())
                 .collect(toList());
  }

  // Q037: Write a function named "totalAssetValues" that get total of asset's value.
  final List<Asset> assets = Arrays.asList(
    new Asset(Asset.AssetType.BOND, 1000)
    ,new Asset(Asset.AssetType.BOND, 2000)
    ,new Asset(Asset.AssetType.STOCK, 3000)
    ,new Asset(Asset.AssetType.STOCK, 4000));

  public int totalAssetValues() {
    return assets.stream()
                 .mapToInt(Asset::getValue)
                 .sum();
  }

  // Q038: Write a function named "totalAssetValues" that get total of indicated asset type's value.
  // ex)
  // totalAssetValues(asset -> asset.getType() == Asset.AssetType.BOND)
  public int totalAssetValues(Predicate<Asset> assetSelector) {
    return assets.stream()
                 .filter(assetSelector)
                 .mapToInt(Asset::getValue)
                 .sum();
  }
  // Q039: Read classes related "CalculateNAV".(Delegation Pettern Example)
  // Q040: Read a class named "Camera".(Decoration Pettern Example)
}
