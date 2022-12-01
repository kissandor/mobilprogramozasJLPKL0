package com.example.mobilprogramozasjlpkl0;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteTodo} factory method to
 * create an instance of this fragment.
 */
public class DeleteTodo extends Fragment {

    ImageView deleteYes;
    ImageView deleteNo;

    public DeleteTodo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_delete_todo, container, false);

        deleteNo = (ImageView) view.findViewById(R.id.deleteNo);
        deleteYes = (ImageView) view.findViewById(R.id.deleteYes);

        deleteNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).showHideFragment();
            }
        });
       return view;
    }
}