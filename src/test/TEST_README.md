# 테스트 코드 구조 및 설명

## 테스트 파일 구조

```
src/test/java/calculator/
├── ApplicationTest.java                          # 통합 테스트 (기존 유지)
├── CalculatorTest.java                           # Facade 레이어 테스트
├── CalculatorViewTest.java                       # View 레이어 테스트
├── parser/
│   ├── DefaultDelimiterParserTest.java          # 기본 구분자 파서 단위 테스트
│   ├── CustomDelimiterParserTest.java           # 커스텀 구분자 파서 단위 테스트
│   ├── DelimiterParserFactoryTest.java          # 팩토리 패턴 테스트
│   └── ParseResultTest.java                     # DTO 테스트
├── validator/
│   ├── NegativeNumberValidatorTest.java         # 음수 검증기 단위 테스트
│   └── CompositeNumberValidatorTest.java        # 복합 검증기 단위 테스트
└── service/
    └── CalculationServiceTest.java              # 비즈니스 로직 통합 테스트
```

## 테스트 레벨별 분류

### 1. 단위 테스트 (Unit Tests)

#### Parser 패키지
- **`DefaultDelimiterParserTest`** (5개 테스트)
  - 기본 구분자 파싱 로직 검증
  - 쉼표(`,`)와 콜론(`:`) 구분자 처리
  - 빈 문자열 및 단일 숫자 처리

- **`CustomDelimiterParserTest`** (8개 테스트)
  - 커스텀 구분자 추출 및 파싱
  - 특수문자 이스케이프 처리
  - 잘못된 형식 예외 처리
  - 여러 문자로 된 구분자 처리

- **`ParseResultTest`** (3개 테스트)
  - DTO 불변성 검증
  - 데이터 저장 및 조회 확인

#### Validator 패키지
- **`NegativeNumberValidatorTest`** (5개 테스트)
  - 음수 검증 로직 테스트
  - 양수, 0, 음수 케이스 검증
  - Template Method 패턴 구현 검증

- **`CompositeNumberValidatorTest`** (5개 테스트)
  - 복합 검증기 동작 확인
  - 여러 검증기 조합 테스트
  - Composite 패턴 구현 검증

#### Factory 패키지
- **`DelimiterParserFactoryTest`** (5개 테스트)
  - Factory 패턴 구현 검증
  - 입력에 따른 올바른 파서 선택
  - Chain of Responsibility 패턴 동작 확인

### 2. 통합 테스트 (Integration Tests)

- **`CalculationServiceTest`** (16개 테스트)
  - Parser + Validator 통합 동작 확인
  - 다양한 입력 케이스 시나리오 테스트
  - 엣지 케이스 및 예외 상황 검증

### 3. 레이어 테스트 (Layer Tests)

- **`CalculatorTest`** (7개 테스트)
  - Facade 패턴 동작 확인
  - Service 레이어와의 연동 검증
  - 기존 API 호환성 확인

- **`CalculatorViewTest`** (5개 테스트)
  - View 출력 로직 검증
  - 포맷팅 정확성 확인
  - UI 독립성 테스트

### 4. E2E 테스트 (End-to-End Tests)

- **`ApplicationTest`** (2개 테스트) - 기존 유지
  - 전체 애플리케이션 흐름 검증
  - 실제 사용자 시나리오 테스트

##  테스트 커버리지 목표

| 레이어 | 클래스 | 테스트 수 | 주요 검증 항목 |
|--------|--------|-----------|---------------|
| **Parser** | 4개 | 21개 | 구분자 파싱, 팩토리 패턴, Strategy 패턴 |
| **Validator** | 3개 | 10개 | 검증 로직, Template Method, Composite 패턴 |
| **Service** | 1개 | 16개 | 비즈니스 로직 통합, 엣지 케이스 |
| **Facade** | 1개 | 7개 | API 호환성, 간소화된 인터페이스 |
| **View** | 1개 | 5개 | 출력 포맷, UI 독립성 |
| **E2E** | 1개 | 2개 | 전체 시나리오 |
| **합계** | **11개** | **61개** | - |

## 테스트 전략

### Given-When-Then 패턴
모든 테스트는 Given-When-Then 패턴을 따릅니다:
```java
@Test
void testExample() {
    // given: 테스트 준비
    String input = "1,2,3";
    
    // when: 실행
    int result = calculator.calculate(input);
    
    // then: 검증
    assertThat(result).isEqualTo(6);
}
```

### @DisplayName 활용
모든 테스트에 한글 설명을 추가하여 가독성을 높였습니다.

### 독립성 보장
- 각 테스트는 독립적으로 실행 가능
- `@BeforeEach`로 초기화 상태 보장
- 테스트 간 의존성 없음

## 주요 테스트 케이스

### 정상 케이스
-  빈 문자열 → 0
-  null → 0
-  단일 숫자
-  기본 구분자 (쉼표, 콜론)
-  커스텀 구분자
-  혼합 구분자
-  특수문자 구분자
-  큰 숫자

### 예외 케이스
-  음수 입력 → `IllegalArgumentException`
-  숫자가 아닌 값 → `IllegalArgumentException`
-  잘못된 구분자 형식 → `IllegalArgumentException`

### 엣지 케이스
-  빈 토큰 무시
-  연속된 구분자
-  구분자만 있는 입력
-  여러 문자로 된 구분자
-  실제 줄바꿈 vs 이스케이프 문자

## 테스트 실행 방법

### 전체 테스트 실행
```bash
./gradlew test
```

### 특정 패키지 테스트 실행
```bash
# Parser 테스트만 실행
./gradlew test --tests "calculator.parser.*"

# Validator 테스트만 실행
./gradlew test --tests "calculator.validator.*"

# Service 테스트만 실행
./gradlew test --tests "calculator.service.*"
```

### 특정 클래스 테스트 실행
```bash
./gradlew test --tests "calculator.CalculatorTest"
```

### 특정 메서드 테스트 실행
```bash
./gradlew test --tests "calculator.CalculatorTest.calculateEmptyString"
```

## 테스트 작성 원칙

### 1. FIRST 원칙
- **F**ast: 빠른 실행
- **I**ndependent: 독립적 실행
- **R**epeatable: 반복 가능
- **S**elf-validating: 자체 검증
- **T**imely: 적시 작성

### 2. 테스트 네이밍
- 한글 `@DisplayName` 사용
- 행위 중심 명명
- "~한다", "~를 반환한다" 형식

### 3. Assertion 라이브러리
- **AssertJ** 사용
- 가독성 높은 Fluent API
- 풍부한 에러 메시지

### 4. 테스트 범위
- 각 레이어별 책임 범위 내 테스트
- 하위 레이어는 Mock 없이 실제 객체 사용
- 통합 테스트는 전체 흐름 검증

## 디자인 패턴별 테스트 전략

### Strategy Pattern (Parser)
- 각 전략 구현체를 독립적으로 테스트
- 인터페이스 계약 준수 확인
- `canParse()` 조건 검증

### Factory Pattern (ParserFactory)
- 입력에 따른 올바른 객체 생성 확인
- 팩토리 로직의 정확성 검증

### Template Method Pattern (Validator)
- 템플릿 메서드 흐름 검증
- 서브클래스별 구체적 구현 테스트
- 추상 메서드 구현 확인

### Composite Pattern (CompositeValidator)
- 여러 검증기 조합 동작 확인
- 순차적 검증 실행 검증
- 첫 번째 실패 시 예외 전파 확인

### Facade Pattern (Calculator)
- 간소화된 인터페이스 동작 확인
- 복잡한 내부 로직 은닉 검증
- API 호환성 유지 확인

### MVC Pattern
- Controller: 흐름 제어 로직
- Model: 비즈니스 로직
- View: 출력 포맷
- 각 레이어 독립적 테스트

## 테스트 코드 품질

### 코드 리뷰 체크리스트
- [ ] Given-When-Then 구조 준수
- [ ] @DisplayName 한글 설명 작성
- [ ] 독립적 실행 가능
- [ ] 명확한 Assertion 메시지
- [ ] 엣지 케이스 포함
- [ ] 예외 케이스 검증
- [ ] 테스트 이름의 명확성

### 유지보수성
- 각 테스트는 하나의 개념만 검증
- 테스트 코드도 리팩토링 대상
- 중복 제거 (헬퍼 메서드 활용)
- 가독성 최우선

## 지속적 개선

### 추가할 수 있는 테스트
1. **성능 테스트**: 큰 입력에 대한 처리 시간
2. **파라미터 테스트**: `@ParameterizedTest` 활용
3. **경계값 테스트**: Integer.MAX_VALUE 등
4. **동시성 테스트**: 멀티스레드 환경

### 리팩토링 시 고려사항
- 기존 테스트가 통과하는지 확인
- 새로운 기능 추가 시 테스트 먼저 작성 (TDD)
- 테스트 실패 시 원인 명확히 파악
