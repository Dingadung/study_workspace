package com.eomcs.quiz.ex02;

// 출처: codefights.com
//
// 배열의 전체 길이를 L이라고 하자.
// 배열을 절반(L/2)으로 나눌 때, 앞쪽 부분과 뒤쪽 부분의 위치를 바꿔라.
// 예)
// [2, 4, 5, 6, 4, 3, 7, 8] => [4, 3, 7, 8, 2, 4, 5, 6]
//
// [시간 복잡도]
// - ?
//
public class Test02 {

  public static void main(String[] args) {
    int[] values = {2, 4, 5, 6, 4, 3, 7, 8};
    changeValuePosition(values);

    int[] results = {4, 3, 7, 8, 2, 4, 5, 6};

    for (int i = 0; i < results.length; i++) {
      if (values[i] != results[i]) {
        System.out.println(false);
        return;
      }
    }
    System.out.println(true);
  }

  static void changeValuePosition(int[] values) {
    // 이 메서드를 완성하시오!
    int[]t1 = new int[values.length];
    int[]t2 = new int[values.length];
    int len = values.length;
    int half = values.length /2;
    if(len %2==0) {
      for(int i=0;i<half;i++) {
        t1[i] = values[i];
      }
      for(int i=0;i<half;i++) {
        t2[i] = values[half +i];
      }
      for(int i=0;i<half;i++) {
        values[i] = t2[i];
      }
      for(int i=0;i<half;i++) {
        values[half +i] = t1[i];
      }
    }else {
      for(int i=0;i<half;i++) {
        t1[i] = values[i];
      }
      for(int i=0;i<half;i++) {
        t2[i] = values[half + 1 + i];
      }
      for(int i=0;i<half;i++) {
        values[i] = t2[i];
      }
      for(int i=0;i<half;i++) {
        values[half + 1 + i] = t1[i];
      }
    }
  }
}
