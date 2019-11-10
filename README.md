# aluco-api

### Add the teacher id on token;
1. Import the JwtTokenUtil in the Controller used.
```java
@Autowired
private JwtTokenUtil jwtTokenUtil;
```
2. add HttpServletRequest as parameter for your method in the controller used.
```java
@GetMapping
public ResponseEntity<Set<ClassDTO>> getAll(HttpServletRequest request) {
```
3. retrieve the teacherId from jwtTokenUtil
```java
Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
```

### Creating custom repository to aux Spring Data, working with `JdbcTemplate`
1. create Interface for your custom repository with the method to be implemented
```java
public interface CustomUserRepository {
	public Long getTeacherIdByUsername(String username);
}
```
2. create a class that implement the interface and also receives JdbcTemplate object
```java
@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {
	
  @Autowired
  JdbcTemplate jdbcTemplate;
```

3. implement the query and use jdbcTemplate to do the query
```java
@Override
public Long getTeacherIdByUsername(String username) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT te.id FROM teacher te ");
		sql.append(" INNER JOIN user_aluco au ON au.id = te.id ");
		sql.append(" WHERE au.email = ? ");

		return jdbcTemplate.queryForObject(sql.toString(), new Object[]{username}, Long.class);
}
```

4. Add the custom interface to be extended by the interface JpaRepository of the resource used
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
```
