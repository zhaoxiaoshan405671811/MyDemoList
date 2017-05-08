package com.example.a1.myheadlinenews;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import utils.MyUserHelper;
import utils.RegexUtils;

import static com.example.a1.myheadlinenews.R.id.image_tx;

public class Yanzheng_Login extends AppCompatActivity  {

    @BindView(R.id.zhuce_wancheng)
    TextView mZhuceWancheng;
    @BindView(R.id.finish_view)
    View mFinishView;
    @BindView(image_tx)
    CircleImageView mImageTx;
    @BindView(R.id.set_username)
    EditText mSetUsername;
    @BindView(R.id.set_linear)
    LinearLayout mSetLinear;
    private MyUserHelper mHelper;
    private AlertDialog.Builder builder;
    private Bitmap mBm;
    private Uri mUri;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private String mPassword;
    private String mPhone;
    private String mTxuri;
    private String mNickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yanzheng__login);
        ButterKnife.bind(this);
        sp = getSharedPreferences("isLogin", MODE_PRIVATE);
        edit = sp.edit();
        mPassword = sp.getString("password","");
        mPhone = sp.getString("phone","");
        mHelper = new MyUserHelper(this);
    }

    @OnClick({R.id.zhuce_wancheng, image_tx, R.id.set_username})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //完成的点击事件
            case R.id.zhuce_wancheng:
                boolean username = RegexUtils.isUsername(mSetUsername.getText());
                if(username){
                    String user_name = mSetUsername.getText().toString();
                    SQLiteDatabase db = mHelper.getWritableDatabase();
                    ContentValues values=new ContentValues();
                    values.put("name",user_name);
                    values.put("phone",mPhone);
                    values.put("pic",mTxuri);
                    values.put("password",mPassword);
                    db.insert("user",null,values);
                    edit.putBoolean("isLogin",true);
                    edit.commit();
                    Intent intent_cg = new Intent(this,MainActivity.class);
                    startActivity(intent_cg);
                    finish();
                }else {
                    Toast.makeText(this, "请输入合法的用户名", Toast.LENGTH_SHORT).show();
                }

                break;
            //头像设置的点击事件
            case R.id.image_tx:
                builder = new AlertDialog.Builder(Yanzheng_Login.this);
                View view1 = View.inflate(Yanzheng_Login.this, R.layout.editpage_alertdialog, null);
                TextView textCamera= (TextView) view1.findViewById(R.id.textCamera);
                TextView textchoosePic= (TextView) view1.findViewById(R.id.textchoosePic);
                TextView textcancel= (TextView) view1.findViewById(R.id.textcancel);
                textCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.create().dismiss();
                        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.addCategory("android.intent.category.DEFAULT");
                        startActivityForResult(intent, 3);
                    }
                });
                textchoosePic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.create().dismiss();
                        Intent i=new Intent(Intent.ACTION_PICK);
                        i.setType("image*//*");

                        startActivityForResult(i, 1);
                    }
                });
                textcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.create().dismiss();
                    }
                });
                builder.setView(view1);
                builder.create().show();
                break;
            //设置用户名的点击事件
            case R.id.set_username:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if(requestCode==1){
            Uri uri = data.getData();
            crop(uri);
        }else if(requestCode==2){
            mBm = data.getParcelableExtra("data");
            mUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), mBm, "", ""));
            Log.d("zzz", mUri.toString()+"");
            mImageTx.setImageBitmap(mBm);
            mTxuri = mUri.toString();
        }else if(requestCode==3){
            mBm = data.getParcelableExtra("data");
            mUri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), mBm, "", ""));
            Log.d("zzz",mUri.toString()+"");
            mImageTx.setImageBitmap(mBm);
            mTxuri = mUri.toString();
        }

        super.onActivityResult(requestCode, resultCode, data);

    }
    private void crop(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", false);


        intent.putExtra("return-data", true);
        startActivityForResult(intent, 2);

    }


   /* @Override
    public void onClick(View v) {
        switch (v.getId()){
        case R.id.textCamera:
        builder.create().dismiss();
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory("android.intent.category.DEFAULT");
        startActivityForResult(intent, 3);
        break;
        case R.id.textchoosePic:
        builder.create().dismiss();
        Intent i=new Intent(Intent.ACTION_PICK);
        i.setType("image*//*");
        startActivityForResult(i, 1);
        break;
        case R.id.textcancel:
        builder.create().dismiss();
        break;
        }
    }*/
}
