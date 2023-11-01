package skypro.test.project.service;

import org.springframework.stereotype.Service;
import skypro.test.project.NotEnoughQuestionExeption;
import skypro.test.project.Question;

import java.util.Collection;
@Service
public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}



