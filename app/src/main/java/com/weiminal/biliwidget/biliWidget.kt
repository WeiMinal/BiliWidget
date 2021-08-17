package com.weiminal.biliwidget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.util.Log
import android.widget.RemoteViews
import com.weiminal.biliwidget.utils.ApiUtil
import org.json.JSONException
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection


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
                Log.d("TAG", "run: $dataView")

                // 获取信息
                val info = ApiUtil.getInfo("35606358")
                views.setTextViewText(R.id.textView_name, info.getString("name"))
                Log.d("TAG", "updateAppWidget: " + info.getString("name"))
                Log.d("TAG", "updateAppWidget: " + info.getString("face"))
                val infoFollow = ApiUtil.getInfoFollow("35606358")
                views.setTextViewText(
                    R.id.textView_follower,
                    "粉丝数：${infoFollow.getString("follower")}"
                )

                views.setImageViewBitmap(
                    R.id.imageView_head,
                    toRoundBitmap(
                        getBitmap(info.getString("face").replace("http", "https"))
                        !!
                    )
                )
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
            thread.start()
        }

        private fun getBitmap(url: String?): Bitmap? {
            var bm: Bitmap? = null
            try {
                val iconUrl = URL(url)
                val conn: URLConnection = iconUrl.openConnection()
                val http: HttpURLConnection = conn as HttpURLConnection
                val length: Int = http.getContentLength()
                conn.connect()
                // 获得图像的字符流
                val `is`: InputStream = conn.getInputStream()
                val bis = BufferedInputStream(`is`, length)
                bm = BitmapFactory.decodeStream(bis)
                bis.close()
                `is`.close() // 关闭流
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Log.d("TAG", "getBitmap: $bm")
            return bm
        }

        private fun toRoundBitmap(bitmap: Bitmap): Bitmap? {
            //--Bitmap转圆形图片Bitmap
            var width = bitmap.width
            var height = bitmap.height
            val roundPx: Float
            val left: Float
            val top: Float
            val right: Float
            val bottom: Float
            val dst_left: Float
            val dst_top: Float
            val dst_right: Float
            val dst_bottom: Float
            if (width <= height) {
                roundPx = (width / 2).toFloat()
                top = 0f
                bottom = width.toFloat()
                left = 0f
                right = width.toFloat()
                height = width
                dst_left = 0f
                dst_top = 0f
                dst_right = width.toFloat()
                dst_bottom = width.toFloat()
            } else {
                roundPx = (height / 2).toFloat()
                val clip = ((width - height) / 2).toFloat()
                left = clip
                right = width - clip
                top = 0f
                bottom = height.toFloat()
                width = height
                dst_left = 0f
                dst_top = 0f
                dst_right = height.toFloat()
                dst_bottom = height.toFloat()
            }
            val output = Bitmap.createBitmap(
                width,
                height, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(output)
            val color = -0xbdbdbe
            val paint = Paint()
            val src = Rect(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
            val dst = Rect(
                dst_left.toInt(), dst_top.toInt(), dst_right.toInt(),
                dst_bottom.toInt()
            )
            val rectF = RectF(dst)
            paint.setAntiAlias(true)
            canvas.drawARGB(0, 0, 0, 0)
            paint.setColor(color)
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
            paint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_IN))
            canvas.drawBitmap(bitmap, src, dst, paint)
            return output
        }
    }

}