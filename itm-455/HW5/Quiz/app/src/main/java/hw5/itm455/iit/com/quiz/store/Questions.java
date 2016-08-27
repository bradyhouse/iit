package hw5.itm455.iit.com.quiz.store;


import java.util.ArrayList;


import hw5.itm455.iit.com.quiz.model.Question;

public class Questions {

    private ArrayList<Question> _list = new ArrayList<Question>();

    public Questions(ArrayList<Question> questions) {
        this._list.addAll(questions);
    }

    public ArrayList<Question> getList() {
        return _list;
    }

    public Integer getTotalCorrect() {
        ArrayList<Question> correctAnswers = new ArrayList<Question>();
        for (Question q : this._list) {
            if (q.isCorrect()) {
                correctAnswers.add(q);
            }
        }
        return correctAnswers.size();
    }

    public boolean isEmpty() {
        return this._list.size() == 0;
    }

}
