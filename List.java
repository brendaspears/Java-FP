// created by : Brenda Spears ( 2201841702 )

package com.example.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;       // to transfer transfer/receive data between activities
import android.view.View;      // to connect the onClick button ( set as parameter )
import android.widget.Button;       // to create the button
import android.widget.TextView;     // To output the arraylist data
import android.content.Intent;      // to transfer/receive data between activities
import java.util.*;     // to call the arraylist


public class List extends AppCompatActivity {


    ArrayList<String> array = null;



    private void setArray(){        // to extract the received arraylist

        Bundle bundle = getIntent().getExtras();
        try{
            array = (ArrayList<String>) bundle.getStringArrayList("array_list");
        }catch(NullPointerException ignored){

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {        // to override the onClick (button)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        Button returnBtn = (Button) findViewById(R.id.activityButton);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        TextView stringTextView = (TextView) findViewById(R.id.textView2);
        setArray();
        if ( array!= null) {
            String combined = "";
//            stringTextView.setText(array);s
            for(int i=0; i < array.size(); i++){
                combined = combined + array.get(i) + ", \n";
            }
                stringTextView.setText(combined);
        }

        else {
            stringTextView.setText("Empty");
        }
    }

    public void openActivity(){         // to open the first/main activity again
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
