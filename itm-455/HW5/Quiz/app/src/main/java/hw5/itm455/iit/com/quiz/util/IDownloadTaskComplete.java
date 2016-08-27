package hw5.itm455.iit.com.quiz.util;

import java.util.ArrayList;

import hw5.itm455.iit.com.quiz.model.Question;

public interface IDownloadTaskComplete {
    void onDownloadComplete(ArrayList<Question> list);
}
