package fragmentlearn.hoangduchuu.net.notifycation;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "huuhoang";
    Button btnNotify, btnRegister;
    EditText edtUser, edtpass;
    TextView tvInfos;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "login success");
                } else {
                    Log.d(TAG, "login failed");

                }
            }
        };


        findView();

    }

    private boolean validateForm() {
        String email = edtUser.getText().toString().trim();
        String pass = edtpass.getText().toString().trim();
        if (email.isEmpty() && pass.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    private void findView() {
        btnNotify = (Button) findViewById(R.id.btn);
        btnRegister = (Button) findViewById(R.id.btnReg);
        edtpass = (EditText) findViewById(R.id.edtpass);
        edtUser = (EditText) findViewById(R.id.edtUser);
        tvInfos = (TextView) findViewById(R.id.txtShow);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReg:
                Toast.makeText(this, "BtnReg", Toast.LENGTH_SHORT).show();

                boolean isValidForm = validateForm();
                if (!isValidForm){
                    Toast.makeText(this, "Check null in edt", Toast.LENGTH_SHORT).show();
                }else{
                    abc();
                }
                break;
            case R.id.btn:
                FirebaseMessaging.getInstance().subscribeToTopic("tech");
                break;

        }
    }

    private void abc() {
        Toast.makeText(this, "not null roi", Toast.LENGTH_SHORT).show();
    }
}
