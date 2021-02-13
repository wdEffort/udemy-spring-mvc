# Spring Framework MVC

---

## Spring MVC 흐름

1. Client가 Web Browser에 요청을 하면 Dispatcher Servlet(Front Controller)가 이 요청을 받는다.
    - Client --------> Dispatcher Servlet
2. Dispatcher Servlet은 Handler Mapping을 이용해서 실제로 요청을 처리할 Controller를 결정한다.
    - Dispatcher Servlet --------> Handler Mapping
3. 이후 Handler Adapter를 통해 Controller에서 요청을 처리할 메소드를 실행시킨다.
    - Dispatcher Servlet --------> Handler Adapter --------> Controller
4. 메소드 실행 결과로 Model과 View에 대한 정보를 다시 Handler Adapter에게 전달한다.
    - Controller --------> Handler Adapter
5. Handler Adapter는 Controller에서 전달된 Model과 View에 대한 정보를 Dispatcher Servlet에게 전달하고, View Resolver를 이용해서 응답할 View를 요청한다.
    - Handler Adapter --------> Dispatcher Servlet --------> View Resolver
6. View Resolver를 통해 보여질 View는 Dispatcher Servlet에게 전달되어 최종적으로 Client는 해당 View를 응답으로 Web Browser에서 확인할 수 있게 된다.
    - View Resolver --------> Dispatcher Servlet --------> Client

---

## Controller 제작 순서

#### 예제 클래스 : com.udemy.springmvc.HomeController

> 최초의 클라이언트의 요청이 들어오면 컨트롤러로 진입을 하고 요청에 대한 작업 처리를 한 후에 뷰에 데이터를 전달한다.

1. @Controller 어노테이션을 이용한 클래스 생성
2. @RequestMapping 어노테이션을 이용한 요청 경로 매핑을 한다.
3. 요청 처리 메소드를 구현한다.
4. 뷰를 리턴한다.

---

## ModelAndView 객체를 이용한 데이터 전달

#### 예제 클래스 : com.udemy.springmvc.user.controller.UserController#info()

1. ModelAndView 객체를 생성한다.
2. Model 객체에 데이터를 추가 시킨다.
    - addObject() 메소드 이용
3. View 이름을 설정한다.
    - setViewName() 메소드 이용

---

## 커맨드(Command) 객체를 이용한 데이터 전달

#### 예제 클래스 : com.udemy.springmvc.user.controller.UserController#detailUserInfo()

1. 커맨드 객체란 HttpServletRequest를 통해 들어온 요청 파라미터들을 특정 클래스의 Setter 메소드를 이용하여 객체에 정의되어 있는 속성(Property, 멤버 변수)에 바인딩이 되는 객체를
   의미한다.
2. 커맨드 객체는 보통 `VO(Value Object)` 또는 `DTO(Data Transfer Object)`를 의미하며, HttpServletRequest로 받아오는 요청 파라미터의 key 값과 동일한 이름의
   속성들과 Setter 메소드를 가지고 있어야 한다.
3. 스프링이 내부적으로 HttpServletRequest와 커맨드 객체의 Setter 메소드를 이용하여 요청 파라미터의 값을 커맨드 객체 속성 값으로 자동으로 바인딩 시켜준다.
4. 커스텀 객체를 사용하면 요청을 처리하는 메소드의 인자로 HttpServletRequest 또는 @RequestParam 어노테이션을 사용하는 것보다 코드의 양이 줄고, 가독성도 좋아진다.

---

## @ModelAttribute 어노테이션 사용 방법

#### 예제 클래스 : com.udemy.springmvc.bbs.controller.BbsController

1. @ModelAttribute 어노테이션 사용위치
    - 크게 두 가지로 나뉜다.
        1) 메소드명 위에 사용하는 방법
            - 컨트롤러에서 @ModelAttribute 어노테이션이 붙은 메소드가 있다면 해당 컨트롤러 내 어떠한 메소드보다 먼저 실행이 된다.
            - 즉, 여러 핸들러 메소드에서 공통적으로 쓰이며, 메소드의 결과 값은 해당 컨트롤러 내에서 참조하는 View에서 사용 가능하다.
        2) 파라미터 옆에 사용하는 방법
            - 커맨드 객체와 같이 요청 파라미터들을 객체 속성에 바인딩 시켜주는 역할을 한다.
            - 이때는 @ModelAttribute 어노테이션을 생략해도 커맨드 객체에 요청 파라미터들이 바인딩 되는데, 그 이유는 스프링이 내부적으로 String 이나 int 등은 @RequestParam
              으로 보고, 그 외의 복잡한 객체들은 @ModelAttribute가 생략됬다고 간주하기 때문이다.(그렇다고 무조건 생략하는 것은 위험하기 때문에 되도록 명시적으로 사용하는 것을 권장한다.)
2. @ModelAttribute의 역할
    - Model에 객체를 자동으로 담아준다.
      ```java
       @RequestMapping(value = "/sample/users/info", method = RequestMethod.GET)
       public String userInfo(@ModelAttribute User user, Model model) {
           // @ModelAttribute 어노테이션을 사용하면 내부적으로 user 객체를 Model에 담기 때문에
           // 아래와 같은 코드를 작성할 필요가 없다.
           // model.addAttribute("user", user);
         
           return "sample/users/info";
       }
       ```
    - `검증(Validation)` 작업을 내부적으로 처리한다
        1) 보통 등록 또는 수정을 처리하는 핸들러 메소드에서 다양한 검증 처리를 실시해야 하는데, 사용자의 입력에 오류가 있는 경우 @ModelAttribute가 붙은 파라미터 바로 뒤에 Errors 또는
           BindingResult 객체를 선언함으로써 검증 처리를 실시한다.

---

## Redirect

#### 예제 클래스 : com.udemy.springmvc.sample.request.controller.RedirectSampleController

1. Web Container는 Redirect 명령 들어오면 웹 브라우저에게 다른 페이지로 이동하라는 명령을 내린다.
2. 웹 브라우저는 URL을 지시된 주소로 바꾸고 그 주소로 이동한다.
3. 새로운 페이지에서는 request,response 객체가 새롭게 생성된다.
    - 즉, 이전 요청과 Redirect된 요청에서 request, response 객체는 공유되지 않는다.

---

## Forward

1. Web Container 차원에서의 페이지 이동이며, 실제로 웹 브라우저는 다른 페이지로 이동했는지 알 수 없다.
2. 웹 브라우저에서는 최초로 요청한 URL만 표시되고, 이동한 페이지의 URL은 알 수 없다.
3. 현재 실행중인 페이지와 Forward에 의해 호출될 페이지는 request, response 객체를 공유한다.

---

## 유효성 검사(Validation)

#### 예제 패키지 : com.udemy.springmvc.validation

1. Validator
    - org.springframework.validation.Validator 인터페이스
    - 구현 메소드
        1) boolean supports(Class<?> arg0) : 구현한 Validator가 해당 커맨드 객체에 대한 값 검증을 지원하는지의 여부를 반환한다.
        2) void validate(Object target, Errors errors) : target 객체에 대한 검증을 실행하는 메소드이다. `target`은 커맨드 객체를 가르키며, `errors`는
           검증에 대한 문제가 있을 때 에러 정보를 저장하는 객체이다.

---

## 커맨드 객체에 검증 코드를 추가하는 방법

#### 예제 패키지 : com.udemy.springmvc.validation

1. @RequestMapping 어노테이션 메소드에서 커맨드 객체 다음 파라미터로 `BindingResult` 타입이나 `Errors` 타입의 파라미터를 추가한다.
2. @RequestMapping 어노테이션 메소드에서 Validator 객체를 생성한 후 validate() 메소드를 호출한다.
    - 이때 커맨드 객체와 BindingResult 또는 Errors 타입의 파라미터를 전달한다.
3. Errors.hasErrors() 메소드를 이용하면 에러가 있는지 여부를 확인 할 수 있다.
    - 에러가 있는 경우 `TRUE`, 없는 경우 `FALSE`를 리턴한다.

---

## ValidationUtils 클래스

#### 예제 패키지 : com.udemy.springmvc.validation

1. validate() 메소드를 좀 더 편리하게 사용할 수 있도록 하는 클래스이다.
    - ValidationUtils 클래스 사용 전
      ```java
      // validate() 메소드 코드 일부
      @Override
      public void validate(Object target, Errors errors) {
        Member member = (Member) target;
        String memberId = member.getId();
      
        if (member.getId() == null || member.getId().trim().isEmpty()) {
            System.out.println("회원 아이디를 입력하세요.");
            errors.rejectValue("id", "입력시 오류 발생");
        }
      }
      ```
    - ValidationUtils 클래스 사용 후
      ```java 
      // validate() 메소드 코드 일부 
      @Override 
      public void validate(Object target, Errors errors) { 
         Member member = (Member) target; 
         String memberId = member.getId();
         ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "입력시 오류 발생");
      }
      ```

---

## @Valid와 @InitBinder 어노테이션 사용

#### 예제 클래스 : com.udemy.springmvc.validation.member.controller.MemberController#memberRegist()

1. 구현한 Validator의 `validate()` 메소드를 직접 호출하지 않고 스프링 프레임워크에서 호출하는 방법이다.
    - pom.xml에서 의존성 라이브러리를 추가시켜야 한다.
       ```xml
        <!-- https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator -->
        <dependency>
            <groupId>org.hibernate.validator</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>6.1.5.Final</version>
        </dependency>
       ```
2. @RequestMapping 어노테이션 메소드에서 커맨드 객체에 @Valid 어노테이션을 지정한다.
   ```java
    @RequestMapping(value = "/members", method = RequestMethod.POST)
    public String memberRegist(@Valid @ModelAttribute("Member") Member member, BindingResult bindingResult) {
        // code ...
    }
   ```
3. 컨트롤러에서 @InitBinder 어노테이션이 붙은 메소드를 추가한다.
   ```java
    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new MemberValidator());
    }
   ```

---

## Errors 인터페이스와 BindingResult 인터페이스

1. Errors 인터페이스 : org.springframework.validation.Errors : 유효성 검증 결과를 저장할 때 사용
    - 제공하는 메소드
        1) `void` reject(String errorCode) : 전 객체에 대한 글로벌 에러 코드를 추가한다.
        2) `void` reject(String errorCode, String defualtMessage) : 전 객체에 대한 글로벌 에러코드를 추가하고, 에러코드에 대한 메시지가 존재하지 않을 경우
           defaultMessage를 사용한다.
        3) `void` reject(String errorCode, Object[] errorArgs, String defaultMessage) : 전 객체에 대한 글로벌 에러코드를 추가하고, 메시지 인자로
           errorArgs를 전달 할 수 있고, 에러코드에 대한 메시지가 존재하지 않을 경우에는 defaultMessage를 사용한다.
        4) `void` rejectValue(String field, String errorCode) : 필드에 대한 에러코드를 추가한다.
        5) `void` rejectValue(String field, String errorCode, String defaultMessage) : 필드에 대한 에러코드를 추가하고, 에러코드에 대한 메시지가
           존재하지 않을 경우 defaultMessage를 사용한다.
        6) `void` rejectValue(String field, String errorCode, Object[] errorArgs, String defaultMessage) : 필드에 대한 에러코드를
           추가하고, 에러메시지 인자로 errorArgs를 전달하고, 에러코드에 대한 메시지가 존재하지 않을 경우 defaultMessage를 사용한다.
    - 에러 발생 여부를 확인하기 위한 메소드
        1) `boolean` hasErrors() : 에러가 존재하는 경우 TRUE를 반환한다.
        2) `int` getErrorCount() : 에러 개수를 반환한다.
        3) `boolean` hasGlobalErrors() : reject() 메소드를 이용해서 추가된 글로벌 에러가 존재할 경우 TRUE를 반환한다.
        4) `int` getGlobalErrorCount() : reject() 메소드를 이용해서 추가된 글로벌 에러 개수를 반환한다.
        5) `boolean` hasFieldErrors() : rejectValue() 메소드를 이용해서 추가된 에러가 존재할 경우 TRUE를 반환한다.
        6) `int` getFieldErrorCount() : rejectValue() 메소드를 이용해서 추가된 에러 개수를 반환한다.
        7) `boolean` hasFieldErrors(String field) : rejectValue() 메소드를 이용해서 추가한 특정 필드의 에러가 존재할 경우 TRUE를 반환한다.
        8) `int` getFieldErrorCount(String field) : rejectValue() 메소드를 이용해서 추가한 특정 필드의 에러 개수를 반환한다.
2. BindingResult 인터페이스 : org.springframework.validation.BindingResult : Errors 인터페이스를 확장한 인터페이스이며, 요청 데이터를 커맨드 객체에 바인딩한
   결과를 저장하고 에러코드로 부터 에러메시지를 가져오는 역할을 한다.

---

## @Component 어노테이션

#### 예제 패키지 : com.udemy.springmvc.sample.abstracts

1. `<context:component-scan base-package=""/>` 태그는 base-package에서 지정한 패키지에서 @Component 어노테이션이 적용된 클래스를 검색해서 @Bean으로 자동
   등록한다.
    - 따라서 XML 설정 파일에서 다양한 Bean 정보를 추가하지 않아도 된다.
    - 설정 파일에서는 `<context:annotation-config/>` 태그를 사용해야 한다.
       ```java
       @Component("service01")
       public class AbstractServiceImpl implements AbtractService {
         
         private String name = "service";
       
         @Override
         public String getClassName() {
             return "AbstractService : " + name;
         }
       }
       ```
    - 위 코드를 XML 설정 파일에서 <bean> 태그로 작성한다면 아래와 같이 작성해야 한다.
       ```xml
      <bean id="service01" class="com.udemy.springmvc.AbtractService"/> 
      ```

---

## @Resource 어노테이션

#### 예제 패키지 : com.udemy.springmvc.sample.abstracts

1. Java 6 및 JEE 5에 추가되었다.
2. 어플리케이션에서 필요로 하는 자원을 자동으로 연결할 때 사용한다.
    - 이후 배우게 될 `@Autowired` 어노테이션과 동일한 기능을 한다.
3. 스프링에서 의존하는 Bean 객체를 전달할 때 사용한다.
4. 설정 파일에서는 `<context:annotation-config/>` 태그를 사용해야한다.
    - 또는 `<bean class="org.springframework.beans.factory.annotation.CommonAnnotationBeanPostProcessor"/>` 클래스를 Bean으로
      등록시켜 사용하는 방법이 있다.

---

## @Autowired 어노테이션

#### 예제 패키지 : com.udemy.springmvc.shoes

1. 의존 관계를 설정하는 어노테이션이다.
    - Type 기반
    - 생성자, 필드, 메소드에 적용 가능하다.
    - set 계열의 메소드가 아닌 메소드에도 적용 가능하다.

---

## @Qualifier 어노테이션

#### 예제 패키지 : com.udemy.springmvc.shoes

1. 동일한 타입의 Bean이 여러 개 있을 때 그 중 어떤 Bean을 주입할 것인지 설정하는 어노테이션이다.

---

## ViewResolver 구현 클래스와 다수의 ViewResolver 설정

1. ViewResolver 구현 클래스
    - InternalResourceViewResolver : View 이름으로 부터 JSP나 Tiles 연동을 위한 View 객체를 반환한다.
        1) JSP나 HTML 파일과 같이 웹 어플리케이션의 내부 자원을 이용하여 View를 생성하는 `AbstractUrlBaseView` 타입의 View 객체를 반환한다.
        2) 기본적으로 사용하는 View 클래스이다.
            - viewClass, prefix, suffix 프로퍼티를 사용한다.
        3) 설정방법(예)
           ```xml
           <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
              <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
              <property name="prefix" value="/WEB-INF/views/"/>
              <property name="suffix" value=".jsp"/>
           </bean>
           ```
    - VelocityViewResolver : View 이름으로 부터 Velocity 연동을 위한 View 객체를 반환한다.
    - VelocityLayoutResolver : VelocityViewResolver와 동일한 기능을 제공하며, 추가로 Velocity의 레이아웃 기능을 제공한다.
    - BeanNameViewResolver : View 이름과 동일한 이름을 갖는 Bean 객체를 View 객체로 사용한다.
        1) View 이름과 동일한 이름을 갖는 Bean을 View 객체로 사용한다.
        2) 주로 커스텀 View 클래스를 View로 사용해야 하는 경우에 사용한다.
        3) 설정방법(예)
            ```xml
            <bean id="viewResolver" class="org.springframework.web.servlet.view.BeanNameViewResolver">
                <!-- 특정 Controller에서 View의 이름을 "download"로 지정했을 경우, "com.spring.download.pdfDown" 클래스가 처리를 한다. -->
                <bean id="download" class="com.spring.download.pdfDown"/>
            </bean>
           ```
    - ResourceBundleViewResolver : View 이름과 View 객체간의 매핑 정보를 저장하기 위해 자원 파일을 사용한다.
    - XmlViewReslolver : View 이름과 View 객체간의 매핑 정보를 저장하기 위해 XML 설정 파일을 사용한다.
        1) View 이름과 동일한 이름을 갖는 Bean을 View 객체로 사용한다.
        2) 별도의 XML 설정 파일로부터 Bean 객체를 검색한다.
        3) 설정방법(예)
         ```xml
         <bean id="viewResolver" class="org.springframework.web.servlet.view.XmlViewResolver">
            <!-- aaa.xml 설정 파일로부터 Bean 객체를 검색하여 View 객체로 사용하게 된다. -->
            <property name="location" value="/WEB-INF/views/aaa.xml"/>
         </bean>
         ```
2. 다수의 ViewResolver 설정
    - 하나의 DispatcherServlet은 한 개 이상의 ViewResolver를 설정할 수 있다.
    - `order` 라는 프로퍼티를 이용해서 View 이름을 검사할 ViewResolver의 순서를 결정한다.
        1) `order` 프로퍼티 값이 작을수록 우선 순위가 높다.
        2) 높은 우선 순위의 ViewResolver가 `null`을 반환하는 경우 다음 우선 순위의 ViewResolver에 View를 요청한다.
        3) `InternalResourceViewResolver`는 마지막 우선 순위를 갖도록 지정한다.
            - InternalResourceViewResolver는 항상 View 이름에 매핑되는 View 객체를 반환하기 때문에 null을 반환하지 않는다. 따라서 다른 ViewResolver의 우선
              순위를 이보다 낮게 설정한 경우 다음 순서로 사용되지 못하는 상황이 발생할 수 있다.
    - 설정방법(예)
      ```xml
      <!-- BeanNameViewResolver 설정 -->
      <bean id="beanNameViewResolver" class="org.springframework.web.servlet.view.BeanNameViewResolver" p:order="1"/>
      
      <!-- InternalResourceViewResolver 설정 -->
      <bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
         <!-- order 프로퍼티를 이용해서 다른 ViewResolver보다 낮은 순위를 갖도록 한다. -->
         <property name="order" value="2"/>
         <property name="prefix" value="/WEB-INF/views/"/>
         <property name="suffix" value=".jsp"/>
      </bean>
      ```

---

## Spring Form Tag Library(스프링 커스텀 태그)

#### 예제 패키지 : com.udemy.springmvc.sample.formtag

1. 스프링 프레임워크 2.0부터 JSP와 WEB MVC를 사용하는 경우 `<form/>` 태그 요소를 다룰 수 있도록 데이터 바인딩과 관련된 광범위한 태그 라이브러리를 제공한다.
    - 폼 태그 라이브러리는 spring-webmvc.jar 안에 있다.
2. `<form:form>` 태그
    - 이 태그는 HTML `<form/>` 태그를 생성하고 바인딩을 위해서 내부 태그에 `바인딩 경로(path)`를 지정한다.
      ```html
      <form:form>
        <!-- code ... -->
      </form:form>
      ```
    - 위의 예처럼 `<form/>` 태그에 method, action을 지정하지 않으면 기본적으로 method는 post로 설정되고, action은 현재 요청한 URL로 설정이 된다.
      ```html
      <form id="command" method="post" action="url">
        <!-- code ... -->
      </form>
      ```
    - 커맨드 객체의 이름이 기본 값인 `command`가 아닌 경우 commandName 속성에 커맨드 객체의 이름을 명시해야 한다.
      ```html
      <form:form commandName="commandName">
        <!-- code ... -->
      </form:form>
      ```
3. `<form:form>` 태그 내부에는 `<input>` 태그나 `<select>` 태그와 같이 입력 폼을 출력하는데 필요한 HTML 태그를 입력해서 사용할 수 있다. 이때 입력한 값이 잘못되어 다시 입력해야
   하는 경우에는 이전에 입력한 값을 출력할 수 있다.
   ```html
   <form:form commandName="commandName">
        <form:input path="id" value="${commentName.id}">
   </form:form>
   ```

---

## 스프링 커스텀 태그의 종류

#### 예제 패키지 : com.udemy.springmvc.sample.customtag

1. \<input\> 태그를 위한 커스텀 태그
    - \<form:input/\> : text 타입의 \<input\> 태그
    - \<form:password/\> : password 타입의 \<input\> 태그
    - \<form:hidden/\> : hidden 타입의 \<input\> 태그
    - path 속성 : 입력한 값이 바인딩 될 커맨드 객체의 프로퍼티를 지정하는 속성이다.
   ```html
   <!-- 스프링 폼 태그 -->
   <form:input path="userId"/>
   
   <!-- HTML 변환 후 -->
   <input type="text" name="userId" id="userId" value=""/>
   ```
2. \<select\> 태그를 위한 커스텀 태그
    - \<form:select\> : \<select\> 태그를 생성한다.
    - \<form:options\> : 지정한 콜렉션 객체를 이용해서 \<option\> 태그를 생성한다.
    - \<form:option\> : 하나의 \<option\> 태그를 생성한다.
    - 이 태그는 선택 옵션을 제공할 때 주로 사용하는 태그이므로, 여러 개의 선택사항들을 제공한다.
    - 이러한 선택 정보(옵션)는 Controller에서 생성해서 View로 전달하는 경우가 일반적이다.
        1) @ModelAttribute 어노테이션을 이용해서 전달한다.
         ```java
        // 특정 Controller의 소스 코드
        // 메소드에서 반환하는 List 콜렉션은 View에서 사용할 수 있다.
         @ModelAttribute("seasons")
        protected List<String> referData() throws Exception {
            List<String> seasons = new ArrayList<String>();
            seasons.add("봄");
            seasons.add("여름");
            seasons.add("가을");
            seasons.add("겨울");
        }
         ```
      ```html
      <!-- 스프링 폼 태그 -->
      <form:select path="seasons" items="${seasons}"/>
      
      <!-- HTML 변환 후 -->
      <select name="seasons" id="seasons">
        <option value="봄">봄</option>
        <option value="여름">여름</option>
        <option value="가을">가을</option>
        <option value="겨울">겨울</option>
      </select>
      
      <!-- 스프링 폼 태그 또 다른 방법(1) -->
      <form:select path="seasons">
        <form:option value="">선택</form:option>
        <form:options items="${seasons}"/>
      </form:select>

      <!-- HTML 변환 후 -->
      <select name="seasons" id="seasons">
        <option value="">선택</option>
        <option value="봄">봄</option>
        <option value="여름">여름</option>
        <option value="가을">가을</option>
        <option value="겨울">겨울</option>
      </select>
      
      <!-- 스프링 폼 태그 또 다른 방법(2) -->
      <form:select path="seasons">
        <form:option value="따듯한 봄"/>
        <form:option value="뜨거운 여름">여름</form:option>
        <form:option value="추운 겨울" label="눈 내리는 겨울"/>
      </form:select>
      
      <!-- HTML 변환 후 -->
      <select name="seasons" id="seasons">
        <option value="따뜻한 봄">따뜻한 봄</option>
        <option value="뜨거운 여름">여름</option>
        <option value="추운 겨울">눈 내리는 겨울</option>
      </select>
      ```
    - 위의 코드에서 만약, `seasons` 객체의 프로퍼티 값이 "봄"인 경우 해당 \<option\> 태그에 `selected` 속성이 자동으로 추가된다.
    - 콜렉션에 있는 객체가 String이 아닌 경우
      ```java
      public class Code {
        private String code;
        private String label;
      
        // getter, setter ...
      }
      ```
      ```html
      <!-- 
      "jobCode" 라는 콜렉션 객체(List<Code>)를 사용하는 경우 스프링 폼 태그 
      Code 클래스의 label 프로퍼티를 <form:options/> 태그의 itemLabel 값에 매칭시키고,
      Code 클래스의 code 프로퍼티를 <form:options/> 태그의 itemValue 값에 매칭시킨다.
      -->
      <form:select path="jobCode">
        <form:option value="">선택</form:option>
        <form:options items="${jobCode}" itemLabel="label" itemValue="code"/>
      </form:select>
      
      <!-- HTML 변환 후 -->
      <select name="jobCode" id="jobCode">
        <option value="">선택</option>
        <option value="code 프로퍼티 값">label 프로퍼티 값</option>
        <option value="code 프로퍼티 값">label 프로퍼티 값</option>
        <option value="code 프로퍼티 값">label 프로퍼티 값</option>
      </select>
      ```
3. checkbox 타입의 \<input\> 태그를 위한 커스텀 태그
    - \<form:checkboxes\> : 커맨드 객체의 특정 프로퍼티와 관련된 checkbox 타입의 \<input\> 태그 목록을 생성한다.
    - \<form:checkbox\> : 커맨드 객체의 특정 프로퍼티와 관련된 checkbox 타입의 \<input\> 태그 한 개를 생성한다.
      ```java
      // 커맨드 객체(MemberInfo)가 아래와 같을 때
      public class MemberInfo {
        private String[] favorites;
      
        // getter, setter ...
      }
      ```
      ```html
      <!-- 스프링 폼 태그, "favor" 이라는 콜렉션 객체를 사용하는 경우 -->
      <form:label path="favorites">선호하는 것</form:label>
      <form:checkboxes path="favorites" items="${favor}"/>
      
      <!-- HTML 태그 변환 후(예) -->
      <input type="checkbox" name="favorites" id="favorites0" value="독서"/><label for="favorites0">독서</label>
      <input type="checkbox" name="favorites" id="favorites1" value="여행"/><label for="favorites1">여행</label>
      ```
    - 스프링에서 제공하는 '_'를 이용하여 커맨드 객체의 특정 프로퍼티에 값을 바인딩 할 수도 있다.
       ```java
      public class MemberInfo {
        private boolean contractAgreement;
      
        public boolean isContractAgreement() {
            return this.contractAgreement;
        }
      
        public void setContractAgreement(boolean contractAgreement) {
            this.contractAgreement = contractAgreement;
        }
      }
       ```
      ```html
      <!-- 스프링 폼 태그 -->
      <form:checkbox path="contractAgreement" label="위의 약관에 동의합니다."/>
      
      <!-- HTML 태그 변환 후 -->
      <!-- checkbox가 checked 되지 않았더라도 기본 값을 전달하고 싶은 경우 아래처럼 input[type="hidden"] 태그를 사용한다. -->
      <input type="hidden" name="_contractAgreement" value="on"/>
      <input type="checkbox" name="contractAgreement" id="contractAgreement1" value="true"/>
      <label for="contractAgreement1">위의 약관에 동의합니다.</label>
      ```
4. radio 타입의 \<input\> 태그를 위한 커스텀 태그
    - \<form:radiobuttons\> : 커맨드 객체의 특정 프로퍼티와 관련된 radio 타입의 \<input\> 태그 목록을 생성한다. `items` 속성을 이용하여 값으로 사용할 콜렉션 객체를
      전달받는다. 또한 `path` 속성을 이용하여 값을 바인딩할 커맨드 객체의 프로퍼티를 지정한다.
    - \<form:radiobutton\> : 커맨드 객체의 특정 프로퍼티와 관련된 radio 타입의 \<input\> 태그 한 개를 생성한다.
      ```html
      <!-- 스프링 폼 태그 -->
      <form:label for="program">
        <form:radiobuttons path="program" items="${programs}"/>
      </form:label>
      
      <!-- HTML 변환 후 -->
      <input type="radio" name="program" id="program1" value="c"/>
      <label for="program1">C 언어</label>
      <input type="radio" name="program" id="program2" value="java"/>
      <label for="program2">Java 언어</label>
      ```
5. \<textarea\> 태그를 위한 커스텀 태그
    - \<form:textarea\> : 여러 줄을 입력받아야 하는 경우
       ```html
      <!-- 스프링 폼 태그 -->
      <form:label path="memo">메모</form:label>
      <form:textarea path="memo" cols="50" rows="5"/>
      
      <!-- HTML 변환 후 -->
      <label for="memo">메모</label>
      <textarea name="memo" id="memo" cols="50" rows="5"></textarea>
       ```
6. 에러 관련 커스텀 태그
    - Errors 객체 또는 BindingResult 객체를 이용해서 에러 정보를 추가한 경우 사용할 수 있다.
    - \<form:errors\> : `path` 속성을 이용해서 커맨드 객체의 특정 프로퍼티와 관련된 `한 개 이상`의 에러 메시지를 출력할 수 있다.
      ```java
      public class MemberValidator implements Validator {
        @Override
        public void validate(Object target, Errors errors) {
            ValidationUtils.rejectIfEmpty(errors, "userId", "아이디를 입력해 주세요.");
        } 
      }
      ```
       ```html
      <form:form commandName="memberValidator">
        <form:label path="userId">아이디</form:label>
        <form:input path="userId"/>
        <form:errors path="userId"/>
      </form:form> 
       ```
    - \<form:errors\> 태그의 element, delimiter 속성
        1) element : 각 에러 메시지를 출력할 때 사용할 HTML 태그를 지정한다. 기본 값은 `<span>`이다.
        2) delimiter : 각 에러 메시지를 구분할 때 사용할 HTML 태그를 지정한다. 기본 값은 `<br/>`이다.