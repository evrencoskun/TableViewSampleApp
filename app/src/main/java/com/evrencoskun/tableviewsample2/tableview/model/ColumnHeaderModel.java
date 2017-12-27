package com.evrencoskun.tableviewsample2.tableview.model;

/**
 * Created by evrencoskun on 27.11.2017.
 */

public class ColumnHeaderModel {
    // Sort states
    public static final int ASCENDING = 1;
    public static final int DESCENDING = 2;
    public static final int CLEAR = 3;

    private int mSortState = CLEAR;

    private String mData;

    public ColumnHeaderModel(String mData) {
        this.mData = mData;
    }

    public String getData() {
        return mData;
    }

    public void setSortState(int p_nSortState) {
        this.mSortState = p_nSortState;
    }

    public int getSortState() {
        return mSortState;
    }
}
