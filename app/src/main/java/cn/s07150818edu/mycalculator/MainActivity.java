package cn.s07150818edu.mycalculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText tv1;
    private RadioButton manButton,falmanButton;
    private Button bt;
    private TextView resulttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1= (EditText) findViewById(R.id.textView1);
        manButton= (RadioButton) findViewById(R.id.man);
        falmanButton= (RadioButton) findViewById(R.id.falman);
        bt= (Button) findViewById(R.id.button1);
        resulttext= (TextView) findViewById(R.id.textView2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerEvent();
    }
    private  void registerEvent(){
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!tv1.getText().toString().trim().equals("")){
                    if(manButton.isChecked()||falmanButton.isChecked()){
                        Double weight=Double.parseDouble(tv1.getText().toString());
                        StringBuffer sb=new StringBuffer();
                        sb.append("----------评估结果------------\n");
                        if(manButton.isChecked()){
                            sb.append("男性标准身高：");
                            double result=evaluateHeight(weight,"男");
                            sb.append((int)result+"厘米");
                        }else{
                            sb.append("");
                            double result=evaluateHeight(weight,"女");
                            sb.append((int)result+"厘米");
                        }
                        resulttext.setText(sb.toString());
                    }else {
                        showMessage("请选择性别");
                    }
                }else{
                    showMessage("请选择体重");
                }
            }
        });
    }
    private  void showMessage(String message){
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    alert.show();

    }
    private double evaluateHeight(double weight,String sex){
        double height;
        if(sex=="男"){
            height=170-(62-weight)/0.6;
        }else{
            height=158-(52-weight)/0.5;
        }
        return  height;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}