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

> 최초의 클라이언트의 요청이 들어오면 컨트롤러로 진입을 하고 요청에 대한 작업 처리를 한 후에 뷰에 데이터를 전달한다.

1. @Controller 어노테이션을 이용한 클래스 생성
2. @RequestMapping 어노테이션을 이용한 요청 경로 매핑을 한다.
3. 요청 처리 메소드를 구현한다.
4. 뷰를 리턴한다.

---

## ModelAndView 객체를 이용한 데이터 전달

1. ModelAndView 객체를 생성한다.
2. Model 객체에 데이터를 추가 시킨다.
    - addObject() 메소드 이용
3. View 이름을 설정한다.
    - setViewName() 메소드 이용

---

## 커맨드(Command) 객체를 이용한 데이터 전달

1. 커맨드 객체란 HttpServletRequest를 통해 들어온 요청 파라미터들을 특정 클래스의 Setter 메소드를 이용하여 객체에 정의되어 있는 속성(Property, 멤버 변수)에 바인딩이 되는 객체를
   의미한다.
2. 커맨드 객체는 보통 `VO(Value Object)` 또는 `DTO(Data Transfer Object)`를 의미하며, HttpServletRequest로 받아오는 요청 파라미터의 key 값과 동일한 이름의
   속성들과 Setter 메소드를 가지고 있어야 한다.
3. 스프링이 내부적으로 HttpServletRequest와 커맨드 객체의 Setter 메소드를 이용하여 요청 파라미터의 값을 커맨드 객체 속성 값으로 자동으로 바인딩 시켜준다.
4. 커스텀 객체를 사용하면 요청을 처리하는 메소드의 인자로 HttpServletRequest 또는 @RequestParam 어노테이션을 사용하는 것보다 코드의 양이 줄고, 가독성도 좋아진다.

---

## @ModelAttribute 어노테이션 사용 방법

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

1. Validator
    - org.springframework.validation.Validator 인터페이스
    - 구현 메소드
        1) boolean supports(Class<?> arg0) : 구현한 Validator가 해당 커맨드 객체에 대한 값 검증을 지원하는지의 여부를 반환한다.
        2) void validate(Object target, Errors errors) : target 객체에 대한 검증을 실행하는 메소드이다. `target`은 커맨드 객체를 가르키며, `errors`는
           검증에 대한 문제가 있을 때 에러 정보를 저장하는 객체이다.

---

## 커맨드 객체에 검증 코드를 추가하는 방법

1. @RequestMapping 어노테이션 메소드에서 커맨드 객체 다음 파라미터로 `BindingResult` 타입이나 `Errors` 타입의 파라미터를 추가한다.
2. @RequestMapping 어노테이션 메소드에서 Validator 객체를 생성한 후 validate() 메소드를 호출한다.
    - 이때 커맨드 객체와 BindingResult 또는 Errors 타입의 파라미터를 전달한다.
3. Errors.hasErrors() 메소드를 이용하면 에러가 있는지 여부를 확인 할 수 있다.
    - 에러가 있는 경우 `TRUE`, 없는 경우 `FALSE`를 리턴한다.

---

## ValidationUtils 클래스

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