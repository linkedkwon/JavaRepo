# final vs finally vs finalize
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Keyword-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Final-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Finally-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Finalize-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. final
<img width="635" alt="final" src="https://user-images.githubusercontent.com/78818063/168737263-4c458006-18ea-4e0a-8c5f-79f635f5ed72.png">

`final`은 키워드의 일종으로 변수와 오브젝트, 클래스, 메소드 등 구성 요소를 정의할 때 함께 사용하며  
시간이 지나도 변수에 처음으로 정의된 상태가 변하지 않는 **불변(Immutable)함을 보장**한다. 먼저 final이  
정의된 변수 중 **원시 타입은 싱수로서 사용되고, 참조 타입은 한 번 참조한 인스턴스 외에 다른 인스턴스의 참조가  
불가능**하다. 보통 여러 인스턴스가 값을 공유하는 경우와 웹 서비스의 레이어 4 계층과 같이 인스턴스와 애플리케이션의  
생명주기와 같고 전달하는 매개 변수의 상태만 변하면 되는 상황에서 자주 불변 상태를 자주 사용한다. 그리고 **클래스나  
메소드에서도 정의가 가능한데 각각 상속과 오버라이딩이 제한**된다. 이는 상수 필드의 비중이 크거나 정적 메소드를  
제공하는 클래스로, 인스턴스 내부 데이터나 로직의 수정을 피하고 싶은 경우 많이 사용되어 진다.

<br>

## 2. finally
<img width="645" alt="finally" src="https://user-images.githubusercontent.com/78818063/168737273-d9c9631e-07c6-4f7d-8145-edded605c566.png">

`finally`는 **try-catch 블록의 가장 마지막에 둘 수 있는 선택 블록을 의미**한다. try-catch문이 끝나기전에  
항상 실행되어야하는 로직이 있을 경우 또는 DB나 I/O 같은 스트림을 닫아주는 작업 등을 해당 절에서 처리한다.  
보통 예외가 던져지더라도 항상 실행될 코드를 지정하기 위해 사용하는데, 위 코드처럼 finally 블록에서 오류가  
일어나 입력 값을 받지 못했으면 해당 상황을 나타내는 리터럴 값을 변수에 할당하고 있다. 또한 try-catch문  
중에 **return 문이 실행되더라도  try 블록이 종료되었다 판단하여 로직을 수행**하는데, 만약 프로그램이 종료되거나  
로직을 수행하고 있던 쓰레드가 죽어버리면 해당 구문은 실행되지 않는다.  

<br>

## 3. finalize
<img width="638" alt="finalize" src="https://user-images.githubusercontent.com/78818063/168737280-2862edc7-b281-4593-9009-3cb841d80833.png">

`finalize()`는 최상위 클래스 `Object`에 속해 있는 메소드로, **GC(Garbage Collector)가 더 이상 참조되지  
않는 인스턴스를 발견했을 때 할당을 해제하기 위해 사용되는 메소드**이다. Java 내의 모든 클래스는 Object 클래스를  
상속받고 있으므로 별도의 제약없이 **내부 메소드를 호출**할 수 있는데, **인스턴스의 할당이 해제해야 한다고 판단되면    
finalize() 메소드를 호출**한다. 만약 객체가 삭제되기 직전에 실행되어야 하는 로직이 있다면 Object 클래스에 정의된  
finalize() 메서드를 오버라이딩하여 재정의 할 수 있다. 하지만 객체 소멸 상태가 언제인지 정확히 알 수 없기 때문에   
해당 방식은 권장되지 않는다.


<br>
