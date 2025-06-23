package ps.center.views.layout.overscroll;

/* loaded from: classes.jar:ps/center/views/layout/overscroll/ListenerStubs.class */
public interface ListenerStubs {

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/ListenerStubs$OverScrollStateListenerStub.class */
    public static class OverScrollStateListenerStub implements IOverScrollStateListener {
        @Override // ps.center.views.layout.overscroll.IOverScrollStateListener
        public void onOverScrollStateChange(IOverScrollDecor decor, int oldState, int newState) {
        }
    }

    /* loaded from: classes.jar:ps/center/views/layout/overscroll/ListenerStubs$OverScrollUpdateListenerStub.class */
    public static class OverScrollUpdateListenerStub implements IOverScrollUpdateListener {
        @Override // ps.center.views.layout.overscroll.IOverScrollUpdateListener
        public void onOverScrollUpdate(IOverScrollDecor decor, int state, float offset) {
        }
    }
}