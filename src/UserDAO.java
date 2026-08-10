import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDAO {

    public static void saveUser(String name) {

        try {

            Connection con = DBConnection.getConnection();

            if(con==null){
                 return;
            }   

            String sql =
                    "INSERT INTO Users(name) VALUES(?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, name);

            ps.executeUpdate();

            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}