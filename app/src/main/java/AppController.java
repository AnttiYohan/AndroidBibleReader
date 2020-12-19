import android.app.Application;

import java.util.ArrayList;

public class AppController extends Application
{
    private static AppController mInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this;
    }

    @Override
    public void onTerminate()
    {
        super.onTerminate();
    }



}
