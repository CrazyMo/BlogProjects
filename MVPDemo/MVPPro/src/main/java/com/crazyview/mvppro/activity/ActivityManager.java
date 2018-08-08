package com.crazyview.mvppro.activity;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Auther: Crazy.Mo
 * DateTime: 2017/9/7 17:10
 * Summary:
 */
public class ActivityManager {
    private static List<Activity> activityList = new ArrayList<Activity>();


    public static void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        }
    }


    public static void removeActivity(Activity activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    //退出app
    public static void finishAllActivity() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        activityList.clear();
    }

    //销毁指定的activity
    public static void finishActivity(Activity activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity);
            activity.finish();
        }
    }
}
