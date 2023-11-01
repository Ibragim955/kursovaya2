package skypro.test.project.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import skypro.test.project.service.ExaminerService;
import skypro.test.project.NotEnoughQuestionExeption;
import skypro.test.project.Question;

import java.util.Collection;

@RestController
public class Controller {

    private final ExaminerService examinerService;


    public Controller(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable Integer amount) throws NotEnoughQuestionExeption {
        return examinerService.getQuestions(amount);
    }
}

