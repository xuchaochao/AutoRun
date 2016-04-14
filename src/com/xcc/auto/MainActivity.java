package com.xcc.auto;

import java.io.DataOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

	private Button runButton;
	private Button dialButton;
	private EditText jarname;
	private EditText testname;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runButton=(Button)findViewById(R.id.run);
        dialButton=(Button)findViewById(R.id.dial);
        jarname=(EditText)findViewById(R.id.jarname);
        testname=(EditText)findViewById(R.id.testname);

        runButton.setOnClickListener(this);
        dialButton.setOnClickListener(this);
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void exec(String command) throws IOException {	
        
  	    Process process = Runtime.getRuntime().exec("su");
  		DataOutputStream os = new DataOutputStream(process.getOutputStream());      
//        for(int i=0;i<3;i++){
  		os.writeBytes(command+"\n"); 
  		toastStart();
//        }
        os.writeBytes("exit\n");  
        
        os.flush();
        
  	 
    }
    
    
   public void toastStart(){
	   Toast.makeText(getApplication(), "开始运行...", Toast.LENGTH_LONG).show();
	   
   }
  
    
    @Override
    public void onClick(View arg0) {
    	switch (arg0.getId()) {
    	case R.id.dial:
    		try {
    			exec("uiautomator runtest Demo.jar --nohup -c com.jikexueyuan.demo.Demo1#testPhone");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		break;
    	
    	case R.id.run:
    		String jarnameString=jarname.getText().toString();
    		String testnameString=testname.getText().toString();
    		try {
    			exec("uiautomator runtest "+jarnameString+".jar --nohup -c "+testnameString);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		break;
    	default:
    		break;
    	}
    }
}
