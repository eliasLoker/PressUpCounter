package com.example.alexa.pressupcounter.setprogram.inject;

import com.example.alexa.pressupcounter.data.ProgramDao;
import com.example.alexa.pressupcounter.setprogram.interactor.SetProgramInteractor;
import com.example.alexa.pressupcounter.setprogram.router.SetProgramRouter;
import com.example.alexa.pressupcounter.setprogram.router.SetProgramRouterImpl;
import com.example.alexa.pressupcounter.setprogram.view.SetProgramFragment;
import com.example.alexa.pressupcounter.setprogram.viewmodel.SetProgramViewModel;
import com.example.alexa.pressupcounter.setprogram.viewmodel.SetProgramFactory;
import com.example.alexa.pressupcounter.setprogram.viewmodel.SetProgramViewModelImpl;

import androidx.lifecycle.ViewModelProviders;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Mikhalev on 24.03.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
public class SetProgramModule {

    @SetProgramScope
    @Provides
    SetProgramInteractor provideSetProgramInteractor(ProgramDao programDao) {
        return new SetProgramInteractor(programDao);
    }

    @SetProgramScope
    @Provides
    SetProgramViewModel provideSetTrainingDayViewModel(SetProgramFragment fragment, SetProgramFactory factory) {
        return ViewModelProviders.of(fragment, factory).get(SetProgramViewModelImpl.class);
    }

    @SetProgramScope
    @Provides
    SetProgramFactory provideSetProgramFactory(SetProgramInteractor setProgramInteractor, SetProgramRouter router) {
        return new SetProgramFactory(setProgramInteractor, router);
    }

    @Provides
    SetProgramRouter provideSetProgramRouter(SetProgramFragment fragment) {
        return new SetProgramRouterImpl(fragment);
    }
}
