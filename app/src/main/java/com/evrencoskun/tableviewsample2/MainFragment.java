package com.evrencoskun.tableviewsample2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableviewsample2.json.UserInfo;
import com.evrencoskun.tableviewsample2.json.WebServiceHandler;
import com.evrencoskun.tableviewsample2.tableview.MyTableAdapter;
import com.evrencoskun.tableviewsample2.tableview.MyTableViewListener;
import com.evrencoskun.tableviewsample2.tableview.model.CellModel;
import com.evrencoskun.tableviewsample2.tableview.model.ColumnHeaderModel;
import com.evrencoskun.tableviewsample2.tableview.model.RowHeaderModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private static final String LOG_TAG = MainFragment.class.getSimpleName();

    private TableView mTableView;
    private MyTableAdapter mTableAdapter;

    private ProgressDialog mProgressDialog;
    private WebServiceHandler mWebServiceHandler;

    // For TableView
    private List<List<CellModel>> mCellList;
    private List<ColumnHeaderModel> mColumnHeaderList;
    private List<RowHeaderModel> mRowHeaderList;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mTableView = (TableView) view.findViewById(R.id.my_TableView);

        // Create TableView Adapter
        mTableAdapter = new MyTableAdapter(getContext());
        mTableView.setAdapter(mTableAdapter);

        // Create listener
        mTableView.setTableViewListener(new MyTableViewListener(mTableView));

        // UserInfo data will be getting from a web server.
        mWebServiceHandler = new WebServiceHandler(this);
        mWebServiceHandler.loadUserInfoList();

        return view;
    }

    public void populatedTableView(List<UserInfo> userInfoList) {
        // create Models
        mColumnHeaderList = createColumnHeaderModelList();
        mCellList = loadCellModelList(userInfoList);
        mRowHeaderList = createRowHeaderList();

        // Set all items to the TableView
        mTableAdapter.setAllItems(mColumnHeaderList, mRowHeaderList, mCellList);
    }


    private List<ColumnHeaderModel> createColumnHeaderModelList() {
        List<ColumnHeaderModel> list = new ArrayList<>();

        // Create Column Headers
        list.add(new ColumnHeaderModel("Id"));
        list.add(new ColumnHeaderModel("Name"));
        list.add(new ColumnHeaderModel("Nickname"));
        list.add(new ColumnHeaderModel("Email"));
        list.add(new ColumnHeaderModel("Birthday"));
        list.add(new ColumnHeaderModel("Sex"));
        list.add(new ColumnHeaderModel("Age"));
        list.add(new ColumnHeaderModel("Job"));
        list.add(new ColumnHeaderModel("Salary"));
        list.add(new ColumnHeaderModel("CreatedAt"));
        list.add(new ColumnHeaderModel("UpdatedAt"));
        list.add(new ColumnHeaderModel("Address"));
        list.add(new ColumnHeaderModel("Zip Code"));
        list.add(new ColumnHeaderModel("Phone"));
        list.add(new ColumnHeaderModel("Fax"));

        return list;
    }

    private List<List<CellModel>> loadCellModelList(List<UserInfo> userInfoList) {
        List<List<CellModel>> lists = new ArrayList<>();

        // Creating cell model list from UserInfo list for Cell Items
        // In this example, UserInfo list is populated from web service

        for (int i = 0; i < userInfoList.size(); i++) {
            UserInfo userInfo = userInfoList.get(i);

            List<CellModel> list = new ArrayList<>();

            // The order should be same with column header list;
            list.add(new CellModel("1-" + i, userInfo.getId()));       // "Id"
            list.add(new CellModel("2-" + i, userInfo.getName()));     // "Name"
            list.add(new CellModel("3-" + i, userInfo.getNickName())); // "Nickname"
            list.add(new CellModel("4-" + i, userInfo.getEmail()));    // "Email"
            list.add(new CellModel("5-" + i, userInfo.getBirthDay())); // "BirthDay"
            list.add(new CellModel("6-" + i, userInfo.getGender()));   // "Gender"
            list.add(new CellModel("7-" + i, userInfo.getAge()));      // "Age"
            list.add(new CellModel("8-" + i, userInfo.getJob()));      // "Job"
            list.add(new CellModel("9-" + i, userInfo.getSalary()));   // "Salary"
            list.add(new CellModel("10-" + i, userInfo.getCreatedAt()));// "CreatedAt"
            list.add(new CellModel("11-" + i, userInfo.getUpdatedAt()));// "UpdatedAt"
            list.add(new CellModel("12-" + i, userInfo.getAddress()));  // "Address"
            list.add(new CellModel("13-" + i, userInfo.getZipCode()));  // "Zip Code"
            list.add(new CellModel("14-" + i, userInfo.getMobile()));   // "Phone"
            list.add(new CellModel("15-" + i, userInfo.getFax()));      // "Fax"

            // Add
            lists.add(list);
        }

        return lists;
    }

    private List<RowHeaderModel> createRowHeaderList() {
        List<RowHeaderModel> list = new ArrayList<>();
        for (int i = 0; i < mCellList.size(); i++) {
            // In this example, Row headers just shows the index of the TableView List.
            list.add(new RowHeaderModel(String.valueOf(i + 1)));
        }
        return list;
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage("Get data, please wait...");
            mProgressDialog.setCancelable(false);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {

        if ((mProgressDialog != null) && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
        mProgressDialog = null;
    }
}
