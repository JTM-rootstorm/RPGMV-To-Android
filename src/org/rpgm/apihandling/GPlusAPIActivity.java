package org.rpgm.apihandling;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameActivity;

import org.rpgm.main.MainActivity;

public class GPlusAPIActivity extends BaseGameActivity {

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        getGameHelper().setConnectOnStart(true);
        startGame();
    }

    @Override
    public void onSignInSucceeded(){

    }

    @Override
    public void onSignInFailed(){

    }

    public void showAchievementWindow(){
        if(isSignedIn()){
            startActivityForResult(Games.Achievements.getAchievementsIntent(getApiClient()), 1);
        }
    }

    private void startGame(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        MainActivity.setAPIActivity(this);
    }
}
