# 예외 처리(Exception Handling)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Exception-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 오류(Error)의 발생 시점에 따른 분류
`오류`란 프로그램 실행 중 특정 원인으로 **시스템 수준에서 문제가 발생해 프로그램을 정상적으로 복구할 수 없는 상황**을 뜻한다. 오류는 아래와 같이 **발생 시점**에 
따라 크게 세 가지로 나눌 수 있다.

<img width="628" alt="error" src="https://user-images.githubusercontent.com/78818063/172034601-d113ed3f-ef1a-4365-98a2-6369c06160d1.png">

  * ### 컴파일 에러(Compile Error) ###
    `컴파일 에러`는 **컴파일러가 소스를 컴파일할 때 발생하는 오류**로 보통 아래 코드처럼 **오타, 문법, 타입 체크 등에서 문제**가 생긴 경우를 의미한다. 
    해당 오류는 문제를 찾아 수정하고 재컴파일하여 해결해야 한다.

    <img width="700" alt="compileError" src="https://user-images.githubusercontent.com/78818063/172034603-3f148d2c-876b-477a-a092-19a3abbb2933.png">

  * ### 런타임 에러(Runtime Error) ###
    `런타임 에러`는 프로그램이 실행되는 중에 발생하는 오류로 **컴파일 시점에 예측할 수 없는 잠재적인 문제들에 해당**된다. 아래 코드와 같이 특정 변수에 
    다른 타입의 데이터가 입력되거나 배열의 저장 공간을 사용할 수 없는 등 **실행 도중 의도치 않은 동작**들에 의해 발생한다.
    
    <img width="697" alt="runtimeError" src="https://user-images.githubusercontent.com/78818063/172034605-df5a97b6-194c-410f-9660-f1fcf53e4b03.png">

  * ### 로지컬 에러(Logical Error) ###
    `로지컬 에러`는 앞서 설명한 컴파일과 런타임 시점에 특정 오류가 발생하는 것이 아닌 **로직 자체가 개발자의 의도와 정반대로 수행되는 경우**를 의미한다. 
    이는 프로그램이 비정상적으로 종료되는 것이 아닌 연산의 기대값이나 이벤트 호출 등이 예상과 다른 결과가 나타날 때를 의미한다. 이러한 문제는 보통 
    디버깅 작업으로 로직을 `트래킹(Tracking)`하여 문제를 찾아 로직을 다시 작성해야 한다. 

<br>

## 2. 런타임 에러의 종류
런타임 시점에 발생하는 에러는 **복구 가능 여부를 기준**으로 다시 `에러(Error)`와 `예외(Exception)` 두 가지로 분류한다. 아래에서 두 용어를 자세히 앏아보자.

  * ### 에러 ###
    `에러`는 **시스템 수준에서 뮨재가 발생해 프로그램을 정상적으로 복구할 수 없는 상황**을 의미한다. 주로 더 이상 메모리 할당이 불가능한 `OOM(OutOfMemory)`과 
    `스택오버플로우(StackOverFlow)` 같은 문제들에 해당된다. 이러한 에러는 보통 서버나 실행 환경과 같은 외부에서 발생할 확률이 높으며, **내부적으로 예측이 불가능하고 
    처리할 방법이 없어 사전에 대비**하는 것이 바람직하다.

  * ### 예외 ###
    반면에 `예외`는 개발자의 실수로 **실행 중 예기치 못한 문제가 발생하는 상황**을 의미한다. 프로그램에서 심각한 오류는 아니므로 **복구할 수 있다 판단하여 내부에서 예외 처리가 
    가능**하다. 주로 배열의 범위를 초과하여 참조하는 `IndexOutOfBoundsException`이나 아직 인스턴스화가 이루어지지 않은 참조 변수를 참조하는 `NullPointerException`과 
    같이 실행 중 충분히 발생할 수 있는 상황들에 해당된다. 이러한 **예외는 작성한 로직에서 어떤 에외가 발생할지 미리 예측하여 핸들링하는 코드를 함께 작성**하는 것이 바람직하다. 

    
## 3. 클래스 계층
앞서 살펴본 예외를 처리하기 위해 Java에서는 프로그램 실행 중 **발생할 수 있는 예외로 구성된 표준화**된 API(Application Programming Interface)를 제공하고 있다.

<img width="472" alt="hierarchy" src="https://user-images.githubusercontent.com/78818063/171544209-f1cb5a22-6732-4bae-94f7-dd17f0dbed0e.png">

위 그림은 예외와 관련된 클래스 계층 구조를 나타내는데, 먼저 Java의 최상위 클래스인 `Object`와 에러를 다루는 최상위 클래스 `Throwable`을 상속받고 있다. 
그리고 다시 시스템 수준의 복구가 불가능한 에러를 다루는 `Erorr` 클래스, 예외 상황을 다루는 `Exception` 클래스로 나눠진다. Exception 클래스 하위에는 
`RuntimeException`이란 클래스를 두고 있다. 이는 런타임 중 일어날 수 있는 예외들로 복구를 강제하지 않는 `언체크 예외(Unchecked Exception)`인데, 
반대로 나머지 포함된 클래스들은 복구 로직을 반드시 작성해야 하는 `체크 예외(Checked Exception)`라 부른다.

<br>

## 4. 사용법
Java는 이러한 예외를 **실행 중 내부에서 처리할 수 있도록** 하는 여러 방법들을 제공하고 있다. 아래에서 기본적인 예외 처리 방식에 대해 알아보고 다른 항목에서 타입과 방식 
등의 더욱 자세한 내용을 다루도록 하자.

  * ### try-catch 블록 ###
    `try-catch` 구문은 예외를 처리하기 위해 제공되는 블록으로 먼저 **예외가 발생할 것으로 예상되는 로직을 try{} 구문에 작성**한다. 그리고 예외가 발생한 경우 **해당 예외와 일치하는 
    클래스를 담은 catch 문으로 가서 처리 로직을 실행**하고 모든 블록을 빠져나온다. 만약 예외가 발생했는데 일치하는 catch 문이 없다면 별도의 처리없이 바로 모든 블록을 빠져나온다. 

    <img width="694" alt="tryCatch" src="https://user-images.githubusercontent.com/78818063/172034607-f7407cf1-bcd4-4dc2-b790-c6cc69c4cba2.png">
    
  * ### Multi-catch 블록 ###
    예외를 처리하는 catch 문은 다음과 같이 **여러 개를 정의할 수 있다**. 예외가 발생하면 해당 예외와 일치하는 catch 문으로 이동하며, **한 번에 여러 개의 예외 클래스를 동시에 
    담을 수도 있다**. 이때 주의할 점은 Java에서 제공되는 **예외 클래스는 모두 계층적으로 상속 관게를 형성하고 있으므로 정확한 에외 처리를 위해 발생한 예외에 적절한 자식 클래스들로 
    처리 로직을 구현**해야 한다. 만약 발생할 예외를 정확히 예측하기 힘들다면, 아래 코드처럼 **가장 하단에 상위 클래스를 담은 catch 구문을 작성**하는 것이 바람직하다. 

    <img width="695" alt="multiCatch" src="https://user-images.githubusercontent.com/78818063/172034609-380177b6-9f18-4bff-ba0c-545251437067.png">
    
  * ### Finally 블록 ###
    `finally` 블록은 **예외 발생 여부와 상관없이 무조건 수행되어야 할 로직이 있을 경우 사용하는 블록**이다. 보통 try 문에서 사용한 버퍼나 소켓과 같은 `스트림(Stream)`을 
    **닫기 위한 용도로 사용**되어 진다.

    <img width="698" alt="finally" src="https://user-images.githubusercontent.com/78818063/172034610-c0aed0db-b77b-49ae-87b4-dac808436753.png">

  * ### printStackTrace(), getMessage() ###
    예외가 발생했을 때 두 함수를 사용하면 발생한 **예외 정보를 보다 구체적으로 확인**할 수 있다. `printStackTrace()`는 `호출 스택(Call Stack)`에 있었던 **메소드 정보와 
    예외 메세지를 추적해 화면에 출력**하도록 하며, `getMessage()`는 **발생한 예외 클래스의 인스턴스에 저장된 메세지를 얻을 수 있다**. 보통 **플러그인이나 API를 사용해 특정 기능을 
    구현하면 예외 발생 시 구체적인 메세지를 해당 인스턴스에 담아 개발자가 확인할 수 있도록 한다**. 대표적으로 데이터베이스 작업을 수행하는 `JDBC(Java Database Connectivity)` 
    API는 특정 작업을 수행하다 오류가 발생하면, 해당 에러 코드를 인스턴스에 담아 어떤 문제가 발생했는지 상세히 기재한다. 

    <img width="693" alt="method" src="https://user-images.githubusercontent.com/78818063/172034613-7662a905-2c2c-4444-8b09-d1b87460390e.png">

<br>
