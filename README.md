# cs496_1stWeek
> 2022 여름 MadCamp 2분반 정세진, 문동우 팀
## 프로젝트 이름
```
무제
```
## 팀원
* 고려대 컴퓨터학과 [정세진](https://github.com/asjay18)
* KAIST 전산학부 [문동우](https://github.com/snaoyam)
## 개발 환경
* OS: Android (targetSdk: 31)
* Language: Kotlin
* IDE: Android Studio
* Target Device: Galaxy Note 10 Plus
## 프로젝트 소개
### TAB 1: 연락처
* 휴대전화의 주소록에 접근해 저장된 사진과 이름, 전화번호를 가져와 RecyclerView로 보여준다.
* 연락처 옆의 전화 버튼을 눌러 그 번호로 전화를 걸 수 있다.
### TAB 2: 갤러리
* 휴대전화의 갤러리에 접근해 저장된 사진을 가져올 수 있다.
* 사진 가져오려면 오른쪽 아래의 +(플러스) 버튼을 누른다. 연결되는 사진 앱에서 사진을 하나 선택하면 앱의 로컬 저장소에 사진의 복사본을 저장한 후 RecyclerView의 맨 위에 추가하여 보여준다.
* 각 사진 밑의 DELETE 버튼을 눌러 사진의 복사본을 완전히 삭제할 수도 있다.
### TAB 3: 숫자야구
* 네 자리 수로 하는 숫자야구 게임을 구현했다.
* onCreateView가 실행될 때마다 중복되는 숫자가 없는, 무작위의 새로운 네 자리의 숫자를 생성한다.
* 아래에 있는 10개의 자판을 이용하여 네 자리 숫자를 만들어 GO 버튼을 누르면 결과를 볼 수 있다.
* 맞춰야할 숫자와 입력한 숫자를 비교하여 Strike(번호와 자리가 모두 맞는 숫자의 갯수), Ball(번호는 맞으나 자리가 틀린 숫자의 갯수), Out(Strike, Ball이 모두 0이면 out값이 1 증가한다.)을 계산하여 그 결과를 GridView에 출력한다.
* GridView에는 '입력한 숫자', 'Strike'(초록색)의 수, 'Ball'(노란색)의 수, 'Out'(빨간색) 값이 표시된다.
* 정답을 맞추거나 3OUT이 된 경우 DialogFragment로 결과를 알린다.
### TAB 4: 폭탄돌리기
* 
