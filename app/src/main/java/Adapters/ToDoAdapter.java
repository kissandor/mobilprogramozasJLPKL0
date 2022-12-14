package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.example.mobilprogramozasjlpkl0.DeleteTodo;
import com.example.mobilprogramozasjlpkl0.MainActivity;
import com.example.mobilprogramozasjlpkl0.R;

import java.util.ArrayList;
import java.util.Objects;

import data.ToDo;

public class ToDoAdapter extends ArrayAdapter<ToDo>  {
    private ArrayList<ToDo> todos;
    private Context context;
    private FragmentManager fmanager;

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

    @Override
    public boolean isEnabled(int position) {
        return true;
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
                    Intent intent = new Intent(context, DeleteTodo.class);
                    intent.putExtra("TodoChecked", todos.get(position).id);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
        TextView tView = convertView.findViewById(R.id.task);
        tView.setText(todos.get(position).getTodo());
        return convertView;
    }
}
