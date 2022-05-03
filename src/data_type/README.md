# Data Type(자료형)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/DataType-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>


## 1. 기본형(Primitivie Type)?
  기본형은 변수에 실제 값을 저장하고 문자나 숫자를 계산하기 위해 사용되는 자료형이다. 변수는 실제 `리터럴` 값을 담으며  
  JVM(Java Virtuaal Machine)의 Stack 영역에 할당된다. 아래와 같이 논리형, 실수형 정수형, 문자형 등을 포함하고 있다.  
  
  * **Boolean Type(boolean : 1 byte)**
  * **Numeric Type**
    * **Integer Type(byte, short : 2byte, int : 4byte, long : 8byte)**
    * **Floating Type(float : 4byte, double : 8byte)**
  * **Character Type(char : 2byte)**

<br>

## 2. 참조형(Reference Type)
  참조형 변수는 기본형을 제외한 배열 또는 클래스 타입으로 생성된 변수를 의미한다. 생성된 실제 인스턴스는 Heap   
  영역에 할당되는데, 선언된 변수는 실제 값이 아닌 인스턴스가 할당된 메모리의 `참조 주소`를 저장하며 종류는 다음과 같다.  
  * **클래스 타입(Class Type)**
  * **인터페이스(Interface)**
  * **불변 객체 String Class**
  * **열거형 상수 Enum**
  * **래퍼 클래스(Wrapper Class)**

<br>

## 3. 변수의 스코프(Scope)와 라이프 타임(Life Time)
  스코프란 **변수에 접근하거나 사용할 수 있는 영역**을 의미하며, 라이프 타임은 **선언된 변수가 메모리에 할당되어 있는 유효  
  기간**을 의미한다. 일반적으로 {} 블록 사이에서 선언된 변수는 해당 블록 영역이 끝나기 전 사용함을 원칙으로 한다.  
  선언 방식에 따라 크게 세 가지로 나눌 수 있는데 아래 코드와 함께 항목들을 살펴보자.    

  <img width="674" alt="scope" src="https://user-images.githubusercontent.com/78818063/166433065-2bf5dcde-2d5b-4036-8720-15662161a896.png">

* ### 전역 변수(클래스 내부 필드 또는 멤버 변수) ###
  전역 변수는 클래스 내부에 위치한 멤버 변수를 의미한다. 리터럴 값 또는 특정 객체의 주소를 할당하고 싶으면 실제 메모리에  
  할당된 데이터의 주소가 매핑되어야 하는데, `static 키워드`를 함께 붙여 `Class Loader`가 미리 Stack 영역에 참조 데이터를  
  가르키도록 해 블록 내부 모든 공간에서 접근 가능하다. 또한 애플리케이션이 끝날 때까지 해당 변수의 라이프 타임이 지속된다.

* ### 지역 변수 ###
  메소드 내부에서 사용하는 변수로 **해당 변수가 포함된 블록 내부**에서만 접근 및 사용 가능하다. 마찬가지로 라이프타임 또한  
  포함된 블록의 명령이 끝날 때까지 지속된다.

* ### 인스턴스 변수 ###
  객체 타입의 인스턴스는 선언 시 Heap 영역에 적재되므로 전역에서 Static 키워드 없이 사용 가능하며, 일반적인 지역 변수의  
  스코프도 함께 갖는다. 만약 **해당 인스턴스를 참조하는 변수가 없다면 GC(Garbage Collection) 대상**이 되어 소멸한다.  
  
<br>

## 4. 변수의 초기화(Initialization)
  위에서 살펴본 변수들은 세 가지 스코프 영역에 따라 변수를 초기화하는 방법과 내부 동작 원리가 달라진다. 인스턴스는  
  `싱글톤`이나 `어댑터 패턴`과 같이 애플리케이션의 서비스 로직에 따라 조금 다른 방식을 지니므로 다른 저장소에서 자세히  
  다룰 예졍이다. 아래는 일반적인 지역 및 전역 변수를 초기화하는 방식을 각각 두 가지로 나눠 섦명하고 있다.  

* ### 명시적 초기화(Explicit Initialization) ###
  변수의 선언과 동시에 값을 초기화하는 방식을 `명시적 초기화`라 부르는데, 앞서 살펴본 변수들의 초기화 방식이 이에 해당하며  
  일반적으로 특정 명령 시점과 스레드 스택에서 초기화하므로 해당 공간 내에서 사용함을 원칙으로 하는 가장 기본적인 초기화 방식이다.  
  
* ### 초기화 블럭(Initialization Block) ###
  초기화 블럭은 다시 두 가지로 나눌 수 있는데, 클래스 내부 멤버 변수들의 복잡한 초기화에 사용하는 `클래스 초기화 블럭`과  
  인스턴스화에 여러 개의 생성자가 있을 때 공통적으로 수행해야 할 코드를 추가하는 `인스턴스 초기화 블럭`이 있다.  

  <img width="512" alt="initialization" src="https://user-images.githubusercontent.com/78818063/166439626-a4f9d7b0-2005-4b12-b7b8-e7edcf89745b.png">
  
  위 그림을 살펴보면 먼저 **명시적 초기화만으로 멤버 변수들의 초기화를 완료하기 어려운 경우** 추가적인 로직을 수행하기 위해 다음과  
  같이 static 클래스 초기화 블럭을 사용해 각 인원이 받아야 할 식품의 값들을 초기화하고 있다. 다음으로 해당 클래스의 인스턴스를  
  생성할 때 **여러 생성자가 공통으로 수행할 로직**을 인스턴스 초기화 블럭을 사용해 재고 소진 여부 파악하는 로직 추가하고 있다.    