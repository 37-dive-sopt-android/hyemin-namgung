
## 🫥 빈칸은 어떻게 만들어야할까 ? Spacer, Modifier.padding
빈칸을 구분하는 방법에는 크게 2가지가 있다. `Spacer`와 `Modifier.padding` 이 두개의 차이에 대해 알아보고, 어떨 때 사용해야 적절한지 기준을 정해보려고 한다.

`Spacer` 와 `Modifier.padding`은 UI를 그릴 때, 컴포넌트 사이 간의 간격을 조정하기 위해 사용된다.

공식 문서에서 `Modifier.padding()`와 `Spacer`의 설명
- **Modifier.padding()**
  `Modifier.padding()` 은 내부 여백을 만들기 위한 도구이다.
  <img width="817" height="321" alt="Image" src="https://github.com/user-attachments/assets/5db87236-355c-4839-8224-2714d0fb3f20" />

- **Spacer**
  `Spacer`는 요소 간의 빈 공간을 만들어주는 레이아웃 컴포저블이다.
  <img width="909" height="333" alt="Image" src="https://github.com/user-attachments/assets/04905361-5243-4edd-9270-39efeceef50c" />



위 공식문서 설명처럼 `Modifier.padding()`은 컴포넌트 자신의 내부 여백을 만들어주는 역할을 한다. 즉 해당 컴포넌트의 콘텐츠가 테두리에 너무 밀착되지 않도록 안쪽에 여유 공간을 주는 것이다.
반면  `Spacer`는 컴포넌트 사이에 독립적인 빈 공간을 생성하는 레이아웃 컴포저블이다. `Modifier.padding` 처럼 내장되어, 내부 여백을 채우는 것이 아니라 요소 간의 간격을 조정하기 위해 사용되는 빈 레이아웃이다.


## 🤔 어떨 때 어떤 걸 사용하는 것이 좋을까 ?
위에서 알아본 것처럼 `Spacer`와 *Modifier.padding*은 겉보기엔 여백을 만든다는 공통점이 있지만 분명 차이점도 있다. 이번에 찾아보면서 생각해본 방법은 아래와 같다.
- 컴포넌트 내부의 여백 -> ***Modifier.padding***
- 컴포넌트 사이 간격 -> ***Spacer***
- 유연한 공간 채움 -> ***Spacer, Modifier.weight***

-> *실습하면서 로그인 버튼만 아래로 배치하고 싶었는데, 안되는 어려움을 겪었을 때 OB님이 알려주신 방법이다... `Spacer`와 `Modifier.weight(1f)`를 같이 사용하면  `Modifier.weight`가 비율을 계산해 버튼이 배치되고 남은 부분에 `Spacer`가 남은 공간을 모두 차지하여 레이아웃이 자연스럽게 화면 전체에 균형있게 배치된다고 ,, OB님 짱 👍  ,, ,,,* 
