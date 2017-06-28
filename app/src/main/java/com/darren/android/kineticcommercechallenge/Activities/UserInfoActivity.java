package com.darren.android.kineticcommercechallenge.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.darren.android.kineticcommercechallenge.DataModels.Location;
import com.darren.android.kineticcommercechallenge.DataModels.Name;
import com.darren.android.kineticcommercechallenge.DataModels.Picture;
import com.darren.android.kineticcommercechallenge.R;
import com.darren.android.kineticcommercechallenge.Utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoActivity extends AppCompatActivity {

    private Name userName;
    private Location userLocation;
    private Picture userPicturel;
    private String userEmail;
    private String userPhone;
    private String userCell;

    @BindView(R.id.sign_up_toolbar)
    Toolbar signUpToolbar;
    @BindView(R.id.userMediumImageView)
    ImageView userMediumIV;
    @BindView(R.id.userNameTextView)
    TextView userNameTV;
    @BindView(R.id.userPhoneTextView)
    TextView userPhoneTV;
    @BindView(R.id.userCellTextView)
    TextView userCellTV;
    @BindView(R.id.userAddressTextView)
    TextView userAddressTV;
    @BindView(R.id.userEmailTextView)
    TextView userEmailTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);

        setSupportActionBar(signUpToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            userName = bundle.getParcelable(Constants.USER_NAME_TAG);
            userLocation = bundle.getParcelable(Constants.USER_LOCATION_TAG);
            userPicturel = bundle.getParcelable(Constants.USER_PICTURE_TAG);
            userEmail = bundle.getString(Constants.USER_EMAIL_TAG);
            userPhone = bundle.getString(Constants.USER_PHONE_TAG);
            userCell = bundle.getString(Constants.USER_CELL_TAG);
        }

        if(userPicturel != null){
            Glide.with(this).load(userPicturel.getMedium()).into(userMediumIV);
        }
        userNameTV.setText(userName != null ? capitalize(userName.getTitle()) + " " + capitalize(userName.getFirst()) + " " + capitalize(userName.getLast()) : "");
        userAddressTV.setText(userLocation != null ? capitalize(userLocation.getStreet()) + ", " + capitalize(userLocation.getCity()) + ", " + capitalize(userLocation.getState()) + ", " + capitalize(userLocation.getPostcode()) : "");
        userEmailTV.setText(userEmail != null ? userEmail : "");
        userPhoneTV.setText(userPhone != null ? userPhone : "");
        userCellTV.setText(userCell != null ? userCell : "");
    }

    //Add navigation back button on ToolBar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private String capitalize(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
