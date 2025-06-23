package ps.center.business.http.base;

import ps.center.business.http.AiDraw;
import ps.center.business.http.Bot;
import ps.center.business.http.Collect;
import ps.center.business.http.Common;
import ps.center.business.http.Config;
import ps.center.business.http.FaceChange;
import ps.center.business.http.OSS;
import ps.center.business.http.Service;
import ps.center.business.http.Share;
import ps.center.business.http.Street;

/* loaded from: classes.jar:ps/center/business/http/base/BusHttp.class */
public class BusHttp {
    public static Config config() {
        return new Config();
    }

    public static Bot bot() {
        return new Bot();
    }

    public static OSS oss() {
        return new OSS();
    }

    public static Street street() {
        return new Street();
    }

    public static Share share() {
        return new Share();
    }

    public static AiDraw aiDraw() {
        return new AiDraw();
    }

    public static Collect collect() {
        return new Collect();
    }

    public static Common common() {
        return new Common();
    }

    public static Service service() {
        return new Service();
    }

    public static FaceChange faceChange() {
        return new FaceChange();
    }
}