package com.example.alexa.pressupcounter.main.inject;

import androidx.lifecycle.ViewModelProviders;

import com.example.alexa.pressupcounter.data.ProgramDao;
import com.example.alexa.pressupcounter.main.interactor.MainInteractor;
import com.example.alexa.pressupcounter.main.router.MainRouter;
import com.example.alexa.pressupcounter.main.router.MainRouterImpl;
import com.example.alexa.pressupcounter.main.view.MainActivity;
import com.example.alexa.pressupcounter.main.viewmodel.MainFactory;
import com.example.alexa.pressupcounter.main.viewmodel.MainViewModel;
import com.example.alexa.pressupcounter.main.viewmodel.MainViewModelImpl;
import com.example.alexa.pressupcounter.preferences.Preferences;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 19.04.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class MainModule {

    @Provides
    @MainScope
    MainInteractor provideMainInteractor(ProgramDao programDao, Preferences preferences) {
        return new MainInteractor(programDao, preferences);
    }


    @Provides
    @MainScope
    MainViewModel provideMainViewModel(MainActivity mainActivity, MainFactory mainFactory) {
        return ViewModelProviders.of(mainActivity, mainFactory).get(MainViewModelImpl.class);
    }

    @Provides
    @MainScope
    MainFactory provideMainFactory(MainInteractor mainInteractor, MainRouter mainRouter) {
        return new MainFactory(mainInteractor, mainRouter);
    }

    @Provides
    MainRouter provideMainRouter(MainActivity mainActivity) {
        return new MainRouterImpl(mainActivity);
    }
}
