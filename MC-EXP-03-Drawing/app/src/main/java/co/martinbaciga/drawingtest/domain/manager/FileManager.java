package co.martinbaciga.drawingtest.domain.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileManager {
    public static Uri saveBitmap(Context context, Bitmap bitmap) {
        // Saving to the app's private external storage to avoid permission issues in Android 10+
        File dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }

        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()).concat(".png");
        File file = new File(dir, name);

        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
            return FileProvider.getUriForFile(context,
                    context.getApplicationContext().getPackageName() + ".provider", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}