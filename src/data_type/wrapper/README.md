# 래퍼 클래스(Wrapper Class)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/DataType-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/WrapperClass-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>


## 1. 래퍼 클래스란?
  Java의 자료형은 크게 두 가지로 `기본형(Primitive Type)`과 `참조형(Reference Type)`으로 구분할 수 있다. 이 중 
  기본 자료형은 리터럴 값을 다루기 위해 사용되는데, 상황에 따라 이러한 **기본 자료형을 객체로 취급해야 하는 경우**가 
  종종 발생한다. 예를 들어 특정 자료구조가 적용된 객체를 지원하는 `Collection Framework`는 **원소를 객체 타입의 
  데이터만 지원**한다. 이처럼 객체 타입을 지원하는 유용한 도구들을 사용하기 위해 Java는 기본 자료형 변수를 객체 타입으로 
  변환하는 기능을 제공한다. **내부에 리터럴 값을 보존**하여 포장하며, 이러한 변환 과정을 Boxing(박싱) 또는 래핑(Wrapping)이라 
  부르고 포장된 객체를 `래퍼 클래스` 또는 `포장 클래스`라 부른다. 아래 항목은 각 기본 자료형에 대응하는 래퍼 클래스를 나타낸다.

  * boolean - **Boolean**
  * byte - **Byte**
  * char - **Character**
  * float - **Float**
  * int - **Integer**
  * long - **Long**
  * short - **Short**
  * double - **Double**

<br>

## 2. 박싱(Boxing)과 언방식(Unboxing) 
아래 그림처럼 기본형 데이터를 래퍼 클래스 객체로 포장하는 과정을 `박싱(Boxing)`이라 부르고 반대로 포장된 값을 
다시 해제해 기본형 데이터를 꺼내는 작업을 `언박싱(Unboxing)`이라 부른다.  

<img width="556" alt="boxingUnboxing" src="https://user-images.githubusercontent.com/78818063/167741716-769cf57f-7453-40ec-9ee1-6ca684442a0f.png">

<br>

## 3. AutoBoxing
기존에는 상반되는 두 가지 타입이 호환되려면 개발자가 타입을 명시적으로 변환해야 했지만 JDK(Java Development Kit) 1.5 버전 
이후부터는 **컴파일러를 통한 자동 변환 기능이 제공된다**. 이처럼 기본형 데이터를 래퍼 클래스로 자동 변환하는 과정을 `Autoboxing`이라 
칭하며, 기본형 데이터가 아래 두 가지 경우에 해당될 때 컴파일러는 변환을 진행한다.

<img width="558" alt="autoboxing" src="https://user-images.githubusercontent.com/78818063/167741718-41eed71e-55b6-4762-9fb0-f8c89e388453.png">

* **기본형 데이터가 래퍼 클래스 타입으로 반환되는 경우**
* **기본형 데이터가 래퍼 클래스의 변수로 즉시 할당되는 경우**

위 메소드는 Autoboxing이 적용된 예시로 다른 타입으로 할당되는 과정을 나타낸다. 위 과정은 **언박싱에서도 마찬가지로 적용된다.**

<br>

## 4. 주의할 점 
앞서 기본형 데이터를 객체 타입으로 변환하는 이유와 과정에 대해 알 수 있었는데, 이때 한 가지 주의할 점을 아래 코드를 통해 알아보자.

<img width="558" alt="wrapperClassIssue" src="https://user-images.githubusercontent.com/78818063/167741723-c82db6cd-1377-4cbe-b5e5-d448050911cd.png">

두 메소드는 각각 원시 타입과 오브젝트 타입의 sum 변수를 만들어 1부터 100까지 합을 구하고 있다. 여기서 주의할 점은 
래퍼 클래스 또한 **인스턴스라는 점**이다. 산술 연산으로 리터럴 값만 바뀌는 원시 타입에 비해 인스턴스 연산은 매 **루프 
합을 계산할 때마다 박싱-언박싱 과정을 거친 연산이 일어나며, 새로운 결과의 인스턴스가 Heap에 할당**된다. 이같은 과정은 
래퍼 클래스의 메모리 사용을 매우 비효율적이게 만들며, 원시 타입과 비교해 성능과 속도 측면에서 매우 상반된 결과를 보여준다. 
결론적으로 래퍼 클래스를 일반적인 연산에 무분별하게 사용하기 보다는 가급적 **원시적 타입을 사용할 수 없는 상황(임피던스, Impedance)에서만 
쓰는 것**이 바람직하단 사실을 알 수 있다.

<br>
