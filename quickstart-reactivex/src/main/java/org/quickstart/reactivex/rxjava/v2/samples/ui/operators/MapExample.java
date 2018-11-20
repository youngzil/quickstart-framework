package org.quickstart.reactivex.rxjava.v2.samples.ui.operators;

import java.util.List;

import org.quickstart.reactivex.rxjava.v2.samples.model.ApiUser;
import org.quickstart.reactivex.rxjava.v2.samples.model.User;
import org.quickstart.reactivex.rxjava.v2.samples.utils.AppConstant;
import org.quickstart.reactivex.rxjava.v2.samples.utils.Utils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.java.Log;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class MapExample {

    private static final String TAG = MapExample.class.getSimpleName();
    
    public static void main(String[] args) throws Exception{
        new MapExample().doSomeWork();
        System.in.read();
    }

    /*
    * Here we are getting ApiUser Object from api server
    * then we are converting it into User Object because
    * may be our database support User Not ApiUser Object
    * Here we are using Map Operator to do that
    */
    private void doSomeWork() {
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                // .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<ApiUser>, List<User>>() {

                    @Override
                    public List<User> apply(List<ApiUser> apiUsers) {
                        return Utils.convertApiUserListToUserList(apiUsers);
                    }
                }).subscribe(getObserver());
    }

    private Observable<List<ApiUser>> getObservable() {
        return Observable.create(new ObservableOnSubscribe<List<ApiUser>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ApiUser>> e) {
                if (!e.isDisposed()) {
                    e.onNext(Utils.getApiUserList());
                    e.onComplete();
                }
            }
        });
    }

    private Observer<List<User>> getObserver() {
        return new Observer<List<User>>() {

            @Override
            public void onSubscribe(Disposable d) {
                System.out.println(" onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(List<User> userList) {
                System.out.println(" onNext : " + userList.size());
                System.out.println(AppConstant.LINE_SEPARATOR);
                for (User user : userList) {
                    System.out.println(" firstname : " + user.firstname);
                    System.out.println(AppConstant.LINE_SEPARATOR);
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(" onError : " + e.getMessage());
                System.out.println(AppConstant.LINE_SEPARATOR);
            }

            @Override
            public void onComplete() {
                System.out.println(" onComplete");
                System.out.println(AppConstant.LINE_SEPARATOR);
            }
        };
    }

}
