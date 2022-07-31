# 무한대 범위 BigInteger
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/DataType-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/BigInteger-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>


## 1. 정수 자료형의 한계
  Java는 byte, short, int, long 등의 정수 자료형을 제공하고 있다. 수를 표현할 때는 주로 int와 long 타입을 많이 사용하며, 
  두 자료형의 메모리 크기는 각각 4byte와 8byte로 표현할 수 있는 범위는 다음과 같다.
 
  * int : -2,147,483,648 ~ 2,147,483,647
  * long : -9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807
  
  일반적으로 실제 서비스 중인 애플리케이션에서 위 범위를 넘어선 숫자를 다룰 일은 거의 없다. 하지만 비용과 시간을 예측하고 
  계산하는 상황에서 위 정수 범위를 초과하는 최악의 상황을 배제할 수 없다. 즉 이렇게 `무한대`에 가까운 수를 표현할 때에 
  대비해 Java에서는 `BigInteger 클래스`를 제공하고 있다.  

<br>

## 2. BigInteger 클래스
  BigInteger 클래스는 `java.math 패키지`에서 제공 중이며, `Number 클래스`를 상속받고 있다. 숫자를 내부에서 문자로 
  변환해 처리하므로 무한대 범위로 수를 표현할 수 있는데, 정수형외에도 소수점을 무한히 다룰 수 있는 실수형 `BigDemical` 
  클래스도 제공하고 있다. 이같이 무한대 숫자를 다룰 수 있다는 장점에 반해 비용과 속도 측면에서는 매우 비효율적이다는 단점을 가진다. 
  인스턴스의 기본 메모리 크기가 70 byte로 기본 자료형에 비해 상당히 큰 비용을 지닌다. 또한 String 클래스와 같은 **불변 
  객체(Immutable Object)로 연산을 통해 값이 바뀌면 Heap 영역에 해당되는 새로운 수의 인스턴스가 할당된다.** 따라서 많은 
  연산이 수행되는 경우 발생되는 오버헤드가 크기 때문에 여러 개의 쓰레드가 돌고 있거나 실시간으로 서비스 중인 애플리케이션에서는 
  가급적 사용을 자제하는 것이 좋다.

<br>

## 3. 사용법
  앞서 BigInteger 클래스는 정수 표현 범위를 넘어선 숫자를 지원하기 위해 내부에서 숫자를 `문자로 처리`하였으며, 
  객체의 초기화, 연산, 비교 등의 방식이 문자열을 다루는 String 클래스와 유사하다. 아래에서 해당 클래스의 몇 가지 
  사용법을 알아보자.  

  * ### 인스턴스 생성과 초기화 방식 ###
  <img width="535" alt="constructor" src="https://user-images.githubusercontent.com/78818063/167311312-ed2a8b5f-bb78-4531-9b81-d0848ffab260.png">

  가장 많이 사용하는 초기화 방법은 문자열을 파라미터로 받아 인스턴스를 생성하는 방식이다. 생성자의 파라미터가 1개인 경우는 
  10진수를, 2개인 경우는 `두 번째 인자에 맞는 진법`으로 변환된다. 이 방식외에도 byte 배열을 넘겨주거나 BigInteger.TEN과 
  같은 고정 상수 값을 대입하는 추가 방식들도 존재한다. 

  * ### 사칙연산 ###
  <img width="534" alt="arth" src="https://user-images.githubusercontent.com/78818063/167311316-c61a45f3-fac6-4b83-bb4e-07a7a4f1d2bb.png">

  기본적인 사칙연산으로 각 함수들은 계산하여 만들어진 새로운 **BigInteger 인스턴스를 반환한다**. 

  * ### 형변환 ###
  <img width="536" alt="casting" src="https://user-images.githubusercontent.com/78818063/167311318-f57907de-5e64-4e99-ab6f-23d0d6186d48.png">

  인스턴스를 문자열이나 byte 배열로 변환이 가능하며, Number 클래스를 상속받아 기본 자료형으로도 변환 가능하다. 

  * ### 인스턴스 비교 ###
  <img width="534" alt="compareTo" src="https://user-images.githubusercontent.com/78818063/167311320-3c5313d9-c0da-4f33-be5d-26ab24a8455c.png">

  클래스 내부에는 Comparable 인터페이스에서 제공하는 `compareTo` 함수를 내장하고 있어 값을 쉽게 비교할 수 있다. 

<br>

## 4. 불변 객체(Immutable Object)와 연산
  <img width="534" alt="loop" src="https://user-images.githubusercontent.com/78818063/167311516-9bdc2722-b3cf-4b47-9843-ac86ff5a82d2.png">

  BigInteger 클래스는 String과 같은 `불변 객체`로 연산의 결과 값이 기존 값과 다른 경우 새로운 인스턴스를 할당한다는 
  사실을 알 수 있었다. 즉 불변 객체의 특성때문에 연산이 일어나면 결과 **값을 담은 새로운 인스턴스를 참조 변수로 받아야만 한다**. 
  따라서 위 코드처럼 **새로운 계산 값을 담은 인스턴스를 지속적으로 참조 유지해줘야 한다**. 

<br>
