package skypro.test.project.service.implemention;

import org.springframework.stereotype.Service;
import skypro.test.project.service.ExaminerService;
import skypro.test.project.NotEnoughQuestionExeption;
import skypro.test.project.Question;
import skypro.test.project.service.QuestionService;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private List<QuestionService> questionServices;
    int iterator;
    private Random random;


    public ExaminerServiceImpl() {
        this.random = new Random();
        this.iterator = 0;
    }
    @Override
    public Collection<Question> getQuestions(int amount){

        Collection<Question> result = new HashSet<Question>(amount);
        int type;

        while (result.size() < amount) {
            type = random.nextInt(questionServices.size());
            result.add(getQuestion(questionServices.get(type)));
        }
        return result;

    }
    private Question getQuestion(QuestionService questionService) {
        Question question = questionService.getRandomQuestion();
        if (question == null) {
            iterator++;
            if (iterator < 10) {
                System.err.println("Got null question. Trying again");
                return getQuestion(questionService);
            } else {
                throw new RuntimeException("Too much null questions detected in " + questionService + " repository");
            }
        }
        iterator = 0;
        return question;
    }

}
