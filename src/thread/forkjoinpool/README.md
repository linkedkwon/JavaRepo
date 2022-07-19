# ForkJoinPool
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Thread-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/ForkJoinPool-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 스레드 풀(Thread Pool)의 문제점
기존 멀티 스레드 환경에서 개발자가 직접 스레드를 생성하고 관리하게 되면 **스레드의 할당이나 해제와 같은 작업에 많은 비용이 소모**될 수 있다. 
Java에서는 이같은 낭비를 막기 위해 `스레드를 풀(Pool)`에 사용할 **스레드를 미리 생성**해두고 처리할 작업을 큐에 담아 각 스레드에 요청하는 
방식을 취하고 있다.

![pool](https://user-images.githubusercontent.com/78818063/179652012-f181d892-6ae0-4b38-a5f6-56367aeb4c02.png)

개발자는 스레드 풀을 사용하면 작업의 편의성을 제공받을 수 있으며, 앞서 말한 비용 측면에서 나타나는 낭비를 막을 수 있다. 하지만 스레드 풀을 
사용하며 실제 작업량에 **필요한 스레드 보다 더 많은 스레드를 초기에 생성해두어 매모리가 낭비**될 우려가 있고, 먼저 **작업이 끝난 일부 스레드가 
놀고 있는 상태로 유휴(idle) 시간이 발생**한다는 단점이 지닌다.

<br>

## 2. ForkJoinPool
앞서 살펴본 스레드 풀의 단점을 극복하기 위해 Java 7부터는 `ForkJoinPool`을 제공한다. 해당 풀은 작업 요청이 들어오면 **큰 작업을 작은 
작업 단위로 나누고(Fork), 그것을 다른 쓰레드에서 각각 병렬 처리한 뒤 결과를 취합(Join)하는 분할-정복(Divide and Conquer) 방식**을 
사용한다.

![forkjoin](https://user-images.githubusercontent.com/78818063/179842303-afe9cd17-746a-4131-a6fc-4cd4b2a743dc.png)

<br>

여기서 한 가지 특이한 점은 ForkJoinPool은 풀에 생성된 스레드들이 개별적으로 `내부 작업 덱(Dequeue)`을 가지고 있단 점으로, 이를 통해 
**다른 작업을 훔쳐오는** `Work-Stealing 알고리즘`이 적용 가능하다.

![forkjoinpool](https://user-images.githubusercontent.com/78818063/179842307-cb932e39-6d56-4de7-85bb-e5e8bd7bfb33.png)

위 그림처럼 작업 큐에 처리할 작업들이 적재되면 풀에 존재하는 스레드들은 **해당 작업을 들고와 자신의 작업 덱에 적재**시킨다. 그리고 처리가 
끝나고 나면 **다른 스레드들의 덱 또는 다시 작업 큐에 남은 업무를 훔쳐와 수행**한다. 이때 스레드 내부는 덱 구조로 남은 작업을 관리하고 있기 
때문에 현재 진행되지 않은 작업을 **맨 뒤에서부타 가져올 수 있고**, 이는 **즉 유휴 상태에 있는 스레드들이 분리된 작업을 훔쳐와 일을 수행**할 
수 있음을 의미한다.

<br>

## 3. 사용법
ForkJoinPool을 사용하는 방법은 매우 간단한데, **풀과 작업을 정의하고 실행 메소드를 통해 사용 가능**하다. 자세한 과정을 아래에서 알아보자.

  * ### ForkJoinPool 생성 ###
    가장 먼저 풀을 만들기 위해 ForkJoinPool 객체를 생성한다. 이때 **인자로 풀에 생성할 스레드 개수를 넘겨준다**.

    ![case1](https://user-images.githubusercontent.com/78818063/179847085-fccd2dbf-5bb9-45a6-b175-6336274d1a9e.png)

  * ### 작업 객체 생성 : RecursiveAction/RecursiveTask ###
    `RecursiveAction`과 `RecursiveTask`는 작업 구현을 대표하는 두 가지 클래스로, **작업 처리 후 리턴 값 반환 여부**로 구분된다. 작업이 
    실행되면 `compute()` 메소드가 호출되므로 원하는 **로직을 해당 메소드 내부에 작성**하면 되고, 만약 **작업을 분할하고 싶으면 아래와 같이 특정 
    자료구조에 서브 테스크를 담아 `fork()` 메소드를 호출**할 수 있다.

    ![case2](https://user-images.githubusercontent.com/78818063/179847081-95d0822b-2115-4e82-97b0-de15cff41dae.png)

  * ### 작업 처리 요청 ###
    마지막은 만들어두었던 작업 객체를 풀에 전달해 처리 요청을 부탁하면 된다. 아래와 같이 `invoke()` **메소드의 인자로 작업 객체를 넘겨주면 된다**.

    ![case3](https://user-images.githubusercontent.com/78818063/179847088-c1a29582-858f-4d61-a9c6-f9c3a203ec9a.png)

<br>