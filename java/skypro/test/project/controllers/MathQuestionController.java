package skypro.test.project.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.test.project.Question;
import skypro.test.project.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/math/")
public class MathQuestionController {

    QuestionService questionService;

    public MathQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("add")
    public Question addQuestion(@RequestParam String question, String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("delete")
    public Question removeQuestion(@RequestParam String questionText) {
        return questionService.remove(questionService.get(questionText));
    }
}