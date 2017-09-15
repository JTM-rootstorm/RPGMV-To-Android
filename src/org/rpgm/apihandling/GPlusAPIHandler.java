package org.rpgm.apihandling;

import android.os.Bundle;

import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;

public class GPlusAPIHandler extends BaseGameActivity {
    private boolean signedIn = false;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
    }

    @Override
    public void onSignInSucceeded(){
        signedIn = true;
    }

    @Override
    public void onSignInFailed(){
        signedIn = false;
    }

    @android.webkit.JavascriptInterface
    public void showAchievementWindow(){
        if(signedIn){
            startActivityForResult(Games.Achievements.getAchievementsIntent(getApiClient()), 1);
        }
    }

    @android.webkit.JavascriptInterface
    public void unlockAchievement(String id){
        if(signedIn){
            //do stuff
        }
    }

    private enum Achievements{

    }
}
