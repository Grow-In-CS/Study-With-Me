
# MVC 패턴

> MVC 패턴은 사용자 인터페이스(UI)와 비즈니스 로직, 데이터 관리를 분리함으로써 관심사의 분리(SoC)를 실현하는 구조입니다.

---

## 1. 구성 요소
- **Model**  
  애플리케이션의 핵심 데이터와 비즈니스 로직을 담당
- **View**  
  Model 데이터를 사용자에게 시각적으로 표시  
- **Controller**  
  사용자 요청을 받아 Model과 View를 연결·제어

![MVC](https://velog.velcdn.com/images/langoustine/post/7c433d37-6e00-4444-8ad4-d37e06d8cf27/image.png)

## 2. 기본 흐름
1. **User → Controller**: 사용자의 URL 접속 또는 버튼 클릭 이벤트 수신  
2. **Controller → Model**: 서비스(비즈니스 로직) 호출 및 결과 저장  
3. **Controller → View**: Model 데이터를 View에 바인딩하여 화면 갱신  
4. **View → User**: 렌더링된 화면을 사용자에게 전달  

## 2-1 웹에서의 흐름

1. User: 사용자가 웹 사이트에 접속한다.
2. Manipulates: Controller는 사용자가 요청한 웹 페이지를 보여주기 위해 Model을 호출한다.
3. Updates: Model은 비즈니스 로직을 통해 DB 및 파일과 같은 데이터를 제어한 후 결과를 반환한다. 이후 Controller는 Model에게 반환받은 결과를 View에 반영한다.
4. Sees: 데이터를 받아온 View가 사용자에게 웹 페이지를 출력하여 보여준다.

```
1. Model (모델)
- Model은 소프트웨어나 애플리케이션에서 정보 및 데이터 부분을 의미한다. Controller 에게 받은 데이터를 조작하는 역할을 수행한다고 볼 수 있다. 즉 데이터와 관련된 부분을 담당하고 값과 기능을 가지는 객체.

* 모델의 규칙
  1. 사용자가 편집하길 원하는 모든 데이터를 가지고 있어야 한다
  2. View나 Controller에 대해서 어떤 정보도 알지 말아야 한다.
  3. 변경이 일어나면, 변경 통지에 대한 처리방법을 구현해야만 한다.

2. View(뷰)
- View는 입력값이나 체크박스 등과 같은 사용자 인터페이스 요소를 나타낸다. 이는 Controller에게 받은 Model의 데이터를 사용자에게 시각적으로 보여주기 위한 역할을 수행함. 사용자에게 보여지는 화면이라고 볼 수 있다.

* View가 가지는 규칙
  1. Model이 가지고 있는 정보를 따로 저장해서는 안된다
  2. Model이나 Controller를 알고 있을 필요가 없다
  3. 변경이 일어나면 변경통지에 대한 처리방법을 구현해야만 한다

3. Controller(컨트롤러)
- Controller는 Model과 View 사이에서 데이터 흐름을 제어한다. 사용자가 접근한 URL에 따라 요청을 파악하고 URL에 적절한 Method를 호출하여 Service 에서 비즈니스 로직을 처리함.
이후 결과를 Model에 저장하여 View에게 전달하는 역할을 수행한다. 결국 Controller는 Model과 View의 역할을 분리하는 중요한 요소이다.

* 컨트롤러의 규칙
  1. Model이나 View에 대해서 알고 있어야 한다.
  2. Model이나 View의 변경을 모니터링 해야 한다.

* 요약
 - Model과 View는 각각의 역할에 충실하고, Controller는 Model과 View의 흐름을 제어하기 위한 역할이 더욱 중요함.
```

## 3. MVC의 규칙
1.	Model (모델)
•	오로지 데이터와 비즈니스 로직만 담당
•	View나 Controller 코드는 전혀 몰라야 함
2.	View (뷰)
•	화면 표시와 사용자 입력 수집만 담당
•	모델을 읽어 보여줄 수 있지만, 수정 로직은 직접 수행하지 않고
•	모든 입력 이벤트는 Controller에게 넘겨야 함
3.	Controller (컨트롤러)
•	사용자의 액션을 받아 모델에 명령을 내리고(데이터 변경/조회)
•	그 결과를 View에 전달해 화면을 갱신
•	모델과 뷰를 중재(조정)하는 역할

## 4. 장점
- **관심사의 분리**: UI 변경과 도메인 로직 변경의 영향 최소화
- **유지보수·테스트 용이**: Model, View, Controller 단위 테스트 가능
- **재사용성**: 동일 Model에 여러 View 연결 또는 다른 Model 재활용
- **협업 효율**: 역할 분리로 개발자 간 충돌 감소

## 5. 한계 및 보완

![Massive-View-Controller](https://velog.velcdn.com/images/langoustine/post/4c0ab3aa-2ecb-40d2-b963-a4621ae9b868/image.png)

- **Model과 View의 의존성을 완전히 분리시킬 수 없다.**
일반적으로 View는 Controller와 연결되어 화면을 구성하게 된다. 
그렇기에 자연스럽게 Controller는 여러 개의 View를 가질 수 있게 된다.
이 때, Model은 Controller를 통해서 View와 연결된다. 
즉, Controller에 의해 하나의 View에 연결되는 Model도 여러 개가 될 수 있다는 말이다. 
결국 복잡한 구조의 애플리케이션일수록 하나의 Controller에 다수의 View와 Model이 복잡하게 연결되어 서로간의 의존성이 커지는 상황이 발생할 수 있다. 
하나의 Controller에 하나의 View와 하나의 Model만 연결하면 되는거 아니야? 라고 말할수도 있겠지만,
요구사항에 따라서 Controller-View-Model의 비중을 1:1:1로 둘 수 없는 상황도 고려해야 하기 때문에 골치아픈 문제로 이어질 수도 있을 것이라고 느껴졌다.

- **Massive-View-Controller 문제**: Controller에 로직이 집중되어 비대화 가능  
- **Model–View 의존성 완전 분리 어려움**  
- 보완 패턴: MVP, MVVM, Flux/Redux 등에서 Controller 책임 재분배  

