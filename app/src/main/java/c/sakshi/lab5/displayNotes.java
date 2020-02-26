package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class displayNotes extends AppCompatActivity {

    TextView textViewTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_notes);

        textViewTwo = (TextView) findViewById(R.id.txtWelcome);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        textViewTwo.setText("Welcome to Notes " + str + "!");
    }

    //DIsplays Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    //Display items in Menu with Functionality
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.itmLogout:
                logOut();
                return true;
            case R.id.itmAddNote:
                //IMPL IN MileStoneTwo
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

    public void logOut(){
        Intent intent = new Intent(this, MainActivity.class);
        SharedPreferences sharedPreferences = getSharedPreferences("com.potineni.lab5msl", Context.MODE_PRIVATE);
        sharedPreferences.edit().remove("username").apply();
        startActivity(intent);

    }
}
