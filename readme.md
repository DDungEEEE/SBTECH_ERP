# 🔋 배터리프렌즈 회사 관리 시스템

헥사고날 아키텍처 기반으로 설계된 회사 관리 시스템입니다.  
JWT 인증, 파일 업로드, 허브-회사-상품 관리 기능을 중심으로 구성되어 있으며,  
ELK 도입을 통해 서버 로그 추적과 디버깅 효율을 높이는 구조를 설계 중입니다.

---

## 🛠 Technologies & Tools

- Java 17  
- Spring Boot 3.3.3  
- Spring Security  
- JWT (Json Web Token)  
- PostgreSQL  
- Redis  
- Swagger  
- Gradle  
- Docker / Docker Compose  
- Git / GitHub  
- IntelliJ IDEA  
- Postman / Notion  

---

## 📌 프로젝트 개요

- 헥사고날 아키텍처 기반의 단일 서버 구조  
- JWT 기반 인증 시스템 + Redis 블랙리스트 처리  
- 관리자용 허브, 회사, 상품 CRUD 기능  
- ELK 연동을 통해 서버 내부 로그를 실시간 확인할 수 있는 시스템 설계 중

---

## 🔧 적용 기술 및 목적

- **Redis**  
  → JWT 블랙리스트 처리 및 캐싱

- **Swagger**  
  → 프론트와의 명세 공유 및 테스트 자동화

- **Spring Security + JWT**  
  → Access / Refresh 토큰 기반 인증 처리, 인가 흐름 설계

---

## 🧨 Trouble Shooting

- JWT 유효성 검사 실패 시 프론트에는 `"유효하지 않은 토큰입니다"`로 추상화된 메시지를 전달  
  → 실제 원인(Logback 로그)은 **ELK 기반으로 서버 내부에서 추적** 가능하게 설계 중

- 로그 레벨 설정 고민  
  → `warn` vs `error` 기준 재정의 중 (보안 로그, 예외 로그 분리 검토 중)

---

## 🤔 현재 고민 중인 부분

- ELK(Logstash, Elasticsearch, Kibana) 도입 중  
  → 추상화된 에러 응답 뒤의 실제 예외 원인을 실시간 추적하는 목적  
- 로그 포맷(JSON 변환), 로그 수집 필드, 보안 로그 마스킹 정책 설정 검토 중

---

## 🧱 Architecture Overview

