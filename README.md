# cs496_1stWeek
> 2022 여름 MadCamp 2분반 정세진, 문동우 팀
## 프로젝트 이름
```
CGB
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
![tab1](https://user-images.githubusercontent.com/93732046/177306011-41365749-0f61-4c25-8fa7-bee8d8227a7c.png)
* 휴대전화에 저장된 모든 연락처를 조회할 수 있다.
* 주소록에 접근해 저장된 사진과 이름, 전화번호를 가져와 RecyclerView로 보여준다.
* 연락처 옆의 전화 버튼을 눌러 그 번호로 전화를 걸 수 있다.
### TAB 2: 갤러리
![tab2](https://user-images.githubusercontent.com/93732046/177290867-9d32e5d9-e426-4334-8415-128985a5a32c.png)
* 휴대전화의 갤러리에 접근해 저장된 사진을 가져올 수 있다.
* 사진 가져오려면 오른쪽 아래의 +(플러스) 버튼을 누른다. 연결되는 사진 앱에서 사진을 하나 선택하면 앱의 로컬 저장소에 사진의 복사본을 저장한 후 RecyclerView의 맨 위에 추가하여 보여준다.
* 각 사진 밑의 DELETE 버튼을 눌러 사진의 복사본을 완전히 삭제할 수도 있다.
### TAB 3: 숫자야구
![tab3](https://user-images.githubusercontent.com/93732046/177306058-5ca64d47-69f6-4f74-bd65-5771fe151afd.png)
* 네 자리 수로 하는 숫자야구 게임을 구현했다.
* onCreateView가 실행될 때마다 중복되는 숫자가 없는, 무작위의 새로운 네 자리의 숫자를 생성한다.
* 아래에 있는 10개의 자판을 이용하여 네 자리 숫자를 만들어 GO 버튼을 누르면 결과를 볼 수 있다.
* 맞춰야할 숫자와 입력한 숫자를 비교하여 Strike(번호와 자리가 모두 맞는 숫자의 갯수), Ball(번호는 맞으나 자리가 틀린 숫자의 갯수), Out(Strike, Ball이 모두 0이면 out값이 1 증가한다.)을 계산하여 그 결과를 GridView에 출력한다.
* GridView에는 '입력한 숫자', S(Strike)의 수, B(Ball)의 수, O(Out) 값이 표시된다.
* 정답을 맞추거나 3 OUT이 된 경우 DialogFragment로 결과를 알린다.
### TAB 4: 폭탄돌리기
![tab4](https://user-images.githubusercontent.com/93732046/177290944-433beb21-f949-4be5-a94c-8e786aef8f23.png)
* 여러 사람들이 번갈아가며 폭탄 넘기기 버튼을 누르면 일정 확률로 폭탄이 터지는 폭탄돌리기 게임을 구현했다.
* 폭탄이 터질 확률은 0%에서부터 시작하여 폭탄 넘기기 버튼이 눌러질 때마다 1%씩 증가한다.
* 폭탄이 돌 동안은 타이머가 돌아가는 째깍째깍 소리가 나고, 폭탄이 터지면 펑하는 효과음과 함께 소리가 멈춘다. 
