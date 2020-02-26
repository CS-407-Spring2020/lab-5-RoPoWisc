package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String usernameKey = "username";
        SharedPreferences sharedPreferences = getSharedPreferences("com.potineni.lab5msl", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(usernameKey,"");
        if(!username.equals("")){
            goToMainPage(username);
        }else{
            setContentView(R.layout.activity_main);
        }
    }

    public void onButtonPressed(View view) {

        EditText txtFieldDemo = (EditText) findViewById(R.id.txtUsrname);
        String str = txtFieldDemo.getText().toString();
        //Toast.makeText(MainActivity.this, txtFieldDemo.getText().toString(), Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences = getSharedPreferences("com.potineni.lab5msl", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", str).apply();
        goToMainPage(str);
    }

    public void goToMainPage(String s){
        if(s != "") {
            Intent intent = new Intent(this, displayNotes.class);
            intent.putExtra("message", s);
            startActivity(intent);
        }


    }
}
