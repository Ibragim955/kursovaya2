package skypro.test.project.service.implemention;

import org.springframework.stereotype.Repository;
import skypro.test.project.Question;
import skypro.test.project.service.QuestionRepository;
import skypro.test.project.service.QuestionService;

import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {
    private int size;
    private Collection<Question> questions;

    public QuestionRepositoryImpl(Collection<Question> questions) {
        this.questions = questions;
        this.size = questions.size();
    }
    @Override
    public Question add(Question question) {
        if (question != null) {
            questions.add(question);
            size = questions.size();
            return question;
        }
        throw new IllegalArgumentException("Question can't be null");
    }

    @Override
    public Question addQuestion(String question, String answer) {
        if (question == null || answer == null) {
            throw new IllegalArgumentException("Question or answer can't be null");
        }

        Question result = new Question(question, answer);
        questions.add(result);
        size = questions.size();
        return result;
    }

    @Override
    public Question removeQuestion(Question question) {
        if (question != null) {
            questions.remove(question);
            size = questions.size();
            return question;
        }
        throw new IllegalArgumentException("Question can't be null");
    }

    @Override
    public Collection<Question> getAllQuestions() {
        if (size < 1) {
            throw new RuntimeException("No questions yet!");
        }
        TreeSet<Question> result = new TreeSet<>(questions);
        return result;
    }

    @Override
    public int getSize() {
        return size;
    }

}
