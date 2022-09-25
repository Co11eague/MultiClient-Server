import java.sql.*;

public class Database {
    private static Connection connection = null;

    //This method executes a query and returns the number of albums for the artist with name artistName
    public int getTitles(String artistName) {
        int titleNum = 0;
        String query = "SELECT COUNT(artist.name) FROM album INNER JOIN artist ON album.artistid = artist.artistid WHERE name = ?";
        //Implement this method
        //Prevent SQL injections!
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.setString(1, artistName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                titleNum = rs.getInt("count");
            return titleNum;

        } catch (SQLException e) {
            return titleNum;

        }

    }

    // This method establishes a DB connection & returns a boolean status
    public boolean establishDBConnection() {

        try {
            connection = DriverManager
                    .getConnection(Credentials.URL, Credentials.USERNAME, Credentials.PASSWORD);
            return connection.isValid(5);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}