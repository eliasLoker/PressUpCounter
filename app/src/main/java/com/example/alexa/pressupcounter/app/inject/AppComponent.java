package com.example.alexa.pressupcounter.app.inject;

import com.example.alexa.pressupcounter.setprogram.inject.SetProgramModelComponent;
import com.example.alexa.pressupcounter.setprogram.inject.SetProgramModelModule;
import com.example.alexa.pressupcounter.starttraining.inject.StartTrainingModelComponent;
import com.example.alexa.pressupcounter.starttraining.inject.StartTrainingModelModule;

import dagger.Component;

/**
 * Created by Alexandr Mikhalev on 24.03.2019.
 *
 * @author Alexandr Mikhalev
 */
@AppScope
@Component(modules = {AppModule.class, RoomModule.class})
public interface AppComponent {
        SetProgramModelComponent createSetProgramModelComponent(SetProgramModelModule setProgramModelModule);

        StartTrainingModelComponent createStartTrainingModelComponent(StartTrainingModelModule startTrainingModelModule);

        //PressUpDao pressUpDao();

        //AppDatabase appDatabase();
}