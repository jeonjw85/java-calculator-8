# java-calculator-precourse
프리코스 1주차 자바 문자열 덧셈 계산기

## 구현항목

### 입력 기능
- 문자열 입력
- 빈 문자열 입력 처리

### 구분자 처리 기능
- 기본 구분자(쉼표, 콜론) 인식
- 커스텀 구분자 추출 (`//` 와 `\n` 사이의 문자)
- 커스텀 구분자와 기본 구분자 같이 사용

### 숫자 추출 및 계산 기능
- 문자열 분리
- 분리된 문자열을 숫자로 변환
- 모든 숫자 합 계산
- 빈 문자열인 경우 0 반환

### 예외 처리 기능
- 음수 입력 시 `IllegalArgumentException` 발생
- 숫자가 아닌 값 입력 시 `IllegalArgumentException` 발생

### 출력 기능
- 계산 결과를 "결과 : {sum}" 형식으로 출력

---

## 프로젝트 구조

### 디자인 패턴 적용
이 프로젝트는 객체지향 설계 원칙과 여러 디자인 패턴을 적용하여 구현되었습니다.

```
calculator/
├── Application.java                      # 애플리케이션 진입점
├── Calculator.java                       # Facade Pattern - 간단한 인터페이스 제공
├── CalculatorController.java             # MVC Controller - 흐름 제어
├── CalculatorView.java                   # MVC View - 사용자 인터페이스
├── parser/                               # Strategy Pattern + Factory Pattern
│   ├── DelimiterParser.java              # 전략 인터페이스
│   ├── ParseResult.java                  # DTO (불변 객체)
│   ├── DefaultDelimiterParser.java       # 기본 구분자 처리 전략
│   ├── CustomDelimiterParser.java        # 커스텀 구분자 처리 전략
│   └── DelimiterParserFactory.java       # 파서 생성 팩토리
├── validator/                            # Template Method Pattern + Composite Pattern
│   ├── NumberValidator.java              # 검증 템플릿 추상 클래스
│   ├── NegativeNumberValidator.java      # 음수 검증 구현
│   └── CompositeNumberValidator.java     # 복합 검증기
└── service/
    └── CalculationService.java           # 비즈니스 로직 계층
```

### 적용된 디자인 패턴

#### 1. **Strategy Pattern (전략 패턴)**
- **위치**: `calculator.parser` 패키지
- **목적**: 구분자 파싱 방식을 동적으로 선택
- **효과**: 새로운 구분자 타입 추가 시 기존 코드 수정 불필요 (OCP 준수)

#### 2. **Factory Pattern (팩토리 패턴)**
- **위치**: `DelimiterParserFactory`
- **목적**: 입력에 따라 적절한 파서 생성
- **효과**: 객체 생성 로직 중앙화, 클라이언트 코드 간소화

#### 3. **Template Method Pattern (템플릿 메서드 패턴)**
- **위치**: `calculator.validator.NumberValidator`
- **목적**: 검증 흐름을 정의하고 구체적 로직은 서브클래스에서 구현
- **효과**: 일관된 검증 인터페이스, 새로운 검증 규칙 추가 용이

#### 4. **Composite Pattern (복합체 패턴)**
- **위치**: `CompositeNumberValidator`
- **목적**: 여러 검증기를 하나처럼 다룸
- **효과**: 검증 로직의 재사용성 증가, 쉬운 조합

#### 5. **Facade Pattern (파사드 패턴)**
- **위치**: `Calculator` 클래스
- **목적**: 복잡한 서비스 계층을 간단한 인터페이스로 제공
- **효과**: 기존 코드와의 호환성 유지, 클라이언트 코드 단순화

#### 6. **MVC Pattern**
- **Model**: `Calculator`, `CalculationService`, parser, validator 패키지
- **View**: `CalculatorView` - 사용자 인터페이스 담당
- **Controller**: `CalculatorController` - 흐름 제어
- **효과**: 관심사의 명확한 분리, 독립적인 테스트 가능

#### 7. **Chain of Responsibility Pattern (책임 연쇄 패턴)**
- **위치**: `DelimiterParserFactory`
- **목적**: 처리 가능한 파서가 요청을 처리
- **효과**: 파서 추가/제거 유연, 우선순위 조정 용이

### SOLID 원칙 준수

- **SRP (단일 책임 원칙)**: 각 클래스는 하나의 책임만 가짐
- **OCP (개방-폐쇄 원칙)**: 확장에는 열려있고 수정에는 닫혀있음
- **LSP (리스코프 치환 원칙)**: 서브타입은 기반 타입으로 대체 가능
- **ISP (인터페이스 분리 원칙)**: 인터페이스는 작고 구체적
- **DIP (의존성 역전 원칙)**: 구체적인 구현이 아닌 추상화에 의존

### 주요 클래스 설명

#### `CalculationService`
- 계산 비즈니스 로직의 핵심
- `DelimiterParserFactory`를 통해 적절한 파서 선택
- `CompositeNumberValidator`로 숫자 검증 수행

#### `DelimiterParser` 인터페이스
- 구분자 파싱 전략을 정의
- `DefaultDelimiterParser`: 기본 구분자(`,`, `:`) 처리
- `CustomDelimiterParser`: 커스텀 구분자 처리

#### `NumberValidator` 추상 클래스
- 템플릿 메서드 패턴으로 검증 흐름 정의
- `NegativeNumberValidator`: 음수 검증 구현
- 새로운 검증 규칙 추가 시 서브클래스만 작성

### 확장 가능성

이 구조를 통해 다음과 같은 확장이 쉽게 가능합니다:

1. **새로운 구분자 타입 추가**: `DelimiterParser` 인터페이스 구현
2. **새로운 검증 규칙 추가**: `NumberValidator` 추상 클래스 상속
3. **다른 연산 추가**: `CalculationService` 확장
4. **UI 변경**: `CalculatorView`만 수정

### 테스트 용이성

각 컴포넌트가 독립적이므로:
- 파서 단위 테스트
- 검증기 단위 테스트
- 서비스 레이어 통합 테스트
- Controller 단위 테스트

모두 독립적으로 수행 가능합니다.

---

## 추가 문서

- **[DESIGN_PATTERNS.md](./DESIGN_PATTERNS.md)**: 각 디자인 패턴의 상세 설명과 적용 이유
- **[ARCHITECTURE.md](./ARCHITECTURE.md)**: 클래스 다이어그램, 시퀀스 다이어그램 및 아키텍처 설명
- **[TEST_README](./src/test/TEST_README.md)**: 테스트 코드 구조 및 설명
