package doctyping;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class CachedDocument {
    private final SmartDocument curratedDoc;
    Connection co;

    public CachedDocument(SmartDocument curratedDoc) {
        this.curratedDoc = curratedDoc;
    }

    @SneakyThrows
    public void connect(String dbURL) {
        try {
            Class.forName("org.sqlite.JDBC");
            co = DriverManager.getConnection(
                    "jdbc:sqlite:"+dbURL
            );
            System.out.println("Connected!");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String parseUsingCache() {
        return select();
    }

    @SneakyThrows
    public void insert() {
        String query = "INSERT INTO DOCS (url, content) VALUES ('" +
                curratedDoc.gcsPath + "', '" + curratedDoc.parse() + "')";
        Statement statement = co.createStatement();
        statement.executeUpdate(query);
        System.out.println("Inserted " + curratedDoc.gcsPath + " to cache.");
    }

    @SneakyThrows
    public String select() {
        String query = "SELECT url, content FROM DOCS WHERE url = '" + curratedDoc.gcsPath + "'";
        Statement statement = co.createStatement();
        ResultSet rs = statement.executeQuery(query);
        String output = "";
        while (rs.next()) {
            output = rs.getString("content");
        }
        if (output == "") {
            insert();
            rs.close();
            statement.close();
            return select();
        }
        else {
            System.out.println("Selected " + curratedDoc.gcsPath + " from cache.");
            return output;
        }
    }

}
