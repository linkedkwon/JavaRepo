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
  **정적인 또는 고정**이란 의미를 가진 `static`은 Java에서 제공하는 예약어로 변수, 클래스, 메서드 등의 앞에 선언할 수 있다.  
  static을 붙인 오브젝트들은 클래스 로더(Class Loader)가 변환된 .class 파일을 수집해 초기화하는 시점에 별도 메모리  
  공간인 **Method(또는 static) 영역에 할당되며, 클래스 로딩이 끝난 즉시 호출이 가능**하다. 별다른 선언과 초기화 작업이  
  필요없어 빠른 속도로 사용이 가능하며, static 메서드의 경우는 인스턴스를 만들지 않고 바로 호출이 가능하다. static이  
  사용된 오브젝트의 `스코프`는 해당 클래스 타입의 모든 인스턴스가 서로 공유하는 전역 공간을 가지며, `라이프타임`은 기본  
  자료형의 경우 JVM(Java Virtual Machine)이 종료되기전까지 계속 할당이 지속된다.

<br>

## 2. static 적용 지점
  static 예약어는 총 네 가지 경우에 사용할 수 있는데, 각각의 지점에 따른 용도와 사용성에 대해 알아보자.

  * ### static 블록 ###
  <img width="662" alt="staticblock" src="https://user-images.githubusercontent.com/78818063/167723770-fddaa011-71e7-4201-89f8-0c07cc84a0ae.png">

  `static 블록`은 해당 클래스가 인스턴스화 될 때 단 한 번 실행되는 블록으로 초기화 블록(Initialization Block)  
  이라고도 불린다. 보통 명시적으로 클래스의 static 필드들을 모두 초기화하기 힘든 동적인 경우에 사용한다.

  * ### static 변수 ###
  <img width="651" alt="staticvaribles" src="https://user-images.githubusercontent.com/78818063/167723781-0c2fd66f-f450-45ea-917a-0f7635978f07.png">

  static 변수는 클래스 내부 필드를 메모리에 고정으로 할당시켜 **여러 인스턴스가 해당 변수를 접근 및 공유**할 수 있도록  
  한다. 위 그림과 같이 서로 다른 인스턴스에서 같은 메모리의 할당된 변수에 접근하므로 해당 리터럴이 반복적으로 사용되며  
  여러 곳에서 참조하는 상황에서 효율적이다. 하지만 서로 다른 인스턴스가 Write하는 경우 동시성 문제가 발생할 수 있어    
  가급적 자제하는 것이 좋으며, 이를 방지하기 위해 `final 키워드`를 붙여 상수를 활용할 때 사용하는 것이 바람직하다.  

  * ### static 클래스 ###
  Java의 외부 클래스는 기본적으로 static이 생략되어 있어 내부 클래스를 정적으로 만들 수 있는데, **외부에서 바로  
  접근이 가능하다**. 단 주의할 점은 외부 클래스의 인스턴스로 해당 정적 내부 클래스에는 접근이 불가능하다. 보통 정적  
  클래스는 **유틸 기능이 포함된 메소드**를 만들 때 사용한다. 또한 static class를 내부에서 만들어 외부에서 빌더  
  패턴을 적용해 인스턴스를 만들 때 많이 사용한다. 해당 내용은 JavaDesignPattern 저장소에서 자세히 다루도록 한다.

  * ### static 메소드 ###
  <img width="659" alt="staticmethod" src="https://user-images.githubusercontent.com/78818063/167723788-838df715-6364-4632-bc11-4e6ec225dc4a.png">
      
  앞서 설명한 static class와 유사한 방식으로 외부에서 클래스 타입으로 바로 접근이 가능고 내부에서는 다른  
  static 메소드를 통해 접근이 가능하다. 해당 방식은 특수한 메서드 사용에 초기화가 필요한 경우와 날짜 출력과  
  최댓값 반환 같은 **유틸리티성 메소드**를 호출할 때 많이 사용되어 진다. 보통 클래스를 싱글톤 패턴으로 만들어 외부에서  
  접근을 막고 해당 인스턴스를 반환해줄 때 활용된다. 해당 내용은 JavaDesignPattern 저장소에서 다루도록 한다.

<br>

## 3. static 이슈와 접근법
  앞서 살펴본 편리성과 빠른 접근성 등의 이점과 함께 단점 또한 존재하는데 아래에서 살펴보자.
  
  * ### 메모리 성능 저하 ###
    static 키워드가 포함된 오브젝트 대부분은 **애플리케이션이 끝날 때까지 라이프타임을 유지**하므로   
    무분별하게 사용할 시 메모리 성능이 저하될 우려가 있다. 

  * ### 테스트에 부적합 ###
    static 변수는 전역으로 관리되기 때문에 애플리케이션 전역에서 해당 필드에 접근과 수정이 가능하다.  
    즉 **특정 상황에 해당 필드 값을 추론하기 어려워** 테스트에 부적합하다. 
    
  * ### 객체 지향 부적합 ###
    static 메소드는 필요한 **파라미터를 모두 외부에서 주입해야 한다**. 그리고 **클래스 내부 필드를  
    사용하기 위해서 해당 필드 또한 static으로 선언**되어야만 한다. 즉 이렇게 static 메소드가 많아지면  
    외부에 의존적이게 되고 객체의 특성을 잘 활용하지 못한다.

  * ### 런타임 다형성 불가 ###
    static 메소드에는 인스턴스를 할당해 접근하는 것이 아닌 **직접적인 호출**이 필요하다. 그리고 해당  
    메소드는 오버라이딩이 불가능해 클래스의 확장이 어려운데, 이는 객체의 다형성 원칙에 부적합하다.
    
  <br>

  위 같은 단점들 때문에 static의 무분별한 사용은 여러 측면에서 비효율적인 결과를 초래할 수 있다. 가급적 반복적으로 사용되고 여러    
  곳에서 공유되는 상수를 final 키워드와 함께 사용하는 것이 좋다. 그리고 null 값으로 참조 재선언하여 런타임 중 할당을 해제할 수도 있다.
  