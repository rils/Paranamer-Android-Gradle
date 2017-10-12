package tester.ours.com.helloworld;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.Paranamer;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Instrumentation test, which will execute on an Android device.
 * Call  test class by instrumentaion as shown below
 * adb shell am instrument -w -r   -e debug true -e class tester.ours.com.helloworld.ExampleInstrumentedTest#test tester.ours.com.helloworld.test/android.support.test.runner.AndroidJUnitRunner
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void test() throws Exception {
        help();
    }

    public String help() {
        StringBuilder methods = new StringBuilder();
        Paranamer paranamer = new CachingParanamer();
        try {
            String cl_str = Test.class.getName();

            Class<?> c;
            c = Class.forName(cl_str);
            Method[] allMethods = c.getDeclaredMethods();
            for (Method m : allMethods) {
                //paranamer usage
                String[] parameterNames = paranamer.lookupParameterNames(m, false);
                methods.setLength(0);
                methods.append(m.getName()).
                        append("\n-------\n");
                if (m.getParameterTypes().length != 0) {
                    methods.append("Parameter Types-\n");
                    methods.append(Arrays.toString(m.getGenericParameterTypes())).append("\n");
                    methods.append("Paranamer data-\n").append(Arrays.toString(parameterNames)).append("\n\n");
                }
                Log.i("paranamer", String.valueOf(methods));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return String.valueOf(methods);
    }
}
