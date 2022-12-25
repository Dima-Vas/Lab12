package doctyping;

public class Main {
    public static void main(String[] args) {
        SmartDocument document = new SmartDocument("gs://lab12_oop/holy.jpg");
        TimedDocument timedDocument = new TimedDocument(document);
        CachedDocument cachedDocument = new CachedDocument(document);
        cachedDocument.connect("/home/dmytro/sqlite/cacheddocs.db");
        System.out.println(cachedDocument.parseUsingCache());
    }
}
