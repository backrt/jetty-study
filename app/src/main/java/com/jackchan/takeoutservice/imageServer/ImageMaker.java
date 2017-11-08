package com.jackchan.takeoutservice.imageServer;

import android.content.Context;

import com.jackchan.takeoutservice.servlet2.business.LocalAppProvider;
import com.jackchan.takeoutservice.servlet2.proper.LocalAppProper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：lhb on 2017/11/4 16:21
 * Mail：lihaibo@znds.com
 */

public class ImageMaker {

    public static void generateImageWithApkFile(final Context context) {

        Observable.just("app_icon")
                .map(new Function<String, List<LocalAppProper>>() {
                    @Override
                    public List<LocalAppProper> apply(@NonNull String s) throws Exception {
                        return LocalAppProvider.getLocalUninstalledApps(context);
                    }
                })
                .filter(new Predicate<List<LocalAppProper>>() {
                    @Override
                    public boolean test(@NonNull List<LocalAppProper> localAppPropers) throws Exception {
                        return localAppPropers != null && !localAppPropers.isEmpty();
                    }
                })
                .flatMap(new Function<List<LocalAppProper>, ObservableSource<LocalAppProper>>() {
                    @Override
                    public ObservableSource<LocalAppProper> apply(@NonNull List<LocalAppProper> localAppPropers) throws Exception {
                        return Observable.fromIterable(localAppPropers);
                    }
                })
                .map(new Function<LocalAppProper, String>() {
                    @Override
                    public String apply(@NonNull LocalAppProper proper) throws Exception {
                        return ImageUtil.drawableToFile(context, proper.getDrawable(), proper.getPackagename());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        System.out.println("onNext = " + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");
                    }
                });

    }

}
