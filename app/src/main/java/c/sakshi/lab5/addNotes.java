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
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class addNotes extends AppCompatActivity {

    int noteid = -1;
    EditText txtEditNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        txtEditNote = (EditText) findViewById(R.id.txtNote);

        final Intent intent = getIntent();
        noteid = intent.getIntExtra("noteid",-1);

        if(noteid != -1){
            Note note = displayNotes.notes.get(noteid);
            String noteContent = note.getContent();
            txtEditNote.setText(noteContent);
        }

    }


    public void onSave(View view) {
        txtEditNote = (EditText) findViewById(R.id.txtNote);
        String content = txtEditNote.getText().toString();

        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("com.potineni.basicnotesapp", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");

        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if(noteid == -1){//CREATE NOTE
            title = "NOTE_"+(displayNotes.notes.size() + 1);
            dbHelper.saveNotes(username,title,content,date);
        }else{//UPDATE NOTE
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, content);
        }
        goToMainPage(username);

    }

    /*//DIsplays Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optaddnote, menu);
        return true;
    }

    //Display items in Menu with Functionality
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.delNote:
                deleteNote();
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

    public void deleteNote(){
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        SharedPreferences sharedPreferences = getSharedPreferences("com.potineni.basicnotesapp", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");

        String title = "NOTE_" + (noteid + 1);

        if(noteid != -1){//UPDATE NOTE
            dbHelper.deleteNote(title);
        }
        goToMainPage(username);

    }*/

    public void goToMainPage(String s){
        if(s != "") {
            Intent intent = new Intent(this, displayNotes.class);
            intent.putExtra("message", s);
            startActivity(intent);
        }
    }

}