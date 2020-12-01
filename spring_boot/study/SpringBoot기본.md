## REST
 HTTP 프로토콜에 있는 Method를 활용한 아키텍처 스타일으로서, HTTP Method를 이용하여 Resource를 통일되고 한정적인 인터페이스로 조작(처리)한다.

|HTTP Method|동작|URL형태|
|--|--|--|
|GET|조회( SELECT * READ )|/user/{id}|
|POST|생성( CREATE )|/user|
|PUT/PATCH|수정( UPDATE ) * CREATE|/user|
|DELETE|삭제( DELETE )|/user/{1}|

**REST API의 주요 규칙**
1. URI는 정보의 리소스를 표현해야 함.(리소스명은 동사보다는 명사를 사용)
2. 리소스에 대한 행위는 HTTP method(GET,POST,PUT,DELETE)로 표현
```
//잘못된 URI, URI에 행위에 대한 표현이 들어가서는 안됨.   
GET /members/delete/1

//위의 예시를 수정   
DELETE /members/1
```



## Lombok
어노테이션을 통해 코딩(타이핑)하지 않더라도 멤버변수에 대한 생성자나 get() / set()을 자동적으로 생성해주는 plug-in으로서 코드를 간략하게 해주는 장점이 있음.



## JPA(Java Persistent API)
ORM(Object Relational Mapping)으로, RDB 데이터 베이스의 정보를 객체지향형태로 손쉽게 활용할 수 있도록 도와주는 툴



#### Entity
JPA에서 테이블을 자동으로 생성해주는 기능 존재(JPA Entity ==> DB Table )
*  JPA Entity는 클래스의 멤버변수들을 DB의 format에 맞게 변환시켜줌
*   Java의 Camel Case를 DB의 snake_case에 매칭시켜줌. 


|Annotation|용도|
|---|---|
|@Entity|해당 클래스가 Entity임을 명시|
|@Table|실제 DB테이블의 이름을 명시, 클래스명과 실제 DB테이블의 이름이 동일하다면 생략가능|
|@Id|Index primary key를 명시|
|@GeneratedValue|Primary key 식별키의 전략 설정|
|@Column|실제 DB Column의 이름을 명시, Column의 이름과 동일하다면 생략 가능|



#### JPA 연관관계 설정 배우기
해당 멤버변수(Column)를 가지고 있는 클래스를 기준으로 관계를 정함
```
@Entity 
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    // 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderDetail> orderDetailList;

}

@Entity
public class OrderDetail{
	@Id
	@GenerateValue(strategy = GenerationType.IDENTITY)
	
	...
	
	// N : 1
	@ManyToOne
	private User user; // user_id
	
}
```
-> OrderDetail 입장에서는 자신이 다수의 입장이고 User(user_id)가 1이므로 **N:1**의 관계를 갖는다. (user_id대신 User객체를 사용함.)

-> 반면 User입장에서는 자신이 1의 입장이고, OrderDetail이 다수이므로 **1:N**의 관계를 갖는다. 따라서 다수의 OrderDetail을 처리하기위해 List를 사용한다.



**Entity 관계 Annotation**

|관계 |Annotation|
|--|:--|
|일대일|@OneToOne|
|일대다|@OneToMany(fetch = FetchType.xxx, mappedBy ="variable")|
|다대일|@ManyToOne|
|다대다|@ManyToMany|



**FetchType**
* Lazy: 지연로딩, 해당 테이블에 대해서만 JOIN
	SELECT * FROM item where id = ?
* Eager: 즉시로딩, 모든 테이블에 대해서 JOIN이 진행됨, 1:1관계에 대해서만 사용하는걸 추천
	item_id = order_detail.item_id
	user_id = order_detail.user_id



## Repository
따로 쿼리문을 작성하지 않아도 기본적인 인터페이스를 통해서 CRUD를 사용할 수 있는 기능
**CRUD:** **C**REATE, **R**EAD(SELECT), **U**PDATE, **D**ELETE



1. @Repository 어노테이션과 JpaRepository(Entity, Id)를 상속받아 해당 Entity에 대한 Repository 생성

```
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	...
```

2. 해당 Entity에 대한 Repository에 Query Method를 추가해서 사용할 수 있음.
```
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Query Method 생성
    // findBy***, JPA에서findBy를 접두사로 사용하게 되면 하기와 같이 뒤에오는 표기이 대하여 select하는 쿼리문을 수행하는 method 생성

    // select from user where account = ?
    Optional<User> findByAccount(String account);

    // select from user where email = ?
    Optional<User> findByEmail(String email);

    // select from user where account = ? and email = ?
    Optional<User> findByAccountAndEmail(String account, String email);
}
```
