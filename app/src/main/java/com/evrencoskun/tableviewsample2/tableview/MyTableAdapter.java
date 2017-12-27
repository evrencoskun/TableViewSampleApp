package com.evrencoskun.tableviewsample2.tableview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import com.evrencoskun.tableviewsample2.R;
import com.evrencoskun.tableviewsample2.tableview.holder.CellViewHolder;
import com.evrencoskun.tableviewsample2.tableview.holder.ColumnHeaderViewHolder;
import com.evrencoskun.tableviewsample2.tableview.holder.GenderCellViewHolder;
import com.evrencoskun.tableviewsample2.tableview.holder.MoneyCellViewHolder;
import com.evrencoskun.tableviewsample2.tableview.holder.RowHeaderViewHolder;
import com.evrencoskun.tableviewsample2.tableview.model.CellModel;
import com.evrencoskun.tableviewsample2.tableview.model.ColumnHeaderModel;
import com.evrencoskun.tableviewsample2.tableview.model.RowHeaderModel;

/**
 * Created by evrencoskun on 27.11.2017.
 */

public class MyTableAdapter extends AbstractTableAdapter<ColumnHeaderModel, RowHeaderModel,
        CellModel> {

    public MyTableAdapter(Context p_jContext) {
        super(p_jContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout;

        switch (viewType) {
            case GENDER_TYPE:
                // Get gender cell xml Layout
                layout = LayoutInflater.from(m_jContext).inflate(R.layout
                        .tableview_gender_cell_layout, parent, false);

                return new GenderCellViewHolder(layout);


            case MONEY_TYPE:
                // Get money cell xml Layout
                layout = LayoutInflater.from(m_jContext).inflate(R.layout
                        .tableview_money_cell_layout, parent, false);

                // Create the relevant view holder
                return new MoneyCellViewHolder(layout);
            default:
                // Get default Cell xml Layout
                layout = LayoutInflater.from(m_jContext).inflate(R.layout.tableview_cell_layout,
                        parent, false);

                // Create a Cell ViewHolder
                return new CellViewHolder(layout);
        }
    }

    @Override
    public void onBindCellViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nXPosition, int p_nYPosition) {
        CellModel cell = (CellModel) p_jValue;

        if (holder instanceof CellViewHolder) {
            // Get the holder to update cell item text
            ((CellViewHolder) holder).setCellModel(cell, p_nXPosition);

        } else if (holder instanceof GenderCellViewHolder) {
            ((GenderCellViewHolder) holder).setCellModel(cell);
        } else if (holder instanceof MoneyCellViewHolder) {
            ((MoneyCellViewHolder) holder).setCellModel(cell);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(m_jContext).inflate(R.layout
                .tableview_column_header_layout, parent, false);

        return new ColumnHeaderViewHolder(layout, getTableView());
    }

    @Override
    public void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nXPosition) {
        ColumnHeaderModel columnHeader = (ColumnHeaderModel) p_jValue;

        // Get the holder to update cell item text
        ColumnHeaderViewHolder columnHeaderViewHolder = (ColumnHeaderViewHolder) holder;

        columnHeaderViewHolder.setColumnHeaderModel(columnHeader, p_nXPosition);
    }

    @Override
    public RecyclerView.ViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {

        // Get Row Header xml Layout
        View layout = LayoutInflater.from(m_jContext).inflate(R.layout
                .tableview_row_header_layout, parent, false);

        // Create a Row Header ViewHolder
        return new RowHeaderViewHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object p_jValue, int
            p_nYPosition) {

        RowHeaderModel rowHeaderModel = (RowHeaderModel) p_jValue;

        RowHeaderViewHolder rowHeaderViewHolder = (RowHeaderViewHolder) holder;
        rowHeaderViewHolder.row_header_textview.setText(rowHeaderModel.getData());

    }

    @Override
    public View onCreateCornerView() {
        return LayoutInflater.from(m_jContext).inflate(R.layout.tableview_corner_layout, null,
                false);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        // 5. column header is gender.
        if (position == 5) {
            return GENDER_TYPE;
        } else if (position == 8) {
            // 8. column header is Salary.
            return MONEY_TYPE;
        }
        /*
        "Id"
        "Name"
        "Nickname"
        "Email"
        "Birthday"
        "Gender"
        "Age"
        "Job"
        "Salary"
        "CreatedAt"
        "UpdatedAt"
        "Address"
        "Zip Code"
        "Phone"
        "Fax"
         */
        return 0;
    }

    private static final int GENDER_TYPE = 1;
    private static final int MONEY_TYPE = 2;
}
