package com.unisob.vclibs.activties;

import static com.unisob.vclibs.mads.AppManage.ADMOB_B;
import static com.unisob.vclibs.mads.AppManage.ADMOB_N0;
import static com.unisob.vclibs.mads.AppManage.Both_video_show;
import static com.unisob.vclibs.mads.AppManage.FACEBOOK_N;
import static com.unisob.vclibs.mads.AppManage.FACEBOOK_NB;
import static com.unisob.vclibs.mads.AppManage.False_Video_Show;
import static com.unisob.vclibs.mads.AppManage.True_Video_Show;
import static com.unisob.vclibs.mads.AppManage.app_privacyPolicyLink;
import static com.unisob.vclibs.mads.AppManage.maxvidcount;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.loopj.android.http.RequestParams;
import com.unisob.vclibs.R;
import com.unisob.vclibs.httprequest.GetRequestList;
import com.unisob.vclibs.mads.AppManage;
import com.unisob.vclibs.models.MoreApp;
import com.unisob.vclibs.unitlity.TestActivity_1;
import com.unisob.vclibs.unitlity.TestActivity_10;
import com.unisob.vclibs.unitlity.TestActivity_2;
import com.unisob.vclibs.unitlity.TestActivity_3;
import com.unisob.vclibs.unitlity.TestActivity_4;
import com.unisob.vclibs.unitlity.TestActivity_5;
import com.unisob.vclibs.unitlity.TestActivity_6;
import com.unisob.vclibs.unitlity.TestActivity_7;
import com.unisob.vclibs.unitlity.TestActivity_8;
import com.unisob.vclibs.unitlity.TestActivity_9;
import com.unisob.vclibs.utility.Connectivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomePageActivity extends AppCompatActivity implements GetRequestList.HttpGetResponsable {

    private List<MoreApp> moreAppList = new ArrayList<>();

    public static int counter = 0;
    public static int crandomcounter = 0;

    LinearLayout call_timer_layout;
    TextView timer;
    CountDownTimer myCountdownTimer;

    public static int min = 1;
    public static int max = 11;
    public static int randomposition = 0;
    public static int LP = 0;
    public static SharedPreferences sh;

    GradientDrawable gd1,gd2;


    private String mColors[] = {
            "#39add1", // light blue [0]
            "#3079ab", // dark blue [1]
            "#c25975", // mauve [2]
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    private String mColors2[] = {
            "#3079ab", //dark blue  [0]
            "#39add1", // light blue [1]
            "#e0ab18", // mustard [2]
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7",  // light gray
            "#c25975", // mauve
            "#51b46d", // green
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
    };


    public int[] getGcolors() {
        String color = "";
        String color2 = "";

        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mColors.length);

        color = mColors[randomNumber];
        color2 = mColors2[randomNumber];
        int colorAsInt = Color.parseColor(color);
        int colorAsInt2 = Color.parseColor(color2);

        return new int[]{colorAsInt, colorAsInt2};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        LP = sh.getInt("layout_position", 0);
        randomposition = LP;

        if (randomposition == 0) {
            randomposition = new Random().nextInt((max - min) + 1) + min;
            SharedPreferences.Editor editor = sh.edit();
            editor.putInt("layout_position", randomposition);
            editor.apply();
            LP = randomposition;
        }

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAGS_CHANGED);
        window.setStatusBarColor(Color.WHITE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        }

        call_timer_layout = findViewById(R.id.call_timer_layout);
        timer = findViewById(R.id.timer);

        int[] gColors = getGcolors();
        gd1 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{gColors[0], gColors[1]});
        gd1.setCornerRadius(0f);
        findViewById(R.id.lnrsbg).setBackground(gd1);

        int[] gColors2 = getGcolors();
        gd2 = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{gColors2[0], gColors2[1]});
        gd2.setCornerRadius(20f);
        findViewById(R.id.btn_click).setBackground(gd2);
        findViewById(R.id.btn_click2).setBackground(gd2);


        final int cmin = 1;
        final int cmax = 5;
        final int radmserver1 = new Random().nextInt((cmax - cmin) + 1) + cmin;

        final int cmin2 = 6;
        final int cmax2 = 9;
        final int radmserver2 = new Random().nextInt((cmax2 - cmin2) + 1) + cmin2;

        TextView server1 = findViewById(R.id.server1);
        server1.setText("Server " + radmserver1);

        TextView server2 = findViewById(R.id.server2);
        server2.setText("Server " + radmserver2);

        findViewById(R.id.privacypolicy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(app_privacyPolicyLink.contains("blogspot.com")){
                    startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(app_privacyPolicyLink)));
                }else{
                    Intent intent = new Intent(HomePageActivity.this, PravacyPolicyActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        Intent intent = getIntent();
        if (intent.getBooleanExtra("rate", false)) {

            findViewById(R.id.btn_click).setVisibility(View.GONE);
            findViewById(R.id.btn_click2).setVisibility(View.GONE);
            findViewById(R.id.txtConnect).setVisibility(View.GONE);
            call_timer_layout.setVisibility(View.VISIBLE);


            SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
            Boolean rate_state = prefs.getBoolean("rate_state", false);

            if (rate_state == false) {
                Rate();
            } else {
                myCountdownTimer = new CountDownTimer(7000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        timer.setText("00:0" + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        myCountdownTimer.cancel();
                        findViewById(R.id.btn_click).setVisibility(View.VISIBLE);
                        findViewById(R.id.btn_click2).setVisibility(View.VISIBLE);
                        findViewById(R.id.txtConnect).setVisibility(View.VISIBLE);
                        call_timer_layout.setVisibility(View.GONE);
                    }
                }.start();

                myCountdownTimer.start();
            }
        }


        goToMain();

        counter = sh.getInt("counter", 0);
        crandomcounter = sh.getInt("crandomcounter", 0);

        AppManage.getInstance(this).showNativeBanner((ViewGroup) findViewById(R.id.banner_container), ADMOB_B[0], FACEBOOK_NB[0]);
        AppManage.getInstance(this).showNative((ViewGroup) findViewById(R.id.native_container), ADMOB_N0, FACEBOOK_N[0]);

        findViewById(R.id.backs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppManage.getInstance(HomePageActivity.this).showInterstitialAd(HomePageActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Next_Activity();
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });

        findViewById(R.id.btn_click2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppManage.getInstance(HomePageActivity.this).showInterstitialAd(HomePageActivity.this, new AppManage.MyCallback() {
                    public void callbackCall() {
                        Next_Activity();
                    }
                }, "", AppManage.app_mainClickCntSwAd);
            }
        });
        getListApps();
    }

    public void Next_Activity() {
        if (False_Video_Show.equals("true")) {
            final int min = 1;
            final int max = maxvidcount;
            final int randomposition = new Random().nextInt((max - min) + 1) + min;
            Intent countrylist = new Intent(HomePageActivity.this, VideoPlayerActivity.class);
            countrylist.putExtra("title", (moreAppList.get(randomposition).getUrl()));
            startActivity(countrylist);
            finish();
        } else if (Both_video_show.equals("true")) {
            counter++;
            if (crandomcounter == 0) {
                final int cmin = 3;
                final int cmax = 8;
                crandomcounter = new Random().nextInt((cmax - cmin) + 1) + cmin;
                SharedPreferences.Editor Editor = sh.edit();
                Editor.putInt("crandomcounter", crandomcounter);
                Editor.putInt("counter", counter);
                Editor.apply();
                Test_Activity_Lyout(HomePageActivity.this, LP);

            } else {
                if (crandomcounter == counter) {
                    crandomcounter = 0;
                    counter = 0;
                    SharedPreferences.Editor Editor = sh.edit();
                    Editor.putInt("counter", counter);
                    Editor.putInt("crandomcounter", crandomcounter);
                    Editor.apply();
                    final int min = 1;
                    final int max = maxvidcount;
                    final int randomposition = new Random().nextInt((max - min) + 1) + min;
                    Intent countrylist = new Intent(HomePageActivity.this, VideoPlayerActivity.class);
                    countrylist.putExtra("title", (moreAppList.get(randomposition).getUrl()));
                    startActivity(countrylist);
                    finish();
                } else {
                    SharedPreferences.Editor Editor = sh.edit();
                    Editor.putInt("counter", counter);
                    Editor.apply();
                    Test_Activity_Lyout(HomePageActivity.this, LP);
                }
            }


        } else if (True_Video_Show.equals("true")) {
            Test_Activity_Lyout(HomePageActivity.this, 1);
        } else {
            Test_Activity_Lyout(HomePageActivity.this, LP);
        }
    }

    public static void Test_Activity_Lyout(Activity activity, int position) {
        switch (position) {
            case 1:
                Intent i1 = new Intent(activity, TestActivity_1.class);
                activity.startActivity(i1);
                activity.finish();
                break;

            case 2:
                Intent i2 = new Intent(activity, TestActivity_2.class);
                activity.startActivity(i2);
                activity.finish();
                break;

            case 3:
                Intent i3 = new Intent(activity, TestActivity_3.class);
                activity.startActivity(i3);
                activity.finish();
                break;

            case 4:
                Intent i4 = new Intent(activity, TestActivity_4.class);
                activity.startActivity(i4);
                activity.finish();
                break;

            case 5:
                Intent i5 = new Intent(activity, TestActivity_5.class);
                activity.startActivity(i5);
                activity.finish();
                break;

            case 6:
                Intent i6 = new Intent(activity, TestActivity_6.class);
                activity.startActivity(i6);
                activity.finish();
                break;

            case 7:
                Intent i7 = new Intent(activity, TestActivity_7.class);
                activity.startActivity(i7);
                activity.finish();
                break;

            case 8:
                Intent i8 = new Intent(activity, TestActivity_8.class);
                activity.startActivity(i8);
                activity.finish();
                break;

            case 9:
                Intent i9 = new Intent(activity, TestActivity_9.class);
                activity.startActivity(i9);
                activity.finish();
                break;

            case 10:
                Intent i10 = new Intent(activity, TestActivity_10.class);
                activity.startActivity(i10);
                activity.finish();
                break;

            default:
                Intent i0 = new Intent(activity, TestActivity_1.class);
                activity.startActivity(i0);
                activity.finish();
                break;
        }

    }

    private void getListApps() {
        if (Connectivity.isConnected(this)) {
            RequestParams requestParams = new RequestParams();
            GetRequestList getRequest = new GetRequestList(this);
            getRequest.sendRequest("www.aws.com", requestParams, "datalist");
        } else {
            ConnectionEroor();
        }
    }

    public void ConnectionEroor() {
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(this).create();
        alertDialog.setTitle("Oops!!! Connection Error!");
        alertDialog.setMessage("Please check your internet connection");
        alertDialog.setIcon(R.drawable.ic_warning);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alertDialog.show();
    }

    @Override
    public void onHttpGetResponse(JSONObject jsonObject, String httpurl) {
        try {
            if (httpurl.equals("datalist")) {
                JSONArray jsonArray = jsonObject.getJSONArray("message");
                int size1 = jsonArray.length();
                for (int i = 0; i < size1; i++) {
                    JSONObject j1 = (JSONObject) jsonArray.get(i);
                    MoreApp categoryDetail = new MoreApp();
                    categoryDetail.setName(j1.getString("id"));
                    categoryDetail.setUrl(j1.getString("link"));
                    moreAppList.add(categoryDetail);
                }
            } else {
                Toast.makeText(this, getString(R.string.err_somethingwentwrong), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        AppManage.getInstance(HomePageActivity.this).showInterstitialAd(HomePageActivity.this, new AppManage.MyCallback() {
            public void callbackCall() {
                finish();
            }
        }, "", AppManage.app_innerClickCntSwAd);
    }

    private void AppPermissions() {
        if (!allPermissions() || !system_sto() || !statePermissions() || !CAMERA() || !CHANGE_NETWORK_STATE() || !MODIFY_AUDIO_SETTINGS() || !RECORD_AUDIO() || !BLUETOOTH() /*|| !WRITE_EXTERNAL_STORAGE()*/ || !CAPTURE_VIDEO_OUTPUT() /*|| !READ_EXTERNAL_STORAGE()*/) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.CAMERA", "android.permission.SYSTEM_ALERT_WINDOW", /*"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION",*/ "android.permission.CHANGE_NETWORK_STATE", "android.permission.MODIFY_AUDIO_SETTINGS", "android.permission.RECORD_AUDIO", "android.permission.BLUETOOTH", "android.permission.INTERNET", /*"android.permission.WRITE_EXTERNAL_STORAGE",*/ "android.permission.ACCESS_NETWORK_STATE", "android.permission.CAPTURE_VIDEO_OUTPUT"/*, "android.permission.READ_EXTERNAL_STORAGE"*/}, 1);
        }
    }

    private boolean allPermissions() {
        return ContextCompat.checkSelfPermission(HomePageActivity.this, "android.permission.INTERNET") == 0;
    }

    private boolean statePermissions() {
        return ContextCompat.checkSelfPermission(HomePageActivity.this, "android.permission.ACCESS_NETWORK_STATE") == 0;
    }

    private boolean CAMERA() {
        return ContextCompat.checkSelfPermission(HomePageActivity.this, "android.permission.CAMERA") == 0;
    }

    private boolean CHANGE_NETWORK_STATE() {
        return ContextCompat.checkSelfPermission(HomePageActivity.this, "android.permission.CHANGE_NETWORK_STATE") == 0;
    }

    private boolean MODIFY_AUDIO_SETTINGS() {
        return ContextCompat.checkSelfPermission(HomePageActivity.this, "android.permission.MODIFY_AUDIO_SETTINGS") == 0;
    }

    private boolean RECORD_AUDIO() {
        return ContextCompat.checkSelfPermission(HomePageActivity.this, "android.permission.RECORD_AUDIO") == 0;
    }

    private boolean BLUETOOTH() {
        return ContextCompat.checkSelfPermission(HomePageActivity.this, "android.permission.BLUETOOTH") == 0;
    }

    private boolean system_sto() {
        return ContextCompat.checkSelfPermission(HomePageActivity.this, "android.permission.SYSTEM_ALERT_WINDOW") == 0;
    }

    private boolean CAPTURE_VIDEO_OUTPUT() {
        return ContextCompat.checkSelfPermission(HomePageActivity.this, "android.permission.CAPTURE_VIDEO_OUTPUT") == 0;
    }

    public void goToMain() {
        AppPermissions();
    }

    public void Rate() {

        Dialog dialog = new Dialog(this, com.unisob.vclibs.R.style.Transparent);
        dialog.setContentView(com.unisob.vclibs.R.layout.rate_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        RatingBar simpleRatingBar = (RatingBar) dialog.findViewById(R.id.rb_stars);
        ((LinearLayout) dialog.findViewById(com.unisob.vclibs.R.id.bt_later)).setBackground(gd2);
        ((LinearLayout) dialog.findViewById(com.unisob.vclibs.R.id.bt_later)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (simpleRatingBar.getRating() == 1 || simpleRatingBar.getRating() == 2 || simpleRatingBar.getRating() == 3 || simpleRatingBar.getRating() == 4) {

                    findViewById(R.id.btn_click).setVisibility(View.VISIBLE);
                    findViewById(R.id.btn_click2).setVisibility(View.VISIBLE);
                    findViewById(R.id.txtConnect).setVisibility(View.VISIBLE);

                    call_timer_layout.setVisibility(View.GONE);

                    SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
                    editor.putBoolean("rate_state", true);
                    editor.apply();

                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{"sdenterprise0610@gmail.com"});
                    email.putExtra(Intent.EXTRA_SUBJECT, " User Feedback");
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                    dialog.dismiss();

                } else if (simpleRatingBar.getRating() == 5) {
                    findViewById(R.id.btn_click).setVisibility(View.VISIBLE);
                    findViewById(R.id.btn_click2).setVisibility(View.VISIBLE);
                    findViewById(R.id.txtConnect).setVisibility(View.VISIBLE);

                    call_timer_layout.setVisibility(View.GONE);
                    SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
                    editor.putBoolean("rate_state", true);
                    editor.apply();

                    startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                    dialog.dismiss();
                } else {
                    Toast.makeText(HomePageActivity.this, "Please Select Stars", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ((LinearLayout) dialog.findViewById(com.unisob.vclibs.R.id.Maybe)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                myCountdownTimer = new CountDownTimer(7000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        timer.setText("00:0" + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        myCountdownTimer.cancel();
                        findViewById(R.id.btn_click).setVisibility(View.VISIBLE);
                        findViewById(R.id.btn_click2).setVisibility(View.VISIBLE);
                        findViewById(R.id.txtConnect).setVisibility(View.VISIBLE);

                        call_timer_layout.setVisibility(View.GONE);
                    }
                }.start();

                myCountdownTimer.start();
            }
        });
        dialog.show();
    }
}

