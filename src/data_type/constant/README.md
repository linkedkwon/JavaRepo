# Constant(상수)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/DataType-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Constant-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>


## 1. 상수란?
  상수란 `불변(Immmtable) 상태`인 변수를 의미하며, 주로 숫자나 문자 같은 기본 자료형이나 Heap 영역에 할당된 인스턴스의 주소 등에 사용된다. 
  이는 한 번 값을 선언하면 다시 값을 변경할 수 없는데, 보통 선언할 변수 앞에 예약어 `'final'`을 붙여 바로 값을 `명시적으로 할당`하거나 객체의 
  멤버 변수를 `생성자`를 통해 동적으로 할당하는 두 가지 방법을 사용한다.  

  <img width="563" alt="constant" src="https://user-images.githubusercontent.com/78818063/162140248-4e352f19-3c92-43c4-a035-6814f70160a5.png">
  
  먼저 기본형 변수를 상수로 선언한 방식이다. 회원의 메일은 최대 20자를 넘지 않도록 상수 `Max Length`는 서비스의 `유효성을 체크`한다. 그리고 
  아래는 객체의 멤버 변수를 상수로 선언한 예시다. 회원 가입에 필요한 이메일과 비밀번호를 담은 `DTO(Data Transfer Object)` 객체를 외부로부터 
  전달받았고 보안 문제를 예방해 비밀번호를 암호화하여 DB(Database)에 보관하려 한다. 이때 DTO 객체는 요청(Request)부터 DB Access까지 
  `생명주기`를 가지며, **해당 주기 동안 회원의 이메일 정보는 반드시 불변 상태를 유지해야 한다**. 반면에 비밀번호는 중간에 암호화 과정을 거쳐 
  새로운 문자열로 재할당되어야 하므로 불변 상태로 유지시키지 않는다. 따라서 이메일을 상수로 정의하여 안전하게 불변 상태를 지속시키고, 암호화 과정을 
  거친 비밀번호는 새롭게 외부에서 주입받고 있다. 
  
  <br>

  결론적으로 상수는 자주 `재사용`되거나 `공유`되는 값, 데이터의 특성 상 `불변 상태를 지속`해야 하는 경우, `보안` 이슈와 같이 외부에서 값을 함부로 
  수정하지 않아야 하는 경우 등에 많이 활용된다. 실제 서비스에서 특별한 이유가 없다면 객체는 불변 상태를 유지하는 것이 좋으므로 상수를 적극적으로 활용하는 것이 
  좋으며, 그렇지 않은 경우는 메서드나 참조를 통해 값을 수정하길 권장한다. 


<br>


## 2. 리터럴(Literal)
  리터럴은 앞서 살펴본 상수와 함께 많이 등장하는 용어로 **변수에 할당된 변하지 않는 실제 데이터 값 자체**를 의미한다.

  <img width="572" alt="literal" src="https://user-images.githubusercontent.com/78818063/162140585-84156af3-1f71-484b-81bc-a08bcf24813e.png">
  
  위 코드에서 선언된 변수들을 상수라 정의하고 `실제 할당된 데이터`(20, "/api/v1", "MEMBER")를 `리터럴`이라 표현한다. 
  이는 주로 기본 자료형에서 많이 사용되는데, 객체 타입은 서비스 로직에 따라 인스턴스 내부 값이 동적으로 주입되는 가변 상태를 기본으로 
  하기 때문에 해당 표현이 적합하지 않다. 물론 객체에서도 리터럴이 적용되는 경우가 있다. 앞서 살펴봤던 예제의 이메일처럼 멤버 변수들이 
  객체 주기동안 불변 상태를 유지하게 되면, 이를 `객체 리터럴(Instance Literal)`이라 명시한다. 문자열을 다룰 때 많이 사용하는 
  String class는 객체에 문자열을 할당하면 String Pool에 해당 데이터를 불변 상태로 계속 적재하기 때문에 객체 리터럴의 대표적인 
  예시라 볼 수 있다. 결론적으로 **상수는 변하지 않는 변수나 인스턴스의 참조 주소를 말하고, 리터럴은 상수에 할당된 실제 데이터를 
  뜻한다. 

<br>

## 3. 사용법(How To Use)
  마지막으로 상수를 만들 때 사용하는 컨벤션과 상황에 따라 선언하는 방식 차이에 대해 다룬다. 

* ### 선언 규칙 ###
  상수를 선언할 때는 보통 `static`(다른 문서에서 자세히 다룸) 키워드를 함께 사용한다. static이 선언된 변수는 JVM(Java Virtual Machine) 
  내부의 `Class Loader`가 클래스들을 메모리에 `로딩`하고 `초기화`할 때 `스택 영역`에 할당된다. 그리고 프로그램이 종료되기 전까지 할당 상태를 계속 
  유지하여, 인스턴스를 만들 때 마다 변수를 생성하지 않고 해당 변수를 `공유`한다. 즉 상수와 static을 함께 사용하면 반복적인 메모리 낭비를 줄이고 
  인스턴스들이 해당 상수를 공유할 수 있다.   

* ### 명명 규칙 ###
  자바에서 상수를 선언할 때 사용하는 `네이밍 규칙`으로 변수명을 모두 `대문자`로 표기하고 각 단어 사이를 `언더바`를 사용해 연결한다. 

* ### 초기화 규칙 ###
  final 키워드를 붙이면 **반드시 값을 초기화** 해야 하는데, 보통 변수를 선언함과 동시에 값을 바로 명시한다. 물론 나중에 값을 
  초기화해도 되며 **단 한 번의 할당만 허용**한다. 이 두 가지 경우를 벗어나면 런타임 중 오류가 발생한다. 

  <img width="605" alt="convention" src="https://user-images.githubusercontent.com/78818063/162136578-94753b97-318f-4be2-ac13-ec21addf3fc1.png">

* ### 인스턴스 내부 상수 필드 ###
  앞서 객체 내부의 상수는 `생성자`를 통해 값을 받아 특정 리터럴 값을 할당할 수 있었다. 해당 방법은 같은 클래스에서 만든 객체의 형태가 
  각각 다른 상수 값들을 필요로 한 상황에서 사용되어 지는데, 아래 코드를 살펴보자.  

  <img width="602" alt="objectconstant" src="https://user-images.githubusercontent.com/78818063/162136580-82401029-d34f-432a-8903-6efe6b843f57.png">
  
  두 개의 인스턴스는 각각 목재와 실리콘을 싣는 다른 종류의 화물차를 표현하고 있다. 서로 수용 가능한 최대 중량은 다르지만 화물차를 표현하기 위한 
  객체로서 형태가 같기 때문에 `생성자(Constructor)`를 사용해 최대 중량의 상수 값만 다르게 대입해 사용한다. 즉 생성된 **인스턴스 내부의 상수 
  값이 항상 동일하다면 static 예약어를 선언함과 동시에 리터럴 값을 바로 초기화**하는 것이 좋지만, 반대로 서비스 로직의 목적에 따라 **인스턴스가(상수 포함) 
  다형성을 지녀야 하는 경우는 위 방식처럼 생성자를 통한 대입 방법**을 사용하는 것이 바람직하다.

<br>
