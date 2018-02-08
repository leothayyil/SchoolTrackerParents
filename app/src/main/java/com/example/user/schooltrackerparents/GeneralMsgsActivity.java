package com.example.user.schooltrackerparents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import com.example.user.schooltrackerparents.Adapter.GM_Adapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class GeneralMsgsActivity extends AppCompatActivity {


    GM_Adapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expListView = (ExpandableListView) findViewById(R.id.gm_expandable);

        prepareListData();

        listAdapter = new GM_Adapter(this, listDataHeader, listDataChild);

        expListView.setAdapter(listAdapter);
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

}

//        GeneralMsgs  generalMsgs=new GeneralMsgs();
//        generalMsgs.execute();

//    }
    /*
    public class GeneralMsgs extends AsyncTask{

        String action="general_message";
        String user_id="1";
        String classa="7";
        String division="7";

        @Override
        protected Object doInBackground(Object[] objects) {
            new RetrofitHelper(GeneralMsgsActivity.this).getApi().getGeneralMsg(action,user_id,classa,division).enqueue(
                    new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            try {

                                JSONObject jsonObject=new JSONObject(response.body().toString());
                                String status=jsonObject.getString("status");
                                JSONArray jsonArray=jsonObject.getJSONArray("general_message");
                                for (int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                                    String title=jsonObject1.getString("title");
                                    String message=jsonObject1.getString("message");
                                    titleArray.add(title);
                                    child.add(message);
                                    _listDataChild.put(titleArray.get(i),child);

                                    GM_Adapter gmAdapter=new GM_Adapter(titleArray,_listDataChild);
                                    expandable.setAdapter(gmAdapter);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {

                        }
                    }
            );
            return null;
        }

    }
    */
//}
