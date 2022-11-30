package com.example.practica_firebase;

import androidx.appcompat.app.AppCompatActivity;

public class Agenda {
    int id;
    String nombre;
    String corre;
    String Telefono;

    public Agenda() {

    }

    public Agenda(int id) {
        this.id = id;
    }

    public Agenda(int id, String nombre, String corre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.corre = corre;
        Telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorre() {
        return corre;
    }

    public void setCorre(String corre) {
        this.corre = corre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }




}
