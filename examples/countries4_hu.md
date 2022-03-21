Feladatok
=========

Az alábbi feladatok megoldásához a JShellben hajtsuk végre az alábbi kódrészeket:
```java
import countries.*;
import static java.util.stream.Collectors.*;
var countries = new CountryRepository().getAll();
```
A továbbiakban a `countries` változó értéke `Country` objektumok egy listája, ezt kell használni a megoldásokban.

Írj _stream_ csővezetéket, mely:

1. Visszaadja a leghosszabb országnév fordítást.

1. Visszaadja a leghosszabb perzsa (fárszi) (azaz `"fa"` nyelvkódú) országnév fordítást.

1. A konzolra írja a leghosszabb országnév fordítást a nyelvkódjával együtt _nyelvkód_`=`_fordítás_ formában. Tipp: használd a [`Map.entrySet()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Map.html#entrySet()) metódust.

1. A konzolra írja az egyszavas (azaz szóköz karaktert nem tartalmazó) országneveket.

1. Visszaadja a legtöbb szóból áll országnevet. Tipp: használd a [`String.split()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html#split(java.lang.String)) metódust.

1. Visszaadja, hogy van-e legalább egy olyan főváros, melynek neve palindrom. Tipp: használd a [`StringBuilder.reverse()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StringBuilder.html#reverse()) metódust. 

1. Visszaadja a kisbetű-nagybetű érzéketlenül a legtöbb `'e'` karaktert tartalmazó országnevet. Tipp: deklarálj egy `int charCount(String s, char c)` segédmetódust, mely visszaadja az adott sztringben az adott karakter előfordulásainak számát.

1. Visszaadja a legtöbb angol magánhangzót (azaz `'a'`, `'e'`, `'i'`, `'o'`, `'u'` karaktert) tartalmazó fővárost. Tipp: deklarálj egy `int vowelCount(String s)` segédmetódust, mely visszaadja az adott sztringben az angol magánhangzók számát.

1.  Visszaad egy asszociatív tömböt, mely az országnevekben előforduló minden egyes karakterhez megadja az előfordulások számát, ahol a kisbetű és nagybetű karakterek azonosnak tekintendők. A visszatérési érték tehát `Map<Character, Long>` típusú kell, hogy legyen.

1. Visszaad egy asszociatív tömböt, mely minden egyes időzónához megadja az országok számát. A visszatérési érték tehát `Map<ZoneId, Long>` típusú kell, hogy legyen.

1. Visszaadja földrészenként azoknak az országoknak a számát, melyek neve kisbetű-nagybetű érzéketlen módon az országkódjukkal kezdődik. A visszatérési érték tehát `Map<Region, Long>` típusú kell, hogy legyen.

1. Visszaad egy, az átlagosnál nagyobb vagy egyenlő népsűrűségű országok és az átlagosnál kisebb népsűrűségű országok számát tartalmazó asszociatív tömböt. A visszatérési érték tehát `Map<Boolean, Long>` típusú kell, hogy legyen.

1. Visszaad egy asszociatív tömböt, mely minden egyes országkódhoz megadja az ország portugál (azaz `"pt"` nyelvkódú) nevét. A visszatérési érték tehát `Map<String, String>` típusú kell, hogy legyen. Tipp: használd a [`Collectors.toMap()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Collectors.html#toMap(java.util.function.Function,java.util.function.Function)) metódust.

1. Visszaadja földrészenként azoknak a fővárosoknak a listáját, melyek neve megegyezik az országuk nevével. A visszatérési érték tehát `Map<Region, List<String>>` típusú kell, hogy legyen. Tipp: használd a [`Collectors.groupingBy()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Collectors.html#groupingBy(java.util.function.Function,java.util.stream.Collector)), [`Collectors.filtering()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Collectors.html#filtering(java.util.function.Predicate,java.util.stream.Collector)) és [`Collectors.mapping()`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Collectors.html#mapping(java.util.function.Function,java.util.stream.Collector)) metódusokat.

1. Visszaad egy, az országnév-népsűrűség párokból álló asszociatív tömböt. A visszatérési érték tehát `Map<String, Double>` típusú kell, hogy legyen. Ha egy ország területe `null`, akkor a [`Double.NaN`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Double.html#NaN) speciális érték legyen a népsűrűsége.
