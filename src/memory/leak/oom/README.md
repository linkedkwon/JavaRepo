# OOM(Out Of Memory)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Memory-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Leak-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/OOM-blue"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>


## 1. OOM이란?
OOM이란 특정 작업을 수행하며 JVM(Java Virtual Machine)에 더 이상 `메모리를 할당할 수 없는 현상`을 의미한다. 이는 애플리케이션이 
비정상적으로 종료되는 주요 원인 중 하나로, 주로 Heap 영역의 메모리가 누수되어 새로운 공간을 할당받지 못하거나 GC(Garbage Collection) 
실행 시간, `STW(Stop The World)`가 길어 메모리를 사용하지 못할 때 발생한다.

<img width="710" alt="oom" src="https://user-images.githubusercontent.com/78818063/160732892-93ea109e-009e-484b-9c27-d69199fb27be.png">

<br>

## 2. 영역별 발생 원인
OOM은 앞서 설명한 Heap 영역 외에 다른 영역에서도 발생할 수 있는데, 각 영역의 오류와 원인은 다음과 같다.

  * ### Heap 영역(java.lang.OutOfMemoryError : heap sapce)
    가장 빈번히 일어나는 OOM 케이스로 **Heap 영역에 새로운 인스턴스를 할당할 수 없는 경우**에 발생한다. 주로 OS로부터 할당받은 영역의 기본 크기가 
    생성 객체보다 작은 경우, 가비지(더 이상 참조 불가능한 인스턴스)가 쌓여 새로운 공간을 할당하지 못하는 상황, 실행과 속도가 보장되지 않는 객체 
    소멸자 `finalize`(jdk 9 이하 버전, 다른 문서에서 자세히 다룸) 메소드를 명시적으로 사용함으로서 GC 마킹 시간이 길어져 메모리를 할당받지 
    못하는 경우 등에서 발생한다. 이를 예방하는 방법으로는 테스트를 통해 Heap 영역의 크기를 적절히 조정하거나 튜닝으로 메모리 누수를 막는 방법 등이 있다.

  * ### Metaspace(jdk 7 이하 permanent space, java.lang.OutOfMemory: Metaspace) ###
    Metaspace는 JVM의 클래스 로더가 로드한 **클래스들의 메타데이터가 저장되는 영역으로 해당 공간이 가득 차게 되면 OOM이 발생**할 수 있다. jdk 7 버전 
    이하에서는 해당 영역을 Permanent Space라 부르며, JVM이 로드될 때 해당 영역에 고정된 메모리 크기를 할당 하기 때문에 OOM 발생했을 때 **할당되는 고정 
    크키를 늘려 오류를 해결**할 수 있다. 이는 Java 8부터 Permanent 영역이 Heap 영역에서 Native Heap의 `Metaspace`로 대체되었는데, 영역 자체에 
    할당된 메모리가 `동적`으로 늘어나도록 설계되어 이전과 같이 고정된 할당 사이즈로 인한 문제는 해결되었다. 하지만 **무한정 사이즈가 늘어나 다른 영역의 할당 크기가 
    줄거나 메모리가 Swap되는 문제**가 발생할 수 있다. 따라서 애플리케이션에 특성에 따라 **적절한 메모리 크기(MaxMetaSpaceSize)를 할당하고 지속적으로 관리할 
    필요**가 있다.

  * ### GC 오버헤드(java.lang.OutOfMemoryError: GC Overhead limit exceeded) ###
    GC 오버헤드는 의미 그대로 GC가 빈번하게 일어나 생긴 오류로 GC 작업을 통해 확보한 메모리가 `전체 메모리의 2%` 이하인 경우 발생한다. 
    즉 **실시간으로 사용되고 있는 인스턴스가 매우 많거나 메모리가 해당 용량만큼 누수된 경우**를 의미한다. 사실 1번 오류와 같은 맥락으로 누수된 
    메모리를 찾아 Heap 영역에 할당할 사이즈를 확보해 오류를 해결할 수 있다.

  * ### Native Heap ###
    위 세 가지 항목은 개발자가 변수나 인스턴스를 생성하며 생길 수 있는 오류였다면 **JVM이 사용하는 Native Heap 영역에서도 OOM이 발생**할 수 있다. 
    해당 영역은 주로 Thread 관리, Buffer 작업, JNI(Java Native Interface, 다른 언어로 작성된 라이브러리를 다루는 인터페이스) 
    사용 및 호출, JIT(Just In Time) 컴파일러와 인터프리터 실행 등의 여러 작업을 수행한다. 따라서 Thread Stack과 같이 작업에 필요한 테이블 
    공간이 부족하거나 이러한 구성 요소들이 누수되는 경우에 OOM이 발생할 수 있다. 해당 오류는 보통 `OS의 자원소진`일 확률이 높기 때문에 외부 자원량을 
    늘리거나 테스트를 통해 상태를 관리할 필요가 있다.

<br>

## 3. OOM에 대처하기 ##
앞서 OOM이 무엇인지와 함께 발생할 수 있는 여러 케이스에 대해 알아보았다. 그렇다면 아래에서 이러한 문제를 해결할 수 있는 유용한 방법들에 대해 
알아보자.

  * ### Heap 모니터링 ###
  Heap 모니터링은 OOM을 예방할 수 있는 가장 대표적인 방법으로 실시간으로 가동 중인 애플리케이션의 **메모리 점유 상황을 시각화하여 현재 상태를 확인할 수 있다**. 
  아래는 모니터링 도구 `Visual VM`을 사용해 실제 러닝중인 애플리케이션의 메모리를 대시보드로 확인하고 있는데, `Heap`과 `Metaspace` 뿐만 아니라 로드된 
  클래스와 CPU 상태까지도 확인 할 수 있다. 따라서 실시간으로 일어나는 로직에 대한 전반적인 자원 상황이 확인 가능해 문제 발생 시 빠른 대처가 가능하다.

  <img width="700" alt="visualvm" src="https://user-images.githubusercontent.com/78818063/160731188-6398ef32-bc04-4196-a82f-9c851e0c7e39.png">

  * ### Heap Dump 분석 ###
  `Heap Dump`는 **특정 시점의 Heap 메모리 상황을 본 떠 파일로 저장한 것을 의미**한다. 보통 오류가 생겨 JVM이 작동을 멈추거나 부하 테스트 중 상황을 확인하기 위해 사용하는데, 
  `특정 시점`에 사용되었던 인스턴스들에 대한 정보를 한 눈에 파악하고 문제 발생 소지를 찾을 수 있다. 해당 파일을 분석하면 언제 **어떤 인스턴스가 메모리를 많이 사용했는지** 등 자세한 
  자원 사용 정보까지 알 수 있어 미연에 사고를 방지할 수 있다. 또한 테스트 중 서비스 로직을 직접 수행해보며 코드를 리팩토링을 해 애플리케이션의 성능을 개선하는데도 사용된다.

  <img width="700" alt="heapdump" src="https://user-images.githubusercontent.com/78818063/160731194-47c8222d-565a-45e1-9b08-2fea32271885.png">

  * ### 부하 테스트 ###
  모니터링으로 직접적인 메모리 상황을 한 눈에 볼 수 있었다면, 다음은 **서비스를 출시했을 때 예상되는 트래픽에 따른 성능을 직접 테스트**해 볼 수 있다. 애플리케이션에 예상되는 `MCU(Maximum Current User, 동시 접속자 수)` 
  요청을 보내 속도 저하와 OOM 발생 여부까지 미리 체크해볼 수 있다. 아래와 같이 부하 테스트 툴 `JMeter`를 사용해 스레드의 수와 생성 시간 등을 설정해 요청을 보낼 수 있다. 보통 배포하기 전 해당 테스트와 모니터링을 거쳐 실제 
  서비스에서 발생할 수 있는 예기치 못한 상황들을 예방하는데 사용된다.

  <img width="731" alt="mou" src="https://user-images.githubusercontent.com/78818063/160738067-a26190af-e9da-47ae-a37f-b1f11e8f9093.png">
  
<br>