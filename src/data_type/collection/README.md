# 컬렉션 프레임워크(Collection Framework)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/DataType-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/CollectionFramework-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>


## 1. 컬렉션 프레임워크(Collection Framework)란?
Java에서 제공하는 `컬렉션 프레임워크`의 컬렉션은 데이터 그룹을, 프레임워크는 표준화된 방식을 뜻하는데, 이는 데이터를 쉽고 효율적으로 
다룰 수 있는 **표준화된 자료 구조와 방법을 담은 클래스(인터페이스와 구현체 클래스)들의 집합**을 의미한다. 해당 클래스는 데이터의 특성에 
따른 주요 자료 구조 및 효율적으로 추가, 검색, 삭제할 수 있는 알고리즘을 제공하고 있다.

![collectionFramework](https://user-images.githubusercontent.com/78818063/168951952-8e084bf3-0685-4d20-81a0-eb7ece654041.png)

위 그림은 프레임워크의 계층 관계를 나타내며, 모두 `java.util` 패키지에서 제공하고 있다. 최상위의 `Collection`과 `Map` 인터페이스에서 
하위 자료 구조의 특성에 따라 `List`와 `Set`, `Map`으로 분류되며, 다시 하위에서 자신의 특성에 맞게 기능이 추가 및 구현된 컬렉션 클래스들이 
포함되어 있다. 모든 클래스의 **원소는 제네릭 타입으로 명시**해 사용해야 한다.

<br>

## 2. 사용하는 이유
  앞서 컬렉션 프레임워크가 무엇인지와 함께 간략한 상속 구조에 대해 알아보았는데, 아래에서 이러한 컬렉션 프레임워크가 가지는 몇 가지 장점에 대해 알아보자.
  
  * ### 높은 편의성과 접근성 ###
    사용자가 직접 구현체를 만들 필요가 없고 구체적인 학습 없이도 쉽게 사용해 볼 수 있다.
  
  * ### 성능 향상 ###
    고성능의 데이터 구조와 알고리즘으로 구현된 API(Application Programming Interface)를 제공하여 성능을 향상시키며 
    상속 관계에 있는 각 구현체들은 **상호 호환이 가능**하므로 필요에 따라 쉽게 다른 자료 구조로 변환이 가능하다.

  * ### 재사용 촉진 ###
    데이터와 알고리즘을 조작할 수 있는 **표준 인터페이스를 제공하여 해당 객체들이 재사용**되기 쉽게 설계하였다.

  * ### 높은 편의성과 접근성 ###
    배열은 크기를 생성과 동시에 명시적으로 지정해줘야 한다. 할당된 메모리 크기가 고정되므로 데이터를 삭제하면 홀(Hole) 공간이 생겨 
    공간이 부족하거나 남는 문제가 종종 발생하는데, **컬렉션 프레임워크는 원소를 동적으로 삽입, 삭제**하여 이러한 단점을 모두 보완하였다.

<br>

## 3. 주요 인터페이스 특징
컬렉션 프레임워크는 가장 최상위를 두 가지 집합체로 구분하는데, 먼저 `Collection` 인터페이스는 다시 하위에 
List, Set Queue(내용 생략)의 자식 요소를 두고 있다. 컬렉션을 다루는 가장 **기본적인 삽입, 삭제, 포함 여부와 
반복자(Iterator) 클래스 생성 등의 템플릿 메소드를 정의**하고 있으며 하위 요소들은 각각 자신의 자료 구조에 적합한 
내부 기능을 재정의하고 있다. 기본 메소드의 종류는 아래와 같다.

<img width="611" alt="collection" src="https://user-images.githubusercontent.com/78818063/168952007-91434bcd-43a1-4c8b-9f75-b24ce01a8a44.png">


다음으로 Map 인터페이스는 컬렉션이 `쌍(Pair)`으로 정의된 자료들을 관리하는 기능을 제공하는 집합체로 
앞서 살펴보았던 Collection 인터페이스와는 구분되는 다른 기능을 제공한다. 내부 데이터는 **Key와 Value 값 
두 가지를 원소로 다루는 템플릿 메소드를 제공**하고 있으며, 주요 메소드는 아래와 같다.

<img width="660" alt="map" src="https://user-images.githubusercontent.com/78818063/168952019-884a623f-c554-424a-b303-704149bb2b04.png">


결론적으로 실제 컬렉션의 특징을 지닌 주요 인터페이스는 `List`, `Set`, `Map`의 세 가지로 구분할 수 있다. 주요 인터페이스의 
간략한 특징은 아래와 같다. 해당 인터페이스를 상속 받는 컬렉션 클래스의 특징과 사용법은 다른 문서에서 자세히 다룬다.

<img width="583" alt="main" src="https://user-images.githubusercontent.com/78818063/168952025-ae85f2b0-3b1b-4991-b295-2403aacc04e5.png">


