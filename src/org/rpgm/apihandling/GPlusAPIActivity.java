package org.rpgm.apihandling;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;

import org.rpgm.main.MainActivity;

public class GPlusAPIActivity extends BaseGameActivity {

    @Override
    public void onCreate(Bundle savedInstance){
        getGameHelper().setConnectOnStart(true);
        super.onCreate(savedInstance);

        MainActivity.setAPIClient(this);
        startGame();
    }

    @Override
    public void onSignInSucceeded(){

    }

    @Override
    public void onSignInFailed(){

    }

    private void startGame(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void showAchievementWindow(){
        if(isSignedIn()){
            startActivityForResult(Games.Achievements.getAchievementsIntent(getApiClient()), 1);
        }
    }

    public void unlockAchievement(String achievementID){
        if(isSignedIn()){
            Games.Achievements.unlock(getApiClient(), achievementID);
        }
    }
}
