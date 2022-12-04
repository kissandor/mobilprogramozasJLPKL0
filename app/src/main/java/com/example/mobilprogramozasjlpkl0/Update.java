package com.example.mobilprogramozasjlpkl0;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Update extends Fragment {

    EditText inputText;
    Button updateButton;

    public Update() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update, container, false);
        //extracting the data from the main activity
        String textToUpdate = getArguments().getString("textToUpdate");
        inputText = (EditText) view.findViewById(R.id.input_update);
        //EditText gets the data from activity
        inputText.setText(textToUpdate);

        updateButton = (Button) view.findViewById(R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newTodo = inputText.getText().toString();
                SQLiteDatabaseHandler db = new SQLiteDatabaseHandler(getContext());
                db.updateTodo(61,newTodo);

                //closing the fragment.
                ((MainActivity) getActivity()).showHideFragment(Update.this);
            }
        });

        return view;
    }
}