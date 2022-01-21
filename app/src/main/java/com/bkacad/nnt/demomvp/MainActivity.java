package com.bkacad.nnt.demomvp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import static android.view.View.GONE;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private TextView textView;
    private ProgressBar progressBar;
    private Button button;
    private MainContract.Presenter presenter;

    private void initUI(){
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(GONE);
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setQuote(String string) {
        textView.setText(string);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        presenter = new MainPresenterImpl(this, new GetQuoteInteractImpl());
        button.setOnClickListener(v ->  presenter.onButtonClick());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
