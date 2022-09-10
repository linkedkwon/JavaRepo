# String Class
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/DataType-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/String-yellow"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 불변 객체(Immutable Object)란?
  모든 변수는 상태가 변할 수 있는 `가변(Mutable)` 상태, 불가능한 `불변 상태`의 두 가지 형태로 구분할 수 있다. 불변 상태의 기본형 변수는 한 번 값이 할당되면 
  다시는 값을 변경할 수 없고, 참조형 변수는 인스턴스 내부의 필드 수정은 가능하지만 다른 인스턴스를 재참조할 수 없다. 이는 Heap 영역에 인스턴스가 생성되고 나면, 
  해당 인스턴스의 **참조 변수는 Stack 영역에 할당되어 애플리케이션이 종료될 때까지 재사용 가능**하다.(불변 객체는 다른 문서에서 자세히 다룸)

<br>

## 2. 불변 객체 String
  문자열을 다루기 위해서는 Character 타입의 배열을 사용할 수도 있지만, Java에서는 기본적으로 String 클래스를 제공하고 있다. 아래에서 String 클래스를 사용해 
  문자열을 생성하는 두 가지 방법을 알아보자. 

  * ### New 연산자 ###
    먼저 일반적인 인스턴스를 생성할 때 사용하는 `New 연산자 방식`으로 할당시킬 문자열을 생성자의 인자로 넘겨줄 수 있다. 생성된 문자열은 일반적인 객체들과 
    같은 방식으로 **Heap 영역 어딘가에 할당**된다.
  
    <img width="524" alt="new" src="https://user-images.githubusercontent.com/78818063/161482884-e1597b6c-1824-4a2c-b039-e55249b1293d.png">
  
  * ### String 리터럴(Literal) ###
    다음은 `리터럴 초기화`로 String 객체에 직접 문자열을 대입하는 방식으로 이전과 다르게 생성된 문자열을 `String Pool`(Constant Pool, 기존 Perm 영역에서 
    Java 7 이후 Heap 영역으로 대체됨)이란 `문자열 상수 저장소`에 할당한다. 이때 생성된 문자열이 **이미 존재하면 해당 인스턴스의 참조 주소를 반환하고**, 만약 **없다면 
    새로운 인스턴스를 생성**한다. 이는 자바 측에서 애플리케이션에서 문자열 사용 빈도가 높은 것을 감안해 문자열을 상수로 정의하였고, 생성된 문자열을 풀에서 관리하여 재사용하기 
    위해 고안된 방식이다. 
    
    <img width="609" alt="stringpool" src="https://user-images.githubusercontent.com/78818063/161482981-8b43d36a-b37e-4033-b2be-58ca21988208.png">

<br>

## 3. String, 불변 객체로서 또 다른 장점 ##
앞서 String Pool의 문자열 관리 방식을 통해 String 객체가 왜 불변 객체라 불리는지 이해할 수 있었다. 다음은 String 클래스가 갖는 다른 장점을 아래 항목에서 간략히 정리해보자. 

  * ### 보안 문제 ###
    객체의 필드 값을 수정하기 위해 사용되는 `Setter`, `생성자`, `메소드` 등의 방식은 기본적으로 값을 파라미터로 전달받는다. 이같은 메소드가 실제 서비스에서 호출될 때 문자열을 
    인자로 받는 경우를 생각해보자. 회원의 아이디와 패스워드 뿐만 아니라 세션을 관리하는 토큰, 호스트의 정보를 담고 있는 포트 번호와 리소스의 위치를 가리키는 URL(Uniform Resource Locator) 등의 
    대부분의 데이터는 문자열로 구성되어 있다. 그리고 위 값들은 대부분 `외부 노출`에 취약하기 때문에 앞서 설명한 `메소드 호출`에 의해 값이 수정될 우려가 있다. String 객체는 불변 상태로 
    이같은 데이터를 다룰 때 변경 우려가 있는 요소들에 생길 수 있는 문제를 최소화 할 수 있다.
    
    <img width="518" alt="security" src="https://user-images.githubusercontent.com/78818063/161483596-34fe79b8-b28a-4819-93c3-a26388772ebd.png">

  * ### 동기화 문제 ###
    멀티 스레드 환경에서 서로 다른 스레드가 특정 변수를 참조한다고 할 때, `Read & Write`가 일어나면 한 쪽에서 값을 사용할 때 동기화 문제가 발생할 수 있다. 보통 트랜잭션에서 
    많이 발생하는 참조에 의한 동기화 문제인데, 앞서 설명한 리터럴 방식은 이러한 문제 소지를 완전히 제거한다. Write가 일어나 새로운 문자를 만든 경우에는 풀에 새로운 인스터스가 
    할당되어 새로운 참조 주소를 반환할 뿐이다. 따라서 String 객체는 이러한 동기화 문제를 완벽히 예방할 수 있다. 
    
  * ### Hash Code 캐싱 ###
    String 클래스의 hashCode() 메소드는 기본적으로 **처음 Hash 값을 계산할 때만 로직을 수행**한다. 즉 불변 객체기 때문에 한 번 계산한 값을 캐싱해 다음 번 해시 값이 필요할 때 
    재사용하므로 내부적으로 hashCode()를 사용하는 `HashMap`, `Set` 등의 자료 구조에서 String 타입을 사용했을 때 높은 효율을 지닌다.

<br>

## 4. 단점, 메모리 이슈 ##
이전까지 String 객체가 불변 상태로서 가지는 여러 장점들을 정리해 보았다. 인스턴스의 재사용은 메모리 사용성을 높인다는 강력한 장점을 지니기도 하지만 반면에 단점 또한 존재한다. 
먼저 아래 코드를 살펴보자.

<img width="518" alt="concat" src="https://user-images.githubusercontent.com/78818063/161483635-87cb4679-ddfd-4db3-bc61-59e94e7a82ad.png">

* ### 무한한 인스턴스 생성 ###
  먼저 두 문자열을 복사하는 작업으로 "Hello"와 "World"를 풀에 생성하여 A와 B 객체에 각각 참조 주소를 할당하였다. 그리고 다시 C와 D에 이미 생성된 
  두 문자열의 참조 주소를 할당해주고 있다. 여기서 실제 생성된 인스턴스는 단 두개로 앞서 설명했던 String Pool을 통해 문자열이 재사용된 것이다. 하지만 
  아래 객체 E는 얘기가 다르다. 객체 E는 두 객체 A와 B를 합쳐 "Hello World"란 문자열을 새롭게 생성하고 있다. 이때 String 클래스가 불변 객체로서 갖는 
  가장 큰 문제점을 한 가지 확인할 수 있다. 앞서 객체 C와 D는 A와 B에서 생성된 인스턴스가 이미 String Pool에 있기 때문에 Stack에서 해당 인스턴스를 
  참조하는 참조 변수만 새로 만들 뿐이었다. 하지만 **E에 할당된 문자열은 기존 문자열을 더했을 뿐이지 애초에 저장되어 있지 않기 떄문에 풀에 새로운 인스턴스가 
  할당**된다. 즉 같은 문자열이 아닌 **모든 새로운 문자열은 상수화되어 인스턴스가 풀에 적재**되는 것이다. 따라서 문자열을 합치거나 형태를 조합하는 로직에서 
  String 객체를 사용하면 무수히 많은 인스턴스가 생성되어 메모리가 부족해질 수 있고, 또한 이후 `GC(Garbage Collection)` 성능에 영향을 주거나 심하면 
  메모리가 누수되는 문제까지 야기할 수 있다.  

* ### 해결 방안 ###
  따라서 특정 로직에서 문자열 변수들의 거의 대부분이 재사용되지 않는다면 기본적으로 `StringBuildr`, `StringBuffer`와 같이 **Character 형을  
  조합해 문자열을 리턴해주는 클래스**들을 사용하여 힙 영역의 메모리를 낭비자히 않도록 하는 것이 바람직하다.(각 방식 차이는 하위 문서에서 자세히 다룸)

<br>

## 5. String Class 주요 함수 정리 ##
다음은 String 클래스에서 자주 사용되는 함수들의 목록으로 이 외에는 공식 문서를 참고하여 사용하자.   

* **char charAt(int index)** : 특정 index의 char 값을 반환한다.
* **int length()** : 문자열의 길이를 반환한다.
* **String substring(int beginIndex)** or substring(int beginIndex, int endIndex) : 파라미터를 사용해 부분 문자열을 반환한다. 
* **boolean contains(CharSequences)** : 문자열 포함 여부를 판단한다. 
* **boolean equals(Object another) or equalsIgnoreCase(Object another)** : 문자열을 비교한다. 
* **String replace(char old, char new)** : 지정된 문자열의 특정 원소를 다른 원소로 대체한다.
* **String trim()** : 선행 및 후행 공백을 제거한다. 
* **String split(String regex)** : 나누어진 문자열과 일치하는 정규 표현식을 반환한다. 
* **int indexOf(int ch)** : 특정 문자의 위치를 반환한다. 

<br>
