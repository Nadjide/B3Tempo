package com.example.b3tempo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempoDate {

    @SerializedName("date")
    @Expose
    private TempoColor date;
    @SerializedName("couleur")
    @Expose
    private TempoColor couleur;

    public TempoColor getDate() {
        return date;
    }

    public void setDate(TempoColor date) {
        this.date = date;
    }

    public TempoColor getCouleur() {
        return couleur;
    }

    public void setCouleur(TempoColor couleur) {
        this.couleur = couleur;
    }

    public TempoColor getDateBegin() {
        return date;
    }

    public TempoColor getDateEnd() {
        return date;
    }
}