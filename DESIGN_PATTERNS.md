# 디자인 패턴 적용

##  적용된 디자인 패턴

### 1. **Strategy Pattern (전략 패턴)**
**위치**: `calculator.parser` 패키지

- **목적**: 구분자 파싱 전략을 동적으로 선택
- **구현**:
  - `DelimiterParser` 인터페이스: 파싱 전략의 추상화
  - `DefaultDelimiterParser`: 기본 구분자(`,`, `:`) 처리
  - `CustomDelimiterParser`: 커스텀 구분자 처리
  
**장점**:
- 새로운 구분자 처리 방식 추가 시 기존 코드 수정 불필요 (OCP 준수)
- 각 파싱 전략을 독립적으로 테스트 가능

### 2. **Factory Pattern (팩토리 패턴)**
**위치**: `DelimiterParserFactory`

- **목적**: 입력에 따라 적절한 파서를 생성하고 반환
- **구현**: 입력 문자열을 분석하여 적절한 `DelimiterParser` 선택

**장점**:
- 객체 생성 로직을 중앙화
- 클라이언트 코드는 구체적인 파서 구현을 알 필요 없음

### 3. **Template Method Pattern (템플릿 메서드 패턴)**
**위치**: `calculator.validator` 패키지

- **목적**: 검증 흐름을 정의하고 구체적인 검증 로직은 서브클래스에서 구현
- **구현**:
  - `NumberValidator`: 추상 클래스로 검증 템플릿 제공
  - `NegativeNumberValidator`: 음수 검증 구현

**장점**:
- 검증 흐름이 일관성 있게 유지됨
- 새로운 검증 규칙 추가가 용이

### 4. **Composite Pattern (복합체 패턴)**
**위치**: `CompositeNumberValidator`

- **목적**: 여러 검증기를 하나처럼 다룸
- **구현**: 여러 `NumberValidator`를 조합하여 순차적으로 실행

**장점**:
- 여러 검증 규칙을 쉽게 조합 가능
- 검증 로직의 재사용성 증가

### 5. **Facade Pattern (파사드 패턴)**
**위치**: `Calculator` 클래스

- **목적**: 복잡한 서비스 계층을 간단한 인터페이스로 제공
- **구현**: `CalculationService`의 복잡성을 숨기고 간단한 `calculate()` 메서드만 노출

**장점**:
- 기존 코드와의 호환성 유지
- 클라이언트 코드가 단순해짐

### 6. **MVC Pattern (Model-View-Controller)**
**위치**: 전체 구조

- **Model**: `Calculator`, `CalculationService`, parser, validator 패키지
- **View**: `CalculatorView`
- **Controller**: `CalculatorController`

**장점**:
- 관심사의 명확한 분리
- 각 레이어를 독립적으로 테스트 가능
- 유지보수성 향상

### 7. **Chain of Responsibility Pattern (책임 연쇄 패턴)**
**위치**: `DelimiterParserFactory`

- **목적**: 여러 파서 중 처리 가능한 파서가 요청을 처리
- **구현**: 파서 목록을 순회하며 `canParse()`로 처리 가능 여부 확인

**장점**:
- 파서 추가/제거가 유연
- 파싱 우선순위 쉽게 조정 가능

## 프로젝트 구조

```
calculator/
├── Application.java                  # 애플리케이션 진입점
├── Calculator.java                   # Facade - 간단한 인터페이스 제공
├── CalculatorController.java         # MVC Controller
├── CalculatorView.java              # MVC View
├── parser/                          # Strategy + Factory 패턴
│   ├── DelimiterParser.java         # 전략 인터페이스
│   ├── ParseResult.java             # DTO (불변 객체)
│   ├── DefaultDelimiterParser.java  # 기본 구분자 전략
│   ├── CustomDelimiterParser.java   # 커스텀 구분자 전략
│   └── DelimiterParserFactory.java  # Factory + Chain of Responsibility
├── validator/                       # Template Method + Composite 패턴
│   ├── NumberValidator.java         # 검증 템플릿
│   ├── NegativeNumberValidator.java # 음수 검증 구현
│   └── CompositeNumberValidator.java # 복합 검증기
└── service/
    └── CalculationService.java      # 비즈니스 로직 계층
```

## SOLID 원칙 준수

### 1. **SRP (Single Responsibility Principle)**
- 각 클래스는 하나의 책임만 가짐
- 예: `CustomDelimiterParser`는 커스텀 구분자 파싱만 담당

### 2. **OCP (Open-Closed Principle)**
- 확장에는 열려있고 수정에는 닫혀있음
- 새로운 파서나 검증기 추가 시 기존 코드 수정 불필요

### 3. **LSP (Liskov Substitution Principle)**
- 서브타입은 기반 타입으로 대체 가능
- 모든 `DelimiterParser` 구현체는 인터페이스로 대체 가능

### 4. **ISP (Interface Segregation Principle)**
- 인터페이스는 작고 구체적임
- `DelimiterParser`는 꼭 필요한 메서드만 정의

### 5. **DIP (Dependency Inversion Principle)**
- 구체적인 구현이 아닌 추상화에 의존
- `CalculationService`는 `DelimiterParser` 인터페이스에 의존

## 확장성

이 구조를 통해 다음과 같은 확장이 쉽게 가능합니다:

1. **새로운 구분자 타입 추가**: `DelimiterParser` 구현체 추가
2. **새로운 검증 규칙**: `NumberValidator` 서브클래스 추가
3. **다른 연산 추가**: `CalculationService` 확장
4. **UI 변경**: `CalculatorView`만 수정

## 테스트 용이성

각 컴포넌트가 독립적이므로:
- 파서 단위 테스트
- 검증기 단위 테스트
- 서비스 레이어 통합 테스트
- Controller 단위 테스트

모두 독립적으로 수행 가능합니다.
