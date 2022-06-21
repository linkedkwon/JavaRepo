# 메소드의 다형성, 오버로딩(Overloading)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/OOP-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Overloading-yellow" style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 오버로딩이란?
`오버로딩`은 한 **클래스 내에서 같은 이름을 가진 메소드를 중복 정의하는 기능을 의미**한다. 원칙적으로 클래스 내에는 같은 이름을 
가진 변수와 메소드 선언이 불가능하지만, Java는 서로 **다른 파라미터를 갖도록 하여 이같은 기능을 제공**한다. 보통 하나의 메소드 
이름에 하나의 기능을 구현해야 하지만 오버로딩을 통해 **같은 메소드 이름으로 여러 기능을 구현할 수 있다**.

![overloading](https://user-images.githubusercontent.com/78818063/174912138-0842a537-72e3-4ade-964e-b66e0671f36e.png)

<br>

## 2. 사용하는 이유
오버로딩의 이점을 아래의 간단한 예시와 함께 알아보자. 아래 코드는 일반 회원과 `OAuth(Open Authorization)` 로그인 회원 데이터를 
통합 관리하고 있는 특정 서비스의 회원 클래스이며, 회원가입이 완료되었을 때(OAuth의 경우 첫 로그인 시) 공통으로 이메일과 패스워드를 받고 
OAuth 회원은 `Provider(OAuth 서비스 제공자)` 정보를 추가로 전송 받는다. 즉 회원가입 시 두 기능에서 서로 다른 데이터를 처리해야 한다.

![field](https://user-images.githubusercontent.com/78818063/174912478-44ade730-a863-425a-9752-eb9db5d22960.png)

<br>

이처럼 같은 기능을 수행하지만 **입출력 값의 타입 변경이 필요한 경우 오버로딩을 사용**할 수 있다. 이는 **세부 처리 과정에 다형성을 제공함**과 동시에 
메소드에 **같은 이름을 부여하기 때문에 개발 의도와 목적을 정확히 할 수 있다**. 아래 코드는 생성자 오버로딩을 통해 OAuth 작업에서만 필요한 필드를 
다른 시그니처(Signature)로 정의하여 **테이터를 일관성 있게 관리**하고 있다.

![constructorOverriding](https://user-images.githubusercontent.com/78818063/174912745-da2dbb38-0bab-4c70-a73b-dcb975ba52b5.png)

<br>

## 3. 사용법
마지막으로 오버로딩의 사용법에 대해 다룬다. 오버로딩은 **같은 클래스 내에서만 제공되는 기능으로 기본적으로 `정적 바인딩` 방식을 사용**한다. 이는 
컴파일러가 이미 메소드 호출에 필요한 모든 정보를 인지하고 있어 적절히 메소드를 각 객체에 바인딩 할 수 있다. 따라서 오버로딩을 사용하기 위해서는 
**컴파일 시점에 메소드의 시그니처가 구분**되어야 하므로 아래와 같은 조건이 동반되어야 한다.

* 메소드의 이름이 같아야 한다.
* 메소드의 매개변수의 개수 또는 타입이 달라야 한다.

<br>

![static](https://user-images.githubusercontent.com/78818063/174913085-1d45586e-b392-4f49-8e00-6aa19e5081fd.png)

또한 **상속 관계의 클래스 타입을 파라미터로 받는 일은 가급적 피하는 것이 좋다**. 아래 코드처럼 메소드의 파라미터 타입을 지정하면 컴파일러는 실제 
**런타임에서 어떤 구현체로 인스턴스화 할 지 알 수 없으므로** 가장 최상위 클래스 타입으로 구체화한다. 따라서 오버로딩을 사용할 때는 **컴파일 타임에 
바인딩할 수 있도록 타입을 분명히** 해야 하며, 같은 **자료형의 조합이나 같은 수의 파라미터를 갖는 오버로딩을 자제**하는 것이 바람직하다.

<br>
