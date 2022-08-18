# 불변 키워드 final
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Keyword-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Final-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 예약어 final
Java에서 제공되는 예약어 `final`은 변수, 클래스, 메소드 등 프로젝트 내에 여러 구성 요소를 정의할 때 사용하며, 시간이 지나도 
처음 정의된 상태가 변하지 않는 **불변(Immutable)성을 보장**한다. 이는 고정할 값을 명시적으로 바로 할당하거나 객체의 필드 특성에 
따라 생성자를 통해 외부에서 동적으로 할당하는 두 가지 방법을 주로 사용하는데, 원시 타입은 한 번 초기화하면 값의 변경이 불가능하고 
참조 타입은 다른 인스턴스로의 재참조가 불가능하다.

<br>

## 2. final 적용 지점

  * ### final variables ###
    기본 자료형 변수에 final을 선언하면 해당 값은 `상수`로 지정된다. 이때 주의할 점은 **반드시 한 번 값을 초기화해야 하는 것으로 
    값이 할당되면 해당 값을 수정할 수 없다. 이에 final이 붙은 변수는 **동시성 문제가 보장되기 때문에 보통 `static` 키워드를 함께 사용해 
    모든 인스턴스가 상수 필드 값을 공유하는 형태로 사용한다. 대게 명시적 초기화를 통해 바로 리터럴 값을 할당하는 것이 좋고, 드물지만 final 
    block(static이 정의되면 static 블록)에서 유동적으로 값을 초기화하기도 한다.
    
    <img width="621" alt="var" src="https://user-images.githubusercontent.com/78818063/168413543-2bcd08f8-3fe8-421c-b272-ec59e1dda614.png">
    
  * ### final instance ###
    final을 선언한 참조 변수는 한 번 인스턴스화를 거치고 나면 다른 **인스턴스를 재참조하는 것이 불가능**하다. 
    **단 선언된 인스턴스가 불변 상태인 것은 아니므로** Setter와 같은 내부 메소드를 통해 필드 값의 수정은 가능하다. 
    
    <img width="620" alt="instnace" src="https://user-images.githubusercontent.com/78818063/168413546-b0fff328-03d3-4841-bede-fa06f8c1a10c.png">
    
  * ### final parameter ###   
    메소드 인자 역시 final 키워드가 적용 가능하며, 아래 코드와 같이 기본형과 참조형 타입 모두 선언할 수 있다.
    
    <img width="625" alt="para" src="https://user-images.githubusercontent.com/78818063/168413550-22bfadeb-aaf2-4196-ad9e-1b4ceae51214.png">

  * ### final class ###
    final이 선언된 클래스는 **상속이 제한된다**. 보통 `상수 필드` 비중이 크거나 `정적 메소드`로 구성된 클래스 또는 
    유틸 클래스에서 많이 사용된다. final이 선언된 클래스의 대표적인 예시로는 `String` 클래스가 있는데, 이는 불변 객체로서 
    실제 서비스에서 많은 비중을 차지하고 있는 문자 데이터가 **외부로부터 수정되는 상황을 막고(Read-Only)** 인스턴스를 
    가볍게 유지시켜 높은 성능을 유지시킨다.

    <img width="619" alt="method" src="https://user-images.githubusercontent.com/78818063/168413551-c0069fdd-50db-483d-85b1-8a3bd5b126c9.png">

  * ### final method ###
    클래스와 유사하게 메소드에 final을 선언하면 해당 **메소드는 재정의(Overriding)가 불가능하다**. 보통 메소드 내부에서 
    다루는 데이터나 로직의 수정을 피하고 싶을 때 사용되는데, Java의 코어 라이브러리들이 대부분 final로 선언되어 있는 것을 
    확인할 수 있다.
    
    <img width="608" alt="class" src="https://user-images.githubusercontent.com/78818063/168413553-9ed98adb-3efb-4031-9898-e3492baca8e5.png">
    
<br>
