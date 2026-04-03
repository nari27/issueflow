# 🚀 IssueFlow

협업 환경에서 프로젝트 이슈를 등록하고  
상태를 관리하며 댓글로 소통할 수 있는  
**이슈 트래킹 서비스 (풀스택 프로젝트)**

> 백엔드 구현 완료 / 프론트엔드 및 배포 진행 예정

---

## 📌 프로젝트 개요

IssueFlow는 협업 프로젝트에서 발생하는 작업(Task)과 버그(Issue)를  
체계적으로 관리하기 위한 서비스입니다.

단순 CRUD를 넘어서,

- 상태 기반 이슈 관리 (TODO → IN_PROGRESS → DONE)
- 담당자 지정
- JWT 기반 인증/인가 처리

를 중심으로 설계했습니다.

---

## 🛠 기술 스택

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT 인증 (Stateless)
- MySQL

### Frontend (예정)
- React
- TypeScript
- Axios
- React Router

---

## ✨ 주요 기능

### 🔐 인증
- 회원가입
- 로그인 (JWT 발급)
- 사용자 정보 조회 (`/users/me`)

---

### 📁 프로젝트
- 프로젝트 생성
- 프로젝트 목록 조회
- 프로젝트 상세 조회
- 프로젝트 수정 (예정)
- 프로젝트 삭제 (예정)

---

### 🐞 이슈 관리
- 이슈 생성
- 프로젝트별 이슈 목록 조회
- 이슈 상세 조회 (예정)
- 이슈 수정 (제목, 설명, 담당자)
- 이슈 삭제
- 이슈 상태 변경  
  `TODO → IN_PROGRESS → DONE`
- 담당자 지정

---

### 💬 댓글 (예정)
- 댓글 작성
- 댓글 조회
- 댓글 삭제

---

## 🧠 설계 포인트

### DTO 분리
- Entity와 DTO를 분리하여 API 응답 구조 명확화
- 클라이언트에 필요한 데이터만 전달

---

### 도메인 중심 설계

```java
public void update(String title, String description, User assignee) {
    this.title = title;
    this.description = description;
    this.assignee = assignee;
    this.updatedAt = LocalDateTime.now();
}
```

- setter 대신 엔티티 메서드로 상태 변경 관리

---

### RESTful API 설계
- HTTP Method 기반 역할 분리
- 상태 코드 (200 / 201 / 204) 명확히 사용

---

### JWT 인증 흐름

```
로그인 → JWT 발급 → 요청 시 Header 포함 → Filter 검증 → Controller 접근
```

---

## 📡 API

### 인증
- POST `/auth/signup`
- POST `/auth/login`
- GET `/users/me`

### 프로젝트
- POST `/projects`
- GET `/projects`
- GET `/projects/{projectId}`
- PATCH `/projects/{projectId}` (예정)
- DELETE `/projects/{projectId}` (예정)

### 이슈
- POST `/projects/{projectId}/issues`
- GET `/projects/{projectId}/issues`
- GET `/issues/{issueId}` (예정)
- PATCH `/issues/{issueId}`
- PATCH `/issues/{issueId}/status`
- DELETE `/issues/{issueId}`

### 댓글 (예정)
- POST `/issues/{issueId}/comments`
- GET `/issues/{issueId}/comments`
- DELETE `/issues/{issueId}/comments/{commentId}`

---

## 🗂 ERD

```
User 1 : N Project
Project 1 : N Issue
User 1 : N Issue (author)
User 1 : N Issue (assignee)
Issue 1 : N Comment
```

---

## ⚙️ 실행 방법

### Backend
```bash
./gradlew bootRun
```

### DB 설정
- MySQL 실행
- application.yml에 DB 정보 설정

---

### API 테스트
- Postman 사용
- JWT 토큰 기반 요청

---

## ⚡ 트러블슈팅

### Content-Type 오류
PATCH 요청 시 `text/plain` 오류 발생  
→ `application/json`으로 변경하여 해결

---

### JWT 인증 문제
토큰 없이 요청 시 인증 실패  
→ Authorization 헤더에 Bearer 토큰 추가하여 해결

---

## 🎨 UI (예정)

- 로그인 페이지
- 프로젝트 목록
- 이슈 보드 (TODO / DOING / DONE)
- 이슈 상세 + 댓글

---

## 🚀 향후 개선 계획

### 기능
- 댓글 기능 구현
- 프로젝트 CRUD 완성
- 이슈 상세 조회 API 추가

### 품질
- 권한 처리 (작성자 / 담당자 기반 접근 제어)
- 유효성 검증 (@Valid)
- 예외 응답 구조 통일

### 성능
- 페이징 처리
- 상태 / 담당자 필터링
- 정렬 기능

### 인프라
- AWS 배포 (EC2, RDS)

---

## 💡 프로젝트 목적

단순 CRUD 구현을 넘어서,

- REST API 설계
- JPA 기반 데이터 모델링
- JWT 인증/인가 처리
- 계층형 구조 설계

를 경험하고,  
실무에 가까운 백엔드 개발 역량을 증명하기 위해 개발했습니다.
