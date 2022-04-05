# String vs StringBuilder vs StringBuffer
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/DataType-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/String-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Builder-blue"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>


## 1. String 연산과 가비지(Garbage)
  새로운 문자열을 만들기 위해 String 객체는 문자열 연산 기능을 제공하고 있다.  
  아래 그림은 더하기 연산을 수행해 새로운 문자열을 만드는 과정이다.

  <img width="490" alt="addStrByString" src="https://user-images.githubusercontent.com/78818063/161700490-762a6424-a0ad-429f-8ce7-6780f6d3bff7.png">

  위 로직은 최종적으로 인스턴스가 "AAAAA"란 값을 가지며 종료된다. 여기서 String 객체는 일반 변수처럼 기존 인스턴스에 다른 문자를  
  붙여 새로운 문자를 할당하는 것이 아니다. 반복문을 실행하면서 더한 각 문자열은 매번 `String Pool`에 할당된다. 그리고 다시 다음  
  문자를 더할 때 마다 해당 인스턴스는 `참조 불가능한 상태(Unreachable State)`로 변한다. 즉 String 객체의 `불변(Immutable) 상태`란  
  특징 때문에 이처럼 문자열을 만들기 위해 사용되었던 부분 문자열들이 모두 `가비지`가 되어 `GC(Garbage Collection)` 대상이 되어 버린다. 

<br>


## 2. StringBuffer와 StringBuilder
  위처럼 String 객체를 사용해 문자열을 다루는 것은 상당히 비효율적인데, 그 이유는 내부에 문자를 저장하는 Character  
  배열 또한 불변 상태기 때문이다. 그래서 Java는 위 같은 저장 공간이 `가변(Mutable) 상태`인 두 가지 클래스를 제공한다. 

  <img width="564" alt="abstractStringBuilder" src="https://user-images.githubusercontent.com/78818063/161698370-b724bc5f-545e-4cde-a31d-9058f2c8ee2d.png">

  `StringBuffer`와 `StringBuilder` 클래스는 모두 `AbstractStringBuilder`란 추상 클래스를 상속 받고 있는데  
  그 내부를 살펴보면 문자의 값을 저장하는 `'value'` 배열(버퍼)과 현재 문자열의 사이즈를 나타내는 `count` 변수를 가지고 있다.  
  이 두 가지 변수를 사용해 문자열을 생성하거나 수정할 때, 또 합치는 경우 필요한 만큼 사이즈를 늘려주고 추가할 새로운 문자를 해당 배열에 추가한다.  
  그러므로 초기 만들어진 클래스 내부에 Character 배열은 계속해서 참조 가능한 상태를 유지해 메모리를 효율적으로 관리할 수 있다.

<br>

## 3. StringBuffer vs StringBuilder 
  위에서 소개한 두 개의 빌더 클래스는 모두 내부 변수를 사용해 `가변성`을 부여하는 똑같은 방식을 이용하는데  
  각각 사용 목적에서 차이점을 가지고 있다. 먼저 StringBuffer는 메소드에 자바 `Synchronized` 키워드를 사용하고 있다.  
  이 키워드는 `동기화 처리` 기능을 부여하는데, 특정 스레드가 작업을 하고 있을 때 다른 스레드가 데이터에 접근하지 못하도록 막는다.  
  즉 멀티 스레드 환경에서 같은 변수를 참조해 생길 수 있는 `동시성 문제(또는 Race Condition)`를 해결하기 위해 고안된 방식이다.  
  반면에 StringBuilder는 위처럼 동시성에 관여하는 기능을 두지 않아 그로 하여금 가장 빠른 속도와 성능을 보장한다.  

  <img width="485" alt="addStrByBuilder" src="https://user-images.githubusercontent.com/78818063/161700500-1bfbd4cb-abe0-4119-8517-95882c0c712a.png">

  **단 두 가지 클래스를 사용하면 초기 버퍼 공간을 할당해야 하며, 매번 필요에 따라 사이즈를 지정하는 작업이 수행되므로 문자열의 연산  
  횟수가 작거나 재사용되는 경우는 위 방법이 적합하지 않다.** 즉 일반적인 String 객체는 적은 연산 횟수와 인스턴스의 재사용을 적극 사용할 때  
  그 외에는 멀티 스레드 환경에서 StringBuffer를, 싱글 스레드 환경에서 StringBuilder를 사용하길 권장한다.  


<br>

## 4. 성능 비교 ##
  아래는 싱글 스레드 환경에서 새로운 문자열을 만들 때(+연산 수행) 세 가지 방식의 성능을 비교한다.  
  `'작업 수행 시간'`과 `'힙 메모리 사용량'` 두 가지를 척도로 아래에서 성능을 측정해보자.  

* ### 사용 함수 ###
  <img width="812" alt="performanceCode" src="https://user-images.githubusercontent.com/78818063/161705902-6b4f7fc7-d670-4773-97cd-7eca72301aef.png">

  * `System.currentMillis()` : 현재 시간을 millisecond로 반환한다.
  * `Runtime.getRuntime().totalMemory()` : Heap 메모리 총 용량을 반환한다.
  * `Runtime.getRuntime().freeMemory()` : 현재 사용 중인 Heap의 여유 공간을 반환한다.
  
* ### 비교 결과 ###
  <img width="526" alt="performanceResult" src="https://user-images.githubusercontent.com/78818063/161706900-61081d69-2442-41a9-bc81-fb03906a4516.png">
  
  성능은 String 객체의 연산이 다른 두 방법에 비해 현저히 떨어지는 것을 확인할 수 있다. 결론적으로 String  
  객체는 반드시 설계 목적 그대로 상수로서 재사용되거나 보안이나 중요한 데이터 등의 값을 온전히 보존해야 하는 등   
  또 문자열 연산 횟수가 적어 빌더 클래스를 쓸 때 보다 효율이 좋은지 등 여러 요소를 고려해볼 필요가 있다.  
  그리고 사용한다 하더라도 인스턴스의 `참조 상태(Reachable State)`를 잘 `유지`시켜 무분별하게 가비지가 쌓이지 않도록 한다.  
  만약 위 같은 상황이 아니라면 평소 빌더 클래스들을 사용해 문자열을 다뤄 **메모리 누수를 막는 습관**을 기르는 것도 중요하다.  

<br>
