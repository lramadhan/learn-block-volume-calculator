package com.belajar.samplebarvolume;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    membuat variable berdasarkan layout
    EditText edtHeight, edtLength, edtWidth;
    Button btnCalculate;
    TextView tvResult;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//  memasukan id layout pada variable
        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);
//  set onclicklistener untuk tombol pada activity ini
        btnCalculate.setOnClickListener(this);
//  menampilkan data dari STATE_RESULT bila savedinstancestate tidak kosong
        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }
//onclick
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_calculate) {
//          menyimpan text kolom pada variable
            String inputLength = edtLength.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

//          Mengecek nilai kosong pada kolom
            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputWidth)) {
                isEmptyFields = true;
                edtWidth.setError("Masukkan nilai lebar!");
            }

            if (TextUtils.isEmpty(inputHeight)) {
                isEmptyFields = true;
                edtHeight.setError("Masukkan nilai tinggi!");
            }

            if (TextUtils.isEmpty(inputLength)) {
                isEmptyFields = true;
                edtLength.setError("Masukkan nilai panjang!");
            }

//          Mengecek nilai masukan angka
            Double length = toDouble(inputLength);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if (length == null) {
                isInvalidDouble = true;
                edtLength.setError("Field ini harus berupa nomor yang valid!");
            }

            if (width == null) {
                isInvalidDouble = true;
                edtWidth.setError("Field ini harus berupa nomor yang valid!");
            }

            if (height == null) {
                isInvalidDouble = true;
                edtHeight.setError("Field ini harus berupa nomor yang valid!");
            }

            if (!isEmptyFields && !isInvalidDouble) {
                double volume = length * width * height;

                tvResult.setText(String.valueOf(volume));
            }
        }
    }
//todouble
    Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        mengisi STATE_RESULT dari hasil di tvresult dengan type string
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
