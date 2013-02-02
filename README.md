# Smart Collections

SmartCollections ist eine Java-Bibliothek, die die Arbeit mit der Java Collections API erleichtert. Sie enthält zu jedem Standard-Collection-Typ eine “smarte” Variante, die zusätzliche Funktionalität zur Verfügung stellt. Durch Funktions- und Prädikatsklassen werden die aus der funktionalen Programmierung bekannten High-Order-Funktionen nachgebildet. Dadurch können häufig auftretende Problemstellungen eleganter und weniger fehleranfällig gelöst werden.

## Motivation
Wieso noch eine Collections-API? Es gibt doch schon Apache Commons Collections und Google Guava (vorher Google Collections)? Mein Entschluss eine neue weitere Collection-API zu implementieren, hat im wesentlichen zwei Gründe:
* Die APIs von Google und Apache fügen neue Collection-Typen zu den vorhandenen hinzu. Das Ziel bei meinen SmartCollections ist ein anderes: Die Benutzung der Java-Standard-Collections soll vereinfacht werden. Es ist auch möglich SmartCollections zusammen mit anderen Collections, wie den Apache Commons, zu verwenden. Ich möchte nicht das Rad neu erfinden, sondern die alltägliche Arbeit mit den normalen Java-Collections vereinfachen!
Die SmartCollections erweitern die Standard Java Collection Typen um zusätzliche Funktionen, die das Schreiben von eleganterem Code ermöglichen. Für viele Programmierer ist es intuitiver, die Methode eines Objekts direkt zu verwenden, statt eine Utility-Klasse zu benötigen. Sowohl Apache Commons und Google Guava bieten ähnliche Operationen, jedoch nur über Utility-Klassen.
* Wichtiges Designziel bei der Umsetzung war: Die alten, gewohnten Java-Standard-Collections sollen weiterverwendet werden. Deshalb habe ich die SmartCollections als Decorators für die bestehenden Klassen implementiert. Das Ergebnis ist, dass es zu jeder Standard-Java-Collection eine SmartCollection-Variante gibt. Zu dieser kann jederzeit die dekorierte Standard-Collection über eine Methode abgefragt werden.

Einige Vorteile der SmartCollections sind:
* Durch Kapselung in Funktionsobjekte muss Code nur einmal geschrieben werden. Die Funktion wird über eine Methode auf jedes Element einer Collection angewendet. Fehler werden so unwahrscheinlicher. Iterationen auf die “alte” Art und Weise (for-Schleife) sind fehleranfällig und ähnlicher Code wiederholt sich häufig innerhalb eines Projekts.
* Die Art Code zu schreiben ändert sich dahingehend, dass Methoden noch weiter unterstrukturiert werden. Komplexe Logik verteilt sich auf kleine überschaubare Funktionsobjekte mit nur einer Methode, die nur genau eine definierte Funktion erfüllen.
* Viele Algorithmen können wesentlich prägnanter formuliert werden. Das liegt zum einen an der Nachbildung von High-Order-Functions und Closures. Zum anderen enthält die API eine generische Arithmetik-Klasse, mit deren Hilfe mathematische Operationen, wie Addition, Subtraktion, Multiplikation und Division auf den Elementen von Collections ausgeführt werden können. Wer schon einmal versucht hat in Java generisch Arithmetik zu programmieren, der weiß, dass dies nicht trivial ist!
* Dadurch, dass nur bestehende Collection-Typen erweitert wurden, fühlt man sich als Java-Entwickler sofort “zu Hause”. Theoretisch könnten in einem Projekt sämtliche Standard-Collection-Typen durch die entsprechenden SmartCollections ersetzt werden und der Code würde nach wie vor funktionieren. Es werden lediglich einige hilfreiche neue Methoden hinzugefügt.

## Tutorial
Hier werden die wesentlichen Bestandteile der API kurz erläutert. Alles weitere sollte den hier gezeigten Beispielen entsprechen und übertragbar sein. Als Demo habe ich eine kleine Musik-Verwaltung implementiert. In dieser können Künstler, Alben und Songs verwaltet werden. Ein Künstler hat einen Namen, ein Genre, eine Herkunft und beliebig viele Alben. Diese Alben haben ebenfalls einen Titel, außerdem ein Veröffentlichungsdatum, ein Format (CD, Vinyl, Mp3) und beinhalten beliebig viele Songs. Es existiert eine Klasse, die ein paar Verwaltungsfunktionen bereitstellt, und dabei Gebrauch von den SmartCollections macht. Das Beispielszenario wirkt zugegebenermaßen etwas künstlich, es sollte jedoch ausreichen, um die grundsätzliche Benutzung der Collections zu zeigen.
Grundsätzlich besteht die Möglichkeit, Funktionen oder Prädikate an Methoden zu übergeben. Dies ermöglicht einen Programmierstil, wie er in der funktionalen Programmierung üblich ist. Möchte man aus einer Liste von Künstlern nur diejenigen auswählen, die einem bestimmten Genre angehören, so sieht das folgendermaßen aus:

```java
public List<Artist> getAllArtistsOfGenre(final List<Artist> allArtists, final String genre) {
    SmartList<Artist> artists = new SmartArrayList<Artist>(allArtists);

    return artists.filter(new Predicate<Artist>() {

        @Override
        public boolean test(Artist input) {
            return input.getGenre().equals(genre);
        }
    }
}
```

Die übergeben Liste wird hier zunächst in eine SmartList umgewandelt. Danach wird eine filter-Operation durchgeführt. Das Prädikat wird als anonyme Klasse an die filter-Methode übergeben. Als Ergebnis wird eine neue Liste zurückgegeben, die nur noch die Künstler enthält, die dem Prädikat genügen, d.h. dem vorgegebenen Genre angehören.
Als nächstes sollen alle Alben eines vorgegebenen Genres ermittelt werden, die innerhalb des Testsystems bekannt sind. Dazu wird die reduce-Methode verwendet. Diese Methode bekommt einen Initialwert und eine binäre Funktion übergeben. Diese Funktion bekommt im ersten Schritt den Initialwert und das erste Element der Liste übergeben und erzeugt daraus ein Ergebnis. In allen weiteren Schritten wird das Ergebnis des vorhergehenden Schritts und das nächste Element der Collection übergeben. In funktionalen Programmiersprachen ist diese Operation häufig als foldLeft bekannt. Der Code ergibt sich folgendermaßen:

```java
public List<Album> getAllAlbumsOfGenre(final String genre) {
    SmartList<Artist> artists = new SmartArrayList<Artist>(getAllArtistsOfGenre(genre));

    return artists.reduce(new SmartLinkedList<Album>(), new BinaryFunction<SmartList<Album>, Artist>() {

        @Override
        public SmartList<Album> apply(SmartList<Album> input1, Artist input2) {
            return input1.addAllReturn(input2.getAlbums());
        }
    });
}
```

Da das Genre im Künstler gespeichert ist, wird hier die Methode getAllArtistsOfGenre aus dem ersten Beispiel verwendet. Zur Sicherheit wird eine neue SmartArrayList erzeugt (wir wissen zwar, dass die Methode getAllArtistsOfGenre eine SmartList zurückgibt und könnten einen expliziten Cast durchführen, durch die erneute Erzeugung der SmartArrayList sind wir jedoch auf der sicheren Seite, auch wenn jemand in Zukunft die Methode getAllArtistsOfGenre verändert). Die reduce-Methode bekommt eine leere Liste von Alben als Initialwert und eine binäre Funktion übergeben. Diese Funktion erwartet als Parameter eine Liste von Alben und einen Künstler. Der Rückgabetyp entspricht dem Typ des ersten Parameters (da der Rückgabewert eines Schrittes der Eingabewert des nächsten Schrittes ist). Innerhalb der Funktion werden alle Alben eines Künstlers ermittelt und in die übergebene Liste eingefügt. Als Ergebnis ergibt sich eine Liste mit allen Alben eines Genres.
Interessant ist hier außerdem die Methode addAllReturn. Sie entspricht der addAll-Methode, die alle Collections implementieren, mit dem Unterschied, dass sie nicht void, sondern die resultierende Collection zurückgibt. Dies ist häufig sehr praktisch. Es gibt in allen SmartCollections entsprechende Methoden zum Hinzufügen, Löschen und Einfügen von Elementen. Alle tragen den Namen der standardmäßig vorhandenen Methode und das Suffix “Return”.
Als nächstes sehen wir uns die map-Methode an. Sie bildet jedes Element einer Collection auf den Rückgabewert einer Funktion ab. Was zunächst abstrakt klingt, wird in der Realität sehr häufig benötigt. Im folgenden Beispiel soll eine Liste der Titel aller Alben ermittelt werden:

```java
public List<String> getAlbumTitles() {
    SmartList<Album> albums = new SmartLinkedList<Album>(dao.getAllAlbums());

    return albums.map(new UnaryFunction<String, Album>() {

        @Override
        public String apply(Album input) {
            return input.getTitle();
        }
    });
}
```

Wie bereits erwähnt, enthalten die SmartCollections eine Klasse für generische Arithmetik. Die Klassen Functions und Predicates enthalten einige Funktionen und Prädikate, die häufig verwendet werden. Darunter auch mathematische Operationen für beliebige numerische Typen. Die Verwendung wird im folgenden gezeigt. Wir möchten die Gesamtdauer eines Albums ermitteln. Dafür wird die Liste aller Songs auf eine Liste aller Song-Dauern abgebildet und anschließend eine reduce-Operation durchgeführt, um alle Werte der Liste zu addieren.

```java
public Integer getDurationOfAlbum(Album album) {
    return new SmartHashSet<Song>(album.getSongs()).map(new UnaryFunction<Integer, Song>() {

        @Override
        public Integer apply(Song input) {
            return input.getDurationInSeconds();
        }
    }).reduce(Functions.addFn());
}
```

Alle bisher gezeigten Operationen waren frei von Seiteneffekten. Es wurde stets nur mit dem Rückgabewert einer Funktion oder eines Prädikates gearbeitet. Ab und zu möchte man jedoch z.B. über eine Collection iterieren, dabei aber eine andere Collection verändern. Auch Maps fanden in den bisherigen Beispielen keine Verwendung. Beides werden wir nun einsetzen, um die Gesamtanzahl aller Alben der verschiedenen Genres zu zählen. Dazu wird eine SmartHashMap erstellt, die einem Genre die Anzahl der vorhandenen Alben zuordnet. Für die Zählung wird jeder vorhandene Künstler betrachtet, die Anzahl der Alben des Künstlers ermittelt und diese Anzahl in der Map zum jeweiligen Genre hinzuaddiert. Hier der Code dazu:

```java
public Map<String, Integer> countAlbumsOfGenres() {
    final SmartMap<String, Integer> result = new SmartHashMap<String, Integer>();
    SmartList<Artist> artists = new SmartArrayList<Artist>(dao.getAllArtists());

    artists.foreach(new VoidFunction<Artist>() {

        @Override
        public void apply(Artist input) {
            result.put(input.getGenre(), result.get(input.getGenre(), 0) + input.getAlbums().size());
        }
    });

    return result;
}
```

Die foreach-Methode entspricht grundsätzlich einer einfachen Iteration der Collection durch eine for-Schleife. Ihr wird eine VoidFunction übergeben, die auf jedes Element der Collection angewandt wird. Die Funktion gibt keinen Wert zurück, sondern erzeugt lediglich einen Seiteneffekt (hier das Addieren der Alben-Anzahlen in der Map). Die SmartMap bietet eine zusätzliche get-Funktion, die als zweiten Parameter einen Defaultwert übergeben bekommt. Dieser wird anstelle von null zurückgegeben, wenn es den angeforderten Schlüssel in der Map nicht gibt. Dadurch kann in vielen Fällen eine null-Prüfung entfallen. Hier wurde sie verwendet, um die Albenanzahl 0 als Wert zurückzugeben, falls ein Genre in der Ergebnis-Map noch nicht vorhanden ist.
Eine SmartCollection kann jederzeit wieder zu einer Standard-Java-Collection umgewandelt werden. Dafür gibt es eine Methode toStandardCollection. Die ist überall dort erforderlich, wo der reale Typ einer Collection tatsächlich dem Standard-Collection-Typ entsprechen muss. Dies ist z.B. der Fall, wenn ein Feld einer Klasse mit Hibernate persistiert werden soll. In allen anderen Fällen ist keine explizite Umwandlung notwendig, da die SmartCollections die gleichen Interfaces wie die Standard-Collections implementieren und damit kompatibel sind. Dies wird im Folgenden nochmal gezeigt:

```java
SmartList<Artist> artists = new SmartArrayList<Artist>(dao.getAllArtists());

// SmartList ist kompatibel zu List
List<Artist> artists2 = artists;

// Explizite Umwandlung in Standard-List
List<Artist> artists3 = artists.toStandardCollection();
```

Dies sollte als Einstieg reichen. Ansonsten empfehle ich: Ausprobieren, im Zweifel in die API-Dokumentation schauen.

## Lizenz
Copyright (c) Stefan Münchow. All rights reserved. 

The use and distribution terms for this software are covered by the
Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
which can be found in the file epl-v10.html at the root of this distribution.
By using this software in any fashion, you are agreeing to be bound by
the terms of this license.
You must not remove this notice, or any other, from this software.