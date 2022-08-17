# default & static method
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Version-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Java 8-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/default-blue"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/static-blue"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. Java 8 이전 인터페이스
인터페이스는 **객체들의 동일한 행위와 공통으로 사용할 상수를 정의하기 위한 추상 자료형**이다. 내부에는 `상수`와 `추상 메소드`만을 두기 때문에 필요한 로직은 
인터페이스를 상속받는 **하위 객체에서 재정의**해야 한다. 이는 곧 관련된 객체 필드를 설계함에 있어 표준 방식을 명시함과 동시에 다른 클래스들 사이 매개 역할을 
제공한다.

<img width="710" alt="case1" src="https://user-images.githubusercontent.com/78818063/185064426-b6430a4b-2dad-45ee-b53a-66ddc5a35db7.png">

<br>

## 2. 개방 폐쇄 원칙(OCP, Open-Closed-Principle) 위반
기존 인터페이스는 **확장 측면**에서 한 가지 단점을 가질 수 있는데, 먼저 아래 예시 코드를 살펴보자.

<img width="710" alt="case2" src="https://user-images.githubusercontent.com/78818063/185064437-3500a0d6-9ead-4676-927c-cf9a62d562f9.png">

위 코드는 앞서 결제 기능을 추상화한 Payment 인터페이스를 결제 모듈을 대변한 세 기업(A, B, C)의 클래스가 상속받고 있다. 인터페이스 내부에는 결제 기능을 
추상화한 pay() 메소드가 있고, 현재 **이벤트를 진행 중인 A사와 B의 상품 할인을 추상화한 discount() 메소드가 추가되었다**. 이때 추상 메소드의 특징이 
적용되는데, **Payment 인터페이스를 상속받은 모든 하위 클래스는 추가된 discount() 메소드를 반드시 재정의해야 한다**. 즉 할인 이벤트와 전혀 관계 없는 C사에서 
해당 메소드를 재정의하는 불필요한 확장이 일어난다.

<br>

이같은 상황은 확장에는 열려 있고, 수정에는 닫혀 있도록 하는 `개방 폐쇄 원칙`을 위반한다. 물론 확장할 기능을 사용할 **하위 클래스에서 추가**하거나 **새로운 
인터페이스로 분리시켜 상속**받을 수는 있다. 

<img width="695" alt="case3" src="https://user-images.githubusercontent.com/78818063/185064442-d01918f4-8727-4207-82af-9e590bddfe3a.png">

하지만 만약 위 그림처럼 소수 클래스들이 공통으로 기능을 사용해야 하거나 확장된 기능을 마치 `조합(Combination)` 형태로 복잡한 관계가 형성되면 
위같은 방식으로 기능을 관리하는 작업 또한 효율적이라 볼 순 없다. 즉 **확장된 기능을 모든 하위 클래스에서 재정의하지 않고 사용할 새로운 방법이 필요**하다.

<br>

## 3. default & static 메소드의 등장 
Java 8에서는 이같은 패러다임을 변화시킨 `default`와 `static` 메소드가 추가되었다. 기존 추상 메소드, 즉 메소드의 선언부만 작성할 수 있었던 인터페이스 
내부에서는 이제 두 메소드를 사용해 아래와 같이 **구현 로직을 작성할 수 있게 되었다**.

<img width="710" alt="case4" src="https://user-images.githubusercontent.com/78818063/185064452-c36cf836-6a77-420b-953c-2e03b91797b3.png">

default 메소드는 인터페이스를 상속받은 하위 클래스에서 **자동으로 구현되기 때문에 반드시 재정의할 필요가 없다**. 또한 일반적인 상속 관계에 있는 상위 클래스의 
메소드처럼 사용 가능하므로 이전 예제에서 각 기업의 할인율을 다르게 적용하는 등 **필요에 따라 재정의하여 다형성까지도 부여할 수 있다**. 

<img width="706" alt="case5" src="https://user-images.githubusercontent.com/78818063/185064462-cb625918-9f5c-4025-af98-b21a160d96e1.png">

이제 이벤트를 진행하지 않는 C사 클래스는 추가된 discount() **메소드를 재정의할 필요가 없어졌고**, 추가적으로 **A사와 B사의 할인율까지 다르게 적용할 수 있게 되었다**. 
이처럼 내부에 로직을 추가함으로서 하위 클래스의 기능들을 더욱 관리하기 쉬워졌으며, static 메소드를 통한 유틸리티성 기능까지 포함해 기존 인터페이스 보다 더욱 사용성을 
높일 수 있게 되었다.

<br>

## 4. 다중 상속과 메소드 시그니처 모호성
서로 다른 인터페이스에서 **동일한 시그니처를 가진 default 메소드를 선언**하고, 이를 **다중 상속**받으면 무슨 일이 발생할까? 먼저 아래 코드를 살펴보자. 

<img width="710" alt="case6" src="https://user-images.githubusercontent.com/78818063/185064547-7146efe9-91f2-45fb-80c0-0229b35c84e9.png">

이처럼 동일한 시그니처를 가진 default 메소드가 충돌하게 되면 **컴파일러는 에러를 발생**시킨다. 이는 **자동으로 구현되는 default 메소드의 특성 상 컴파일러가 
어떤 메소드를 구현해야 할지 알 수 없기 때문**인데, 아래와 코드와 같이 해당 메소드를 오버라이딩하고 `super 키워드`로 사용할 default 메소드를 **직접 명시해줘야 한다.**

<img width="707" alt="case7" src="https://user-images.githubusercontent.com/78818063/185064575-a82b8bcc-226e-47ab-b1b4-2c4df606708a.png">

이와 마찬가지로 클래스와 인터페이스에서 동일한 시그니처 메소드를 사용하는 경우도 종종 발생하는데, 이같은 경우 **Java는 기본적으로 상속받은 클래스의 우선순위를 더 높게 
취급**한다. 따라서 위 방식과 마찬가지로 메소드를 재정의하여 사용할 상위 자료형의 메소드를 직접 지정해줘야 한다.

<img width="712" alt="case8" src="https://user-images.githubusercontent.com/78818063/185064589-f43722fc-c6cb-4868-bb9c-80016bcf26ce.png">

<br>
