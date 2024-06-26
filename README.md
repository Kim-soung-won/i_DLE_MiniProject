## 프로젝트 기획 의도
* JPA, SpringBoot심화에 대한 학습 내용 실습을 위한 사이드 프로젝트
* 이후 실시할 팀 프로젝트에서 팀원을 리드함에 있어 막힘이 없을 수 있도록 다양한 트러블 슈팅 경험
* 팀원들이 SpringBoot 프로젝트 간에 예시 및 참고 용으로 활용할 수 있는 가이드 제공  

<hr>

### <a href="https://github.com/Kim-soung-won/Team_Idle/tree/Readme">팀 프로젝트 Git 링크</a>  

<hr>

### 트러블 슈팅1 [JPA 동적 쿼리 작성 문제]
<a href="https://rlatmddnjs0103.tistory.com/214">작성한 블로그 링크</a>  
🚨 **문제 배경**
실습용 프로젝트에서 JPA 사용간에 동적 쿼리 작성에 어려움을 겪었다. JPA가 Select 결과를 정렬하는 과정에서 Join을 통해 가져온 Column을 기준으로 정렬을 수행하지 못하였다.
JPA는 Pageable을 통해 정렬을 수행하는데 Join을 통해 가져온 Entity에 속하지 않은 Column은 대상으로 지정이 안되었다.

⭐️ **해결 방법**

JPA와 MyBatis를 하나의 프로젝트에서 같이 사용하여서 해결하였다. Join과 다양한 정렬 조건을 함께 요구하는 조회쿼리에 대해서는 MyBatis를 사용하여 따로 select문을 작성하였다.

🤩 **해당 경험을 통해 알게된 점**

MyBatis가 갖는 장점에 대해서 느끼게 되었다. 컴파일 과정에서 Query의 유효성을 알 수 있는 기능은 놓쳤지만 비교적 빠르게 기능 구현을 해야하는 상황에서는 직관적이라서 쉽게 사용할 수 있는 것 같다. 아마 JPA의 심화과정인 QueryDSL을 사용하면 동일한 작업을 JPA로도 할 수 있다고 하는데, 추가적인 학습이 필요할 것 같다.  

<hr>


### 트러블 슈팅2 [@Transaction 누락으로 인한 다량의 조회 쿼리 발생]

[블로그 링크](https://rlatmddnjs0103.tistory.com/190)

🚨 **문제 배경**

@Transaction 누락으로 인한 추가 조회 쿼리 발생, JPA 사용간 데이터베이스 쓰기 작업에 사용되는 쿼리를 실행할 때는 조회가 선행된다. 하지만 예외처리를 위해 미리 조회한 데이터가 있기 때문에 조회할 필요가 없었는데, @Transacion 어노테이션을 빼먹으면 영속성 컨텍스트에 조회쿼리의 결과가 캐싱되지 않아 한번 더 조회쿼리가 발생한 것이었다.

⭐️ **해결 방법**

빼먹은 @Transation어노테이션을 다시 붙여 넣었다.

🤩 **해당 경험을 통해 알게된 점**

짧은, 한번의 쓰기 작업에도 Transaction 어노테이션을 꼭 붙여야한다는 것을 느꼈다. 또한 단순 조회 작업에도 @Transaction(readOnly = True)를 붙여 DB와 연결을 수행할 때 커넥션 시간을 단축할 수 있었다. 습관적으로 Service단의 메서드에는 Transaction을 붙여야할 것 같다.
