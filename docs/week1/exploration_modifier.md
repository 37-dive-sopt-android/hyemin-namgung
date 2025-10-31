## Modifier에 관한 구글의 경고는 쓸모있을까?

1차 세미나를 듣고, 과제를 하면서 Modifier가 굉장히 꾸밈뿐만 아니라, UI의 구조와 동작을 결정하는 중요한 객체라는 것을 알게되었다.
과제 후, Modifier의 공식문서를 다시 읽어보면서 왜 구글이 Modifier를 항상 첫 번째 선택적 매개변수로 두라고 강조하는지 찾아보았다.
공식 문서에서 Modifier는 다음과 같이 정리한다.


    1. Modifier는 Optional parameter 중 항상 첫 번째 선택적 매개변수로 두어야한다. 
    2. 이름은 반드시 `modifier`로 하고, 기본 값은 Modifier이어야 한다. 
    3. Modifier는 UI의 모양, 행동 둘 다 바꾸는 역할을 하기에, 컴포저블의 가장 루트 레이아웃에 적용되어야한다. 
    4. Modifier 파라미터는 오직 하나만 존재해야한다. 


<img width="973" height="567" alt="Image" src="https://github.com/user-attachments/assets/0e51922a-b1a6-455c-8b82-79a5574f0247" />


### **🤔 그럼 왜 '항상 첫 번째 optional parameter일까?**
이 이유를 찾기 위해 Jetpack Compose 공식 가이드라인과 Compose API Guidelines 문서를 읽어본 결과 다음과 같은 이유를 정리할 수 있었다.

**1. UI 컴포넌트의 핵심 원칙은 '확장성(Scalability)'과 '일관성(Consistency)'이다.**
UI 컴포넌트에서는 컴포넌트가 장기적으로 유지, 확장될 수 있도록 확장성과 모든 컴포넌트가 동일한 구조와 규칙을 가져 개발자가 새로운 API를 직관적으로 예측할 수 있는 일관성 원칙을 강조한다.
이러한 이유로 Modifier는 거의 모든 컴포넌트에서 사용되므로, 항상 동일한 위치(첫 번째 optional parameter)에 두어야 개발자가 일관되고 예측가능한 방식으로 코드를 작성할 수 있다.

**2.Modifier는 외형과 행동을 모두 제어하는 파라미터다.**
Modifier의 역할은 모양과 행동에 둘 다 영향 미치는 요소라, 대부분의 컴포넌트에 반드시 존재해야한다. 그래서 대부분의 컴포넌트에 존재하기에 항상 같은 위치에 와야한다고 생각한다.


**3. Modifier는 단순하지만 매우 자주 사용되는 파라미터다.**
Modifier는 외형과 행동을 모두 제어하기에, 굉장히 자주 사용된다. 그래서 대부분의 컴포저블이 Modifier를 포함하고 있으며, 이를 맨 앞에 두면 이름을 생략하고 바로 사용할 수 있다. 이렇게 사용하면 직관적으로 작성할 수도 있고, 일관성을 유지할 수 있기 때문이다.



	     Button(onClick = {}, Modifier.padding(8.dp))

### 📚 추가적으로 알게 된 점**

공식 문서에서는 **Modifier를 변수로 추출해 재사용할 것**을 권장한다.  
그 이유는 다음과 같다.

<img width="856" height="81" alt="Image" src="https://github.com/user-attachments/assets/313b45c8-88a4-44d0-b2e8-cc706b3b3d05" />

- Modifier는 **불변(immutable)** 객체이지만,  
  매번 `Modifier.padding(8.dp)` 와 같이 새로 작성하면  
  `Compose`는 **새로운 인스턴스**로 인식해 비교 연산이 늘어난다.
- `Modifier` 체인을 변수로 추출해 재사용하면  
  `Compose`가 동일한 객체로 인식하여 **비교 비용이 줄어든다.**
- 이로 인해 **불필요한 객체 생성과 비교 연산이 줄어**,  
  **성능이 개선될 수 있다.**


또한 다른 Modifier 체인을 추가하고 싶을 때는  
`then()` 함수를 사용해 기존 Modifier에 안전하게 체이닝할 수 있다.

    val containerModifier = Modifier.padding(8.dp)
       
    val clickableModifier = Modifier.clickable { /* handle click */ }
  
    Box(
        modifier = containerModifier.then(clickableModifier)
    ) {
        Text("Click Me!")
    }

이번 탐구 과제를 통해 Modifier에 대해 많은 것을 알게되었다. 다음 과제부터, Modifier의 장점을 활용해서 코드를 구현해야겠다. 

----------
📄 [관련 공식문서]
[compose component - API 가이드 라인]
https://android.googlesource.com/platform/frameworks/support/+/androidx-main/compose/docs/compose-component-api-guidelines.md

[compose - modifiers]
https://developer.android.com/develop/ui/compose/modifiers
