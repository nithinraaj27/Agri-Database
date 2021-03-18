package com.example.mini_project_java;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB ;
    EditText name,cropname,cropweight,cropprice,id;
    Button add,view,update,delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        cropname = findViewById(R.id.cropname);
        cropweight = findViewById(R.id.cropweight);
        cropprice  = findViewById(R.id.cropprice);
        add = findViewById(R.id.button_add);
        view = findViewById(R.id.button_view);
        update = findViewById(R.id.button_update);
        delete = findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        deleteData();
    }

    public void  deleteData()
    {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteedRows = myDB.deleteData(id.getText().toString());
                if(deleteedRows > 0)
                {
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Data Not Deleted",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void UpdateData(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDB.updateData(id.getText().toString(),
                        name.getText().toString(),
                        cropname.getText().toString(),
                        cropweight.getText().toString(),
                        cropprice.getText().toString());
                if(isUpdate == true)
                {
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Data Not Updated",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void AddData()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isInserted =  myDB.insertData(name.getText().toString(),cropname.getText().toString(),cropweight.getText().toString(),cropprice.getText().toString());

               if(isInserted == true)
               {
                   Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
               }
               else{
                   Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
               }

            }
        });
    }

    public void viewAll()
    {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.getAllData();
                if(res.getCount() == 0)
                {
                    showMessage("Error","Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("ID: "+res.getString(0)+"\n");
                    buffer.append("NAME: "+res.getString(1)+"\n");
                    buffer.append("CROP NAME: "+res.getString(2)+"\n");
                    buffer.append("CROP WEIGHT: "+res.getString(3)+"\n");
                    buffer.append("CROP PRICE: "+res.getString(4)+"\n\n");
                }

                showMessage("Data",buffer.toString() );

            }
        });
    }

    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
}