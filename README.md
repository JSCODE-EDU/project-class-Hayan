# 게시판 만들기

## 💬 프로젝트 소개

- 웹 프로그래밍의 기초라고 할 수 있는 게시판을 직접 만들어보기 위해 시작했습니다.
- 엔티티 설계부터 API 문서화까지 하나 하나 직접 구현했습니다.
- 익명 게시판으로 시작했지만 Spring Security를 적용해 회원만 이용 가능한 게시판으로 리팩토링 중입니다.

## 🌟 프로젝트 기능
프로젝트의 주요 기능은 아래와 같습니다.
- **게시판** : CRUD, 특정 키워드로 검색
- **회원** : 회원가입 및 로그인, 내 정보 조회

## 💻 사용 기술
#### 주요 프레임워크 / 라이브러리
- Java 17
- SpringBoot 3.0.6
- JPA(Spring Data JPA)
- Spring Security
- Gradle 7.6

#### 데이터베이스
- MySQL 8.0.11

---

## 구조 및 설계
```markdown
📦hayan
┣ 📂board
┃ ┣ 📂domain
┃ ┃ ┗ 📜Board.java
┃ ┣ 📂repository
┃ ┃ ┗ 📜BoardRepository.java
┃ ┣ 📂service
┃ ┃ ┗ 📜BoardService.java
┃ ┣ 📂controller
┃ ┃ ┗ 📜BoardController.java
┃ ┗ 📂dto
┃ ┃ ┣ 📜ResponseDto.java
┃ ┃ ┗ 📜RequestDto.java
┣ 📂member
┃ ┣ 📂domain
┃ ┃ ┗ 📜Member.java
┃ ┣ 📂repository
┃ ┃ ┗ 📜MemberRepository.java
┃ ┣ 📂service
┃ ┃ ┗ 📜MemberService.java
┃ ┣ 📂dto
┃ ┃ ┣ 📜RequestDto.java
┃ ┃ ┗ 📜ResponseDto.java
┃ ┗ 📂controller
┃ ┃ ┗ 📜MemberController.java
┣ 📂global
┃ ┣ 📂exception
┃ ┃ ┣ 📜ErrorResponse.java
┃ ┃ ┣ 📜GlobalExceptionHandler.java
┃ ┃ ┣ 📜CustomException.java
┃ ┃ ┗ 📜ErrorInformation.java
┃ ┗ 📂config
┃ ┃ ┣ 📜OpenApiConfig.java
┃ ┃ ┗ 📜SecurityConfig.java
┗ 📜SpringProjectApplication.java
```

## DB 설계

// TODO

## API 설계

// TODO

---

## 추가해야 할 기능

- Spring Security 적용
- 회원만 작성이 가능하게 수정


