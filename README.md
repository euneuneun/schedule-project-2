## API 명세서

### 일정(schedule)

|Method|URI|기능|
|--|--|--|
|POST|`/api/v1/schedules`|일정 생성|
|GET|`/api/v1/schedules`|일정 전체 조회|
|GET|`/api/v1/schedules/{scheduleId}`|일정 단건 조회|
|PATCH|`/api/v1/schedules/{scheduleId}`|일정 수정|
|DELETE|`/api/v1/schedules/{scheduleId}`|일정 삭제|

<details>
<summary><h3>일정 생성</h3></summary>

할일 제목, 할일 내용, 작성자명을 입력받아 새로운 일정을 생성합니다.<br>
작성일과 수정일은 서버에서 자동으로 저장됩니다.

|메서드|요청 URL|
|--|--|
|POST|`/api/v1/schedules`

**Request Headers**

|Key|Value|필수|설명|
|--|--|--|--|
|`Content-Type`|`application/json`|✅|요청 본문 형식|

**Request Body**

|필드|타입|필수|설명|
|--|--|--|--|
|`title`|String|✅| 할일 제목 (최대 30자)|
|`content`|String|✅| 할일 내용 (최대 200자)|
|`author`|String|✅| 작성자명 (최대 20자)|

```json
{
    "title": "할일 제목",
    "content": "할일 내용",
    "author": "작성자명"
}
```
**Response Body** `201 Created`

|필드|타입|설명|
|--|--|--|
|`scheduleId`|Long|일정 ID|
|`title`|String|할일 제목|
|`content`|String|할일 내용|
|`author`|String|작성자명|
|`createdAt`|LocalDateTime|작성일
|`modifiedAt`|LocalDateTime|수정일

```json
{
    "scheduleId": 1,
    "title": "할일 제목",
    "content": "할일 내용",
    "author": "작성자명",
    "createdAt": "2026-10-16T14:30:00",
    "modifiedAt": "2026-10-16T14:30:00"
}
```

</details>

<details>
<summary><h3>일정 전체 조회</h3></summary>

전체 일정을 수정일 기준 내림차순으로 조회합니다.<br>
작성자명을 입력하면 해당 작성자의 일정만 조회합니다.

|메서드|요청 URL|
|--|--|
|GET|`/api/v1/schedules`

**Query Parameter**

|필드|타입|필수|설명|
|--|--|--|--|
|`author`|String|❌| 작성자명

**Response Body** `200 OK`

> 수정일(`modifiedAt`) 기준 내림차순 정렬

|필드|타입|설명|
|--|--|--|
|`scheduleList`|List|일정 목록|
|`scheduleList[].scheduleId`|Long|일정 ID|
|`scheduleList[].title`|String|할일 제목|
|`scheduleList[].content`|String|할일 내용|
|`scheduleList[].author`|String|작성자명|
|`scheduleList[].createdAt`|LocalDateTime|작성일|
|`scheduleList[].modifiedAt`|LocalDateTime|수정일|

```json
{
    "scheduleList": [
        {
            "scheduleId": 1,
            "title": "제목",
            "content": "내용",
            "author": "홍길동",
            "createdAt": "2026-10-14T17:09:00",
            "modifiedAt": "2026-10-14T17:09:00"
        },
        {
            "scheduleId": 3,
            "title": "제목",
            "content": "내용",
            "author": "김철수",
            "createdAt": "2026-10-08T17:09:00",
            "modifiedAt": "2026-10-08T17:09:00"
        },
        {
            "scheduleId": 4,
            "title": "제목",
            "content": "내용",
            "author": "김철수",
            "createdAt": "2026-10-07T17:09:00",
            "modifiedAt": "2026-10-07T17:09:00"
        },
        {
            "scheduleId": 2,
            "title": "제목",
            "content": "내용",
            "author": "홍길동",
            "createdAt": "2026-09-14T17:09:00",
            "modifiedAt": "2026-09-14T17:09:00"
        }
    ]
}
```

</details>

<details>
<summary><h3>일정 단건 조회</h3></summary>

일정 ID로 특정 일정 하나를 조회합니다.

|메서드|요청 URL|
|--|--|
|GET|`/api/v1/schedules/{scheduleId}`

**Response Body** `200 OK`

|필드|타입|설명|
|--|--|--|
|`scheduleId`|Long|일정 ID|
|`title`|String|할일 제목|
|`content`|String|할일 내용|
|`author`|String|작성자명|
|`createdAt`|LocalDateTime|작성일
|`modifiedAt`|LocalDateTime|수정일

```json
{
    "scheduleId": 2,
    "title": "제목",
    "content": "내용",
    "author": "홍길동",
    "createdAt": "2026-09-14T17:09:00",
    "modifiedAt": "2026-09-14T17:09:00"
}
```

</details>

<details>
<summary><h3>일정 수정</h3></summary>

일정 ID로 특정 일정의 할일 제목이나 할일 내용을 수정합니다.<br>
수정할 필드만 보내면 되고, 최소 1개는 보내야 합니다. 수정일은 자동으로 갱신됩니다.

|메서드|요청 URL|
|--|--|
|PATCH|`/api/v1/schedules/{scheduleId}`

**Request Headers**

|Key|Value|필수|설명|
|--|--|--|--|
|`Content-Type`|`application/json`|✅|요청 본문 형식|

**Request Body**

|필드|타입|필수|설명|
|--|--|--|--|
|`title`|String|❌| 할일 제목 (최대 30자)|
|`content`|String|❌| 할일 내용 (최대 200자)|

```json
{
    "title": "할일 제목",
    "content": "할일 내용"
}
```
**Response Body** `200 OK`

|필드|타입|설명|
|--|--|--|
|`scheduleId`|Long|일정 ID|
|`title`|String|할일 제목|
|`content`|String|할일 내용|
|`author`|String|작성자명|
|`createdAt`|LocalDateTime|작성일
|`modifiedAt`|LocalDateTime|수정일

```json
{
    "scheduleId": 1,
    "title": "할일 제목",
    "content": "할일 내용",
    "author": "작성자명",
    "createdAt": "2026-10-15T09:00:00",
    "modifiedAt": "2026-10-16T14:30:00"
}
```

</details>

<details>
<summary><h3>일정 삭제</h3></summary>

일정 ID로 특정 일정을 삭제합니다.

|메서드|요청 URL|
|--|--|
|DELETE|`/api/v1/schedules/{scheduleId}`

**Response** `204 No Content`

</details>




## ERD
```mermaid
%%{init: {"er": {"useMaxWidth": true}, "themeVariables": {"fontSize": "12px"}}}%%
erDiagram

    schedules {
        BIGINT schedule_id PK
        VARCHAR(30) title
        VARCHAR(200) content
        VARCHAR(20) author
        TIMESTAMP created_at
        TIMESTAMP modified_at
    }
    

```

## 테이블 명세서

### 공통 컬럼: `BaseEntity`

|칼럼 이름| 데이터타입|null 허용| key종류| 기본값| 설명|
|--|--|--|--|--|--|
|`created_at`|TIMESTAMP|❌|-|-|작성일 (최초 저장 시 자동 입력, 수정 불가)|
|`modified_at`|TIMESTAMP|❌|-|-|수정일 (저장·수정 시 자동 갱신)|

### 테이블명: `schedules`
|칼럼 이름| 데이터타입|null 허용| key종류| 기본값| 설명|
|--|--|--|--|--|--|
|`schedule_id`|BIGINT|❌|PK|AUTO_INCREMENT|일정 ID|
|`title`|VARCHAR(30)|❌|-|-|할일 제목|
|`content`|VARCHAR(200)|❌|-|-|할일 내용|
|`author`|VARCHAR(20)|❌|-|-|작성자명|
|`created_at`|TIMESTAMP|❌|-|-|작성일 (`BaseEntity` 상속)|
|`modified_at`|TIMESTAMP|❌|-|-|수정일 (`BaseEntity` 상속)|
