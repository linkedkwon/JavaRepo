# NPE(NullPointerException)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Exception-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/NPE-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. NPE란?
Java에서 사용하는 자료형은 두 가지로, 먼저 `원시 타입(Primitive Type)`은 변수를 생성함과 동시에 **0, 공백, false와 같은  
기본(default) 값**을 가진다. 이와 마찬가지로 `참조 타입(Reference Type)`은 **기본 값으로 null을 갖는다**. null은 다른 리터럴처럼  
데이터 자체로 구분되는 것이 아닌 모든 참조 변수에 할당할 수 있는 특수한 값이다. 즉 다시 말해 **null이란 참조 변수에 아직 인스턴스가  
할당되지 않은 상태를 의미하고(또 다른 의미로 인스턴스화가 일어나지 않음) NPE는 이렇게 null 상태를 가진 객체를 참조하려고 했을 때   
발생하는 예외를 의미**한다. 사소한 실수로 인스턴스 되지 않은 객체나 배열, String 등에 접근하는 행위부터 FileIO나 서버 송수신까지  
예기치 못한 오류로 많은 NPE가 발생되기도 한다. 애플리케이션의 내부 로직에 따라 현재 인스턴스의 상태를 잘 파악하는 것이 매우  
중요하며, 멀티쓰레드 환경이나 데이터베이스와 같이 외부에서 인스턴스 또는 데이터가 삭제되어 NPE가 발생할 수도 있다.

<br>

## 2. 발생하는 이유
앞서 null의 의미와 NPE에 대해서 알아보았는데, Java에서 NPE가 발생하는 근본적인 원인은 아래와 같다.
* **null 객체의 instance 함수를 호출하는 경우**
* **null 객체의 instance 변수에 접근하는 경우**
* **null 배열 객체의 length를 구하려는 경우**
* **null 배열 객체의 값을 index로 접근하는 경우**
* **애플리케이션에서 NPE를 던지는 경우**

<br>

그렇다면 실제 서비스에서 NPE가 발생할 수 있는 가장 흔한 시나리오를 한 번 살펴보자.  

<img width="716" alt="npeCase" src="https://user-images.githubusercontent.com/78818063/168332447-2ababbfa-cc0b-4ea1-bc24-4cbdea849d38.png">

위 예시는 가장 최근 접속일이 1년을 넘는 계정들 중 프리미엄 회원을 제외하고 모두 휴면 계정으로 전환하는 **배치 작업**을 수행하고 있다.  
만약 휴면 계정으로 전환할 회원이 존재하지 않으면 어떻게 될까? 가장 먼저 쿼리 조건에 해당하는 컬럼이 없으므로 데이터베이스에서 null  
상태의 리스트를 반환받는다. 반환받은 리스트는 프리미엄 여부를 위해 파라미터로 넘겨주게 되는데, 필터링 과정에서 null 상태인 회원  
리스트를 참조하기 떄문에 NPE가 발생하고(해당 예시는 NPE를 설명하기 위해 필터링을 서비스에서 진행하고 있다.) 만약 남은 작업을 더  
수행한다 가정해도 배치 작업에서 또 다시 NPE가 발생하게 된다. 위는 가장 흔하게 발생할 수 있는 시나리오 중 하나인데, 실제로는 **여러  
로직을 거치면서 언제 null 상태의 객체를 참조하는지 정확한 파악이 필요하다.**

<br>

## 3. NPE를 예방하는 좋은 습관
앞서 살펴본 예시처럼 NPE가 발생되면 쉽게 오류를 해결하기가 힘든데, 보통 null 자체의 의미가 매우 모호하므로 파생된 에러에서  
**근본적인 원인을 찾기가 매우 힘들다**. 또한 에러가 발생되면 인스턴스의 상태가 존재하지 않기 때문에 디버깅 작업으로 쉽게 해결이  
불가능하다. 즉 한 번 발생되면 원인 규명이 쉽지 않아 서비스 로직과 함께 경우의 수를 잘 파악해 이를 **미연에 방지하는 것이 바람직하다**.  
아래에서 NPE를 예방하는 좋은 습관들을 알아보자. 

 * ### 파라미터와 리턴 값으로 null을 주지 말자 ###
    <img width="661" alt="pareturn" src="https://user-images.githubusercontent.com/78818063/168332455-8514c100-b9cf-43ab-aa7a-73cbde0290da.png">
   
    특수한 경우를 제외하고 `파라미터`와 `리턴 값`으로 의미없는 null 값을 주고 받지 않아야 한다. 되도록 문**자열이나  
    boolean 값을 통해 해당 객체가 null 상태임**을 알려주자.
     
 * ### Null Check ###
    <img width="673" alt="nullCheck" src="https://user-images.githubusercontent.com/78818063/168332464-a2e2aa10-3556-465b-a60b-a47bf47422bb.png">
   
    NPE가 발생할 것 같은 서비스 로직에는 객체가 null 상태인지 **사전에 방지하는 코드를 작성하는 것이 바람직하다**.  

 * ### 체이닝 메소드(Chaining Method) 사용을 지양하자 ###
    <img width="666" alt="chaining" src="https://user-images.githubusercontent.com/78818063/168332473-8da63699-48e8-400a-b117-dbb82ff2007a.png">
   
   반환되는 값이 어떤 상태인지 정확히 알 수 없는 경우 위 방식은 바람직하지 않은데, 중간에 반환받은 객체가  
   null 상태라면 `Stack Trace`에서도 **해당 라인의 위치만 출력되므로 어디서 에러가 발생했는지 디버깅하기도  
   매우 까다롭다**.  

 * ### 객체는 빈 값으로라도 초기화하자 ###
    <img width="667" alt="init" src="https://user-images.githubusercontent.com/78818063/168332475-ab6b6f45-18e2-437d-a53f-3f63b66a55c0.png">
   
   NPE의 근본적인 원인은 객체의 `비인스턴스화` 상태였는데, 특수한 경우를 제외하고선 빈 필드 값을 가지더라도  
   객체를 항상 초기화 해주는 것이 좋으며, 문자열은 반드시 공백으로 초기화하자. 

 * ### toString() 보단 valueOf()를 사용하자 ###
    <img width="666" alt="valueOf" src="https://user-images.githubusercontent.com/78818063/168332479-23fd043a-2c46-4a45-bb9a-131ebe48a82e.png">

   만약 위 코드에서 파라미터로 받은 값이 null일 수 있는 경우 `toString()` 메소드는 NPE가 발생할 위험이  
   있다. 반면에 정적 메소드인 `valueOf()`는 파라미터로 **null 상태를 받아도 그 자체로 변환된 문자열 "null"  
   값을 리턴**하기 때문에 안전하게 NPE를 예방할 수 있다.

 * ### 문자열 비교 순서 ###
    <img width="655" alt="equals" src="https://user-images.githubusercontent.com/78818063/168332487-f44de0c7-b0a1-4614-86da-fb7e0136a3dd.png">
   
    문자열을 비교하는 `equals()` 메소드는 두 문자열을 비교할 때 `non-null String`을 기준으로 삼는다.     
    따라서 두 번째 예시처럼 null 상태일 가능성이 높은 객체를 **후위에서 비교해주면 NPE 발생을 예방**할 수 있다.

 * ### Library(Optional)와 Keyword(try-catch) 적극 활용하기 ###
    위에서 소개했던 방법들 외에도 NPE를 예방하기 위해 Java에서 지원하는 `Optional Class(jdk 1.8 이상)`나  
    NPE가 발생했을 때 `로깅(Logging)`과 후처리 작업을 편리하게 돕는 `try-catch` 구문을 적극 활용하는 것이 좋다.  
    해당 방법들은 소개할 내용이 많고 활용도가 높아 다른 디렉토리에서 자세히 다루도록 한다.

<br>
