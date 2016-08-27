package hw5.itm455.iit.com.quiz.util;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import hw5.itm455.iit.com.quiz.model.Question;

public class DownloadTask extends AsyncTask<String, String, String> {

    private String _url = "";
    private ArrayList<Question> _list = new ArrayList<>();
    private IDownloadTaskComplete _delegate = null;


    public DownloadTask(IDownloadTaskComplete callback, String url) {
        this._url = url;
        this._delegate = callback;
    }


    @Override
    protected String doInBackground(String... f_url) {

        URL _textUrl;
        Integer _questionId = 1;
        try {
            _textUrl = new URL(this._url);
            BufferedReader bufferReader = new BufferedReader(
                    new InputStreamReader(_textUrl.openStream()));
            String questionText;
            while ((questionText = bufferReader.readLine()) != null) {
                this._list.add(new Question(_questionId, questionText));
                ++_questionId;
            }
            bufferReader.close();

        } catch (Exception e) {
            Log.i("DownloadTask", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String file_url) {
        this._delegate.onDownloadComplete(this._list);
    }

}
