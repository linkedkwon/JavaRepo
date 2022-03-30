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
특정 작업을 수행할 때 JVM 메모리의 각 영역에서 더 이상 `메모리를 할당할 수 없는 현상`을 의미한다.  
애플리케이션이 비정상적으로 종료되는 주요 원인 중 하나로, 주로 Heap 영역에서 메모리가 누수되어 새로운 공간을  
할당받지 못하거나 GC(Garbage Collection) 실행 시간, STW(Stop The World)가 길어 메모리를 사용하지 못할 때 발생한다.  
<br/>
<img width="710" alt="oom" src="https://user-images.githubusercontent.com/78818063/160732892-93ea109e-009e-484b-9c27-d69199fb27be.png">

<br>

## 2. 영역별 발생 원인
OOM은 앞서 설명한 Heap 외에 다른 영역에서도 발생할 수 있는데 각 영역의 오류와 원인은 다음과 같다.

* ### Heap 영역(java.lang.OutOfMemoryError : heap sapce)
  가장 빈번히 발생하는 케이스로 Heap 영역에 새로운 인스턴스를 할당할 수 없는 경우에 일어난다.  
주로 OS로부터 할당받은 영역의 기본 크기가 생성 객체보다 작은 경우, 가비지가 쌓여 공간을 할당하지 못하는 상황 또는  
실행과 속도가 보장되지 않는 객체 소멸자 `finalize`(jdk 9 이하 버전, 다른 문서에서 자세히 다룸)  
메소드를 명시적으로 사용해 GC 마킹 시간이 너무 길어져 메모리를 할당받지 못하는 경우 등에서 발생할 수 있다.  
해결 방법은 테스트를 통해 Heap 영역의 크기를 적절히 조정하거니 튜닝으로 메모리 누수를 막는 방법 등이 있다.


* ### Metaspace(jdk 7 이하 permanent space, java.lang.OutOfMemory: Metaspace) ###
  JVM의 Classloader가 로드한 클래스들의 메타데이터가 저장되는 영역으로 해당 공간이 모두 가득 차면 발생한다.  
jdk 7 이하 Permanent space는 JVM이 로드될 때 해당 영역에 고정된 메모리 크기를 할당하므로 OOM 발생 시  
고정 크키를 늘려주며 오류를 해결하였다. 그리고 Java 8부터는 이 Perm 영역이 Heap영역에서 Native Heap으로 넘어가 `Metaspace`로 대체되었는데,  
영역 자체에 할당된 메모리가 `동적`으로 늘어나도록 설계되어 무한히 사이즈가 늘어나 다른 영역의 할당 크기가 줄거나 메모리가 Swap되는 문제가 생길 수 있다.  
따라서 애플리케이션에 특성에 따라서 적절한 메모리 크기(MaxMetaSpaceSize)를 할당하는 것이 좋다.

* ### GC 오버헤드(java.lang.OutOfMemoryError: GC Overhead limit exceeded) ###
  의미 그대로 GC가 빈번하게 일어나 생긴 오류로 GC 작업을 통해 확보한 메모리가 `전체 메모의 2%`인 경우 발생한다.  
즉 실시간으로 이용되고 있는 객체가 매우 많거나 메모리가 누수된 경우를 의미한다. 사실 1번 오류와 맥락은 같기 때문에  
Heap 영역의 크기를 확보하거나 메모리 누수를 찾아 해결하도록 한다.

* ### Native Heap ###
  위 세 가지 항목이 Java Object를 관리 중 생기는 오류라면 JVM이 사용하는 공간인 Native Heap에서도 OOM이 발생할 수 있다.  
이 영역은 주로 Thread 관리, Buffer 작업, JNI(Java Native Interface, 다른 언어로 작성된 라이브러리를 다루는 인터페이스) 사용 및 호출 또는  
JIT(Just In Time)컴파일러와 Interpreter의 실행 등의 작업을 수행한다. 따라서 Thread Stack과 같이 작업에 필요한 테이블 공간이  
부족하거나 마찬가지로 메모리가 누수되는 경우에 OOM이 발생할 수 있다. 해당 오류는 보통 `OS의 자원소진`일 확률이 높으므로 외부 자원량을 늘려보는 것이 좋다.

<br>

## 3. OOM에 대처하기 ##
* ### Heap 모니터링 ###
  실시간으로 가동 중인 애플리케이션의 메모리 점유 상황을 시각화하는 여러 툴들이 존재한다.  
  아래는 `Visual VM`을 사용한 실제 러닝중인 애플리케이션의 메모리 대시보드인데, `Heap`과 `Metaspace` 뿐만 아니라 로드된 Class와  
  CPU 상태까지도 확인 할 수 있다. 따라서 실시간으로 일어나는 요청에 대한 전반적인 자원 상황이 확인 가능해, 문제가 발생하면 빠른 대처가 가능하다.
  <br/>  
  <img width="700" alt="visualvm" src="https://user-images.githubusercontent.com/78818063/160731188-6398ef32-bc04-4196-a82f-9c851e0c7e39.png">    

* ### Heap Dump 분석 ###
  
  `Heap Dump`는 특정 시점의 Heap 메모리 상황을 본 떠 파일로 저장한 것을 의미한다.  
  보통 오류가 생겨 JVM이 작동을 멈추거나 부하 테스트 중에 사용하는데, `특정 시점`에 사용되었던 인스턴스들에 대한 정보를 제공받는다.      
  해당 파일을 분석해 언제, 어떤 인스턴스가 메모리를 많이 사용했는지 파악해 문제에 빠른 대응이 가능하다.     
  또한 테스트 중 서비스 로직을 직접 수행해보며 코드를 리팩토링을 해 애플리케이션의 성능을 개선할 수도 있다.     
  </br>
  <img width="700" alt="heapdump" src="https://user-images.githubusercontent.com/78818063/160731194-47c8222d-565a-45e1-9b08-2fea32271885.png">


* ### 부하 테스트 ###
  모니터링으로 직접적인 메모리 상황을 한 눈에 볼 수 있었다면, 다음은 서비스를 출시했을 때 예상되는 트래픽에 따른 성능을 직접 테스트해 볼 수 있다.     
  예상되는 MCU(Maximum Current User, 동시 접속자 수) 요청을 보내 속도 저하, 심하게는 OOM 발생 여부를 미리 체크해볼 수 있다.  
  아래처럼 부하 테스트 툴(JMeter)을 사용해 스레드의 수와 생성 시간 등을 설정하고 요청을 보낸 뒤, 모니터링을 통해 문제 소지 여부를 미연에 방지한 뒤 배포하도록 한다.     
  <br/>
  <img width="731" alt="mou" src="https://user-images.githubusercontent.com/78818063/160738067-a26190af-e9da-47ae-a37f-b1f11e8f9093.png">
  
<br>
