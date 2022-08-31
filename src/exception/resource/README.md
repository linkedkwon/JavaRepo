# Try-with-resources
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Exception-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Resource-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 자원 반환
Java에서 `메모리 누수(Memory Leak)`는 개발자의 의도와 다르게 더 이상 사용하지 않는 인스턴스의 참조 상태가 지속되어 
GC(Garbage Collection) 작업이 제대로 수행되지 않는 현상을 말한다. 이러한 **메모리 누수는 예외를 처리하는 try-catch 
구문에서도 종종 발생**하는데, 먼저 아래 코드를 살펴보자. 

![closeOnTry](https://user-images.githubusercontent.com/78818063/170882761-8b86e9ee-2b06-4a37-84d9-2fc7fca28c8b.png)

위 코드는 외부 파일을 읽기 위해 데이터의 입출력을 돕는 `스트림(Stream)`을 사용하고 있다. 스트림을 try-catch 문에서 
작성함으로서 입출력 중 예외가 발생했을 때 적절한 처리 로직을 통해 런타임 중 로그를 남기거나 여러 후처리를 둘 수 있다. 
하지만 이같은 경우 발생하는 한 가지 문제점은 **스트림 연결은 끊기지 않고 여전히 지속된다는 것이다.** 이는 GC가 해당 스트림 인스턴스와 
내부 데이터들을 여전히 참조 가능한 상태라 인지하여 메모리 누수 현상이 발생할 수 있다. 따라서 외부 또는 저장소와 연결되는 스트림과 
풀(Pool)은 반드시 **사용 후 close()를 호출해 연결을 끊어줘야 한다**. 따라서 위 코드를 아래와 같이 변경해야 한다. 

![closeOnFinally](https://user-images.githubusercontent.com/78818063/170882764-3c5d950f-de5d-44b1-9950-6d3dee933cd7.png)

위 코드는 마지막에 반드시 실행되는 `finally 블록`에서 close() 메소드를 호출해 스트림 연결을 닫고 있다. 이제 앞서 살펴본 
예제의 메모리 누수 현상을 완벽히 예방할 수 있게 되었다. 하지만 해당 방식은 finally 구문에서 다루는 스트림에 대해 다시 예외 
처리를 해줘야 하므로 불필요한 코드가 많아지고, 해당 스트림 객체가 **null 상태인지 항상 체크**해야 한다. 즉 자원을 안정적으로 
반환할 수는 있었지만 코드의 가독성이 매우 떨어진다는 단점 또한 발생한다.

<br>

## 2. 향상된 예외 처리, try-with-resources
Java 7부터 지원되는 `Try-with-resources`를 사용하면 예외 처리 중 자원 반환을 보다 쉽게 처리할 수 있다. 

![tryWithResources](https://user-images.githubusercontent.com/78818063/170882768-93a07846-bb22-4582-b09b-cea319893df4.png)

`try() 구문` 내에 사용할 **스트림 객체를 선언과 할당**하면 해당 객체는 아래 **{} 블록 내에서도 사용이 가능**한데, 
이때 **try문의 실행이 끝나고 나면 ()블록에 선언된 스트림의 close() 메소드가 자동으로 호출**된다. 즉 개발자는 별도로 메소드를 
호출하지 않아도 되기 때문에 코드를 간결하게 만들어 가독성을 높이고, 실수로 해제를 명시하지 않을 경우에 대비할 수 있다.  

<br>

## 3. 자동 반환은 AutoCloseable 클래스
앞서 살펴본 Try-with-resources 구문에서 **close() 메소드를 자동으로 호출하기 위해서는** `AutoCloseable` 
클래스를 구현해야 한다. Java에서 제공되는 기본적인 스트림 클래스인 I/O Stream은 모두 해당 클래스의 하위에 있는 
`closeable` 인터페이스를 상속받아 내부에 close() 함수를 재구현하고 있기 떄문에 자동으로 스트림을 해제할 수 있다. 

![closeable](https://user-images.githubusercontent.com/78818063/170882774-bd808164-4534-4983-b970-216e0c769faf.png)

또한 개발자가 직접 만든 클래스의 인스턴스가 구문이 실행되고 자동으로 할당 해제되길 원한다면 **AutoCloseable 
인터페이스를 상속받아 처리할 수 있다**. 아래처럼 내부에 **close() 함수를 오버라이딩**하여 내부에 실행할 로직을 
작성하면 된다.

![customTry](https://user-images.githubusercontent.com/78818063/170882770-c48236ff-11ba-43d9-8591-acec95ccf279.png)

<br>
