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

<img src="./src/main/resources/static/images/service_architecture.png" style="width: 60%; height: auto;" />

---

## 🗂 ERD (Entity Relationship Diagram)

> 아래는 프로젝트에 실제로 사용된 테이블만 포함한 ERD입니다.

<img src="./src/main/resources/static/images/erd.png" style="width: 80%; height: auto;" />

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

<img src="./src/main/resources/static/images/prix_startpage.png" style="width: 80%; height: auto;" />

---

## 4. 기술적 어려움과 해결

### 1. 문서화되지 않은 JSP 코드 구조 분석

기존 시스템은 JSP 기반으로 구성되어 있었고, 내부 로직이 문서화되어 있지 않아 전체 흐름을 파악하는 데 많은 시간이 걸렸습니다.  
초기에는 화면에 출력되는 결과를 보고 직접 기능을 따라가며 흐름을 추적해야 했고,  
이 과정에서 코드 내 중복된 로직과 불필요한 분기가 많다는 점을 발견했습니다.  
이를 해결하기 위해 각 기능을 모듈 단위로 정리하고,  
Spring MVC 구조를 적용하여 컨트롤러, 서비스, 레포지토리 계층으로 분리함으로써 **구조를 명확히 하고 유지보수성을 개선**했습니다.

---

### 2. 사용자 및 관리자 권한 제어

기존에는 로그인만 구현되어 있을 뿐, 사용자와 관리자의 역할이 구분되지 않아 보안상 문제가 있었습니다.  
이를 해결하기 위해 Spring Security를 도입하고,  
`hasRole` 기반의 접근 제어 로직을 통해 **일반 사용자와 관리자 페이지 접근 권한을 분리**했습니다.  
로그인 실패 핸들러와 세션 무효화 기능도 함께 설정하여 **보다 안전하고 명확한 인증 흐름을 구축**했습니다.

---

### 3. 외부 기능 호출 시 사용자 흐름 단절

USE라는 분석 기능은 외부 사이트를 통해 제공되었고,  
기존에는 단순 링크 연결로 외부 사이트로 이동해야만 기능을 사용할 수 있었습니다.  
이로 인해 사용자 흐름이 단절되고, 다시 돌아와야 하는 번거로움이 있었습니다.  
해결을 위해 iframe을 활용하여 **해당 기능을 현재 페이지 내에서 직접 실행할 수 있도록 통합**했습니다.  
기능 구조가 복잡하지 않았고, 같은 대학교 시스템이라 보안상 우려도 크지 않아 현실적인 해결 방안으로 판단했습니다.  
이 경험을 통해 사용성 개선을 위한 기술 선택과 UX 흐름의 중요성을 체감했습니다.

---

### 4. 템플릿 중복 및 유지보수 어려움

여러 페이지에서 비슷한 HTML 구조가 반복되면서 코드 중복이 많고 유지보수가 어려웠습니다.  
이를 해결하기 위해 Thymeleaf 템플릿을 활용해 공통 레이아웃을 구성하고,  
각 화면은 개별 콘텐츠 블록만 채워넣는 방식으로 변경했습니다.  
이후 화면 변경이나 기능 추가 시에도 영향을 최소화할 수 있었고,  
전체 코드의 일관성과 재사용성이 크게 향상되었습니다.

---

## 5. 결과 및 배운 점

- 단순히 작동하는 프로그램을 만드는 것을 넘어, 구조적 완성도와 유지보수성을 고려한 개발의 중요성을 체감했습니다.
- 사용자 경험을 개선하는 기능 개선 방향을 고민하고 구현한 경험을 통해, 사용자 중심의 개발 태도를 배울 수 있었습니다.
