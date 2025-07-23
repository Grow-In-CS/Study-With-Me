# IP 주소 체계

## IPv4

![IPv4](https://www.cloudns.net/blog/wp-content/uploads/2023/03/IPv4-Address-Format.png)

IPv4는 8비트 씩 총 32비트로 주소를 구분합니다.

### IPv4 주소 고갈 문제

IPv4는 32비트 주소 체계로 한계가 있습니다. 이러한 문제를 해결하기 위해 IPv6를 도입했지만, 아직도 많은 기기가 IPv4를 사용하고 있습니다.

- IPv6 도입: 128비트의 주소 체계로 사실상 무한대의 주소를 제공합니다.
- NAT(Network Address Translation): 여러 내부 장치에 사설 IP를 부여하고, 외부 통신시 공인 IP로 변경
- Subnetting: 큰 네트워크를 작은 네트워크로 분할 (CIDR)

## IPv6

![IPv4vsIPv6](https://www.cloudns.net/blog/wp-content/uploads/2023/11/IPv6.png)

128비트로 이루어져 16진수 8개 블록을 콜론(:)으로 구분합니다.

전송 방법
- Unicast: 단일 전송
- Anycast: 최적 경로 1곳 전송
- Multicast: 여러 그룹 동시 전송

이외에도 IPSec, Header 단순화 등 IPv4 보다 나은 장점이 있습니다.

## DHCP (Dynamic Host Configuration Protocol)

네트워크에 새로운 기기가 연결되었을 때에 기본적으로 IP를 자동 할당해주는 프로토콜입니다.

- 자동 IP 주소 할당: 네트워크 내의 클라이언트들에게 IP 주소를 자동으로 할당합니다.
- 네트워크 설정 정보 제공 (서브넷 마스크, 게이트웨어, DNS 주소, 등)
- 중복 IP 주소 방지: 이미 할당된 IP 주소를 다른 장치에 할당하지 않도록 관리합니다.
- 임대 시간 관리: IP 주소를 일정 기간 동안 임대해 주고, 만료되면 회수하여 다른 장치에 할당할 수 있도록 합니다.

다른 기능으로는 고정 IP 할당 기능이 있습니다.

## NAT (Network Address Translation)

####  NAT 동작 방식

1. 내부 PC -> 외부로 요청
2. 라우터(NAT)가 변환
    - 내부 출발지 IP를 공인 IP 변경하고 송신포트를 기록 해둡니다. (NAT 세션 테이블)
3. 외부 서버에서 응답
4. 라우터가 내부로 라우팅
    - 라우터가 NAT 테이블을 참조하여 해당 송신포트로 온 패킷이 어떤 컴퓨터와 연결된 세션인지 확인 후에 내부 컴퓨터로 전달합니다.

## 요약

- IPv4: 주소 한정, 고갈 문제 존재, 현재도 광범위하게 사용됨
- IPv6: 사실상 무한대 주소, 미래 표준, 자동설정 및 보안 강화 
- DHCP: IP/네트워크 설정의 자동 할당 담당, 운영/관리에 필수
- NAT: 사설망 장치 다수를 공인IP 하나로 인터넷 연결, 보안/주소 효율성 증진

## Reference

https://www.cloudns.net/blog/what-is-ipv4-everything-you-need-to-know/
https://kmicety1.tistory.com/entry/CS%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-IP-%EC%A3%BC%EC%86%8C-%EC%B2%B4%EA%B3%84-IPv4-IPv6-Subnetting-CIDR
