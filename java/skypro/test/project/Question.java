package skypro.test.project;

import java.util.Objects;

public class Question implements Comparable<Question>{
    private String question;
    private String answer;


    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(question, question1.question) && Objects.equals(answer, question1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answer);
    }


    @Override
    public int compareTo(Question q) {
        if (hashCode() > q.hashCode()) {
            return 1;
        }
        else if (hashCode() < q.hashCode()) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
