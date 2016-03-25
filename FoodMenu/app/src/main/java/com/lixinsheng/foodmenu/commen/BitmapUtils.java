package com.lixinsheng.foodmenu.commen;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

/**
 * Created by lixinsheng on 16/3/21.
 */
public class BitmapUtils {
    //Drawable → Bitmap
    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap

                .createBitmap(

                        drawable.getIntrinsicWidth(),

                        drawable.getIntrinsicHeight(),

                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                                : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

// canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;

    }

    //从资源中获取Bitmap
    public Bitmap getBitmapFromResource(Context context, int re) {
        Resources res = context.getResources();

        return BitmapFactory.decodeResource(res, re);
    }

    //Bitmap → byte[]
    public byte[] Bitmap2Bytes(Bitmap bm) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);

        return baos.toByteArray();

    }

    //byte[] → Bitmap
    public Bitmap Bytes2Bimap(byte[] b){

        if(b.length!=0){

            return BitmapFactory.decodeByteArray(b, 0, b.length);

        }

        else {

            return null;

        }

    }
}
