import java.sql.*;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/QuizApplication";

    private static final String USER = "root";

    private static final String PASSWORD = "kaushik#@27";

    public static Connection getConnection() {

        try {

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {

            e.printStackTrace();

            return null;
        }
    }
}