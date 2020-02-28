package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class displayNotes extends AppCompatActivity {

    TextView textViewTwo;
    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_notes);

        textViewTwo = (TextView) findViewById(R.id.txtWelcome);
        final Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        textViewTwo.setText("Welcome to Notes " + str + "!");

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        notes = dbHelper.readNotes(str);

        ArrayList<String> displayNotes = new ArrayList<>();
        for (Note note : notes){
            displayNotes.add(String.format("Title: %s\nDate: %s", note.getTitle(), note.getDate()));
        }

        //ListView view to display notes on screen
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, displayNotes);
        ListView listView = (ListView) findViewById(R.id.ListdispNotes);
        listView.setAdapter(adapter);

        //Add onItemClickListner for ListView item, a note in this case
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentAddNote = new Intent(getApplicationContext(), addNotes.class);
                intentAddNote.putExtra("noteid", position);
                startActivity(intentAddNote);
            }
        });
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
                addNote();
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

    public void logOut(){
        Intent intent = new Intent(this, MainActivity.class);
        SharedPreferences sharedPreferences = getSharedPreferences("com.potineni.basicnotesapp", Context.MODE_PRIVATE);
        sharedPreferences.edit().remove("username").apply();
        startActivity(intent);
    }

    public void addNote(){
        Intent intent = new Intent(this, addNotes.class);
        //SharedPreferences sharedPreferences = getSharedPreferences("com.potineni.basicnotesapp", Context.MODE_PRIVATE);
        //sharedPreferences.edit().remove("username").apply();
        startActivity(intent);
    }
}
