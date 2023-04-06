package com.ale95.img2png;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes.dex */
public class ShowInfo extends Activity implements View.OnClickListener {

    /* renamed from: a */
    private PackageManager f0a;

    /* renamed from: c */
    private Button f2c;

    /* renamed from: d */
    private Button f3d;

    /* renamed from: e */
    private TextView f4e;

    /* renamed from: f */
    private ImageView f5f;

    /* renamed from: g */
    private String f6g;

    /* renamed from: h */
    private String f7h;

    /* renamed from: i */
    private boolean f8i;

    /* renamed from: b */
    private ArrayList f1b = new ArrayList();

    /* renamed from: j */
    private boolean f9j = false;

    /* renamed from: a */
    private void m23a(int i, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(this).setTitle(R.string.areyousure).setMessage(i).setIconAttribute(16843605).setPositiveButton(17039379, onClickListener).setNegativeButton(17039369, (DialogInterface.OnClickListener) null).create().show();
    }

    /* renamed from: a */
    public void m16a(File file) {
        File[] listFiles;
        for (File file2 : file.listFiles()) {
            if (file2.isFile() && m10b(file2)) {
                this.f1b.add(file2);
                Log.i("Image", file2.getName());
            } else if (file2.isDirectory()) {
                m16a(file2.getAbsoluteFile());
            }
        }
    }

    /* renamed from: b */
    public static String m9b(String str) {
        String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(str);
        if (fileExtensionFromUrl != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl);
        }
        return null;
    }

    /* renamed from: b */
    public void m8b(String str, String str2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            byte[] m22a = m22a(BitmapFactory.decodeStream(bufferedInputStream));
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
            fileOutputStream.flush();
            fileOutputStream.write(m22a);
            fileOutputStream.close();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
        } catch (Exception e) {
            Log.e("Error reading file", e.toString());
        }
    }

    /* renamed from: b */
    private boolean m10b(File file) {
        return file.getName().endsWith(".qmg") || file.getName().endsWith(".astc") || file.getName().endsWith(".webp") || file.getName().endsWith(".pio") || file.getName().endsWith(".wbp") || file.getName().endsWith(".optipng") || file.getName().endsWith(".atc") || file.getName().endsWith(".pkm") || file.getName().endsWith(".quantpng") || file.getName().endsWith(".miw") || file.getName().endsWith(".miq") || file.getName().endsWith(".bmp") || file.getName().endsWith(".spr") || file.getName().endsWith(".png_opti") || file.getName().endsWith(".png_quant") || file.getName().endsWith(".qio");
    }

    /* renamed from: c */
    public void m6c(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                m6c(file2);
            }
        }
        file.delete();
    }

    /* renamed from: d */
    public void m3d(String str) {
        new AlertDialog.Builder(this).setMessage(str).setPositiveButton(17039370, (DialogInterface.OnClickListener) null).create().show();
    }

    /* renamed from: e */
    public static String m2e(String str) {
        Matcher matcher = Pattern.compile(".*?([^\\\\/]+)$").matcher(str);
        return matcher.find() ? matcher.group(1) : "";
    }

    /* renamed from: f */
    private void m1f(String str) {
        Intent intent = new Intent();
        intent.putExtra("CONTENT_TYPE", "application/*");
        intent.putExtra("FOLDERPATH", str);
        intent.setAction("com.sec.android.app.myfiles.PICK_DATA");
        startActivity(intent);
    }

    /* renamed from: a */
    public void m15a(String str) {
        Intent createChooser;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType(str);
        intent.addCategory("android.intent.category.OPENABLE");
        Intent intent2 = new Intent("com.sec.android.app.myfiles.PICK_DATA");
        intent2.putExtra("application/*", str);
        intent2.addCategory("android.intent.category.DEFAULT");
        if (this.f0a.resolveActivity(intent2, 0) != null) {
            createChooser = Intent.createChooser(intent2, "Open file");
            createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", new Intent[]{intent});
        } else {
            createChooser = Intent.createChooser(intent, "Open file");
        }
        try {
            startActivityForResult(createChooser, 1);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No suitable File Manager was found.", 0).show();
        }
    }

    /* renamed from: a */
    public boolean m14a(String str, String str2) {
        boolean z = false;
        byte[] bArr = new byte[1024];
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            while (nextEntry != null) {
                String name = nextEntry.getName();
                File file2 = new File(String.valueOf(str2) + File.separator + name);
                System.out.println("#" + file2.getAbsoluteFile() + "#");
                if (!nextEntry.isDirectory()) {
                    if (!name.startsWith("assets") && !name.startsWith("res")) {
                        nextEntry = zipInputStream.getNextEntry();
                    } else if (m10b(new File(name))) {
                        new File(file2.getParent()).mkdirs();
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        while (true) {
                            int read = zipInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        }
                        fileOutputStream.close();
                    } else {
                        nextEntry = zipInputStream.getNextEntry();
                    }
                }
                nextEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.closeEntry();
            zipInputStream.close();
            System.out.println("Done");
            z = true;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return z;
        }
    }

    /* renamed from: a */
    public byte[] m22a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight());
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 1:
                if (i2 != -1 || intent == null || intent.getData() == null) {
                    return;
                }
                this.f6g = intent.getData().getPath();
                if (TextUtils.isEmpty(this.f6g) || !m9b(this.f6g).equals("application/vnd.android.package-archive")) {
                    Toast.makeText(this, "File is not an APK", 0).show();
                    return;
                }
                this.f3d.setEnabled(true);
                String str = m2e(this.f6g).split("\\.")[0];
                this.f2c.setText(m2e(this.f6g));
                return;
            default:
                return;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pickApk /* 2131034114 */:
                m15a("application/apk");
                return;
            case R.id.convert /* 2131034115 */:
                if (this.f9j) {
                    new Thread(new RunnableC0007h(this, ProgressDialog.show(this, "", "Converting. Please wait...", true, false))).start();
                    return;
                } else {
                    m3d("Compatible only on Android 5.0 and above (Touchwiz).");
                    return;
                }
            case R.id.outputFolder /* 2131034116 */:
                m1f(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()) + File.separator + "Img2Png");
                return;
            default:
                return;
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.about_activity);
        setTitle("Img2Png");
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3D59AB")));
        this.f0a = getPackageManager();
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Img2Png");
        if (!file.exists()) {
            file.mkdir();
        }
        if (Build.VERSION.SDK_INT > 19 || (Build.VERSION.SDK_INT == 19 && Build.VERSION.RELEASE.equals("5.0"))) {
            this.f9j = true;
        }
        TextView textView = (TextView) findViewById(R.id.version);
        TextView textView2 = (TextView) findViewById(R.id.appInfo);
        this.f2c = (Button) findViewById(R.id.pickApk);
        this.f3d = (Button) findViewById(R.id.convert);
        this.f4e = (TextView) findViewById(R.id.outputFolder);
        this.f5f = (ImageView) findViewById(2131034117);
        this.f2c.setOnClickListener(this);
        this.f3d.setOnClickListener(this);
        this.f4e.setOnClickListener(this);
        try {
            textView.setText("Version " + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
        }
    }

    public boolean saveBitmapToFile(File dir, String fileName, Bitmap bm, Bitmap.CompressFormat format, int quality) {
        FileOutputStream fos;
        File imageFile = new File(dir, fileName);
        FileOutputStream fos2 = null;
        try {
            fos = new FileOutputStream(imageFile);
        } catch (IOException e) {
            e = e;
        }
        try {
            bm.compress(format, quality, fos);
            fos.close();
            return true;
        } catch (IOException e2) {
            e = e2;
            fos2 = fos;
            Log.e("app", e.getMessage());
            if (fos2 != null) {
                try {
                    fos2.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return false;
        }
    }
}
