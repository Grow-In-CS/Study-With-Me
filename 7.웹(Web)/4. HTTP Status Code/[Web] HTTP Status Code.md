# HTTP Status Code

HTTP 상태 코드는 서버가 클라이언트의 요청을 어떻게 처리했는지 나타내는 3자리 숫자입니다.

이 코드는 응답 메시지에서 확인할 수 있으며, 첫 번째 숫자에 따라 아래의 5가지 주요 그룹으로 분류됩니다.

<br/>

## 1XX: Informational (정보 제공)

- 클라이언트의 요청을 받았으며 작업을 계속 진행 중임을 알리는 임시적인 응답입니다.
- HTTP/1.1 버전부터 사용되며, 응답 본문 없이 상태 라인과 헤더만 포함됩니다.
- 주요 코드:
  - 100 (Continue): 요청을 계속 진행해도 됨
  - 101 (Switching Protocols): 프로토콜 전환
  - 102 (Processing): 서버가 요청을 처리 중

<br/>

## 2XX: Success (성공)

- 클라이언트의 요청이 서버에서 성공적으로 처리됨을 의미합니다.
- 주요 코드:
  - 200 (OK): 정상 처리
  - 201 (Created): 새로운 리소스가 생성됨
  - 202 (Accepted): 요청을 접수했으나 아직 완료되지 않음
  - 204 (No Content): 응답 본문이 없음(예: DELETE 결과)

<br/>

## 3XX: Redirection (리다이렉션)

- 요청을 완료하기 위해 추가 동작(다른 URI로 재요청 등)이 필요함을 의미합니다.
- 주요 코드:
  - 301 (Moved Permanently): 영구적으로 URI가 변경됨
  - 302 (Found): 임시적으로 URI가 변경됨
  - 303 (See Other): GET 방식으로 다른 URI 요청
  - 304 (Not Modified): 수정되지 않음(캐시 용도)
  - 307 (Temporary Redirect): 임시 리다이렉션

<br/>

## 4XX: Client Error (클라이언트 에러)

- 요청 자체가 잘못되었거나, 리소스 접근 권한 등의 문제가 있을 때 사용됩니다.
- 주요 코드:
  - 400 (Bad Request): 부적절한 요청
  - 401 (Unauthorized): 인증 필요
  - 403 (Forbidden): 접근 금지
  - 404 (Not Found): 리소스를 찾을 수 없음
  - 405 (Method Not Allowed): 허용되지 않은 HTTP 메서드

<br/>

## 5XX: Server Error (서버 에러)

- 서버 내부에서 문제가 발생해 정상적인 요청 처리가 불가능할 때 사용됩니다.
- 주요 코드:
  - 500 (Internal Server Error): 서버의 일반적인 오류
  - 501 (Not Implemented): 미구현 기능
  - 502 (Bad Gateway): 게이트웨이 오류
  - 503 (Service Unavailable): 서비스 불가(과부하, 점검 등)
  - 504 (Gateway Timeout): 게이트웨이 시간 초과

<br/>
<br/>

> 참고
>
> - https://hongong.hanbit.co.kr/http-%EC%83%81%ED%83%9C-%EC%BD%94%EB%93%9C-%ED%91%9C-1xx-5xx-%EC%A0%84%EC%B2%B4-%EC%9A%94%EC%95%BD-%EC%A0%95%EB%A6%AC/
