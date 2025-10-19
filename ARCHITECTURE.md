# 디자인 패턴

## 시퀀스 다이어그램

### 정상적인 계산 흐름:
```
User → Application → Controller → View → User (프롬프트 출력)
User → Console Input → View → Controller
Controller → Calculator → CalculationService
CalculationService → ParserFactory → 적절한 Parser 선택
Parser → CalculationService (파싱 결과 반환)
CalculationService → Validator (각 숫자 검증)
CalculationService → Controller (결과 반환)
Controller → View → User (결과 출력)
```

### 예외 발생 시:
```
User → Application → Controller → Calculator → Service
Service → Validator → Exception (음수 발견)
Exception → Service → Calculator → Controller
Controller → User (예외 전파)
```

## 패턴별 책임

### Strategy Pattern (전략 패턴)
**문제**: 다양한 구분자 파싱 방식을 어떻게 유연하게 처리할까?
**해결**: 파싱 알고리즘을 인터페이스로 추상화하고 여러 구현체 제공
**결과**: 새로운 구분자 타입 추가 시 기존 코드 수정 불필요

### Factory Pattern (팩토리 패턴)
**문제**: 입력에 따라 적절한 파서를 어떻게 선택할까?
**해결**: 팩토리가 입력을 분석하여 적절한 파서 생성
**결과**: 클라이언트는 구체적인 파서 구현을 몰라도 됨

### Template Method Pattern (템플릿 메서드 패턴)
**문제**: 검증 흐름은 동일하지만 구체적인 검증 로직은 다를 때?
**해결**: 추상 클래스에서 검증 흐름 정의, 서브클래스에서 구현
**결과**: 일관된 검증 인터페이스와 확장 가능한 검증 규칙

### Composite Pattern (복합체 패턴)
**문제**: 여러 검증기를 하나처럼 다루려면?
**해결**: 검증기 목록을 가지고 순차적으로 실행
**결과**: 검증 규칙의 쉬운 조합과 재사용

### Facade Pattern (파사드 패턴)
**문제**: 복잡한 서비스 레이어를 간단하게 사용하려면?
**해결**: 간단한 인터페이스 제공하여 복잡성 숨김
**결과**: 클라이언트 코드 간소화 및 기존 코드와의 호환성

### MVC Pattern
**문제**: UI, 비즈니스 로직, 제어 흐름을 어떻게 분리할까?
**해결**: Model, View, Controller로 역할 분리
**결과**: 각 레이어의 독립적 개발 및 테스트 가능

## 패턴 선택 이유

1. **Strategy + Factory**: 구분자 처리가 확장 가능해야 함
2. **Template Method**: 검증 로직의 일관성과 확장성
3. **Composite**: 여러 검증 규칙의 조합
4. **Facade**: 기존 API 호환성 유지
5. **MVC**: 전체적인 구조의 명확성