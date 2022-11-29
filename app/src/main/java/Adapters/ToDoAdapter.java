package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobilprogramozasjlpkl0.DeleteTodo;
import com.example.mobilprogramozasjlpkl0.MainActivity;
import com.example.mobilprogramozasjlpkl0.R;
import com.example.mobilprogramozasjlpkl0.SQLiteDatabaseHandler;
import com.example.mobilprogramozasjlpkl0.StartPage;

import java.util.ArrayList;

import data.ToDo;

public class ToDoAdapter extends ArrayAdapter<ToDo> {
    private ArrayList<ToDo> todos;
    private Context context;

    public ToDoAdapter(@NonNull Context context, int resource, ArrayList<ToDo> todos) {
        super(context, resource, todos);
        this.todos = todos;
        this.context = context;
    }

    public void updateAdapter(ArrayList<ToDo> newlist) {
        todos = newlist;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Nullable
    @Override
    public ToDo getItem(int position) {
        return todos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        CheckBox cBox = convertView.findViewById(R.id.checkbox_task);
        cBox.setChecked(todos.get(position).getCompleted());
        cBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final boolean isChecked = cBox.isChecked();
                if(isChecked){
                    /*
                    SQLiteDatabaseHandler dbH = new SQLiteDatabaseHandler(getContext());
                    dbH.updateTodo(todos.get(position));
                    ToDoAdapter.this.notifyDataSetChanged();
                     */
                    Intent intent = new Intent(context, StartPage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                    DeleteTodo deleteTodo =  new DeleteTodo();
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    activity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, deleteTodo)
                            .addToBackStack(null).commit();
                }

            }
        });
        TextView tView = convertView.findViewById(R.id.task);
        tView.setText(todos.get(position).getTodo());

        return convertView;
    }

}
