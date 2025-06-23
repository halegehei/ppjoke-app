package ps.center.application.exception;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import ps.center.utils.Save;

/* loaded from: classes.jar:ps/center/application/exception/ExceptionLogsBean.class */
public class ExceptionLogsBean implements Parcelable {
    static final String exceptionLogsBeanTag = "mmkv_exceptionLogsBeanTag";
    private List<ExceptionInfo> exceptionInfoList;
    public static final Creator<ExceptionLogsBean> CREATOR = new Creator<ExceptionLogsBean>() { // from class: ps.center.application.exception.ExceptionLogsBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExceptionLogsBean createFromParcel(Parcel in) {
            return new ExceptionLogsBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExceptionLogsBean[] newArray(int size) {
            return new ExceptionLogsBean[size];
        }
    };

    public static List<ExceptionInfo> getExceptionLogs() {
        ExceptionLogsBean exceptionLogsBean = (ExceptionLogsBean) Save.instance.getParcelable(exceptionLogsBeanTag, ExceptionLogsBean.class);
        return exceptionLogsBean == null ? new ExceptionLogsBean().exceptionInfoList : exceptionLogsBean.exceptionInfoList;
    }

    public static void putExceptionInfo(String time, String content) {
        ExceptionLogsBean exceptionLogsBean = (ExceptionLogsBean) Save.instance.getParcelable(exceptionLogsBeanTag, ExceptionLogsBean.class);
        if (exceptionLogsBean == null) {
            exceptionLogsBean = new ExceptionLogsBean();
        }
        if (exceptionLogsBean.exceptionInfoList.size() >= 30) {
            exceptionLogsBean.exceptionInfoList.remove(29);
        }
        exceptionLogsBean.exceptionInfoList.add(0, new ExceptionInfo(content, time));
        Save.instance.put(exceptionLogsBeanTag, (Parcelable) exceptionLogsBean);
    }

    public ExceptionLogsBean() {
        if (this.exceptionInfoList == null) {
            this.exceptionInfoList = new ArrayList();
        }
    }

    /* loaded from: classes.jar:ps/center/application/exception/ExceptionLogsBean$ExceptionInfo.class */
    public static class ExceptionInfo implements Parcelable {
        public String content;
        public String time;
        public static final Creator<ExceptionInfo> CREATOR = new Creator<ExceptionInfo>() { // from class: ps.center.application.exception.ExceptionLogsBean.ExceptionInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ExceptionInfo createFromParcel(Parcel in) {
                return new ExceptionInfo(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ExceptionInfo[] newArray(int size) {
                return new ExceptionInfo[size];
            }
        };

        public ExceptionInfo(String content, String time) {
            this.content = content;
            this.time = time;
        }

        protected ExceptionInfo(Parcel in) {
            this.content = in.readString();
            this.time = in.readString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.content);
            dest.writeString(this.time);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }
    }

    protected ExceptionLogsBean(Parcel in) {
        this.exceptionInfoList = in.createTypedArrayList(ExceptionInfo.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.exceptionInfoList);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}