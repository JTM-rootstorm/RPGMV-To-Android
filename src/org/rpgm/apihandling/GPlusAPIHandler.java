package org.rpgm.apihandling;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;

import org.rpgm.main.MainActivity;

public class GPlusAPIHandler extends BaseGameActivity {
    private boolean signedIn = false;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        startGame();
    }

    @Override
    public void onSignInSucceeded(){
        signedIn = true;
    }

    @Override
    public void onSignInFailed(){
        signedIn = false;
    }

    private void startGame(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
