package eclass.dogking.com.oneclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eclass.dogking.com.oneclass.API.TeacherAPI;
import eclass.dogking.com.oneclass.API.UserAPI;
import eclass.dogking.com.oneclass.entiry.HttpDefault;
import eclass.dogking.com.oneclass.entiry.Teacher;
import eclass.dogking.com.oneclass.entiry.User;
import eclass.dogking.com.oneclass.utils.OneclassUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dog on 2018/3/10 0010.
 */

public class AdminUpdata2 extends AppCompatActivity {
    @BindView(R.id.edit1)
    EditText edit1;
    @BindView(R.id.edit2)
    EditText edit2;
    @BindView(R.id.edit3)
    EditText edit3;
    @BindView(R.id.edit4)
    EditText edit4;
    @BindView(R.id.edit5)
    EditText edit5;
    @BindView(R.id.udbutton)
    Button udbutton;
    @BindView(R.id.udbutton2)
    Button udbutton2;
    private String Lid;
    private int id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.adminud2);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        //取得课程名称
        if (intent != null) {
            Lid = intent.getStringExtra("Lid");
        }
        Log.e("tag=========",Lid);
        id=Integer.parseInt(Lid);
        receive();
    }

    @OnClick({R.id.udbutton,R.id.udbutton2})
    public void onClick(View view){
        String text1=edit1.getText().toString();
        String text2=edit2.getText().toString();
        String text3=edit3.getText().toString();
        String text4=edit4.getText().toString();
        String text5=edit5.getText().toString();



        switch (view.getId()){
            case R.id.udbutton:
                if (!((text1.length() < 1) || (text2.length() < 1) || (text3.length() < 1)|| (text4.length() < 1)|| (text5.length() < 1)))
                    udteacher(id,text1,text2,text3,text4,text5);
                else
                    Toast.makeText(AdminUpdata2.this,"请输入完整信息",Toast.LENGTH_SHORT).show();

                break;
            case R.id.udbutton2:
                finish();

                break;




        }

    }


    private void receive(){
        Observable<HttpDefault<Teacher>> observable = OneclassUtils.createAPI(TeacherAPI.class).findTeacher(
                id

        );
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<Teacher>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<Teacher> userHttpDefault) {

                        if (userHttpDefault.getError_code() == 0) {
                            //Toast.makeText(Tab4_1Activity.this,userHttpDefault.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("tag:", "============" + "个人信息");
                            edit1.setText(userHttpDefault.getData().getName());
                            edit2.setText(userHttpDefault.getData().getEmail());
                            edit3.setText(userHttpDefault.getData().getSchool());
                            edit4.setText(userHttpDefault.getData().getSex());
                            edit5.setText(userHttpDefault.getData().getDescription());
                        } else {

                            Toast.makeText(AdminUpdata2.this,userHttpDefault.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("tag:", e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    private void udteacher(int id,String name,String email,String school,String sex,String description){
        Observable<HttpDefault<Teacher>> observable = OneclassUtils.createAPI(TeacherAPI.class).udteacher(
                id,name,sex,email,school,description);

        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<Teacher>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<Teacher> userHttpDefault) {

                        if (userHttpDefault.getError_code() == -1) {
                            Toast.makeText(AdminUpdata2.this, userHttpDefault.getMessage(), Toast.LENGTH_SHORT).show();
                        } else if (userHttpDefault.getError_code() == 0) {
                            Toast.makeText(AdminUpdata2.this, userHttpDefault.getMessage(), Toast.LENGTH_SHORT).show();
                            AdminUpdata2.this.finish();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //showToast("服务器貌似出问题了(throwable)...", ToastDuration.SHORT);
                        Log.e("tag:", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }

                });






    }


}
