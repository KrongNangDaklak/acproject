package firebase.hoangduchuu.net.appchat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import firebase.hoangduchuu.net.appchat.R;
import firebase.hoangduchuu.net.appchat.model.Phuongtien;
import firebase.hoangduchuu.net.appchat.model.SinhVien;

public class MainActivity extends AppCompatActivity {
    DatabaseReference mDatabase;
    Button btnChange;
    TextView tvKhoaHoc;
    Phuongtien phuongtien;

    Button btnChat;
    EditText edtChat;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewByIds();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        learning();
        
        submitChatContents();


    }

    private void submitChatContents() {
//        String chatContent =
    }

    private void learning() {
//        bai cu
//        guiDuLieu();
//
//        bai moi
//
//        nhanDuLieu();
//
//        New Firebase in Android Bài 10:Nhận dữ liệu sử dụng AddChild với Push String
//        nhanVaGuiDuLieuVoiPush();
//
//
//        New Firebase in Android Bài 11:Nhận dữ liệu sử dụng AddChild với Push Object
//        là lấy về những thằng em của class nào đó được tạo.
//
//        guiNhanDulieuVoiObjects();
    }

    private void guiNhanDulieuVoiObjects() {

        ghiDulieuXePhuongTien();

        // nhan du l
        mDatabase.child("tenphuongtien").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Phuongtien mPhuongtien = dataSnapshot.getValue(Phuongtien.class);
                Toast.makeText(MainActivity.this, "" + mPhuongtien.Ten, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void ghiDulieuXePhuongTien() {
        phuongtien = new Phuongtien("Xe do", 230921831);

        mDatabase.child("tenphuongtien").push().setValue(phuongtien);
    }


    private void nhanVaGuiDuLieuVoiPush() {
        createDataWithPush();
        mDatabase.child("khokhan").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Toast.makeText(MainActivity.this, "onChildAdded" + dataSnapshot.getValue().toString() + "/n", Toast.LENGTH_SHORT).show();
                tvKhoaHoc.append(dataSnapshot.getValue().toString() + "\n");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(MainActivity.this, "" + dataSnapshot.getValue().toString() + dataSnapshot.getRef().getRoot(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Toast.makeText(MainActivity.this, "onChildRemoved: " + dataSnapshot.getValue().toString() + dataSnapshot.getRef().getRoot(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(MainActivity.this, "onChildMoved: " + dataSnapshot.getValue().toString() + dataSnapshot.getRef().getRoot(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void createDataWithPush() {
        mDatabase.child("khokhan").push().setValue("Nao cung vuot qua");
        mDatabase.child("khokhan").push().setValue("ve dau mai toc ngoui thuong");
        mDatabase.child("khokhan").push().setValue("xa em ky niem");
        mDatabase.child("khokhan").push().setValue("Nao cung vuot qua");
        mDatabase.child("khokhan").push().setValue("ve dau mai toc ngoui thuong");
        mDatabase.child("khokhan").push().setValue("xa em ky niem");
        mDatabase.child("khokhan").push().setValue("Nao cung vuot qua");
        mDatabase.child("khokhan").push().setValue("ve dau mai toc ngoui thuong");
        mDatabase.child("khokhan").push().setValue("xa em ky niem");

    }

    private void findViewByIds() {
        mRecyclerView = (RecyclerView) findViewById(R.id.listChat);
        btnChange = (Button) findViewById(R.id.btnChat);
        tvKhoaHoc = (TextView) findViewById(R.id.tvKhoaHoc);

    }

    private void nhanDuLieu() {
        mDatabase.child("khoahoc").setValue("Khóa học lập trình android");
        mDatabase.child("khoahoc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvKhoaHoc.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "loi cmnr", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guiDuLieu() {
        // key v easyBasic
        mDatabase.child("HuuHoangDiHoc").setValue("Nghi hoc roi nha");

        // add object

        SinhVien sinhVien = new SinhVien("KhoaPham", "VCL2", 1999);
        mDatabase.child("SinhVien").push().setValue(sinhVien);


        //add via maps

        Map<String, Integer> myMap = new HashMap<String, Integer>();
        myMap.put("xemay", 2);
        myMap.put("oto", 4);
        myMap.put("dibo", 0);
        myMap.put("xedapdi", 2);
        myMap.put("hangcai", 24);
        myMap.put("otoz", 14);

        mDatabase.child("phuongtin").setValue(myMap);

        // using push();
        SinhVien sinhVien2 = new SinhVien("Trang Thuw", "California", 1996);
        mDatabase.child("hocvien").push().setValue(sinhVien2, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Toast.makeText(MainActivity.this, "saved failed: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "saved ok", Toast.LENGTH_SHORT).show();
                }
                if (databaseReference != null) {
                    Toast.makeText(MainActivity.this, "" + databaseReference.getKey(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
