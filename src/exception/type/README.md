# 예외의 종류, Checked와 Unchecked Exception
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Exception-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Type-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 예외(Exception)의 세 가지 종류
`예외`란 프로그램 실행 중 개발자가 **예상하지 못하게 입력 값 처리가 불가능하거나 정상적인 실행 흐름을 벗어나는 상황**을 의미한다. 
Java에서는 이러한 예외를 해당 내용과 수준에 따라 다시 몇 가지 타입으로 분류한다. 아래는 예외와 관련된 클래스 계층 구조를 나타내고 있다. 

<img width="472" alt="hierarchy" src="https://user-images.githubusercontent.com/78818063/171544209-f1cb5a22-6732-4bae-94f7-dd17f0dbed0e.png">

위 그림을 살펴보면 `Throwable` 클래스를 기준으로 **Error와 Excpetion 클래스로 나눠지고, 다시 하위에 Runtime Exception 클래스**를 두고 있다. 
이처럼 Java는 시스템 수준의 문제를 다루는 `에러(Error)`, 복구 로직을 작성해야 하는 `체크 예외(Checked Exception)`, 런타임 중 일어날 수 있어 
복구를 강제하지 않는 `언체크 예외(Unchecked Exception)`로 크게 세 가지로 예외를 분류하였다. 

<br>

## 2. Error와 Exception의 차이
에러와 예외의 **복구 기능을 기준으로 차이**를 둘 수 있다. **시스템에 수준에서 오류가 발생해 프로그램을 정상적으로 복구할 수 없는 경우를 에러**라 칭하며, 주로 더 이상 
메모리 할당이 불가능한 OOM(Out Of Memory)와 스택오버플로우(StackOverflowError)가 이에 해당된다. 이러한 에러는 예측이 불가능하고 내부적으로 처리할 
방법이 없어 반드시 피해야 한다.

반면에 **예외는 개발자의 실수로 예기치 못한 상황이 발생하는 것으로 프로그램에서 심각한 오류는 아니므로 복구할 수 있다 판단하여 내부에서 예외 처리가 가능**하다. 
주로 배열의 범위가 벗어난 IndexOutOfBoundsException과 Null 상태를 참조하는 NPE(NullPointerException) 등이 이에 해당된다. 이러한 예외는 
개발자가 미리 예외를 예측하여 핸들링할 수 있다.

<br>

## 3. Exception의 두 가지 종류(Checked, Unchecked)
앞서 살펴본 예외는 **다시 체크 예외와 언체크 예외**로 나눌 수 있다. `RuntimeException` 클래스를 **상속하는 클래스들이 언체크 에외이며, 그렇지 않은 
클래스들을 체크 예외로 분류**한다. 

  * ### 체크 예외(Checked Exception) ###
    체크 예외는 RuntimeException 클래스를 상속하지 않는 클래스들에 해당된다. 컴파일 타임에 발생 가능한 **예외가 충분히 예측 가능하므로 사용시 반드시 
    예외 처리를 해줘야 한다**.

  * ### 언체크 예외(Unchecked Exception) ###
    언체크 예외는 반대로 RuntimeException 클래스를 상속한 클래스들에 해당된다. 의미 그대로 **실행 중(Runtime)에 발생할 수 있는 예외들이라 쉽게 예측 
    불가능하기 때문에 예외 처리를 강제하지 않는다**. 또한 대부분 로직에서 해당 예외들이 발생할 가능성이 있으므로 예외 처리를 강제하면 코드가 번잡해지고 가독성이 
    낮아져 반드시 필요로 하지 않는다.

<br>
아래 표는 체크 예외와 언체크 예외가 가지는 차이점을 정리하여 나타내고 있다.

<img width="513" alt="board" src="https://user-images.githubusercontent.com/78818063/171544215-9da44ef8-1bd7-42d8-8b12-2a687a5753c8.png">

<br>

## 4. 예외 전환 사용법
체크 예외가 예상되는 경우 항상 복구 로직을 작성하기 보다는 이를 **언체크 예외로 포장하여 호출자에게 예외를 던져 회피**할 수 있다. 이러한 방식을 `예외 전환`이라 부르는데, 
발생 예외를 언체크 예외로(또는 런타임 예외) 바꾸어 Caller에서 별도의 예외 처리를 명시하지 않아도 되도록 한다. 즉 **예외 처리 작업을 줄여 불필요한 작업을 생략**하는 것이다. 
해당 내용은 예외 처리 방식에서 자세히 다룬다.

<br>
