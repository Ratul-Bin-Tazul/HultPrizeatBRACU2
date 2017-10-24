package com.ratulbintazul.www.hultprizeatbracu.DataClasses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventData {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("place")
    @Expose
    private Place place;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("event_times")
    @Expose
    private List<EventTime> eventTimes = null;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<EventTime> getEventTimes() {
        return eventTimes;
    }

    public void setEventTimes(List<EventTime> eventTimes) {
        this.eventTimes = eventTimes;
    }

}