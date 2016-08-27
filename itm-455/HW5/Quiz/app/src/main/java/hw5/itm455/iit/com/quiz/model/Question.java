package hw5.itm455.iit.com.quiz.model;


public class Question {

    private Integer id;
    private String question;
    private Boolean expected;
    private Boolean actual;

    public Question(Integer id, String question) {
        this.id = id;
        this.question = question;
        this.expected = this.hardCodeExpected(id);
        this.actual = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getExpected() {
        return expected;
    }

    public void setExpected(Boolean expected) {
        this.expected = expected;
    }

    public Boolean getActual() {
        return actual;
    }

    public void setActual(Boolean actual) {
        this.actual = actual;
    }

    public Boolean isCorrect() {
        return this.expected == this.actual;
    }

    private Boolean hardCodeExpected(Integer id) {
        switch (id) {
            case 1:
                return true;
            case 2:
                return false;
            case 3:
                return true;
            case 4:
                return false;
            case 5:
                return false;
            default:
                return false;
        }
    }

}
