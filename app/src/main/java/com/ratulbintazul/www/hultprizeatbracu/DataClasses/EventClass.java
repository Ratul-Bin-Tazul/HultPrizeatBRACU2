
package com.ratulbintazul.www.hultprizeatbracu.DataClasses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventClass {

    @SerializedName("data")
    @Expose
    private List<EventData> data = null;
    @SerializedName("paging")
    @Expose
    private Paging paging;

    public List<EventData> getData() {
        return data;
    }

    public void setData(List<EventData> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

}
