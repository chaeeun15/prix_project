# JSP 기반 웹 시스템의 HTML5 환경 전환 프로젝트

**📌 개인 프로젝트 | 학부 졸업 프로젝트**

기존 JSP 기반 교수 연구실 웹사이트의 유지보수성과 확장성을 개선하기 위해 HTML5 기반 환경으로 전환하고, Spring 프레임워크를 활용해 구조를 재설계한 프로젝트입니다.

---

## 🔧 사용 기술 스택

- Java 17
- Spring Boot
- MyBatis
- MySQL
- Thymeleaf
- Gradle
- Tomcat 8

---

## 1. 프로젝트 개요

기존 시스템은 JSP 기반으로 구성되어 있었으며, 문서화가 부족해 유지보수가 어려운 상태였습니다. 본 프로젝트는 이를 HTML5 환경으로 전환하고, Spring MVC 구조를 적용하여 코드 구조를 명확히 재정비한 개인 프로젝트입니다.

---

## 2. 주요 기술 및 아키텍처

- Controller / Service / Repository 계층 분리 (Spring MVC)
- 사용자 인증 및 접근 권한 분리 (Spring Security)
- MyBatis를 활용한 DB 연동
- Thymeleaf 기반의 템플릿 구성
- Gradle 기반 빌드 환경 + Tomcat 서버

### 🔽 아키텍처 구성도

![서비스 아키텍처](./src/main/resources/static/images/service_architecture.png)

---

## 🗂 ERD (Entity Relationship Diagram)

> 아래는 프로젝트에 실제로 사용된 테이블만 포함한 ERD입니다.

![ERD](./erd.png)

**관계 설명**
- `px_account` ↔ `px_search_log`: 1:N (한 명의 사용자가 여러 검색 기록)
- `px_account` ↔ `px_enzyme`: 1:N (한 명의 사용자가 여러 효소 등록)
- `px_search_log.db` → `px_database.id`: 검색 시 사용한 데이터베이스 정보 참조

---

## 📌 주요 기능 흐름

### 1. 사용자 회원가입 및 로그인
- 사용자 정보는 `px_account` 테이블에 저장
- Spring Security를 사용한 로그인 처리
- 로그인 성공 시 세션 생성 및 권한 확인

### 2. 검색 기록 저장 (px_search_log)
- 사용자가 검색을 실행하면 검색 조건, DB 선택 내역을 저장
- 사용자 ID와 DB ID를 외래키로 연결
- 검색 결과는 검색 코드로 구분하여 식별

### 3. 관리자 페이지 기능
- 관리자는 로그인 후 별도의 관리자 페이지에 접근할 수 있음
- 관리자 페이지에서는 다음과 같은 데이터 관리 기능을 제공함:

| 기능 | 반영되는 테이블 |
|------|------------------|
| 소프트웨어 정보 수정/등록/삭제 | `px_software_log` |
| 데이터베이스 정보 관리 | `px_database` |
| 소프트웨어 메시지 등록 | `px_software_msg` |
| 버전 및 변경사항 로그 기록 | `px_modification_log` |

- 모든 데이터 변경 작업은 권한이 ADMIN인 사용자만 수행 가능하며,  
  **각 기능 수행 시 관련 테이블에 자동으로 기록**됩니다.
  
---

### 🔽 실행 화면

> ![실행화면](./src/main/resources/static/images/prix_startpage.png)

---

## 4. 기술적 어려움과 해결

| 문제 | 해결 방식 |
|------|------------|
| 문서화되지 않은 JSP 코드로 인해 로직 해석 어려움 | 전체 기능 흐름을 직접 정리하고 모듈 단위로 기능을 분리 |
| 인증 및 권한 제어 미비 | Spring Security를 도입해 사용자/관리자 권한 제어 로직 구현 |
| 코드 중복과 유지보수 어려움 | Thymeleaf 템플릿화 및 MVC 구조 재정비로 구조 간결화 |

---

## 5. 결과 및 배운 점

- 단순히 작동하는 프로그램을 만드는 것을 넘어, 구조적 완성도와 유지보수성을 고려한 개발의 중요성을 체감했습니다.
- 사용자 경험을 개선하는 기능 개선 방향을 고민하고 구현한 경험을 통해, 사용자 중심의 개발 태도를 배울 수 있었습니다.
