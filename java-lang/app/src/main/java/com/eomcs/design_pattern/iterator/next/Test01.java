// iterator 디자인 패턴 : 데이터 목록에서 값을 꺼내는 것을 별도의 객체로 분리하는 설계 방식 
package com.eomcs.design_pattern.iterator.next;

// Iterator 디자인 패턴
// => 데이터 목록을 관리하는 방식에 상관없이 일관된 방식으로 데이터를 꺼낼 수 있게 해주는 설계 기법
// => 즉 데이터 목록을 관리하는 객체를 직접 사용하여 값을 꺼내는 것이 아니라,
//    값을 꺼내는 주는 별도의 객체의 도움을 받아 값을 꺼낸다.
// => 값을 꺼내주는 객체를 "Iterator"라 부른다.
//    값을 꺼내주는 객체의 사용법을 통일하기 위하여 인터페이스로 사용 규칙을 정의한다.
// => 각각의 데이터 목록 관리 객체는 Iterator 규칙에 따라 값을 꺼내는 객체를 리턴한다.
public class Test01 {

  public static void main(String[] args) {
    ArrayList<String> list1 = new ArrayList<>();
    list1.add("aaa");
    list1.add("bbb");
    list1.add("ccc");
    list1.add("ddd");

    // 목록에서 값 꺼내기
    // 1) ArrayList 에서 값 꺼내기
    //    for(int i=0;i<list1.size();i++) {
    //      System.out.println(list1.get(i));
    //    }
    System.out.println("-----------------------------------------------");

    Iterator<String> iterator = list1.iterator();
    while(iterator.hasNext()) {
      System.out.println(iterator.next());
    }
    System.out.println("-----------------------------------------------");
  }

  // 결론!
  // - 자료 구조에 따라 데이터를 꺼내는 방식이 다르다!
  // - 데이터 조회에 일관성이 없다!
  //
  // 해결책!
  // 데이터 조회하는 일은 별도의 객체에게 맡기자!
  // - 데이터 조회를 별도의 객체로 분리하자!
  // - 데이터 조회 방식을 통일하기 위해 인터페이스로 조회 방식을 규격화 하자!

}









