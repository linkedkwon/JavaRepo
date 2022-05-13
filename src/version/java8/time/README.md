# 날짜와 시간 다루기, Time 패키지
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
날짜와 시간을 다루는 작업을 지원하는 `Date`와 `Calendar` 클래스는 java.util 패키지에 속해 있으며, 각각 jdk 1.0과    
1.1 버전에 출시되었다. 먼저 Date 클래스는 **기본 생성자를 통해 현재 시간과 날짜 정보를 가진 인스턴스**를 만들어 사용하며    
Calendar 클래스는 추상 클래스로 하위에 속해 있는 GregorianCalendar에 상속시키거나 **인스턴스를 만들지 않고  
정적 메소드를 바로 호출**하여 사용한다. 그리고 `Formatter 클래스`를 함께 사용해 데이터를 원하는 형식으로 출력할 수 있다.  

<img width="721" alt="dateAndCalendar" src="https://user-images.githubusercontent.com/78818063/168204737-9ac99398-b329-4a4a-b4ab-cff1f3f6610a.png">

<br>

## 2. 위 방식의 문제점
앞서 살펴본 두 클래스는 인스턴스의 상태와 내부 메소드 호출 등 데이터를 처리하는 방식이 매우 비효율적이며, 현재 클래스의 일부  
생성자와 메소드는 `사용 권장되지 않는 상태(Deprecated)`다. 두 클래스가 가지는 몇 가지 문제점을 아래에서 알아보자.  

  * ### 낮은 가독성과 일관성 없는 상수 필드 ###
    <img width="723" alt="cause" src="https://user-images.githubusercontent.com/78818063/168204744-6e056a7e-cd20-4847-b6e8-ba220b7ac2cf.png">
    
    위 코드처럶 클래스에서 제공되는 **상수 필드**를 사용하는 날짜 연산의 경우 개발자가 이해하기 힘든 모호한 코드가 작성될 수 있다.  
    날짜와 시간 연산 시 **인자로 넣는 상수 값들의 의미를 이해하기 힘든데**, 심지어 두 클래스 모두 월과 요일을 나타내는 **첫 번째  
    1월과 일요일의 상수 값이 0과 1로 각기 달라 전혀 일관성없는 모습**을 보여준다.   

  * ### 컴파일 시점에 오류를 찾기 힘들다 ###
    위처럼 가독성이 낮은 코드는 해당 연산의 기대 값을 예측하기 힘든데, 개발자가 **컴파일 시점에 오류를 확인할 방법이 없다**.  

  * ### 모두 불변(Immutable) 상태가 아니므로 Side-Effect에 불안정하다. ###
    **두 인스턴스는 모두 불변 상태가 아니며** 시간 필드를 Setter 함수를 통해 할당받는다. 즉 생성된 인스턴스가  
    여러 곳에서 공유되는 멀티 쓰레드 환경에서 결과 값이 수정되거나 예측하기 힘들어 안정성이 매우 낮다. 

<br>

## 3. Java 8부턴 Time 패키지
두 클래스가 가지는 내부 상태와 인스턴스의 특징은 개발자로 하여금 많은 혼란을 주었고, 결국 사용성에 대해 여러 의문들이 제기되었다.  
이에 `jdk 1.8 버전`에서는 위 같은 문제점들을 보완한 `Time 패키지`가 추가되었다. 아래의 패키지는 **개별적으로 날짜와 시간을 기준으로  
구분된 클래스**들을 하위에 두었으며, **불변 상태로 인스턴스**를 설계해 앞서 보였던 안정성 문제 또한 매우 잘 해결해 내었다.

* **java.time** - 날짜와 시간을 다루는데 필요한 핵심 클래스
* **java.time.chrono** - 표준(ISO)이 아닌 달력 시스템을 위한 클래스 
* **java.time.format** - 날짜와 시간을 파싱하고, 형식화하기 위한 클래스
* **java.time.temporal** - 날짜와 시간의 필드와 단위를 위한 클래스
* **java.time.zone** - 시간대(time-zone)와 관련된 클래스

<br>

## 4. 사용법(How To Use)
지금까지 버전에 따라 날짜와 시간을 다루는 `API(Application Programming Interface)`의 등장과 변화에 대해 알아보았는데  
마지막으로 time 패키지에서 자주 사용되는 클래스들과 기본적인 사용 방법에 대해 알아보자.  

  * ### 기본 클래스 ###
    <img width="724" alt="timeBasicClass" src="https://user-images.githubusercontent.com/78818063/168204747-c14443e0-85c2-404b-a520-ce51bb7b582d.png">
    
    날짜와 시간을 나타내는 가장 기본이 되는 클래스들로, 자신이 원하는 데이터에 해당되는 클래스를 선택하여 사용한다. **로컬**에 입력되어 있는  
    날짜 정보를 가져오기 위해선 `Local 접두사`를, **표준(ISO)** 달력 시스템에서 정의된 타임존의 정보를 가져오기 위해선 `Zoned 접두사`를 사용한다.  
    다음으로 `날짜는 Date`, `시간은 Time`, `두 정보가 모두 필요하면 DateTime`이 붙은 클래스를 사용할 수 있다. 기본적으로 `Getter()`를 통해  
    필요한 정보를 반환받으며, 정적 메소드 `now()`를 사용해 **현재 날짜와 시간의 정보를 저장**할 수 있다. 또한 `of()` 메소드를 사용해 **파라미터**에  
    해당되는 값을 인스턴스에 할당이 가능하다. 마지막으로 `표준 시간`을 사용하는 경우 **시차와 원하는 지역 정보를 입력**해야 한다.   
    

  * ### 연산과 비교하기 ###
    <img width="726" alt="arth" src="https://user-images.githubusercontent.com/78818063/168204748-ab003548-0b8a-4f70-b4a8-6639e17a92cc.png">
    
    위의 클래스들은 모두 **날짜와 시간을 조작하고 비교할 수 있으며, 두 인스턴스의 날짜와 시간의 차까지 계산 가능**하다. 먼저 연산을 담당하는 plus, minus 메소드는  
    해당 접두사와 연산이 필요한 필드명을 함께 사용해 계산하고자 하는 값을 인자로 넘겨받는다. 다음으로 인스턴스 간의 비교는 isEqual(), isBefore(), isAfter()  
    메소드를 통해 주체와 비교 대상 사이에서 날짜가 동일한지 혹은 이전이나 후인지 까지 비교가 가능하다. 마지막으로 날짜 또는 시간의 차를 구할 수도 있는데, 위는  
    시간 차를 계산하는 예제로 until() 함수에 인자로, 대상이 되는 인스턴스와 Java에서 제공되는 단위 클래스 ChronoUnit에 원하는 필드를 넘겨준다. 위에서 소개한  
    예시 외에도 모든 경우에서 계산과 연산이 가능하며, 가독성이 높아 메소드를 이해하는 것이 크게 어렵지 않다. 다른 작업이 필요한 경우 공식 문서를 찾아 예시를 살펴보도록 하자. 


  * ### 포맷팅 ###
    <img width="727" alt="formatter" src="https://user-images.githubusercontent.com/78818063/168204750-391fc450-344e-46f3-8249-50740e5be1f5.png">

    기존 방식과 유사하게 날짜와 시간에 특정 형식을 지정하기 위해서 패턴을 지정한 Formatter 클래스를 사용해야 한다.  
    `DateTimeFormatter 클래스`를 사용해 내장된 **format 메소드에 형식을 지정한 인스턴스를 인자로 넘겨줘야 한다**.  

<br>