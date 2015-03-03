package com.github.mathieudebrito.utils;

import android.content.Context;

import com.github.mathieudebrito.utils.Logs;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Google Analytics static tool
 */
public class Analytics {
    // Events
    public static final String CATEGORY_APP_EVENT = "app_event";

    // Action
    public static final String ACTION_APP_UPDATE = "app_update";
    public static final String ACTION_CLICK = "Click";
    public static final String ACTION_VALUE = "Value";
    public static final String ACTION_SWIPE = "Swipe";
    public static final String LABEL_MENU = "Menu";
    public static final String LABEL_BUTTON = "Button";
    public static final String LABEL_SLIDER = "Slider";

    protected Tracker tracker;
    protected Context context;

    public Analytics(Context context, int xmlTracker) {
        this.context = context;
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
        tracker = analytics.newTracker(xmlTracker);
    }

    /**
     * Send a page view event
     *
     * @param screenName the name of the page
     */
    public void view(String screenName) {
        Logs.debug("[ANALYSIS] view (screenName='" + screenName + "')");

        tracker.setScreenName(screenName);
        tracker.send(new HitBuilders.AppViewBuilder().build());
    }

    /**
     * Send the event that the app has been update
     *
     * @param appVersion the new versionName of the app
     */
    public void appUpdate(String appVersion) {
        event(CATEGORY_APP_EVENT, ACTION_APP_UPDATE + "_" + appVersion);
    }

    /**
     * Send a click on a button
     *
     * @param key Ex : MenuShare
     */
    public void click(String key) {
        action(key, ACTION_CLICK, LABEL_BUTTON);
    }

    /**
     * Send a swipe on a slider
     *
     * @param key Ex : MenuShare
     */
    public void swipe(String key) {
        action(key, ACTION_SWIPE, LABEL_SLIDER);
    }

    /**
     * Send a click on a menu
     *
     * @param key Ex : MenuShare
     */
    public void menuClick(String key) {
        action(key, ACTION_CLICK, LABEL_MENU);
    }

    /**
     * Send a click on a button
     *
     * @param key   Ex : LayersMapMode
     * @param value Ex : offline
     */
    public void value(String key, String value) {
        action(key, ACTION_VALUE, value);
    }

    /**
     * Send an action event
     *
     * @param category Ex : Settings
     * @param action   Ex : Share with Friends
     */
    public void event(String category, String action) {
        Logs.debug("[ANALYSIS] event(category='" + category + "', action='" + action + "')");

        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(category)
                .setValue(1)
                .build());
    }

    public void action(String category, String action, String label) {
        Logs.debug("[ANALYSIS] action (category='" + category + "', action='" + action + "', label='" + label + "')");

        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(action)
                .setValue(1)
                .build());
    }
}

