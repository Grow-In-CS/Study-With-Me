# DNS(Domain Name Server)

DNS란 도메인 이름과 IP 주소를 상호 변환해주는 시스템입니다.
사람이 IP 주소를 직접 기억하기 어렵기 때문에 DNS가 도메인 이름을 IP 주소로 매핑해주는 역할을 합니다.

> shell 명령어 nslookup (Name Server Lookup)

## DNS의 계층적 구조

DNS는 트리 구조로 관리되며 아래와 같은 계층으로 나누어집니다.

- Root 도메인: 최상위, 전 세계적으로 13개의 루트 서버가 존재
- TLD(Top Level Domain): com, net, org 등 국가 및 일반 도메인 구분
- 2차/3차 도메인: example.com, sub.example.com 등 하위 도메인

## DNS 동작 원리

1. 사용자가 브라우저에 도메인 입력
2. 로컬/OS 캐시 확인: 이미 IP 정보가 있으면 바로 사용
3. DNS Resolver에 쿼리 (계층적으로 네임서버 조회)
   - Resolver가 루트 서버에 질의 -> TLD 네임서버 정보 획득
   - TLD 네임 서버 조회 -> Authoritative 네임서버 정보 획득
   - Authoritative 네임 서버에서 해당 도메인의 IP 반환

## 주요 DNS 레코드 타입

DNS에서는 다양한 Record를 통해서 도메인 유형의 정보를 제공합니다.

| 레코드 | 설명 | 예시(값) |
| --- | --- | --- |
| A | 도메인을 IPv4 주소와 매핑 | example.com → 123.45.67.89 |
| AAAA | 도메인을 IPv6 주소와 매핑 | example.com → 2001:db8::1 |
| CNAME | 도메인 별칭(다른 도메인으로 매핑) | www.example.com → example.com |
| MX | 해당 도메인의 이메일 서버 정보 | example.com → mail.example.com |
| NS | DNS Name Server 정보 | example.com → ns1.dns.com |
| SOA | 도메인 관리자·영역 정보 | - |
| TXT | 부가 정보(예: 인증, SPF 등) | “v=spf1 include:…” |
| PTR | IP → 도메인(역방향 조회) | - |

> 서버의 도메인, 인증, 로드밸런싱 등 다양한 정보들이 DNS와 연관되어 있습니다.