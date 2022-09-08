# 날짜와 시간을 다루는 Time 패키지
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Version-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Java 8-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Time-blue"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. Date와 Calendar 클래스 
날짜와 시간 작업을 지원하는 `Date`와 `Calendar` 클래스는 java.util 패키지에 속해 있고, 각각 jdk 1.0과 1.1 버전에 출시되었다. 
먼저 Date 클래스는 **현재 시간과 날짜 정보를 가진 인스턴스**를 생성해 사용하고, Calendar 클래스는 추상 클래스로 **정적 메소드를 통해 바로 
호출하거나 역법이나 지역에 따라 구현된 클래스에 상속시켜** 사용 가능하다. 그리고 두 방식 모두 `Formatter 클래스`를 사용해 데이터를 원하는 형식으로 
출력할 수 있다.  

<img width="721" alt="dateAndCalendar" src="https://user-images.githubusercontent.com/78818063/168204737-9ac99398-b329-4a4a-b4ab-cff1f3f6610a.png">

<br>

## 2. 위 방식의 문제점
앞서 살펴본 두 클래스는 인스턴스의 상태와 내부 메소드 호출 등 데이터를 다루는 방식이 상당히 비효율적이라 개발자들 사이에서 평이 좋지 않고, 현재는 
클래스의 일부 생성자와 메소드가 `사용 권장되지 않는 상태(Deprecated)`가 되었다. 아래 항목에서 두 클래스가 가지는 문제점에 대해 알아보자.  

  * ### 낮은 가독성과 일관성 없는 상수 필드 ###
    아래 코드처럼 클래스 내부의 **상수 필드**를 사용한 날짜 연산의 경우 개발자가 이해하기 힘든 모호한 코드가 작성될 수 있다. 두 클래스 모두 
    **인자로 전달할 상수 값들의 의미를 파악하기 힘들며**, 월과 요일을 나타내는 **첫 번째 1월과 일요일의 상수 값이 0과 1로 각기 다르는 등 
    전혀 일관성없는 모습**을 보여준다.
    
    <img width="723" alt="cause" src="https://user-images.githubusercontent.com/78818063/168204744-6e056a7e-cd20-4847-b6e8-ba220b7ac2cf.png">

  * ### 컴파일 시점에 오류를 찾기 힘들다 ###
    위처럼 가독성이 낮은 코드는 개발자가 기대 값을 예측하기 힘들기 떄문에 **컴파일 시점에 오류를 확인할 방법이 없다**.  

  * ### 모두 불변(Immutable) 상태가 아니므로 Side-Effect에 불안정하다. ###
    **두 클래스의 인스턴스는 모두 불변 상태로 설계되지 않았기 때문에** Setter를 통해 날짜와 시간 값을 할당받는다. 즉 생성된 인스턴스가 
    여러 곳에서 공유되는 멀티 쓰레드 환경에서 결과 값이 수정도리 수 있어 안정성이 매우 낮다.

<br>

## 3. Java 8부턴 Time 패키지
두 클래스가 가지는 내부 상태와 인스턴스의 특징은 개발자로 하여금 많은 혼란을 주었고, 결국 사용성에 대해 여러 의문들이 제기되었다. 이에 `Java 8`에서는 
이같은 문제점들을 보완한 `Time 패키지`가 출시되었다. 해당 패키지는 필요한 기능에 따라 **클래스**들을 구조적으로 분리시켰으며, **인스턴스**를 불변 상태로 
설계하여 앞서 보였던 안정성 문제 또한 보완하였다.

* **java.time** - 날짜와 시간을 다루는데 필요한 핵심 클래스
* **java.time.chrono** - 표준(ISO)이 아닌 달력 시스템을 위한 클래스 
* **java.time.format** - 날짜와 시간을 파싱하고, 형식화하기 위한 클래스
* **java.time.temporal** - 날짜와 시간의 필드와 단위를 위한 클래스
* **java.time.zone** - 시간대(time-zone)와 관련된 클래스

<br>

## 4. 사용법(How To Use)
지금까지 버전에 따라 날짜와 시간을 다루는 `API(Application Programming Interface)`의 등장과 변화에 대해 알아보았는데, 마지막으로 time 패키지에서 
자주 사용되는 클래스들과 기본적인 사용 방법에 대해 다두로독 한다. 

  * ### 기본 클래스 ###
    날짜와 시간을 나타내는 가장 기본이 되는 클래스로 자신이 원하는 데이터에 해당되는 클래스를 선택하여 사용할 수 있다. **로컬**에 입력되어 있는 날짜와 시간 
    정보가 필요하다면 접두사로 `Local`을, **표준(ISO)** 달력 시스템에서 정의된 타임존 정보를 가져오기 위해선 접두사 `Zoned`를 붙여야 한다. 그리고 
    `날짜 정보는 Date`, `시간 정보는 Time`, `두 정보가 모두 필요하면 DateTime을 함께 붙여` 사용할 클래스를 선정하면 된다. 기본적으로 `Getter()`를 
    통해 필요한 정보를 반환받거나 정적 메소드 `now()`를 호출해 **현재 날짜와 시간의 정보를 반환** 받을 수 있다. 또한 `of()` 메소드를 사용하면 **파라미터**에 
    해당되는 값을 인스턴스에 할당이 가능하며, `표준 시간`을 다루는 경우 **시차와 원하는 지역 정보를 입력**하면 된다.

    <img width="724" alt="timeBasicClass" src="https://user-images.githubusercontent.com/78818063/168204747-c14443e0-85c2-404b-a520-ce51bb7b582d.png">

  * ### 연산과 비교하기 ###
    패키지에 속해 있는 클래스들은 **날짜와 시간을 조작 및 비교할 수 있거나 차를 구하는 등 기본적인 연산 기능을 모두 지원**한다. 먼저 연산을 담당하는 plus, minus 
    메소드는 접두사와 연산에 필요한 필드명을 함께 사용해 계산하고자 하는 값을 인자로 전달할 수 있다. 다음으로 인스턴스 간의 비교는 isEqual(), isBefore(), isAfter()  
    메소드를 호출해 주체와 비교 대상 간의 날짜가 동일한지 혹은 선행과 후행까지도 비교가 가능하다. 또한 인스턴스끼리 날짜와 시간의 차를 구할 수도 있는데, 시간 차를 
    계산하는 until() 메소드에 인자로 비교할 인스턴스와 Java에서 제공되는 단위 클래스 ChronoUnit에 원하는 필드를 함께 넘겨주면 된다. 이러한 예시 외에도 대부분 
    상황에서 계산과 연산을 지원해 활용도가 높고, 가독성이 좋아 메소드를 이해하는 과정도 크게 어렵지 않다.
    
    <img width="726" alt="arth" src="https://user-images.githubusercontent.com/78818063/168204748-ab003548-0b8a-4f70-b4a8-6639e17a92cc.png">

  * ### 포맷팅 ###
    날짜와 시간을 특정 형식에 따라 변환하고 싶다면 이러한 패턴을 지정할 수 있도록 하는 Formatter 클래스를 사용해야 한다. `DateTimeFormatter 클래스`를 
    사용해 내장된 **format 메소드에 형식을 지정하고, 현재 날짜와 시간 값이 할당된 인스턴스를 인자로 넘겨주면 쉽게 변형이 가능하다.**
    
    <img width="727" alt="formatter" src="https://user-images.githubusercontent.com/78818063/168204750-391fc450-344e-46f3-8249-50740e5be1f5.png">

<br>