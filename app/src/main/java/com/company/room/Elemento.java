package com.company.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Elemento {
    @PrimaryKey(autoGenerate = true)
    int id;

    String nombre;


    int vida,ataque,velocidad,escudo;

    public Elemento(String nombre, Integer vida, Integer ataque, Integer velocidad, Integer escudo ) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.velocidad = velocidad;
        this.escudo = escudo;
    }
}
