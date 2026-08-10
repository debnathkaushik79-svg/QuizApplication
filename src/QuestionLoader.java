import java.sql.*;
import java.util.*;

public class QuestionLoader {

    public static ArrayList<Question> loadQuestions(String topic) {

        ArrayList<Question> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();
            if(con==null)
                return list;

            String sql =
            "SELECT * FROM Questions WHERE topic=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, topic);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Question q = new Question(

                        rs.getString("question"),

                        rs.getString("option1"),

                        rs.getString("option2"),

                        rs.getString("option3"),

                        rs.getString("option4"),

                        rs.getString("answer")

                );

                list.add(q);

            }

            con.close();

        }

        catch(Exception e) {

            e.printStackTrace();

        }

        return list;
    }
}