package skypro.test.project.service.implemention;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skypro.test.project.Question;
import skypro.test.project.service.QuestionRepository;
import skypro.test.project.service.QuestionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private Random random;
    private QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        this.random = new Random();
    }
    @Override
    public Question add(String question, String answer) {
        return questionRepository.addQuestion(question, answer);
    }

    public Question add(Question question) {
        return questionRepository.add(question);
    }
    @Override
    public Question remove(Question question) {
        return questionRepository.removeQuestion(question);
    }

    @Override
    public Collection<Question> getAllQuestions() {
        return questionRepository.getAllQuestions();
    }

    @Override
    public Question getRandomQuestion() {
        if (questionRepository.getSize() < 1) {
            throw new RuntimeException("No Java Questions Yet!");
        }
        return questionRepository.getAllQuestions().stream().collect(Collectors.toList()).get(random.nextInt(questionRepository.getSize()));

    }

    @Override
    public Question get(String text) {
        if (text == null) {
            throw new RuntimeException("Text was null");
        }
        return questionRepository.getAllQuestions().stream().filter(question -> question.getQuestion().equals(text)).findFirst().orElseThrow(() -> new RuntimeException(""));
    }
    @Override
    public int getSize() {
        return questionRepository.getSize();
    }
}
