package ps.center.utils.exception;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ps.center.R;
import ps.center.application.exception.ExceptionLogsBean;

/* loaded from: classes.jar:ps/center/utils/exception/ExceptionLogsActivity.class */
public class ExceptionLogsActivity extends Activity {
    private RecyclerView bugsList;
    private BugsListAdapter adapter;

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) ExceptionLogsActivity.class));
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_exception_event_activity);
        this.bugsList = findViewById(R.id.bugsList);
        initData();
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void initData() {
        this.adapter = new BugsListAdapter(this, ExceptionLogsBean.getExceptionLogs());
        this.bugsList.setLayoutManager(new LinearLayoutManager(this));
        this.bugsList.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }
}