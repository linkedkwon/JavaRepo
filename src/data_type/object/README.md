# 최상위 클래스 Object
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/DataType-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Object-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 최상위 클래스 Object
  Object 클래스는 Java 내에 있는 모든 클래스의 조상이 되는 `최상위 클래스`로 기본 동작을 수행하는 클래스 집합체인 
  `java.lang 패키지`에서 제공 중이다. 해당 패키지는 자동으로 프로그램에 포함되어 있어 별다른 선언없이 사용 가능한데, 
  Java 내의 **모든 클래스는 기본적으로 Object 클래스를 상속받고 있기 때문에 해당 클래스의 메소드를 직접 호출할 수 있다**. 
 
  <img width="577" alt="object" src="https://user-images.githubusercontent.com/78818063/167459254-44033f15-7c7b-4f6e-b8ed-ded1abb53178.png">

<br>

## 2. 클래스 내부 살펴보기
  Object 클래스는 내부에 따로 필드를 가지지 않고 오직 11개의 메소드만을 가지고 있으며, 하위 클래스는 사용 의도에 따라 
  메소드를 적절히 `오버라이딩(Overriding)`해 사용해야 한다. 자주 사용하는 메소드와 재정의 방식은 다음과 같다.

  * ### Equals() ###
  `Equals()`는 메소드를 호출하는 객체와 파라미터로 받는 객체가 **가리키는 인스턴스가 같은지 비교**하는 메소드로 
  같다면 true 값을, 그렇지 않다면 false 값을 반환한다. 해당 메소드를 재정의한 대표적인 예시로는 String 클래스에서 
  제공하고 있는 문자열 비교 메소드 `equals()`와 `equalsIgnoreCase()`가 있다. 만약 이처럼 서로 다른 객체의 
  특정 필드 값을 비교하는 로직을 만들고 싶은 경우 아래 코드와 같이 개발자가 직접 재정의할 수 있다. 먼저 **예약어 
  instanceOf로 두 객체의 타입을 비교**하고 해당 객체가 **null 상태인지 체크**한다. 그런 다음 타입이 같으면 
  Object 타입의 파라미터를 `형변환(Casting)`해 **필드 값을 비교하여 결과를 리턴**하도록 한다. 
  
  <img width="557" alt="equals" src="https://user-images.githubusercontent.com/78818063/167469327-e2f1113c-5a63-466c-a278-1041e6a3e41f.png">

  * ### toString() ###
  `toString()`는 현재 객체가 가리키는 인스턴스의 정보를 문자열로 반환하는 메소드로 클래스명과 구분자 '@'가 나온 뒤 
  16진수의 해쉬코드로 변환된 인스턴스 주소를 반환한다. 보통 아래 코드처럼 객체 내부의 필드 값을 출력해 인스턴스의 현재 상태를 
  확인 가능하도록 재정의 해 사용하곤 한다. 
  
  <img width="559" alt="toString" src="https://user-images.githubusercontent.com/78818063/167469328-d2373e52-b69e-4075-a2b4-8736753cce1b.png">

  * ### clone() ###
  `clone()`은 객체의 복사본을 생성해 반환하는 메소드로 `Shallow Copy(얕은 복사) 방법`으로 **원본 객체가 가리키는 인스턴스의 
  주소를 가진 새로운 참조 변수**를 생성한다. 이는 복사된 객체가 가리키는 인스턴스가 원본과 같으므로 수정 시 실제 인스턴스에 변경 사항이 
  반영된다. 원본과 같은 인스턴스 자체를 복사하는 `Deep Copy 방법`은 Comparable 인터페이스를 상속받아 clone() 메소드를 재정의하면 
  되는데, **java > data_type > copy** 디렉토리에서 자세한 내용을 다루고 있다. 

  * ### hashCode() ###
  `hashCode()`는 참조형의 경우 변수가 가리키는 실제 인스턴스가 저장된 메모리 주소를, 기본형의 경우 해당 리터럴 값을 **해싱한 
  10진수로 반환**한다. 보통 두 인스턴스가 같은지 판별하기 위해 재정의하는데, 앞서 설명한 equals() 메소드와 함께 자주 사용된다. 
  아래 코드처럼 **객체가 가지는 고유한 Id 값이 있다면 앞서 사용했던 equals() 메소드처럼 모든 필드를 비교하지 않고 두 인스턴가 
  같은지 쉽게 확인**할 수 있다.
  
  <img width="560" alt="hasCode" src="https://user-images.githubusercontent.com/78818063/167469330-a1f72399-ff8a-4510-9425-c9f2341390c6.png">

<br>
