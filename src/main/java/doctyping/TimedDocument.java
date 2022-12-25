package doctyping;


public class TimedDocument{
    private final Document curratedDoc;
    public TimedDocument(Document toTime) {
        curratedDoc = toTime;
    }
    public long getTime() {
        long before = System.currentTimeMillis();
        curratedDoc.parse();
        return System.currentTimeMillis() - before;
    }
}
