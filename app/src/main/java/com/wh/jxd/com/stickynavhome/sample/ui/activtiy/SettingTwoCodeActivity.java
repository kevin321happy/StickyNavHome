package com.wh.jxd.com.stickynavhome.sample.ui.activtiy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.wh.jxd.com.stickynavhome.BaseActivtiy;
import com.wh.jxd.com.stickynavhome.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kevin321vip on 2017/12/28.
 * 设置二维码界面
 */

public class SettingTwoCodeActivity extends BaseActivtiy implements View.OnClickListener {

    private Button mBtn_share;
    private Button mBtn_creaet;
    private ImageView mIv_code;
    private Bitmap mQrBitmap;
    private String mPath;
    private TextView mTv_path;
    private Bitmap mAddLogo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_two_code;
    }

    @Override
    protected void initView() {
        mBtn_share = (Button) findViewById(R.id.btn_share);
        mBtn_creaet = (Button) findViewById(R.id.btn_creat);
        mIv_code = (ImageView) findViewById(R.id.tv_code);
        mTv_path = (TextView) findViewById(R.id.tv_path);
        mBtn_creaet.setOnClickListener(this);
        mBtn_share.setOnClickListener(this);
        setToolBarTitle("设置二维码");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share:
                if (mQrBitmap == null) {
                    Toast.makeText(this, "请先生成二维码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mPath == null) {
                    Toast.makeText(this, "生成文件失败", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(mPath));
                startActivity(Intent.createChooser(intent, "官网"));
                break;
            case R.id.btn_creat:
                try {
                    mQrBitmap = generateBitmap("https://github.com/kevin321happy", 400, 400);
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_lyon);
                    mAddLogo = addLogo(mQrBitmap, bitmap);
                    saveBitmap(mAddLogo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mIv_code.setImageBitmap(mAddLogo);
                break;
            default:
                break;
        }
    }

    /**
     * 生成二维码的图片
     *
     * @param content
     * @param width
     * @param height
     * @return
     */
    private Bitmap generateBitmap(String content, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (encode.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 给二维码加一个LOGO
     *
     * @param qrBitmap
     * @param logoBitmap
     * @return
     */
    private Bitmap addLogo(Bitmap qrBitmap, Bitmap logoBitmap) {
        int qrBitmapWidth = qrBitmap.getWidth();
        int qrBitmapHeight = qrBitmap.getHeight();
        int logoBitmapWidth = logoBitmap.getWidth();
        int logoBitmapHeight = logoBitmap.getHeight();
        Bitmap blankBitmap = Bitmap.createBitmap(qrBitmapWidth, qrBitmapHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(blankBitmap);
        canvas.drawBitmap(qrBitmap, 0, 0, null);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        float scaleSize = 1.0f;
        while ((logoBitmapWidth / scaleSize) > (qrBitmapWidth / 5) || (logoBitmapHeight / scaleSize) > (qrBitmapHeight / 5)) {
            scaleSize *= 2;
        }
        float sx = 1.0f / scaleSize;
        canvas.scale(sx, sx, qrBitmapWidth / 2, qrBitmapHeight / 2);
        canvas.drawBitmap(logoBitmap, (qrBitmapWidth - logoBitmapWidth) / 2, (qrBitmapHeight - logoBitmapHeight) / 2, null);
        canvas.restore();
        return blankBitmap;
    }


    /**
     * 讲BitMap对象存成文件
     *
     * @param bitmap
     * @throws IOException
     */
    private void saveBitmap(Bitmap bitmap) throws IOException {
        File file = new File("/sdcard/DCIM/" + "fenxiang");
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)) {
                out.flush();
                out.close();
            }
            mPath = file.getAbsolutePath();
            mTv_path.setText("二维码的保存路径:"+mPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

