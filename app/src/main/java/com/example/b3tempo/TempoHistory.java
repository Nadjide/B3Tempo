package com.example.b3tempo;

import java.util.Date;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TempoHistory {

    @SerializedName("dates")
    @Expose
    private List<TempoDate> dates;

    public List<TempoDate> getTempoDates() {
        return dates;
    }

    public void setTempoDates(List<TempoDate> dates) {
        this.dates = dates;
    }

}