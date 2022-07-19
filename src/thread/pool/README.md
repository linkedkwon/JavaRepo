# 스레드 풀(Thread Pool)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Thread-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Pool-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 스레드 풀이란?
멀티 스레드 환경에서는 작업량이 증가함에 따라 **스레드를 생성하고 관리하는 비용 또한 급격히 증가**한다. 스레드를 생성하면 OS(Operating System)는 
메모리 영역에 스레드를 할당하고 스케줄링과 제어같은 부가 작업을 처리하고 모든 작업을 수행하고 나면 스레드의 할당을 해제한다. 생성된 스레드는 모두 동일한 
메모리 영역에서 생성되고 관리되지만, 이같이 **생성과 수거에 드는 비용을 무시할 수 없다**.

![pool](https://user-images.githubusercontent.com/78818063/179652012-f181d892-6ae0-4b38-a5f6-56367aeb4c02.png)

`스레드 풀`은 작업 처리에 사용되는 스레드를 요청에 따라 매번 생성하지 않는다. 제한된 개수를 **`풀`(Pool, 필요할 때마다 개체를 생성 및 삭제하는 것이 
아닌 사용 준비 상태로 초기화해둔 개체 집합)에 미리 생성해두고 작업 큐 (Queue)에 들어오는 작업들을 하나씩 스레드가 맡아 처리한다**. 그러면 작업처리 
요청이 폭주하더라도 스레드의 전체 개수는 늘지 않고 순번을 대기하는 상태가 되어 시스템의 성능저하를 방지할 수 있다. 단 **초기에 너무 많은 양의 스레드를 
할당해두면 메모리 낭비가 심해질 수 있어 테스트를 거쳐 필요한 스레드 개수를 예측해서 할당**하는 것이 좋으며, **먼저 작업이 끝난 스레드가 놀고 있는 상태인 
유휴시간 발생**을 막기 위해 `ForkJoinPool`을 사용할 수 있다.

<br>

## 2. 스레드 풀 생성 및 종료
스레드 풀 객체는 `ExecutorService 인터페이스` 타입을 가지며, `Executors 클래스`에서 제공하는 **정적 메소드들을 통해 구현체를 할당 받는다**. 
크게 세 가지 방식으로 생성 가능하며, 풀의 설정 값이 정해져 있는 클래스를 사용하거나 직접 설정 값을 초기화 가능하다. 설정 값은 보통 아래의 세 가지 
구성요소를 포함한다.

* **초기 스레드 수** : ExecutorService 객체가 생성될 때 기본적으로 생성되는 스레드 수
* **코어 스레드 수** : 스레드가 늘어난 후 사용되지 않은 스레들르 풀에서 제거할 때 최소한으로 유지할 스레드 수
* **최대 스레드 수** : 스레드 풀에서 관리 가능한 최대 스레드 수

<br>

  * ### 생성1 - newCachedThreadPool() : 초기 스레드 0, 코어 스레드 0, 최대 스레드 Integer.MAX_VALUE ###
    현재 스레드 개수보다 작업 개수가 많아지면 새로운 스레드를 생성해 작업을 처리하며, 만약 스레드가 **1분 동안 아무런 작업을 하지 않으면 스레드를 
    풀에서 제거하고 할당 해제시킨다**.

    ![case1](https://user-images.githubusercontent.com/78818063/179719865-efc068eb-6d8b-443c-9112-c547a00d7885.png)
    
  * ### 생성2 - newFixedThreadPool(int nThreads) : 초기 스레드 0, 코어 스레드 0, 최대 스레드 nThreads ###
    처리 방식은 newCachedThreadPool()과 동일하지만, 해당 구현체는 **스레드가 작업을 수행하지 않아도 제거되지 않는다**.

    ![case2](https://user-images.githubusercontent.com/78818063/179719874-9b7a55fd-469e-4e4b-8588-2f1823d76aab.png)

  * ### 생성3 - ThreadPoolExecutor() : 원하는 값 구성 ###
    앞서 설명한 두 가지 메소드를 사용하지 않고, **직접 스레드 개수들을 설정하고 싶다면** 생성자를 통해 `ThreadPoolExecutor 구현체`를 
    생성하면 된다.

    ![case3](https://user-images.githubusercontent.com/78818063/179719885-d7fa4bea-139f-4a1e-91c6-57f9bfb39798.png)

  * ### 스레드 풀 종료 ###    
    스레드 풀은 기본적으로 데몬 스레드가 아니므로 **main 스레드가 종료되어도 계속 실행 상태로 남아있다**. 따라서 애플리케이션을 종료하려면 
    스레드풀을 종료시켜 스레드들이 종료 상태가 되도록 처리해야 한다. ExecutorService 구현체는 기본적으로 아래와 같은 세 가지 종료 메서드를 
    제공한다.

    * **shutDown()** : 작업큐에 남아있는 작업을 모두 마무리 후 종료
    * **shutDownNow()** : 작업큐에 작업 잔량과 관계없이 강제 종료
    * **awaitTermination(long timeout, TimeUnit unit)** : 모든 작업 처리를 인자로 받는 timeout 시간안에 처리하면 true 값을, 
      그렇지 않으면 모든 스레드를 중단시키고 false 값을 리턴한다.
      
<br>

## 3. 스레드 풀에 작업 처리 요청
기본적으로 스레드 풀에 작업 처리를 요청하기 위해서는 `Runnable 인터페이스` 또는 `Callable 인터페이스`를 구현한 구현체를 생성하고 각각 `run()`, 
`call()` **메소드를 오버라이딩해 수행할 작업 로직을 추가**한다. 그리고 해당 구현체를 앞서 설명했던 ExecutorService 구현체에 `execute()` 또는 
`submit()` **메소드에 인자로 넘겨주면 된다**. 

![case4](https://user-images.githubusercontent.com/78818063/179719892-e62c59b9-65f1-402e-82dc-ad79621b8715.png)

여기서 execute() 메소드는 작업 처리 결과를 따로 반환하지 않으며, 처리 중 **예외가 발생하면 해당 스레드를 종료하고 풀에서 제거시킨다**. 
반면에 submit() 메소드의 경우는 **작업 처리 결과를 알 수 있도록 Future를 리턴하고, 예외가 발생하더라도 스레드가 종료되지 않고 다음 
작업을 위해 재사용**된다. 따라서 **스레드를 생성하는 오버헤드를 고려하여 submit()을 사용하는 것이 바람직하다**.

<br>
