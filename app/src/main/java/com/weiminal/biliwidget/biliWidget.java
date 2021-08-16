package com.weiminal.biliwidget;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import org.json.JSONException;

import java.io.IOException;

/**
 * Implementation of App Widget functionality.
 */
public class biliWidget extends AppWidgetProvider {

    @Override
    public  void onReceive(Context context, Intent intent){
        super.onReceive(context,intent);
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        //显示信息内容
        /**CharSequence widgetText = context.getString(R.string.appwidget_text);
           //AS自带
         **/
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bili_widget);

        Thread thread = new Thread((new Runnable() {
            @Override
            public void run() {
                String dataView = "无法连接网络";
                try {
                    dataView = dataGet.dataShow();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                views.setTextViewText(R.id.信息展示, dataView);
                appWidgetManager.updateAppWidget(appWidgetId, views);
            }
        }));
        thread.start();

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}