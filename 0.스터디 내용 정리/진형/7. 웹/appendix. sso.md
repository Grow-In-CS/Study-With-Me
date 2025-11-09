# 🧩 SSO (Single Sign-On) — 한 번의 로그인으로 여러 서비스 이용하기

---

## 📘 1. 개념 요약

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F99CE614E5B27749C0D)

**SSO (Single Sign-On)**  
→ 사용자가 **한 번 로그인하면** 같은 인증 체계를 공유하는 여러 애플리케이션에  
**다시 로그인하지 않고 자동으로 접근**할 수 있도록 하는 인증 방식.

> 💬 즉, “회사 포털 로그인 한 번으로 이메일, 인사시스템, 메신저까지 전부 로그인 유지!”

---

## 🧠 2. 왜 필요한가?

기업 시스템이나 플랫폼 서비스는 보통 여러 개의 애플리케이션으로 구성됩니다.  
(예: 사내 인트라넷 + 메일 + 결재 시스템 + ERP)

- 각 서비스마다 로그인하면 **중복 로그인 불편**
- 사용자 비밀번호를 여러 시스템이 각각 보유 → **보안 취약**
- 비밀번호 변경 시 모든 시스템에 적용 어려움

✅ 그래서 등장한 개념이 **SSO**
→ 중앙 인증 서버를 하나 두고, 나머지 서비스는 그 서버의 인증 결과만 신뢰하도록 설계합니다.

---

## ⚙️ 3. SSO 기본 구조

```
[User]
  │
  ▼
[Service A]──┐
              │
              ▼
         [SSO 서버] ──> [인증 DB / ID Provider]
              │
              ▼
[Service B]───┘
```

- **SSO 서버**: 사용자의 로그인 상태를 관리하고, 인증 토큰을 발급
- **서비스들(Service Provider, SP)**: SSO 서버에서 발급받은 토큰을 검증하여 로그인 처리
- **사용자(User)**: 한 번 로그인하면 다른 시스템에서도 자동 로그인

---

## 🔄 4. SSO 인증 동작 흐름

1️⃣ **사용자 접근**  
→ 사용자가 `Service A`에 접근 시 아직 로그인 상태가 아님.

2️⃣ **SSO 서버로 리디렉션**  
→ `Service A`는 인증이 필요하므로 사용자를 **SSO 서버(Login Page)** 로 리디렉션.

3️⃣ **사용자 로그인 처리**  
→ 사용자가 SSO 서버에서 아이디/비밀번호 입력.  
→ SSO 서버가 인증 후, **세션 or 토큰** 생성.

4️⃣ **토큰 발급 및 리턴**  
→ `Service A`에 인증 토큰(SAML / JWT / Cookie 등)을 전달.

5️⃣ **다른 서비스 접근 시 자동 로그인**  
→ 사용자가 `Service B` 접근 시 SSO 서버 세션을 확인 → 이미 로그인되어 있음을 감지 → 자동 인증 완료.

---

## 🔐 5. 주요 구성 요소

| 구성 요소 | 역할 |
|------------|------|
| **Identity Provider (IdP)** | 사용자 인증을 담당하는 중앙 인증 서버 (예: Google, Keycloak, Okta) |
| **Service Provider (SP)** | 인증 결과를 신뢰하고 토큰을 받아 처리하는 각 애플리케이션 |
| **SSO Token / Assertion** | 인증 결과를 전달하는 보안 토큰 (예: SAML Assertion, JWT) |
| **Session Store / Cookie** | 로그인 상태를 유지하기 위한 저장 매체 |

---

## 🧾 6. 대표 프로토콜 및 기술 방식

| 프로토콜 | 설명 | 특징 |
|-----------|------|------|
| **SAML (Security Assertion Markup Language)** | XML 기반 SSO 표준 | 기업 환경에서 많이 사용 (ADFS, Shibboleth 등) |
| **OAuth 2.0** | 인증 위임 프로토콜 | 주로 API 접근 위임용, SSO로 확장 가능 |
| **OpenID Connect (OIDC)** | OAuth 2.0 위에 인증 기능 추가 | 현대 SSO 표준, Google/Naver 로그인 |
| **Kerberos** | 대칭키 기반 인증 프로토콜 | 내부 네트워크용 (Windows AD 환경) |

---

## 🧩 7. SSO 방식별 비교

| 구분 | SAML | OpenID Connect | Kerberos |
|------|------|----------------|-----------|
| 포맷 | XML | JSON | Binary |
| 사용 환경 | 기업, 기관 | 웹/모바일 | 내부 네트워크 |
| 사용 토큰 | SAML Assertion | ID Token (JWT) | Ticket |
| 복잡도 | 높음 | 중간 | 낮음 |
| 보안 강도 | 매우 높음 | 높음 | 높음 |

---

## 🧰 8. 실제 사례

| 환경 | 설명 |
|------|------|
| **Google Workspace (Gmail, Drive, Calendar)** | 로그인 한 번으로 모든 Google 서비스 사용 (OIDC 기반) |
| **사내 시스템 (Active Directory + ADFS)** | 회사 로그인 1회로 인사, ERP, 메일 자동 로그인 (SAML 기반) |
| **AWS Management Console + IAM Identity Center (SSO)** | 사내 인증 서버와 AWS 계정 통합 (OIDC 기반) |

---

## 💡 9. SSO와 OAuth의 관계

| 비교 항목 | OAuth 2.0 | SSO |
|------------|-----------|----|
| 목적 | 타 서비스의 자원 접근 위임 | 여러 서비스 통합 로그인 |
| 결과물 | Access Token | ID Token / SSO Token |
| 사용자 인증 여부 | Optional (위임 중심) | 필수 (인증 중심) |

👉 즉,
- **OAuth**는 “허락받은 서비스가 데이터를 대신 접근”
- **SSO**는 “인증 결과를 여러 서비스가 공유”

⚙️ 실제로는 `OpenID Connect(OIDC)`가 **OAuth2 기반 SSO**로 가장 많이 쓰임.

---

## 🧱 10. 보안 고려사항

| 이슈 | 설명 | 해결 방법 |
|------|------|-----------|
| **토큰 탈취 위험** | SSO 토큰이 유출되면 전체 서비스 접근 가능 | HTTPS, 짧은 토큰 만료시간 |
| **쿠키 도메인 공유 문제** | 서로 다른 도메인에서는 SSO 쿠키 공유 불가 | 중앙 IdP 리디렉션 방식 사용 |
| **세션 만료 동기화** | 한 시스템에서 로그아웃해도 다른 시스템은 유지 | Global Logout 구현 (SLO) |

---

## 🚪 11. Single Logout (SLO)

SSO 환경에서 로그아웃 처리도 통합되어야 함.  
→ **하나의 서비스에서 로그아웃하면 모든 서비스 세션도 종료**되어야 함.

흐름:
1. 사용자가 Service A에서 로그아웃
2. SSO 서버에 “세션 만료” 알림
3. 서버가 연결된 모든 SP에 세션 종료 알림 전파
4. 사용자의 모든 로그인 세션 삭제 완료 ✅

---

## 🧠 12. 핵심 요약

- **SSO는 “인증의 중앙화”를 통해 보안성과 편의성을 동시에 확보**
- 주요 표준: **SAML**, **OAuth2 + OpenID Connect**, **Kerberos**
- 필수 요소: **IdP(인증 서버)**, **SP(서비스 제공자)**, **토큰(JWT/SAML)**
- 함께 고려해야 할 사항: **SLO(통합 로그아웃)**, **MFA 연동**, **토큰 만료 관리**

---

📘 **References**
- [SAML 2.0 Specification (OASIS)](https://docs.oasis-open.org/security/saml/v2.0/)
- [OpenID Connect Core Spec](https://openid.net/specs/openid-connect-core-1_0.html)
- [Okta SSO Overview](https://developer.okta.com/docs/concepts/sso/)
- [Microsoft Entra ID SSO Docs](https://learn.microsoft.com/en-us/entra/identity/enterprise-apps/add-application-portal-setup-sso)
