# 계산기 TDD 예제

## To-do-list

- ~~문자열을 구분자를 기준으로 분리해라~~
    - "" -> null
    - "1,2,3" -> {1, 2, 3}
    - "1,2:3" -> {1, 2, 3}
- ~~커스텀 구분자를 지정할 수 있다.~~
    - "//;\n1;2;3" -> {1, 2, 3}
- ~~숫자 배열을 더해라~~
    - "1,2,3"
    - "1,2:3"
    - "//;\n1;2;3"
- ~~음수를 받으면 RuntimeException 에러를 throw 한다.~~
- ~~문자열 구분 메소드 리팩토링 필요~~

# 사다리 게임

## To-do-list
- ~~라인이 겹치면 안 된다. (isConsecutive())~~
    - true, true -> true
    - true, false -> false
    - false, true -> false
- ~~라인은 위치를 받았을 때 진행할 방향을 알려줘야 한다.~~
    - 0, {true, false} -> RIGHT
    - 1, {true, false} -> LEFT
    - 0, {false, true} -> STRAIGHT
- ~~사다리 타기를 진행해서 결과를 얻는다.~~
    - lines, {"pobi" : 0, "crong" : 1, "honux": 2} -> {"pobi" : 2, "crong" :1, "honux" : 0}
- ~~사다리 게임 결과를 생성한다.~~
    - {"pobi" : 2, "crong" :1, "honux" : 0}, {"꽝", "2000", "3000"} -> {"honux" : "꽝", "crong" : "2000", "pobi" : "3000"}
- ~~플레이어 클래스 추가.~~
- ~~라인 생성 기능 추가.~~
- ~~래더 게임 클래스 게임 진행 로직 추가~~
- ~~이름 입력 기능 추가~~
- ~~높이 입력 기능 추가~~
- ~~보상 입력 기능 추가~~
- ~~사다리 출력 기능 추가~~
- ~~결과 출력 기능 추가~~
- ~~이름 입력 예외 처리 추가 (이름은 5글자 이내여야 함)~~
- ~~보상 입력 예외 처리 추가 (보상도 5글자 이내여야 함)~~
- ~~예외 처리 단위테스트 추가~~
- ~~리팩토링~~

## 리팩토링
- ~~불필요한 공백 라인을 지운다.~~
- ~~Player의 name이 null 인지 체크한다.~~
- ~~Player의 position 값을 검증한다.~~
- ~~Direction enum 을 쓰지 말고 구현한다.~~
- ~~Line 생성자에서 points 값을 바로 받지 않고 예외처리를 먼저 해라.~~
- Player 와 Reward 도 Line 처럼 일급컬렉션으로 구현할 수 있지 않을까?
- LadderGame의 goDown()의 리턴타입을 Result 객체로 받아서 OutputView에서 출력을 정의하는 방법은 어떨까? 장점은 무엇일까?
- 내부 변수를 사용하면 메서드의 파라미터 수나 메서드 수를 줄일 수 있다.
- 메서드 리네이밍

## 다시 짜자
1. players - player, Rewards - reward 일급컬렉션 사용.
2. 컨트롤러는 메시지만 보내야한다. 복잡한 로직이 있으면 안 됨. (그래서 일급 컬렉션을 사용하는 것)
3. 프러덕션 코드를 수정하기 위해서는 테스트 코드를 먼저 짜라
4. 컨트롤러에서 게임 로직과 결과를 조합하는 부분이 섞여있으면 안 된다. 게임 로직(플레이어 시작점, 끝점 페어)은 사다리 게임 엔진
이라는 도메인 객체를 하나 만들어서 처리해라. 
5. 원시값, 문자열은 다 객체로 포장한다.

- (계산기) PositiveNumber 클래스를 만들어 사다리 높이를 입력받는다.
- (true,true), (true,false) 와 같이 두 개의 불리언 값을 객체로 만들면 어떨까?
- Line 에서 getDirection 으로 나아갈 방향을 찾는 것보다 이러한 역할을 하는 객체를 만들어라.
    
    
## To-do-list
### Domain(Model)
- Player의 현재 위치를 나타내는 Position 클래스를 작성한다.
    - ~~현재 위치는 0 이상이어야한다.~~
    - ~~현재 위치는 전체 플레이어 수보다 작아야한다.~~
- Player가 어디로 움직여야할 지 알려주는 Direction 클래스를 작성한다.
    - ~~왼쪽과 현재가 동시에 true일 수 없다.~~
    - ~~(true, false) -> -1~~
    - ~~(false, true) -> 1~~
    - ~~(false, false) -> 0~~
    - ~~첫 번째 플레이어는 왼쪽으로 움직일 수 없고, 마지막 플레이어는 오른쪽으로 움직일 수 없다.~~
    - ~~direction의 생성자를 private으로 만들고, first() 메서드로만 인스턴스를 생성할 수 있도록 강제한다.~~
- Position을 인스턴스 변수로 갖는 Player 클래스를 작성한다.
    - ~~Player는 자신의 Position과 인자로 받은 Line을 이용하여 움직일 수 있다.~~
    - ~~Player는 이름을 갖는다.~~
    - ~~Player 이름은 한 글자 이상 5글자 이하이어야 하고 null일 수 없다.~~
- Direction의 리스트만을 인스턴스변수로 갖는 일급컬렉션인 Line 클래스를 작성한다.
    - ~~Position을 인자로 받고 해당하는 Direction을 반환한다.~~
- Player의 리스트만을 인스턴스변수로 갖는 일급컬렉션인 Players 클래스를 작성한다.
    - ~~Line을 입력받아 player들을 사다리에서 한 라인 내려가도록 만든다.~~
- ~~도메인 객체들에 toString()을 추가해 디버깅에 용이하도록 만든다.~~
- Line의 리스트만을 인스턴스변수로 갖는 일급컬렉션인 Ladder 클래스를 작성한다.
    - ~~getNextLine() 을 통해 다음 순서의 Line을 반환한다.~~
- 사다리 게임의 로직을 담당하는 LadderEngine 클래스를 작성한다.
    - ~~Ladder와 Player를 입력으로 받으면 사다리 이동 후 Player 리스트를 리턴한다.~~
- Direction 클래스를 Point 클래스로 리네이밍한다.
- 클래스 생성자에 Not Null validation을 추가한다.
- 사다리를 임의로 생성하는 RandomLadderGenerator 클래스를 작성한다.
    - ~~numOfPlayers 와 height 를 생성자 파라미터로 받아서 그에 맞는 타당한 사다리를 생성한다.~~
    - generateOneline() 코드를 리팩토링하여 메소드 라인 수를 줄인다.
    - 더 나은 테스트 방법에 대해 고민해본다.
    - 뭔가 마음에 안 듬
- PlayerName, RewardName 클래스 작성
- RewardName 클래스 제거, 불필요하다고 생각됨
- Reward 클래스 작성
    - ~~보상은 null일 수 없다.~~
    - ~~보상은 한 글자 이상, 다섯 글자 이하여야 한다.~~
- Reward의 리스트만을 인스턴스변수로 갖는 일급컬렉션인 Rewards 클래스를 작성한다.
- Players와 Rewards를 인자로 받는 QueryProcessor 클래스를 작성한다.
    - ~~PlayerName 과 Reward로 구성된 맵인 Results를 인스턴스변수로 갖는다. (더 이상 포지션은 의미가 없다.)~~
    - ~~플레이어의 이름을 받으면 그 플레이어가 받을 보상을 반환한다.~~
    - ~~"all"을 받으면 Result를 반환한다.~~
- Map<PlayerName,Reward> 만을 인스턴스 변수로 갖는 일급컬렉션인 Reults 클래스를 작성한다.

### View
- 사용자로부터 플레이어의 이름들을 입력받는 메서드 작성
- 사용자로부터 사다리의 높이를 입력받는 메서드 작성
    - ~~LadderHeight 클래스 작성~~
- 사용자로부터 보상들을 입력받는 메서드 작성
- 사용자로부터 결과를 알고 싶은 플레이어의 이름을 입력받는 메서드 작성

- 플레이어의 이름들을 출력하는 메서드 작성
- 사다리를 출력하는 메서드 작성
- 보상들을 출력하는 메서드 작성
- 보상을 출력하는 메서드 작성
- 결과를 출력하는 메서드 작성

### Controller
- **게임의 로직을 담당하는 LadderGame 클래스 작성**