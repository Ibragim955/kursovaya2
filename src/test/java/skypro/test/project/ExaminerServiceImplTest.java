package skypro.test.project;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.test.project.service.QuestionRepository;
import skypro.test.project.service.QuestionService;
import skypro.test.project.service.implemention.ExaminerServiceImpl;
import skypro.test.project.service.implemention.QuestionServiceImpl;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {

    private Question testQuestion;
    private Question testQuestion2;
    private Collection<Question> questions;
    private int amount = 2;


    @Mock
    private List<QuestionService> questionServiceListMock;
    @Mock
    private QuestionServiceImpl javaQuestionServiceMock;

    @InjectMocks
    private ExaminerServiceImpl examinerService;


    @BeforeEach
    void setUp() {
        testQuestion = new Question("foo", "bar");
        testQuestion2 = new Question("bar", "foo");
        questions = new HashSet<Question>();
        questions.add(testQuestion);

        when(questionServiceListMock.size()).thenReturn(2);
        when(questionServiceListMock.get(anyInt())).thenReturn(javaQuestionServiceMock);
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(testQuestion).thenReturn(testQuestion2);
    }

    @Test
    void getOneQuestion() {
        assertIterableEquals(questions, examinerService.getQuestions(1));
    }

    @Test
    void getQuestionsSize() {

        questions.add(testQuestion2);
        assertEquals(amount, examinerService.getQuestions(amount).size());
    }

    @Test
    void getQuestions() {

        questions.add(testQuestion2);
        assertTrue(questions.containsAll(examinerService.getQuestions(amount)));
    }

    @Test
    void nullLoopBehaviorTest() {
        //
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(null);
        assertThrows(RuntimeException.class, () -> examinerService.getQuestions(1));
    }

    @Test
    void nullQuestionTryAgainTest() {


        questions.add(testQuestion2);
        when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(testQuestion).thenReturn(null).thenReturn(testQuestion2);
        assertTrue(questions.containsAll(examinerService.getQuestions(amount)));
    }
}





