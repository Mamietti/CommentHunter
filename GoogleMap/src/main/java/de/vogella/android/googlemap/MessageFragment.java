package de.vogella.android.googlemap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

public class MessageFragment extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View v =  LayoutInflater.from(getActivity())
                .inflate(R.layout.message_layout, null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .create();
    }

}
