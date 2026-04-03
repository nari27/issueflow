# IssueFlow

JWT 인증 기반의 협업 이슈 트래킹 백엔드 프로젝트입니다.  
프로젝트를 생성하고, 프로젝트별 이슈를 등록/조회/수정/삭제할 수 있으며, 이슈 상태 변경과 담당자 지정 기능을 포함합니다.

## 프로젝트 소개

IssueFlow는 협업 환경에서 프로젝트 단위로 이슈를 관리할 수 있는 이슈 트래킹 서비스입니다.  
사용자는 로그인 후 프로젝트를 생성하고, 각 프로젝트 내에서 이슈를 등록하고 상태를 관리할 수 있습니다.

이 프로젝트는 단순 CRUD 구현을 넘어서 다음과 같은 백엔드 핵심 흐름을 직접 구현하는 것을 목표로 했습니다.

- JWT 기반 인증/인가 흐름 이해
- 프로젝트 / 이슈 / 사용자 간 연관관계 설계
- REST API 설계 및 상태 코드 적용
- JPA 기반 엔티티 관리 및 변경 감지(Dirty Checking) 이해
- DTO와 Entity를 분리한 구조 설계

---

## 기술 스택

### Backend
- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- MySQL
- JWT (jjwt)

### Build / Tool
- Gradle
- IntelliJ IDEA
- Postman
- Git / GitHub

---

## 주요 기능

### 1. 사용자 인증
- 회원가입
- 로그인
- JWT 토큰 발급
- 인증된 사용자 정보 조회 (`/users/me`)

### 2. 프로젝트 관리
- 프로젝트 생성
- 프로젝트 목록 조회
- 프로젝트 상세 조회

### 3. 이슈 관리
- 이슈 생성
- 프로젝트별 이슈 목록 조회
- 이슈 상태 변경 (`TODO → IN_PROGRESS → DONE`)
- 이슈 수정
- 이슈 삭제
- 담당자 지정

---

## API 예시

### 회원가입
`POST /auth/signup`

### 로그인
`POST /auth/login`

### 내 정보 조회
`GET /users/me`

### 프로젝트 생성
`POST /projects`

### 프로젝트 목록 조회
`GET /projects`

### 프로젝트 상세 조회
`GET /projects/{projectId}`

### 이슈 생성
`POST /projects/{projectId}/issues`

### 프로젝트별 이슈 조회
`GET /projects/{projectId}/issues`

### 이슈 상태 변경
`PATCH /issues/{issueId}/status`

### 이슈 수정
`PATCH /issues/{issueId}`

### 이슈 삭제
`DELETE /issues/{issueId}`

---

## 프로젝트 구조

```text
src/main/java/com/issueflow
├── config
│   ├── SecurityConfig
│   └── jwt
│       ├── JwtAuthenticationFilter
│       └── JwtProvider
├── global
│   └── exception
│       ├── GlobalExceptionHandler
│       └── NotFoundException
├── user
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
├── project
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
└── issue
    ├── controller
    ├── dto
    ├── entity
    ├── repository
    └── service
