package com.example.alexa.pressupcounter.utils;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexa.pressupcounter.R;

/**
 * Created by Alexandr Mikhalev on 05.02.2019.
 *
 * @author Alexandr Mikhalev
 */
public class DialogTrainingRest extends DialogFragment implements View.OnClickListener {

    private OnButtonClick mOnButtonClick;

    public void initDialog(OnButtonClick onButtonClick) {
        this.mOnButtonClick = onButtonClick;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Начать отдых?");
        View v = inflater.inflate(R.layout.dialog_training_rest, null);
        v.findViewById(R.id.positive_button).setOnClickListener(this);
        v.findViewById(R.id.negative_button).setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.positive_button: mOnButtonClick.onPositiveButton(); break;
            case R.id.negative_button: mOnButtonClick.onNegativeButton(); break;
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public interface OnButtonClick {
        void onPositiveButton();
        void onNegativeButton();
    }
}