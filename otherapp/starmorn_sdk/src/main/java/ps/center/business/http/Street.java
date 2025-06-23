package ps.center.business.http;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import ps.center.business.bean.street.AbroadScenic;
import ps.center.business.bean.street.City;
import ps.center.business.bean.street.Country;
import ps.center.business.bean.street.GlobalLiveVideo;
import ps.center.business.bean.street.HomeClassify;
import ps.center.business.bean.street.HomeScenic;
import ps.center.business.bean.street.HomeStreet;
import ps.center.business.bean.street.HomeStreetScape;
import ps.center.business.bean.street.HotScenic;
import ps.center.business.bean.street.Province;
import ps.center.business.bean.street.Scenic;
import ps.center.business.http.base.BusinessBaseHttp;
import ps.center.business.http.base.BusinessRequest;
import ps.center.business.route.StreetUrls;
import ps.center.library.http.base.HttpObserver;
import ps.center.library.http.base.Result;

/* loaded from: classes.jar:ps/center/business/http/Street.class */
public class Street extends BusinessBaseHttp {
    public void byHomeClassifyList(final Result<List<HomeClassify>> result) {
        BusinessRequest baseRequest = new BusinessRequest(StreetUrls.byHomeClassifyList, "get", 7);
        getStreetApis().byHomeClassifyList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<HomeClassify>>() { // from class: ps.center.business.http.Street.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<HomeClassify> homeClassifies) {
                if (result != null) {
                    result.success(homeClassifies);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void byHomeScenicList(String classify_id, String page, final Result<List<HomeScenic>> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("classify_id", classify_id);
        params.add("page", page);
        BusinessRequest baseRequest = new BusinessRequest(StreetUrls.byHomeScenicList, "get", params, 7);
        getStreetApis().byHomeScenicList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<HomeScenic>>() { // from class: ps.center.business.http.Street.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<HomeScenic> homeClassifies) {
                if (result != null) {
                    result.success(homeClassifies);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void byGlobalLiveVideoList(String page, final Result<List<GlobalLiveVideo>> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("page", page);
        BusinessRequest baseRequest = new BusinessRequest(StreetUrls.byGlobalLiveVideoList, "get", params, 7);
        getStreetApis().byGlobalLiveVideoList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<GlobalLiveVideo>>() { // from class: ps.center.business.http.Street.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<GlobalLiveVideo> homeClassifies) {
                if (result != null) {
                    result.success(homeClassifies);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void byHotScenicList(String page, final Result<List<HotScenic>> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("page", page);
        BusinessRequest baseRequest = new BusinessRequest(StreetUrls.byHotScenicList, "get", params, 7);
        getStreetApis().byHotScenicList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<HotScenic>>() { // from class: ps.center.business.http.Street.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<HotScenic> hotScenicList) {
                if (result != null) {
                    result.success(hotScenicList);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void byHomeStreetScape(final Result<List<HomeStreetScape>> result) {
        BusinessRequest baseRequest = new BusinessRequest(StreetUrls.byHomeStreetscape, "get", 7);
        getStreetApis().byHomeStreetScape(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<HomeStreetScape>>() { // from class: ps.center.business.http.Street.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<HomeStreetScape> homeStreetScapeList) {
                if (result != null) {
                    result.success(homeStreetScapeList);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void byProvinceList(final Result<List<Province>> result) {
        BusinessRequest baseRequest = new BusinessRequest(StreetUrls.byProvinceList, "get", 7);
        getStreetApis().byProvinceList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<Province>>() { // from class: ps.center.business.http.Street.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<Province> provinceList) {
                if (result != null) {
                    result.success(provinceList);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void byCityList(String province_id, String page, final Result<List<City>> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("province_id", province_id);
        params.add("page", page);
        BusinessRequest baseRequest = new BusinessRequest(StreetUrls.byCityList, "get", params, 7);
        getStreetApis().byCityList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<City>>() { // from class: ps.center.business.http.Street.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<City> cityList) {
                if (result != null) {
                    result.success(cityList);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void byScenicList(String city_id, String page, final Result<List<Scenic>> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("city_id", city_id);
        params.add("page", page);
        BusinessRequest baseRequest = new BusinessRequest(StreetUrls.byScenicList, "get", params, 7);
        getStreetApis().byScenicList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<Scenic>>() { // from class: ps.center.business.http.Street.8
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<Scenic> scenicList) {
                if (result != null) {
                    result.success(scenicList);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void byCountryList(final Result<List<Country>> result) {
        BusinessRequest baseRequest = new BusinessRequest(StreetUrls.byCountryList, "get", 7);
        getStreetApis().byCountryList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<Country>>() { // from class: ps.center.business.http.Street.9
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<Country> countryList) {
                if (result != null) {
                    result.success(countryList);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void getAbroadScenicList(String country_id, String page, final Result<List<AbroadScenic>> result) {
        BusinessRequest.Params params = new BusinessRequest.Params();
        params.add("country_id", country_id);
        params.add("page", page);
        BusinessRequest baseRequest = new BusinessRequest(StreetUrls.getAbroadScenicList, "get", params, 7);
        getStreetApis().getAbroadScenicList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<AbroadScenic>>() { // from class: ps.center.business.http.Street.10
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<AbroadScenic> abroadScenicList) {
                if (result != null) {
                    result.success(abroadScenicList);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }

    public void getHomeStreetList(final Result<List<HomeStreet>> result) {
        BusinessRequest baseRequest = new BusinessRequest(StreetUrls.getHomeStreetList, "get", 7);
        getStreetApis().getHomeStreetList(getRequestData(baseRequest)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpObserver<List<HomeStreet>>() { // from class: ps.center.business.http.Street.11
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // ps.center.library.http.base.HttpObserver
            public void success(List<HomeStreet> homeStreets) {
                if (result != null) {
                    result.success(homeStreets);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void err(int i, String s) {
                if (result != null) {
                    result.err(i, s);
                }
            }

            @Override // ps.center.library.http.base.HttpObserver
            protected void end() {
                if (result != null) {
                    result.end();
                }
            }
        });
    }
}