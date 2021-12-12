package com.company.room;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

import androidx.lifecycle.LiveData;

public class Fighter extends RecyclerElementosFragment{



    interface FighterListener {
        void cuandoDeLaOrden(String orden);
    }


    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> entrenando;

    void iniciarEntrenamiento(FighterListener entrenadorListener) {
        if (entrenando == null || entrenando.isCancelled()) {
            entrenando = scheduler.scheduleAtFixedRate(new Runnable() {
                int ejercicio;
                int repeticiones = 5;

                @Override
                public void run() {
                    if (repeticiones < -1) {
                        repeticiones = 5;
                        ejercicio++ ;
                    }
                    entrenadorListener.cuandoDeLaOrden("" + (repeticiones <= 0 ? "CAMBIO" : repeticiones) );
                    repeticiones--;

                    if(ejercicio == 5){
                        ejercicio = 0;
                    }

                }
            }, 0, 1, SECONDS);
        }
    }

    void pararEntrenamiento() {
        if (entrenando != null) {
            entrenando.cancel(true);
        }
    }

    LiveData<String> ordenLiveData = new LiveData<String>() {
        @Override
        protected void onActive() {
            super.onActive();

            iniciarEntrenamiento(new FighterListener() {
                @Override
                public void cuandoDeLaOrden(String orden) {
                    postValue(orden);
                }
            });
        }

        @Override
        protected void onInactive() {
            super.onInactive();

            pararEntrenamiento();
        }
    };
}