import java.sql.Connection;
import java.sql.PreparedStatement;

public class ScoreDAO {

    public static void saveScore(String name, String topic, int score, int total) {

        int percentage=0;

        if(total!=0){
            percentage=(score*100)/total;
        }

        String result;

        if (percentage >= 50)
            result = "PASS";
        else
            result = "FAIL";

        try {

            Connection con = DBConnection.getConnection();
            if(con==null)
                return;

            String sql = "INSERT INTO Scores(name, topic, score, total_questions, percentage, result) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, topic);
            ps.setInt(3, score);
            ps.setInt(4, total);
            ps.setInt(5, percentage);
            ps.setString(6, result);

            ps.executeUpdate();

            System.out.println("Score Saved Successfully!");

            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}