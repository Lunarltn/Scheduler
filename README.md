# 일정표 과제
<br>

## 📑 소개
스프링 부트 기초 문법 실습 과제
<br><br>

## 📆 기간
* 25/08/01 ~ 25/08/04
<br>

## ⚙ 개발 환경
* JDK 17.0.12
<br>

## API 명세서
### POST /schedulers
- 일정을 생성함
- body:
  ```
  {
    "title" : "일정 제목",
    "contents" : "일정 내용",
    "author" : "김기수",
    "password" : "1234"
  }
  ```
- return:
  ```
  {
    "id": 1,
    "title": "일정 제목",
    "contents": "일정 내용",
    "author": "김기수",
    "creationDate": "2025-08-03T16:56:54.778+00:00",
    "modificationDate": "2025-08-03T16:56:54.778+00:00"
  }
  ```
- error<br>
  400 Bad Request
### GET /schedulers
- 전체 일정을 수정일 기준 내림차순 조회함
- body:
- return:
  ```
  {
    "id": 1,
    "title": "일정 제목1",
    "contents": "일정 내용1",
    "author": "김기수1",
    "creationDate": "2025-08-03T16:56:54.778+00:00",
    "modificationDate": "2025-08-04T16:56:54.778+00:00"
  },
  {
    "id": 2,
    "title": "일정 제목2",
    "contents": "일정 내용2",
    "author": "김기수2",
    "creationDate": "2025-08-03T16:57:54.778+00:00",
    "modificationDate": "2025-08-03T16:57:54.778+00:00"
  }
  ```
### GET /schedulers/id/{id}
- 아이디로 일정과 댓글들을 조회함
- body:
- return:
  ```
  {
      "id": 1,
      "title": "오늘 일정 제목",
      "contents": "오늘 일정 내용",
      "author": "김기수",
      "creationDate": "2025-08-02T14:59:41.330+00:00",
      "modificationDate": "2025-08-02T14:59:41.330+00:00",
      "comments": [
          {
              "id": 1,
              "contents": "댓글 내용",
              "author": "김기수",
              "creationDate": "2025-08-03T14:24:08.139+00:00",
              "modificationDate": "2025-08-03T14:24:08.139+00:00",
              "scheduleId": 1
          }
      }
  }
  ```
- error<br>
  404 NotFound
  ### PATCH /schedulers/id/{id}/password/{password}
- 자격 증명 후 제목과 작성자를 수정함
- body:
  ```
  {
        "title": "제목 수정",
        "author" : "김길숭"
  }
  ```
- return:
  ```
  {
    "id": 1,
    "title": "제목 수정",
    "contents": "오늘 일정 내용",
    "author": "김길숭",
    "creationDate": "2025-08-02T14:59:41.330+00:00",
    "modificationDate": "2025-08-02T14:59:41.330+00:00"
  }
  ```
- error<br>
  401 UNAUTHORIZED<br>
  404 NotFound
  ### DELETE /schedulers/id/{id}/password/{password}
- 자격 증명 후 일정 삭제
- body:
- return:
  ```
  {
    "id": 1,
    "title": "제목 수정",
    "contents": "오늘 일정 내용",
    "author": "김길숭",
    "creationDate": "2025-08-02T14:59:41.330+00:00",
    "modificationDate": "2025-08-02T14:59:41.330+00:00"
  }
  ```
- error<br>
  401 UNAUTHORIZED<br>
  404 NotFound
  ### POST /schedulers/id/{schedulerid}/comments
- 댓글을 생성함
- body:
  ```
  {
    "contents" : "댓글 내용",
    "author" : "김기수",
    "password" : "1234"
  }
  ```
- return:
  ```
  {
    "id": 1,
    "contents": "댓글 내용",
    "author": "김기수",
    "creationDate": "2025-08-03T14:24:08.139+00:00",
    "modificationDate": "2025-08-03T14:24:08.139+00:00",
    "scheduleId": 1
  }
  ```
- error<br>
  400 Bad Request
<br>

## ERD
<img width="413" height="406" alt="erd" src="https://github.com/user-attachments/assets/e66dc28a-3742-4455-bbb6-6f42421b7102" />

💾 [velog](https://velog.io/@qpsrltn/250804-%EC%9D%BC%EC%A0%95%ED%91%9C-%EA%B3%BC%EC%A0%9C)
