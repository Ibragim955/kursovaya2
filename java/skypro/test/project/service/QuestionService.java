package skypro.test.project.service;

import skypro.test.project.Question;

import java.util.Collection;

public interface QuestionService {

    Question add(String question, String answer);

    Question remove(Question question);

    Question getRandomQuestion();

    Collection<Question> getAllQuestions();

    Question get(String questionText);
    int getSize();
}
