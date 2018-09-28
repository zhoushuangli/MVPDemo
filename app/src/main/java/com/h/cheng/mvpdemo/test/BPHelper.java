package com.h.cheng.mvpdemo.test;

import com.h.cheng.mvpdemo.api.ApiRetrofit;
import com.h.cheng.mvpdemo.api.ApiServer;
import com.h.cheng.mvpdemo.base.BaseObserver;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者： ch
 * 时间： 2018/6/2 0002-下午 3:57
 * 描述：
 * 来源：
 */


public class BPHelper<V extends BVHelper> {

    private CompositeDisposable compositeDisposable;
    public V helper;


    protected ApiServer apiServer = ApiRetrofit.getInstance().getApiService();

    public BPHelper(V helper) {
        this.helper = helper;
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        removeDisposable();
    }


    public void addDisposable(Observable<?> observable, BOHelper observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer));


    }

    public void removeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
