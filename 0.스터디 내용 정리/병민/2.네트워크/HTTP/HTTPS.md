# HTTPS (HTTP + Secure)

HTTP는 기존의 HTTP 프로토콜에 SSL/TLS 프로토콜을 결합하여 보안 기능을 강화한 버전입니다.

## 목적

1. 데이터 암호화
   - 클라이언트와 서버 간에 주고 받는 모든 HTTP 데이터를 암호화합니다.
2. 데이터 무결성
   - 전송 중인 데이터가 변조되지 않았음을 보장합니다.
3. 서버 인증
	- 클라이언트가 접속하는 서버가 실제로 주장하는 서버임을 보장하며 피싱이나 패킷 스니핑을 방지합니다.

### SSL과 TLS

- SSL (Secure Socket Layer)
  - 1990년대 중반 넷스케이프(Netscape)에서 개발된 최초의 보안 프로토콜입니다. 여러 버전(SSL 2.0, SSL 3.0)이 존재했지만, 심각한 보안 취약점(예: POODLE 공격)이 발견되어 현재는 사용이 중단되었습니다.
- TLS (Transport Layer Security)
  - SSL 3.0을 기반으로 1999년부터 IETF(Internet Engineering Task Force)에서 표준화하여 발전시킨 프로토콜입니다. TLS는 SSL의 후속 버전으로, 더 강력한 암호화 알고리즘과 개선된 보안 기능을 제공합니다. 
  - 즉, 현재 HTTPS 통신에서 사용되는 것은 사실상 모두 TLS입니다.

> 현재에도 'SSL 인증서'라는 용어를 계속 사용하고 있지만. 사실은 TLS 프로토콜을 사용합니다.

### TLS와 CA인증 방식

서버는 디지털 인증서를 통해 HTTPS 환경을 만들 수 있습니다. 이러한 인증서를 발급하는 CA(Certificate Authority)가 있습니다.
CA는 신뢰가능한 제 3자 기관으로 서버의 신원(도메인 소유권)을 확인하고 이를 증명하는 디지털 인증서를 발급합니다.

#### CA 인증 절차

1. 서버 인증서 요청 -> CSR 생성 (서버 메타 데이터, 공개키, 도메인 이름)
2. CA로 CSR 전송
3. CA가 도메인 소유권 및 신원 확인
4. 서버에 인증서 설치
5. 클라이언트의 인증서 검증

### HTTPS 통신 과정

- TCP 연결 수립: 3-Way Handshake를 통해 TCP 연결을 설정합니다.
- TLS Handshake
   1. Client Hello: 클라이언트가 서버에게 지원하는 TLS 버전, 암호화 방식 목록을 전달합니다.
   2. Server Hello: 서버가 TLS버전과 암호화 방식을 선택하고, 인증서를 보냅니다.
   3. 클라이언트의 인증서 검증: 클라이언트가 인증서를 받아서 해당 CA에 신뢰성 검증을 요청합니다.
   4. 키 교환: 클라이언트와 서버가 공개키 암호화를 사용하여 앞으로 통신에서 사용할 대칭키(세션키)를 생성하고 공유합니다.
   5. Change Cipher Spec & Finished: 양측은 이제부터 모든 통신을 대칭 키로 암호화합니다.

![TLS-handshake](https://cf-assets.www.cloudflare.com/slt3lc6tev37/5aYOr5erfyNBq20X5djTco/3c859532c91f25d961b2884bf521c1eb/tls-ssl-handshake.png)

### TLS 1.2 와 TLS 1.3

> Forward Secrecy는 일회용 대칭키 라고 생각하면 됩니다.

재사용되는 세션 키는 Forward Secrecy를 지원하지 않아, 이전 세션 키가 유출되면 과거 통신 데이터까지 복호화될 수 있습니다.

TLS 1.3 사용하면 Forward Secrecy를 기본적으로 제공하며, 세션 재사용 시에도 안전합니다.

![TLS1.3](https://www.appviewx.com/wp-content/uploads/2022/03/Improved-Performance-and-Efficiency.png)



### P.S. Nginx 설정을 통한 HTTPS 환경 구축

HTTPS의 예시로 가져와봤습니다. 아래는 'Let's Encrypt' CA의 SSL 인증서를 사용하는 Nginx 서버의 예시입니다.

#### Docker Compose
```yaml
services:
  nginx: # Web Server
    container_name: nginx
    restart: unless-stopped
    image: nginx:1.27.2
    ports:
      - 80:80
      - 443:443
    volumes:
      - /opt/project/nginx/nginx.conf:/etc/nginx/nginx.conf
      - /opt/project/certbot/conf:/etc/letsencrypt
      - /opt/pro/certbot/www:/var/www/certbot

  certbot: # SSL Certificate Bot: crontab(1 month)
    depends_on:
      - nginx
    image: certbot/certbot
    container_name: certbot
    volumes:
      - /opt/project/certbot/conf:/etc/letsencrypt
      - /opt/pro/certbot/www:/var/www/certbot
    command: certonly --webroot -w /var/www/certbot --force-renewal --email "CA관리자이메일" -d "적용할서버도메인" --agree-tos && tail -f /dev/null
```

#### Nginx.conf
```shell
http {
    server_tokens off;
    charset utf-8;

    # Always Redirect To HTTPS
    # 80포트로 접속해도 443포트로 전환하도록 설정
    server {
        listen 80 default_server;
        server_name _;
        location ~ /.well-known/acme-challenge/ {
            root /var/www/certbot;
        }
        return 301 https://$host$request_uri;
    }

    server {
		listen 443 ssl http2;
		server_name "서버도메인";
		root /var/www/html;
    	ssl_certificate     .../fullchain.pem;
    	ssl_certificate_key .../privatekey.pem;
	# 추가적인 설정 코드 ...
```

## Reference

https://www.cloudflare.com/ko-kr/learning/ssl/what-happens-in-a-tls-handshake/
https://www.appviewx.com/blogs/why-is-tls-1-3-better-and-safer-than-tls-1-2/