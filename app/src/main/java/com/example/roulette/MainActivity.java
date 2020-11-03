package com.example.roulette;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{

    private static final String TAG = "datnt";
    boolean blnButtonRotaion = true;
    long lngDegree = 0;
    long lngExDegree = 0;
    long lngAddDegree = 0;
    int ran, count = 0;
    boolean check1 = false, check2 = false, check3 = false, check4 = false;
    Toast toast;
    SharedPreferences sharedPreferences;
    Button bStart;
    ImageView ivSelected, ivRoulette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(1024);
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bStart = (Button)findViewById(R.id.btStart);
        ivRoulette = (ImageView)findViewById(R.id.ivRoulette);
        ivSelected = (ImageView)findViewById(R.id.ivSelected);

        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        this.blnButtonRotaion = false;
        bStart.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(this.lngExDegree <= 45 ){
            toast = Toast.makeText(this, "Chúc mừng bạn đạt giải 1", 0);
        } else if (this.lngExDegree <= 90){
            toast = Toast.makeText(this, "Chúc bạn may mắn lần sau", 0);
        } else if (this.lngExDegree <= 135){
            toast = Toast.makeText(this, "Chúc mừng bạn đạt giải 2", 0);
        } else if (this.lngExDegree <= 180){
            toast = Toast.makeText(this, "Chúc bạn may mắn lần sau", 0);
        } else if (this.lngExDegree <= 225){
            toast = Toast.makeText(this, "Chúc mừng bạn đạt giải 3", 0);
        } else if (this.lngExDegree <= 270){
            toast = Toast.makeText(this, "Chúc bạn may mắn lần sau", 0);
        } else if (this.lngExDegree <= 315){
            toast = Toast.makeText(this, "Chúc mừng bạn đạt giải 4", 0);
        } else{
            toast = Toast.makeText(this, "Chúc bạn may mắn lần sau", 0);
        }

        toast.setGravity(49, 0 , 0);
        toast.show();
        this.blnButtonRotaion = true;
        bStart.setVisibility(View.VISIBLE);


    }


    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void onClickButtonRotation(View v){
        if(this.blnButtonRotaion){
            ran = new Random().nextInt(100) + 1;
            if(ran == 1 ){
                if(check1== false){
                    this.lngDegree = new Random().nextInt(45) + 3600;
                    check1 = true;
                } else{
                    this.lngDegree = new Random().nextInt(45) + 3600 + 45;
                }
            } else if (1< ran && ran < 26){
                this.lngDegree = new Random().nextInt(45) + 3600 + 45;
            } else if (ran == 26){
                if(check2 == false){
                    this.lngDegree = new Random().nextInt(45) + 3600 + 90;
                    check2 = true;
                } else{
                    this.lngDegree = new Random().nextInt(45) + 3600 + 90 + 45;
                }
            } else if (26< ran && ran < 51){
                this.lngDegree = new Random().nextInt(45) + 3600 + 135;
            } else if (ran == 51){
                if(check3 == false){
                    this.lngDegree = new Random().nextInt(45) + 3600 + 180;
                    check3 = true;
                } else{
                    this.lngDegree = new Random().nextInt(45) + 3600 + 180 + 45;
                }
            } else if (51< ran && ran < 76){
                this.lngDegree = new Random().nextInt(45) + 3600 + 225;
            } else if (ran == 76){
                if(check4 == false){
                    this.lngDegree = new Random().nextInt(45) + 3600 + 270;
                    check4 = true;
                } else{
                    this.lngDegree = new Random().nextInt(45) + 3600 + 270 + 45;
                }
            } else{
                this.lngDegree = new Random().nextInt(45) + 3600 + 315;
            }

            count++;
            if(count >= 100){
                check1 = false;
                check2 = false;
                check3 = false;
                check4 = false;
            }

            RotateAnimation rotateAnimation = new RotateAnimation((float)this.lngDegree, (float)
                    (this.lngDegree + 3600), 1, 0.5f, 1, 0.5f);

            this.lngAddDegree = this.lngDegree - this.lngExDegree;
            this.lngExDegree = this.lngDegree % 360;

            rotateAnimation.setDuration(this.lngAddDegree);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setAnimationListener(this);
            ivRoulette.setAnimation(rotateAnimation);
            ivRoulette.startAnimation(rotateAnimation);
        }
    }
}