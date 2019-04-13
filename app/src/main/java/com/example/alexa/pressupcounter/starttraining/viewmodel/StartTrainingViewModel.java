package com.example.alexa.pressupcounter.starttraining.viewmodel;

import com.example.alexa.pressupcounter.data.PressUp;
import com.example.alexa.pressupcounter.starttraining.router.StartTrainingRouter;

import androidx.databinding.ObservableField;

/**
 * Created by Alexandr Mikhalev on 01.02.2019.
 *
 * @author Alexandr Mikhalev
 */
public interface StartTrainingViewModel {

    void setRouter(StartTrainingRouter startTrainingRouter);

    ObservableField<PressUp> getPressUp();

    ObservableField<Integer> getFinalQuantityRepetition();

    void onClickStartTrainingButton();

    void onClickListButton();

    void onClickSettingsButton();
}
