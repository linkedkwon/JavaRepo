# 익명 클래스(Anonymous Class)
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/OOP-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Anonymous-yellow" style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. 익명 클래스란?
`익명 클래스`는 `내부 클래스(Inner Class)`의 한 종류로 별도의 클래스 선언을 하지 않는데, **로직에서 클래스 선언과 인스턴스 생성을 
동시에 수행**한다. 즉 **필요에 따라 일시적으로 단 한 번 사용하는 일회용 클래스를 익명 클래스**라 칭한다.

익명 클래스는 의미 그대로 클래스를 지칭하는 **이름이 없다**. 다시 말해 자신의 상태와 특성을 반영하는 클래스가 없다는 것인데, 이는 **다른 클래스들의 
상태와 특성을 상속받아 사용해야 함**을 뜻한다. 해당 내용읃 좀 더 정확하게 이해하기 위해 아래의 코드를 살펴보자.

<img width="683" alt="anonymousClass" src="https://user-images.githubusercontent.com/78818063/172998413-4fd0fa36-fc77-46ca-83a8-a46487ade93a.png">

해당 코드는 익명 클래스의 **인스턴스를 생성하는 과정**을 나타낸다. 언뜻 보면 Product 클래스 타입의 인스턴스를 생성하는 것처럼 보이지만 
new 연산자 호출 옆에는 특정 필드를 정의하는 블록이 따라온다. 블록에는 Product 클래스 내부에 포함된 필드 요소가 있는데, 특이한 점은 멤버 
변수를 다른 값으로 초기화 하고 있으며, 메소드를 재정의(Overriding)하여 특정 기능을 구현하고 있다.

위 과정은 마치 클래스를 상속받은 하위 클래스를 새롭게 정의하고 기능을 확장하는 것처럼 보인다. 정확하게는 별도의 클래스르 선언하지 않고 
실행 중에 **상속받은 클래스의 필드를 새롭게 정의하여 인스턴스를 생성**하는 것이다. 그렇기 때문에 우리는 이러한 클래스를 익명 클래스라 부르며, 
**이름이 없기 때문에 생성자가 없고, 자체적으로 선언한 필드 요소들을 외부에서 접근이 불가능**하다. 즉 **상속을 통해 필드를 특성에 맞게 정의하고 
단 한 번 사용한 뒤 버리는 클래**스인 것이다.

<br>

## 2. 사용하는 이유
앞서 익명 클래스가 무엇을 의미하는지를 간단한 예제를 통해 알아보았다. 그렇다면 **그저 클래스를 만들고 기능들을 상속받으면** 되는데, 왜 이러한 방법을 
사용하는 것일까? 위 물음에 대한 답변은 다음과 같다.

  * ### 낮은 재사용성 ###
    클래스를 설계하는 이유는 기본적으로 **객체의 특성과 행위를 표현하기 위함**이다. 이러한 객체는 여러 로직에서 빈번하게 사용되는데, 
    만약 프로그램 내에서 단발성으로 사용되는 크래스는 어떨까? 예를 들어 **스레드나 이벤트 처리 리스너와 같이 생성된 인스턴스가 
    단발성으로 사용되고 더 이상 참조되지 않는다면**, 이를 클래스로 선언하기엔 매우 비효율적이다. 따라서 이처럼 인스턴스가 일회성으로 
    사용되거나 확장이 예상되지 않는다면 익명 클래스를 사용하는 것이 바람직하다.
    
  * ### 확장성과 유지보수 비용 ###
    특정 클래스를 상속받는 하위 클래스의 **특징이 각기 다르면 매번 새로운 클래스를 선언해야 한다**. 만약 기업에서 많은 제품을 만들고 소비자의 반응에 
    따라 인기 상품을 정식으로 모델링하고 확장하려 한다고 가정해보자. 이는 **비즈니스 로직에서는 실제로 사용할 수 있음을 보장해야 하지만 아직 모든 제품을 
    모델링하기엔 설계나 유지보수 등의 비용 측면에서 매우 비합리**적이다. 즉 익명클래스는 이처럼 비용 측면에서 **결정이 고려되는 경우 임시 방편으로 사용**하기에 
    매우 유리하다.

  * ### 함수형 프로그래밍 ###
    익명 클래스는 **함수형 프로그래밍 방식**인 `람다식(Lambda Expression)`을 적용해 코드를 더욱 명확하고 간결히 표현할 수 있다. 

<br>

## 3. 사용법(익명 클래스의 인스턴스 생성하기)
마지막으로 익명 클래스의 사용법에 대해 다루고 글을 마치도록 한다. **기본적으로 클래스 또는 인터페이스를 상속받으며, 해당 특성과 행위를 물려받거나 재정의하여 
사용**해야 한다. 주의할 점으로는 **상속받은 필드가 아니라면 외부에서 사용이 불가능하므로 익명 클래스 내부에 자체적으로 제작한 필드는 내부에서 일어나는 특정 작업을 
처리하기 위해 사용**해야 한다. 그렇다면 아래에서 인스턴스 사용법에 대해 자세히 알아보자.

  * ### 클래스를 확장 ###
    가장 많이 사용되는 `클래스를 상속받아 확장`하는 방법으로 기존 상속 관계에서 사용하던 방식과 같다. 상위 클래스 필드는 super 키워드를 통해 
    접근이 가능하며, 메소드를 재정의하여 사용하면 외부에서 접근이 가능하다. 내부에 자신만의 필드를 선언할 수도 있는데, 이는 외부에서 접근이 불가능하므로 
    내부의 로직을 처리하기 위한 용도로 사용되어 진다.
    
    <img width="682" alt="byClass" src="https://user-images.githubusercontent.com/78818063/172998422-0895ee0f-8c43-45a3-af74-c345dd5aecc6.png">
    
  * ### 인터페이스 확장 ###
    `인터페이스 역시 상속받아 익명 클래스의 확장`이 가능하다. 방법은 클래스 확장과 같다.
    
    <img width="686" alt="byInterface" src="https://user-images.githubusercontent.com/78818063/172998431-d18af8fa-4101-4854-8c61-d6588a0869f3.png">
    
  * ### 익명 클래스를 인자로 받기 ###
    위의 두 항목에서 익명 클래스를 생셩하는 방법을 알아보았는데, 해당 `인스턴스를 인자로 전달`해 생성자나 메서드의 내부 인자로 취급할 수도 있다.
    
    <img width="682" alt="asParameter" src="https://user-images.githubusercontent.com/78818063/172998435-afe9465f-9a41-4017-b7be-8effc82cd724.png">

<br>