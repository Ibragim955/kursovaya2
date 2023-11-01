package skypro.test.project.service;

import org.springframework.stereotype.Repository;
import skypro.test.project.Question;

import java.util.Collection;

@Repository
public interface QuestionRepository {
    Question add(Question question);
    Question addQuestion(String question, String answer);

    Question removeQuestion(Question question);

    Collection<Question> getAllQuestions();

    int getSize();
}
