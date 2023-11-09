package skypro.test.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.test.project.service.QuestionRepository;
import skypro.test.project.service.implemention.QuestionServiceImpl;


import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {
    @Mock
    QuestionRepository javaQuestionRepositoryMock;
    @InjectMocks
    QuestionServiceImpl javaQuestionService;

    Question test = new Question("Foo", "Bar");
    Question test2 = new Question("Bar", "Foo");
    String question = "Foo";
    String answer = "Bar";
    Collection<Question> questions = new TreeSet<Question>(Set.of(test, test2, new Question("A", "BE")));


    @Test
    void add() {
        when(javaQuestionRepositoryMock.addQuestion(question, answer)).thenReturn(test);
        Assertions.assertEquals(test, javaQuestionService.add(question, answer));
        //
    }

    @Test
    void addQ() {
        when(javaQuestionRepositoryMock.add(test)).thenReturn(test);
        Assertions.assertEquals(test, javaQuestionService.add(test));
    }

    @Test
    void remove() {
        when(javaQuestionRepositoryMock.removeQuestion(test)).thenReturn(test);
        Assertions.assertEquals(test, javaQuestionService.remove(test));
    }

    @Test
    void getAll() {
        when(javaQuestionRepositoryMock.getAllQuestions()).thenReturn(new TreeSet<Question>(Set.of(test, test2)));
        Collection<Question> expected = new TreeSet<Question>(Set.of(test2, test));

        Assertions.assertIterableEquals(expected, javaQuestionService.getAllQuestions());
    }

    @Test
    void getRandomQuestion() {
        when(javaQuestionRepositoryMock.getSize()).thenReturn(2);
        when(javaQuestionRepositoryMock.getAllQuestions()).thenReturn(questions);

        Question actual = javaQuestionService.getRandomQuestion();

        Assertions.assertTrue(javaQuestionRepositoryMock.getAllQuestions().contains(actual));
    }

    @Test
    void get() {
        when(javaQuestionRepositoryMock.getAllQuestions()).thenReturn(questions);
        Assertions.assertEquals(test, javaQuestionService.get(question));
    }
    @Test
    void getThrowsExceptionAtNull() {
        Assertions.assertThrows(RuntimeException.class, () -> javaQuestionService.get(null));
    }

    @Test
    void getRandomThrowsExceptionIfEmpty() {
        Assertions.assertThrows(RuntimeException.class, () -> javaQuestionService.getRandomQuestion());
    }

    @Test
    void getUnexistingQuestion() {
        when(javaQuestionRepositoryMock.getAllQuestions()).thenReturn(questions);
        Assertions.assertThrows(RuntimeException.class, () -> javaQuestionService.get("ASD"));
    }


}