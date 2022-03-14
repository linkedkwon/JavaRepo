## Java의 반복문(Loop)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Loop-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/for-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Iterator-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/foreach-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>


### 1. 반복문이란?
특정 연산 또는 작업을 반복적으로 수행하도록 제어하는 명령문을 의미한다.  
주로 `연속적인 데이터 집합`의 `인덱스`를 사용하여 `탐색`, `수정`, `삭제` 등의 처리를 위해 사용된다.


### 2. 외부 반복자와 내부 반복자
<img width="656" alt="alliterator" src="https://user-images.githubusercontent.com/78818063/158158339-dfa28fa3-c146-4079-9273-fb60d0f87296.png">  

* **(1). 외부 반복자**  
일반적인 for, while, Iterator와 같이 외부에서 데이터 요소를 가져와 처리하는 방식으로  
보통 코드가 길어지면 가독성을 높이기 위해 `메서드 추출(Method Extract)`를 활용한다.
  

* **(2). 내부 반복자**  
Stream API와 같이 Collection 내부에서 요소들을 반복하며, 각 반복 당 수행할 `액션`만을 제공하는 방식이다.  
주로 `함수형 프로그래밍`인 `람다식`을 사용하여 표현력과 가독성이 높으며, 요소들의 반복 순서나 작업의 `병렬처리`가 가능하다.  
또한 요소들을 순회하며 중간 처리와 최종 처리를 할 수 있는데, 예를 들어 필터링과 정렬, 평균과 총합 계산 등의 연산이 가능하다.

두 가지 방법은 실제 애플리케이션의 속도와 성능 또는 가독성을 고려하여 적절한 방법을 선택하는 것이 좋다.   


### 3. 기본 for문
<img width="567" alt="for" src="https://user-images.githubusercontent.com/78818063/158167707-7fd3e61c-2e1c-4509-bc8f-c3303fb68f21.png">

앞서 설명한 외부 반복자 중 하나로 탐색할 데이터의 양이 정해진 경우 사용한다.  
인덱스를 통해 각 요소에 접근하므로 `Array와` `ArrayList`에서 다른 자료형에 비해 빠른 접근 속도를 보인다.  
또한 탐색 중 특정 조건이나 이벤트를 두어 탐색을 멈출 수 있다.


### 4. Iterator 반복자
<img width="567" alt="iterator" src="https://user-images.githubusercontent.com/78818063/158167717-940f958b-e18b-41c2-93a5-4ecd879424ff.png">

컬렉션 프레임워크에서 제공하는 모든 자료 객체와 인터페이스를 순회하기 위해 표준화한 반복자 인터페이스다.  
순차적으로 각 요소에 `반복자 객체`를 생성하여 탐색하므로 Set과 같이 index가 존재하지 않는 자료형도 탐색 가능하다.

<img width="567" alt="interface" src="https://user-images.githubusercontent.com/78818063/158168651-78a9a52e-e7dd-4601-a35f-393ac857d56c.png">

주요 함수로는 hasNext(), next(), `remove()` 등이 있어 각 요소에 `이동` 및 `삭제`가 가능하다.

<img width="698" alt="linkedlist" src="https://user-images.githubusercontent.com/78818063/158166967-24ebc6d1-34fd-4297-9ce0-774b3596bde2.png">

`연결리스트(Linked List)`에서 사용하면 효율이 좋은데, 연결리스트의 각 노드는 다음 노드의 주소를 가지고 있다.   
따라서 기본 반복문을 사용하면 10번째 요소를 탐색하기 위해 list.get(10)이 바로 해당 요소를 가르키지 않고  
첫 번째 원소부터 10번째 원소로 연결된 `모든 주소를 탐색`해야 한다. 따라서 `O(1)`이 아닌 `O(10)`의 시간 복잡도가 소요된다.  
즉 모든 요소를 탐색하기 위해선 `O(N * (N + 1) / 2)`의 시간이 소요된다.  

반면에 Iterator는 각 요소에 대한 객체를 생성하여 접근하므로 각 접근은 `O(1)`의 시간을 가져 더욱 효율적으로 탐색이 가능하다.  
주의할 점으로는 리스트의 **각 요소의 자료 크기가 아주 크다면 반복자를 매번 만드는데 생기는 비용과 탐색 시간을 두고 적절한 방법을 잘 선택**해야 한다.
  
      연결리스트 탐색 효율 문제
      1406번 에디터 - https://www.acmicpc.net/problem/1406


### 5. foreach(향상된 for문)
<img width="567" alt="foreach" src="https://user-images.githubusercontent.com/78818063/158167728-35c1c348-a776-4365-b782-2a55a7c5de14.png">  

일반 배열과 컬렉션 등 모든 객체에서 사용 가능하며, 각 요소에 직접 매핑된 반복자를 만들어 탐색한다.  
반복문의 내부적으로 컴파일러가 Iterator를 이용하여 해당 요소를 매핑한다.   
기본적으로 집합의 모든 요소를 탐색하는데 순회 중에 해당 요소를 삭제할 수 없다.(삭제 시 `currentModificationException`이 발생한다.)  
또한 반복자와 index 변수를 사용하지 않아 코드의 `가독성`이 높고, 변수를 잘못 사용해 생길 오류를 예방할 수 있다.


### 6. 성능 비교 for vs Iterator vs foreach
**(1). 기본 for문**
- `index를 사용하는 자료형`에서만 사용 가능하며 Set과 같은 자료형에서는 사용할 수 없다.
- 반복자를 만들지 않아 `Array`와 `ArrayList` 같이 index로 접근하는 자료형에서 속도가 뛰어나다.

**(2). Iterator(반복자)**
- 컬렉션 프레임워크 제공 자료에서만 사용 가능하다.
- 각 노드의 데이터가 크지 않은 경우 `연결리스트(Linked list)`에서 사용 효율이 좋다.
- 순회 중 요소를 `삭제(Filtering)`할 필요가 있을 경우 사용한다.

**(3). foreach(향상된 for문)**
- `일반 배열`과 `컬렉션` 모두 사용 가능하다.
- `가독성`이 좋아 순회 중 별다른 제약이 없으면 사용하도록 한다.

각 방법의 성능 차는 일반적으로 미세하지만 데이터의 양과 크기가 대폭적으로 증가하는 경우  
위에서 서술한 각 방식의 특징을 잘 이해하고 있다면 더욱 효율적인 데이터를 다룰 수 있을 것이다.