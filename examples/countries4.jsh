import countries.*;
import java.time.ZoneId;
import static java.util.stream.Collectors.*;

var countries = new CountryRepository().getAll();

// 1. Returns the longest country name translation:

countries.stream().
  flatMap(country -> country.getTranslations().values().stream()).
  max(Comparator.comparingInt(String::length));

// 2. Returns the longest Farsi (i.e., `"fa"`) country name translation:

countries.stream().
  map(country -> country.getTranslations().get("fa")).
  filter(Objects::nonNull).
  max(Comparator.comparingInt(String::length));

// 3. Prints the longest country name translation together with its language code in the form language=translation:

countries.stream().
  flatMap(country -> country.getTranslations().entrySet().stream()).
  max(Comparator.comparingInt(entry -> entry.getValue().length())).
  ifPresent(System.out::println);

// 4. Prints single word country names (i.e., country names that do not contain any space characters):

countries.stream().
  map(Country::getName).
  filter(s -> s.matches("^[^\\s]+$")).
  sorted().
  forEach(System.out::println);

// 5. Returns the country name with the most number of words:

countries.stream().
  map(Country::getName).
  max(Comparator.comparingInt(s -> s.split("[\\s\\p{Punct}]+").length));

// 6. Returns whether there exists at least one capital that is a palindrome: 

countries.stream().
  map(Country::getCapital).
  filter(Objects::nonNull).
  anyMatch(capital -> capital.toLowerCase().equals(new StringBuilder(capital.toLowerCase()).reverse().toString()));

// 7. Returns the country name with the most number of `'e'` characters ignoring case:

int charCount(String s, char c) {
  int count = 0;
  for (int i = 0; i < s.length(); i++) {
    if (s.charAt(i) == c) {
      count++;
    }
  }
  return count;
}

countries.stream().
  map(Country::getName).
  max(Comparator.comparingInt(s -> charCount(s.toLowerCase(), 'e')));

// 8. Returns the capital with the most number of English vowels (i.e., `'a'`, `'e'`, `'i'`, `'o'`, `'u'`):

int vowelCount(String s) {
  s = s.toLowerCase();
  int count = 0;
  for (int i = 0; i < s.length(); i++) {
    switch(s.charAt(i)) {
      case 'a':
      case 'e':
      case 'i':
      case 'o':
      case 'u':
        count++;
    }
  }
  return count;
}

countries.stream().
  map(Country::getCapital).
  filter(Objects::nonNull).
  max(Comparator.comparingInt(s -> vowelCount(s)));

// 9.  Returns a map that contains for each character the number of occurrences in country names ignoring case:

countries.stream().
  map(Country::getName).
  map(String::toLowerCase).
  flatMapToInt(s -> s.chars()).
  mapToObj(codePoint -> (char) codePoint).
  collect(groupingBy(Function.identity(), counting()));

// 10. Returns a map that contains the number of countries for each possible timezone:

countries.stream().
  flatMap(country -> country.getTimezones().stream()).
  collect(groupingBy(Function.identity(), counting()));

// 11. Returns the number of country names by region that starts with their two-letter country code ignoring case:

countries.stream().
  filter(country -> country.getName().toLowerCase().startsWith(country.getCode().toLowerCase())).
  forEach(country -> System.out.printf("%s: %s\n", country.getCode(), country.getName()));

countries.stream().collect(groupingBy(Country::getRegion, filtering(country -> country.getName().toLowerCase().startsWith(country.getCode().toLowerCase()), counting())));

// 12. Returns a map that contains the number of countries whose population is greater or equal than the population average versus the the number of number of countries with population below the average:

double avgPopulation = countries.stream().
  mapToDouble(Country::getPopulation).
  average().
  getAsDouble();

countries.stream().collect(partitioningBy(country -> country.getPopulation() >= avgPopulation, counting()));

// 13. Returns a map that contains for each country code the name of the corresponding country in Portuguese ("pt"):

countries.stream().collect(toMap(Country::getCode, country -> country.getTranslations().get("pt")));

// 14. Returns the list of capitals by region whose name is the same is the same as the name of their country:

countries.stream().
  filter(country -> country.getName().equals(country.getCapital())).
  map(Country::getName).
  forEach(System.out::println);

countries.stream().collect(groupingBy(Country::getRegion, filtering(country -> country.getName().equals(country.getCapital()), mapping(Country::getName, toList()))));

// 15. Returns a map of country name-population density pairs:

countries.stream().collect(toMap(Country::getName, country -> country.getArea() == null ? Double.NaN : country.getPopulation() / country.getArea().doubleValue()));
