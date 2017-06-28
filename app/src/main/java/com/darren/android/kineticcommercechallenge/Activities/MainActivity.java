package com.darren.android.kineticcommercechallenge.Activities;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.darren.android.kineticcommercechallenge.Adapters.UserListAdapter;
import com.darren.android.kineticcommercechallenge.DataModels.RandomUserResponse;
import com.darren.android.kineticcommercechallenge.DataModels.Result;
import com.darren.android.kineticcommercechallenge.R;
import com.darren.android.kineticcommercechallenge.Services.UserClient;
import com.darren.android.kineticcommercechallenge.Utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String RECYCLER_STATE_TAG = "recycler_state";
    private static final String USER_RESULT_TAG = "user_result";

    private ArrayList<Result> userResult;
    private UserListAdapter userListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private Parcelable recyclerViewState;

    @BindView(R.id.indeterminateBar)
    ProgressBar indeterminateBar;
    @BindView(R.id.userBriefInfoRecyclerView)
    RecyclerView userBriefInfoRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (linearLayoutManager == null)
            linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        if(recyclerViewState != null) {
            linearLayoutManager.onRestoreInstanceState(recyclerViewState);
        }
        if (userResult == null) {
            userResult = new ArrayList<>();
            getRandomUser();
        }
        if(userListAdapter == null)
            userListAdapter = new UserListAdapter(this, userResult);
        userBriefInfoRV.setLayoutManager(linearLayoutManager);
        userBriefInfoRV.setAdapter(userListAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        recyclerViewState = linearLayoutManager.onSaveInstanceState();
        bundle.putParcelable(RECYCLER_STATE_TAG, recyclerViewState);
        bundle.putParcelableArrayList(USER_RESULT_TAG, userResult);
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if(bundle != null) {
            recyclerViewState = bundle.getParcelable(RECYCLER_STATE_TAG);
            userResult = bundle.getParcelableArrayList(USER_RESULT_TAG);
        }
    }

    private void getRandomUser() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Constants.randomUserBaseURL)
                .addConverterFactory(GsonConverterFactory.create());

        UserClient userClient = builder.build().create(UserClient.class);
        Call<RandomUserResponse> call = userClient.repositoryOfUsers("json", 30);
        indeterminateBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<RandomUserResponse>() {
            @Override
            public void onResponse(Call<RandomUserResponse> call, Response<RandomUserResponse> response) {

                RandomUserResponse randomUserResponse = response.body();

                for(Result result : randomUserResponse.getResults()) {
                    userResult.add(result);
                    userListAdapter.notifyDataSetChanged();
                }
                indeterminateBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<RandomUserResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage().toString());
                indeterminateBar.setVisibility(View.GONE);
            }
        });
    }
}
