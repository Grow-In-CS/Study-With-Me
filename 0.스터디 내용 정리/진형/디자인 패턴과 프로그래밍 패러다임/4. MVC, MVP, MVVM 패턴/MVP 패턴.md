# MVP 패턴 (Model-View-Presenter)

> MVP 패턴은 MVC의 Controller 대신 Presenter를 도입하여 V

---

![MVP](https://blog.kakaocdn.net/dna/bwH0mz/btsON301frN/AAAAAAAAAAAAAAAAAAAAAHOCEuPLKDDiSj1EBAgxwU4VjlS0uGUNDeveWvp5XFvW/img.png?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1750658399&allow_ip=&allow_referer=&signature=stq8MduL1dQzbxCvuAHX5oY9yaM%3D)

## 1. 구성 요소
- **Model**: 애플리케이션의 데이터와 비즈니스 로직  
- **View**: 사용자에게 보여지는 UI, 사용자 입력을 Presenter에 전달  
- **Presenter**: View와 Model을 연결, 비즈니스 로직 호출 및 결과를 View에 전달  

## 2. 동작 흐름
1. 사용자가 View에서 액션 수행  
2. View가 Presenter에 요청  
3. Presenter가 Model에서 데이터 처리  
4. Model이 처리 결과 반환  
5. Presenter가 View에 결과 전달  
6. View가 화면 갱신  

![View](https://blog.kakaocdn.net/dna/KrCC8/btsOMTrbLzu/AAAAAAAAAAAAAAAAAAAAAGLW_I-72DeUod1Q1t926fjePVYWJd89vpt4XzoMsEFq/img.png?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1750658399&allow_ip=&allow_referer=&signature=%2BJxHUO%2FNqNDEpfpvx8Q90x27BLw%3D)
- onCreate
  - 화면이 생성될 때 뷰 바인딩 설정
  -  버튼 클릭 리스너에서 직접 로직을 처리하지 않고, 모두 mainPresenter에게 위임 (update, reset 메서드)
- updateView
  - MainContract.View 인터페이스가 정의한 메서드
  - Presenter가 Model을 조작한 후 view.updateView(newModel) 식으로 호출
  - 화면의 TextView에 Model 객체(예: MainModel(data="B", count=2))를 문자열로 출력

![Presenter](https://blog.kakaocdn.net/dna/xi82Q/btsON7Wxbqk/AAAAAAAAAAAAAAAAAAAAABEegEXSGOHBDdAMzAs0MmktIMhYNVmwR7oe1IlPa9Ce/img.png?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1750658399&allow_ip=&allow_referer=&signature=J3GNiIpcT3KAi8NfUE1JL8A4ggg%3D)
- Presenter는 View(화면)와 Model(데이터)을 중재하는 역할
- View가 버튼 클릭 같은 사용자 이벤트를 넘기면,
- Presenter가 Model을 조작하고
- 그 결과를 다시 View에 updateView(...) 형태로 콜백해 줌으로써
- 완벽한 관심사 분리(UI로직 ↔ 비즈니스 로직)가 이루어집니다.

![Model](https://blog.kakaocdn.net/dna/m0LY6/btsONBwUqkP/AAAAAAAAAAAAAAAAAAAAAA3wbB6XrQ0SrWBCMLmUcbmQEKVh6SytpTdI0VYrFAjf/img.png?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1750658399&allow_ip=&allow_referer=&signature=w1eSGL9S%2BsnkMIcvqbtSPTum5ik%3D)
1.	data class 선언
   - data class 키워드를 사용해 name 과 value 두 프로퍼티를 정의합니다.
   - data class 는 equals(), hashCode(), toString(), copy() 등을 자동 생성해 줍니다.
2.	프로퍼티(var name, var value)
   - var 로 선언되어 런타임에 변경 가능(mutable)하며, Presenter가 update() 로 값을 갱신할 수 있습니다.
3.	update(name: String, value: Int)
   - 전달받은 새 name 과 value 로 내부 상태를 변경합니다.
   - 보통 Presenter 쪽에서 사용자 입력 등을 받아 모델에 반영할 때 호출합니다.
4.	reset()
   - 모델을 초기 상태로 되돌리는 메서드입니다.
     - 예시에선 빈 문자열과 0으로 재설정하므로, “리셋” 버튼 클릭 시 화면이 초기 화면처럼 보이도록 합니다.

## 3. 특징
- Presenter가 View와 Model의 인스턴스를 보유하며 1:1 관계  
- View는 Presenter를 통해서만 데이터 수신, Model 직접 접근 금지  
- Presenter 단위 테스트가 가능하여 테스트 용이  

## 4. 장점
- View와 Model 간 결합도 감소  
- 책임이 명확하게 분리되어 유지보수 용이  
- Presenter만 모의(Mock)하면 단위 테스트가 쉬움  

## 5. 단점
- View-Presenter 간의 결합 강도 증가  
- Presenter 수 증가 시 관리 복잡도 상승  
