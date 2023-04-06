package com.ale95.img2png;

import android.app.ProgressDialog;
import java.io.File;

/* renamed from: com.ale95.img2png.i */
/* loaded from: classes.dex */
class RunnableC0008i implements Runnable {

    /* renamed from: a */
    final /* synthetic */ RunnableC0007h f13a;

    /* renamed from: b */
    private final /* synthetic */ ProgressDialog f14b;

    public RunnableC0008i(RunnableC0007h runnableC0007h, ProgressDialog progressDialog) {
        this.f13a = runnableC0007h;
        this.f14b = progressDialog;
    }

    @Override // java.lang.Runnable
    public void run() {
        ShowInfo showInfo;
        String str;
        ShowInfo showInfo2;
        ShowInfo showInfo3;
        boolean z;
        ShowInfo showInfo4;
        ShowInfo showInfo5;
        this.f14b.dismiss();
        showInfo = this.f13a.f11a;
        str = showInfo.f7h;
        File file = new File(str);
        if (!file.exists() || file.listFiles().length <= 0) {
            showInfo2 = this.f13a.f11a;
            showInfo2.m3d("No Qmg resources in APK file.");
            return;
        }
        showInfo3 = this.f13a.f11a;
        z = showInfo3.f8i;
        if (z) {
            showInfo5 = this.f13a.f11a;
            showInfo5.m3d("Conversion Done.");
            return;
        }
        showInfo4 = this.f13a.f11a;
        showInfo4.m3d("Error during extraction or coversion.");
    }
}
