# 정적 키워드 static
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Keyword-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/static-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 예약어 static
  **정적 또는 고정**이란 의미를 가진 `static`은 Java에서 제공하는 예약어로 변수, 클래스, 메서드 등 여러 구성 요소 앞에 선언이 가능하다. 
  static을 붙인 요소는 클래스 로더(Class Loader)가 변환된 .class 파일을 수집해 초기화하는 시점에 별도 메모리 공간인 **Method(또는 static) 영역에 할당되며, 
  클래스 로딩이 끝난 즉시 호출이 가능**해 별다른 선언과 초기화 작업이 필요없어 빠른 속도로 사용 가능하다. 또한 static이 사용된 요소의 `스코프`는 해당 클래스 
  타입의 모든 인스턴스가 서로 공유하는 전역 공간을 가지며, `라이프타임`은 기본 자료형의 경우 JVM(Java Virtual Machine)이 종료되기전까지 할당이 지속된다.

<br>

## 2. static 적용 지점
  static 키워드는 모두 네 가지 경우에 사용할 수 있는데, 아래 항목에서 각 사용 지점에 따른 용도와 사용법에 대해 알아보자.

  * ### static 블록 ###
   `static 블록`은 클래스가 인스턴스화 될 때 단 한 번 실행되는 블록으로 초기화 블록(Initialization Block) 이라고도 불린다. 보통 클래스의 
   static 필드들을 명시적으로 초기화하기 힘든 동적인 경우 사용한다.
  
  <img width="662" alt="staticblock" src="https://user-images.githubusercontent.com/78818063/167723770-fddaa011-71e7-4201-89f8-0c07cc84a0ae.png">

  * ### static 변수 ###
  static 변수는 클래스의 필드를 메모리에 고정으로 할당시켜 **여러 인스턴스가 변수에 접근 및 공유**할 수 있도록 한다. 아래 그림과 같이 서로 
  다른 인스턴스에서 같은 메모리에 할당된 static 변수에 접근하므로 해당 변수가 반복적으로 사용되고 여러 곳에서 참조하는 상황에서 효율적이다. 
  하지만 서로 다른 인스턴스가 Write하는 경우 사이드 이팩트(Side-Effect)와 같은 동시성 문제가 발생할 수 있어 가급적 자제하는 것이 좋으며, 
  이를 방지하기 위해 `final 키워드`를 붙여 변수나 인스턴스의 불변성을 보장하는 것이 좋다.
  
  <img width="651" alt="staticvaribles" src="https://user-images.githubusercontent.com/78818063/167723781-0c2fd66f-f450-45ea-917a-0f7635978f07.png">

  * ### static 클래스 ###
  Java의 외부 클래스는 기본적으로 static이 생략되어 있어 내부 클래스를 정적으로 만들 수 있으며, 이는 **외부에서 바로 접근 가능하다**. 
  단 주의할 점은 외부 클래스의 인스턴스로 내부의 정적 클래스로는 접근이 불가능하다. 정적 클래스는 **유틸 기능이 포함된 메소드**나 static 클래스를 
  내부에서 만들고 외부에서 빌더 패턴을 적용시켜 인스턴스를 생성할 때 주로 사용한다. 해당 내용은 JavaDesignPattern 저장소에서 자세히 다루도록 한다.

  * ### static 메소드 ###      
  앞서 설명한 static 클래스와 유사한 방식으로 static 메소드는 외부에서 클래스 타입으로 바로 접근이 가능하고 내부에서는 다른 static 메소드를 통해 접근 가능하다. 
  해당 방식은 메서드를 통해 필드의 초기화가 필요한 경우나 날짜 출력과 최댓값 반환 등 **유틸리티성 메소드**를 호출할 때 많이 사용된다. 보통 클래스를 싱글톤 패턴으로 
  만들어 외부로부터 접근을 막고 인스턴스를 반환할 때 많이 활용된다. 해당 내용은 JavaDesignPattern 저장소에서 다루도록 한다.
  
  <img width="659" alt="staticmethod" src="https://user-images.githubusercontent.com/78818063/167723788-838df715-6364-4632-bc11-4e6ec225dc4a.png">

<br>

## 3. static 이슈와 접근법
  앞서 살펴본 편리성과 빠른 접근성 등의 이점과 함께 static 키워드는 단점 역시 존재한다. 아래의 항목들을 살펴보자.
  
  * ### 메모리 성능 저하 ###
    static 키워드가 포함된 오브젝트 대부분은 **애플리케이션이 끝날 때까지 라이프타임을 유지**하기 때문에 무분별한 사용은 메모리 성능을 저하시킬 우려가 있다. 

  * ### 테스트에 부적합 ###
    static 변수는 전역으로 관리되기 때문에 애플리케이션 전역에서 접근 또는 수정이 가능하다. 즉 **특정 상황에 해당 필드 값을 추론하기 어려워** 테스트에 부적합하다. 
    
  * ### 객체 지향 부적합 ###
    static 메소드는 필요한 **파라미터를 모두 외부에서 주입해야 한다**. 그리고 **클래스 내부 필드를 사용하기 위해서는 사용할 필드 또한 정적으로 선언**되어야만 한다. 
    즉 이렇게 static 메소드가 많아지면 코드는 외부에 의존적이게 되고 객체의 특성을 잘 활용할 수 없다.

  * ### 런타임 다형성 불가 ###
    static 메소드는 인스턴스를 할당해 접근하는 것이 아닌 **직접적인 호출**이 필요하며, 메소드는 오버라이딩이 불가능해 클래스의 확장이 어려워 
    객체의 다형성 원칙에 부적합하다.
    
  <br>

  위 같은 단점들 때문에 static의 무분별한 사용은 여러 측면에서 비효율적인 상황을 불러올 수 있다. 가급적 반복적으로 사용되고 여러 곳에서 공유되는 상수에만 사용하고 
  final 키워드를 함께 선언해 불변성을 보장하는 것이 좋다. 또한 static 변수가 더 이상 필요가 없어지면 해당 시점에 변수를 null 값으로 재참조시켜(Garbage Collection) 
  런타임 중 할당을 해제할 수도 있으니 참고하자.
  
  <br>
