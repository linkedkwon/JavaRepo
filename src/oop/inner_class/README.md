# 내부 클래스(Inner class)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/OOP-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/InnerClass-yellow" style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>


## 1. 내부 클래스란?
`내부 클래스`란 의미 그대로 **클래스 또는 메소드 내부에 선언된 클래스**를 뜻하는데, 내부 클래스를 포함한 클래스를 
`외부 클래스(Outer class)`라 칭한다. 외부 클래스는 필드에 선언된 **내부 클래스의 인스턴스를 생성할 수 있으며, 
이를 통해 클래스의 필드를 호출**할 수 있다. 내부 클래스는 하나의 클래스 또는 메소드에서만 사용되는 클래스가 필요할 때 
사용되어 지는데, 이처럼 클래스를 구조화하여 얻을 수 있는 이점은 다음과 같다.

* **객체의 구조를 더 조직화하여 보다 구체적으로 표현할 수 있다.**
* **외부 클래스를 통해 내부 클래스의 필드에 접근 가능하므로 코드의 복잡성을 줄여 구현 시 더 적은 코드 최적화가 가능하다.**
* **서로 연관된 객체를 캡슐화하기 때문에 데이터를 응집시키고 은닉하므로 데이터 압축, 보안 및 성능 향상 등의 장점을 갖는다.**

<br>

## 2. 내부 클래스의 종류
내부 클래스는 **선언하는 위치에 따라 아래와 같이 분류**할 수 있다. 이 중 `익명 클래스`는 다른 문서에서 보다 자세히 다루고 있다.

  * ### 정적 클래스(Static class) ###
    클래스 로더에 의해 메모리 영역에 고정 할당되는 `static 키워드`를 **사용해 선언한 내부 클래스**를 의미한다. 외부 클래스의 
    인스턴스 또는 메소드에서 사용할 목적으로 선언하며, **static 키워드의 특징이 사용하는 모든 곳에서 적용된다**. 클래스 내부에 
    static 멤버 변수를 취급 가능하며, 별도의 인스턴스 생성 없이도 외부 클래스를 통해 접근 가능하다. 보통 도메인 데이터를 
    유연하게 처리하는 `빌더 패턴(Builder Pattern)`에서 자주 사용된다.
    
    <img width="725" alt="static" src="https://user-images.githubusercontent.com/78818063/173276684-f591f46a-429c-45dc-bede-800930fbfd06.png">
    
  * ### 인스턴스 클래스(Instance class) ###
    인스턴스 클래스란 **static 키워드 없이 선언된 모든 내부 클래스**를 칭한다. 보통 아래 코드와 같이 외부 클래스의 멤버 변수로 
    해당 클래스의 **인스턴스를 생성**하여 접근하기 때문에 외부 클래스의 인스턴스 또는 내부 메소드에서 사용할 목적으로 선언된다.
    
    <img width="723" alt="instance" src="https://user-images.githubusercontent.com/78818063/173276685-036cad22-be78-4f81-880c-f15a5abc48f5.png">
    

  * ### 지역 클래스(Local class) ###
    지역 클래스는 지역 변수와 같이 **메소드 내부에서 정의하여 사용하는 클래스**를 의미한다. 메소드 내부에서 사용되는 모든 지역 변수는 
    지역 클래스러 하여금 상수로 취급되므로 **오직 읽기(Only-Read)만 가능**하다.
    
    <img width="721" alt="local" src="https://user-images.githubusercontent.com/78818063/173276687-95b97444-7732-4635-8fb3-8c30ec7066d8.png">
    
  * ### 익명 클래스(Static class) ###
    익명 클래스는 별도의 클래스 선언 없이 **로직에서 바로 클래스 선언과 인스턴스 생성을 동시에 수행**한다. 필요에 따라 일시적으로 
    단 한 번 사용하는 **일회용 클래스를 익명 클래스**라 칭한다. 아래 코드와 같이 클래스 또는 인터페이스를 상속받아 해당 필드를 
    재정의 및 확장하여 사용한다.

    <img width="720" alt="anonymous" src="https://user-images.githubusercontent.com/78818063/173276691-5ab05035-342b-4cbb-86f5-bdef02216965.png">

<br>
