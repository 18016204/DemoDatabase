package sg.edu.rp.c346.id18016204.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

Button btnInsert,btnGetTasks;
TextView tvResults;
ListView lvData;

    ArrayList<Task> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lvData = findViewById(R.id.lv);

    list = new ArrayList<>();

        ArrayAdapter<Task> adapter = new ListAdapter(this, R.layout.row, list);
        lvData.setAdapter(adapter);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask("Submit RJ", "25 Apr 2016");
                db.close();
        }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create the DBHelper object
                DBHelper db = new DBHelper(MainActivity.this);
                //insert task
                list.clear();
                // direct assigning will cause the adapter to reference the wrong instance.
                list.addAll(db.getTasks());
                db.close();
                String text = "";
                for (int i = 0; i < list.size(); i++) {
                    Log.d("Database Content", i + ". " + list.get(i));
                    text += i + ". " + list.get(i).getDescription() + "\n";
                }
                tvResults.setText(text);
                adapter.notifyDataSetChanged();
            }
        });

    }
}