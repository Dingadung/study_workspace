package com.eomcs.design_pattern.iterator.after4;

public class Stack<E> {

  public static final int DEFAULT_SIZE = 5;

  Object[] list;
  int size;

  public Stack() {
    list = new Object[DEFAULT_SIZE];
  }

  public void push(E value) {
    if (size == list.length) {
      Object[] arr = new Object[list.length + (list.length >> 1)];
      for (int i = 0; i < list.length; i++) {
        arr[i] = list[i];
      }
      list = arr;
    }

    list[size++] = value;
  }

  @SuppressWarnings("unchecked")
  public E pop() {
    if (size == 0)
      return null;

    return (E) list[--size];
  }

  public boolean empty() {
    return size == 0;
  }

  public int size() {
    return this.size;
  }

  // Iterator 구현체를 제공한다.
  public Iterator<E> iterator(){
    //Stack에서 데이터를 꺼내줄 객체//
    class StackIterator<T> implements Iterator<T> {
      @Override
      public boolean hasNext() {
        return !Stack.this.empty(); // 유효한 인덱스!
      }
      @SuppressWarnings("unchecked")
      @Override
      public T next() {
        return (T)Stack.this.pop();
      }
    }
    return new StackIterator<E>();
  }


}






