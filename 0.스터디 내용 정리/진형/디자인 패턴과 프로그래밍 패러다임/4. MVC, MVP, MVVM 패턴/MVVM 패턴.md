# MVVM 패턴 (Model-View-ViewModel)

MVVM 패턴은 Model과 View 사이에 ViewModel을 두어 UI 로직과 상태를 관리하며, Data Binding을 통해 View와 ViewModel 간 의존성을 최소화하는 디자인 패턴입니다.

![MVVM](https://blog.kakaocdn.net/dna/ccerIU/btsOMbsrYei/AAAAAAAAAAAAAAAAAAAAAF5pUIgwCzjWZw8cEFTDqM6YLybpNdOmqv3VH8tQ4wru/img.png?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1750665599&allow_ip=&allow_referer=&signature=jnuExO0FxnbaOpfIId%2FVCxOsDeU%3D)

## 1. 구성 요소
- **Model**: 애플리케이션의 데이터와 비즈니스 로직  
- **View**: 사용자에게 보여지는 UI, 입력 이벤트를 ViewModel에 전달  
- **ViewModel**: View를 위한 데이터와 상태를 보유하고, Model과 View 간 중개자 역할 수행  

## 2. 동작 흐름

![view](https://blog.kakaocdn.net/dna/vxGSk/btsON6cqs6o/AAAAAAAAAAAAAAAAAAAAAMotfk2dpR4gRsaP_8acUAl26ypaIz-Hh88DGvYZNXlo/img.png?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1750665599&allow_ip=&allow_referer=&signature=deMU3rE1%2FcoMSos6Hq9Hlpar0S4%3D)
- 

![viewModel](https://blog.kakaocdn.net/dna/bav2Gx/btsOL39mPXp/AAAAAAAAAAAAAAAAAAAAAHUhjhfvSYQ-EnT7jqQHckD2iWd7KZbwRar8T57tLgde/img.png?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1750665599&allow_ip=&allow_referer=&signature=kNI%2Fv8Up7A0QaPZpSf%2FY72BXtGk%3D)
-

![mainModel](https://blog.kakaocdn.net/dna/u1V27/btsOMS670R3/AAAAAAAAAAAAAAAAAAAAAH23pO4TZ0x26zqOYed6dxSw_GjHFl5EBb0cNXGM0e3_/img.png?credential=yqXZFxpELC7KVnFOS48ylbz2pIh7yKj8&expires=1750665599&allow_ip=&allow_referer=&signature=FxfrnmZEQYNpNmlS7n0Fa58YDJA%3D)
-

1. 사용자가 View에서 액션 수행  
2. View가 Command 또는 이벤트를 통해 ViewModel에 전달  
3. ViewModel이 Model에 데이터 요청  
4. Model이 처리된 데이터를 반환  
5. ViewModel이 받은 데이터를 가공·저장  
6. View는 Data Binding을 통해 ViewModel의 변경을 자동으로 반영하여 화면 갱신  

## 3. 특징
- **Data Binding**: View와 ViewModel 간 양방향 데이터 바인딩 지원  
- **Command 패턴**: View의 사용자 이벤트를 ViewModel로 캡슐화  
- ViewModel과 View는 유연한 1:N 관계 가능  
- ViewModel 단위 테스트가 용이  

## 4. 장점
- View와 Model 간 결합도 최소화  
- UI 로직(ViewModel)을 독립적으로 테스트 가능  
- 코드 모듈화 및 재사용성 향상  
- Data Binding으로 코드 작성량 감소  

## 5. 단점
- Data Binding 구현 복잡성 증가  
- ViewModel 설계 난이도 높음  
- 프레임워크 의존성 및 디버깅 어려움  
