# Java Version이란?
<a href="http://melonicedlatte.com/">
    <img src="https://img.shields.io/badge/Java-red"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
    <img src="https://img.shields.io/badge/Version-orange"
        style="height : auto; margin-left : 8px; margin-right : 8px;"/>
</a>

## 1. JDK(Java Development Kit)
JDK란 Java로 애플리케이션을 개발할 수 있도록 필요한 기능을 갖춘 Java 용 `SDK(Software Development Kit)`를 의미하며, 
내부에는 코드 작성을 위한 개발 툴 `JDT(Java Development Tools)` 와 해당 코드를 실행하기 위한 도구 `JRE(Java Runtime Environment)`로 구성되어 있다. 
이는 버전에 따라 지원하는 클래스와 메소드들이 추가되거나 사용 지양되고 있으므로 필요한 기능에 따라 적절한 버전을 잘 선택해야 한다. 

  * ### 표기법 변화 ###
    * **JDK(1.0 ~ 1.1)**
    * **J2SE(1.2 ~ 5)**
    * **Java SE(6 ~ 17)**
  
기존 JDK 또는 J2SE로 불리던 방식은 현재 Java (사용 버전)로 불리고 있다.

<br>

## 2. JDK 구성 요소
앞서 JDK가 JDT과 JRE로 구성되어 있는 것을 확인할 수 있었다. 이 중 JDT는 프로젝트에서 클래스나 자원을 찾도록 돕는 Builder, 컴파일 시 단위와 
구문을 지정하고 편집하는 Editor, 오류나 버그를 진단하는 디버거 등을 포함해 **개발 편의성을 높여주고**, JRE는 내부에 `라이브러리 클래스들`과 
`JVM(Java Virtual Machine)`을 두어 **애플리케이션을 실제 실행할 수 있는 환경을 제공**한다. 

<img width="578" alt="jdkinner" src="https://user-images.githubusercontent.com/78818063/161191632-ba2c65c3-a1e0-4c29-8edb-ff7c6ebb1d16.png">

    JDK = JDT + JRE
    JDT = Editor, Builder, Debugger, Viewer, Jconsole.. etc
    JRE = JVM + Library

<br>

## 3. JDK 종류 ##
JDK는 개발 용도에 따라 지원하는 플랫폼을 몇 가지로 구분하고 있으며, 애플리케이션 환경에 따라 필요한 라이브러리와 API(Application Programming Interface)를 
중점적으로 제공한다. 네 종류의 키트는 다음과 같다.

  * ### Java SE(Standard Edition) ###
    **가장 기본이 되는 표준 에디션으로 Java의 코어 기능을 제공**한다. 기본적인 자료형과 문자열(String) 클래스부터 java.lang/io/util/awt 등 개발에 필요한 
    여러 클래스를 포함한 패키지들을 제공한다. 따라서 데스크탑의 프로그램(GUI, Graphic User Interface) 개발부터 네트워킹 중심 엔터프라이즈 애플리케이션의 DB 처리나 
    보안까지도 지원한다.   

  * ### Java EE(Enterprise Edition) ###
    Java EE는 **SE 스펙을 기반으로 애플리케이션의 인프라를 추가적으로 제공**한다. 이는 웹 프로그래밍에 필요한 다수의 기능을 포함하고 있는데 JSP(Java Servlet Page)나 
    DB 커넥션 등 대규모, 다계층, 확장성, 신뢰성, 보안 네트워킹 API, 환경 등 실제 서비스에서 필요한 기능을 제공한다. 일반적으로 **동적으로 일어나는 비즈니스 로직을 수행**하기 
    위한 용도로 사용되며, 주로 세션이나 커넥션 상태를 기반으로 EJB 아키텍처 기반 컴포넌트를 지원한다.  
  
  * ### Java ME(Micro Edition) ###
    ME 에디션은 **모바일 환경과 같이 낮은 스팩을 가진 가상 머신에서 사용할 여러 도구를 지원**한다. EE 에디션과 마찬가지로 SE를 기반으로 한 **경량화된 라이브러리와 API**를 
    포함하고 있으며, 서비스의 클라이언트 역할을 수행하기도 한다.  

  * ### Java FX ###
    FX는 주로 경량화된 API를 통해 리치 인터넷 어플리케이션을 만들 때 사용되는데, 하드웨어 수준에서 가속 기능을 사용할 수 있는 그래픽과 미디어 엔진을 갖추고 있으므로 
    **UI(User Interface)와 UX(User eXperience)에 고속적인 성능이 필요한 경우** 사용된다. 추가적으로 ME 환경과 마찬가지로 Java EE 에디션과 같이 클라이언트 
    역할을 하기도 한다.  
  
<br>

## 4. Java LTS(Long Term Support) ##
일반적으로 Java 버전은 매년 두 번(3월과 9월), 6개월을 주기로 업데이트하여 출시된다. 그리고 **6번 이상 업데이트 되면(현재는 3년에서 2년으로 주기를 변경함) 그간의 
성능과 안정성을 보장받았다 판단하여 비교적 오랜 기간 사용 권장되는 Java LTS 버전을 출시**한다. 실무에서 가장 많이 사용되고 있는 `8`과 `11`, 그리고 최근 발표된 
`17` 버전 등이 이러한 LTS 버전에 해당된다. 

  * ### LTS 버전을 써야 하는 이유 ###
    버전 업데이트는 개발자에게 여러 가지 장점을 제공한다. 그간 사용했던 라이브러리의 오류나 버그 발생 등 취약했던 기능을 검토하고 수정하는데, 
    아래와 같은 세 단계의 피드백 과정을 거치고 나면 JDK는 실제 서비스에 도입할 수 있는 안정적인 새로운 버전으로 업데이트 된다. 
    즉 검증된 기능들을 사용해 애플리케이션을 개발하면 보다 안정성이 보장된 결과물을 얻을 수 있는 것이다.
    
    * **Incubator** : 피드백을 수용해 설계하는 단계로 테스트 유닛을 제작한다.
    * **Preview** : 실제 버전에서 테스트로 도입하는 단계
    * **Standard** : 지속적인 검증을 거쳐 정식 기능으로 채택하는 단계  

  * ### 버전 업데이트와 채택 이슈 ###  
    결론적으로 LTS 버전은 몇 차례 검증을 거친 스탠다드의 여러 기능들이 제공된다. 코드 작성 방식 변경, 내부 자동화, 심지어 외부 하드웨어나 플러그인까지도 
    지원해 개발자는 높은 편의성과 빠른 개발 속도를 제공받는다. 하지만 한 가지 주의할 점은 업데이트 된 기능을 사용하기 위해 반드시 최신 버전을 선택하면 안된다는 
    것이다. 이러한 이유는 업데이트 된 버전에서는 다른 기술과 환경에서 호환되지 않을 수 있기 때문인데, 예를 들어 실제 개발 환경에서 Kotlin UI 라이브러리나 
    Android 환경은 Java 11과 `호환성` 문제가 발생해 실무에서는 **여전히 Java 8을 선호하고 있다**. 이뿐만이 아니다. 업데이트 된 버전에 따라 외부 기술을 
    제공하는 기업들서도 자신의 기술과 JDK에서 발생할 수 있는 호환성 이슈에 대한 검증을 거쳐야 하므로 이전 버전들에 더 많은 지원 기간을 부여하기도 한다. 따라서 반드시 
    최신 버전을 채택하기 보다는 **조직이나 팀에서 사용하고 있는 기술과 상황에 따라 별다른 이슈가 없는지 판단해 적절한 버전을 채택**해야 한다.
  
  * ### LTS 버전 지원 기능 ###
    아래는 주요 LTS 버전인 8, 11, 17의 일부 추가 사항으로 해당 기능들은 이전 버전에서 테스트와 검증을 거쳐 각 LTS 버전에서 정식으로 채택되었다. 
    각 기능에 대한 자세한 정리는 하위 문서에서 다루도록 한다.
  
      * **Java 8**
        * 인터페이스의 default & static 메소드
        * 날짜 및 시간을 다루는 Time 패키지
        * 함수형 프로그래밍 람다식(Lambda Expression)
        * 함수형 인터페이스  
        * Stream API
        * null 처리를 돕는 Optional 클래스
  
      * **Java 11**
        * 람다 스트림 내부 지역 변수 표현
        * 프로파일링 및 진단
        * Garbage 수집(Base : G1GC)
      
      * **Java 17**
        * 향상된 의사 난수 생성기(Enhanced Pseudo-Random Number Generators)
        * macOS Rendering Pipeline
        * 스위치 패턴 매칭
  
<br>
