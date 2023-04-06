package com.ale95.img2png;

import android.app.ProgressDialog;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.ale95.img2png.h */
/* loaded from: classes.dex */
public class RunnableC0007h implements Runnable {

    /* renamed from: a */
    final /* synthetic */ ShowInfo f11a;

    /* renamed from: b */
    private final /* synthetic */ ProgressDialog f12b;

    public RunnableC0007h(ShowInfo showInfo, ProgressDialog progressDialog) {
        this.f11a = showInfo;
        this.f12b = progressDialog;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        String str2;
        boolean z;
        String str3;
        String m2e;
        ArrayList arrayList;
        String str4 = String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()) + File.separator + "Img2Png";
        String str5 = String.valueOf(str4) + File.separator + "source";
        String str6 = String.valueOf(str4) + File.separator + "output";
        str = this.f11a.f6g;
        if (!TextUtils.isEmpty(str)) {
            this.f11a.m6c(new File(str5));
            File file = new File(str5);
            if (!file.exists()) {
                file.mkdir();
            }
            ShowInfo showInfo = this.f11a;
            ShowInfo showInfo2 = this.f11a;
            str2 = this.f11a.f6g;
            showInfo.f8i = showInfo2.m14a(str2, str5);
            z = this.f11a.f8i;
            if (z) {
                str3 = this.f11a.f6g;
                m2e = ShowInfo.m2e(str3);
                String str7 = m2e.split("\\.")[0];
                this.f11a.m6c(new File(str4, str7));
                this.f11a.f7h = String.valueOf(str4) + File.separator + str7;
                this.f11a.m16a(new File(str5));
                arrayList = this.f11a.f1b;
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    String absolutePath = ((File) it.next()).getAbsolutePath();
                    String replace = absolutePath.replace("/source/", "/" + str7 + "/");
                    if (replace.endsWith("qmg")) {
                        replace = replace.replace("qmg", "png");
                    } else if (replace.endsWith("astc")) {
                        replace = replace.replace("astc", "png");
                    } else if (replace.endsWith("pio")) {
                        replace = replace.replace("pio", "png");
                    } else if (replace.endsWith("webp")) {
                        replace = replace.replace("webp", "png");
                    } else if (replace.endsWith("wbp")) {
                        replace = replace.replace("wbp", "png");
                    } else if (replace.endsWith("optipng")) {
                        replace = replace.replace("optipng", "png");
                    } else if (replace.endsWith("atc")) {
                        replace = replace.replace("atc", "png");
                    } else if (replace.endsWith("pkm")) {
                        replace = replace.replace("pkm", "png");
                    } else if (replace.endsWith("quantpng")) {
                        replace = replace.replace("quantpng", "png");
                    } else if (replace.endsWith("miw")) {
                        replace = replace.replace("miw", "png");
                    } else if (replace.endsWith("miq")) {
                        replace = replace.replace("miq", "png");
                    } else if (replace.endsWith("bmp")) {
                        replace = replace.replace("bmp", "png");
                    } else if (replace.endsWith("spr")) {
                        replace = replace.replace("spr", "png");
                    } else if (replace.endsWith("png_opti")) {
                        replace = replace.replace("png_opti", "png");
                    } else if (replace.endsWith("png_quant")) {
                        replace = replace.replace("png_quant", "png");
                    } else if (replace.endsWith("qio")) {
                        replace = replace.replace("qio", "png");
                    }
                    File file2 = new File(replace);
                    new File(file2.getParent()).mkdirs();
                    this.f11a.m8b(absolutePath, file2.getAbsolutePath());
                }
                this.f11a.m6c(new File(str5));
            }
        }
        this.f11a.runOnUiThread(new RunnableC0008i(this, this.f12b));
    }
}
