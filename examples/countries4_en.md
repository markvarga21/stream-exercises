Assignments
===========

Execute the following code snippets in JShell to be able to work with countries:
```java
import countries.*;
import static java.util.stream.Collectors.*;
var countries = new CountryRepository().getAll();
```
In the following, the `countries` variable holds a list of `Country` objects, you must work with this list in the solutions.

Write a stream pipeline that:

1. Returns the longest country name translation.

1. Returns the longest Farsi (i.e., `"fa"`) country name translation.

1. Prints the longest country name translation together with its language code in the form _language_`=`_translation_. Hint: use [`Map.entrySet()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Map.html#entrySet()).

1. Prints single word country names (i.e., country names that do not contain any space characters).

1. Returns the country name with the most number of words. Hint: use [`String.split()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#split(java.lang.String)).

1. Returns whether there exists at least one capital whose name is a palindrome string. Hint: use [`StringBuilder.reverse()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StringBuilder.html#reverse()). 

1. Returns the country name with the most number of `'e'` characters ignoring case. Hint: declare a helper function `int charCount(String s, char c)` to count the number of occurrences of a character in a string.

1. Returns the capital with the most number of English vowels (i.e., `'a'`, `'e'`, `'i'`, `'o'`, `'u'`). Hint: declare a helper function `int vowelCount(String s)` to count the number of English vowels in a string.

1.  Returns a map that contains for each character the number of occurrences in country names ignoring case. Thus, the return value must be of type `Map<Character, Long>`.

1. Returns a map that contains the number of countries for each possible timezone. Thus, the return value must be of type `Map<ZoneId, Long>`.

1. Returns the number of country names by region that starts with their two-letter country code ignoring case. Thus, the return value must be of type `Map<Region, Long>`.

1. Returns a map that contains the number of countries whose population is greater or equal than the population average versus the number of number of countries with population below the average. Thus, the return value must be of type `Map<Boolean, Long>`.

1. Returns a map that contains for each country code the name of the corresponding country in Portuguese (`"pt"`). Thus, the return value must be of type `Map<String, String>`. Hint: use [`Collectors.toMap()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Collectors.html#toMap(java.util.function.Function,java.util.function.Function)).

1. Returns the list of capitals by region whose name is the same as the name of their country. Thus, the return value must be of type `Map<Region, List<String>>`. Hint: use [`Collectors.groupingBy()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Collectors.html#groupingBy(java.util.function.Function,java.util.stream.Collector)), [`Collectors.filtering()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Collectors.html#filtering(java.util.function.Predicate,java.util.stream.Collector)), and [`Collectors.mapping()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Collectors.html#mapping(java.util.function.Function,java.util.stream.Collector)).

1. Returns a map of country name-population density pairs. Thus, the return value must be of type `Map<String, Double>`. If an area is `null`, population density must be the special value [`Double.NaN`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Double.html#NaN).
