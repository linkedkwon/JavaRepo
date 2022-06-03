# 사용자 정의 예외(Custom Exception)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Exception-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Custom-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 사용자 정의 예외란?
프로그램을 개발하다 보면 Java의 **표준 API(Application Programming Interface)가 제공하는 예외 클래스만으로 비즈니스 
로직에서 예상되는 모든 예외를 표현할 수 없다**. 예를 들어 회원이 상품을 구매할 때 남은 재고가 부족한 경우 예외를 호출해야 하는데, 
이러한 기능은 표준 API에서 제공되지 않는다. 즉 **사용자가 직접 예외를 정의하고 로직에 따라 예외를 발생시키는 작업을** `사용자 정의 예외`라 칭한다. 

<br>

## 2. Exception의 두 가지 종류(Checked, Unchecked)
사용자 정의 예외를 만들기 위해서는 먼저 **예외 클래스를 선언**해야 하는데, 아래에서 몇 가지 단계를 살펴보자.

  * ### 예외 클래스 상속 ###
    예외는 컴파일 시점과 런타임 시점에 확인하는 `체크 예외(Checked Exception)`과 `언체크 예외(Unchecked Exception)` 두 가지로 
    분류되어 진다. 이같은 분류에 따라 **별도의 예외 처리 로직을 둬야 하는 경우는 Exception 클래스를 상속받고, 반대로 로직을 생략하고 
    싶은 경우는 RuntimeException 클래스를 상속받으면 된다**.

  * ### 클래스 컨벤션 ###
    기본적으로 **클래스명은 마지막에 Exception으로 끝나야 하고** 클래스 **내부에는 생성자만을 포함**하는 것이 좋다. 보통 생성자는 **기본 생성자와 
    예외 메세지를 전달하기 위해 String 타입의 매개 변수를 갖는 생성자 두 개를 선언한다**. 해당 메세지는 내부에 예외 처리 로직을 뒀을 때 
    오류 내용을 확인하기 위해 사용되어 진다. 
    
<br>

<img width="707" alt="constructor" src="https://user-images.githubusercontent.com/78818063/171789599-043d6a6d-03b1-4b62-a916-e70c7208d6b1.png">

위 코드는 앞서 설명한 컨벤션을 토대로 만든 사용자 정의 예외 클래스를 나타낸다.

<br>

## 3. 예외 발생시키기
사용자 정의로 만든 예외는 로직에 따라 **개발자가 예외를 직접 발생시**켜야 한다. 만약 예외 메세지가 필요하다면 메세지를 매개 변수로 받는 
생성자를 사용하면 되고 보통 `throws`와 `throw` 키워드를 사용해 **호출한 곳에서 예외를 처리하도록 하는 예외 전환 방식을 사용**한다.

<img width="701" alt="customException" src="https://user-images.githubusercontent.com/78818063/171789606-9b8541ca-a918-46af-bb1c-be66c04e5c29.png">

위 코드는 사용자 정의 예외를 직접 비즈니스 로직에 따라 호출하는 예제로 호출한 Caller는 해당 에외 **인스턴스를 catch 블록에서 참조**하고 있다. 
또한 **Exception을 상속받고 있기 때문에 내부 메소드를 그대로 사용 가능**하다. 데이터베이스에서 발생한 예외는 상세한 오류 정보를 전달하기 위해 
코드가 내부 인스턴스에 포함되는데, 해당 원리는 예외 메세지를 매개 변수로 받는 생성자에서 사용할 수 있다. **메세지는 인스턴스 내부에 자동으로 저장되어** 
`getMessage()` **함수를 호출해 반환** 받을 수 있다. 이외에도 **예외를 추적하는** `printStackTrack()` 등이 자주 사용된다.

<br>
