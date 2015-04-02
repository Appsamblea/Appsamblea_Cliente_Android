package cliente.appsamblea.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cliente.appsamblea.R;

public class AniadirUsuarioActivity extends ActionBarActivity {
    private ListView listView;
    ArrayAdapter<Integer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        listView = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_list_item_multiple_choice, new ArrayList<Integer>());
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

        for(int i = 0; i < listView.getAdapter().getCount(); ++i){
            listView.setItemChecked(i, false);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void Aceptar(View view){
        List<Integer> ids = new ArrayList<Integer>();
        SparseBooleanArray checked = listView.getCheckedItemPositions();

        for (int i = 0; i < checked.size(); i++) {
            int position = checked.keyAt(i);

            if (checked.valueAt(i))
                ids.add(position);
        }
    }
}
