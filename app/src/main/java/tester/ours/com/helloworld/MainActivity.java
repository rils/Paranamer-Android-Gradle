package tester.ours.com.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.Paranamer;

import java.lang.reflect.Method;
import java.util.Arrays;
/*
Paranamer Demonstration
Run this app . And press button Lookup. It will display the parameter names and types
 */
public class MainActivity extends AppCompatActivity {
    private String tag = "paranamer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button b = (Button) findViewById(R.id.lookup);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final TextView helloTextView = (TextView) findViewById(R.id.textView);
                helloTextView.setText(help());
                // Code here executes on main thread after user presses button
            }
        });
    }

    //sample method 1
    public void method1(String para1, int para2) {
        Log.i(tag, "method1");
    }

    //sample method 1
    public void method2(int parameter1, boolean parameter2) {
        Log.i(tag, "method1");
    }

    public String help() {
        StringBuilder method_data = new StringBuilder();
        Paranamer paranamer = new CachingParanamer();
        try {
            String cl_str = Testing.class.getName();

            Class<?> c;
            c = Class.forName(cl_str);
            Method[] allMethods = c.getDeclaredMethods();
            for (Method m : allMethods) {
                method_data.setLength(0);
                //paranamer usage
                String[] parameterNames = paranamer.lookupParameterNames(m, false);
                method_data.append(m.getName()).
                        append("\n-------\n");
                if (m.getParameterTypes().length != 0) {
                    method_data.append("Param Types-\n");
                    method_data.append(Arrays.toString(m.getGenericParameterTypes())).append("\n");
                    method_data.append("Paranamer data-\n").append(Arrays.toString(parameterNames)).append("\n\n");
                }
                Log.i(tag, m.getName() + "-" + Arrays.toString(m.getParameterTypes()));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return String.valueOf(method_data);
    }
}
