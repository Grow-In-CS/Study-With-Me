# HTTP 버전별 변천사와 동작원리

## HTTP 정의

> HTTP(HyperText Transfer Protocol)는 World Wide Web 상에서 정보를 주고 받을 수 있는 프로토콜이다.
 
위 문장을 해석해 보면 3 가지 요소를 알 수 있습니다.

### HyperText

하이퍼텍스트란 다른 문서에 대한 참조를 통해 독자가 한 문서에서 다른 문서로 즉시 접근할 수 있는 텍스트를 말합니다. HTML 상에서 < a > 라는 요소를 사용할 때, 저희는 다른 문서로 이동할 수 있는데, 이렇게 이동할 수 있는 텍스트를 하이퍼텍스트라고 말합니다.

![HP](https://velog.velcdn.com/images/yesbb/post/703a1054-7c41-4cf4-9e14-a024e1423276/image.jpeg)

사진과 같이, 하이퍼텍스트를 통해서 다양한 문서사이를 왔다갔다 할 수 있습니다. 기존의 문서는 위에서 아래로 순차적이면서 서열적인 구조를 갖추고 있었지만, 하이퍼텍스트는 문서 내에 삽입된 링크에 의해서 그 차례가 바뀌면서 임의적이고 나열적인 구조를 가지게 됩니다.

보통 이런 하이퍼텍스트를 사용하고 있는 대표적인 기술이 HTML입니다. HTTP 초창기에는 HTML만 지원가능했지만, 최근에는 거의 모든 파일 형식을 HTTP를 통해서 지원할 수 있게 되었습니다.

### Protocol

> 프로토콜은 컴퓨터 내부에서, 또는 컴퓨터 사이에서 데이터의 교환 방식을 정의하는 규칙 체계입니다.

프로토콜은 언어라는 규칙이라고 생각하면 됩니다.

### World Wide Web

WWW는 간단히 말하면, 컴퓨터를 통해 정보를 공유하는 정보공간 입니다. 당연히 이것의 탄생 또한 정보 공유와 얽혀있습니다.

1989년 스위스와 프랑스 사이에는 CERN(유럽 입자 물리 연구소)라는 곳에서 내부 망에서 정보 교환을 위해 만들어졌습니다.

## HTTP/0.9 - 원 라인 프로토콜

초창기의 HTTP로 출시 당시에 버전이 붙어있지는 않았지만 추후 추가되었습니다.

이용 가능한 메서드는 오직 GET 하나만 있었습니다.

연결 또한 응답을 주고나면, 곧바로 연결이 제거되었었습니다. 헤더도 존재하지 않았고, 상태코드, 버저닝 같은것들 또한 존재하지 않았습니다. 헤더가 존재하지 않았다는 것은 오직 HTML 파일만 전송만 가능했습니다.

### HTTP/1.0 - 확장성 만들기

HTTP/1.0에선 몇가지 추가적인 변화가 생겼습니다.

1. 버저닝
   ```html
	 GET /mypage.html HTTP/1.0 
   ```
2. 상태코드
   ```html
	  200 OK
	  Date : Tue, 15 nov 1994 08:12:31 GMT
   ```
3. 헤더: 헤더를 통해 메타데이터(CSS, 스크립트 미디어 파일)를 전송할 수 있게 되었습니다.
4. 메서드 확장: 1.0에서는 GET, HEAD, POST를 지원합니다.

### HTTP/1.0의 한계

0.9버전과 마찬가지로 response가 보내지고 나면, 연결은 제거되었습니다.

![HTTP/1.0의 한계](https://velog.velcdn.com/images/yesbb/post/d6dc7b63-ac19-4766-89c6-8d5e19f7a009/image.png)

매 요청와 응답 사이에 단 하나의 연결만 존재했습니다. 응답이 끝나면 연결이 사라지고, 다음번 요청을 하기 위해서는 다시 3-way-handshake를 통해서 TCP 커넥션을 생성해주어야 했습니다.

### HTTP/1.1 - 표준 프로토콜

HTTP/1.1버전에서 생긴 변화입니다.

- 커넥션의 재사용 : 이전에 사용된 커넥션을 다시 열어 시간을 절약하게 되었습니다. (keep-alive)
- 파이프라이닝 : 덕분에 첫번째 요청의 응답이 완전히 전송되기 전에 두번째 요청 전송을 가능하게 했습니다. 이 덕분에 커뮤니테이션 latency 낮출 수 있었습니다. (latency : 일반적으로 요청과 응답 사이의 시간을 말한다.)
- 청크 가능한 응답 : 뭉텅이진 응답을 나누어 보낼 수 있게 되었습니다.
- 캐시 제어 메커니즘 : 캐시 제어에 대한 세심한 컨트롤이 가능해졌습니다.
- 언어, 인코딩 혹은 타입을 포함한 컨텐츠 협상 : 클라이언트와 서버로 하여금 교환하기에 가장 적합한 컨텐츠를 보여줄 수 있게 되었습니다.
- Host 헤더를 지원 : 덕분에 동일 IP주소에서 다른 도메인을 호스트하는 기능이 서버 코로케이션을 가능하게 했습니다.
- 다양한 메서드들을 지원 : GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS

![HTTP/1.1 - 표준 프로토콜](https://velog.velcdn.com/images/yesbb/post/ff505029-6534-4743-b7c0-8941530013db/image.png)

### HTTP/1.1이 가진 한계

#### 1. HOL Blocking (Head Of Line Blocking)

HTTP/1.1에서 keep-alive기능이 도입되고, 파이프라이닝을 통해 다수의 요청을 연속적으로 보낼 수 있게 했습니다. 성능이 크게 향상되었습니다. 그러나 이 파이프라이닝에도 한계가 존재했습니다. 앞 번에 보냈던 요청에서 병목이 생기면, 이후에 들어왔던 요청에 대한 작업이 마무리가 되었어도 응답으로 보내지 못하는 현상이 생긴 것입니다. 이렇게 이전에 있었던 요청에 대해서 이슈가 생김으로 이후의 응답들에 병목이 생기는 현상을 Head Of Line Blocking이라고 부릅니다. 때문에 이런 문제를 해결하고자 사람들이 사용했던 방법이 있습니다. Multiple Connection입니다.

Multiple connection이란 여러 개의 TCP 커넥션을 생성하는 것입니다. 이렇게 여러 커넥션을 생성하게 되면 특정 커넥션에서 이슈가 생긴다할지라도 다른 커넥션에선 병목현상이 생기지 않습니다. 그러면 이를 통해 모든 문제가 해결된 것일까요? 그렇지 않습니다. 이 Multiple Connection도 한계가 있었으니, 그 수가 늘어날 수록 서버와 클라이언트 사이에 많은 오버헤드가 생겨나게 되고, 대역폭에 대한 경쟁이 심해진다는 단점이 있었습니다

#### 2. 무거워진 헤더

웹이 발전하면서 웹을 통해서 전달할 수 있는 수 없이 많은 데이터가 생겨나게 되었습니다. 또한 필요로 하는 데이터도 많아지게 되었고, 그에 따라 요구되는 기능과 옵션들이 추가되었습니다. 이 말인즉 헤더에 많은 정보가 들어가게 되었다는 것입니다. 이 헤더의 무게가 무거워짐으로 인해서 네트워크의 성능을 저하시키는 측면이 있었습니다.

### HTTP/2 - 더 나은 성능을 위한 프로토콜

HTTP에서는 몇가지 중대한 변화들이 있었습니다.

1. Binary Framing layer

	![Binary Framing layer](https://velog.velcdn.com/images/yesbb/post/10e23d9e-af40-4de2-8e1e-70caa4acac13/image.svg)

2. MultiPlexing

   ![HTTP/2](https://velog.velcdn.com/images/yesbb/post/08aab52c-0235-4af3-99f9-00c5ad42542c/image.gif)

	![HTTP/2](https://velog.velcdn.com/images/yesbb/post/dfaab1be-fae8-47f4-8cec-fc891c679582/image.png)

	하나의 커넥션 안에서 순서가 섞여 데이터가 전송되어도 스트림에 대한 식별자를 가지고 있기 때문에 괜찮습니다.

3. Stream prioritization: 프로토콜 내의 우선순위 선정
4. Compress Header: HPACK이라는 압축 기술로 헤더 압축
5. Server Push: 클라이언트에서 특정 리소스에 대한 요청이 없어도, 여러 개의 리소스를 같이 보낼 수 있게 합니다.

HTTP/2.0의 가장 핵심되는 포인트는 요청을 보내고나서 응답을 받을 때까지 기다리지 않아도 된다는 것입니다. 요청을 보내고 받는 것은 순서에 상관없이 받을 수 있다는 점이 바로 HTTP/2.0의 핵심입니다.

### HTTP/2.0 의 한계

#### TCP레벨에서의 HOLB(Head Of Line Blocking)

![HOLB](https://velog.velcdn.com/images/yesbb/post/6155eb4a-6d65-49c3-9c2e-603872b1d286/image.png)

이 중 맨 앞에 있던 stream5/style.css - headers가 손상되었으면, 클라이언트는 이를 서버에 알리고 서버는 해당 stream5-header를 재전송합니다.

![HOLB](https://velog.velcdn.com/images/yesbb/post/84757ec8-5161-484c-a343-8e6f44a12d8a/image.png)

stream5가 제일 마지막 순번으로 전송되는 모습을 볼 수 있습니다. 만약 또 다른 패킷 손상이 일어나지 않았다면 stream7과 stream9는 클라이언트에 도착해서 흩어져있던 프레임들이 재구성되어 있을 것입니다. 하지만, 문제는 TCP는 순서를 보장하는 프로토콜이라는 것입니다. 응답 데이터들은 queue 되어야 하기 때문에 당장에 사용할 수가 없는 한계가 있습니다.

H2는 Multiplexing을 통해서 HTTP 레벨에서의 Blocking을 해결했습니다. 한가지 리소스에서 발생한 지연이, 다른 리소스에 영향이 미치지 않도록 만들어주었습니다. 하지만, TCP 레벨에서의 Blocking은 여전히 남아있었습니다. 하나의 스트림에서 발생한 패킷 손상이 다른 스트림에도 영향을 미치는 한계점입니다.

### HTTP/3 - HTTP over QUIC

HTTP/3은 UDP에서 설계되어 TCP 프로토콜을 사용하는 HTTP의 구조적인 한계점을 개선할 수 있었습니다.

#### HTTP3의 개선점

1.빠른 연결 수립 (built-in security)

  ![빠른 연결 수립](https://velog.velcdn.com/images/yesbb/post/83be0200-e7a2-4323-a6a4-7598248a2b16/image.png)

  HTTP2의 한계점은 TCP의 느린 연결수립입니다. TCP에서는 3-Way-Handshake 이후에 보안 연결(TLS)을 진행합니다.
  하지만, HTTP3는 위의 그림처럼 QUIC에 TLS를 내장하고 있기 때문에 아래의 그립과 같이 빠른 연결 수립이 가능합니다.

  ![빠른 연결 수립](https://velog.velcdn.com/images/yesbb/post/4c9b0b0a-246b-4624-92a2-032257da1b05/image.jpeg)

  QUIC은 TLS1.3 보안 프로토콜의 기능들을 통합하고, 암호화 작업을 응용 계층에서 전송 계층으로 내려 연결 수립 속도를 높였습니다.

2.HOLB 문제가 해결된 멀티플렉싱

  ![멀티플렉싱](https://velog.velcdn.com/images/yesbb/post/89e45565-c5bb-4186-b118-dd951e45d736/image.png)

  > 1. packet1이 도착했습니다. byte range가 0-499이네요. 이 사실을 기억합니다.
  > 2. packet3이 도착했습니다. byte range가 750-1599이네요. 어, 중간에 450-749가 비었습니다. 문제가 있는 것 같아요. packet2를 재전송 요청합니다.
  > 3. packet2가 도착할 때까지 이후의 다른 패킷들은 대시 상태에 들어가게 됩니다. 만약 위의 예시에서 packet1과 packet3 담고 있는 데이터의 종류가 stream1과 관련된 데이터였고, packet2가 담고 있는 데이터의 종류가 stream2와 관련된 데이터였다고 해보겠습니다. 그렇다면 사실 packet2가 손실된 것은 packet1과 packet3의 데이터와는 큰 상관이 없습니다.

  QUIC은 이런 문제를 각각의 패킷에 stream id를 부여함으로써 해결합니다.

  > 1. packet 1이 도착했다.
  > 2. stream id를 확인하니 1번이다. 해당 스트림에 대한 byte range를 기억한다.
  > 3. packet 3이 도착했다.
  > 4. stream id를 확인해보니 1번 stream이다. 그러면 이전에 stream id의 byte range를 확인한다. 확인해보니 0-449다. 지금 받아온 byte range는 450-999다. byte range 사이의 어떤 gap도 존재하지 않는다. 정상이라고 처리한다.
  > 5. packet 4를 받았다.stream id를 확인해보니 2번 stream이다. 그러면 이전에 stream id 2번의 byte range를 확인한다. 어, 그런데 해당 데이터가 존재하지 않는다. 지금 받아온 byte range는 300-599인데, 0-299라는 gap이 존재한다. stream id 2 번에 대한 이전 패킷을 다시 요청해야겠다.
  > 6. stream 2번에 해당하는 이전 패킷을 요청한다. 다시 손실된 패킷을 받아오는 동안, packet 4번의 데이터는 보관된다. 그리고 나머지 상관없는 stream의 패킷에는 지연이 발생하지 않는다. 오로지 Stream 2번과 관련된 패킷에만 지연이 생긴다.

  위와 같은 원리를 통해서 http3에서는 TCP 차원에서 발생하던 HOLB의 문제를 해결합니다.

3.Connection ID: connection id(CID)는 연결에 대한 랜덤하고 고유한 식별자입니다. 해당 정보는 QUIC의 패킷 헤더에 붙어있습니다. 이 CID의 주된 기능은 전송계층 이하의 단계(TCP, IP..)에서 변경사항이 생긴다고 할지라도, 전송되는 패킷들이 잘못된 end point로 전달되지 않도록 보장합니다
4.UDP의 단점을 보완한 QUIC: UDP의 신뢰성, 보안 문제를 위의 위의 stream id와 TLS를 통해 해결했습니다.

## 정리

![HTTP2.0](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdna%2FnN1MG%2FbtrkIjrQxV2%2FAAAAAAAAAAAAAAAAAAAAADxnY5Fvrd2s8CeU2mEUmcLPvik6QPS8NSZtTxzzW1w7%2Fimg.jpg%3Fcredential%3DyqXZFxpELC7KVnFOS48ylbz2pIh7yKj8%26expires%3D1753973999%26allow_ip%3D%26allow_referer%3D%26signature%3D4Il%252B964JmibNkh87isgbxBtTa5g%253D)

위와 같은 그림을 통해서 HTTP/0.9 부터 HTTP/2.0까지의 변천사를 확인할 수 있습니다. 

## Reference

https://velog.io/@yesbb/HTTP3%EA%B9%8C%EC%A7%80-%EB%B2%84%EC%A0%84%EB%B3%84-%EB%B3%80%EC%B2%9C%EC%82%AC

https://gngsn.tistory.com/99
https://developer.mozilla.org/ko/docs/Web/HTTP/Guides/Evolution_of_HTTP
https://jaehyeon48.github.io/network/history-of-http/
https://brunch.co.kr/@swimjiy/39
