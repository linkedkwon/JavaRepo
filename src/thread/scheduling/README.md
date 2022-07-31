# 스레드 스케줄링(Thread Scheduling)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Thread-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Scheduling-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 스레드 스케줄링
보다 효율적으로 멀티 스레드 기반 프로그램을 제어하기 위해서는 아래 코드와 같이 **스레드의 우선순위를 지정**하거나 **행동을 제어**하는 등의 
`스케줄링 기법`을 사용하는 것이 좋다.

![case1](https://user-images.githubusercontent.com/78818063/182037264-6a6c9f37-7de1-454a-b6e5-239449a41d4a.png)

* **MAX_PRIORITY** : 가장 높은 우선순위 10
* **NORM_PRIORITY** : 일반적인 우선순위 5
* **MIN_PRIORITY** : 가장 낮은 우선순위 1

여기서 `스케줄링`이란 제한된 자원을 여러 프로세스와 스레드가 효율적으로 사용할 수 있도록 특정 기준에 따라 정책을 부여하는 것을 말한다. 
각 **프로세스나 스레드의 실행 순서와 상태를 정의**하고, 이에 따라 특정 시점에 프로세스 또는 스레드에게 CPU 자원을 할당할 수 있다.

<br>

## 2. 스레드의 상태
스레드의 행동을 직접 제어하기 위해서는 먼저 **스레드의 상태**가 무엇인지 이해해야 한다. 아래 항목에서 스레드가 갖는 여러 상태에 대해 
자세히 알아보자.

  * ### 기본 네 가지 상태(생성, 실행 대기, 실행, 종료) ###
    스레드를 생성하고 start() 메소드를 호출하면 곧바로 스레드가 작업을 수행하는 것처럼 보이지만, **실제로는 실행대기 상태를 갖는다**. 
    실행대기 상태란 **아직 스케줄링을 통해 CPU 사용을 할당받지 못해 대기하고 있는 상태**를 뜻하며, 이 중 **스케줄링으로 선택된 스레드는 
    CPU 자원을 할당받아 run() 메소드를 호출하고 실행 상태로 전환**된다. 이렇게 실행 상태로 접어든 스레드는 상황에 따라 실행대기와 실행 
    상태로 반복 전환되며, 작업이 완전히 끝나면 스레드는 종료 상태가 된다. 

    ![img1](https://user-images.githubusercontent.com/78818063/182037330-9b904861-5d16-415e-a7c9-6d82a0d2d30f.png)

  * ### 일시정지 상태 ###    
    앞서 살펴본 네 가지 기본 상태와 함께 특수한 상황이나 사용자에 의해 스레드 실행이 **일시적으로 중단되는 정지 상태가 존재**한다. 

    ![img2](https://user-images.githubusercontent.com/78818063/182037333-a3bdebdb-f14b-4ec8-a68f-edc705744f0c.png)
    
    일시정지 상태는 스레드가 실행할 수 없는 상태로 다른 스레드의 공지를 받을 때까지 대기하는 `WAITING`, 정해둔 시간 동안 대기하는 
    `TIMED_WAITING`, 객체의 락이 풀릴 때까지 대기하는 `BLOCKED`의 세 가지 상태가 존재하며, 일시정지 상태에 있는 스레드가 다시 
    실행 상태로 가기 위해서는 **실행대기 상태에서 스케줄링 과정을 다시 반복해야 한다**.

  * ### 전체 상태 요약 정리 ###    

    ![img3](https://user-images.githubusercontent.com/78818063/182037338-5e353407-0d17-41be-8c87-7142ac6a4bc4.png)

<br>

## 3. 실행 제어하기
앞서 다뤘던 스레드의 상태는 **Thread 클래스에서 제공하는 메소드**를 통해 사용자가 직접 제어 가능하다. 아래 항목에서 스레드를 각 상태로 만들기 
위해 필요한 메소드를 알아보자.

![img4](https://user-images.githubusercontent.com/78818063/182037342-c5517526-4db4-4bff-9977-746a4b4e1960.png)

  * ### 일시정지 sleep() ###
    **주어진 시간 동안 스레드를 일시정지 상태로 만드는 메소드**로, 인자로 정지할 시간(ms)을 받는다. 해당 시간이 지나면 스레드는 다시 실행 상태로 
    전환된다. 

    ![case2](https://user-images.githubusercontent.com/78818063/182037275-549ceeb0-a14d-4a0c-b97b-11ca32bac9d6.png)

  * ### 실행 양보 yield() ###
    실행 중인 스레드에서 **CPU 점유 권한을 자신보다 우선순위가 같거나 큰 다른 스레드에게 넘긴 후 실행 대기 상태**로 전환된다. 일반적으로 소켓이나 
    서버와 같이 루프가 계속 돌고 있는 스레드의 필드로 실행 유무를 나타내는 변수를 두고, **현재 상태(idle)에 따라 다른 스레드에게 점유 권한을 넘겨준다**. 

    ![case3](https://user-images.githubusercontent.com/78818063/182037281-6ae46fd4-0bc9-4fb5-bc2b-2bc0b477a946.png)

  * ### 종료 대기 join() ###
    특정 스레드의 작업이 종료될 때까지 또는 인자로 시간(ms)을 설정해 스레드를 대기시킬 수 있다. 특정 시간을 대기하는 **sleep() 메소드와는 달리 
    지정한 스레드의 작업이 모두 종료될 때까지 대기하기 때문에 동기화 작업 등에서 유용**하게 사용된다.

    ![case4](https://user-images.githubusercontent.com/78818063/182037300-5ed3d3c0-2088-4439-9f35-806e55274bdd.png)

    위 코드는 후처리를 담당하는 postThread에 join() 메소드를 호출하여 실행을 대기시키고, 전처리 작업을 담당하는 preThread에 높은 우선순위를 
    부여해 먼저 작업이 실행되도록 하고 있다. 
    
  * ### 공유 인스턴스 동기화 처리 wait(), notify(), notifyAll() ###
    다음 세 가지 메소드는 싱글톤과 같이 **생성한 인스턴스가 공유**될 때, 스레드들이 작업할 내용을 각각 동기화 메소드로 구분하기 위한 방식에서 사용된다. 
    작업을 마치면 wait() 메소드를 호출해 해당 메소드를 **현재 스레드를 일시정지 시키고**, notify()를 호출시켜 **정지 상태에 있는 다른 스레드를 실행 
    대기 상태로 만든다**. 하나의 스레드를 실행 대기 상태로 만드는 notify()와 다르게 notifyAll()는 자신을 제외한 모든 스레드를 실행 대기 상태로 
    전환시킨다. 주의할 점으로는 공유 객체에서만 사용하는 것이 좋고, `synchronized` 키워드를 정의한 **동기화 메소드 또는 동기화 블록에서만 사용 가능**하다.

    ![case5](https://user-images.githubusercontent.com/78818063/182037309-cd34b215-eebc-4eeb-a5e7-c4d14bc0e5d2.png)

  * ### 스레드 실행 종료 stop(), interrupt() ###
    두 메소드는 실행 중인 메소드를 **강제로 종료시킬 때 사용**한다. ~~stop()~~ 메소드는 호출 즉시 스레드를 종료시키는데, 이때 스레드가 사용하고 있던 자원들이 
    불완전한 상태로 남게되어 현재는 Java 측에서 권장하지 않는 상태(Deprecated)다. 이같은 이유로 스레드를 종료시키기 위해서는 주로 interrupt() 
    메소드를 사용한다. 해당 메소드를 호출하면 **스레드가 일시정지 상태에 있을 때** `InterruptedException` **예외를 발생시켜 사용 중이던 풀, 커넥션, 
    스트림 등 자원의 반환 작업을 처리**할 수 있다. 이때 스레드를 일시정지 상태로 만들기 위해 sleep()과 같은 메소드를 호출시키기도 하지만 보통 아래와 같이 
    **interrupt() 메소드의 호출 여부를 알 수 있는 정적 메소드 Thread.interrupted()를 활용해 실행을 종료시킨다**. 

    ![case6](https://user-images.githubusercontent.com/78818063/182037312-fcdfe66f-eed3-493d-bbc7-374e6ea0fe02.png)

<br>