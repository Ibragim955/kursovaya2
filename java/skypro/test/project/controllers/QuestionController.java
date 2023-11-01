package skypro.test.project.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.test.project.Question;
import skypro.test.project.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("")
public class QuestionController {
    private QuestionService questionService;


    //как параметры получаем вопрос - ответ
    public Question addQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.add(question, answer);
    }

    //метод для получения всех вопросов
    private Collection<Question> getAll() {
        return questionService.getAllQuestions();
    }



    //метод для удаления вопроса
    public Question removeQuestion(@RequestParam String question, @RequestParam String answer) {
        return questionService.remove(new Question(question, answer));
    }

    //метод для получения рандомного вопроса
    public Question getRandomQuestion() {
        return questionService.getRandomQuestion();
    }
}
