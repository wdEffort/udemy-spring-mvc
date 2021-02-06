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