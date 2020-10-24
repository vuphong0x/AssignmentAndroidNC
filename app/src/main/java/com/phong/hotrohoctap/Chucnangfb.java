package com.phong.hotrohoctap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.FileNotFoundException;
import java.io.InputStream;

import androidx.appcompat.app.AppCompatActivity;

public class Chucnangfb extends AppCompatActivity {
    Button shareLink, shareImg;
    ImageView imgHinhAnh;
    ShareDialog shareDialog;
    ShareLinkContent shareLinkContent;
    SharePhoto sharePhoto;
    SharePhotoContent sharePhotoContent;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chucnangfb);
        anhxaabc();
        shareanh();
        shareDialog = new ShareDialog(Chucnangfb.this);
        shareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    shareLinkContent = new ShareLinkContent.Builder()
                            .setContentUrl(Uri.parse("https://developers.facebook.com"))
                            .build();
                }
                shareDialog.show(shareLinkContent);
            }
        });


    }

    private void shareanh() {
        imgHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        shareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePhoto = new SharePhoto.Builder()
                        .setBitmap(bitmap)
                        .build();
                sharePhotoContent = new SharePhotoContent.Builder()
                        .addPhoto(sharePhoto)
                        .build();
                /// nhan image le dialog
                shareDialog.show(sharePhotoContent);
            }
        });
    }

    private void anhxaabc() {
        shareLink = (Button) findViewById(R.id.shareLink);
        shareImg = (Button) findViewById(R.id.shareImg);
        imgHinhAnh = (ImageView) findViewById(R.id.imgHinhAnh);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream);
                imgHinhAnh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
