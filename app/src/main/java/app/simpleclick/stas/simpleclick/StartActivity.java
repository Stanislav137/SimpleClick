package app.simpleclick.stas.simpleclick;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    private Button btnEasy;
    private Button btnMedium;
    private Button btnHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnEasy = (Button) findViewById(R.id.btnEasy);
        btnMedium = (Button) findViewById(R.id.btnMedium);
        btnHard = (Button) findViewById(R.id.btnHard);
    }
    public void startEasy(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("difficulty", 1500);
        startActivity(intent);
        this.finish();
    }

    public void startMedium(View view) {

        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("difficulty", 1200);
        startActivity(intent);
        this.finish();
    }

    public void startHard(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("difficulty", 1000);
        startActivity(intent);
        this.finish();
    }
}
