# 스레드 그룹(Thread Group)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Thread-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Group-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 스레드 그룹이란?
`스레드 그룹`이란 **서로 관련된 스레드를 그룹화하여 일시적으로 관리하는 방법**이다. 그룹은 `트리 구조`를 형성하므로 하나의 스레드 
그룹이 다른 그룹을 포함할 수 있으며, 자신의 상위 스레드 그룹으로부터 **우선순위를 상속받는다**. 또한 **스레드는 자신이 속한 그룹이나 
하위 그룹에 접근 가능하지만 다른 그룹의 스레드에는 접근 불가능**하므로 관련없는 스레드에 영향을 주지 않고 관리할 수 있다. 따라서 접근 
범위를 제한하여 보안상으로 중요한 역할을 하고 있다. 기본적으로 모든 스레드는 반드시 하나의 스레드 그룹에 속하는데, JVM(Java Virtual 
Machine)의 운영과 관련한 작업을 처리하는 `시스템 스레드 그룹`이, 다시 하위에 `메인 스레드 그룹`으로 나뉘어진다. 이때 사용자가 
스레드를 생성할 때 **따로 그룹을 지정하지 않으면 기본적으로 자신을 생성한 스레드 그룹과 같은 그룹에 속한다**. 

![threadgroup](https://user-images.githubusercontent.com/78818063/178667576-65ff9f37-724f-4b3d-aa37-aa6ae2ec094f.png)

<br>

## 2. 사용법
다음은 스레드 그룹을 사용하는 방법으로 Java에서 제공하는 `ThreadGroup` 클래스를 통해 스레드들을 그룹화하여 관리 가능하다. 
아래에서 그룹 생성과 지정 등 주요 사용법에 대해 알아보자.

  * ### 스레드 그룹 생성 ###
    **ThreadGroup 객체를 생성해 스레드 그룹을 생성**할 수 있다. `생성자 오버로딩(Overloading)`을 통해 인자에 그룹명(String 
    타입)을 넘겨줬을 경우 현재 스레드가 속한 그룹의 하위에 지정되며, 상위 타입과 그룹명 두 가지를 함께 넘겨주면 해당 그룹의 하위에 속하게 된다. 

    ![case1](https://user-images.githubusercontent.com/78818063/178674002-6a1400af-b328-4cdf-8b09-ce98b498e7cc.png)

  * ### 그룹 지정 ###
    다음은 생성한 스레드의 그룹을 지정하는 방식으로 기본 Thread 클래스의 객체 생성 시 **생성자로 스레드 그룹 객체를 인자를 넘겨주면 된다**. 
    만약 사용자가 **직접 만든 스레드 클래스라면 내부에 아래와 같이 생성자를 추가**해 동일하게 사용 가능하다.

    ![case2](https://user-images.githubusercontent.com/78818063/178674004-b25afede-9727-4223-a2cf-0c0270d09243.png)

* **interrupt()** : 해당 그룹의 모든 스레드의 동작을 멈춘다.
* **setMaxPriority(int priority)** : 해당 그룹의 우선순위를 지정한다.

<br>
