package com.example.alexa.pressupcounter.app.inject;

import com.example.alexa.pressupcounter.firstlaunch.inject.FirstLaunchModule;
import com.example.alexa.pressupcounter.firstlaunch.inject.FirstLaunchScope;
import com.example.alexa.pressupcounter.firstlaunch.view.FirstLaunchFragment;
import com.example.alexa.pressupcounter.resulttraining.inject.ResultTrainingModule;
import com.example.alexa.pressupcounter.resulttraining.inject.ResultTrainingScope;
import com.example.alexa.pressupcounter.resulttraining.view.ResultTrainingFragment;
import com.example.alexa.pressupcounter.setprogram.inject.SetProgramModule;
import com.example.alexa.pressupcounter.setprogram.inject.SetProgramScope;
import com.example.alexa.pressupcounter.setprogram.view.SetProgramFragment;
import com.example.alexa.pressupcounter.settime.inject.SetTimeModule;
import com.example.alexa.pressupcounter.settime.inject.SetTimeScope;
import com.example.alexa.pressupcounter.settime.view.SetTimeFragment;
import com.example.alexa.pressupcounter.settings.inject.SettingsModule;
import com.example.alexa.pressupcounter.settings.inject.SettingsScope;
import com.example.alexa.pressupcounter.settings.view.SettingsFragment;
import com.example.alexa.pressupcounter.settrainingday.inject.SetTrainingDayModule;
import com.example.alexa.pressupcounter.settrainingday.inject.SetTrainingDayScope;
import com.example.alexa.pressupcounter.settrainingday.view.SetTrainingDayFragment;
import com.example.alexa.pressupcounter.training.inject.TrainingFragmentModule;
import com.example.alexa.pressupcounter.training.inject.TrainingFragmentScope;
import com.example.alexa.pressupcounter.training.view.TrainingFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Alexandr Mikhalev on 24.03.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
public interface AppModule {

    @ContributesAndroidInjector(modules = FirstLaunchModule.class)
    @FirstLaunchScope
    FirstLaunchFragment contributeFirstLaunchFragment();

    @ContributesAndroidInjector(modules = TrainingFragmentModule.class)
    @TrainingFragmentScope
    TrainingFragment contributeTrainingFragment();

    @ContributesAndroidInjector(modules = ResultTrainingModule.class)
    @ResultTrainingScope
    ResultTrainingFragment contributeResultTrainingFragment();

    @ContributesAndroidInjector(modules = SetProgramModule.class)
    @SetProgramScope
    SetProgramFragment contributeSetProgramFragment();

    @ContributesAndroidInjector(modules = SetTimeModule.class)
    @SetTimeScope
    SetTimeFragment contributeSetTimeFragment();

    @ContributesAndroidInjector(modules = SettingsModule.class)
    @SettingsScope
    SettingsFragment contributeSettingsFragment();

    @ContributesAndroidInjector(modules = SetTrainingDayModule.class)
    @SetTrainingDayScope
    SetTrainingDayFragment contributeSetTrainingDayFragment();
}
