package com.evrencoskun.tableviewsample2.json;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.evrencoskun.tableviewsample2.MainFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evrencoskun on 2.12.2017.
 */

public class WebServiceHandler {
    private static final String URL = "http://vuetable.ratiw.net/api/users?per_page=40";
    private MainFragment mainFragment;

    public WebServiceHandler(MainFragment mainFragment) {
        this.mainFragment = mainFragment;
    }

    public void loadUserInfoList() {
        mainFragment.showProgressDialog();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new
                Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                List<UserInfo> userInfoList = new ArrayList<>();

                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        //Log.e(LOG_TAG, "json object : " + object.toString());

                        UserInfo userInfo = createUserInfoFromJSONObject(object);
                        // Add created cell model
                        userInfoList.add(userInfo);
                    }

                    // we 've got whole user info data. So let's populate the tableView
                    mainFragment.populatedTableView(userInfoList);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mainFragment.hideProgressDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                mainFragment.hideProgressDialog();
            }
        }) {};

        RequestQueue requestQueue = Volley.newRequestQueue(mainFragment.getActivity());
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy
                .DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);

        requestQueue.add(jsObjRequest);
    }


    private UserInfo createUserInfoFromJSONObject(JSONObject object) throws JSONException {
        UserInfo cellModel = new UserInfo();

        cellModel.setId(object.getInt("id"));
        cellModel.setName(object.getString("name"));
        cellModel.setNickName(object.getString("nickname"));
        cellModel.setEmail(object.getString("email"));
        cellModel.setBirthDay(object.getString("birthdate"));
        cellModel.setGender(object.getString("gender"));

        cellModel.setSalary(object.getInt("salary"));
        cellModel.setCreatedAt(object.getString("created_at"));
        cellModel.setUpdatedAt(object.getString("updated_at"));
        cellModel.setAge(object.getInt("age"));

        // Job
        JSONObject groupObject = object.getJSONObject("group");
        cellModel.setJob(groupObject.getString("description"));

        // Address
        JSONObject addressObject = object.getJSONObject("address");
        cellModel.setAddress(addressObject.getString("line1"));
        cellModel.setZipCode(addressObject.getString("zipcode"));
        cellModel.setMobile(addressObject.getString("mobile"));
        cellModel.setFax(addressObject.getString("fax"));

        return cellModel;
    }

}
