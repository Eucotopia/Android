// IBookManager.aidl
package com.pvt.aidl;
import com.pvt.aidl.Book;
// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void addBook(in Book book);
    List<Book> getBooks();
}