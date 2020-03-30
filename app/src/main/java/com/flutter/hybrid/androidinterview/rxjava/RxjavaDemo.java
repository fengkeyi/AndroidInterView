package com.flutter.hybrid.androidinterview.rxjava;

import android.graphics.BitmapFactory;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class RxjavaDemo {

    private LruCache lruCache;

    BitmapFactory.Options options;

    public void ints() {
        options.inJustDecodeBounds = true;
        options.inSampleSize = 10;
    }


    public void simple(final String cache, final String disk, String network) {
        Observable<String> cacheObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                if (TextUtils.isEmpty(cache)) {
                    e.onComplete();
                }else {
                    e.onNext(cache);
                }
            }
        });
        Observable<String> diskObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                if (TextUtils.isEmpty(disk)) {
                    e.onComplete();
                } else {
                    e.onNext(disk);
                }
            }
        });
        Observable networkObservable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                e.onNext("data from network");
            }
        });
        Observable.concat(cacheObservable,diskObservable,networkObservable)
                .firstElement()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String o) throws Exception {
                        Log.i("Cache", "cache from "+o);
                    }
                });
    }

    public void create() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext("1");
                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void map() {
        Observable.just(1,2,3).map(new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) throws Exception {
                return integer*10+"";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public static void flatmap() {
        Observable.just(1,2,3,4,5).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(integer+"");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.i("Rxjava", "flatmap:" + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void groupby() {
        Observable.just(1,2,3,4,5,6,7).groupBy(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                return integer%3;
            }
        }).subscribe(new Observer<GroupedObservable<Integer, Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(final GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
                integerIntegerGroupedObservable.subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer data) {
                        Log.i("groupby", "groupid:" + integerIntegerGroupedObservable.getKey() + " data:" + data);
                        /**
                         *  1 1
                         *  2 2
                         *  0 3
                         *  1 4
                         *  2 5
                         *  0 6
                         *  1 7
                         */
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void buffer() {
        Observable.range(1,5)
                .buffer(2).subscribe(new Observer<List<Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Integer> integers) {
                /**
                 *  [1 2]
                 *  [3 4]
                 *  [5]
                 */
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    public static void debounce() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    emitter.onNext(i);
                }
            }
        }).debounce(1, TimeUnit.SECONDS)
        .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                // 1 9
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static void zip() {
        Observable<Integer> observable1 = Observable.just(1, 2, 3);
        Observable<Integer> observable2 = Observable.just(4, 5, 6, 7);
        Observable.zip(observable1, observable2, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer*10+integer2;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                //14 25 36
                Log.i("Zip", "integer:"+integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static void merge() {
        Observable<Integer> observable1 = Observable.just(1, 2, 3);
        Observable<Integer> observable2 = Observable.just(4, 5, 6, 7);
        Observable.merge(observable1,observable2).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                //1 2 3 4 5 6 7
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static void combineLast() {
        Observable<Integer> observable1 = Observable.just(1, 2, 3);
        Observable<Integer> observable2 = Observable.just(4, 5, 6, 7);
        Observable.combineLatest(observable1, observable2, new BiFunction<Integer, Integer, String>() {
            @Override
            public String apply(Integer integer, Integer integer2) throws Exception {
                Log.i("combinelast", "apply:" + integer+" "+integer2);
                return (integer*10+integer2)+"" ;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                //  7（3+4） 8（3+5） 9（3+6） 10（3+7）
                Log.i("combinelast", "onNext:" + s);
                Log.i("combinelast", "onNext:" + Process.myTid());
            }

            @Override
            public void onError(Throwable e) {
                Log.i("combinelast", "" + e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
        Schedulers.io().createWorker().schedule(new Action0() {
            @Override
            public void call() {
                Log.i("combinelast", "Schedulers:" + Process.myTid());
            }
        });
    }


}
