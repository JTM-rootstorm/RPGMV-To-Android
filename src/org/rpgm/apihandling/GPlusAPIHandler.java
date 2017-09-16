package org.rpgm.apihandling;

import org.rpgm.main.MainActivity;

public class GPlusAPIHandler {

    @android.webkit.JavascriptInterface
    public void showAchievementWindow(){
        MainActivity.getgPlusAPIActivity().showAchievementWindow();
    }
}
