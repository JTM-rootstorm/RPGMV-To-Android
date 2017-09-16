package org.rpgm.apihandling;

public class GPlusAPIHandler {

    private GPlusAPIActivity gPlusAPIActivity;

    public GPlusAPIHandler(GPlusAPIActivity apiClient){
        gPlusAPIActivity = apiClient;
    }

    @android.webkit.JavascriptInterface
    public void showAchievementWindow(){
        gPlusAPIActivity.showAchievementWindow();
    }
}
