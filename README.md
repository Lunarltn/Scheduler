# API 명세서
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
- error
  
