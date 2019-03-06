package com.example.alexa.pressupcounter.training.viewmodel;

import com.example.alexa.pressupcounter.DialogEvent;
import com.example.alexa.pressupcounter.PressUp2;
import com.example.alexa.pressupcounter.training.model.TrainingFragmentModel;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alexandr Mikhalev on 23.01.2019.
 *
 * @author Alexandr Mikhalev
 */
public class TrainingViewModelImpl extends ViewModel implements TrainingViewModel {

    private TrainingFragmentModel mTrainingFragmentModel;

    private ObservableField<Integer> mRepetition;
    private ObservableField<String> mQuantityOfRepetitionOrRestTime;
    private ObservableField<Boolean> mStateOfRestButton;

    private MutableLiveData<DialogEvent> mDialogEventForRest;
    private MutableLiveData<DialogEvent> mDialogEventForRestOff;
    private MutableLiveData<DialogEvent> mDialogEventFinishTraining;

    private String mTextForTraining;
    private String mTextForRest;
    private ObservableField<String> mTextForTrainingOrRest;

    private PressUp2 mPressUp;

    public TrainingViewModelImpl(TrainingFragmentModel trainingFragmentModel) {
        mTrainingFragmentModel = trainingFragmentModel;

        mTrainingFragmentModel.getPressUpById(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PressUp2>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(List<PressUp2> pressUp2s) {
                        mPressUp = pressUp2s.get(0);
                        mQuantityOfRepetitionOrRestTime = new ObservableField<>(String.valueOf(mPressUp.getFirstRepetition()));
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        mTextForTraining = "Эй, Амиго! Сделай количество повторений и жми кнопку отдыха!";
        mTextForRest = "Жди, когда закончится время отдыха и приступай к следующему повторению!";
        mTextForTrainingOrRest = new ObservableField<>(mTextForTraining);

        mRepetition = new ObservableField<>(1);
        //mQuantityOfRepetitionOrRestTime = new ObservableField<>(String.valueOf(mPressUp.getFirstRepetition()));

        mStateOfRestButton = new ObservableField<>(true);

        mDialogEventForRest = new MutableLiveData<>();
        mDialogEventForRestOff = new MutableLiveData<>();
        mDialogEventFinishTraining = new MutableLiveData<>();
    }

    @Override
    public ObservableField<Integer> getRepetition() {
        return mRepetition;
    }

    @Override
    public ObservableField<String> getQuantityOfRepetitionOrRestTime() {
        return mQuantityOfRepetitionOrRestTime;
    }

    @Override
    public ObservableField<String> getTextForTrainingOrRest() {
        return mTextForTrainingOrRest;
    }

    @Override
    public ObservableField<Boolean> getStateOfRestButton() {
        return mStateOfRestButton;
    }

    @Override
    public MutableLiveData<DialogEvent> getDialogEventForRest() {
        return mDialogEventForRest;
    }

    @Override
    public MutableLiveData<DialogEvent> getDialogEventForRestOff() {
        return mDialogEventForRestOff;
    }

    @Override
    public MutableLiveData<DialogEvent> getDialogEventFinishTraining() {
        return mDialogEventFinishTraining;
    }

    @Override
    public void onClickNextRepetitionButton() {
        goToNextRepetition();
    }

    @Override
    public void onClickRestButton() {
        mDialogEventForRest.postValue(new DialogEvent());
        mStateOfRestButton.set(false);
    }

    @Override
    public void onClickPositiveButtonOfRestDialog() {
        mTextForTrainingOrRest.set(mTextForRest);
        Observer<Long> observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                mQuantityOfRepetitionOrRestTime.set("Start");
            }

            @Override
            public void onNext(Long aLong) {
                mQuantityOfRepetitionOrRestTime.set(String.valueOf(aLong));
            }

            @Override
            public void onError(Throwable e) {
                mQuantityOfRepetitionOrRestTime.set("Error" + e);
            }

            @Override
            public void onComplete() {
                mQuantityOfRepetitionOrRestTime.set("Отдых закончен");
                mTextForTrainingOrRest.set(mTextForTraining);
                mStateOfRestButton.set(true);
                /*
                if (mRepetition.get() == 5) {
                    mDialogEventFinishTraining.postValue(new DialogEvent());
                    return;
                }
                */
                mDialogEventForRestOff.postValue(new DialogEvent());
            }
        };
        Observable<Long> observable = mTrainingFragmentModel.getMainTimer();
        observable.subscribe(observer);
    }

    @Override
    public void onClickNegativeButtonOfRestDialog() {
        mStateOfRestButton.set(true);
    }

    @Override
    public void onClickAdditionalTimeForRest() {
        mTextForTrainingOrRest.set(mTextForRest);
        Observer<Long> observer = new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                mQuantityOfRepetitionOrRestTime.set("Доп.время началось");
            }

            @Override
            public void onNext(Long aLong) {
                mQuantityOfRepetitionOrRestTime.set(String.valueOf(aLong));
            }

            @Override
            public void onError(Throwable e) {
                mQuantityOfRepetitionOrRestTime.set("Error" + e);
            }

            @Override
            public void onComplete() {
                if (mRepetition.get() == 5) return;
                goToNextRepetition();
            }
        };
        Observable<Long> observable = mTrainingFragmentModel.getAdditionalTimer();
        observable.subscribe(observer);
    }

    @Override
    public void goToNextRepetition() {
        if (mRepetition.get() < 5) {
            mRepetition.set(mRepetition.get() + 1);
        } else {
            mRepetition.set(1);
        }
        switch (mRepetition.get()) {
            case 1:
                mQuantityOfRepetitionOrRestTime.set(String.valueOf(mPressUp.getFirstRepetition()));
                break;
            case 2:
                mQuantityOfRepetitionOrRestTime.set(String.valueOf(mPressUp.getSecondRepetition()));
                break;
            case 3:
                mQuantityOfRepetitionOrRestTime.set(String.valueOf(mPressUp.getThirdRepetition()));
                break;
            case 4:
                mQuantityOfRepetitionOrRestTime.set(String.valueOf(mPressUp.getFourthRepetition()));
                break;
            case 5:
                mQuantityOfRepetitionOrRestTime.set(String.valueOf(mPressUp.getFifthRepetition()));
                break;
        }
    }

    @Override
    public void onClickFinishTrainingButton() {
        mDialogEventFinishTraining.postValue(new DialogEvent());
    }
}
