// IBookManager.aidl
package com.pvt.binder;
import com.pvt.binder.Book;
// Declare any non-default types here with import statements

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}