# 데몬 스레드(Daemon Tread)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Thread-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Daemon-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 데몬 스레드란?
`데몬`이란 멀티태스킹 환경에서 사용자가 직접 제어하지 않고, **백그라운드에서 돌면서 여러 작업을 수행하는 프로그램을 의미**한다.
이는 스레드에서도 똑같이 적용되어, **메인 스레드의 보조적인 작업을 수행하는 스레드**를 `데몬 스레드`라 부른다. 대표적으로 메모리의 
인스턴스 할당을 해제하는 GC(Garbage Collector)나 화면 자동 갱신과 같은 보조 작업을 처리하기 위해 사용된다. 이는 스레드 중 
가장 우선순위가 낮은데, 일반 스레드의 작업이 끝나면 데몬 스레드의 실행 상태 여부와 상관없이 모든 작업을 종료한다.

<br>

## 2. 사용법
데몬 스레드는 상위 문서에서 살펴본 일반 스레드 사용법과 동일하다. 실행하기 전 생성한 스레드 객체에 `setDaemon(true)` 메소드를 호출해 
해당 스레드를 데몬 스레드로 만들어주면 된다. 일반적으로 아래와 같이 **조건문과 무한 루프를 사용해 데몬 스레드 여부를 판단**한 후, 대기 
상태와 작업 수행을 반복한다. 

![case1](https://user-images.githubusercontent.com/78818063/178424228-f627d6ca-f441-4af9-ae35-2696355f39f0.png)

* **void setDaemon(true or false)** : 해당 스레드 객체를 데몬 상태로 정의한다.
* **boolean isDaemon()** : 해당 스레드가 데몬 스레드인지 아닌지 상태값을 반환한다.

<br>
