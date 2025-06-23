package ps.center.utils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.Timer;
import java.util.TimerTask;
import ps.center.application.BuildConfig;

/* loaded from: classes.jar:ps/center/utils/Test.class */
public class Test {

    /* loaded from: classes.jar:ps/center/utils/Test$MyDisposable.class */
    public interface MyDisposable extends Disposable {
        public static final String taskName = null;
    }

    public void test() {
        Observable.create(new ObservableOnSubscribe<String>() { // from class: ps.center.utils.Test.2
            public void subscribe(final ObservableEmitter<String> observableEmitter) {
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() { // from class: ps.center.utils.Test.2.1
                    int index = 0;

                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        this.index++;
                        System.out.println(this.index);
                        observableEmitter.onNext(this.index + BuildConfig.VERSION_NAME);
                        if (this.index == 5) {
                            observableEmitter.onComplete();
                        }
                        if (this.index == 6) {
                            if (!observableEmitter.isDisposed()) {
                                observableEmitter.onError(new Throwable("over"));
                                timer.cancel();
                            } else {
                                System.out.println("over end");
                            }
                        }
                    }
                }, 3000L, 3000L);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<String>() { // from class: ps.center.utils.Test.1
            public void onSubscribe(Disposable disposable) {
                LogUtils.e("开始处理");
            }

            public void onNext(String s) {
                ToastUtils.show(s);
            }

            public void onError(Throwable throwable) {
                ToastUtils.show(throwable.getMessage());
            }

            public void onComplete() {
                LogUtils.e("end");
            }
        });
    }
}