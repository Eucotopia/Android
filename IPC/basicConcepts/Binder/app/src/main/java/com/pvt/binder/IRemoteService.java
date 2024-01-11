package com.pvt.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LIWEI
 */
public class IRemoteService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        binder.linkToDeath(() -> Log.i("IRemoteService", "断开了"), 0);
        return binder;
    }

    private Binder binder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            ArrayList<Book> books = new ArrayList<>();
            books.add(new Book(1, "当下的力量"));
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            return;
        }
    };
}
