package com.bitcamp.util;

public class AbstractList {
  // 역할:
  // - List 인터페이스를구현한다.
  // 인터페이스의 메서드 중에서 서브 클래스에 공통으로 상속해줄메서드를 일부 구현한다.
  // 물론 서브 클래스에상속해줄 필드가 있다면 이 클래스에서 미리 추가한다.
  public abstract class AbstractList implements List{

    // 목록에 저장된 항목의 개수를 저장하는 필드는 모든 서브 클래스가 가져야 할 필드이다.
    /// 또한 이 필드는 서브 클래스에서 접근 가능하도록 개방한다.
    protected int size; // 같은 패키지에 소속된 클래스+서브 클래스는 직접 접근 가능!

    // 서브 클래스에게 상속해 줄 메서드를 미리 구현
    public int size() {
      return size;
    }
  }

  // 하위 클래스들에게 공통으로물려줄 메서드 들을 여기서 정의.
  // List 인터페이스의 나머지메서드는 추상 메서드인채로 그냥 둔다.
  // 왜? 서브 클래스마다 구현방법이 다르기 때문이다.
}
