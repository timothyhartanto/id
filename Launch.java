package com.example.proto.launcher;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

public class Launch extends FragmentActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);
    }

    public void launchAppTwitter(View v){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/intent/follow?screen_name=NASA"));
                    //Uri.parse("twitter://user?screen_name=NASA"));
                    //Uri.parse("twitter://user?screen_name=timothyhendrik"));
            startActivity(intent);

        }catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/intent/follow/NASA")));
                    //Uri.parse("https://twitter.com/#!/timothyhendrik")));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void launchApp(View v){
        //first way
        Intent facebookIntent = getOpenFacebookIntent(this);
        startActivity(facebookIntent);

//        Session session = Session.getActiveSession();
//        Request request = Request.newGraphPathRequest(session, "me", null);
//        com.facebook.Response response = Request.executeAndWait(request);

        //second way
            //String uri = "https://www.facebook.com/timmy.hartanto";
//        String uri = "fb://facewebmodal/f?href=https://www.facebook.com/michael.chua.1428"; // put the profile id here
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//
//        startActivity(intent);

        //third way
//        try {
//            PackageManager manager = this.getPackageManager();
//            Intent i = manager.getLaunchIntentForPackage("com.facebook.katana");
//
//            if (i == null) {
//                //throw new PackageManager.NameNotFoundException();
//            }
//            i.addCategory(Intent.CATEGORY_LAUNCHER);
//            this.startActivity(i);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://profile/timmy.hartanto")); //Trys to make intent with FB's URI /254175194653125
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://profile/timmy.hartanto"));
//            return new Intent(Intent.ACTION_VIEW,
//                    Uri.parse("https://www.facebook.com/timmy.hartanto")); //catches and opens a url to the desired page
        }
    }



}
