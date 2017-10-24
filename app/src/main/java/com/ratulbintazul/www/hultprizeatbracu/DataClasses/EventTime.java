
package com.ratulbintazul.www.hultprizeatbracu.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventTime {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("start_time")
    @Expose
    private String startTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

}
