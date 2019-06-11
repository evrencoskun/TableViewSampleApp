package com.evrencoskun.tableviewsample2.ui;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.evrencoskun.tableview.TableView;
import com.evrencoskun.tableviewsample2.R;
import com.evrencoskun.tableviewsample2.model.ServiceRequest;
import com.evrencoskun.tableviewsample2.ui.tableview.MyTableAdapter;
import com.evrencoskun.tableviewsample2.ui.tableview.MyTableViewListener;
import com.evrencoskun.tableviewsample2.ui.viewmodel.MainViewModel;
import com.evrencoskun.tableviewsample2.ui.viewmodel.MainViewModelFactory;
import com.evrencoskun.tableviewsample2.utility.InjectorUtils;

public class MainFragment extends Fragment {

    private static final String LOG_TAG = MainFragment.class.getSimpleName();

    private TableView mTableView;
    private MyTableAdapter mTableAdapter;
    private ProgressBar mProgressBar;
    private MainViewModel vMainViewModel;

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

        mProgressBar = view.findViewById(R.id.progressBar);

        mTableView = view.findViewById(R.id.my_TableView);

        initializeTableView(mTableView);


        // initialize ViewModel
        MainViewModelFactory factory = InjectorUtils.getMainViewModelFactory(getActivity().getApplicationContext());
        vMainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);

        vMainViewModel.getUserList().observe(this, users -> {

            if(users != null && users.size()>0){
                // set the list on TableViewModel
                mTableAdapter.setUserList(users);

                hideProgressBar();
            }
        });

        // Let's post a request to get the User data from a web server.
        postRequest();

        return view;
    }


    private void initializeTableView(TableView tableView){

        // Create TableView Adapter
        mTableAdapter = new MyTableAdapter(getContext());
        tableView.setAdapter(mTableAdapter);

        // Create listener
        tableView.setTableViewListener(new MyTableViewListener(tableView));
    }


    private void postRequest(){
        int size = 100; // this is the count of the data items.
        int page = 1; // Which page do we want to get from the server.
        ServiceRequest serviceRequest = new ServiceRequest(size, page);
        vMainViewModel.postRequest(serviceRequest);

        showProgressBar();
    }


    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mTableView.setVisibility(View.INVISIBLE);
    }

    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mTableView.setVisibility(View.VISIBLE);
    }
}
