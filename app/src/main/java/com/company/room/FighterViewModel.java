package com.company.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class FighterViewModel extends AndroidViewModel {
    Fighter fighter;

    LiveData<String> repeticionLiveData;

    public FighterViewModel(@NonNull Application application) {
        super(application);

        fighter = new Fighter();

        repeticionLiveData = Transformations.switchMap(fighter.ordenLiveData, new Function<String, LiveData<String>>() {
            @Override
            public LiveData<String> apply(String orden) {
                return new MutableLiveData<>(orden);
            }
        });




    }



    LiveData<String> obtenerRepeticion(){
        return repeticionLiveData;
    }

}
