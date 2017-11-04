package com.jackchan.takeoutservice.imageServer;

import android.content.Context;

import com.jackchan.takeoutservice.servlet2.business.LocalAppProvider;
import com.jackchan.takeoutservice.servlet2.proper.LocalAppProper;

import java.util.List;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
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
                .map(new Function<List<LocalAppProper>, Observable<LocalAppProper>>() {
                    @Override
                    public Observable<LocalAppProper> apply(@NonNull List<LocalAppProper> localAppPropers) throws Exception {
                        return Observable.fromIterable(localAppPropers);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<Observable<LocalAppProper>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Observable<LocalAppProper> localAppProperObservable) {
                        System.out.println("onNext -- ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        Observable.just("app_icon")
//                .map(new Function<String, List<LocalAppProper>>() {
//                    @Override
//                    public List<LocalAppProper> apply(@NonNull String s) throws Exception {
//                        return LocalAppProvider.getLocalUninstalledApps(context);
//                    }
//                })
//                .filter(new Predicate<List<LocalAppProper>>() {
//                    @Override
//                    public boolean test(@NonNull List<LocalAppProper> localAppPropers) throws Exception {
//                        return localAppPropers != null && !localAppPropers.isEmpty();
//                    }
//                })
//                .map(new Function<List<LocalAppProper>, Observable<LocalAppProper>>() {
//                    @Override
//                    public Observable<LocalAppProper> apply(@NonNull List<LocalAppProper> localAppPropers) throws Exception {
//                        return Observable.fromIterable(localAppPropers);
//                    }
//                })
//                .map(new Function<Observable<LocalAppProper>, String>() {
//                    @Override
//                    public String apply(@NonNull Observable<LocalAppProper> localAppProperObservable) throws Exception {
//                        LocalAppProper localApp = localAppProperObservable.blockingLast();
//                        return ImageUtil.drawableToFile(context, localApp.getDrawable(), localApp.getPackagename());
//                    }
//                })
//                .filter(new Predicate<String>() {
//                    @Override
//                    public boolean test(@NonNull String s) throws Exception {
//                        return !TextUtils.isEmpty(s);
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull String s) {
//                        System.out.println("store app icon in file ==" + s);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        System.out.println(e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });


    }

}
