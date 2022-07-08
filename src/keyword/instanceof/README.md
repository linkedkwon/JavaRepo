# 타입 비교 키워드 instanceof
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Keyword-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/instanceof-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. instanceof 키워드란?
키워드 `instanceof`는 객체의 타입을 확인하는 연산자로 **객체가 특정 타입으로 형변환이 가능한지** 해당 여부를 boolean 값으로 
반환한다. 주로 상속 관계 에서 객체가 상위 클래스 또는 하위 클래스인지 구분하기 위해 사용한다.

![case1](https://user-images.githubusercontent.com/78818063/177986098-44d05635-af52-4abd-9222-e4f91ef0c803.png)

기본적인 사용법은 위 코드와 같이 `객체 instanceof 타입`을 선언하면 되고, **타입 범위가 자신과 같거나 업캐스팅(Upcasting)이 되는 경우 
true 값을 반환**한다. 단 애초에 상속 관게에 포함되지 않은 타입을 비교하면 오류가 발생한다.

<br>

## 2. 주의할 점
사실 상속 관계에 있는 여러 하위 객체들의 타입을 관리하기 위해 instanceof로 매번 검사하기 보단 **하위 객체들을 추상화한 상위 타입으로 
선언해 관리하는 것이 좋다**. 이같은 구조는 업캐스팅 된 객체를 외부로부터 주입받으면 되므로 필요에 따라 유동적으로 타입을 변형할 수 있다. 또한 더욱 
빠른 처리 속도가 보장되고 객체 간 타입을 쉽게 제어가 가능하며, 단일 책임 원칙과 인터페이스 분리 원칙 등이 함께 적용되어 객체의 다형성, 코드 재사용성 
및 확장성 등과 같은 여러 이점을 가질 수 있다.

![case2](https://user-images.githubusercontent.com/78818063/177986102-de300831-ff18-4461-ba30-5ae76eca5a7c.png)

만약 검사하고자 하는 클래스를 **상속받는 하위 클래스가 없거나 간단한 문자 비교와 같이 단위가 작은 경우**에는 instanceof 키워드를 사용해 
작업을 수행해도 별다른 문제가 없으며, **아래와 같이 코드를 최적화** 할 수 있다.

![case3](https://user-images.githubusercontent.com/78818063/177986108-22af6374-6d01-4301-8627-56fca0d62843.png)

<br>
