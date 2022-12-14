package com.isaac012.examenllamada.modelos;

import java.util.List;

public class DataListResponse {
    private List<Data> data;

    public DataListResponse(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
