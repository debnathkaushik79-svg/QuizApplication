import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomeScreen extends JFrame implements ActionListener {

    JLabel titleLabel;
    JLabel nameLabel;
    JTextField nameField;

    JRadioButton javaButton;
    JRadioButton dbmsButton;
    JRadioButton dsaButton;
    JRadioButton cnButton;
    JRadioButton osButton;

    ButtonGroup group;

    JButton startButton;

    public WelcomeScreen() {

        setTitle("Java Quiz Application");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        // Title
        titleLabel = new JLabel("JAVA QUIZ APPLICATION");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(120, 20, 400, 40);
        add(titleLabel);

        // Name
        nameLabel = new JLabel("Enter Your Name:");
        nameLabel.setBounds(70, 90, 150, 30);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(220, 90, 250, 30);
        add(nameField);

        // Topic
        JLabel topicLabel = new JLabel("Select Topic:");
        topicLabel.setBounds(70, 150, 150, 30);
        add(topicLabel);

        javaButton = new JRadioButton("Java");
        dbmsButton = new JRadioButton("DBMS");
        dsaButton = new JRadioButton("DSA");
        cnButton = new JRadioButton("Computer Network");
        osButton = new JRadioButton("Operating System");

        javaButton.setBounds(80, 190, 200, 30);
        dbmsButton.setBounds(80, 220, 200, 30);
        dsaButton.setBounds(80, 250, 200, 30);
        cnButton.setBounds(80, 280, 200, 30);
        osButton.setBounds(80, 310, 200, 30);

        add(javaButton);
        add(dbmsButton);
        add(dsaButton);
        add(cnButton);
        add(osButton);

        group = new ButtonGroup();
        group.add(javaButton);
        group.add(dbmsButton);
        group.add(dsaButton);
        group.add(cnButton);
        group.add(osButton);

        // Start Button
        startButton = new JButton("Start Quiz");
        startButton.setBounds(220, 380, 150, 40);
        startButton.addActionListener(this);
        add(startButton);

        setVisible(true);
    }

    @Override
public void actionPerformed(ActionEvent e) {

    String name = nameField.getText().trim();

    if (name.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter your name.");
        return;
    }

    String topic = "";

    if (javaButton.isSelected())
        topic = "java";
    else if (dbmsButton.isSelected())
        topic = "dbms";
    else if (dsaButton.isSelected())
        topic = "dsa";
    else if (cnButton.isSelected())
        topic = "cn";
    else if (osButton.isSelected())
        topic = "os";

    if (topic.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a topic.");
        return;
    }

    // Save user
    UserDAO.saveUser(name);

    dispose();

    new QuizGUI(name, topic);
}


}