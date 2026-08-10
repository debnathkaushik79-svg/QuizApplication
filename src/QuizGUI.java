import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class QuizGUI extends JFrame implements ActionListener {

    private String name;
    private String topic;

    private ArrayList<Question> questions;

    private int currentQuestion = 0;
    private int score = 0;

    JLabel questionNumberLabel;
    JLabel questionLabel;

    JRadioButton option1;
    JRadioButton option2;
    JRadioButton option3;
    JRadioButton option4;

    ButtonGroup group;

    JButton nextButton;

    public QuizGUI(String name, String topic) {

        this.name = name;
        this.topic = topic;

       questions = QuestionLoader.loadQuestions(topic);
       System.out.println("Questions Loaded = " + questions.size());

        for (Question q : questions) {
            System.out.println(q.getQuestion());
        }
             

        // Randomize questions
        Collections.shuffle(questions);

        

        if (questions.size() > 10) {
            questions = new ArrayList<>(questions.subList(0,10));
        }

        System.out.println("Questions Loaded = " + questions.size());
        for (Question q : questions) {
            System.out.println(q.getQuestion());
        }

        setTitle("Quiz Application");

        setSize(700,500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        questionNumberLabel = new JLabel();
        questionNumberLabel.setBounds(40,20,200,30);
        questionNumberLabel.setFont(new Font("Arial",Font.BOLD,18));
        add(questionNumberLabel);

        questionLabel = new JLabel();
        questionLabel.setBounds(40,60,600,40);
        questionLabel.setFont(new Font("Arial",Font.PLAIN,18));
        add(questionLabel);

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        option1.setBounds(60,120,500,30);
        option2.setBounds(60,170,500,30);
        option3.setBounds(60,220,500,30);
        option4.setBounds(60,270,500,30);

        add(option1);
        add(option2);
        add(option3);
        add(option4);

        group = new ButtonGroup();

        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);

        nextButton = new JButton("Next");

        nextButton.setBounds(260,360,120,40);

        nextButton.addActionListener(this);

        add(nextButton);

        loadQuestion();

        setVisible(true);

    }

    private void loadQuestion() {

    group.clearSelection();

    Question q = questions.get(currentQuestion);

    questionNumberLabel.setText("Question " + (currentQuestion + 1)
            + " / " + questions.size());

    questionLabel.setText("<html><body style='width:600px'>"
            + q.getQuestion()
            + "</body></html>");

    option1.setText(q.getOption1());
    option2.setText(q.getOption2());
    option3.setText(q.getOption3());
    option4.setText(q.getOption4());
}

    @Override
    public void actionPerformed(ActionEvent e) {

        Question q = questions.get(currentQuestion);

        String selectedAnswer = "";

        if(option1.isSelected())
            selectedAnswer = option1.getText();

        else if(option2.isSelected())
            selectedAnswer = option2.getText();

        else if(option3.isSelected())
            selectedAnswer = option3.getText();

        else if(option4.isSelected())
            selectedAnswer = option4.getText();

        if(selectedAnswer.equals("")){

            JOptionPane.showMessageDialog(this,
                    "Please select an answer.");

            return;

        }

        if(selectedAnswer.equalsIgnoreCase(q.getAnswer()))
            score++;

        currentQuestion++;

        if(currentQuestion < questions.size()){

            loadQuestion();

        }else{

            // Save score to scores.txt
            // Save score to MySQL
            ScoreDAO.saveScore(name, topic, score, questions.size());

            // Close Quiz Window
            dispose();

            // Open Result Window
            new ResultGUI(name, topic, score, questions.size());
            

        }

    }

}