# 자원 반환하기, Try-with-resources
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Exception-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Resource-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 자원 반환
Java에서 `메모리 누수(Memory Leak)`는 개발자의 의도와 다르게 인스턴스의 참조 상태가 지속되어  
GC(Garbage Collection)작업이 제대로 수행되지 않는 것을 말한다. 이러한 **누수 현상은 try-catch  
구문에서도 흔히 발생**하는데, 아래 코드를 살펴보자. 

![closeOnTry](https://user-images.githubusercontent.com/78818063/170882761-8b86e9ee-2b06-4a37-84d9-2fc7fca28c8b.png)

위 코드는 외부의 파일을 읽기 위해 try-catch 문에서 `스트림(Stream)`을 사용하고 있다. 만약 스트림을  
닫기 전 예외가 발생하면 어떻게 될까? 작업을 중단하고 그대로 **catch 구문에서 예외 처리 로직을 수행**하여  
런타임 중 일어날 예외들은 면할 수 있다. 하지만 큰 문제가 발생하는데 **스트림 연결은 끊기지 않고 여전히  
지속되며** GC가 해당 스트림 인스턴스와 내부 데이터들을 여전히 참조 중인 상태라 인지하고, 결국 메모리가  
누수되는 상황이 발생할 것이다. 이렇게 외부 또는 저장소와 연결되는 스트림과 풀(Pool)은 반드시 **사용 후  
close()를 호출해 연결을 끊어줘야 한다**. 따라서 아래와 같이 코드를 변경할 수 있다.  

![closeOnFinally](https://user-images.githubusercontent.com/78818063/170882764-3c5d950f-de5d-44b1-9950-6d3dee933cd7.png)

위는 반드시 실행되는 `finally 블록`에서 close() 메소드를 호출해 스트림을 닫고 있다. 하지만 위 방식은  
다시 예외 처리를 해줘야 하므로 불필요한 코드가 많아진다. 해당 스트림 객체가 **null 상태인지 체크**해야 하며  
각 close() 메소드 호출 시 발생할 수 있는 IOException을 개별적으로 처리해줘야 한다. 즉 자원을 반환해  
메모리 누수 현상을 방지할 수는 있지만 코드의 가독성이 매우 떨어진다.

<br>

## 2. 향상된 예외 처리, try-with-resources
Java 7부터 지원되는 `Try-with-resources`를 에외 처리 중 자원 반환을 아래처럼 쉽게 처리할 수 있다.  

![tryWithResources](https://user-images.githubusercontent.com/78818063/170882768-93a07846-bb22-4582-b09b-cea319893df4.png)

`try() 구문` 내에 **스트림 객체를 선언 및 할당**하였는데, 이는 아래에 **{} 블록안에서 사용이 가능**하다.  
그리고 **try문의 실행이 끝나고 나면 ()블록에 선언된 스트림의 close() 함수가 자동으로 호출**된다.  
따라서 개발자는 별도로 메소드를 호출하지 않아도 된다. 즉 코드를 간결하게 만들어 가독성을 높이고  
유지보수에 용이하며, 실수로 해제를 명시하지 않을 경우에 대비할 수 있다.  

<br>

## 3. 자동 반환은 AutoCloseable 클래스
앞서 살펴본 Try-with-resources 구문에서 **close()를 자동으로 호출하기 위해선** `AutoCloseable`을  
클래스를 구현해야만 한다. Java에서 제공되는 기본적인 스트림 클래스인 I/O Stream은 모두 해당 클래스  
하위에 있는 `closeable` 인터페이스를 상속받고 내부 close() 함수를 재구현하고 있기 떄문에 자동으로  
스트림을 해제할 수 있다. 

![closeable](https://user-images.githubusercontent.com/78818063/170882774-bd808164-4534-4983-b970-216e0c769faf.png)

또한 개발자가 직접 만든 클래스의 인스턴스가 해당 구문이 실행되고 자동으로 할당 해제되길 원한다면  
**AutoCloseable 인터페이스를 상속받으면 가능**하다. 아래처럼 내부에 **close() 함수를 오버라이딩**하여  
로그를 찍거나 별도의 로직을 처리할 수도 있다.  

![customTry](https://user-images.githubusercontent.com/78818063/170882770-c48236ff-11ba-43d9-8591-acec95ccf279.png)

<br>

