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
  Java에서 문자열을 다루기 위해 사용되는 String 클래스는 기존 문자열에 새로운 문자열을 
  더하거나 만들 수 있는 연산 기능을 제공하고 있다. 아래 코드는 더하기 연산을 수행해 기존 문자열에 
  새로운 문자열을 추가하는 과정을 나타낸다.

  <img width="490" alt="addStrByString" src="https://user-images.githubusercontent.com/78818063/161700490-762a6424-a0ad-429f-8ce7-6780f6d3bff7.png">

  위 로직을 거친 String 객체는 최종적으로 "AAAAA"란 값을 가지게 된다. 여기서 한 가지 특이한 점은 String 객체는 일반 변수처럼 
  기존 변수에 다른 문자열이 더해진 새로운 문자열 값이 할당되는 것이 아닌 반복문을 실행하며 더한 각 문자열은 매번 `String Pool`에 할당된다. 
  그리고 다시 다음 문자열을 더할 때 마다 해당 인스턴스는 `참조 불가능한 상태(Unreachable State)`로 변한다. 즉 String 객체의 
  `불변(Immutable) 상태`라는 특징 때문에 문자열을 만들기 위해 사용되었던 모든 부분 문자열들이 `가비지`가 되어 `GC(Garbage Collection)` 
  대상이 되는 것이다.

<br>


## 2. StringBuffer와 StringBuilder
  이처럼 String 객체를 통해 직접 문자열을 다루는 행위는 상당히 비효율적인데, 그 이유는 내부에 문자를 저장하는 Character 
  배열 또한 불변 상태기 때문이다. 그래서 Java에서는 위 같은 저장 공간을 `가변(Mutable) 상태`로 만든 아래 두 가지 클래스를 제공한다. 

  <img width="564" alt="abstractStringBuilder" src="https://user-images.githubusercontent.com/78818063/161698370-b724bc5f-545e-4cde-a31d-9058f2c8ee2d.png">

  `StringBuffer`와 `StringBuilder` 클래스는 모두 `AbstractStringBuilder` 추상 클래스를 상속 받고 있어 내부적으로 문자 값을 
  저장하는 `'value'` 배열(버퍼)과 현재 문자열의 사이즈를 나타내는 `count` 변수를 가지고 있다. 이 두 변수를 사용해 문자열을 생성하거나 
  수정할 때, 또 합치는 경우 필요한 만큼 사이즈를 초기화하고 추가할 문자열을 해당 배열에 할당한다. 이는 초기에 생성된 Character 배열을 계속 
  참조 가능한 상태로 유지시켜 매번 새로운 인스턴스를 만들지 않으므로 보다 효율적으로 메모리를 관리할 수 있다.

<br>

## 3. StringBuffer vs StringBuilder 
  앞서 소개한 두 개의 빌더 클래스는 모두 내부 변수에 `가변성`을 부여하는 똑같은 방식을 사용하는데, 각각 사용 목적에서 차이점을 두고 있다. 
  먼저 StringBuffer는 내부 메소드에 동기화 처리 기능을 부여하는 키워드 `Synchronized`를 사용한다. 이는 특정 스레드가 작업을 하고 
  있을 때 다른 스레드가 해당 메소드에 접근하는 것을 막는다. 즉 멀티 스레드 환경에서 같은 변수를 참조해 생길 수 있는 `동시성 문제(또는 Race Condition)`를 
  해결하기 위해 고안된 방식이다. 반면에 StringBuilder는 위처럼 동시성에 관여하는 기능을 두지 않는 대신 빠른 속도와 성능을 보장한다. 
  따라서 멀티 스레드 환경에서 StringBuffer를, 싱글 스레드 환경 또는 사이드 이펙트가 존재하지 않을 경우는 StringBuilder를 사용하길 권장한다.  

  <img width="485" alt="addStrByBuilder" src="https://user-images.githubusercontent.com/78818063/161700500-1bfbd4cb-abe0-4119-8517-95882c0c712a.png">

  **단 한 가지 주의할 점은 두 클래스를 사용하면 초기 버퍼 공간을 할당해야 하며, 매 연산 시 사이즈 지정과 같은 부가 작업이 수행되기 때문에 
  문자열의 연산 횟수가 작거나 자주 재사용되는 경우 두 방법 보단 일반 String 클래스를 사용하는 것이 바람직하다.**

<br>

## 4. 성능 비교 ##
  아래는 싱글 스레드 환경에서 새로운 문자열을 만들 때(+연산 수행) 세 가지 방식의 성능을 비교하고 있으며, 
  `'작업 수행 시간'`과 `'힙 메모리 사용량'` 두 가지를 척도로 한다. 

* ### 성능 비교에 사용할 메소드 ###
  <img width="812" alt="performanceCode" src="https://user-images.githubusercontent.com/78818063/161705902-6b4f7fc7-d670-4773-97cd-7eca72301aef.png">

  * `System.currentMillis()` : 현재 시간을 millisecond로 반환한다.
  * `Runtime.getRuntime().totalMemory()` : Heap 메모리 총 용량을 반환한다.
  * `Runtime.getRuntime().freeMemory()` : 현재 사용 중인 Heap의 여유 공간을 반환한다.
  
* ### 비교 결과 ###
  결과를 보면 먼저 String 객체를 통한 연산 성능이 다른 두 방법에 비해 현저히 떨어지는 것을 확인할 수 있다. 즉 String 객체는 
  설계 목적 그대로 상수로서 재사용되거나 보안과 같이 중요한 데이터를 온전히 보존해야 하거나 연산 횟수에 따른 효율 등 여러 가지 
  요소를 고려해 사용할 필요가 있다. 만약 사용한다 하더라도 인스턴스의 `참조 상태(Reachable State)`를 잘 `유지`시켜 무분별하게 
  가비지가 쌓이지 않도록 주의해야 한다. 만약 위 같은 상황이 아니라면 앞서 언급했듯이 동기화 여부를 판단해 적절한 빌더 클래스를 
  선택하고 **메모리 누수가 일어나지 않도록** 해당 클래스들을 사용해 문자열을 다루도록 하자.

  <img width="526" alt="performanceResult" src="https://user-images.githubusercontent.com/78818063/161706900-61081d69-2442-41a9-bc81-fb03906a4516.png">

<br>
