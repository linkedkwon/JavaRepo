## GC(Garbage Collection)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Memory-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/GC-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>


### 1. Garbage와 GC
GC를 이해하기 전 `가비지`가 무엇이며, 인스턴스가 가비지가 되는 과정을 먼저 이해해야 한다.  
![garbage](https://user-images.githubusercontent.com/78818063/159172620-810037db-610f-4612-b0cb-7b1c2fbec034.png)  
위 그림은 스택의 모든 원소를 꺼내 문자를 만들고 있다. 모든 원소를 pop 한 뒤 스택은 비어있는 상태가 되고  
더 이상 stack.pop()으로 이전 원소에 `참조(또는 접근)`할 수 없다. 그렇다면 메모리 내부는 어떨까?  
코드와 다르게 이전에 사용했던 스택의 원소들은 여전히 `메모리가 할당된 상태로 잔존`하고 있다.  
가비지란 이렇게 사용했던 `인스턴스가 참조(레퍼런스) 주소를 잃고 난 뒤에도 여전히 메모리에 할당되어 있는 상태`를 말한다.  

자바는 이렇게 사용하지 않는 쓰레기 값들을 자동으로 관리하는데 기본적인 원칙은 다음과 같다.

    Heap 영역의 오브젝트 중 Stack(또는 외부)에서 도달 불가능한 (Unreachable) 오브젝트들은 가비지 컬렉션의 대상이 된다.
가비지 컬렉션은 `Heap` 영역에 잔존하고 있는 가비지, 즉 누수된 메모리를 자동으로 관리해주는 기법이다.  

### 2. STW(Stop-The-World)와 GC 튜닝
그렇다면 GC는 언제 작동할까? 일반적으로 런타임에 다음 조건 중 하나를 만족할 경우 실행된다.  
* OS로부터 할당받은 메모리가 부족한 경우
* Heap 영역에 사용되고 있는 메모리가 허용 임계치를 초과한 경우 

이외에도 사용자가 `System.gc()` 함수를 호출해 직접 GC를 수행할 수 있는데  
모든 스레드가 작업을 중단하기 떄문에 가급적 직접적인 호출은 권장하지 않는다.

GC가 실행될 때, JVM의 내부는 해당 스레드를 제외한 `모든 스레드가 작업을 중단`한다.   
이를 `STW(Stop-The-World)`라 부르는데 보통 GC가 수행되는 시간을 의미하며, 해당 시간을 줄여주는 것을 `GC튜닝`이라 부른다.  
그리고 GC 작업이 모두 끝난 후 잠시 중단됬던 스레드들이 모두 작업을 재시작한다.

GC가 진행된 후에는 해당 결과를 분석하고 튜닝 여부를 결정할 수 있다.  
보통 수행에 소요된 시간이 1초 미만이라면 굳이 튜닝을 하지 않아도 되지만, 시간이 1초를 넘어간다면 적극적으로 튜닝을 고려해야 한다.  
프로그램 내부의 인스턴스 생성을 최소화하고 객체 주기를 잘 설정하여 사용하는 등 메모리 누수를 예방해야 한다.(다른 문서에서 자세히 다룸)


### 3. Weak Generational Hypothesis, GC 알고리즘
그렇담 어떤 객체가 가비지인지, 또 계속 참조되고 있는지 GC는 어떻게 판별할까? 모든 객체가 가비지인지 검사하는 방법은  
애플리케이션의 규모에 따라 심각한 자원 낭비를 초래할 수 있어, 설계자들은 다음의 가설 `Weak Generational Hypothesis`에 따라 GC의 작동 원리를 구안해냈다.   

        1. 모든 인스턴스는 금방 접근 불가능한 상태(Unreachable)가 된다.  
        2. 오래된 객체가 새로운 객체로 참조하는 일은 거의 없다.
두 가지 데이터 기반 가설을 토대로 Heap 영역을 몇 가지 공간으로 구분하였으며, 사용 빈도나 주기가 높은 인스턴트를  
다른 공간으로 옮기고 빈도가 낮은 나머지 요소들은 메모리에서 제거하는 Mark and Sweep 방식을 사용한다.

<img width="726" alt="heap" src="https://user-images.githubusercontent.com/78818063/159177343-1676c665-09e9-4d63-be9b-dcb531efa0f9.png">  

위 그림처럼 Heap은 참조 빈도에 따라 인스턴트를 적재할 영역을 세 가지로 분류한다.


### 4. Mark-Sweep-Compact 방식  
분리된 세 가지 영역과 GC 방식을 보기 앞서 할당된 인스턴스가 가비지임을(참조 가능 여부 확인) 판단할 수 있도록 하는  
`Mark-Sweep-Compact` 알고리즘에 대해 이해해야 한다. 아래 과정에서 실제 가비지가 어떻게 삭제되는지 살펴보자.  

**(1). 메모리 누수 단계**  

<img width="750" alt="leaked" src="https://user-images.githubusercontent.com/78818063/160218454-db4b0e8e-02f7-4d89-935c-8731bf3c071c.png"> 

먼저 String 배열의 네 번째 요소이 값을 변경하여, 기존 "d"가 저장된 인스턴스가 참조를 잃어버렸고 `가비지`가 되었다.

**(2). Mark 단계**  

<img width="749" alt="mark" src="https://user-images.githubusercontent.com/78818063/160218337-e4afede5-a3f1-4fa9-9c61-5974e8e5d527.png">

가비지 컬렉터에는 힙 외부에서 내부로 인스턴스를 참조하는 `변수(Static, Local)`나 `스레드`를 모은 `Root`라는 오브젝트를 생성해둔다.    
그리고 GC를 실행할 때 가비지 컬렉터는 `GC Root` 의 오브젝트들이 참조하는 인스턴트들을 차례대로 탐색하며 `마크(Mark)` 처리 한다.

**(3). Sweep 단계**  

<img width="751" alt="sweep" src="https://user-images.githubusercontent.com/78818063/160218339-8b448e1a-64b4-44a2-8cd3-f3bd7c541649.png">

위 Mark 단계가 끝나고 나면 힙 내부를 순환하며 Mark 되지 않은 인스턴트들을 모두 삭제한다. 

**(4). Compact 단계**  

![compaction](https://user-images.githubusercontent.com/78818063/160219727-41aa0b91-a5ef-4d95-a627-691ee0563b78.png)

Sweep 으로 참조 불가능한 인스턴트들이 모두 반환되고 나면 `Compaction` 작업이 진행되는데,  
윈도우의 디스크 조각 모음과 같이 파편화된 메모리 영역의 인스턴트들을 앞에서 부터 채워나간다.


### 5. Young 영역과 Minor GC

**Young Generation**  
새로 생성된 인스턴스는 모두 이곳에 할당된다. Young 영역은 다시 세 가지로 구분되는데, 먼저 모든 객체들은 `Eden 영역`에 적재된다.    
이 영역이 가득차게 되면 GC(Minor)가 일어나며, 참조 가능한(Reachable) 인스턴트들은 모두 `S0(From, Survivor 0) 영역`으로 옮기고, 나머지 참조 불가능한(Unreachable) 인스턴트들을 반환한다.  
다음으로 S0 영역이 가득차게 되면 앞의 방법과 마찬가지로 참조 가능한 인스턴트들을 모두 `S1(To, Survivor 1) 영역`으로 옮긴 뒤 나머지 인스턴트들은 반환한다.  
인스턴스들은 위 과정을 반복하며 사용 기간에 따른 `Age` 값을 부여받는다. 이 Age 값이 일정 기준(MaxTenuringThreshold)를 넘기면 해당 인스턴스는 오래 참조 되었다 판단하여  
`Old Generation` 으로 이동시킨다. 이때 GC 중 인스턴스를 옮기기 위해 반드시 S0과 S1 중 한 쪽 공간은 비어있어야 한다.

![gc](https://user-images.githubusercontent.com/78818063/160214229-bb1bd188-fb7f-43a6-8997-a4f9d913bd24.png)

이떄 앞서 설명한 가설과 반대로 Old 영역의 객체가 Young 영역의 객체를 참조하는 경우가 있을 수 있다.  
Minor GC가 일어날 때 Old 영역의 인스턴스의 참조 여부를 모두 검사하기엔 낭비가 너무 심하다.  
그래서 Old 영역에서 Young 영역으로의 참조 여부를 표시할 `카드 테이블`을 두어 해당 참조 정보들을 기록한다.   
그리고 Minor GC가 일어날 때 해당 테이블을 참조하여 인스턴스의 삭제 여부를 판단한다. 위 방식은 전반적인 GC의 성능을 끌어올린단 장점을 가진다.

### 6. Old 영역과 Major GC, Permanent(Java 8 이후 Metaspace) 영역, Full GC
**Old Generation**  
Old 영역은 Minor GC에서 살아남은 인스턴트들이 적재되는 곳으로 Young 영역보다 초기에 할당 받은 메모리의 크기가 더 크다.  
해당 영역에서 일어나는 GC를 `Major GC`라 부르며 Minor GC 보다 횟수가 적게 일어나며 상대적으로 오래 사용되고 있는 인스턴스들을 관리한다.   

**Permanent Generation**  
Perm 영역은 보통 Class의 Meta 정보나 Method의 Meta 정보, Static 변수와 상수 정보들이 저장되는 공간으로  
흔히 메타데이터 저장 영역이라고도 한다. 이 영역에서 일어나는 GC는 Major GC의 횟수에 포함된다.  
Java 8 부터는 Native 영역으로 이동하여 Metaspace 영역으로 변경되었는데,  
기존 Perm 영역에 존재하던 `Static Object` 와 `String Object` 는 Heap 영역으로 옮겨져 최대한 GC 대상이 될 수 있도록 하였다.  
쉽게 말해 기존에 Perm 영역에서 수정이 필요가 없는 정보(Only-Read)들은 유지하고, 수정 또는 메모리 반환이 필요한 대상은 Heap 영역에서 관리하도록 변경하였다.  

**Full GC**
Heap 전체를 대상으로 클리어하는 작업(Young 영역 + Old 영역)을 `Full GC`라고 부른다.  

### 7. GC 종류
Java에서 제공되는 GC 알고리즘은 대표적으로 다섯 가지 방식이 있는데, JDK 8에서는 Parallel GC를  
JDK 9는 G1 GC, JDK 11은 ZGC(다른 문서에서 자세히 다룸)를 기본 방식으로 채택하였다.  

**(1). Serial GC(-XX:+UseSerialGC)**  
Serial이란 의미 그대로 모든 GC가 `싱글 스레드(Single Thread)`로 처리되는 방식을 의미한다.  
앞서 설명한 `Mark-Sweep-Compact` 알고리즘을 사용하며 규모가 상당히 작은 애플리케이션이나 CPU 코어 개수가 적은 경우 사용을 권장한다.

**(2). Parallel GC (-XX:+UseParallelGC)**  
Serial GC을 병렬화한 방식으로 JDK 8의 기본 채택 방식이다. 속도를 높이기 위해 `Young 영역`의 작업을  
`멀티 스레드(Multi Thread) 방식`을 사용해 작업 시간을 줄인다. 주로 멀티 코어와 하드웨어 성능이 좋을 때 사용하길 권장한다.  
아래 그림은 Serial GC와 parallel GC의 차이를 나타낸다. 

<img width="639" alt="parallel" src="https://user-images.githubusercontent.com/78818063/160220592-de1ac3e8-af1e-431e-a291-da2e0a543eca.png">

**(3). Parallel Old GC(-XX:+UseParallelOldGC)**  
Parallel GC 방식이 Young 영역에서만 병렬 처리를 했다면, 더 업그레이드 해 `Old 영역까지 병렬 처리`하는 방식을 의미한다.  
또한 GC 이후 별도로 남아있는 인스턴스를 식별하는 `Mark-Summary-Compaction` 알고리즘을 사용한다.  

**(4). CMS GC (-XX:+UseConcMarkSweepGC)**  
애플리케이션의 응답 속도가 매우 중요한 경우로 STW 시간이 1초 미만을 유지해야 하는 경우 사용을 권장한다.  
참조 가능한 인스턴스를 4 가지 단계로 나눠 찾는 방식을 의미한다.  

* **Initial Mark**  
  GC Root가 참조하는 객체 중 `가장 가까이 있는 객체`만 마킹하는데 매우 짧게 STW가 발생한다.

* **Concurrent Mark**  
  앞서 마킹한 객체를 기준으로 참조하고 있는 객체들을 `트래킹` 해 GC 대상인지 여부를 확인한다.  
  해당 작업은 병렬로 특정 스레드가 진행하여 STW 가 발생하지 않는단 장점을 지닌다. 

* **Remark**  
  Concurrent 단계에서 검사한 객체들의 변경 사항을 확인하는 단계로 새로 추가되거나 참조가 해제된 인스턴스를 확인한다.  
  이 단계는 STW 가 발생하므로 최대한 시간을 줄이기 위해 멀티스레드로 검증 작업을 수행한다.  

* **Concurrent Sweep**  
  마지막은 Sweep 작업으로 앞 세 단계 과정을 진행하며 찾은 가비지를 반환한다.

위 단계를 보면 작업 시간이 확실히 빠르단 장점이 있는 반면 다른 방식보다 메모리와 CPU 사용량이 매우 높단 것을 알 수 있다.  
또한 Compaction 작업을 수행하지 않기 때문에 GC 이후 Compaction 작업을 임의로 실행하면 꽤 시간을 소비할 수 있다.  
따라서 STW 시간과 Compaction 수행 시간을 잘 판단하며 사용해야 하며, 메모리 단편화가 매우 심한 경우에만 Compaction 작업을 수행하는 것이 좋다.  

**(5). G1 GC(Garbage Frist GC) (-XX:+UseG1GC)**  
앞서 설명한 방식들은 특정 영역을 구분하고 크기를 할당해 구조화한 방식들로 일반적으로 하드웨어 메모리 크기가  
작은 경우 좋은 성능을 발휘한다. 반면에 하드웨어 산업이 발전하면서 기본 사용되는 메모리 크기들이 점차 커졌고,  
Young 과 Old 영역처럼 분리된 공간에 더 큰 메모리를 할당하는 방식은 탐색 시간에 있어 오히려 `역효과`를 가질 수 있다.   

G1 GC는 위 같은 단점에 반해, 즉 `메모리 크기 자체가 큰 환경`에 초점을 맞춰 나온 방식이다. 이전 방식과는 전혀 다르게 Heap 메모리를 관리하는데,  
전체 영역을 `Region` 이란 특정 크기로 나누어 각 Region 상태에 따라 Eden, Survivor, Old 등의 역할을 `동적`으로 부여한다.  
JVM의 힙은 각 1MB ~ 32MB 사이로 지정할 수 있는 Region 을 2048개로 나눌 수 있다. 

<img width="405" alt="g1gc" src="https://user-images.githubusercontent.com/78818063/160222670-abbe283c-990f-4257-b79c-fdc0517ab906.png">

기본적인 GC의 동작은 GC 대상이 가장 많은 Region에 우선적으로 Young GC가 일어난다.  
그리고 해당 Region에서 살아남은 인스턴스들은 다른 Region으로 옮긴 후 위 작업을 반복하는 방식이다.  
GC를 통해 비워진 Region의 경우는 사용 가능한 Region으로 다시 만들어 새로운 인스턴스가 생성될 때 다시 동적으로 할당받는다.  
이러한 동적인 방식으로 높은 성능을 발휘하며 현재 JDK 9에서 기본 방식으로 채택해 사용 중이다.


### 8. GC가 중요한 이유
앞서 언급했듯이 GC가 중요한 이유는 STW, 즉 모든 작업 스레드가 중단된다는 점이었다.  
실시간으로 서비스 중인 애플리케이션의 모든 스레드가 갑자기 수 초간 멈추는 상황이 생긴다면 큰 문제임이 틀림 없으며,      
다시 스레드가 복구된다 하더라도 클라이언트의 요청이 WAS(Web Application Server)에 할당된 대기열을 초과해 커넥션이 끊기는 등  
추가적인 문제로 번지기 쉬울 것이다. 그래서 개바라들은 메모리 누수를 막기 위해 코드와 리팩토링에 신경을 쓰고 WAS 환경 관리나 GC 모니터링 등 지속적인 튜닝을 해나가야만 한다.  
또 사용하고 있는 버전에 따른 GC의 방식을 이해하여 효율적으로 메모리와 객체를 관리할 수 있다면, 더욱 Java에 대한 이해도와 사용성을 높일 수 있을 것이다.  