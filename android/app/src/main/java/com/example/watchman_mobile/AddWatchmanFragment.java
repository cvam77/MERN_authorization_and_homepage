package com.example.watchman_mobile;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddWatchmanFragment extends Fragment {

    EditText mEtEnterUrl, mEtKeyword;
    Button mButtonSave;

    DatabaseReference mRealtimeDatabase  = FirebaseDatabase.getInstance().getReference();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Add Watchman!");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_watchman, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEtEnterUrl = getView().findViewById(R.id.etEnterURLAddWatchman);
        mEtKeyword = getView().findViewById(R.id.etEnterKeywordAddWatchman);
        mButtonSave = getView().findViewById(R.id.buttonSaveAddWatchman);
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveButtonClicked();
            }
        });
    }

    private void SaveButtonClicked() {
        String url = mEtEnterUrl.getText().toString();
        String keyword = mEtKeyword.getText().toString();

        if(url.isEmpty() || keyword.isEmpty())
        {
            Toast.makeText(getContext(),"Textfields cannot be empty!",Toast.LENGTH_SHORT).show();
        }
        else if(keyword.length() < 3)
        {
            Toast.makeText(getContext(),"Keyword needs to be at least 3 characters long!",Toast.LENGTH_SHORT).show();

        }
        else
        {

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle("Home Screen");
    }
}