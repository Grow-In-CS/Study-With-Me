# ARP(Address Resolution Protocol)

ARP는 IP 네트워크에서 IP주소와 MAC 주소 간 매핑을 제공하는 네트워크 프로토콜 입니다.
IP 패킷이 실제로 네트워크 위에서 목적지까지 전달될 수 있도록 IP 주소를 MAC 주소와 매칭해줍니다.

## 동작 계층과 역할

- OSI 데이터 링크 계층과 네트워크 계층 사이에서 동작
- IP 패킷 목적지 주소로부터 목적지 PC의 MAC 주소를 알아냄
- LAN 내에서 통신 시에 MAC 주소가 필수적으로 필요함

## 작동 원리

1. ARP 캐시(테이블) 확인 
   - 데이터 전송을 위해 MAC 주소가 필요한 경우 OS 내의 ARP 캐시에서 해당 IP에 대한 MAC 주소가 있는지 확인합니다.
2. ARP 요청 브로드캐스트
   - 캐시에 없으면 LAN의 브로드 캐스트 주소로 ARP 요청을 보냅니다. 즉 네트워크에 속한 전체 노드에게 ARP 주소를 요청합니다.
3. ARP 응답 유니캐스트
   - 대상 IP를 가진 PC가 자신의 MAC 주소와 IP 주소를 포함해서, ARP Reply 패킷을 유니캐스트로 전달합니다.
4. ARP 캐시 저장
   - 응답을 받은 송신자는 IP-MAC 주소 쌍을 ARP 캐시에 저장하며, 사용합니다.
5. 프레임 데이터 전송
   - 송신자는 위의 ARP 캐시를 통해서 목적지 MAC 주소를 알아내 데이터를 전송합니다.

![ARP작동원리](https://velog.velcdn.com/images/xeropise1/post/4861252b-e0e7-4b65-a93c-a79d1ac89db7/image.webp)

> ARP 캐시는 동적으로 관리되며, 일정 시간 후 만료되어 재확인이 필요합니다.

## 패킷 구조 및 구성

- Hardware Type:	이더넷일 경우 1
- Protocol Type:	IP(v4)이면 0x0800
- Hardware Address Len:	MAC 주소 길이(이더넷: 6바이트)
- Protocol Address Len:	IP 주소 길이(IPv4: 4바이트)
- Operation	1: Request, 2: Reply
- Sender MAC:	요청자의 MAC 주소
- Sender IP:	요청자의 IP 주소
- Target MAC:	대상 MAC 주소(요청 시 빈값)
- Target IP:	대상 IP 주소(찾고자 하는 IP)

## Reference
https://velog.io/@xeropise1/ARP%EB%9E%80