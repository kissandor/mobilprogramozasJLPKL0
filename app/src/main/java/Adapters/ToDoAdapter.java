package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobilprogramozasjlpkl0.R;

import java.util.ArrayList;

import Interfaces.IToDo;
import data.ToDo;

public class ToDoAdapter extends ArrayAdapter<ToDo> {
    private ArrayList<ToDo> todos;

    public ToDoAdapter(@NonNull Context context, int resource, ArrayList<ToDo> todos) {
        super(context, resource, todos);
        this.todos = todos;
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

        TextView tView = convertView.findViewById(R.id.task);
        tView.setText(todos.get(position).getTodo());

        return convertView;
    }
}
