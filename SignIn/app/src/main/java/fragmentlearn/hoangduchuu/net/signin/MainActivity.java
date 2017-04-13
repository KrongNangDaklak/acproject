package fragmentlearn.hoangduchuu.net.signin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "signin";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button btnReg, btnLogin;
    TextView tvInfo;
    EditText edtUser, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        findViewByIds();
        newListennerCreate();

    }

    private void findViewByIds() {

        btnReg = (Button) findViewById(R.id.btnReg);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvInfo = (TextView) findViewById(R.id.txtShow);
        edtPass = (EditText) findViewById(R.id.edtpass);
        edtUser = (EditText) findViewById(R.id.edtUser);

        btnLogin.setOnClickListener(this);
        btnReg.setOnClickListener(this);

    }

    private void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "sign-up : isSuccessful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "sign-up : failed : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void newListennerCreate() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }


    @Override
    public void onClick(View v) {

        String user = edtUser.getText().toString().trim();
        String password = edtPass.getText().toString().trim();
        boolean isValid2 = validForm(user, password);
        if (isValid2) {
            switch (v.getId()) {
                case R.id.btnReg:
                    signUp(user, password);
                    break;
                case R.id.btnLogin:
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    if (currentUser != null){
                        btnLogin.setText("Sign-out");
                    }
                    signIn(user, password);
            }
        } else {
            Toast.makeText(this, "not null nha", Toast.LENGTH_SHORT).show();
        }

    }

    private void signIn(String user, String password) {
        mAuth.signInWithEmailAndPassword(user, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String uid = task.getResult().getUser().getUid();
                    String name = task.getResult().getUser().getDisplayName();
                    String email = task.getResult().getUser().getEmail();
                    String providerId = task.getResult().getUser().getProviderId();

                    tvInfo.setText(uid + "\n"
                            + name + "\n"
                            + email + "\n"
                            + providerId + "\n");
                    Toast.makeText(MainActivity.this, "Login Ok", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Login nhu cl", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validForm(String user, String password) {
        if (user.isEmpty() && password.isEmpty()) {
            return false;
        } else {
            return true;

        }
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
