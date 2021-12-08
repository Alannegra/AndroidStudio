package com.company.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Elemento {
    @PrimaryKey(autoGenerate = true)
    int id;

    String nombre;
    /*String descripcion;*/
    float valoracion;
    int vida;
    int ataque;
    int velocidad;

    /*public Elemento(String nombre, String descripcion, Integer vida, Integer ataque ) {*/
    public Elemento(String nombre, Integer vida, Integer ataque, Integer velocidad ) {
        this.nombre = nombre;
        /*this.descripcion = descripcion;*/
        this.vida = vida;
        this.ataque = ataque;
        this.velocidad = velocidad;
    }
}
