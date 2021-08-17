package com.weiminal.biliwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import org.json.JSONException
import java.io.IOException

/**
 * Implementation of App Widget functionality.
 */
class biliWidget : AppWidgetProvider() {
    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            //显示信息内容
            /**CharSequence widgetText = context.getString(R.string.appwidget_text);
             * //AS自带
             */
            val views = RemoteViews(context.packageName, R.layout.bili_widget)
            val thread = Thread {
                var dataView = "无法连接网络"
                try {
                    dataView = dataGet.dataShow()
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                Log.d("TAG", "run: $dataView")
                views.setTextViewText(R.id.textView, dataView)
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
            thread.start()
        }
    }
}