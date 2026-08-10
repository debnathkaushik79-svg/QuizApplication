import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ResultGUI extends JFrame implements ActionListener {

    JButton restartButton;
    JButton exitButton;

    public ResultGUI(String name, String topic, int score, int totalQuestions) {

        setTitle("Quiz Result");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("QUIZ COMPLETED");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(130, 20, 250, 40);
        add(title);

        int percentage = (score * 100) / totalQuestions;

        String result = percentage >= 50 ? "PASS 🎉" : "FAIL";

        JLabel nameLabel = new JLabel("Name : " + name);
        nameLabel.setBounds(80, 90, 300, 30);
        add(nameLabel);

        JLabel topicLabel = new JLabel("Topic : " + topic.toUpperCase());
        topicLabel.setBounds(80, 130, 300, 30);
        add(topicLabel);

        JLabel scoreLabel = new JLabel("Score : " + score + " / " + totalQuestions);
        scoreLabel.setBounds(80, 170, 300, 30);
        add(scoreLabel);

        JLabel percentageLabel = new JLabel("Percentage : " + percentage + "%");
        percentageLabel.setBounds(80, 210, 300, 30);
        add(percentageLabel);

        JLabel resultLabel = new JLabel("Result : " + result);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setBounds(80, 250, 300, 30);
        add(resultLabel);

        restartButton = new JButton("Restart");
        restartButton.setBounds(80, 310, 120, 40);
        restartButton.addActionListener(this);
        add(restartButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(250, 310, 120, 40);
        exitButton.addActionListener(this);
        add(exitButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == restartButton) {

            dispose();
            new WelcomeScreen();

        } else {

            System.exit(0);

        }
    }
}